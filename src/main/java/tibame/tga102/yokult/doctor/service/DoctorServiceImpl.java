package tibame.tga102.yokult.doctor.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.booking.dao.DoctorCheckinDAO;
import tibame.tga102.yokult.booking.dao.DoctorDAO;
import tibame.tga102.yokult.booking.dao.DoctorScheduleDAO;
import tibame.tga102.yokult.booking.dao.PatientDAO;
import tibame.tga102.yokult.booking.vo.CheckinVO;
import tibame.tga102.yokult.booking.vo.Doctor;
import tibame.tga102.yokult.booking.vo.DoctorConvert;
import tibame.tga102.yokult.booking.vo.DoctorSchedule;
import tibame.tga102.yokult.booking.vo.Patient;
@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {
	@Autowired
	private PatientDAO patientDAOImpl;
	@Autowired
	private DoctorDAO doctorDAOImpl;
	
	@Autowired
	private DoctorScheduleDAO doctorScheduleDAOImpl;
	
	@Autowired
	private DoctorCheckinDAO doctorCheckinDAO;
	
	public DoctorServiceImpl() {
		}

	@Override
	public CheckinVO nextOne(Doctor doctor) {
		Optional<CheckinVO> checkinVO = findFirstOne(doctor);
		if(checkinVO.isPresent()) {
			doctorCheckinDAO.delete(checkinVO.get());
		}
		Optional<CheckinVO> checkinVONext = findFirstOne(doctor);
		if(checkinVONext.isPresent()) {
			return checkinVONext.get();
		}
		return null;
	}
	
	
	public Optional<CheckinVO> findFirstOne(Doctor doctor){
		return doctorCheckinDAO.findAll()
			 	.stream()
			 	.filter(v -> v.getDoctorId() == doctor.getDoctorId())
			 	.sorted(Comparator.comparing(CheckinVO::getBookingNumber))
			 	.findFirst();
	}
	
	
	//回傳一個醫師資料
	@Override
	public DoctorConvert selectOne(Doctor doctor) {
		Doctor vo = doctorDAOImpl.selectOne(doctor);
		DoctorConvert doctorConvert = new DoctorConvert();
		if(vo != null) {
		byte[] photo = vo.getDoctorPhoto();
		System.out.println("[DoctorService] getDoctorPhoto byte[]" + photo);
		if(photo != null) {
			String photostr = Base64.getEncoder().encodeToString(photo);
			System.out.println("[DoctorService] selectOne photostr" + photostr);
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
	public Set<String> returnDrPatientIdcard(Doctor doctor)  {
		
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
	public List<Date> returnDrPatientDates(Doctor doctor, Patient patient)  {
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
	public Patient returnDrPatientChart(Doctor doctor, Patient patient)  {
		//過濾同樣dr 有報到的病人們的list
		List<Patient> list = filterPatient(doctor);
		if(list!= null) {
			for(Patient vo : list) {
				System.out.println("[DoctorService] returnDrPatientChart");
				if(vo.getPatientIdcard().equals(patient.getPatientIdcard()) && (vo.getBookingDate().toString()).equals((patient.getBookingDate().toString()))) {
					System.out.println("service: get Chart success");
					return vo;
				}
			}
		}
		return null;
	}
	
	//過濾同樣dr 有報到的病人們 但名字重複 只顯示已經報到過的名單
	@Override
	public List<Patient> filterPatient(Doctor doctor) {
		int drId = doctor.getDoctorId();
		List<Patient> list = patientDAOImpl.selectAll();
		if(list.size()!=0) {
			for (int i = list.size()-1; i >= 0; i--) {
				if((list.get(i).getDoctorId()) != drId || (list.get(i).getCheckinCondition()) != 1) {
					list.remove(i);
				};
			}
			return list;
		}
		return null;
	}
	
	@Override
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
