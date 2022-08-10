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
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import model.hibernate.HibernateUtil;
import tibame.tga102.yokult.booking.service.BookingService;
import tibame.tga102.yokult.booking.service.BookingServiceImpl;
import tibame.tga102.yokult.booking.vo.Doctor;
import tibame.tga102.yokult.booking.vo.Patient;
import tibame.tga102.yokult.booking.vo.PatientBookingVO;

@WebServlet("/api/0.01/booking/*")
public class BookingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BookingServlet() {
        super();
    }
    
    //最後再來分類doXXX
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		String pathInfo = request.getPathInfo();
		String[] infos = pathInfo.split("/");
		//開啟跨網域，html才能接收到servlet傳出的東西
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		PrintWriter out = response.getWriter();
		
		if ("drSchedule".equals(infos[1])) {
	//回傳醫師上班時間 /api/0.01/booking/drSchedule
			out.append(gson.toJson(sendJsonOfDoctorScheduleAndDoctorName(gson, request)));
			br.close();
			out.close();
			return;
		} else if("bookingQuery".equals(infos[1])) {
//查詢某會員預約資訊/api/0.01/booking/bookingQuery
			out.append(gson.toJson(bookingQuery(gson, request)));
			br.close();
			out.close();
			return;
		} else if("chartQuery".equals(infos[1])) {
//查詢會員看過診日期/api/0.01/booking/bookingQuery get
			out.append(gson.toJson(chartQueryDate(gson, request)));
			br.close();
			out.close();
			return;
		}

		br.close();
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String pathInfo = request.getPathInfo();
		String[] infos = pathInfo.split("/");
		//開啟跨網域，html才能接收到servlet傳出的東西
		setHeaders(response);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		PrintWriter out = response.getWriter();

//新增checkin /api/0.01/booking/patientCheckin
		if ("patientCheckin".equals(infos[1])) {
			out.append(gson.toJson(patientCheckin(gson, br)));
			br.close();
			out.close();
			return;
		} else if("receiveBookingRequest".equals(infos[1])) {
//新增掛號/api/0.01/booking/receiveBookingRequest
			out.append(gson.toJson(receiveBookingRequest(gson, br)));			
			br.close();
			out.close();
			return;
		} else if("chartQuery".equals(infos[1])) {
//回傳就診紀錄日期查詢的醫師和病歷/api/0.01/booking/chartQuery
			out.append(gson.toJson(chartQuery(gson, br)));			
			br.close();
			out.close();
			return;
		} else if ("nowNum".equals(infos[1])) {
//回傳目前叫號
			out.append(nowNum(gson, br));			
			br.close();
			out.close();
			return;
		}
			
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		String pathInfo = request.getPathInfo();
		String[] infos = pathInfo.split("/");
		//開啟跨網域，html才能接收到servlet傳出的東西
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		PrintWriter out = response.getWriter();

//取消預約 /api/0.01/booking/cancelBooking
		if ("cancelBooking".equals(infos[1])) {
			out.append(gson.toJson(cancelBooking(gson, br)));
			br.close();
			out.close();
			return;
		}
	}
	
	private String nowNum(Gson gson, Reader br) {
		Doctor doctor = gson.fromJson(br, Doctor.class);
		String doc = null;
		try {
			BookingService bookingService = new BookingServiceImpl(HibernateUtil.getSessionFactory());
			doc = bookingService.nowNum(doctor);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		if(doc != null) {
			return doc;		
		}
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("msg", "no nowNum");
		return gson.toJson(jsonObject);
	}
	
	
	//新增checkin方法
	private JsonObject patientCheckin(Gson gson, Reader br) {
		//讀進資料
		Patient patient = gson.fromJson(br, Patient.class);
		//patient傳入處理checkin方法
		int result = 0;;
		JsonObject jsonObject = new JsonObject();
		try {
			BookingService bookingService = new BookingServiceImpl(HibernateUtil.getSessionFactory());
			
			String pID = bookingService.getIdcardBymemID(patient);
			if(!pID.equals(patient.getPatientIdcard())) {
				jsonObject.addProperty("msg", "not correct IDcard");
				return jsonObject;
			}
			result = bookingService.patientCheckIn(patient);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		if (result == 1) {
			jsonObject.addProperty("msg", "checkin success");
		} else {
			jsonObject.addProperty("msg", "you have no booking today");
		}
		return jsonObject;
	}
	
	//新增掛號資料方法 
	private JsonObject receiveBookingRequest(Gson gson, Reader br) {
		Patient patient = gson.fromJson(br, Patient.class);
		int bookingNumber = 0;
		JsonObject jsonObject = new JsonObject();
//		System.out.println(patient);
		if((patient.getPatientIdcard().length() == 0)) {
			jsonObject.addProperty("msg", "IDCARD didn't key in");
			return jsonObject;
		}
		if((patient.getBookingDate() == null)) {
			jsonObject.addProperty("msg", "TIME didn't key in");
			return jsonObject;
		}
		
		if(!patient.getPatientIdcard().matches("^[A-Z]{1}[1-2]{1}[0-9]{8}$")) {
			jsonObject.addProperty("msg", "PatientIdcard wrong format");
			return jsonObject;
		}
		try {
			BookingService bookingService = new BookingServiceImpl(HibernateUtil.getSessionFactory());
			bookingNumber = bookingService.setPatientBooking(patient);
		} catch (NamingException e) {
			System.out.println("receiveBookingRequest failure");
			e.printStackTrace();
		}
		if(bookingNumber != -1) {
			jsonObject.addProperty("msg", "receiveBookingRequest success");
			jsonObject.addProperty("bookingNumber", bookingNumber);
		} else {
			jsonObject.addProperty("msg", "receiveBookingRequest failure");
		}
		System.out.println("receiveBookingRequest finish");
		return jsonObject;
	}
	
	//回傳醫師上班時間
	private JsonObject sendJsonOfDoctorScheduleAndDoctorName(Gson gson, HttpServletRequest request) {
//		PatientBookingVO vo = gson.fromJson(br, PatientBookingVO.class);
//		System.out.println(vo);
		String date1sql = request.getParameter("date1").replace("/", "-");
		String date2sql = request.getParameter("date2").replace("/", "-");
//		System.out.println(date1sql);
//		System.out.println(date2sql);
		PatientBookingVO vo = new PatientBookingVO(Date.valueOf(date1sql), Date.valueOf(date2sql),Integer.valueOf(request.getParameter("doctorId")));
		JsonObject jsonObject = new JsonObject();
		//把東西轉成GSON丟出
		try {
			BookingService bookingService = new BookingServiceImpl(HibernateUtil.getSessionFactory());
			Map<String, Object> map = bookingService.getDoctorScheduleAndDoctorName(vo.getDate1(), vo.getDate2(), vo.getDoctorId());
			//toJsonTree方式把直接map物件轉JSONTREE 再轉JsonObject
			JsonObject drNameScheduleJsonObject = gson.toJsonTree(map).getAsJsonObject();
//			System.out.println(drNameScheduleJsonObject);
			//送到前端要把JsonObject轉json
			jsonObject.addProperty("msg", "sendSchedule success");
			jsonObject.add("schedule", drNameScheduleJsonObject);
			System.out.println("sendSchedule success");
			return jsonObject;
		} catch (NamingException e) {
			System.out.println("NamingException at sendJsonOfDoctorScheduleAndDoctorName()");
			jsonObject.addProperty("msg", "sendSchedule failure");
			System.out.println("sendSchedule failure");
			e.printStackTrace();
			return jsonObject;
		}
	}
	
	//查詢某會員預約資訊
	private JsonObject bookingQuery(Gson gson, HttpServletRequest request) {
		JsonObject jsonObject = new JsonObject();
		String cpassword = (String)getServletContext().getAttribute("cpassword");
		System.out.println("[bookginservlet getAttribute]" + cpassword);
		
		if(!cpassword.equals(request.getParameter("cinput"))) {
			jsonObject.addProperty("msg", "incorrect captcha");
			return jsonObject;
		}	
		
		Patient patient = new Patient();
		patient.setMemID(request.getParameter("memID"));
//		Patient patient = gson.fromJson(br, Patient.class);
		//patient傳給service service再查出預約日期
		try {
			BookingService bookingServiceImpl = new BookingServiceImpl(HibernateUtil.getSessionFactory());
			List<HashMap<String, Object>> list = bookingServiceImpl.getPatientBooking(patient);
			if(list != null) {
				jsonObject.addProperty("msg", "bookingQuery sucess");
				jsonObject.add("list", gson.toJsonTree(list, new TypeToken<List<HashMap<String, Object>>>() {}.getType()).getAsJsonArray());
				System.out.println("bookingQuery sucess");
				System.out.println(list);
				return jsonObject;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		jsonObject.addProperty("msg", "you have no unchecked booking data");
		return jsonObject;
	}

//取消預約
	private JsonObject cancelBooking(Gson gson, Reader br) {
		Patient patient = gson.fromJson(br, Patient.class);
		JsonObject jsonObject = new JsonObject();
		//傳入一筆要改變預約的patient
		try {
			BookingService bookingServiceImpl = new BookingServiceImpl(HibernateUtil.getSessionFactory());
			int result = bookingServiceImpl.patientCancel(patient);
			if(result == 1) {
				jsonObject.addProperty("msg", "cancel success");
				return jsonObject;
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
		jsonObject.addProperty("msg", "cancel failure");
		return jsonObject;
	}
	
	//回傳就診紀錄查詢的日期
	private JsonObject chartQueryDate(Gson gson, HttpServletRequest request) {
//		Patient patient = gson.fromJson(br, Patient.class);
		Patient patient = new Patient();
		
		String memID = request.getParameter("memID");
		System.out.println("servlet: chartQueryDate for: " + memID);
		patient.setMemID(memID);
		JsonObject jsonObject = new JsonObject();
		BookingService bookingServiceImpl = null;
		try {
			bookingServiceImpl = new BookingServiceImpl(HibernateUtil.getSessionFactory());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<Date> list =  bookingServiceImpl.getChartDates(patient);
		if(list != null && list.size() != 0) {
			jsonObject.addProperty("msg", "return date success");
			jsonObject.add("list", gson.toJsonTree(list, new TypeToken<List<Date>>() {}.getType()));
		} else {
			jsonObject.addProperty("msg", "you don't see doctor yet");
		}
		return jsonObject;
	}
	
	//回傳就診紀錄日期查詢 的醫師和病歷Map
	private JsonObject chartQuery(Gson gson, Reader br) {
		Patient patient = gson.fromJson(br, Patient.class);
		System.out.println("servlet: chartQuery for: " + patient.getMemID());
		JsonObject jsonObject = new JsonObject();
		BookingService bookingServiceImpl = null;
		try {
			bookingServiceImpl = new BookingServiceImpl(HibernateUtil.getSessionFactory());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		Map<String, String> map =  bookingServiceImpl.showOneChart(patient);
		if(map != null && map.size() != 0) {
			jsonObject.addProperty("msg", "return chart success");
			jsonObject.add("map", gson.toJsonTree(map, new TypeToken<Map<String, String>>() {}.getType()).getAsJsonObject());
		} else {
			jsonObject.addProperty("msg", "you don't see doctor yet");
		}
		return jsonObject;
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
