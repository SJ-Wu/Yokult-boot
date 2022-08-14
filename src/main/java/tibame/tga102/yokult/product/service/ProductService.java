package tibame.tga102.yokult.product.service;

import java.util.List;

import tibame.tga102.yokult.product.vo.Product;



public interface ProductService {

	List<Product>  getAll(String category, String productName);
	
	void save(Product product);
	
	Integer add(Product product);
}