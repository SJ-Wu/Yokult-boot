package tibame.tga102.yokult.doctor.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.reflect.Type;
import java.sql.Date;
import java.util.Base64;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

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
import tibame.tga102.yokult.booking.vo.DoctorChartVO;
import tibame.tga102.yokult.booking.vo.DoctorConvert;
import tibame.tga102.yokult.booking.vo.DoctorSchedule;
import tibame.tga102.yokult.booking.vo.Patient;
import tibame.tga102.yokult.doctor.service.DoctorService;
import tibame.tga102.yokult.doctor.service.DoctorServiceImpl;

@WebServlet("/api/0.01/doctor/*")
public class DoctorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public DoctorServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		//開啟跨網域，html才能接收到servlet傳出的東西
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		PrintWriter out = response.getWriter();
		String pathInfo = request.getPathInfo();
		String[] infos = pathInfo.split("/");
	//過濾同樣dr 有報到過的病人們 身分證字號名單
		if ("getDrPatientIdcard".equals(infos[1])) {
			out.append(gson.toJson(getDrPatientIdcard(gson, request)));
			br.close();
			out.close();
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		PrintWriter out = response.getWriter();
		String pathInfo = request.getPathInfo();
		String[] infos = pathInfo.split("/");

	//回傳index_doctor_chart.html 的  依據所選的醫師和有報到的病患的身分證字號 回傳看診日期選單
		if ("getDrPatientDates".equals(infos[1])) {
			out.append(gson.toJson(getDrPatientDates(gson, br)));
			br.close();
			out.close();
			return;
			
		} else if ("returnChart".equals(infos[1])) {
			out.append(gson.toJson(returnChart(gson, br)));
			br.close();
			out.close();
			return;
			
		} else if ("saveChart".equals(infos[1])) {
			out.append(gson.toJson(saveChart(gson, br)));
			br.close();
			out.close();
			return;
			
		} else if ("saveDr".equals(infos[1])) {
			out.append(gson.toJson(saveDr(gson, br)));
			br.close();
			out.close();
			return;
			
		} else if ("loadDr".equals(infos[1])) {
			out.append(gson.toJson(loadDr(gson, br)));
			br.close();
			out.close();
			return;
		}else if ("nextOne".equals(infos[1])) {
			//回傳下一號
			System.out.println("here nextOne");
			out.append(nextOne(gson, br));			
			br.close();
			out.close();
			return;
		}
	}
//修改
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		setHeaders(response);
		request.setCharacterEncoding("UTF-8");
		//開啟跨網域，html才能接收到servlet傳出的東西
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		BufferedReader br = request.getReader();
		PrintWriter out = response.getWriter();
		String pathInfo = request.getPathInfo();
		String[] infos = pathInfo.split("/");
	
//		/api/0.01/doctor/saveDrSchedule
		if ("updateDrSchedule".equals(infos[1])) {
			
			DoctorConvert doctorConvert = gson.fromJson(br, DoctorConvert.class);
			System.out.println("[Test] get doctor convert");
			List<DoctorSchedule> list = doctorConvert.getListOfDoctorSchedule();
			
			out.append(gson.toJson(updateDrSchedule(list)));
			br.close();
			out.close();
			return;
		} 
		
	}
	

	private String nextOne(Gson gson, Reader br) {
		Doctor doctor = gson.fromJson(br, Doctor.class);
		String doc = null;
		try {
			DoctorService doctorService = new DoctorServiceImpl(HibernateUtil.getSessionFactory());
			doc = doctorService.nextOne(doctor);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		if(doc != null) {
			return doc;		
		}
		
		JsonObject jsonObject = new JsonObject();
		jsonObject.addProperty("msg", "finish");
		return gson.toJson(jsonObject);
	}
	
	//儲存前端傳來的病歷紀錄
	private JsonObject saveChart(Gson gson, Reader br) {
		System.out.println("servlet: save chart start");
		DoctorServiceImpl doctorServiceImpl = null;
		try {
			doctorServiceImpl = new DoctorServiceImpl(HibernateUtil.getSessionFactory());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		Patient patient = gson.fromJson(br, Patient.class);
		JsonObject jsonObject = new JsonObject();
		String reg = "^[A-Z]{1}[01]{1}[0-9]{8}$";
		String regDate = "[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}";
		//過濾
		if(!patient.getPatientIdcard().matches(reg)) {
			jsonObject.addProperty("msg","no idcard");
			return jsonObject;
		}
		if(!patient.getBookingDate().toString().matches(regDate)) {
			jsonObject.addProperty("msg","no Date");
			return jsonObject;
		}
		int result = 0;
		result = doctorServiceImpl.updateChart(patient);
		if (result == 1) {
			jsonObject.addProperty("msg", "updateChart success");
		} else {
			jsonObject.addProperty("msg", "updateChart failure");
		}
		return jsonObject;
	}
	
	//回傳index_doctor_chart.html 的 病患身分證字號
	private JsonObject getDrPatientIdcard(Gson gson, HttpServletRequest request) {
		Doctor doctor = new Doctor();
		doctor.setDoctorId(Integer.valueOf(request.getParameter("doctorId")));
//		Doctor doctor = gson.fromJson(br, Doctor.class);
//		System.out.println(doctor);
		JsonObject jsonObject = new JsonObject();
		try {
			DoctorServiceImpl doctorServiceIImpl = null;
			doctorServiceIImpl = new DoctorServiceImpl(HibernateUtil.getSessionFactory());
			Set<String> set = doctorServiceIImpl.returnDrPatientIdcard(doctor);
			if(set == null) {
				jsonObject.addProperty("msg", "get no Patients booked");
			} else {
				jsonObject.addProperty("msg", "get patient id success");
				jsonObject.add("IDSet", gson.toJsonTree(set, new TypeToken<Set<String>>() {}.getType()).getAsJsonArray());
			}
			
		} catch (NamingException e) {
			System.out.println("getDrPatientID failure");
			jsonObject.addProperty("msg", "getDrPatientID failure");
			e.printStackTrace();
		}
		
		return jsonObject;
	}

	//回傳index_doctor_chart.html 的  依據所選的醫師和有報到的病患的身分證字號 回傳看診日期選單
	private JsonObject getDrPatientDates(Gson gson, Reader br) {
		DoctorService doctorServiceImpl = null;
		try {
			doctorServiceImpl = new DoctorServiceImpl(HibernateUtil.getSessionFactory());
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DoctorChartVO doctorChartVO = gson.fromJson(br, DoctorChartVO.class);
		Doctor doctor = new Doctor();
		Patient patient = new Patient();
		doctor.setDoctorId(doctorChartVO.getDoctorId());
		patient.setPatientIdcard(doctorChartVO.getPatientIdcard());
//		System.out.println(patient);		
		JsonObject jsonObject = new JsonObject();
		try {
			List<Date> list = doctorServiceImpl.returnDrPatientDates(doctor, patient);
			if (list != null) {
				jsonObject.addProperty("msg", "get patient dates success");
				jsonObject.add("list", gson.toJsonTree(list, new TypeToken<List<Date>>() {}.getType()).getAsJsonArray());
				System.out.println("servelt: return List<Date>");
			}
			if (list == null) {
				jsonObject.addProperty("msg", "get no Patients booked");
			} 
			
			
			
		} catch (NamingException e) {
			System.out.println("getDrPatientDates failure");
			jsonObject.addProperty("msg", "getDrPatientDates failure");
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	
	//顯示原本的病歷紀錄
	private JsonObject returnChart(Gson gson, Reader br) {
		DoctorService doctorServiceImpl = null;
		try {
			doctorServiceImpl = new DoctorServiceImpl(HibernateUtil.getSessionFactory());
		} catch (NamingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DoctorChartVO doctorChartVO = gson.fromJson(br, DoctorChartVO.class);
		Doctor doctor = new Doctor();
		Patient patient = new Patient();
		doctor.setDoctorId(doctorChartVO.getDoctorId());
		patient.setPatientIdcard(doctorChartVO.getPatientIdcard());
		patient.setBookingDate(doctorChartVO.getBookingDate());
		JsonObject jsonObject = new JsonObject();
		try {
			Patient patientChart = doctorServiceImpl.returnDrPatientChart(doctor, patient);
			if (patientChart != null) {
				jsonObject.addProperty("msg", "returnChart success");
				jsonObject.addProperty("list", patientChart.getChart());
			}
			
		} catch (NamingException e) {
			System.out.println("returnChart failure");
			jsonObject.addProperty("msg", "returnChart failure");
			e.printStackTrace();
		}
		
		return jsonObject;
	}
	
	//儲存醫師資料
	private JsonObject saveDr(Gson gson, Reader br) {
		System.out.println("servlet: save dr start");
		DoctorServiceImpl doctorServiceImpl = null;
		try {
			doctorServiceImpl = new DoctorServiceImpl(HibernateUtil.getSessionFactory());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		DoctorConvert doctorConvert = gson.fromJson(br, DoctorConvert.class);
		JsonObject jsonObject = new JsonObject();
		//過濾
		if(doctorConvert.getDoctorName().length() == 0) {
			jsonObject.addProperty("msg","no name");
			return jsonObject;
		}
		if(doctorConvert.getDoctorPhoto() == null) {
			jsonObject.addProperty("msg","no photo");
			return jsonObject;
		}
		
		int result = doctorServiceImpl.updateDr(doctorConverter(doctorConvert));
		if (result == 1) {
			jsonObject.addProperty("msg", "updateDr success");
		} else {
			jsonObject.addProperty("msg", "updateDr failure");
		}
		return jsonObject;
	}

	
	//回傳醫師資料
	private JsonObject loadDr(Gson gson, Reader br) {
		System.out.println("servlet: load dr start");
		DoctorServiceImpl doctorServiceImpl = null;
		try {
			doctorServiceImpl = new DoctorServiceImpl(HibernateUtil.getSessionFactory());
		} catch (NamingException e) {
			e.printStackTrace();
		}
		Doctor doctor = gson.fromJson(br, Doctor.class);
		JsonObject jsonObject = new JsonObject();

		DoctorConvert vo = doctorServiceImpl.selectOne(doctor);
		if (vo != null) {
			
			jsonObject.addProperty("msg", "load Dr success");
			jsonObject.add("doctor",  gson.toJsonTree(vo));
		} else {
			jsonObject.addProperty("msg", "load Dr failure");
		}
		return jsonObject;
	}
	
	private JsonObject updateDrSchedule( List list) {
		// 不要把gson放 HibernateUtil.getSessionFactory() 後面，會讀不到2???
//		DoctorConvert doctorConvert = gson.fromJson(br, DoctorConvert.class);
//		List<DoctorSchedule> list = doctorConvert.getListOfDoctorSchedule();
		int result = -1;
		try {
			DoctorServiceImpl doctorServiceImpl = null;
			doctorServiceImpl = new DoctorServiceImpl(HibernateUtil.getSessionFactory());
			result = doctorServiceImpl.saveDrSchedule(list);
		} catch (NamingException e) {
			e.printStackTrace();
		}
		JsonObject jsonObject = new JsonObject();
		if(result > 0) {
			jsonObject.addProperty("msg", "save DrSchedule success");
		} else {
			jsonObject.addProperty("msg", "save DrSchedule failure");
		}
		return jsonObject;
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
