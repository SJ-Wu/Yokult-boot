package orderlist.dao;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tibame.tga102.yokult.orderlist.dao.OrderlistDao;
import tibame.tga102.yokult.orderlist.vo.Orderlist;

@SpringBootTest
public class OrderlistDaoHibernateTest {
	@Test
	void contextLoads() {
		
		
	}	
		@Autowired
		private OrderlistDao orderlistDao;
		
		@Test
		public void testInsert() {
			Orderlist orderlist = new Orderlist();
			System.out.println("====Orderlist insert test====");
			orderlist.setProID(1);
			orderlist.setProprice(339);
			orderlist.setQuantity(1);
			orderlist.setOrdid("2207290001");
			Integer result = orderlistDao.insert(orderlist);
			System.out.println(result);
		}
		@Test
		public void testSelectAll() {
			System.out.println("========TestListAll========");
			List<Orderlist> list = orderlistDao.selectAll();
			for (Orderlist o:list) {
				System.out.println(o);
			}
		}
	
	

}
