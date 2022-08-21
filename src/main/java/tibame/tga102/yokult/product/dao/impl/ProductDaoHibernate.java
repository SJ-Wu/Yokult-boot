package tibame.tga102.yokult.product.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.product.dao.ProductDao;
import tibame.tga102.yokult.product.vo.Product;

@Repository
public class ProductDaoHibernate implements ProductDao {
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}

	@Override
	public Integer insert(Product product) {
		Integer pk = (Integer) this.getSession().save(product);
		if (pk > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	@Override
	public Integer update(Product product) {
		Product oldProduct = this.getSession().get(Product.class, product.getProID());
		System.out.println("oldProduct" + oldProduct);
		if (oldProduct != null) {
			
			oldProduct.setProName(product.getProName());
			oldProduct.setProStock(product.getProStock());
			oldProduct.setProPrice(product.getProPrice());
			oldProduct.setProSpecs(product.getProSpecs());
			oldProduct.setProBrand(product.getProBrand());
			oldProduct.setProCategory(product.getProCategory());
				return 1;
		}
		return 0;
	}

	@Override
	public List<Product> selectAll(String category, String productName) {
//		String hql2 = "from Product where category :cate and productName = :name";
		String hql = "from Product";
		if (category != null || productName != null) {
			hql += " where";
			if (category != null) {
				hql += " proCategory = :cate";
			}
			if (productName != null) {
				if (category == null) {
					hql += " proName like :name";
				} else {					
					hql += " and proName like :name";
				}
			}
		}
		System.out.println("hql= " + hql);
		Query<Product> query = this.getSession().createQuery(hql, Product.class);
		if (category != null) {
			query.setParameter("cate", category);
		}
		if (productName != null) {
			query.setParameter("name", "%" + productName + "%");
		}
		List<Product> list = query.list();

		return list;
	}
}
