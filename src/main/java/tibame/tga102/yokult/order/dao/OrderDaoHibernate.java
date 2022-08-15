package tibame.tga102.yokult.order.dao;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.order.vo.Order;
import tibame.tga102.yokult.orderlist.vo.Orderlist;

@Repository
public class OrderDaoHibernate implements OrderDao {
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}
	
	@Override
	public Integer insert(Order order) {
		this.getSession().save(order);
		return 1;
	}

	@Override
	public List<Order> selectAll() {
		return (List<Order>) this.getSession().createQuery("from Order", Order.class).list();
	}

	@Override
	public Integer insert(Orderlist orderlist) {
		
		return null;
	}

}
