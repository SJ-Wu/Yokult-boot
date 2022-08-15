package tibame.tga102.yokult.booking.service;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;

import tibame.tga102.yokult.booking.vo.CheckinVO;
import tibame.tga102.yokult.booking.vo.Doctor;
import tibame.tga102.yokult.booking.vo.Patient;

public interface BookingService {

	//組裝會員編號和要booking的時段，並回傳是否新增成功 把object資料拿出來
	public int setPatientBooking(Patient patient);

	// 組裝日期 醫師有上班的時段和姓名
	public Map<String, Object> getDoctorScheduleAndDoctorName(Date date1, Date date2, Integer doctorId) ;

	// Overloading組裝日期 醫師有上班的時段和姓名
//	public Map<String, Object> getDoctorScheduleAndDoctorName(String date1, String date2, String doctorId) throws NamingException;
	
	//patient checkin方法 成功回傳1(影響筆數) 失敗回傳-1
	public int patientCheckIn(Patient patient);
	
	//回傳病人未報到的所有欄位 有的話回傳list 沒有回null
	public List<HashMap<String, Object>> getPatientBooking(Patient patient);
	
	//取消預約
	public int patientCancel(Patient patient);
	
	//查詢列出病患身份證字號為? getCheckinCondition=1 的病患所有欄位 的病歷資料
	public List<Patient> getChart(Patient patient) ;
	
	//查身分證字號
	String getIdcardBymemID(Patient patient);
	
	//列出病患 報到狀態=1的日期
	List<Date> getChartDates(Patient patient);
	
	//回傳一個已報到 指定時間 身分證 的病歷
	Map<String, String> showOneChart(Patient patient);

	CheckinVO nowNum(Doctor doctor);

	void putcheckin(Patient patient);
	
}