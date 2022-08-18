package tibame.tga102.yokult.fundraising.dao;

import java.util.List;

import tibame.tga102.yokult.fundraising.vo.OrderBean;

public interface OrderDao {
	public abstract OrderBean insert(OrderBean orderBean);
	public abstract Boolean delete(Integer id);
	public abstract OrderBean update(Integer id, OrderBean orderBean);
	public abstract OrderBean select(Integer id);
	public abstract List<OrderBean> selectAll();
	public abstract List<OrderBean> selectByMemberID(String memID);
	public abstract Integer selectLastID();
}
