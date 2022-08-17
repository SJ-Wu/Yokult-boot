package tibame.tga102.yokult.orderlist.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tibame.tga102.yokult.orderlist.dao.OrderlistDao;
import tibame.tga102.yokult.orderlist.service.OrderlistService;
import tibame.tga102.yokult.orderlist.vo.Orderlist;
import tibame.tga102.yokult.orderlist.vo.OrderlistView;
import tibame.tga102.yokult.product.dao.impl.ProductDaoHibernate;

@Service
@Transactional
public class OrderlistServiceImpl implements OrderlistService {

	@Autowired
	private OrderlistDao orderlistDao; // 把 dao 變成一個屬性 
	
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
//		if (orderlist != null) {
//			Integer id = orderlist.getProID();
//			if (checkValue(id)) 
//			{
//				return orderlistDao.modifyOrderlist(orderlist);
//
//			}
//		}
		return -1;

	}

	@Override
	public List<Map> searchOrderlistViewByOrdid(String orderID) {
		if (checkValue(orderID)) {
			OrderlistView orderlistView = new OrderlistView();
			orderlistView.setOrdid(orderID);
			return orderlistDao.searchOrderlistTest(orderID);
		}
		return null;
	}

}
