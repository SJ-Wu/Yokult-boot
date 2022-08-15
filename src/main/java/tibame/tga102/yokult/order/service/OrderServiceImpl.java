package tibame.tga102.yokult.order.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.naming.NamingException;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ecpay.payment.integration.AllInOne;
import ecpay.payment.integration.domain.AioCheckOutALL;
import tibame.tga102.yokult.order.dao.OrderDao;
import tibame.tga102.yokult.order.dao.OrderDaoJDBC;
import tibame.tga102.yokult.order.vo.Order;
import tibame.tga102.yokult.util.YokultConstants;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderDao orderDao;

	// 回傳全部
	@Override
	public List<Order> searchOrders() {
		return orderDao.selectAll();
	}

	// 回傳指定編號的訂單
	@Override
	public Order selectOrderid(String orderid) {
		List<Order> listall = orderDao.selectAll();
		for (Order order : listall) {
			if (orderid.equals(order.getOrdid())) {
				return order;
			}
		}
		return null;
	}

	// 回傳下拉選單類別查詢
	@Override
	public List<Order> selectOrderStatus(String orderStatus) {
		List<Order> listall = orderDao.selectAll();
		List<Order> listStatus = new ArrayList<Order>();
		for (Order order : listall) {
			if (orderStatus.equals(order.getOrderstatus())) {
				listStatus.add(order);
			}
		}
		if (listStatus.size() > 0) {
			return listStatus;
		}
		return null;
	}

	@Override
	public String addOrder(Order order) {
		order.setOrdid(genOrderid(order.getMemid()));
		order.setOrderstatus("processing");
		order.setShoptime(new java.sql.Timestamp(new Date().getTime()));
		if (orderDao.insert(order) > 0) {
			return "Success";
		}
		return "Fail";
	}

	private String genOrderid(String memid) {
		StringBuilder orderid = new StringBuilder();
		SimpleDateFormat sDateFormat = new SimpleDateFormat("yyMMddHHmmss");
		orderid.append(memid);
		orderid.append("_");
		orderid.append(sDateFormat.format(new Date()));
		return orderid.toString();
	}
	

	@Override
	public String ecpayValidation(List<String> proname, Order order, String totalCount) {

		Optional<String> reduce = proname.stream().reduce((String acc, String curr) -> {
			return acc + "#" + curr; //回傳累加 用#來隔開
		});
		String itemName = reduce.get();

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"); //綠界規定日期模式
		String orderDate = sdf.format(new Date());
		AllInOne allInOne = new AllInOne("");
		AioCheckOutALL aioCheckOutALL = new AioCheckOutALL();
		aioCheckOutALL.setMerchantTradeNo(order.getOrdid().replaceAll("_", "")); //除掉資料庫裡面的底線   //訂單編號必須數字+英文字母 
		System.out.println("OrderID: "+ order.getOrdid());
		aioCheckOutALL.setMerchantTradeDate(orderDate);
		aioCheckOutALL.setTotalAmount(totalCount); //加總
		aioCheckOutALL.setTradeDesc("test"); //訂單描述
		aioCheckOutALL.setItemName(itemName); //商品名稱
		aioCheckOutALL.setClientBackURL(YokultConstants.CLIENT_BACK_URL); //按個按鈕返回商城
		aioCheckOutALL.setReturnURL(YokultConstants.RETURN_URL); //結束後回到自動商城
		aioCheckOutALL.setNeedExtraPaidInfo("N");

		return allInOne.aioCheckOut(aioCheckOutALL, null);

	}
	
	
}
