package tibame.tga102.yokult.doctor.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tibame.tga102.yokult.booking.vo.CheckinVO;
import tibame.tga102.yokult.booking.vo.Doctor;
import tibame.tga102.yokult.booking.vo.DoctorChartVO;
import tibame.tga102.yokult.booking.vo.DoctorConvert;
import tibame.tga102.yokult.booking.vo.DoctorSchedule;
import tibame.tga102.yokult.booking.vo.Patient;
import tibame.tga102.yokult.doctor.service.DoctorService;
import tibame.tga102.yokult.util.YokultConstants;

@Controller
@ResponseBody
@CrossOrigin
@RequestMapping(path= {YokultConstants.DOCTOR_API})
public class DoctorApiController extends HttpServlet {
	@Autowired
	DoctorService doctorServiceImpl;
	

	private static final long serialVersionUID = 1L;
    public DoctorApiController() {
        super();
    }
    
    @InitBinder
    public void register(WebDataBinder webDataBinder) {
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    	CustomDateEditor editor = new CustomDateEditor(sdf, true);
    	webDataBinder.registerCustomEditor(java.sql.Date.class, editor);
    }
    
// 過濾同樣dr 有報到過的病人們 身分證字號名單
    @GetMapping(path = {"/getDrPatientIdcard"})
    public Map<String, Object> getDrPatientIdcard(@RequestParam String doctorId){
		Doctor doctor = new Doctor();
		try {
			doctor.setDoctorId(Integer.valueOf(doctorId));
			Set<String> set = doctorServiceImpl.returnDrPatientIdcard(doctor);
			if (set == null) {
				return toFrontEnd("get no Patients booked");
			} else {
				return toFrontEnd("get patient id success", "IDSet", set);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return toFrontEnd("getDrPatientID failure");
		}
    }
    
// 回傳index_doctor_chart.html 的  依據所選的醫師和有報到的病患的身分證字號 回傳看診日期選單
    @PostMapping(path= {"/getDrPatientDates"})
    public Map<String, Object> getDrPatientDates(@RequestBody DoctorChartVO doctorChartVO){
    	System.out.println("[doctor servlet] doctorChartVO = "+ doctorChartVO);
    	Doctor doctor = new Doctor();
    	Patient patient = new Patient();
		try {
			doctor.setDoctorId(doctorChartVO.getDoctorId());
			patient.setPatientIdcard(doctorChartVO.getPatientIdcard());
			List<Date> list = doctorServiceImpl.returnDrPatientDates(doctor, patient);
			if (list != null) {
				System.out.println("servelt: return List<Date>");
				return toFrontEnd("get patient dates success", "list", list);
			}
			if (list == null) {
				return toFrontEnd("get no Patients booked");
			} 
		} catch (Exception e) {
			System.out.println("getDrPatientDates failure");
			e.printStackTrace();
		}
		return toFrontEnd("getDrPatientDates failure");
	}    

//顯示原本的病歷紀錄
    @PostMapping(path = {"/returnChart"})
    public Map<String, Object> returnChart(@RequestBody DoctorChartVO doctorChartVO){
    	System.out.println("[doctorServlet returnChart doctorChartVO = ]" + doctorChartVO);
		Doctor doctor = new Doctor();
		Patient patient = new Patient();
		try {
    		doctor.setDoctorId(doctorChartVO.getDoctorId());
    		patient.setPatientIdcard(doctorChartVO.getPatientIdcard());
    		patient.setBookingDate(doctorChartVO.getBookingDate());
			Patient patientChart = doctorServiceImpl.returnDrPatientChart(doctor, patient);
			if (patientChart != null) {
				return toFrontEnd("returnChart success", "list", patientChart.getChart());
			} else {
				return toFrontEnd("success no Chart ");
			}
		} catch (Exception e) {
			System.out.println("returnChart failure");
			e.printStackTrace();
		}
		return toFrontEnd("returnChart failure"); 
    }

    //儲存前端傳來的病歷紀錄
	@PostMapping(path = {"/saveChart"})
	public Map<String, Object> saveChart(@RequestBody Patient patient){
		String reg = "^[A-Z]{1}[01]{1}[0-9]{8}$";
		String regDate = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";
		//過濾
		if(!patient.getPatientIdcard().matches(reg)) {
			return toFrontEnd("no idcard");
		}
		if(!patient.getBookingDate().toString().matches(regDate)) {
			return toFrontEnd("no Date");
		}
		int result = 0;
		result = doctorServiceImpl.updateChart(patient);
		if (result == 1) {
			return toFrontEnd("updateChart success");
		}
			
		return toFrontEnd("updateChart failure");
	}
	
//儲存醫師資料
	@PostMapping(path = {"/saveDr"})
	public Map<String, Object> saveDr(@RequestBody DoctorConvert doctorConvert){
			System.out.println("[DoctorServlet]: save dr start");
			try {
				if(doctorConvert.getDoctorName().length() == 0) {
					return toFrontEnd("no name");
				}
				if(doctorConvert.getDoctorPhoto() == null) {
					return toFrontEnd("no photo");
				}
				int result = doctorServiceImpl.updateDr(doctorConverter(doctorConvert));
				if (result == 1) {
					return toFrontEnd("updateDr success");
				} 
			} catch (Exception e) {
				System.out.println("[DoctorServlet saveDr]Exception");
				e.printStackTrace();
			}
			return toFrontEnd("updateDr failure");
	}
	
// 回傳醫師資料
	@PostMapping(path= {"/loadDr"})
	public Map<String, Object> loadDr(@RequestBody Doctor doctor){
			DoctorConvert vo = doctorServiceImpl.selectOne(doctor);
			if (vo != null) {
				return toFrontEnd("load Dr success", "doctor", removeNullProperty(vo));
			} else {
				return toFrontEnd("load Dr failure");
			}
	}
	
	@PostMapping(path = {"/nextOne"})
	public Map<String, Object> nextOne(@RequestBody Doctor doctor){
		try {
			CheckinVO checkinVO = doctorServiceImpl.nextOne(doctor);
			if(checkinVO != null) {
				return toFrontEnd("nextOne success", "checkinVO",  checkinVO);		
			}
		} catch (Exception e) {
			System.out.println("[DoctorServlet nextOne]");
			e.printStackTrace();
		}
		return toFrontEnd("finish");
	}
	
//寫到這裡
	@PutMapping(path= {"/updateDrSchedule"})
	public Map<String, Object> updateDrSchedule(@RequestBody DoctorConvert doctorConvert, BindingResult bindingResult ){
		System.out.println("[Test] get doctor convert");
		if(bindingResult != null && bindingResult.hasFieldErrors("doctorSchedule")) {
			System.out.println(bindingResult.getFieldError("doctorSchedule"));
			
		}
		List<DoctorSchedule> list = doctorConvert.getListOfDoctorSchedule();
		int result = doctorServiceImpl.saveDrSchedule(list);
		if(result > 0) {
			return toFrontEnd("save DrSchedule success");
		} 
			return toFrontEnd("save DrSchedule failure");
	}

	
	public Map<String, Object> toFrontEnd(String msg, String dataName ,Object data){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", msg);
		map.put(dataName, data);
		return map;
	}
	
	public Map<String, Object> toFrontEnd(String msg){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("msg", msg);
		return map;
	}
	
	private Doctor doctorConverter(DoctorConvert doctorConvert) {
		Doctor doctor = new Doctor();
		doctor.setDoctorName(doctorConvert.getDoctorName());
		doctor.setDoctorId(doctorConvert.getDoctorId());
		String str = doctorConvert.getDoctorPhoto();
		if(str == null) {
			return null;
		}
		str = str.substring(str.indexOf(",") + 1);
		doctor.setDoctorPhoto(Base64.getDecoder().decode(str));
//		doctor.setDoctorPhoto(doctorConvert.getDoctorPhoto().getBytes()); //bad idea
		return doctor;
	}
	
	private Map<String, String> removeNullProperty(DoctorConvert doctorConvert){
		Map<String, String> map = new HashMap<String, String>();
		map.put("doctorId", doctorConvert.getDoctorId().toString());
		map.put("doctorName", doctorConvert.getDoctorName());
		map.put("doctorPhoto", doctorConvert.getDoctorPhoto());
		return map;
	}
	
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
//		setHeaders(resp);
		// 重要
				response.setHeader("Access-Control-Allow-Origin", "*");
				
				response.setHeader("Access-Control-Allow-Methods", "GET, POST, PATCH, PUT, DELETE, OPTIONS");
				response.setHeader("Access-Control-Allow-Headers", "*");
				response.setHeader("Access-Control-Max-Age", "86400");
				// 重要
				response.setContentType("application/json;charset=UTF-8");
				response.setHeader("Cache-control", "no-cache, no-store");
				response.setHeader("Pragma", "no-cache");
				response.setHeader("Expires", "-1");
	}
	
	private void setHeaders(HttpServletResponse response) {
		// 重要
		response.setHeader("Access-Control-Allow-Origin", "*");
		
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "*");
		response.setHeader("Access-Control-Max-Age", "86400");
		// 重要
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

	}

}
