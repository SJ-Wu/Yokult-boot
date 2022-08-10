package web.booking.vo;

import java.io.Serializable;
import java.sql.Date;

public class PatientBookingVO implements Serializable{

	private Date date1;
	private Date date2;
	private Integer doctorId;
//	private String doctorName;
	
	@Override
	public String toString() {
		return "PatientBookingVO [date1=" + date1 + ", date2=" + date2 + ", doctorId=" + doctorId + "]";
	}

	public PatientBookingVO() {
	}



//	public String getDate1() {
//		return date1;
//	}
//
//	public void setDate1(String date1) {
//		this.date1 = date1;
//	}
//
//	public String getDate2() {
//		return date2;
//	}
//
//	public void setDate2(String date2) {
//		this.date2 = date2;
//	}



	public Date getDate1() {
		return date1;
	}

	public PatientBookingVO(Date date1, Date date2, Integer doctorId) {
	super();
	this.date1 = date1;
	this.date2 = date2;
	this.doctorId = doctorId;
}

	public void setDate1(Date date1) {
		this.date1 = date1;
	}

	public Date getDate2() {
		return date2;
	}

	public void setDate2(Date date2) {
		this.date2 = date2;
	}

	public Integer getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(Integer doctorId) {
		this.doctorId = doctorId;
	}


//	public String getDoctorId() {
//		return doctorId;
//	}
//
//	public void setDoctorId(String doctorId) {
//		this.doctorId = doctorId;
//	}
	
//	public String getDoctorName() {
//		return doctorName;
//	}
//
//
//	public void setDoctorName(String doctorName) {
//		this.doctorName = doctorName;
//	}

	
	

}
