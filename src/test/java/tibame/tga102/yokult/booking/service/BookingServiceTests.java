package tibame.tga102.yokult.booking.service;

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
	
//	@Test
//	public void nowNumTest() {
//		Doctor doctor = new Doctor();
//		doctor.setDoctorId(1);
//		CheckinVO checkinVO = bookingServiceImpl.nowNum(doctor);
//		System.out.println("[ nowNumTest print ]= " + checkinVO);
//	}
	
	@Test
	public void putcheckinTest() {
		Patient patient = new Patient();
		patient.setPatientIdcard("A123456789");
		patient.setBookingNumber(2);
		patient.setDoctorId(1);
		bookingServiceImpl.putcheckin(patient);
		System.out.println("[putcheckinTest print] finish");
		
	}
}
