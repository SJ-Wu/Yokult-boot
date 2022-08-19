package tibame.tga102.yokult.fundraising.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tibame.tga102.yokult.fundraising.service.PlanService;
import tibame.tga102.yokult.fundraising.service.PostService;
import tibame.tga102.yokult.fundraising.service.ProposalService;
import tibame.tga102.yokult.fundraising.vo.PlanBean;
import tibame.tga102.yokult.fundraising.vo.PostBean;
import tibame.tga102.yokult.fundraising.vo.ProposalBean;

@RestController
@RequestMapping(path = {"/fundraising/ProposalGetOne"})
public class FundraisingPageController{
	
	@Autowired
	ProposalService proposalService;
	@Autowired
	PlanService planService;
	@Autowired
	PostService postService;
	
//	OK===
//	===================================  僅查詢一個proposal + 一個plan + post + 會員資訊 =================================
	@PostMapping(path = "")
	public Map<String ,Object> ProposalGetOne(@RequestBody ProposalBean pb, HttpServletRequest request) {
		
		String test = request.getRequestURI();
		System.out.println(test);
		
		ProposalBean proposalQueryResult = proposalService.selectBean(Integer.parseInt(pb.getPage()));
		System.out.println("proposalBean : " + proposalQueryResult);
		
		List<PlanBean> planQueryResult = planService.selectBeansByProposal(proposalQueryResult);
		
		List<PostBean> postQueryResult = postService.selectAllBeansByMemberID(pb.getMemID());
		
		Map<String ,Object> respObject = new HashMap<String, Object>();
    	if(proposalQueryResult != null) {
			respObject.put("msg", "ProposalGetOne success");
			respObject.put("Proposal", proposalQueryResult);
			respObject.put("Plans", planQueryResult);
			respObject.put("Posts", postQueryResult);
    	}else {
			respObject.put("msg", "ProposalGetOne fail");
			respObject.put("Proposal", proposalQueryResult);
    	}
    	System.out.println(respObject.get("msg"));
    	return respObject;
	}
	
}	
