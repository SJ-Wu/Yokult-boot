package tibame.tga102.yokult.booking.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tibame.tga102.yokult.booking.service.BookingServiceImpl;
import tibame.tga102.yokult.booking.vo.Patient;

@SpringBootTest
public class BookingDAOImplTests {
	@Test
	void contextLoads() {
		
	}
	
	@Autowired
	private PatientDAO patientDao;
	
	@Test 
	public void testInsertBookingIntoPatient() {
		Patient patient = new Patient();
		patient.setMemID("TGA001");
		patient.setPatientIdcard("A123456789");
		patient.setBookingDate(java.sql.Date.valueOf("2025-01-01"));
		patient.setBookingNumber(1);
		patient.setDoctorId(1);
		int result = patientDao.insertBookingIntoPatient(patient);
		System.out.println(result);
	}
	

}
