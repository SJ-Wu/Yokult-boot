package tibame.tga102.yokult.fundraising.dao;

import java.util.List;

import tibame.tga102.yokult.fundraising.vo.OrderBean;
import tibame.tga102.yokult.fundraising.vo.PlanBean;
import tibame.tga102.yokult.fundraising.vo.ProposalBean;

public interface PlanDao {
	public abstract PlanBean insert(PlanBean planBean);
	public abstract Boolean delete(Integer id);
	public abstract Boolean update(Integer id, PlanBean planBean);
	public abstract List<PlanBean> selectAllBeansByProposal(ProposalBean proposalBean);
	public abstract PlanBean select(Integer id);
	public abstract List<PlanBean> selectAll();
	public abstract List<OrderBean> getRelationalOrders(PlanBean planBean);
//	public abstract void SessionClose();
}
