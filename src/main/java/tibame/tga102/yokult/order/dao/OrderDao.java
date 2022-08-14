package tibame.tga102.yokult.order.dao;

import java.util.List;

import tibame.tga102.yokult.order.vo.Order;
import tibame.tga102.yokult.orderlist.vo.Orderlist;



public interface OrderDao {
	Integer insert(Order order);

	List<Order> selectAll();

	Integer insert(Orderlist orderlist);
}
