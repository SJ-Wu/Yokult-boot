package tibame.tga102.yokult.fundraising.dao;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.fundraising.vo.PostBean;

@Repository
public class PostDaoSpringmvc implements PostDao {
//  取得目前session參數，在離開servlet前都沒有session關閉的問題
	@PersistenceContext
	private Session session;
	
	public PostDaoSpringmvc() {}
	
	@Override
	public PostBean insert(PostBean postBean) {
		if(postBean != null && postBean.getPostID() == null) {			
			this.session.save(postBean);
			return postBean;				
		}
		return null;
	}

	@Override
	public Boolean delete(Integer id) {
		if(id != null && id >0) {
			PostBean delete = this.session.get(PostBean.class, id);
			if(delete != null) {
				this.session.delete(delete);
				return true;
			}
		}
		return null;
	}

	@Override
	public PostBean update(Integer id, PostBean postBean) {
		if(id != null && id > 0 && postBean != null && postBean.getPostID() == null) {			
			PostBean update = this.session.get(PostBean.class, id);
			if(update != null) {
				update.setPostFisrtName(postBean.getPostFisrtName());
				update.setPostLastName(postBean.getPostLastName());
				update.setPostCellphone(postBean.getPostCellphone());
				update.setPost_SID(postBean.getPost_SID());
				update.setPostAddress(postBean.getPostAddress());
				update.setMemID(postBean.getMemID());
				update.setPostNickName(postBean.getPostNickName());
				update.setPostCity(postBean.getPostCity());
				update.setPostArea(postBean.getPostArea());
				update.setPostNumber(postBean.getPostNumber());
				
				this.session.save(update);
				return update;				
			}
		}
		return null;
	}
	@Override
	public List<PostBean> selectAllBeansByMemberID(String memID) {
		Query<PostBean> qurey = this.session.createQuery("from PostBean where memID = \'" + memID + "\'", PostBean.class);
		List<PostBean> result = qurey.list();
		return result;
	}

	@Override
	public List<PostBean> selectAll() {
		Query<PostBean> qurey = this.session.createQuery("from PostBean", PostBean.class);
		List<PostBean> result = qurey.list();
		return result;
	}
	
}
