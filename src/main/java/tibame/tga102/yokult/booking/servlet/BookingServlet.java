package tibame.tga102.yokult.booking.servlet;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
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

import org.bson.json.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.bytebuddy.description.method.MethodDescription.TypeToken;
import tibame.tga102.yokult.booking.service.BookingService;
import tibame.tga102.yokult.booking.service.BookingServiceImpl;
import tibame.tga102.yokult.booking.vo.Doctor;
import tibame.tga102.yokult.booking.vo.Patient;
import tibame.tga102.yokult.booking.vo.PatientBookingVO;
import tibame.tga102.yokult.doctor.service.DoctorService;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping(path = {"/api/0.01/booking"})
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Autowired
	private BookingService bookingServiceImpl;
	
	@Autowired
	private DoctorService doctorServiceImpl;
	
	@Autowired
	ServletContext servletContext;
	
    public BookingServlet() {
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
    public Map<String, Object> bookingQuery(@RequestParam String cinput, @RequestParam String memID){
		String cpassword = (String)servletContext.getAttribute("cpassword");
		System.out.println("[bookginservlet getAttribute cpassword]" + cpassword);
		
		if(cpassword != null && !cpassword.equals(cinput)) {
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
		} catch (NamingException e) {
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
    
    @PostMapping(path= {"/chartQuery"})
    public Map<String, Object> chartQuery(){
//    	//回傳就診紀錄日期查詢 的醫師和病歷Map
//    	private JsonObject chartQuery(Gson gson, Reader br) {
//    		Patient patient = gson.fromJson(br, Patient.class);
//    		System.out.println("servlet: chartQuery for: " + patient.getMemID());
//    		JsonObject jsonObject = new JsonObject();
//    		BookingService bookingServiceImpl = null;
//    		try {
//    			bookingServiceImpl = new BookingServiceImpl(HibernateUtil.getSessionFactory());
//    		} catch (NamingException e) {
//    			e.printStackTrace();
//    		}
//    		Map<String, String> map =  bookingServiceImpl.showOneChart(patient);
//    		if(map != null && map.size() != 0) {
//    			jsonObject.addProperty("msg", "return chart success");
//    			jsonObject.add("map", gson.toJsonTree(map, new TypeToken<Map<String, String>>() {}.getType()).getAsJsonObject());
//    		} else {
//    			jsonObject.addProperty("msg", "you don't see doctor yet");
//    		}
//    		return jsonObject;
//    	}
    	
    }
    

    
 
    

//		} else if("chartQuery".equals(infos[1])) {
////回傳就診紀錄日期查詢的醫師和病歷/api/0.01/booking/chartQuery
//			out.append(gson.toJson(chartQuery(gson, br)));			
//			br.close();
//			out.close();
//			return;
//		} else if ("nowNum".equals(infos[1])) {
////回傳目前叫號
//			out.append(nowNum(gson, br));			
//			br.close();
//			out.close();
//			return;
//		}
//			
//	}
//	
//	@Override
//	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		setHeaders(response);
//		request.setCharacterEncoding("UTF-8");
//		String pathInfo = request.getPathInfo();
//		String[] infos = pathInfo.split("/");
//		//開啟跨網域，html才能接收到servlet傳出的東西
//		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//		BufferedReader br = request.getReader();
//		PrintWriter out = response.getWriter();
//
////取消預約 /api/0.01/booking/cancelBooking
//		if ("cancelBooking".equals(infos[1])) {
//			out.append(gson.toJson(cancelBooking(gson, br)));
//			br.close();
//			out.close();
//			return;
//		}
//	}
//	
//	private String nowNum(Gson gson, Reader br) {
//		Doctor doctor = gson.fromJson(br, Doctor.class);
//		String doc = null;
//		try {
//			BookingService bookingService = new BookingServiceImpl(HibernateUtil.getSessionFactory());
//			doc = bookingService.nowNum(doctor);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//		if(doc != null) {
//			return doc;		
//		}
//		JsonObject jsonObject = new JsonObject();
//		jsonObject.addProperty("msg", "no nowNum");
//		return gson.toJson(jsonObject);
//	}
//	
//	


//	
//
//
//
////取消預約
//	private JsonObject cancelBooking(Gson gson, Reader br) {
//		Patient patient = gson.fromJson(br, Patient.class);
//		JsonObject jsonObject = new JsonObject();
//		//傳入一筆要改變預約的patient
//		try {
//			BookingService bookingServiceImpl = new BookingServiceImpl(HibernateUtil.getSessionFactory());
//			int result = bookingServiceImpl.patientCancel(patient);
//			if(result == 1) {
//				jsonObject.addProperty("msg", "cancel success");
//				return jsonObject;
//			}
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//		jsonObject.addProperty("msg", "cancel failure");
//		return jsonObject;
//	}
//	


	
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
