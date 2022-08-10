package web.booking.vo;

import java.io.Serializable;

public class DoctorChartVO implements Serializable {
	private Integer doctorId;
	private String memID;
	private String patientIdcard;
	private java.sql.Date bookingDate;
	
	public java.sql.Date getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(java.sql.Date bookingDate) {
		this.bookingDate = bookingDate;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	public String getMemId() {
		return memID;
	}
	public void setMemId(String memID) {
		this.memID = memID;
	}
	public String getPatientIdcard() {
		return patientIdcard;
	}
	public void setPatientIdcard(String patientIdcard) {
		this.patientIdcard = patientIdcard;
	}

	public DoctorChartVO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
