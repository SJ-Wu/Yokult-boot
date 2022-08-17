package tibame.tga102.yokult.orderlist.vo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "v_admin_orderlist")
public class OrderlistView {
	
	@Id
	@Column(name = "ordid")
	private String ordid;
	
	@Column(name = "orderlistid")
	private Integer orderlistid; // 訂單商品編號
	
	@Column(name = "proname")
	private String proName;// 商品ID
	
	@Column(name = "quantity")
	private Integer quantity;// 訂購數量

	@Override
	public String toString() {
		return "OrderlistView [ordid=" + ordid + ", orderlistid=" + orderlistid + ", proName=" + proName + ", quantity="
				+ quantity + "]";
	}

	public String getOrdid() {
		return ordid;
	}

	public void setOrdid(String ordid) {
		this.ordid = ordid;
	}

	public Integer getOrderlistid() {
		return orderlistid;
	}

	public void setOrderlistid(Integer orderlistid) {
		this.orderlistid = orderlistid;
	}

	public String getProName() {
		return proName;
	}

	public void setProName(String proName) {
		this.proName = proName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Integer getProprice() {
		// TODO Auto-generated method stub
		return null;
	}
}
