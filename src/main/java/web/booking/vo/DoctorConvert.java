package web.booking.vo;

import java.util.List;

public class DoctorConvert {
//	@Column(name="DOCTOR_ALPHABET")
	private String doctorAlphabet;
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(name = "DOCTOR_ID")
	private Integer doctorId;
//	@Column(name = "DOCTOR_NAME")
	private String doctorName;
//	@Column(name = "DOCTOR_PHOTO")
	private String doctorPhoto;
//	@Column(name = "DOCTOR_CERTIFICATE")
	private String doctorCertificate;
//	@Column(name = "DOCTOR_EMAIL")
	private String doctorEmail;
//	@Column(name = "DOCTOR_PASSWARD")
	private String doctorPassword;
	
	private List<DoctorSchedule> listOfDoctorSchedule;
	
	public List<DoctorSchedule> getListOfDoctorSchedule() {
		return listOfDoctorSchedule;
	}
	public void setListOfDoctorSchedule(List<DoctorSchedule> listOfDoctorSchedule) {
		this.listOfDoctorSchedule = listOfDoctorSchedule;
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
	public String getDoctorName() {
		return doctorName;
	}
	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}
	public String getDoctorPhoto() {
		return doctorPhoto;
	}
	public void setDoctorPhoto(String doctorPhoto) {
		this.doctorPhoto = doctorPhoto;
	}
	public String getDoctorCertificate() {
		return doctorCertificate;
	}
	public void setDoctorCertificate(String doctorCertificate) {
		this.doctorCertificate = doctorCertificate;
	}
	public String getDoctorEmail() {
		return doctorEmail;
	}
	public void setDoctorEmail(String doctorEmail) {
		this.doctorEmail = doctorEmail;
	}
	public String getDoctorPassword() {
		return doctorPassword;
	}
	public void setDoctorPassword(String doctorPassword) {
		this.doctorPassword = doctorPassword;
	}
	public DoctorConvert() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DoctorConvert(String doctorAlphabet, Integer doctorId, String doctorName, String doctorPhoto,
			String doctorCertificate, String doctorEmail, String doctorPassword) {
		super();
		this.doctorAlphabet = doctorAlphabet;
		this.doctorId = doctorId;
		this.doctorName = doctorName;
		this.doctorPhoto = doctorPhoto;
		this.doctorCertificate = doctorCertificate;
		this.doctorEmail = doctorEmail;
		this.doctorPassword = doctorPassword;
	}
}
