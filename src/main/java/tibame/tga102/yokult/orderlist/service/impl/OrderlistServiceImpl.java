package tibame.tga102.yokult.orderlist.service.impl;

import java.util.List;
import java.util.Objects;

import javax.naming.NamingException;

import tibame.tga102.yokult.orderlist.dao.OrderlistDao;
import tibame.tga102.yokult.orderlist.dao.impl.OrderlistDaoJDBC;
import tibame.tga102.yokult.orderlist.service.OrderlistService;
import tibame.tga102.yokult.orderlist.vo.Orderlist;
import tibame.tga102.yokult.orderlist.vo.OrderlistView;

public class OrderlistServiceImpl implements OrderlistService {
	private OrderlistDao orderlistDao; // 把 dao 變成一個屬性

	public OrderlistServiceImpl() throws NamingException {
		orderlistDao = new OrderlistDaoJDBC();
	}

	@Override
	// 查詢
	public List<Orderlist> searchOrderlistByOrdid(String orderID) {
		if (checkValue(orderID)) {
			Orderlist orderlist = new Orderlist();
			orderlist.setOrdid(orderID);
			return orderlistDao.searchOrderlist(orderlist);
		}
		return null;
	}

	private boolean checkValue(String value) {
		if (value == null || Objects.equals(value, "")) {
			System.out.println("[Error] Input null value");
			return false;
		}
		return true;
	}

	@Override
	// 新增
	public Integer insertOrderlist(Orderlist orderlist) {
		// 1. Check orderid is valid
		// 2. Check proid is valid
		return orderlistDao.insertOrderlist(orderlist);
	}
	
	@Override
	public String addOrderlist(List<Orderlist> orderlists, String ordid) {
		for (Orderlist orderlist: orderlists) {
			orderlist.setOrdid(ordid);
			if (insertOrderlist(orderlist) < 0) {
				return "Flase";
			}
		}
		return "Success";
	}
	
	@Override
	// 刪除
	public Integer deleteOrderlist(Orderlist orderlist) {
		if (orderlist != null) {
			String id = orderlist.getOrdid();
			if (checkValue(id)) {
				if (orderlistDao.searchOrderlist(orderlist) != null) {
					return orderlistDao.deleteOrderlist(orderlist);
				}
			}
		}
		return -1;
	}

	// 修改
	public Integer modifyOrderlist(Orderlist orderlist) {
		if (orderlist != null) {
			Integer id = orderlist.getProID();
//			if (checkValue(id)) 
			{
				return orderlistDao.modifyOrderlist(orderlist);

			}
		}
		return -1;

	}

	@Override
	public List<OrderlistView> searchOrderlistViewByOrdid(String orderID) {
		if (checkValue(orderID)) {
			OrderlistView orderlistView = new OrderlistView();
			orderlistView.setOrdid(orderID);
			return orderlistDao.searchOrderlistView(orderlistView);
		}
		return null;
	}

}
