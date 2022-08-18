package tibame.tga102.yokult.fundraising.vo;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="fund_order")
public class OrderBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderID;
	private String orderInvoiceNumber;
//	@Column(name = "orderDateTime", columnDefinition = "DATE")
	private Timestamp orderDateTime;
	private Integer orderAmount;
	private Integer proposalID;
	private Integer planID;
	private Integer postID;
	private String memID;
	//new java.sql.Date(System.currentTimeMillis());    <-----取得當前時間毫秒數
//	long long1 = System.currentTimeMillis();
//	java.sql.Timestamp ts = new java.sql.Timestamp(long1);
//	System.out.println(ts);
//	java.text.DateFormat df = new java.text.SimpleDateFormat("yyyy-mm-dd hh:mm:ss:SSS");
//	String str = df.format(ts);
//	System.out.println(str);
	

	public OrderBean() {}

	public OrderBean(String orderInvoiceNumber, Timestamp orderDateTime, Integer orderAmount,
			Integer proposalID, Integer planID, Integer postID, String memID) {
		super();
		this.orderInvoiceNumber = orderInvoiceNumber;
		this.orderDateTime = orderDateTime;
		this.orderAmount = orderAmount;
		this.proposalID = proposalID;
		this.planID = planID;
		this.postID = postID;
		this.memID = memID;
	}
	
	public OrderBean(Integer orderID, String orderInvoiceNumber, Timestamp orderDateTime, Integer orderAmount,
			Integer proposalID, Integer planID, Integer postID, String memID) {
		super();
		this.orderID = orderID;
		this.orderInvoiceNumber = orderInvoiceNumber;
		this.orderDateTime = orderDateTime;
		this.orderAmount = orderAmount;
		this.proposalID = proposalID;
		this.planID = planID;
		this.postID = postID;
		this.memID = memID;
	}

	@Override
	public String toString() {
		return "OrderBean [orderID=" + orderID + ", orderInvoiceNumber=" + orderInvoiceNumber + ", orderDateTime="
				+ orderDateTime + ", orderAmount=" + orderAmount + ", proposalID=" + proposalID + ", planID=" + planID
				+ ", postID=" + postID+ ", memID=" + memID  + "]";
	}
	
	public Integer getOrderID() {
		return orderID;
	}
	public void setOrderID(Integer orderID) {
		this.orderID = orderID;
	}
	public String getOrderInvoiceNumber() {
		return orderInvoiceNumber;
	}
	public void setOrderInvoiceNumber(String orderInvoiceNumber) {
		this.orderInvoiceNumber = orderInvoiceNumber;
	}
	public Timestamp getOrderDateTime() {
		return orderDateTime;
	}
	public void setOrderDateTime(Timestamp startDate) {
		this.orderDateTime = startDate;
	}
	public Integer getOrderAmount() {
		return orderAmount;
	}
	public void setOrderAmount(Integer orderAmount) {
		this.orderAmount = orderAmount;
	}
	public Integer getProposalID() {
		return proposalID;
	}
	public void setProposalID(Integer proposalID) {
		this.proposalID = proposalID;
	}
	public Integer getPlanID() {
		return planID;
	}
	public void setPlanID(Integer planID) {
		this.planID = planID;
	}
	public Integer getPostID() {
		return postID;
	}
	public void setPostID(Integer postID) {
		this.postID = postID;
	}
	public String getMemID() {
		return memID;
	}
	public void setMemID(String memID) {
		this.memID = memID;
	}
	
}
