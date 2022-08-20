package tibame.tga102.yokult.fundraising.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.fundraising.dao.PostNumberDao;
import tibame.tga102.yokult.fundraising.vo.PostNumberBean;

@Service
@Transactional
public class PostNumberService {
	@Autowired
	PostNumberDao postnumberDAO;;
	
	public PostNumberBean selectBean(Integer post_SID) {
		return this.postnumberDAO.select(post_SID);
	}
	public List<PostNumberBean> selectAllBeans() {
		return this.postnumberDAO.selectAll();
	}
	public PostNumberBean selectBeanByCityArea(String postCity , String postArea) {
		return this.postnumberDAO.selectSID(postCity , postArea);
	}
}
