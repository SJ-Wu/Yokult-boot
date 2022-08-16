package tibame.tga102.yokult.order.dao;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tibame.tga102.yokult.order.vo.Order;

@SpringBootTest
public class OrderDaoHibernateTests {
	@Test
	void contextLoads() {
		
	}
	
	@Autowired
	private OrderDao orderDao;
	
	@Test
	public void testInsert() {
		Order order = new Order();
		System.out.println("====Order insert test====");
		order.setMemid("TGA001");
		order.setOrdid("2207290001");
		order.setPaymethod("cash");
		order.setOrderstatus("complete");
		order.setAddr("AAAAAA");
		order.setReceipter("Lobster");
		Long datetime = System.currentTimeMillis();
        Timestamp timestamp = new Timestamp(datetime);
		order.setShoptime(timestamp);
		order.setCellphone("0987654321");
		Integer result = orderDao.insert(order);
		System.out.println(result);
		
	}
	@Test
	public void testSelectAll() {
		System.out.println("========TestListAll========");
		List<Order> list = orderDao.selectAll();
		for (Order o:list) {
			System.out.println(o);
		}
	}
	
	
}
