package tibame.tga102.yokult.booking.service;

import javax.naming.NamingException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tibame.tga102.yokult.booking.vo.CheckinVO;
import tibame.tga102.yokult.booking.vo.Doctor;
import tibame.tga102.yokult.booking.vo.Patient;

@SpringBootTest
public class BookingServiceTests {

	@Test
	void contextLoads() {
	}
	
	@Autowired
	private BookingServiceImpl bookingServiceImpl;
	
	@Test
	public void nowNumTest() {
		Doctor doctor = new Doctor();
		doctor.setDoctorId(1);
		CheckinVO checkinVO = bookingServiceImpl.nowNum(doctor);
		System.out.println("[==>nowNumTest print ]= " + checkinVO);
	}
	
//	@Test
//	public void putcheckinTest() {
//		Patient patient = new Patient();
//		patient.setPatientIdcard("B123456789");
//		patient.setBookingNumber(1);
//		patient.setDoctorId(2);
//		bookingServiceImpl.putcheckin(patient);
//		System.out.println("[==>putcheckinTest print] finish");
//		
//	}
	
//	@Test
//	public void patientCheckInTest() {
//		Patient patient = new Patient();
//		patient.setPatientIdcard("C123456789");
//		patient.setBookingDate(java.sql.Date.valueOf("2022-08-10"));
//		bookingServiceImpl.patientCheckIn(patient);
//		System.out.println("==> patientCheckInTest");
//	}
	
//	@Test
//	public void patientCancelTest() {
//		Patient patient = new Patient();
//		patient.setPatientIdcard("C123456789");
//		patient.setBookingDate(java.sql.Date.valueOf("2022-08-10"));
//		try {
//			bookingServiceImpl.patientCancel(patient);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//
//		System.out.println("==> patientCheckInTest");
//	}
	
//	@Test
//	public void setPatientBookingTest() {
//		Patient patient = new Patient();
//		patient.setPatientIdcard("D123456789");
//		patient.setBookingDate(java.sql.Date.valueOf("2022-08-10"));
//		patient.setDoctorId(1);
//			bookingServiceImpl.setPatientBooking(patient);
//		System.out.println("==> setPatientBooking");
//	}
	
}
