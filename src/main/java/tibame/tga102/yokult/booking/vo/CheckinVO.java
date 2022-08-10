package tibame.tga102.yokult.booking.vo;

import javax.persistence.Id;

import org.springframework.data.mongodb.core.mapping.Document;

@Document("checkin")
public class CheckinVO {
	@Id
	private String id;
	private String patientIdcard;
	private Integer bookingNumber;
	private Integer doctorId;
	
	public CheckinVO() {
		super();
	}
	
	public CheckinVO(String id, String patientIdcard, Integer bookingNumber, Integer doctorId) {
		super();
		this.id = id;
		this.patientIdcard = patientIdcard;
		this.bookingNumber = bookingNumber;
		this.doctorId = doctorId;
	}
	
	

	@Override
	public String toString() {
		return "CheckinVO [id=" + id + ", patientIdcard=" + patientIdcard + ", bookingNumber=" + bookingNumber
				+ ", doctorId=" + doctorId + "]";
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPatientIdcard() {
		return patientIdcard;
	}
	public void setPatientIdcard(String patientIdcard) {
		this.patientIdcard = patientIdcard;
	}
	public Integer getBookingNumber() {
		return bookingNumber;
	}
	public void setBookingNumber(Integer bookingNumber) {
		this.bookingNumber = bookingNumber;
	}
	public Integer getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}
	
	
}
