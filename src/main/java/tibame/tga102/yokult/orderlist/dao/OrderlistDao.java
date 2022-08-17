package tibame.tga102.yokult.orderlist.dao;

import java.util.List;
import java.util.Map;

import tibame.tga102.yokult.orderlist.vo.Orderlist;
import tibame.tga102.yokult.orderlist.vo.OrderlistView;

public interface OrderlistDao {
	List<Orderlist> searchOrderlist(Orderlist orderlist); //查詢（界面）
	List<OrderlistView> searchOrderlistView(OrderlistView orderlist); //查詢（界面）
	Integer insertOrderlist(Orderlist orderlist); //新增（界面）
	Integer deleteOrderlist(Orderlist orderlist); //刪除（界面）
	Integer modifyOrderlist(Orderlist orderlist);  //修改（界面）
	List<Orderlist> selectAll();
	List<Map> searchOrderlistTest(String ordId);
}