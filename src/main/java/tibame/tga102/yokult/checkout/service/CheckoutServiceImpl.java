package tibame.tga102.yokult.checkout.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;

import tibame.tga102.yokult.checkout.vo.Checkout;
import tibame.tga102.yokult.order.dao.OrderDaoJDBC;
import tibame.tga102.yokult.order.service.OrderService;
import tibame.tga102.yokult.order.service.OrderServiceImpl;
import tibame.tga102.yokult.orderlist.service.OrderlistService;
import tibame.tga102.yokult.orderlist.service.impl.OrderlistServiceImpl;
import tibame.tga102.yokult.orderlist.vo.OrderlistView;

public class CheckoutServiceImpl implements CheckoutService {
	private OrderService orderService;
	private OrderlistService orderlistService;
	private OrderDaoJDBC dao = new OrderDaoJDBC();
	
//	
//	Order order = new Order();
//	List<String> list = new ArrayList<>();
	public CheckoutServiceImpl() throws NamingException {
		orderService = new OrderServiceImpl();
		orderlistService = new OrderlistServiceImpl();
	}

	@Override
	public String processCheckout(Checkout checkout) { 
		List<String> list = new ArrayList<>();
		if ("Success".equals(orderService.addOrder(checkout.getOrder()))) {
			if ("Success".equals(orderlistService.addOrderlist(checkout.getOrderlist(), checkout.getOrder().getOrdid()))) {
				List<OrderlistView>orderlistView = orderlistService.searchOrderlistViewByOrdid(checkout.getOrder().getOrdid());
				for (OrderlistView item: orderlistView) {
					list.add(item.getProName());
				}
				return orderService.ecpayValidation(list,checkout.getOrder(),checkout.getTotalCount()); // get到ordertotal，從orderdaojdbc 撰寫 checkout.getTotalCount()
			}
		}
		return "False";
	}
	
	
}
