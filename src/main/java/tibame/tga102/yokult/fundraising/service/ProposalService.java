package tibame.tga102.yokult.fundraising.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.fundraising.dao.ProposalDao;
import tibame.tga102.yokult.fundraising.vo.OrderBean;
import tibame.tga102.yokult.fundraising.vo.ProposalBean;

@Service
@Transactional
public class ProposalService {
	@Autowired
	ProposalDao proposalDAO;
	
	public ProposalBean insertBean(ProposalBean proposalBean) {
		return this.proposalDAO.insert(proposalBean);
	}
	public Boolean deleteBean(Integer id) {
		return this.proposalDAO.delete(id);
	}
	public ProposalBean updateBean(Integer id, ProposalBean proposalBean) {
		ProposalBean bean = this.proposalDAO.update(id, proposalBean).renewBean(this.proposalDAO);
		return bean;
	}
	public ProposalBean selectBean(Integer id) {
		ProposalBean bean = this.proposalDAO.select(id).renewBean(this.proposalDAO);
		return bean;
	}
	
	public List<ProposalBean> selectMyAllBeans(String memID) throws IOException {
		try {
			List<ProposalBean> list_Proposal = this.proposalDAO.selectAll();
			List<ProposalBean> myProposalBeans = new ArrayList<ProposalBean>();
	    	for(ProposalBean b : list_Proposal) {
	    		if(memID.equals(b.getMemID())) {
	    			b.renewBean(this.proposalDAO);
	    			myProposalBeans.add(b);
	    		}
	    	}
	    	return myProposalBeans;
		} catch (Exception e) {
			System.out.println("false");
			return null;
		}
	}
	
	public List<ProposalBean> selectAllBeans() {
		try {
			List<ProposalBean> list_Proposal = this.proposalDAO.selectAll();
	    	for(ProposalBean b : list_Proposal) {
	    		b.renewBean(this.proposalDAO);
	    	}
	    	return list_Proposal;
		} catch (Exception e) {
			System.out.println("false");
			return null;
		}
	}

	
	public List<ProposalBean> selectByOrderList(List<OrderBean> orderBeanList) {
		List<ProposalBean> list_ProposalBean = new ArrayList<ProposalBean>();
		for(OrderBean orderBean : orderBeanList) {
			ProposalBean proposal = this.selectBean(orderBean.getProposalID());		
			list_ProposalBean.add(proposal);
		}
		return list_ProposalBean;
	}
	
}
