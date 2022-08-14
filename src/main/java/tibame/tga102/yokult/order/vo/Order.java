package tibame.tga102.yokult.order.vo;

import java.sql.Timestamp;

public class Order {
	//轉hibeernate要額外@transient
	private String totalCount;
	
	private String ordid;
	private String memid;
	private String paymethod;
	private String orderstatus;
	private String addr;
	private String receipter;
	private Timestamp shoptime;
	private String cellphone;
	private String phone;

	@Override
	public String toString() {
		return "Order [ordid=" + ordid + ", memid=" + memid + ", paymethod=" + paymethod + ", orderstatus="
				+ orderstatus + ", addr=" + addr + ", receipter=" + receipter + ", shoptime=" + shoptime
				+ ", cellphone=" + cellphone + ", phone=" + phone + "]";
	}

	public String getOrdid() {
		return ordid;
	}

	public void setOrdid(String ordid) {
		this.ordid = ordid;
	}

	public String getMemid() {
		return memid;
	}

	public void setMemid(String memid) {
		this.memid = memid;
	}

	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getOrderstatus() {
		return orderstatus;
	}

	public void setOrderstatus(String orderstatus) {
		this.orderstatus = orderstatus;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}

	public String getReceipter() {
		return receipter;
	}

	public void setReceipter(String receipter) {
		this.receipter = receipter;
	}

	public Timestamp getShoptime() {
		return shoptime;
	}

	public void setShoptime(Timestamp shoptime) {
		this.shoptime = shoptime;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Object getTotal() {
		
		return null;
	}

	public Object getPromoteId() {
		
		return null;
	}

}
