package tibame.tga102.yokult.fundraising.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.fundraising.dao.OrderDao;
import tibame.tga102.yokult.fundraising.vo.OrderBean;

@Service
@Transactional
public class OrderService {
	@Autowired
	OrderDao OrderDAO;
	
	public OrderBean insertBean(OrderBean orderBean) {
		return this.OrderDAO.insert(orderBean);
	}
	public Boolean deleteBean(int id) {
		return this.OrderDAO.delete(id);
	}
	public OrderBean updateBean(int id, OrderBean orderBean) {
		return this.OrderDAO.update(id, orderBean);
	}
	public OrderBean selectBean(int id) {
		return this.OrderDAO.select(id);
	}
	public List<OrderBean> selectAllBeans() {
		return this.OrderDAO.selectAll();
	}
	public List<OrderBean> selectMyOrderBeans(String memID) {
		return this.OrderDAO.selectByMemberID(memID);
	}
	public Integer selectLastID() {
		return this.OrderDAO.selectLastID();
	}
}
