package tibame.tga102.yokult.product.service.impl;

import java.util.List;

import javax.naming.NamingException;

import tibame.tga102.yokult.product.dao.ProductDao;
import tibame.tga102.yokult.product.dao.impl.ProductDaoImpl;
import tibame.tga102.yokult.product.service.ProductService;
import tibame.tga102.yokult.product.vo.Product;



public class ProductServiceImpl implements ProductService {
	private ProductDao dao;

	public ProductServiceImpl() throws NamingException {
		dao = new ProductDaoImpl();
	}

	@Override
	public List<Product> getAll(String category, String productName) {
		return dao.selectAll(category,productName);
	}

	@Override
	public void save(Product product) {
		dao.update(product);
	}

	
}
