package tibame.tga102.yokult.member.vo;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "member")
@Component
public class Member {
	@Id
	@Column(name = "memid")
	private String memID;
	
	@Column(name = "password")
	private String memPassword;
	
	@Column(name = "email")
	private String memEmail;
	
	@Column(name = "name")
	private String memName;
	
	@Column(name = "birth", nullable = true)
	private Date memBirth;
	
	@Column(name = "cellphone", length = 10)
	private String memCellPhone;
	
	@Column(name = "addr", nullable = true)
	private String memAddress;
	
	@Column(name = "status")
	private String memStatus;

	@Override
	public String toString() {
		return "Member [memID=" + memID + ", memPassword=" + memPassword + ", memEmail=" + memEmail + ", memName="
				+ memName + ", memBirth=" + memBirth + ", memCellPhone=" + memCellPhone + ", memAddress=" + memAddress
				+ ", memStatus=" + memStatus + "]";
	}

	public String getMemID() {
		return memID;
	}

	public void setMemID(String memID) {
		this.memID = memID;
	}

	public String getMemPassword() {
		return memPassword;
	}

	public void setMemPassword(String memPassword) {
		this.memPassword = memPassword;
	}

	public String getMemEmail() {
		return memEmail;
	}

	public void setMemEmail(String memEmail) {
		this.memEmail = memEmail;
	}

	public String getMemName() {
		return memName;
	}

	public void setMemName(String memName) {
		this.memName = memName;
	}

	public Date getMemBirth() {
		return memBirth;
	}

	public void setMemBirth(Date memBirth) {
		this.memBirth = memBirth;
	}

	public String getMemCellPhone() {
		return memCellPhone;
	}

	public void setMemCellPhone(String memCellPhone) {
		this.memCellPhone = memCellPhone;
	}

	public String getMemAddress() {
		return memAddress;
	}

	public void setMemAddress(String memAddress) {
		this.memAddress = memAddress;
	}

	public String getMemStatus() {
		return memStatus;
	}

	public void setMemStatus(String memStatus) {
		this.memStatus = memStatus;
	}
}
