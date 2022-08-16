package tibame.tga102.yokult.productServiceImpl;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tibame.tga102.yokult.product.service.impl.ProductServiceImpl;
import tibame.tga102.yokult.product.vo.Product;

@SpringBootTest
public class ProductServiceImplTest {
	@Test
	void contextLoads() {
		
	}
	
	@Autowired
	private ProductServiceImpl productServiceImpl;
	
	@Test
	public void getAllTest() {
		List<Product> list = productServiceImpl.getAll("清潔相關", "");
		System.out.println(list);
	}
	
}
