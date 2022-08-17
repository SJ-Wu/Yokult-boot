package tibame.tga102.yokult.checkout.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tibame.tga102.yokult.checkout.vo.Checkout;
import tibame.tga102.yokult.order.service.OrderService;
import tibame.tga102.yokult.orderlist.service.OrderlistService;
import tibame.tga102.yokult.orderlist.vo.OrderlistView;

@Service
@Transactional
public class CheckoutServiceImpl implements CheckoutService {
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderlistService orderlistService;

	@Override
	public String processCheckout(Checkout checkout) {
		List<String> list = new ArrayList<>();
		if ("Success".equals(orderService.addOrder(checkout.getOrder()))) {
			if ("Success"
					.equals(orderlistService.addOrderlist(checkout.getOrderlist(), checkout.getOrder().getOrdid()))) {
				List<Map> orderlistView = orderlistService
						.searchOrderlistViewByOrdid(checkout.getOrder().getOrdid());
				for (Map item : orderlistView) {
					System.out.println("ItemgetProName: "+item.get("proName"));
					list.add(item.get("proName").toString());
				}
				System.out.println("========Order build successfully=======");
				if ("creditcard".equals(checkout.getPaymethod())) {
					// get到ordertotal，從orderdaojdbc
					// 撰寫
					// checkout.getTotalCount()
					return orderService.ecpayValidation(list, checkout.getOrder(), checkout.getTotalCount());
				} else {
					return "Success";
				}
			}
		}
		return "False";
	}
}
