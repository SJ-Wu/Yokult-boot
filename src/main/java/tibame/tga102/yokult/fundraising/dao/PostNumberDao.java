package tibame.tga102.yokult.fundraising.dao;

import java.util.List;

import tibame.tga102.yokult.fundraising.vo.PostNumberBean;

public interface PostNumberDao {
	public abstract PostNumberBean select(Integer post_SID);
	public abstract List<PostNumberBean> selectAll();
	public abstract PostNumberBean selectSID(String postCity , String postArea);
}
