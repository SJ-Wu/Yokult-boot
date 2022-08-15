package tibame.tga102.yokult.orderlist.service;

import java.util.List;

import tibame.tga102.yokult.orderlist.vo.Orderlist;
import tibame.tga102.yokult.orderlist.vo.OrderlistView;

public interface OrderlistService {
	List<Orderlist> searchOrderlistByOrdid(String orderID); //查詢
	List<OrderlistView> searchOrderlistViewByOrdid(String orderID); //查詢
	String addOrderlist(List<Orderlist> orderlists, String ordid);
	Integer insertOrderlist(Orderlist orderlist); //新增
	Integer deleteOrderlist(Orderlist orderlist); //刪除
	Integer modifyOrderlist(Orderlist orderlist); //修改
	
	
}
