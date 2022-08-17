package tibame.tga102.yokult.orderlist.dao.impl;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.orderlist.dao.OrderlistDao;
import tibame.tga102.yokult.orderlist.vo.Orderlist;
import tibame.tga102.yokult.orderlist.vo.OrderlistView;

@Repository
public class OrderlistDaoHibernate implements OrderlistDao {
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}

	@Override
	public List<Orderlist> searchOrderlist(Orderlist orderlist) {
		return this.getSession().createQuery("from Orderlist where ordid = :searchid", Orderlist.class)
				.setParameter("searchid", orderlist.getOrdid()).list();
	}

	@Override
	public List<Map> searchOrderlistTest(String ordId) {
//		final String SELECTORDLIST = "select ordid, orderlistid, proname, quantity from orderlist o join product p on o.proid = p.proid where o.ordid = '"
//				+ orderlist.getOrdid() + "'";
		String hql = "select new map (o.ordid as ordid, o.orderlistid as orderlistid , p.proName as proName, o.quantity as quantity) from Orderlist o , Product p where o.proID = p.proID and o.ordid = :id";
		
		List<Map> listObj = this.getSession().createQuery(hql, Map.class).setParameter("id", ordId)
		.list();
		
//		System.out.println("listObj Vicky" + listObj);
		
//		System.out.println(SELECTORDLIST);
//		List<?> list = this.getSession().createSQLQuery(SELECTORDLIST)
//				.list();
		return listObj;
	}

	@Override
	public List<OrderlistView> searchOrderlistView(OrderlistView orderlist) {
		List<OrderlistView> xx = this.getSession().createQuery("from OrderlistView where ordid = :searchid", OrderlistView.class)
				.setParameter("searchid", orderlist.getOrdid()).list();
		System.out.println("-------------------");
		System.out.println(xx);
		System.out.println("-------------------");
		return xx;
	}

	@Override
	public Integer insertOrderlist(Orderlist orderlist) {
		System.out.println("inserorderlist: " + orderlist);
		this.getSession().save(orderlist);
		return 1;
	}

	@Override
	public Integer deleteOrderlist(Orderlist orderlist) {
		Orderlist delete = (Orderlist) this.getSession().get(Orderlist.class, orderlist.getOrdid());
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
