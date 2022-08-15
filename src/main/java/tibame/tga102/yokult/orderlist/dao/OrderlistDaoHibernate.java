package tibame.tga102.yokult.orderlist.dao;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

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
		Orderlist search = (Orderlist)this.getSession().get(Orderlist.class, orderlist.getOrderlistid());
		if (search != null) {
			search.setOrderlistid(orderlist.getOrderlistid());
			search.setProID(orderlist.getProID());
			search.setProprice(orderlist.getProPrice());
			search.setQuantity(orderlist.getQuantity());
			search.setOrdid(orderlist.getOrdid());
		}
		return null;
	}

	@Override
	public List<OrderlistView> searchOrderlistView(OrderlistView orderlist) {
		Orderlist search = (Orderlist)this.getSession().get(Orderlist.class, orderlist.getOrderlistid());
		if (search != null) {
			search.setOrderlistid(orderlist.getOrderlistid());
			search.setproID(orderlist.getproid);
			search.setProprice(orderlist.getProprice());
			search.setQuantity(orderlist.getQuantity());
			search.setOrdid(orderlist.getOrdid());
		}
		return null;
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
