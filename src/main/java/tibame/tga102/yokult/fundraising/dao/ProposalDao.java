package tibame.tga102.yokult.fundraising.dao;

import java.util.List;

import tibame.tga102.yokult.fundraising.vo.OrderBean;
import tibame.tga102.yokult.fundraising.vo.ProposalBean;

public interface ProposalDao {
	public abstract ProposalBean insert(ProposalBean proposalBean);
	public abstract Boolean delete(Integer id);
	public abstract ProposalBean update(Integer id, ProposalBean proposalBean);
	public abstract ProposalBean select(Integer id);
	public abstract List<ProposalBean> selectAll();
	public abstract Integer currentTotalAmount(ProposalBean proposalBean);
}
