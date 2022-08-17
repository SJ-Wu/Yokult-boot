package tibame.tga102.yokult;

import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import com.google.gson.Gson;

import tibame.tga102.yokult.orderlist.dao.OrderlistDao;
import tibame.tga102.yokult.orderlist.dao.impl.OrderlistDaoHibernate;
import tibame.tga102.yokult.orderlist.vo.Orderlist;
import tibame.tga102.yokult.orderlist.vo.OrderlistView;

@SpringBootTest
@Transactional
public class OrdelistViewTests {
	
	@Autowired
	OrderlistDao dao;
	@Autowired
	OrderlistDaoHibernate orderlistDaoHibernate;
	
//	@Test
//	public void testSearchOrderlistView() {
//		System.out.println("=====testSearchOrderlistView=====");
//		OrderlistView orderlist = new OrderlistView();
//		orderlist.setOrdid("2207290009");
//		List<Map> list = (dao.searchOrderlistTest(orderlist));
//		Gson gson = new Gson();
//		for (Map o: list) {
//			
//			System.out.println(gson.toJson(o));
//		}
//	}
	
	@Test
	public void testSearchOrderlist() {
		System.out.println("=====testSearchOrderlist=====");
		Orderlist orderlist = new Orderlist();
		orderlist.setOrdid("2207290009");
		List<Orderlist> list = dao.searchOrderlist(orderlist);
		for (Orderlist o: list) {
			System.out.println(o);
		}
	}
}
