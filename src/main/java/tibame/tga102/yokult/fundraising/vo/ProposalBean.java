package tibame.tga102.yokult.fundraising.vo;

import java.sql.Timestamp;
import java.util.Base64;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import tibame.tga102.yokult.fundraising.dao.ProposalDao;

@Entity
@Table(name="fund_proposal")
public class ProposalBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer	proposalID;
	private String proposalName;
	private String proposalHostName;
	private Integer proposalGoal;
	private String proposalCategoryID;
	private Timestamp proposalStartedDateTime;
	private Timestamp proposalEndedDateTime;
	private String proposalEmail;
	private String proposalCellphone;
	private String proposalSummary;
	private String proposalHtmlContent;
	@Lob
	@Column(columnDefinition="MEDIUMBLOB")
	private byte[] proposalPicture;
	@Lob
	@Column(columnDefinition="MEDIUMBLOB")
	private byte[] proposalPictureZip;
	private String memID;
	@Transient
	private String proposalPictureBase64;
	@Transient
	private String proposalPictureBaseZip64;
	@Transient
	private long proposalStartedDateTimeMillis;
	@Transient
	private long proposalEndedDateTimeMillis;
	@Transient
	private long currentMillis;
	@Transient
	private Integer proposalAmount;
	
	public ProposalBean renewBean(ProposalDao proposalDAO) {
		this.proposalPictureBase64 = Base64.getEncoder().encodeToString(this.proposalPicture);
		this.proposalPictureBaseZip64 = Base64.getEncoder().encodeToString(this.proposalPictureZip);
		this.proposalStartedDateTimeMillis = this.proposalStartedDateTime.getTime();
		this.proposalEndedDateTimeMillis = this.proposalEndedDateTime.getTime();
		this.currentMillis = System.currentTimeMillis();
		this.proposalAmount = proposalDAO.currentTotalAmount(this);
		return this;
	}
	
	
	public ProposalBean() {}

	public ProposalBean(String proposalName, String proposalHostName, Integer proposalGoal,
			String proposalCategoryID, Timestamp proposalStartedDateTime, Timestamp proposalEndedDateTime,
			String proposalEmail, String proposalCellphone, String proposalSummary,
			byte[] proposalPicture, byte[] proposalPictureZip, String memID, String proposalPictureBase64,
			String proposalPictureBaseZip64, long proposalStartedDateTimeMillis, long proposalEndedDateTimeMillis) {
		super();
		this.proposalName = proposalName;
		this.proposalHostName = proposalHostName;
		this.proposalGoal = proposalGoal;
		this.proposalCategoryID = proposalCategoryID;
		this.proposalStartedDateTime = proposalStartedDateTime;
		this.proposalEndedDateTime = proposalEndedDateTime;
		this.proposalEmail = proposalEmail;
		this.proposalCellphone = proposalCellphone;
		this.proposalSummary = proposalSummary;
		this.proposalPicture = proposalPicture;
		this.proposalPictureZip = proposalPictureZip;
		this.memID = memID;
		this.proposalPictureBase64 = proposalPictureBase64;
		this.proposalPictureBaseZip64 = proposalPictureBaseZip64;
		this.proposalStartedDateTimeMillis = proposalStartedDateTimeMillis;
		this.proposalEndedDateTimeMillis = proposalEndedDateTimeMillis;
	}

	
	@Override
	public String toString() {
		return "ProposalBean [proposalID=" + proposalID + ", proposalName=" + proposalName + ", proposalHostName="
				+ proposalHostName + ", proposalGoal=" + proposalGoal + ", proposalCategoryID=" + proposalCategoryID
				+ ", proposalStartedDateTime=" + proposalStartedDateTime + ", proposalEndedDateTime="
				+ proposalEndedDateTime + ", proposalEmail=" + proposalEmail + ", proposalCellphone="
				+ proposalCellphone + ", proposalSummary=" + proposalSummary + ", proposalHtmlContent="
				+ proposalHtmlContent + ", memID=" + memID + ", proposalStartedDateTimeMillis="
				+ proposalStartedDateTimeMillis + ", proposalEndedDateTimeMillis=" + proposalEndedDateTimeMillis
				+ ", currentMillis=" + currentMillis + ", proposalAmount=" + proposalAmount + "]";
	}


	public Integer getProposalID() {
		return proposalID;
	}


	public String getProposalName() {
		return proposalName;
	}


	public void setProposalName(String proposalName) {
		this.proposalName = proposalName;
	}


	public String getProposalHostName() {
		return proposalHostName;
	}


	public void setProposalHostName(String proposalHostName) {
		this.proposalHostName = proposalHostName;
	}


	public Integer getProposalGoal() {
		return proposalGoal;
	}


	public void setProposalGoal(Integer proposalGoal) {
		this.proposalGoal = proposalGoal;
	}


	public String getProposalCategoryID() {
		return proposalCategoryID;
	}


	public void setProposalCategoryID(String proposalCategoryID) {
		this.proposalCategoryID = proposalCategoryID;
	}


	public Timestamp getProposalStartedDateTime() {
		return proposalStartedDateTime;
	}


	public void setProposalStartedDateTime(Timestamp proposalStartedDateTime) {
		this.proposalStartedDateTime = proposalStartedDateTime;
	}


	public Timestamp getProposalEndedDateTime() {
		return proposalEndedDateTime;
	}


	public void setProposalEndedDateTime(Timestamp proposalEndedDateTime) {
		this.proposalEndedDateTime = proposalEndedDateTime;
	}


	public String getProposalEmail() {
		return proposalEmail;
	}


	public void setProposalEmail(String proposalEmail) {
		this.proposalEmail = proposalEmail;
	}


	public String getProposalCellphone() {
		return proposalCellphone;
	}


	public void setProposalCellphone(String proposalCellphone) {
		this.proposalCellphone = proposalCellphone;
	}


	public String getProposalSummary() {
		return proposalSummary;
	}


	public void setProposalSummary(String proposalSummary) {
		this.proposalSummary = proposalSummary;
	}


	public byte[] getProposalPicture() {
		return proposalPicture;
	}


	public void setProposalPicture(byte[] proposalPicture) {
		this.proposalPicture = proposalPicture;
	}


	public byte[] getProposalPictureZip() {
		return proposalPictureZip;
	}


	public void setProposalPictureZip(byte[] proposalPictureZip) {
		this.proposalPictureZip = proposalPictureZip;
	}


	public String getMemID() {
		return memID;
	}


	public void setMemID(String memID) {
		this.memID = memID;
	}


	public String getProposalPictureBase64() {
		return proposalPictureBase64;
	}


	public void setProposalPictureBase64(String proposalPictureBase64) {
		this.proposalPictureBase64 = proposalPictureBase64;
	}


	public String getProposalPictureBaseZip64() {
		return proposalPictureBaseZip64;
	}


	public void setProposalPictureBaseZip64(String proposalPictureBaseZip64) {
		this.proposalPictureBaseZip64 = proposalPictureBaseZip64;
	}


	public long getProposalStartedDateTimeMillis() {
		return proposalStartedDateTimeMillis;
	}


	public void setProposalStartedDateTimeMillis(long proposalStartedDateTimeMillis) {
		this.proposalStartedDateTimeMillis = proposalStartedDateTimeMillis;
	}


	public long getProposalEndedDateTimeMillis() {
		return proposalEndedDateTimeMillis;
	}


	public void setProposalEndedDateTimeMillis(long proposalEndedDateTimeMillis) {
		this.proposalEndedDateTimeMillis = proposalEndedDateTimeMillis;
	}


	public String getHtml_content() {
		return proposalHtmlContent;
	}


	public void setHtml_content(String proposalHtmlContent) {
		this.proposalHtmlContent = proposalHtmlContent;
	}


	
	
	
	
	
}
