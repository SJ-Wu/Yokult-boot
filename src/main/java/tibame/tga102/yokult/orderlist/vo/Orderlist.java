package tibame.tga102.yokult.orderlist.vo;

public class Orderlist {
	private Integer orderlistid; // 訂單商品編號
	private String proID;// 商品ID
	private Integer proPrice;// 商品價格
	private Integer quantity;// 訂購數量
	private String ordid;// 訂單

	@Override
	public String toString() {
		return "Orderlist [orderlistid=" + orderlistid + ", proid=" + proID + ", proprice=" + proPrice + ", quantity="
				+ quantity + ", ordid=" + ordid + "]";
	}

	public Integer getOrderlistid() {
		return orderlistid;
	}

	public void setOrderlistid(Integer orderlistid) {
		this.orderlistid = orderlistid;
	}

	public String getProID() {
		return proID;
	}

	public void setProID(String proID) {
		this.proID = proID;
	}

	public Integer getProPrice() {
		return proPrice;
	}

	public void setProprice(Integer proPrice) {
		this.proPrice = proPrice;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getOrdid() {
		return ordid;
	}

	public void setOrdid(String ordid) {
		this.ordid = ordid;
	}

}
