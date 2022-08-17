package tibame.tga102.yokult.orderlist.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import tibame.tga102.yokult.orderlist.service.OrderlistService;
import tibame.tga102.yokult.orderlist.vo.Orderlist;
import tibame.tga102.yokult.orderlist.vo.OrderlistView;
import tibame.tga102.yokult.util.YokultConstants;

@RestController
@RequestMapping(path= {YokultConstants.ORDERLIST_API})
public class OrderlistController {
	
	@Autowired
	private OrderlistService orderlistService;
	
	// 查詢
	@GetMapping
	public Map<String, Object> getOrderlist(@RequestParam String ordId) {
		Map<String, Object> respObject = new HashedMap<String, Object>();
		List<Map> orderlist = orderlistService.searchOrderlistViewByOrdid(ordId);
		
//		List<OrderlistView> orderlist = orderlistService.searchOrderlistViewByOrdid(ordId);
		System.out.println("============================TEST=============================");
		System.out.println(orderlist);
		if (orderlist != null) {
			respObject.put("msg", "success");
			respObject.put("Orderlists", orderlist);
			return respObject;
		}
		return null;
	}
}
