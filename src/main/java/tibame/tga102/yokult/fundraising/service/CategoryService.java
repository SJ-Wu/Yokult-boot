package tibame.tga102.yokult.fundraising.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.fundraising.dao.CategoryDao;
import tibame.tga102.yokult.fundraising.vo.CategoryBean;

@Service
@Transactional
public class CategoryService {
	@Autowired
	CategoryDao categoryDAO;
	
	public CategoryBean insertBean(CategoryBean categoryBean) {
		return this.categoryDAO.insert(categoryBean);
	}
	public Boolean deleteBean(String id) {
		return this.categoryDAO.delete(id);
	}
	public CategoryBean updateBean(String id, CategoryBean categoryBean) {
		return this.categoryDAO.update(id, categoryBean);
	}
	public CategoryBean selectBean(String id) {
		return this.categoryDAO.select(id);
	}
	public List<CategoryBean> selectAllBeans() {
		return this.categoryDAO.selectAll();
	}
}
