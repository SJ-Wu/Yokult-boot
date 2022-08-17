package tibame.tga102.yokult.product.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.product.dao.ProductDao;
import tibame.tga102.yokult.product.service.ProductService;
import tibame.tga102.yokult.product.vo.Product;


@Service
@Transactional
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao dao;

	public ProductServiceImpl() {
	}

	@Override
	public List<Product> getAll(String category, String productName) {
		return dao.selectAll(category,productName);
	}

	@Override
	public void save(Product product) {
		dao.update(product);
	}

	@Override
	public Integer add(Product product) {
		return dao.insert(product);
	}
}
