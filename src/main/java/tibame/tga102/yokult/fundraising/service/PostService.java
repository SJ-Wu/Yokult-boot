package tibame.tga102.yokult.fundraising.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.fundraising.dao.PostDao;
import tibame.tga102.yokult.fundraising.vo.PostBean;

@Service
@Transactional
public class PostService {
	@Autowired
	PostDao postDAO;

	public PostBean insertBean(PostBean postBean) {
		return this.postDAO.insert(postBean);
	}

	public Boolean deleteBean(Integer id) {
		return this.postDAO.delete(id);
	}

	public PostBean updateBean(Integer id, PostBean postBean) {
		return this.postDAO.update(id, postBean);
	}

	public List<PostBean> selectAllBeansByMemberID(String memID) {
		return this.postDAO.selectAllBeansByMemberID(memID);
	}

	public List<PostBean> selectAllBeans() {
		return this.postDAO.selectAll();
	}
}
