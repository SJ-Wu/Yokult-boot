package tibame.tga102.yokult.fundraising;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import tibame.tga102.yokult.fundraising.service.CategoryService;
import tibame.tga102.yokult.fundraising.service.OrderService;
import tibame.tga102.yokult.fundraising.service.PlanService;
import tibame.tga102.yokult.fundraising.service.PostNumberService;
import tibame.tga102.yokult.fundraising.service.PostService;
import tibame.tga102.yokult.fundraising.service.ProposalService;
import tibame.tga102.yokult.fundraising.vo.CategoryBean;
import tibame.tga102.yokult.fundraising.vo.OrderBean;
import tibame.tga102.yokult.fundraising.vo.PlanBean;
import tibame.tga102.yokult.fundraising.vo.PostBean;
import tibame.tga102.yokult.fundraising.vo.PostNumberBean;
import tibame.tga102.yokult.fundraising.vo.ProposalBean;

@SpringBootTest
public class CategoryServiceTests {
	

	@Autowired
	private ProposalService proposalService;

	@Test
	public void proposalSelectAll() {
		System.out.println("========TestListAll Proposal========");
		List<ProposalBean> list = proposalService.selectAllBeans();
		for (ProposalBean m:list) {
			System.out.println(m);
		}
	}
	
	@Autowired
	private CategoryService categoryService;

	@Test
	public void categorySelectAll() {
		System.out.println("========TestListAll Category========");
		List<CategoryBean> list = categoryService.selectAllBeans();
		for (CategoryBean m:list) {
			System.out.println(m);
		}
	}
	
	@Autowired
	private OrderService orderService;

	@Test
	public void orderSelectAll() {
		System.out.println("========TestListAll Order========");
		List<OrderBean> list = orderService.selectAllBeans();
		for (OrderBean m:list) {
			System.out.println(m);
		}
	}
	
	@Autowired
	private PlanService planService;

	@Test
	public void planSelectAll() {
		System.out.println("========TestListAll Plan========");
		List<PlanBean> list = planService.selectAllBeans();
		for (PlanBean m:list) {
			System.out.println(m);
		}
	}
	
	
	@Autowired
	private PostService postService;

	@Test
	public void postSelectAll() {
		System.out.println("========TestListAll Post========");
		List<PostBean> list = postService.selectAllBeans();
		for (PostBean m:list) {
			System.out.println(m);
		}
	}
	
	
	@Autowired
	private PostNumberService postNumberService;

	@Test
	public void postNumberSelectAll() {
		System.out.println("========TestListAll PostNumber========");
		List<PostNumberBean> list = postNumberService.selectAllBeans();
		for (PostNumberBean m:list) {
			System.out.println(m);
		}
	}
	

}
