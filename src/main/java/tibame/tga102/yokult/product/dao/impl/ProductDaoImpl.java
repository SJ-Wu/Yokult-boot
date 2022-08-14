package tibame.tga102.yokult.product.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import tibame.tga102.yokult.product.dao.ProductDao;
import tibame.tga102.yokult.product.vo.Product;



public class ProductDaoImpl implements ProductDao {

	private DataSource datasource;

	public ProductDaoImpl() throws NamingException {
		datasource = (DataSource) new InitialContext().lookup("java:comp/env/jdbc/Yokult");
	}

//	final String SELECTALL = "Select PROID,PRONAME,PROSTOCK,PROPRICE,PROSPECS,PROBRAND,PROPICTURE,PROCATEGORY from PRODUCT";
	final String SELECTALL = "Select * from product";

	public List<Product> selectAll(String category, String productName) {

		try {
			Connection conn = datasource.getConnection();

			String query = SELECTALL;
			if (category != null || productName != null) {
				query += " Where ";
			}

			if (category != null) {
				query += " procategory = '" + category + "'";
			}

			if (productName != null) {
				if (category != null) {
					query += " AND ";
				}
				query += "  proname like '%" + productName + "%'";
			}
			
			query += " ORDER BY proid DESC";

			System.out.println(query);
			PreparedStatement ps = conn.prepareStatement(query);
			try (ResultSet rs = ps.executeQuery()) {
				List<Product> products = new ArrayList<Product>();
				System.out.println("Show product list:");
				while (rs.next()) {
					Product p = new Product();
					p.setProID(rs.getInt("proid"));
					p.setProName(rs.getString("proname"));
					p.setProStock(rs.getInt("prostock"));
					p.setProPrice(rs.getInt("proprice"));
					p.setProSpecs(rs.getString("prospecs"));
					p.setProBrand(rs.getString("probrand"));
					p.setProPicture(rs.getString("propicture"));
					p.setProCategory(rs.getString("procategory"));
					products.add(p);
					System.out.println(p);
				}
				return products;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
@Override
	public Integer insert(Product product) {
		String insertStr = "INSERT INTO `product` (`proname`, `prostock`, `proprice`, `prospecs`, `probrand`, `propicture`, `procategory`) VALUES (?,?,?,?,?,?,?);";
		try {
			Connection conn = datasource.getConnection();
			PreparedStatement ps = conn.prepareStatement(insertStr);
			ps.setString(1, product.getProName());
			ps.setInt(2, product.getProStock());
			ps.setInt(3, product.getProPrice());
			ps.setString(4, product.getProSpecs());
			ps.setString(5, product.getProBrand());
			ps.setString(6, product.getProPicture());
			ps.setString(7, product.getProCategory());
			return ps.executeUpdate();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	} 

	@Override
	public Integer update(Product product) {
		String updateStr = """
					UPDATE product SET
						proname = ?,
						prostock = ?,
						proprice = ?,
						prospecs = ?,
						probrand = ?,
						procategory = ?
					WHERE proid = ?
				""";
		try {
			Connection conn = datasource.getConnection();
			PreparedStatement ps = conn.prepareStatement(updateStr);
			ps.setString(1, product.getProName());
			ps.setInt(2, product.getProStock());
			ps.setInt(3, product.getProPrice());
			ps.setString(4, product.getProSpecs());
			ps.setString(5, product.getProBrand());
			ps.setString(6, product.getProCategory());
			ps.setInt(7, product.getProID());
			
			return ps.executeUpdate();
		
		} catch (Exception e) {
			System.out.println(e);
		}
		return 0;
	}

//	public Integer delete(Product product) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//	@Override
//	public Integer insert(ProductDao product) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public ProductDao selectByProductIdAndProduct(ProductDaoImpl product) {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public Integer delete(ProductDao product) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
