package tibame.tga102.yokult.fundraising.vo;

import java.sql.Timestamp;
import java.util.Base64;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Transient;

import tibame.tga102.yokult.fundraising.dao.PlanDao;

@Entity
@Table(name="fund_plan")
public class PlanBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer planID;
	private String planName;
	private Integer planAmount;
	private String planContent;
	private String planPostNote;
	private java.sql.Date planPostDate;
	private java.sql.Date planStartedDateTime;
	private java.sql.Date planEndedDateTime;
	@Lob
	@Column(columnDefinition="MEDIUMBLOB")
	private byte[] planPicture;
	private Integer	proposalID;
//	private String planAttendeeCount;
	@Transient
	private Integer planPurchases;	
	@Transient
	private String planPictureBase64;
	@Transient
	private long planStartedDateTimeMillis;
	@Transient
	private long planEndedDateTimeMillis;
	

	public PlanBean renewBean(PlanDao planDAO) {
		List<OrderBean> list_orderBean = planDAO.getRelationalOrders(this);
		this.setPlanPurchases(list_orderBean.size());
		this.planPictureBase64 = Base64.getEncoder().encodeToString(this.planPicture);
		this.planStartedDateTimeMillis = this.planStartedDateTime.getTime();
		this.planEndedDateTimeMillis = this.planEndedDateTime.getTime();
		return this;
	}
	
	public PlanBean() {}

	
	public PlanBean(String planName, Integer planAmount, String planContent, String planPostNote,
			java.sql.Date planPostDate, java.sql.Date planStartedDateTime, java.sql.Date planEndedDateTime, byte[] planPicture,
			Integer proposalID, Integer planPurchases, String planPictureBase64) {
		super();
		this.planName = planName;
		this.planAmount = planAmount;
		this.planContent = planContent;
		this.planPostNote = planPostNote;
		this.planPostDate = planPostDate;
		this.planStartedDateTime = planStartedDateTime;
		this.planEndedDateTime = planEndedDateTime;
		this.planPicture = planPicture;
		this.proposalID = proposalID;
		this.planPurchases = planPurchases;
		this.planPictureBase64 = planPictureBase64;
	}



	@Override
	public String toString() {
		return "PlanBean [planID=" + planID + ", planName=" + planName + ", planAmount=" + planAmount + ", planContent="
				+ planContent + ", planPostNote=" + planPostNote + ", planPostDate=" + planPostDate
				+ ", planStartedDateTime=" + planStartedDateTime + ", planEndedDateTime=" + planEndedDateTime
				+ ", proposalID=" + proposalID + ", planPurchases=" + planPurchases + ", planStartedDateTimeMillis="
				+ planStartedDateTimeMillis + ", planEndedDateTimeMillis=" + planEndedDateTimeMillis + "]";
	}





	public Integer getPlanID() {
		return planID;
	}

	public void setPlanID(Integer planID) {
		this.planID = planID;
	}

	public String getPlanName() {
		return planName;
	}

	public void setPlanName(String planName) {
		this.planName = planName;
	}

	public Integer getPlanAmount() {
		return planAmount;
	}

	public void setPlanAmount(Integer planAmount) {
		this.planAmount = planAmount;
	}

	public String getPlanContent() {
		return planContent;
	}

	public void setPlanContent(String planContent) {
		this.planContent = planContent;
	}

	public String getPlanPostNote() {
		return planPostNote;
	}

	public void setPlanPostNote(String planPostNote) {
		this.planPostNote = planPostNote;
	}

	public java.sql.Date getPlanPostDate() {
		return planPostDate;
	}

	public void setPlanPostDate(java.sql.Date planPostDate) {
		this.planPostDate = planPostDate;
	}

	public java.sql.Date getPlanStartedDateTime() {
		return planStartedDateTime;
	}

	public void setPlanStartedDateTime(java.sql.Date planStartedDateTime) {
		this.planStartedDateTime = planStartedDateTime;
	}

	public java.sql.Date getPlanEndedDateTime() {
		return planEndedDateTime;
	}

	public void setPlanEndedDateTime(java.sql.Date planEndedDateTime) {
		this.planEndedDateTime = planEndedDateTime;
	}

	public byte[] getPlanPicture() {
		return planPicture;
	}

	public void setPlanPicture(byte[] planPicture) {
		this.planPicture = planPicture;
	}

	public Integer getProposalID() {
		return proposalID;
	}

	public void setProposalID(Integer proposalID) {
		this.proposalID = proposalID;
	}

	public Integer getPlanPurchases() {
		return planPurchases;
	}

	public void setPlanPurchases(Integer planPurchases) {
		this.planPurchases = planPurchases;
	}

	public String getPlanPictureBase64() {
		return planPictureBase64;
	}

	public void setPlanPictureBase64(String planPictureBase64) {
		this.planPictureBase64 = planPictureBase64;
	}

	public long getPlanStartedDateTimeMillis() {
		return planStartedDateTimeMillis;
	}

	public void setPlanStartedDateTimeMillis(long planStartedDateTimeMillis) {
		this.planStartedDateTimeMillis = planStartedDateTimeMillis;
	}

	public long getPlanEndedDateTimeMillis() {
		return planEndedDateTimeMillis;
	}

	public void setPlanEndedDateTimeMillis(long planEndedDateTimeMillis) {
		this.planEndedDateTimeMillis = planEndedDateTimeMillis;
	}

	

}
