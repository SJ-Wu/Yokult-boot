package tibame.tga102.yokult.orderlist.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tibame.tga102.yokult.orderlist.dao.OrderlistDao;
import tibame.tga102.yokult.orderlist.vo.Orderlist;
import tibame.tga102.yokult.orderlist.vo.OrderlistView;

public class OrderlistDaoJDBC implements OrderlistDao {
	private DataSource dataSource;

	public OrderlistDaoJDBC() throws NamingException {
		dataSource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/Yokult");
	}

	// 查询
	final String SELECT = "Select orderlistid, proid, proprice, quantity, ordid from orderlist where ordid = ?";

	@Override
	public List<Orderlist> searchOrderlist(Orderlist orderlist) {
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SELECT);) {
			pstmt.setString(1, orderlist.getOrdid());
			try (ResultSet rs = pstmt.executeQuery()) {
				List<Orderlist> orderlists = new ArrayList<Orderlist>();
				System.out.println("[Orderlist] Selected by orderid:" + orderlist.getOrdid());
				while (rs.next()) {
					Orderlist resultOrderlist = new Orderlist();
					resultOrderlist.setOrderlistid(rs.getInt("orderlistid"));
					resultOrderlist.setProID(rs.getInt("proid"));
					resultOrderlist.setProprice(rs.getInt("proprice"));
					resultOrderlist.setQuantity(rs.getInt("quantity"));
					resultOrderlist.setOrdid(rs.getString("ordid"));
					orderlists.add(resultOrderlist);
				}
				return orderlists;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("[Orderlist] Selected by orderid: " + orderlist.getOrdid() + " fail.");
		return null;
	}

	// 新增
	final String INSERT = "insert into orderlist (proid, proprice, quantity, ordid) values (?, ?, ?, ?);";

	public Integer insertOrderlist(Orderlist orderlist) {
		try (Connection conn = dataSource.getConnection(); PreparedStatement ps = conn.prepareStatement(INSERT);) {
			ps.setInt(1, orderlist.getProID());
			ps.setInt(2, orderlist.getProPrice());
			ps.setInt(3, orderlist.getQuantity());
			ps.setString(4, orderlist.getOrdid());
			int rowCount = ps.executeUpdate();
			System.out.println("[Orderlist] Insert " + rowCount + " orderlist.");
			return rowCount;
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("[Orderlist] Insert orderlist fail");
		return -1;
	}

	@Override
	public Integer deleteOrderlist(Orderlist orderlist) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer modifyOrderlist(Orderlist orderlist) {
		// TODO Auto-generated method stub
		return null;
	}

	final String SELECT_VIEW = "SELECT * FROM v_admin_orderlist where ordid = ?;";
	@Override
	public List<OrderlistView> searchOrderlistView(OrderlistView orderlist) {
		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(SELECT_VIEW);) {
			pstmt.setString(1, orderlist.getOrdid());
			try (ResultSet rs = pstmt.executeQuery()) {
				List<OrderlistView> orderlists = new ArrayList<OrderlistView>();
				System.out.println("[OrderlistView] Selected by orderid:" + orderlist.getOrdid());
				while (rs.next()) {
					OrderlistView resultOrderlist = new OrderlistView();
					resultOrderlist.setOrderlistid(rs.getInt("orderlistid"));
					resultOrderlist.setQuantity(rs.getInt("quantity"));
					resultOrderlist.setOrdid(rs.getString("ordid"));
					resultOrderlist.setProName(rs.getString("proname"));
					orderlists.add(resultOrderlist);
				}
				return orderlists;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("[OrderlistView] Selected by orderid: " + orderlist.getOrdid() + " fail.");
		return null;
	}

	@Override
	public List<Orderlist> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

// TODO: Not implement features
//	// 删除
//	final String DELETE = "delete from OrderList where Ord_ID = ?;";
//
//	public Integer deleteOrderlist(Orderlist orderlist) {
//		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(DELETE);) {
//			pstmt.setString(1, orderlist.getOrdID());
//			int rowCount = pstmt.executeUpdate();
//			System.out.println(rowCount + " row(s) deleted!!");
//			return rowCount;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return 0;
//	}
//
//	// 修改
//	final String UPDATE = "update OrderList set OrderListNumber = ?, Ord_ProductID = ?, Ord_Unitprice = ?, Ord_Quantity = ? where Ord_ID = ?;";
//
//	@Override
//	public Integer modifyOrderlist(Orderlist orderlist) {
//		try (Connection conn = dataSource.getConnection(); PreparedStatement pstmt = conn.prepareStatement(UPDATE);) {
//			pstmt.setString(1, orderlist.getOderListnumber());
//			pstmt.setString(2, orderlist.getOderProductid());
//			pstmt.setInt(3, orderlist.getOderUnitprice());
//			pstmt.setInt(4, orderlist.getOderQuantity());
//			pstmt.setString(5, orderlist.getOrdID());
//			int rowCount = pstmt.executeUpdate();
//			System.out.println(rowCount + " row(s) updated!!");
//			return rowCount;
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return -1;
//	}


}
