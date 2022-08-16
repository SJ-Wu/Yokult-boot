package tibame.tga102.yokult.checkout.vo;
import java.util.List;

import tibame.tga102.yokult.order.vo.Order;
import tibame.tga102.yokult.orderlist.vo.Orderlist;

public class Checkout {
	private String paymethod;
	private String totalCount;
	private Order order;
	private List<Orderlist> orderlist;
	
	public String getPaymethod() {
		return paymethod;
	}

	public void setPaymethod(String paymethod) {
		this.paymethod = paymethod;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public List<Orderlist> getOrderlist() {
		return orderlist;
	}

	public void setOrderlist(List<Orderlist> orderlist) {
		this.orderlist = orderlist;
	}

	@Override
	public String toString() {
		return "Checkout [order=" + order + ", orderlist=" + orderlist + "]";
	}
}
