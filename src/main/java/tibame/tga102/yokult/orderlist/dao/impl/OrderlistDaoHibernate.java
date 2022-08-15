package tibame.tga102.yokult.orderlist.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.orderlist.dao.OrderlistDao;
import tibame.tga102.yokult.orderlist.vo.Orderlist;
import tibame.tga102.yokult.orderlist.vo.OrderlistView;

@Repository
public class OrderlistDaoHibernate implements OrderlistDao{
	@PersistenceContext
	private Session session;
	
	public Session getSession() {
		return this.session;
	}
	
	@Override
	public List<Orderlist> searchOrderlist(Orderlist orderlist) {
		return this.getSession().createQuery("from Orderlist where ordid = :searchid", Orderlist.class)
				.setParameter("searchid", orderlist.getOrdid())
				.list();
	}

	@Override
	public List<OrderlistView> searchOrderlistView(OrderlistView orderlist) {
		return this.getSession().createQuery("from OrderlistView where ordid = :searchid", OrderlistView.class)
				.setParameter("searchid", orderlist.getOrdid())
				.list();
	}

	@Override
	public Integer insertOrderlist(Orderlist orderlist) {
		this.getSession().save(orderlist);
		return 1;
	}

	@Override
	public Integer deleteOrderlist(Orderlist orderlist) {
		Orderlist delete = (Orderlist)this.getSession().get(Orderlist.class, orderlist.getOrdid());
		if (delete != null) {
			this.getSession().delete(delete);
			return 1;
		}
		return null;
	}

	@Override
	public Integer modifyOrderlist(Orderlist orderlist) {
		return null;
	}
	
	@Override
	public List<Orderlist> selectAll() {
		return (List<Orderlist>) this.getSession().createQuery("from Orderlist", Orderlist.class).list();
	}
}
