package tibame.tga102.yokult.product.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.product.dao.ProductDao;
import tibame.tga102.yokult.product.vo.Product;

@Repository
public class ProductDaoHibernate implements ProductDao{
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}
	
	@Override
	public Integer insert(Product product) {
		Integer pk = (Integer)this.getSession().save(product);
		if(pk > 0) {
			return 1;
		} else {
			return -1;
		}
	}
	
//	@Override
//	public Integer insert(Product product) {
//		Product temp = (Product)this.getSession().get(Product.class, product.getProID());
//		if (temp == null) {
//			this.getSession().save(product);
//			return 1;
//		}
//		return -1;
//	}


//	@Override
//	public ProductDao selectByProductIdAndProduct(ProductDaoImpl product) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public Integer update(Product product) {
//		String updateStr = """
//				UPDATE product SET
//					proname = ?,
//					prostock = ?,
//					proprice = ?,
//					prospecs = ?,
//					probrand = ?,
//					procategory = ?
//				WHERE proid = ?
//			""";
		Product oldProduct = this.getSession().get(Product.class, product.getProID());
		System.out.println("oldProduct" + oldProduct);
		if(oldProduct != null) {
//			oldProduct.setProBrand(product.getProBrand());
//			oldProduct.setProCategory(product.getProCategory());
//			oldProduct.setProName(product.getProName());
//			oldProduct.setProPicture(product.getProPicture());
//			oldProduct.setProPrice(product.getProPrice());
//			oldProduct.setProSpecs(product.getProSpecs());
//			oldProduct.setProStock(product.getProStock());
			Object obj = this.getSession().merge(product);
			if(obj != null) {
				return 1;
			}
		}
		
		return 0;
	}

//	@Override
//	public Integer delete(ProductDao product) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@Override
	public List<Product> selectAll(String category, String productName) {
//		String hql2 = "from Product where category :cate and productName = :name";
		String hql = "from Product";
		if(category != null || productName != null) {
			hql += " where";
			if(category != null) {
				hql += " procategory = :cate";
			}
			if(productName != null) {
				if(category == null) {
					hql += " proname like :name";
				}
				hql += " and proname = :name";
			}
		}
		System.out.println("hql= " +hql);
		Query<Product> query = this.getSession().createQuery(hql, Product.class);
		if (category != null) {
			query.setParameter("cate", category);
		}
		if (productName != null) {
			query.setParameter("name", "%"+ productName + "%");
		}
		List<Product> list = query.list();
		
		return list;
	}

//	@Override
//	public Integer insert(ProductDao product) {
//		// TODO Auto-generated method stub
//		return null;
//	}

}
