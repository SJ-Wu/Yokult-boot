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
@Table(name = "doctor_schedule")
public class DoctorSchedule implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="serial_number")
	private Integer serialNumber;
	
	@Column(name = "doctor_alphabet")
	private String doctorAlphabet;
	
	@Column(name = "doctor_id")
	private Integer doctorId;
	
	@Column(name = "doctor_schedule_date")
	private java.sql.Date doctorScheduleDate;
	
	@Column(name = "doctor_ampm")
	private String doctorAmpm;
	
	@Column(name = "doctor_status")
	private Integer doctorStatus;
	
	public DoctorSchedule() {
		super();
	}
	public DoctorSchedule(Integer serialNumber, String doctorAlphabet, Integer doctorId, Date doctorScheduleDate,
			String doctorAmpm, Integer doctorStatus) {
		super();
		this.serialNumber = serialNumber;
		this.doctorAlphabet = doctorAlphabet;
		this.doctorId = doctorId;
		this.doctorScheduleDate = doctorScheduleDate;
		this.doctorAmpm = doctorAmpm;
		this.doctorStatus = doctorStatus;
	}
	public Integer getSerialNumber() {
		return serialNumber;
	}
	public void setSerialNumber(Integer serialNumber) {
		this.serialNumber = serialNumber;
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
	public java.sql.Date getDoctorScheduleDate() {
		return doctorScheduleDate;
	}
	public void setDoctorScheduleDate(java.sql.Date doctorScheduleDate) {
		this.doctorScheduleDate = doctorScheduleDate;
	}
	public String getDoctorAmpm() {
		return doctorAmpm;
	}
	public void setDoctorAmpm(String doctorAmpm) {
		this.doctorAmpm = doctorAmpm;
	}
	public Integer getDoctorStatus() {
		return doctorStatus;
	}
	public void setDoctorStatus(Integer doctorStatus) {
		this.doctorStatus = doctorStatus;
	}
	@Override
	public String toString() {
		return "DoctorSchedule [serialNumber=" + serialNumber + ", doctorAlphabet=" + doctorAlphabet + ", doctorId="
				+ doctorId + ", doctorScheduleDate=" + doctorScheduleDate + ", doctorAmpm=" + doctorAmpm
				+ ", doctorStatus=" + doctorStatus + "]";
	}
		
}
