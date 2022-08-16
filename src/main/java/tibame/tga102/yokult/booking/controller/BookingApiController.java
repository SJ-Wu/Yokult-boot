package tibame.tga102.yokult.booking.controller;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import tibame.tga102.yokult.booking.service.BookingService;
import tibame.tga102.yokult.booking.vo.CheckinVO;
import tibame.tga102.yokult.booking.vo.Doctor;
import tibame.tga102.yokult.booking.vo.Patient;
import tibame.tga102.yokult.doctor.service.DoctorService;
import tibame.tga102.yokult.util.YokultConstants;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping(path = {YokultConstants.BOOKING_API})
public class BookingApiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private BookingService bookingServiceImpl;
	
	@Autowired
	private DoctorService doctorServiceImpl;
	
	@Autowired
	ServletContext servletContext;
	
    public BookingApiController() {
        super();
    }
    
    //回傳醫師上班時間
    @GetMapping(path = {"/drSchedule"})
    public Map<String, Object> drSchedule(@RequestParam String date1, @RequestParam String date2, @RequestParam String doctorId) {
    	String date1sql = date1.replace("/", "-");
    	String date2sql = date2.replace("/", "-");
    	try {
			Map<String, Object> map = bookingServiceImpl.getDoctorScheduleAndDoctorName(Date.valueOf(date1sql), Date.valueOf(date2sql), Integer.valueOf(doctorId));
			return toFrontEnd("sendSchedule success", "schedule", map);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return toFrontEnd("sendSchedule failure");
	}

    //查詢某會員預約資訊
    @GetMapping(path = {"/bookingQuery"})
    public Map<String, Object> bookingQuery(@RequestParam String cinput, @RequestParam String memID, @RequestParam String ctoken){
//		String cpassword = (String)servletContext.getAttribute("cpassword");
//		System.out.println("[bookginservlet getAttribute cpassword]" + cpassword);
		
    	Claims claims = Jwts.parser().setSigningKey(YokultConstants.JWTKEY).parseClaimsJws(ctoken).getBody();
    	claims.get("sub");
		System.out.println("JWT ctoken payload:" + claims.get("sub").toString());
		String strclaims = claims.get("sub").toString();
    	
		if(strclaims != null && !strclaims.equals(cinput)) {
			return toFrontEnd("incorrect captcha");
		}	
		
		try {
			Patient patient = new Patient();
			patient.setMemID(memID);
		//patient傳給service service再查出預約日期
			List<HashMap<String, Object>> list = bookingServiceImpl.getPatientBooking(patient);
			if(list != null) {
				return toFrontEnd("bookingQuery sucess", "list", list);
			} else {
				return toFrontEnd("you have no unchecked booking data");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toFrontEnd(" Exception failure bookingQuery");
    }
    
    //查詢是否初診
    @GetMapping(path = {"/bookingQuery/hasCameHere"})
    public Map<String, Object> bookingQuery(@RequestParam String memID){
		
		try {
			Patient patient = new Patient();
			patient.setMemID(memID);
		//patient傳給service service再查出預約日期
			String id = bookingServiceImpl.getIdcardBymemID(patient);
			if(id != null) {
				return toFrontEnd("booking data sucess", "patientIdcard", id);
			} else {
				return toFrontEnd("you have no booking data");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toFrontEnd(" Exception failure bookingQuery");
    }
    
    //查詢會員看過診日期
    @GetMapping(path= {"/chartQuery"})
    public Map<String, Object> chartQuery(@RequestParam String memID){
    		Patient patient = new Patient();
    		patient.setMemID(memID);

    		try {
				List<Date> list =  bookingServiceImpl.getChartDates(patient);
				if(list != null && list.size() != 0) {
					return toFrontEnd("return date success", "list", list);
				} else {
					return toFrontEnd("you don't see doctor yet");
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
    		return toFrontEnd("Exception chartQuery failure");
    }
    
	//新增checkin方法
    @PostMapping(path= {"/patientCheckin"})
    public Map<String, Object> patientCheckin(@RequestBody Patient patient){
    	int result = 0;
		try {
			String pID = bookingServiceImpl.getIdcardBymemID(patient);
			if(!pID.equals(patient.getPatientIdcard())) {
				return toFrontEnd("not correct IDcard");
			}
			result = bookingServiceImpl.patientCheckIn(patient);
			if (result == 1) {
				return toFrontEnd("checkin success");
			} else {
				return toFrontEnd("you have no booking today");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toFrontEnd("Exception patientCheckin failure");
    }

//新增掛號資料方法 
    @PostMapping(path = {"/receiveBookingRequest"})
    public Map<String, Object> receiveBookingRequest(@RequestBody Patient patient){
		try {

    		if((patient.getPatientIdcard().length() == 0)) {
    			return toFrontEnd("IDCARD didn't key in");
    		}
    		
    		if((patient.getBookingDate() == null)) {
    			return toFrontEnd("TIME didn't key in");
    		}
    		
    		if(!patient.getPatientIdcard().matches("^[A-Z]{1}[1-2]{1}[0-9]{8}$")) {
    			return toFrontEnd("PatientIdcard wrong format");
    		}
    		
			int bookingNumber = bookingServiceImpl.setPatientBooking(patient);
    		if(bookingNumber != -1) {
    			return toFrontEnd("receiveBookingRequest success", "bookingNumber", bookingNumber);
    		}
		} catch (Exception e) {
    			System.out.println("receiveBookingRequest failure");
    			e.printStackTrace();
    		}
		return toFrontEnd("receiveBookingRequest failure");
    }
    
//  回傳就診紀錄日期查詢 的醫師和病歷Map
    @PostMapping(path= {"/chartQuery"})
    public Map<String, Object> chartQuery(@RequestBody Patient patient){
		System.out.println("servlet: chartQuery for: " + patient.getMemID());
		
		try {
			Map<String, String> map =  bookingServiceImpl.showOneChart(patient);
			if(map != null && map.size() != 0) {
				return toFrontEnd("return chart success", "map", map);
			} else {
				return toFrontEnd("you don't see doctor yet");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toFrontEnd("Exception chartQuery");
    }
    
    @PostMapping(path = {"nowNum"})
    public Map<String, Object> nowNum(@RequestBody Doctor doctor){
    	try {
			CheckinVO checkinVO = bookingServiceImpl.nowNum(doctor);
			if(checkinVO != null) {
				return	toFrontEnd("nowNum success", "checkinVO", checkinVO);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toFrontEnd("no nowNum");
    }
    
//取消預約 /api/0.01/booking/cancelBooking
    @DeleteMapping(path = {"cancelBooking"})
    public Map<String, Object> cancelBooking(@RequestBody Patient patient){
// 取消預約
		try {
			int result = bookingServiceImpl.patientCancel(patient);
			if(result == 1) {
				return toFrontEnd("cancel success");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return toFrontEnd("cancel failure");
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
	
	private void setHeaders(HttpServletResponse response) {
		// 重要
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-control", "no-cache, no-store");
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Expires", "-1");

		// 重要
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Methods", "*");
		response.setHeader("Access-Control-Allow-Headers", "*");
		response.setHeader("Access-Control-Max-Age", "86400");
	}
	
	@Override
	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		setHeaders(resp);
	}
}
