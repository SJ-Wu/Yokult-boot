package tibame.tga102.yokult.fundraising.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.fundraising.dao.PlanDao;
import tibame.tga102.yokult.fundraising.vo.PlanBean;
import tibame.tga102.yokult.fundraising.vo.ProposalBean;

@Service
@Transactional
public class PlanService {
	@Autowired
	private PlanDao planDAO;

	public PlanBean insertBean(PlanBean planBean) {
		return this.planDAO.insert(planBean);
	}
	public Boolean deleteBean(int id) {
		return this.planDAO.delete(id);
	}
	public PlanBean updateBean(int id, PlanBean planBean) {
		return this.planDAO.update(id, planBean);
	}
	public List<PlanBean> selectBeansByProposal(ProposalBean proposalBean) {
		List<PlanBean> list_planBean = this.planDAO.selectAllBeansByProposal(proposalBean);
		for(PlanBean planBean : list_planBean) {
			planBean.renewBean(planDAO);
		}
		return list_planBean;
	}
	public List<PlanBean> selectAllBeans() {
		return this.planDAO.selectAll();
	}
}
