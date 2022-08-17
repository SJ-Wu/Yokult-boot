package tibame.tga102.yokult.orderlist.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "orderlist")
public class Orderlist {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orderlistid")
	private Integer orderlistid; // 訂單商品編號
	
	@Column(name = "proid")
	private Integer proID;// 商品ID
	
	@Column(name = "proprice")
	private Integer proPrice;// 商品價格
	
	@Column(name = "quantity")
	private Integer quantity;// 訂購數量
	
	@Column(name ="ordid")
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

	public Integer getProID() {
		return proID;
	}

	public void setProID(Integer proID) {
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
