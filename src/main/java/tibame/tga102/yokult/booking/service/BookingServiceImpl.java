package tibame.tga102.yokult.booking.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.naming.NamingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.booking.dao.DoctorCheckinDAO;
import tibame.tga102.yokult.booking.dao.DoctorDAO;
import tibame.tga102.yokult.booking.dao.DoctorScheduleDAO;
import tibame.tga102.yokult.booking.dao.PatientDAO;
import tibame.tga102.yokult.booking.vo.CheckinVO;
import tibame.tga102.yokult.booking.vo.Doctor;
import tibame.tga102.yokult.booking.vo.DoctorSchedule;
import tibame.tga102.yokult.booking.vo.Patient;
@Service
@Transactional
public class BookingServiceImpl implements BookingService  {
	@Autowired
	private PatientDAO patientDAOImpl;
	
	@Autowired
	private DoctorDAO doctorDAOImpl;
	
	@Autowired
	private DoctorScheduleDAO doctorScheduleDAOImpl;
	
	@Autowired
	private DoctorCheckinDAO doctorCheckinDAO;
	
	public BookingServiceImpl(){
	}
	
	@Override
	public CheckinVO nowNum(Doctor doctor) {
		System.out.println("findAll start");
		List<CheckinVO> list = doctorCheckinDAO.findAll();
		
		System.out.println("findAll = " + list);
		Optional<CheckinVO> o = list.stream()
				.filter(v -> v.getDoctorId().equals(doctor.getDoctorId()))
				.sorted(Comparator.comparing(CheckinVO::getBookingNumber))
				.findFirst();
		if(o.isPresent()) {
			return o.get();
		}
		
		return null;
		
//		Document doc = doctorCheckinDAOImpl.selectOne(doctor);
//		if(doc != null) {
//			doc.append("msg", "nowNum success");
//			return doc.toJson();
//		}
//		return null;
	}
	
	//儲存叫號 CheckinCondition=1 才儲存叫號
	@Override
	public void putcheckin(Patient patient) {
		patient.setCheckinCondition(1);
		List<Patient> list =  patientDAOImpl.selectPatientByIdcardAndCheckinCondition(patient);
		System.out.println("[service putcheckin list]" + list);
		if(list != null) {
			for(Patient vo : list) {
				if(Date.valueOf(LocalDate.now()).equals(vo.getBookingDate())) {
					CheckinVO checkinVO = new CheckinVO();
					checkinVO.setBookingNumber(vo.getBookingNumber());
					checkinVO.setDoctorId(vo.getDoctorId());
					checkinVO.setPatientIdcard(vo.getPatientIdcard());

					doctorCheckinDAO.save(checkinVO);
		//					doctorCheckinDAOImpl.insertOne(vo);

				
				}
			}
		}
	}
	
	//報到 patient checkin方法 成功回傳1(影響筆數) 失敗回傳0 或 -1
	@Override
	public int patientCheckIn(Patient patient) {
		patient.setCheckinCondition(1);
		patient.setBookingDate(Date.valueOf(LocalDate.now()));
		int result = patientDAOImpl.updatePatientCheckinConditionByBookingDate(patient);
		System.out.println("change patientCheckIn condition rowcount = "+ result);
		putcheckin(patient);
		return result;
	}
	
	//取消預約 傳一筆p進來 回傳int
	@Override
	public int patientCancel(Patient patient) {
		patient.setCheckinCondition(2);
		//	patient.setBookingDate();
		int result = patientDAOImpl.updatePatientCheckinConditionByBookingDate(patient);
		System.out.println("change patientCheckIn condition rowcount= "+ result);
		return result;
	}
	//組裝會員編號和要booking的時段，並回傳是否新增成功 把object資料拿出來
	//在這邊計算掛幾號
	@Override
	public int setPatientBooking(Patient patient)  {
		//先查詢是否有掛號日期
		if(patient.getBookingDate() == null) {
			return -1;
		}
		//當天已經有掛號資料 return -1
		List<Patient>list = patientDAOImpl.selectPatientByIdcard(patient);
		if (list != null) {
			boolean hasBooking = list.stream()
									.anyMatch(vo -> patient.getBookingDate().equals(vo.getBookingDate()));
			if(hasBooking) {
				System.out.println("you already booking this day");
				return -1;
			}
//			for (Patient vo : list) {
//				if ((patient.getBookingDate()).equals(vo.getBookingDate())){
//					System.out.println("you already booking this day");
//					return -1;
//				}
//			}
		}
		
		//要先拿到patient中 某醫師某時段總共幾人
	 	int patientCount = patientDAOImpl.selectCountByDoctor(patient.getBookingDate(), patient.getDoctorId());
		// 得到當天有幾人掛號
	 	if(patientCount != -1) {
			patient.setBookingNumber(patientCount+1);
		}
	 	int rowcount = patientDAOImpl.insertBookingIntoPatient(patient);
		if (rowcount == -1) {
			return -1;
		} else {
			return patient.getBookingNumber();
		}
	}
	
	// 組裝日期 醫師有上班的時段和姓名
	@Override
	public Map<String, Object> getDoctorScheduleAndDoctorName(Date date1, Date date2,Integer doctorId) {
		List<DoctorSchedule> listDr = doctorScheduleDAOImpl.selectDoctorSchedule(date1, date2, doctorId);
		String drName = doctorDAOImpl.selectDoctorNameById(doctorId);
		
		Doctor doctor = new Doctor();
		doctor.setDoctorId(Integer.valueOf(doctorId));
		byte[] photo = doctorDAOImpl.selectOne(doctor).getDoctorPhoto();
		String photostr = "";
		if(photo!= null) {
			photostr = Base64.getEncoder().encodeToString(photo);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", listDr);
		map.put("name", drName);
		map.put("photo", photostr);
		return map;
	}
	
	// Overloading組裝日期 醫師有上班的時段和姓名
//	@Override
//	public Map<String, Object> getDoctorScheduleAndDoctorName(String date1, String date2,String doctorId) throws NamingException {
////		Integer.valueOf(doctorId);
//		List<DoctorSchedule> listDr = doctorScheduleDAOImpl.selectDoctorSchedule(java.sql.Date.valueOf(date1), java.sql.Date.valueOf(date2), Integer.valueOf(doctorId));
//		String drName = doctorDAOImpl.selectDoctorNameById(Integer.valueOf(doctorId));
//		Doctor doctor = new Doctor();
//		doctor.setDoctorId(Integer.valueOf(doctorId));
//		System.out.println("getDoctorId"+doctor.getDoctorId());
//		byte[] photo = doctorDAOImpl.selectOne(doctor).getDoctorPhoto();
//		String photostr = Base64.getEncoder().encodeToString(photo);
//		System.out.println(photo);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("list", listDr);
//		map.put("name", drName);
//		map.put("photo", photostr);
//		return map;
//	}

	//回傳病人未報到的所有欄位 加上醫生姓名 有的話回傳list 沒有回null
	@Override
	public List<HashMap<String, Object>> getPatientBooking(Patient patient) {
		List<Patient> list = patientDAOImpl.selectPatientBymemID(patient);
		if (list != null) {
			for (int i = list.size()-1; i >= 0; i--) {
				if(list.get(i).getCheckinCondition() != 0) {
					list.remove(i);
				};
			}
			if (list.size()!=0 ) {
				List<HashMap<String, Object>> listofmap = new ArrayList<HashMap<String, Object>>();
				for(Patient vo : list) {
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("serialNumber", vo.getSerialNumber());
					map.put("memID", vo.getMemID());
					map.put("patientIdcard", vo.getPatientIdcard());
					map.put("bookingDate", vo.getBookingDate());
					map.put("amPm", vo.getAmPm());
					map.put("bookingNumber", vo.getBookingNumber());
					map.put("doctorName",doctorDAOImpl.selectDoctorNameById(vo.getDoctorId()));
					map.put("checkinCondition", vo.getCheckinCondition());
					
					listofmap.add(map);
				}
				
				return listofmap;
			}
		}
		return null;
	}
	
	//查詢列出病患身份證字號為? getCheckinCondition=1 的病患所有欄位 的病歷資料
	@Override
	public List<Patient> getChart(Patient patient) {
		patient.setBookingNumber(1);
		patientDAOImpl.selectPatientBymemID(patient);
		return null;
	}
	//查身分證字號
	@Override
	public String getIdcardBymemID(Patient patient) {
		List<Patient> list = patientDAOImpl.selectPatientBymemID(patient);
		if(list.size() != 0) {
			String patientIdcard = list.get(0).getPatientIdcard();
			return patientIdcard;
		}
		return null;
	}
	
	//列出病患 報到狀態=1的日期
	@Override
	public List<Date> getChartDates(Patient patient){
		String Idcard = getIdcardBymemID(patient);
		patient.setPatientIdcard(Idcard);
		patient.setCheckinCondition(1);
		System.out.println("service: getChartDates start");
		List<Patient> list = patientDAOImpl.selectPatientByIdcardAndCheckinCondition(patient);
		List<Date> listOfDate = new ArrayList<Date>();
		if( list != null) {
			for (Patient vo : list) {
				listOfDate.add(vo.getBookingDate());
			}
			System.out.println("service: getChartDates finish");
			return listOfDate;
		}
		return null;
	}
	//回傳一個已報到 指定時間 身分證 的病歷
	@Override
	public Map<String, String> showOneChart(Patient patient){
		String Idcard = getIdcardBymemID(patient);
		patient.setCheckinCondition(1);
		patient.setPatientIdcard(Idcard);
		System.out.println("service: start showOneChart");
		List<Patient> list = patientDAOImpl.selectPatientByIdcardAndCheckinCondition(patient);
		Map<String, String> map = new HashMap<String, String>();
		for(Patient vo : list) {
			if(patient.getBookingDate().toString().equals(vo.getBookingDate().toString())) {
				String drname = doctorDAOImpl.selectDoctorNameById(vo.getDoctorId());
				map.put("doctorName", drname);
				map.put("chart", vo.getChart());
				System.out.println(vo.getChart());
			}
		
		}
		if(map.size() != 0) {
			return map;
		}
		return null;
		
	}
//	getChart	
	

}
