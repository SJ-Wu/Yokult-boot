package tibame.tga102.yokult.fundraising.dao;

import java.util.List;


import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.fundraising.vo.OrderBean;

@Repository
public class OrderDaoSpringmvc implements OrderDao {
//  取得目前session參數，在離開servlet前都沒有session關閉的問題
	@PersistenceContext
	private Session session;
	
	public OrderDaoSpringmvc() {}

	@Override
	public OrderBean insert(OrderBean orderBean) {
		if (orderBean != null && orderBean.getOrderID() == null) {
			this.session.save(orderBean);
			return orderBean;
		}
		return null;
	}

	@Override
	public Boolean delete(Integer id) {
		if (id != null && id > 0) {
			OrderBean delete = this.session.get(OrderBean.class, id);
			if (delete != null) {
				this.session.delete(delete);
				return true;
			}
		}
		return null;
	}

	@Override
	public OrderBean update(Integer id, OrderBean orderBean) {
		if (id != null && id > 0 && orderBean != null && orderBean.getOrderID() == null) {
			OrderBean update = this.session.get(OrderBean.class, id);
			if (update != null) {
				this.session.save(update);
				return update;
			}
		}
		return null;
	}

	@Override
	public OrderBean select(Integer id) {
		if (id != null && id > 0) {
			return this.session.get(OrderBean.class, id);
		}
		return null;
	}

	@Override
	public List<OrderBean> selectAll() {
		Query<OrderBean> qurey = this.session.createQuery("from OrderBean", OrderBean.class);
		List<OrderBean> result = qurey.list();
		return result;
	}

	@Override
	public List<OrderBean> selectByMemberID(String memID) {
		Query<OrderBean> qurey = this.session.createQuery("from OrderBean where memID = \'" + memID + "\'", OrderBean.class);
		List<OrderBean> result = qurey.list();
		return result;
	}

	public Integer selectLastID() {
		OrderBean lastRow = (OrderBean) this.session
				.createQuery("from OrderBean order by orderID desc", OrderBean.class).setMaxResults(1).uniqueResult();
		return lastRow.getOrderID();
	}
}
