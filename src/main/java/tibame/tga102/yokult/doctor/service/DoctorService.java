package tibame.tga102.yokult.doctor.service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

import javax.naming.NamingException;

import tibame.tga102.yokult.booking.vo.CheckinVO;
import tibame.tga102.yokult.booking.vo.Doctor;
import tibame.tga102.yokult.booking.vo.DoctorConvert;
import tibame.tga102.yokult.booking.vo.Patient;

public interface DoctorService {

	List<Doctor> getDoctorAll();

	int updateChart(Patient patient);

	Set<String> returnDrPatientIdcard(Doctor doctor) throws NamingException;

	List<Date> returnDrPatientDates(Doctor doctor, Patient patient) throws NamingException;

	Patient returnDrPatientChart(Doctor doctor, Patient patient) throws NamingException;

	List<Patient> filterPatient(Doctor doctor) throws NamingException;

	int updateDr(Doctor doctor);

	DoctorConvert selectOne(Doctor doctor);

	CheckinVO nextOne(Doctor doctor);

}
