package tibame.tga102.yokult.doctor.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import org.bson.Document;
import org.hibernate.SessionFactory;

import tibame.tga102.yokult.booking.dao.DoctorCheckinDAOImpl;
import tibame.tga102.yokult.booking.dao.DoctorDAO;
import tibame.tga102.yokult.booking.dao.DoctorDAOImpl;
import tibame.tga102.yokult.booking.dao.DoctorScheduleDAO;
import tibame.tga102.yokult.booking.dao.DoctorScheduleDAOImpl;
import tibame.tga102.yokult.booking.dao.PatientDAO;
import tibame.tga102.yokult.booking.dao.PatientDAOImpl;
import tibame.tga102.yokult.booking.vo.Doctor;
import tibame.tga102.yokult.booking.vo.DoctorConvert;
import tibame.tga102.yokult.booking.vo.DoctorSchedule;
import tibame.tga102.yokult.booking.vo.Patient;

public class DoctorServiceImpl implements DoctorService {
	private PatientDAO patientDAOImpl;
	private DoctorDAO doctorDAOImpl;
	private DoctorScheduleDAO doctorScheduleDAOImpl;
	private DoctorCheckinDAOImpl doctorCheckinDAOImpl;
	
	public DoctorServiceImpl(SessionFactory sessionFactory) throws NamingException {
		patientDAOImpl = new PatientDAOImpl(sessionFactory);
		doctorDAOImpl = new DoctorDAOImpl(sessionFactory);
		doctorScheduleDAOImpl = new DoctorScheduleDAOImpl(sessionFactory);
		doctorCheckinDAOImpl = new DoctorCheckinDAOImpl();
		}

	@Override
	public String nextOne(Doctor doctor) {
		Document docdel = doctorCheckinDAOImpl.selectOne(doctor);
		if(docdel != null) {
			doctorCheckinDAOImpl.deleteOne(docdel);
		}
		Document doc = doctorCheckinDAOImpl.selectOne(doctor);
		if(doc != null) {
			doc.append("msg", "nextOne success");
			return doc.toJson();
		}
		return null;
	}
	//回傳一個醫師資料
	@Override
	public DoctorConvert selectOne(Doctor doctor) {
		Doctor vo = doctorDAOImpl.selectOne(doctor);
		DoctorConvert doctorConvert = new DoctorConvert();
		if(vo != null) {
		byte[] photo = vo.getDoctorPhoto();
		if(photo != null) {
			String photostr = Base64.getEncoder().encodeToString(photo);
			doctorConvert.setDoctorPhoto(photostr);
		}
		doctorConvert.setDoctorId(vo.getDoctorId());
		doctorConvert.setDoctorName(vo.getDoctorName());
			return doctorConvert;
		}
		return null;
	}
	
	

	
	@Override
	public List<Doctor> getDoctorAll() {

		List<Doctor> list = doctorDAOImpl.selectAll();
		if(list.size() != 0) {
			return list;
		}
		return null;
	}
	
	
	//儲存病歷
	@Override
	public int updateChart(Patient patient)  {
		int rowcount = patientDAOImpl.updateChart(patient);
		return rowcount;
	}
	
	//儲存醫師
	@Override
	public int updateDr(Doctor doctor)  {
		int rowcount = doctorDAOImpl.updateDr(doctor);
		return rowcount;
	}
	
	
	//回傳某醫師所有病人MEMID
	@Override
	public Set<String> returnDrPatientIdcard(Doctor doctor) throws NamingException  {
		
		List<Patient> list = filterPatient(doctor);
		if(list.size() != 0) {
			Set<String> set = new HashSet<String>();
			for(Patient patient : list) {
				set.add(patient.getPatientIdcard());
			}
			System.out.println("service: returnDrPatientIdcard: " + set);
		return set;
		}
		return null;
	}
	
	//回傳某醫師某病人的   已報到日期
	@Override
	public List<Date> returnDrPatientDates(Doctor doctor, Patient patient) throws NamingException {
		//回傳某醫師的已報到所有病人資料
		List<Patient> list = filterPatient(doctor);
		if(list.size() != 0) {
			List<Date> listofDate = new ArrayList<Date>();
			for(Patient vo : list) {
				if(vo.getPatientIdcard().equals(patient.getPatientIdcard())) {
					listofDate.add(vo.getBookingDate());
				}
			}
		return listofDate;
		}
		return null;
	}
	
	//回傳某醫師某病人已報到日期 的病歷資料
	@Override
	public Patient returnDrPatientChart(Doctor doctor, Patient patient) throws NamingException {
		//過濾同樣dr 有報到的病人們的list
		List<Patient> list = filterPatient(doctor);
		if(list!= null) {
			for(Patient vo : list) {
				if(vo.getPatientIdcard().equals(patient.getPatientIdcard()) && vo.getBookingDate().equals(patient.getBookingDate())) {
					System.out.println("service: get Chart success");
					return vo;
				}
			}
		}
		return null;
	}
	
	
	//過濾同樣dr 有報到的病人們 但名字重複 只顯示已經報到過的名單
	@Override
	public List<Patient> filterPatient(Doctor doctor) throws NamingException{
		int drId = doctor.getDoctorId();
		List<Patient> list = patientDAOImpl.selectAll();
		if(list.size()!=0) {
			for (int i = list.size()-1; i >= 0; i--) {
				if((list.get(i).getDoctorId()) != drId || (list.get(i).getCheckinCondition()) != 1) {
					list.remove(i);
				};
			}
			System.out.println("service: filterDrPatient success =" + list);
			return list;
		}
		return null;
	}
	
	public int saveDrSchedule(List<DoctorSchedule> list) {
		int result = 0;
		for(DoctorSchedule doctorSchedule : list) {
			if((doctorScheduleDAOImpl.selectSerialNum(doctorSchedule)) > 0) {
				result = doctorScheduleDAOImpl.update(doctorSchedule);
				if(result < 0) {
					return result;
				}
			} else {
				result = doctorScheduleDAOImpl.insert(doctorSchedule);
				if(result < 0) {
					return result;
				}
			}
		}
		return result;
	}

	
//	//過濾 某醫生的已經報到病人們
//	public List<Patient> filterPatientDate(Doctor doctor) throws NamingException{
//		PatientDAOImpl patientDAOImpl = new PatientDAOImpl();
//		List<Patient> list = this.filterPatientNames(doctor);
//		if(list.size()!=0) {
//			for (int i = list.size()-1; i >= 0; i--) {
//				if((list.get(i).getCheckinCondition()) != 1) {
//					list.remove(i);
//				};
//			}
//			return list;
//		}
//		return null;
//	}
	
}
