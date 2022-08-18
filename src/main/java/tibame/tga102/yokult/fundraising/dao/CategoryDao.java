package tibame.tga102.yokult.fundraising.dao;

import java.util.List;

import tibame.tga102.yokult.fundraising.vo.CategoryBean;

public interface CategoryDao {
	public CategoryBean insert(CategoryBean categoryBean);
	public Boolean delete(String id);
	public CategoryBean update(String id, CategoryBean categoryBean);
	public CategoryBean select(String id);
	public List<CategoryBean> selectAll();
}
