package tibame.tga102.yokult.order.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tibame.tga102.yokult.order.vo.Order;
import tibame.tga102.yokult.orderlist.vo.Orderlist;


public class OrderDaoJDBC implements OrderDao{
	private DataSource dataSource;
	
	public OrderDaoJDBC() throws NamingException {
		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/Yokult");
	}
	
	final String INSERT = "insert into `order` (ordid, memid, paymethod, orderstatus, addr, receipter, shoptime, cellphone, phone) values (?,?,?,?,?,?,?,?,?);";
	@Override
	public Integer insert(Order order) {
		try(Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT);) {
			ps.setString(1, order.getOrdid());
			ps.setString(2, order.getMemid());
			ps.setString(3, order.getPaymethod());
			ps.setString(4, order.getOrderstatus());
			ps.setString(5, order.getAddr());
			ps.setString(6, order.getReceipter());
			ps.setTimestamp(7, order.getShoptime());
			ps.setString(8, order.getCellphone());
			ps.setString(9, order.getPhone());
			int rowCount = ps.executeUpdate();
			System.out.println("[INSERT] " + rowCount + " order");
			return rowCount;
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("[INSERT] Fail");
		return -1;
	}
	
	final String SELECTALL = "SELECT ordid, memid, paymethod, orderstatus, addr, receipter, shoptime, cellphone, phone FROM yokult.order;";
	@Override
	public List<Order> selectAll() {
		try(Connection conn = dataSource.getConnection();
				PreparedStatement ps = conn.prepareStatement(SELECTALL);) {
			try (ResultSet rs = ps.executeQuery()) {
				List<Order> orders = new ArrayList<Order>();
				System.out.println("[SELECTALL] Show orders:");
				while(rs.next()) {
					Order o = new Order();
					o.setOrdid(rs.getString("ordid"));
					o.setMemid(rs.getString("memid"));
					o.setPaymethod(rs.getString("paymethod"));
					o.setOrderstatus(rs.getString("orderstatus"));
					o.setAddr(rs.getString("addr"));
					o.setReceipter(rs.getString("receipter"));
					o.setShoptime(rs.getTimestamp("shoptime"));
					o.setCellphone(rs.getString("cellphone"));
					o.setPhone(rs.getString("phone"));					
					orders.add(o);
					System.out.println(o);
				}
				return orders;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("[SELECTALL] GetNothing");
		return null;
	}
	
	final String INSERTorderid = "insert into `orderlist` (ordid FROM yokult.orderlist)";
	@Override
	public Integer insert(Orderlist orderlist) {
		try(Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERTorderid);) {
			ps.setString(1, orderlist.getOrdid());
			int rowCount = ps.executeUpdate();
			System.out.println("[INSERT] " + rowCount + " ordid");
			return rowCount;
		} catch(Exception e) {
			e.printStackTrace();
		}
		System.out.println("[INSERT] Fail");
		return -1;
	} 

}