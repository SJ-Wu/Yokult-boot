package tibame.tga102.yokult.product.dao;

import java.util.List;

import tibame.tga102.yokult.product.dao.impl.ProductDaoImpl;
import tibame.tga102.yokult.product.vo.Product;

public interface ProductDao {
	Integer insert(Product product);

//	ProductDao selectByProductIdAndProduct(ProductDaoImpl product);

	Integer update(tibame.tga102.yokult.product.vo.Product product);

//	Integer delete(ProductDao product);

	List<Product> selectAll(String category, String productName);

//	Integer insert(Product product);

}
