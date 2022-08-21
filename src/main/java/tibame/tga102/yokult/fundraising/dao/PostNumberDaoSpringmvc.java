package tibame.tga102.yokult.fundraising.dao;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.fundraising.vo.PostNumberBean;

@Repository
public class PostNumberDaoSpringmvc implements PostNumberDao {
//  取得目前session參數，在離開servlet前都沒有session關閉的問題
	@PersistenceContext
	private Session session;
	
	public PostNumberDaoSpringmvc() {}
	
	@Override
	public PostNumberBean select(Integer post_SID) {
		if(post_SID != null && post_SID != 0) {
			return this.session.get(PostNumberBean.class, post_SID);
		}
		return null;
	}

	@Override
	public List<PostNumberBean> selectAll() {
		Query<PostNumberBean> qurey = this.session.createQuery("from PostNumberBean", PostNumberBean.class);
		List<PostNumberBean> result = qurey.list();
		return result;
	}
	

	@Override
	public PostNumberBean selectSID(String postCity , String postArea) {
		Query<PostNumberBean> qurey = this.session.createQuery("from PostNumberBean where postCity = \'" + postCity + "\' and postArea = \'" + postArea + "\'", PostNumberBean.class);
		PostNumberBean result = qurey.getSingleResult();
		return result;
	}
	
}
