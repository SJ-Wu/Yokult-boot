package web.booking.vo;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

@Entity
@DynamicInsert
@Table(name = "patient")
public class Patient implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "serial_number")
	private Integer serialNumber;
	
	@Column(name = "memid")
	private String memID;
	
	@Column(name = "patient_idcard")
	private String patientIdcard;
	
	@Column(name = "booking_date")
	private java.sql.Date bookingDate;
	
	@Column(name = "amPm")
	private String amPm;
	
	@Column(name = "booking_number")
	private Integer bookingNumber;
	
	@Column(name = "doctor_alphabet")
	private String doctorAlphabet;
	
	@Column(name = "doctor_id")
	private Integer doctorId;
	
	@Column(name = "checkin_condition")
	private Integer checkinCondition;
	
	@Column(name = "chart",columnDefinition="TEXT")
	private String chart;
	
	public Patient() {
		super();
	}

	//has serialNumber
	public Patient(Integer serialNumber, String memID, String patientIdcard, Date bookingDate, String amPm,
			Integer bookingNumber, String doctorAlphabet, Integer doctorId, Integer checkinCondition, String chart) {
		super();
		this.serialNumber = serialNumber;
		this.memID = memID;
		this.patientIdcard = patientIdcard;
		this.bookingDate = bookingDate;
		this.amPm = amPm;
		this.bookingNumber = bookingNumber;
		this.doctorAlphabet = doctorAlphabet;
		this.doctorId = doctorId;
		this.checkinCondition = checkinCondition;
		this.chart = chart;
	}
	// no serialNumber
	public Patient(String memID, String patientIdcard, Date bookingDate, String amPm, Integer bookingNumber,
			String doctorAlphabet, Integer doctorId, Integer checkinCondition, String chart) {
		super();
		this.memID = memID;
		this.patientIdcard = patientIdcard;
		this.bookingDate = bookingDate;
		this.amPm = amPm;
		this.bookingNumber = bookingNumber;
		this.doctorAlphabet = doctorAlphabet;
		this.doctorId = doctorId;
		this.checkinCondition = checkinCondition;
		this.chart = chart;
	}

	@Override
	public String toString() {
		return "Patient [serialNumber=" + serialNumber + ", memID=" + memID + ", patientIdcard=" + patientIdcard
				+ ", bookingDate=" + bookingDate + ", amPm=" + amPm + ", bookingNumber=" + bookingNumber
				+ ", doctorAlphabet=" + doctorAlphabet + ", doctorId=" + doctorId + ", checkinCondition="
				+ checkinCondition + ", chart=" + chart + "]";
	}

	public Integer getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getPatientIdcard() {
		return patientIdcard;
	}

	public void setPatientIdcard(String patientIdcard) {
		this.patientIdcard = patientIdcard;
	}

	public java.sql.Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(java.sql.Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public String getAmPm() {
		return amPm;
	}

	public void setAmPm(String amPm) {
		this.amPm = amPm;
	}

	public Integer getBookingNumber() {
		return bookingNumber;
	}

	public void setBookingNumber(Integer bookingNumber) {
		this.bookingNumber = bookingNumber;
	}

	public String getDoctorAlphabet() {
		return doctorAlphabet;
	}

	public void setDoctorAlphabet(String doctorAlphabet) {
		this.doctorAlphabet = doctorAlphabet;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}

	public Integer getCheckinCondition() {
		return checkinCondition;
	}

	public void setCheckinCondition(Integer checkinCondition) {
		this.checkinCondition = checkinCondition;
	}

	public String getChart() {
		return chart;
	}

	public void setChart(String chart) {
		this.chart = chart;
	}

	
	

}
