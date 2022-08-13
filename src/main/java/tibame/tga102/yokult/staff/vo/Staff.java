package tibame.tga102.yokult.staff.vo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Staff {

	@Id
	private String staff_id;

	private String staff_name;

	private String staff_email;

	private String staff_idnumber;

	@Column(columnDefinition = "DATETIME")
	private Date staff_birthday;

	private String staff_phone;
	
	@Column(columnDefinition = "LONGBLOB")
	private String staff_picture;

	private String annual_leave;

	private String personal_leave;

	private String official_leave;

	@Override
	public String toString() {
		return "Staff [staff_id=" + staff_id + ", staff_name=" + staff_name + ", staff_email=" + staff_email
				+ ", staff_idnumber=" + staff_idnumber + ", staff_birthday=" + staff_birthday + ", staff_phone="
				+ staff_phone + ", staff_picture=" + (staff_picture) + ", annual_leave=" + annual_leave
				+ ", personal_leave=" + personal_leave + ", official_leave=" + official_leave + "]";
	}

	public String getStaff_id() {
		return staff_id;
	}

	public void setStaff_id(String staff_id) {
		this.staff_id = staff_id;
	}

	public String getStaff_name() {
		return staff_name;
	}

	public void setStaff_name(String staff_name) {
		this.staff_name = staff_name;
	}

	public String getStaff_email() {
		return staff_email;
	}

	public void setStaff_email(String staff_email) {
		this.staff_email = staff_email;
	}

	public String getStaff_idnumber() {
		return staff_idnumber;
	}

	public void setStaff_idnumber(String staff_idnumber) {
		this.staff_idnumber = staff_idnumber;
	}

	public Date getStaff_birthday() {
		return staff_birthday;
	}

	public void setStaff_birthday(Date staff_birthday) {
		this.staff_birthday = staff_birthday;
	}

	public String getStaff_phone() {
		return staff_phone;
	}

	public void setStaff_phone(String staff_phone) {
		this.staff_phone = staff_phone;
	}

	public String getStaff_picture() {
		return staff_picture;
	}

	public void setStaff_picture(String staff_picture) {
		this.staff_picture = staff_picture;
	}

	public String getAnnual_leave() {
		return annual_leave;
	}

	public void setAnnual_leave(String annual_leave) {
		this.annual_leave = annual_leave;
	}

	public String getPersonal_leave() {
		return personal_leave;
	}

	public void setPersonal_leave(String personal_leave) {
		this.personal_leave = personal_leave;
	}

	public String getOfficial_leave() {
		return official_leave;
	}

	public void setOfficial_leave(String official_leave) {
		this.official_leave = official_leave;
	}


}