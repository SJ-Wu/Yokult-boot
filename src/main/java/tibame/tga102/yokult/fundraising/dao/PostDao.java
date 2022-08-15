package tibame.tga102.yokult.fundraising.dao;

import java.util.List;

import tibame.tga102.yokult.fundraising.vo.PostBean;

public interface PostDao {
	public abstract PostBean insert(PostBean postBean);
	public abstract Boolean delete(Integer id);
	public abstract PostBean update(Integer id, PostBean postBean);
	public List<PostBean> selectAllBeansByMemberID(String memID);
	public abstract List<PostBean> selectAll();
}
