package tibame.tga102.yokult.fundraising.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.fundraising.dao.PlanDao;
import tibame.tga102.yokult.fundraising.vo.OrderBean;
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
	public Boolean updateBean(int id, PlanBean planBean) {
		return this.planDAO.update(id, planBean);
	}
	
	public PlanBean selectBean(Integer id) {
		PlanBean bean = this.planDAO.select(id).renewBean(this.planDAO);
		return bean;
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
	
	public List<PlanBean> selectByOrderList(List<OrderBean> orderBeanList) {
		List<PlanBean> list_PlanBean = new ArrayList<PlanBean>();
		for(OrderBean orderBean : orderBeanList) {
			PlanBean plan = this.selectBean(orderBean.getPlanID());		
			list_PlanBean.add(plan);
		}
		return list_PlanBean;
	}
}
