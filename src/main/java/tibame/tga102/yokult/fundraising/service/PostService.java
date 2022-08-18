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
	@Autowired
	PostNumberService postNumberService;

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
		List<PostBean> list_postBean = this.postDAO.selectAllBeansByMemberID(memID);
		for(PostBean postBean : list_postBean) {
			postBean.renew(postNumberService);
		}
		return list_postBean;
	}

	public List<PostBean> selectAllBeans() {
		return this.postDAO.selectAll();
	}
}
