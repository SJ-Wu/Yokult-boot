package tibame.tga102.yokult.order.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tibame.tga102.yokult.order.service.OrderService;
import tibame.tga102.yokult.order.vo.Order;
import tibame.tga102.yokult.util.YokultConstants;

@RestController
@RequestMapping(path= {YokultConstants.ORDER_API})
public class OrderController {

	@Autowired
	private OrderService orderService;
	
	@GetMapping
	public Map<String, Object> selectAll() {
		Map<String ,Object> respObject = new HashMap<String, Object>();
		List<Order> orders = orderService.searchOrders();
		if (orders != null) {
			respObject.put("msg", "success");
			respObject.put("orders", orders);
			return respObject;
		}
		return null;
	}
	
	@PostMapping(path = "selectOrderid")
	public Map<String, Object> selectOrderid(@RequestBody Order order) {
		Map<String, Object> respObject = new HashMap<String, Object>();
		Order vo = orderService.selectOrderid(order.getOrdid());
		if (vo == null) {
			respObject.put("msg", "no this order");
		} else {
			respObject.put("msg", "success");
			respObject.put("order", vo);
		}
		return respObject;
	}
	
	@PostMapping(path = "selectOrderStatus")
	public Map<String, Object> selectOrderStatus(@RequestBody Order order) {
		Map<String, Object> respObject = new HashMap<String, Object>();
		List<Order> list = orderService.selectOrderStatus(order.getOrderstatus());
		if (list == null) {
			respObject.put("msg", "no this order");
		} else {
			respObject.put("msg", "success");
			respObject.put("order", list);
		}
		return respObject;
	}
}
