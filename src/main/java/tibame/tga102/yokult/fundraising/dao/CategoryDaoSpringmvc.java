package tibame.tga102.yokult.fundraising.dao;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.fundraising.vo.CategoryBean;

@Repository
public class CategoryDaoSpringmvc implements CategoryDao {
//    取得目前session參數，在離開servlet前都沒有session關閉的問題
	@PersistenceContext
	private Session session;
	
//	public CategoryDaoSpringmvc() {}

	@Override
	public CategoryBean insert(CategoryBean categoryBean) {
		if(categoryBean != null && categoryBean.getCategoryID() == null) {			
			this.session.save(categoryBean);
			return categoryBean;				
		}
		return null;
	}

	@Override
	public Boolean delete(String id) {
		if(id != null && id.trim() != "" && id.trim().length() <= 2) {
			CategoryBean delete = this.session.get(CategoryBean.class, id);
			if(delete != null) {
				this.session.delete(delete);
				return true;
			}
		}
		return null;
	}

	@Override
	public CategoryBean update(String id, CategoryBean categoryBean) {
		if(id != null && id.trim() != "" && id.trim().length() <= 2 && categoryBean != null && categoryBean.getCategoryID() == null) {			
			CategoryBean update = this.session.get(CategoryBean.class, id);
			if(update != null) {
				this.session.save(update);
				return update;				
			}
		}
		return null;
	}

	@Override
	public CategoryBean select(String id) {
		if(id != null && id.trim() != "" && id.trim().length() <= 2) {
			return this.session.get(CategoryBean.class, id);
		}
		return null;
	}

	@Override
	public List<CategoryBean> selectAll() {
		Query<CategoryBean> qurey = this.session.createQuery("from CategoryBean", CategoryBean.class);
		List<CategoryBean> result = qurey.list();
		return result;
	}
	
}
