package tibame.tga102.yokult.fundraising.controller;

import java.io.BufferedOutputStream;
import java.io.Console;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.codec.multipart.Part;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import tibame.tga102.yokult.fundraising.common.HtmlDatetimeLocalToSQLDateTimeUtil;
import tibame.tga102.yokult.fundraising.service.OrderService;
import tibame.tga102.yokult.fundraising.service.PlanService;
import tibame.tga102.yokult.fundraising.service.PostNumberService;
import tibame.tga102.yokult.fundraising.service.PostService;
import tibame.tga102.yokult.fundraising.service.ProposalService;
import tibame.tga102.yokult.fundraising.vo.OrderBean;
import tibame.tga102.yokult.fundraising.vo.PlanBean;
import tibame.tga102.yokult.fundraising.vo.PostBean;
import tibame.tga102.yokult.fundraising.vo.PostNumberBean;
import tibame.tga102.yokult.fundraising.vo.ProposalBean;
import tibame.tga102.yokult.member.service.MemberService;


@RestController
@RequestMapping(path = {"/fundraising"})
public class FundraisingController{

	@Autowired
    MemberService memberService;
	@Autowired
	ProposalService proposalService;
	@Autowired
	PlanService planService;
	@Autowired
	PostService postService;
	@Autowired
	PostNumberService postnumberService;
	@Autowired
	OrderService orderService;
	

//	OK
//	=======================================  ????????????proposal  =====================================
	@PostMapping(path = "/ProposalGetAll")
	public Map<String ,Object> ProposalGetAll() {
		Map<String ,Object> respObject = new HashMap<String, Object>();
		List<ProposalBean> queryResult = proposalService.selectAllBeans();
    	if(queryResult != null) {
			respObject.put("msg", "ProposalGetAll success");
			respObject.put("Proposals", queryResult);
    	}else {
			respObject.put("msg", "ProposalGetAll fail");
			respObject.put("Proposals", queryResult);
    	}
    	System.out.println(respObject.get("msg"));
    	return respObject;
	}


	
//	OK===
//	===================================  ???????????????proposal + ??????plan + post + ???????????? =================================
	@PostMapping(path = "/ProposalGetOne")
	public Map<String ,Object> ProposalGetOne(@RequestBody ProposalBean pb, HttpServletRequest request) {
		
		String test = request.getRequestURI();
		System.out.println(test);
		
		ProposalBean proposalQueryResult = proposalService.selectBean(Integer.parseInt(pb.getPage()));
		System.out.println("proposalBean : " + proposalQueryResult);
		
		List<PlanBean> planQueryResult = planService.selectBeansByProposal(proposalQueryResult);
		
		List<PostBean> postQueryResult;
		if(pb.getMemID() != null) {
			postQueryResult = postService.selectAllBeansByMemberID(pb.getMemID());
		}else {
			postQueryResult = new ArrayList<PostBean>();
		}
		
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
	
	

	
//	OK===
//	===================================  ?????????post + ???????????? =================================
	@PostMapping(path = "/PostGetMine")
	public Map<String ,Object> PostGetMine(@RequestBody PostBean pb) {
		
		List<PostBean> postQueryResult;
		if(pb.getMemID() != null) {
			postQueryResult = postService.selectAllBeansByMemberID(pb.getMemID());
		}else {
			postQueryResult = new ArrayList<PostBean>();
		}
		
		Map<String ,Object> respObject = new HashMap<String, Object>();
    	if(postQueryResult != null) {
			respObject.put("msg", "PostGetMine success");
			respObject.put("Posts", postQueryResult);
    	}else {
			respObject.put("msg", "PostGetMine fail");
			respObject.put("Posts", postQueryResult);
    	}
    	System.out.println(respObject.get("msg"));
    	return respObject;
	}
	
	
//	OK===
//	===================================  ??????post + ???????????? =================================
	@PostMapping(path = "/PostInsert")
	public Map<String ,Object> PostInsert(@RequestParam Map<String, Object> input) {
		
		PostBean bean = null;
		PostBean postBean = null;
		if(input != null && input.get("memID") != null) {
			bean = new PostBean();
			
			bean.setPostFisrtName(input.get("postFisrtName").toString());
			bean.setPostLastName(input.get("postLastName").toString());
			bean.setPostCellphone(input.get("postCellphone").toString());
			bean.setPostAddress(input.get("postAddress").toString());
			bean.setMemID(input.get("memID").toString());
			bean.setPostNickName(input.get("postNickName").toString());
			bean.setPostCity(input.get("postCity").toString());
			bean.setPostArea(input.get("postArea").toString());
			
			PostNumberBean postNumberBean = postnumberService.selectBeanByCityArea(bean.getPostCity() , bean.getPostArea());
			bean.setPost_SID(postNumberBean.getPost_SID());
			
			postBean = postService.insertBean(bean);
		}
		
		Map<String ,Object> respObject = new HashMap<String, Object>();
    	if(postBean != null) {
			respObject.put("msg", "PostInsert success");
			respObject.put("Post", postBean);
    	}else {
			respObject.put("msg", "PostInsert fail");
			respObject.put("Post", postBean);
    	}
    	System.out.println(respObject.get("msg"));
    	return respObject;
	}
	

	
//	OK===
//	===================================  ??????post + ???????????? =================================
	@PostMapping(path = "/PostModify")
	public Map<String ,Object> PostModify(@RequestParam Map<String, Object> input) {
		
		PostBean bean = null;
		PostBean postBean = null;
		if(input != null && input.get("memID") != null) {
			bean = new PostBean();
			
			bean.setPostFisrtName(input.get("postFisrtName").toString());
			bean.setPostLastName(input.get("postLastName").toString());
			bean.setPostCellphone(input.get("postCellphone").toString());
			bean.setPostAddress(input.get("postAddress").toString());
			bean.setMemID(input.get("memID").toString());
			bean.setPostNickName(input.get("postNickName").toString());
			bean.setPostCity(input.get("postCity").toString());
			bean.setPostArea(input.get("postArea").toString());
			
			PostNumberBean postNumberBean = postnumberService.selectBeanByCityArea(bean.getPostCity() , bean.getPostArea());
			bean.setPost_SID(postNumberBean.getPost_SID());
			
			System.out.println("bean : " + bean);
			
			
			postBean = postService.updateBean(Integer.parseInt(input.get("postID").toString()), bean);
		}
		
		Map<String ,Object> respObject = new HashMap<String, Object>();
    	if(postBean != null) {
			respObject.put("msg", "PostModify success");
			respObject.put("Post", postBean);
    	}else {
			respObject.put("msg", "PostModify fail");
			respObject.put("Post", postBean);
    	}
    	System.out.println(respObject.get("msg"));
    	return respObject;
	}
	
	
	
//	OK===
//	===================================  ??????post + ???????????? =================================
	@PostMapping(path = "/PostRemove")
	public Map<String ,Object> PostRemove(@RequestBody PostBean pb) {
		
		Boolean result = postService.deleteBean(pb.getPostID());
		
		Map<String ,Object> respObject = new HashMap<String, Object>();
    	if(result == true) {
			respObject.put("msg", "PostRemove success");
			respObject.put("planID", pb.getPostID());
			respObject.put("planName", pb.getPostNickName());
    	}else {
			respObject.put("msg", "PostRemove fail");
			respObject.put("planID", pb.getPostID());
    	}
    	System.out.println(respObject.get("msg"));
    	return respObject;
	}
	
	
	
//	OK
//	===================================  ??????????????????proposal + plan + ???????????? =================================
	@PostMapping(path = "/ProposalGetMine")
	public Map<String ,Object> ProposalGetMine(@RequestBody ProposalBean pb) throws IOException {

		System.out.println(pb.getMemID());
//    	List<ProposalBean> allMyProposalBeans = proposalService.selectMyAllBeans(member.getMemID());
    	List<ProposalBean> allMyProposalBeans = proposalService.selectMyAllBeans(pb.getMemID());
    	List<Object> proposalPlanList = new ArrayList<Object>();
    	for(ProposalBean proposalBean : allMyProposalBeans) {
    		List<PlanBean> planQueryResult = planService.selectBeansByProposal(proposalBean);
    		proposalPlanList.add(planQueryResult);
    	}
    	
		Map<String ,Object> respObject = new HashMap<String, Object>();
    	if(allMyProposalBeans != null) {
			respObject.put("msg", "ProposalGetMine success");
			respObject.put("Proposals", allMyProposalBeans);
			respObject.put("Plans", proposalPlanList);
    	}else {
			respObject.put("msg", "ProposalGetMine fail");
			respObject.put("Proposals", allMyProposalBeans);
    	}
    	System.out.println(respObject.get("msg"));
    	return respObject;
	}
	
//	OK
//	===================================  ?????????????????????proposal + plan + ???????????? =================================
	@PostMapping(path = "/ProposalGetMyOrder")
	public Map<String ,Object> ProposalGetMyOrder(@RequestBody ProposalBean pb) {
		System.out.println(pb.getMemID());
	    List<OrderBean> list_Order = orderService.selectMyOrderBeans(pb.getMemID());
	    List<ProposalBean> list_Proposal = proposalService.selectByOrderList(list_Order);
	    List<PlanBean> list_Plan = planService.selectByOrderList(list_Order);

		Map<String ,Object> respObject = new HashMap<String, Object>();
    	if(list_Order != null) {
			respObject.put("msg", "ProposalGetMyOrder success");
			respObject.put("Orders", list_Order);
			respObject.put("Proposals", list_Proposal);
			respObject.put("Plans", list_Plan);
    	}else {
			respObject.put("msg", "ProposalGetMyOrder fail");
			respObject.put("Orders", list_Order);
    	}
    	System.out.println(respObject.get("msg"));
    	return respObject;
	}
	

//	======================================== ????????????plan ===============================================
	@PostMapping(path = "/PlanRemove")
	public Map<String ,Object> PlanRemove(@RequestBody PlanBean pb) {
		
		Boolean result = planService.deleteBean(pb.getPlanID());
		
		Map<String ,Object> respObject = new HashMap<String, Object>();
    	if(result == true) {
			respObject.put("msg", "PlanRemove success");
			respObject.put("planID", pb.getPlanID());
			respObject.put("planName", pb.getPlanName());
    	}else {
			respObject.put("msg", "PlanRemove fail");
			respObject.put("planID", pb.getPlanID());
    	}
    	System.out.println(respObject.get("msg"));
    	return respObject;
	}
	
	

//	======================================== ????????????plan ===============================================
	@PostMapping(path = "/PlanModify")
	public Map<String ,Object> PlanModify(
			@RequestParam Map<String, Object> input,
			@RequestParam Map<String, MultipartFile> files) {
		
		PlanBean bean = null;
		Map<String ,Object> respObject = null;
		if(input != null && input.get("planID").toString() != "") {
			bean = new PlanBean();
			
			try {
				if(files.get("planPicture") == null) {
					byte[] byteArray = planService.selectBean(Integer.parseInt(input.get("planID").toString())).getPlanPicture();
					bean.setPlanPicture(byteArray);
				}else {
					InputStream is = files.get("planPicture").getInputStream();
					byte[] sourceBytes = is.readAllBytes();
					bean.setPlanPicture(sourceBytes);
				}
			}catch (Exception e) {
			}
			
			System.out.println(input.get("planID"));
			
			bean.setPlanName(input.get("planName").toString());
			bean.setPlanAmount(Integer.parseInt(input.get("planAmount").toString()));
			bean.setPlanContent(input.get("planContent").toString());
			bean.setPlanPostNote(input.get("planPostNote").toString());
			
			java.sql.Date planPostDate = HtmlDatetimeLocalToSQLDateTimeUtil.parseDate(input.get("planPostDate").toString());
			bean.setPlanPostDate(planPostDate);
			java.sql.Date planStartedDateTime = HtmlDatetimeLocalToSQLDateTimeUtil.parseDate(input.get("planStartedDateTime").toString());
			bean.setPlanStartedDateTime(planStartedDateTime);
			java.sql.Date planEndedDateTime = HtmlDatetimeLocalToSQLDateTimeUtil.parseDate(input.get("planEndedDateTime").toString());
			bean.setPlanEndedDateTime(planEndedDateTime);
			
			bean.setProposalID(Integer.parseInt(input.get("proposalID").toString()));
			
			System.out.println("bean : " + bean);
			Boolean resultBoolean = planService.updateBean(Integer.parseInt(input.get("planID").toString()), bean);
			System.out.println("planService : "  + bean);
		
		respObject = new HashMap<String, Object>();
    	if(resultBoolean == true) {
			respObject.put("msg", "PlanModify success");
			respObject.put("planID", bean.getPlanID());
			respObject.put("planName", bean.getPlanName());
    	}else {
			respObject.put("msg", "PlanModify fail");
			respObject.put("planID", bean.getPlanID());
    	}
    	System.out.println(respObject.get("msg"));
		}
		return respObject;
	}
	
	
	

//	======================================== ????????????plan ===============================================
	@PostMapping(path = "/PlanInsert")
	public Map<String ,Object> PlanInsert(
			@RequestParam Map<String, Object> input,
			@RequestParam Map<String, MultipartFile> files) {
		
		PlanBean bean = null;
		Map<String ,Object> respObject = null;
		if(input != null && input.get("proposalID").toString() != "") {
			bean = new PlanBean();
			
			try {
				InputStream is = files.get("planPicture").getInputStream();
				byte[] sourceBytes = is.readAllBytes();
				bean.setPlanPicture(sourceBytes);
			}catch (Exception e) {
			}
			
			bean.setPlanName(input.get("planName").toString());
			bean.setPlanAmount(Integer.parseInt(input.get("planAmount").toString()));
			bean.setPlanContent(input.get("planContent").toString());
			bean.setPlanPostNote(input.get("planPostNote").toString());
			
			java.sql.Date planPostDate = HtmlDatetimeLocalToSQLDateTimeUtil.parseDate(input.get("planPostDate").toString());
			bean.setPlanPostDate(planPostDate);
			java.sql.Date planStartedDateTime = HtmlDatetimeLocalToSQLDateTimeUtil.parseDate(input.get("planStartedDateTime").toString());
			bean.setPlanStartedDateTime(planStartedDateTime);
			java.sql.Date planEndedDateTime = HtmlDatetimeLocalToSQLDateTimeUtil.parseDate(input.get("planEndedDateTime").toString());
			bean.setPlanEndedDateTime(planEndedDateTime);
			
			bean.setProposalID(Integer.parseInt(input.get("proposalID").toString()));
			
			System.out.println("bean : " + bean);
			PlanBean planBean = planService.insertBean(bean);
			System.out.println("planService : "  + planBean);
		
		respObject = new HashMap<String, Object>();
    	if(planBean != null) {
			respObject.put("msg", "PlanInsert success");
			respObject.put("planID", planBean.getPlanID());
			respObject.put("planName", planBean.getPlanName());
    	}else {
			respObject.put("msg", "PlanInsert fail");
			respObject.put("planID", planBean.getPlanID());
    	}
    	System.out.println(respObject.get("msg"));
		}
		return respObject;
	}
	
	
	
//	OK
//	=========================================  ????????????proposal  =========================================
	@PostMapping(path = "/ProposalInsert")
	public Map<String, Object> ProposalInsert(
			@RequestParam Map<String, Object> input,
			@RequestParam Map<String, MultipartFile> files) throws IOException {
		
		ProposalBean proposalBean = null;
		if(input != null && files != null) {
			proposalBean = new ProposalBean();
			
			try {
				InputStream is = files.get("proposalHtmlContent").getInputStream();
				byte[] sourceBytes = is.readAllBytes();
				String str = new String(sourceBytes);
				proposalBean.setProposalHtmlContent(str);
			}catch (Exception e) {
			}
			
			try {
				InputStream is = files.get("proposalPicture").getInputStream();
				byte[] sourceBytes = is.readAllBytes();
				proposalBean.setProposalPicture(sourceBytes);
			}catch (Exception e) {
			}
			
			try {
				InputStream is = files.get("proposalPictureZip").getInputStream();
				byte[] sourceBytes = is.readAllBytes();
				proposalBean.setProposalPictureZip(sourceBytes);
			}catch (Exception e) {
			}
			
			proposalBean.setProposalName(input.get("proposalName").toString());
			proposalBean.setProposalHostName(input.get("proposalHostName").toString());
			proposalBean.setProposalGoal(Integer.parseInt(input.get("proposalGoal").toString()));
			proposalBean.setProposalCategoryID(input.get("proposalCategory").toString());
			
			java.sql.Timestamp startDate = HtmlDatetimeLocalToSQLDateTimeUtil.parseDateTime(input.get("proposalStartedDateTime").toString());
			proposalBean.setProposalStartedDateTime(startDate);
			java.sql.Timestamp endDate = HtmlDatetimeLocalToSQLDateTimeUtil.parseDateTime(input.get("proposalEndedDateTime").toString());
			proposalBean.setProposalEndedDateTime(endDate);
			
			proposalBean.setProposalEmail(input.get("proposalEmail").toString());
			proposalBean.setProposalCellphone(input.get("proposalCellphone").toString());
			proposalBean.setProposalSummary(input.get("proposalSummary").toString());
			proposalBean.setMemID(input.get("memID").toString());
			
//			System.out.println("print ProposalBean");
//			System.out.println(proposalBean);
		}

	//	????????????(MySQL)
		try {
	//    	== ????????? newProposal?????? form?????????????????????bean?????? ==
			proposalService.insertBean(proposalBean);
		} catch (Exception e) {}
		
		Map<String ,Object> respObject = new HashMap<String, Object>();
    	if(proposalBean != null) {
    		respObject.put("msg", "ProposalInsert success");
    		respObject.put("proposalID", proposalBean.getProposalID());
    		respObject.put("proposalName", proposalBean.getProposalName());
    	}else {
			respObject.put("msg", "ProposalInsert fail");
			respObject.put("proposalID", proposalBean.getProposalID());
    	}
    	System.out.println(respObject.get("msg"));
    	return respObject;
	}
	
	
	
	
	
	
	
}	
	///////////////////////////////////////////////////////////////////////////
	













//	    
////	    == ????????????form?????? ==
////	   	??????????????????????????????template?????????????????????????????????upload file?????????
//	    Enumeration<String> names = req.getParameterNames();
//	    while(names.hasMoreElements()) {
//	    	String key = names.nextElement();
//	    	System.out.println(String.format(template, key, req.getParameter(key)));				
//	    }
//	    
//	    
//	    
////	    =========================================  ???????????????  =========================================
//	    
////	    =======================================   ?????????????????????   ========================================
//////	    ?????????JSON???????????????????????????????????????????????????
//	    try {
//			HtmlPostJsonGetter htmlPostJsonGetter = new HtmlPostJsonGetter(req);
//			table = htmlPostJsonGetter.getString("table");
//			action = htmlPostJsonGetter.getString("action");
//			page = htmlPostJsonGetter.getString("page");
//			System.out.println("table : " + table);
//			System.out.println("action : " + action);
//		} catch (Exception e1) {}
//	    
//	    
//////    =======================================  ????????????proposal  =====================================
////	    fundraising_AllProposal
//	    if("Proposal".equals(table) && "GetAll".equals(action)){	
//	    	res.setContentType("application/json; charset=utf-8");
//	    	ProposalService proposalService = new ProposalService();
//	    	List<ProposalBean> queryResult = proposalService.selectAllBeans();
//	    	
//	    	PrintWriter out = res.getWriter();
//	    	String jsonString = new Gson().toJson(queryResult);
//	    	out.write(jsonString);
//	    	System.out.println("Succeeded to get proposalListJsonString : \n" + jsonString);
//	    	
//	    	out.flush();
//	    	res.flushBuffer();
//	    	System.out.println();    //????????????
//	    }
//	    
//////    ===================================  ???????????????proposal + ??????plan + ???????????? =================================
////	    fundraising_propsal-n
//	    if("Proposal".equals(table) && "GetOne".equals(action)){	
//	    	res.setContentType("application/json; charset=utf-8");
//	    	ProposalService proposalService = new ProposalService(res);
//	    	ProposalBean proposalQueryResult = proposalService.selectBean(Integer.parseInt(page));
//	    	System.out.println("proposalBean : " + proposalQueryResult);
//	    	
//	    	PlanService planService = new PlanService(res);
//	    	List<PlanBean> planQueryResult = planService.selectBeansByProposal(proposalQueryResult);
//	    	for(PlanBean bean : planQueryResult) {
//				System.out.println("planBean : " + bean);
//	    	}
//	    	
//	    	PostService postService = new PostService();
//	    	List<PostBean> postQueryResult = postService.selectAllBeansByMemberID(member.getMemID());
//	    	PostNumberService postnumberService = new PostNumberService();
//	    	for(PostBean bean : postQueryResult) {
//	    		bean.renew(postnumberService);
//				System.out.println("postBean : " + bean);
//	    	}
//	    	
//	    	List<Object> sum = new ArrayList<Object>();
//	    	sum.add(proposalQueryResult);
//	    	sum.add(planQueryResult);
//	    	sum.add(postQueryResult);
//	    	
//	    	PrintWriter out = res.getWriter();
//	    	String jsonString = new Gson().toJson(sum);
//	    	out.write(jsonString);
////	    	System.out.println("Succeeded to get proposalJsonString + plansJsonString : \n" + jsonString);
//	    	
//	    	out.flush();
//	    	res.flushBuffer();
//	    	System.out.println();    //????????????
//	    }
//	    
//	    
//////    ===================================  ??????????????????proposal + plan + ???????????? =================================
////	    fundraising_propsal-n
//	    if("Proposal".equals(table) && "GetMine".equals(action)){	
//	    	res.setContentType("application/json; charset=utf-8");
//	    	ProposalService proposalService = new ProposalService(res);
//	    	
//	    	List<ProposalBean> allMyProposalBeans = proposalService.selectMyAllBeans(member.getMemID());
//	    	System.out.println("allMyProposalBeans.size : " + allMyProposalBeans.size());
//	    	List<ProposalBean> proposalList = new ArrayList<ProposalBean>();
//	    	List<Object> proposalPlanList = new ArrayList<Object>();
//	    	for(ProposalBean proposalBean : allMyProposalBeans) {
//	    		proposalList.add(proposalBean);
//	    		System.out.println("proposalBean : " + proposalBean);
//	    		
//	    		PlanService planService = new PlanService(res);
//	    		List<PlanBean> planQueryResult = planService.selectBeansByProposal(proposalBean);
//	    		proposalPlanList.add(planQueryResult);
//	    		for(PlanBean bean : planQueryResult) {
//	    			System.out.println("planBean : " + bean);
//	    		}
//	    	}
//	    	
//	    	List<Object> sum = new ArrayList<Object>();
//	    	sum.add(proposalList);
//	    	sum.add(proposalPlanList);
//	    	
//	    	PrintWriter out = res.getWriter();
//	    	String jsonString = new Gson().toJson(sum);
//	    	out.write(jsonString);
////	    	System.out.println("Succeeded to get proposalJsonString + plansJsonString : \n" + jsonString);
//	    	
//	    	out.flush();
//	    	res.flushBuffer();
//	    	System.out.println();    //????????????
//	    }
//	    
//	    
////    	res.setContentType("application/json; charset=utf-8");
////    	
////    	ProposalService proposalService = new ProposalService();
////    	long currentMillis =  System.currentTimeMillis();
////    	List<ProposalBean> list_Proposal = proposalService.selectAllBeans();
////    	for(ProposalBean b : list_Proposal) {
////    		b.renewBean(proposalService);
////    	}
////    	PrintWriter out = res.getWriter();
////    	String proposalJsonString = new Gson().toJson(list_Proposal);
////    	System.out.println("proposalJsonString : " + proposalJsonString);
////    	out.write(proposalJsonString);
////    	out.flush();
////    	res.flushBuffer();
////    	System.out.println();
//	    
//////    ???????????????
////    table = "Order";
////    action = "Insert";
//////    =========================================  ????????????order  =========================================
////    if("Order".equals(req.getParameter("table")) && "Insert".equals(req.getParameter("action"))){	    
//////    if("Order".equals(table) && "Insert".equals(action)){	    
////		res.setContentType("text/html; charset=UTF-8");
//////    	????????????
////    	FundraisingUtil.getTableActionStart(req, table, action);
////    	
////    	OrderService orderService = new OrderService();
////    	OrderBean bean = new OrderBean();
////    	
////    	Integer newID = 100000 + orderService.selectLastID() + 1;
////    	bean.setOrderInvoiceNumber("TG-" + newID.toString());
////    	
////    	java.sql.Timestamp now = new java.sql.Timestamp(System.currentTimeMillis());
////    	bean.setOrderDateTime(now);
////    	
////    	bean.setOrderAmount(Integer.parseInt(req.getParameter("orderAmount")));
////    	bean.setProposalID(Integer.parseInt(req.getParameter("proposalID")));
////    	bean.setPlanID(Integer.parseInt(req.getParameter("planID")));
////    	bean.setPostID(Integer.parseInt(req.getParameter("postID")));
////    	bean.setMemID("TGA001");
////    	
////    	System.out.println("print bean");
////    	System.out.println(bean);
////    	
////    	
//////    	????????????(MySQL)
////    	System.out.println("new orderService");
////    	try {
//////	    	== ????????? newOrder?????? form?????????????????????bean?????? ==
////    		orderService.insertBean(bean);
////		} catch (Exception e) {
////		} finally {
////			System.out.println("insertBean");
//////	    	????????????
////			FundraisingUtil.getTableActionEnd(req, table, action);
////		}
////    }
//	    
//	    
//	    
//	    
//////    ???????????????
////    table = "Post";
////    action = "Insert";
////    =========================================  ????????????post  =========================================
//////    if("Post".equals(req.getParameter("table")) && "Insert".equals(req.getParameter("action"))){	    
////    if("Post".equals(table) && "Insert".equals(action)){	    
////		res.setContentType("text/html; charset=UTF-8");
//////    	????????????
////    	FundraisingUtil.getTableActionStart(req, table, action);
////    	
////    	PostBean bean = new PostBean();
////    	bean.setPostFisrtName(req.getParameter("postFisrtName"));
////    	bean.setPostLastName(req.getParameter("postLastName"));
////    	bean.setPostCellphone(req.getParameter("postCellphone"));
////    	bean.setPost_SID(Integer.parseInt(req.getParameter("post_SID")));
////    	bean.setPostAddress(req.getParameter("postAddress"));
////    	bean.setMemID("TGA001");
////    	
////    	System.out.println("print bean");
////    	System.out.println(bean);
////    	
////    	
//////    	????????????(MySQL)
////    	PostService postService = new PostService();
////    	System.out.println("new postService");
////    	try {
//////	    	== ????????? newPost?????? form?????????????????????bean?????? ==
////    		postService.insertBean(bean);
////		} catch (Exception e) {
////		} finally {
////			System.out.println("insertBean");
//////	    	????????????
////			FundraisingUtil.getTableActionEnd(req, table, action);
////		}
////    }
//
//	    
//	    
//////	    =========================================  ????????????proposal  =========================================
////	    if("Proposal".equals(req.getParameter("table")) && "Insert".equals(req.getParameter("action"))){	    
////    		res.setContentType("text/html; charset=UTF-8");
//////    		????????????newProposal??????upload file??????
////	    	System.out.println("=== proposalFile ===");
////	    	ProposalBean bean = new ProposalBean();
//////	    	??????????????????TGA001
//////	    	????????????
////	    	FundraisingUtil.getTableActionStart(req, table, action);
//////	    	???????????????????????????
////	    	for (Part part : req.getParts()) {
//////	    		newProposal??????
////		    	if("proposalPicture".equals(part.getName())  ) {
////		    		InputStream is = part.getInputStream();
////		    		byte[] sourceBytes = IOUtils.toByteArray(is);
////		    		bean.setProposalPicture(sourceBytes);		    		
////		    	}else if("proposalPictureZip".equals(part.getName())) {
////		    		InputStream is = part.getInputStream();
////		    		byte[] sourceBytes = IOUtils.toByteArray(is);
////		    		bean.setProposalPictureZip(sourceBytes);
////		    	}
////	    	}
////	    	
////	    	bean.setProposalName(req.getParameter("proposalName"));
////	    	bean.setProposalHostName(req.getParameter("proposalHostName"));
////	    	bean.setProposalGoal(Integer.parseInt(req.getParameter("proposalGoal")));
////	    	bean.setProposalCategoryID(req.getParameter("proposalCategory"));
////	    	
////	    	java.sql.Timestamp startDate = HtmlDatetimeLocalToSQLDateTimeUtil.parse(req, req.getParameter("proposalStartedDateTime"));
////		    bean.setProposalStartedDateTime(startDate);
////		    java.sql.Timestamp endDate = HtmlDatetimeLocalToSQLDateTimeUtil.parse(req, req.getParameter("proposalEndedDateTime"));
////		    bean.setProposalEndedDateTime(endDate);
////		    
////	    	bean.setStatusID("3");
////	    	bean.setProposalEmail(req.getParameter("proposalEmail"));
////	    	bean.setProposalCellphone(req.getParameter("proposalCellphone"));
////	    	bean.setProposalSummary(req.getParameter("proposalContent"));
////	    	bean.setMemID("TGA001");
////	    	
////	    	System.out.println("print bean");
////	    	System.out.println(bean);
////	    	
////	    	
//////	    	????????????(MySQL)
////	    	ProposalService proposalService = new ProposalService();
////	    	System.out.println("new proposalService");
////	    	try {
//////		    	== ????????? newProposal?????? form?????????????????????bean?????? ==
////	    		proposalService.insertBean(bean);
////			} catch (Exception e) {
////			} finally {
////				System.out.println("insertBean");
//////		    	????????????
////				FundraisingUtil.getTableActionEnd(req, table, action);
////			}
////	    }
//
//	    
//	    
////	    =========================================  ????????????plan  =========================================
////	    if("Plan".equals(req.getParameter("table")) && "Insert".equals(req.getParameter("action"))) {
////	    	
//////    		????????????newPlan??????upload file??????
////	    	System.out.println("=== planPicture ===");
////	    	String pictureName = req.getPart("planPicture").getSubmittedFileName();
////	    	System.out.println(String.format(template, "planPicture", pictureName));
//////	    	??????????????????TGA001
////	    	String memberUploadPath = getServletContext().getRealPath("/") + "uploaded\\proposal\\plan" + "\\" + memberID;
////	    	String memberUploadPicturePath = memberUploadPath + "\\" + pictureName;
//////	    	???????????????????????????
////	    	for (Part part : req.getParts()) {
//////	    		newPlan??????
////		    	if("planPicture".equals(part.getName())  ) {
////		    		File theDir = new File(memberUploadPath);
////		    		if (!theDir.exists()){
////		    			theDir.mkdirs();
////		    			System.out.println("Directory created : " + memberUploadPath);	    			
////		    		}else {
////		    			System.out.println("Directory is exist.");					
////					}
////		    		part.write(memberUploadPicturePath);
////		    	    System.out.println("The uploaded picture uploaded and writed sucessfully.\n");
////		    	}
////	    	}
////	    	
//////	    	????????????
////	    	FundraisingUtil.getTableActionStart(req, table, action);
//////	    	
////	    	PlanBean bean = new PlanBean();
////	    	bean.setPlanName(req.getParameter("planName"));
////	    	bean.setPlanAmount(Integer.parseInt(req.getParameter("planAmount")));
////	    	bean.setPlanContent(req.getParameter("planContent"));
////	    	bean.setPlanPostNote(req.getParameter("planPostNote"));
////	    	
////	    	java.sql.Date planPostDate = HtmlDatetimeLocalToSQLDateTimeUtil.parse(req, req.getParameter("planStartedDateTime"));
////	    	bean.setPlanStartedDate(planPostDate);
////	    	java.sql.Timestamp planStartedDateTime = HtmlDatetimeLocalToSQLDateTimeUtil.parse(req, req.getParameter("planStartedDateTime"));
////	    	bean.setPlanStartedDate(planStartedDateTime);
////	    	java.sql.Timestamp planEndedDateTime = HtmlDatetimeLocalToSQLDateTimeUtil.parse(req, req.getParameter("planEndedDateTime"));
////	    	bean.setPlanEndedDate(planEndedDateTime);
////	    	
////	    	bean.setStatusID(req.getParameter(memberID));
////	    	bean.setProposalID(proposalID);
////	    	
////	    	System.out.println(bean);
////	    	
////	    	
//////	    	????????????(MySQL)
////	    	PlanService planService = new PlanService();
////	    	System.out.println("new planService");
////	    	try {
////	    		planService.insertBean(bean);
////			} catch (Exception e) {
////			} finally {
////				System.out.println("insertBean");
//////		    	????????????
////				FundraisingUtil.getTableActionEnd(req, table, action);
////			}
////	    	
////	    }
//	    
//	    
//////	    =========================================  ??????(??????)  =========================================
////	    
//////	    if(true) {
////	    CategoryService categoryService = new CategoryService();
////	    List<CategoryBean> list_Category = categoryService.selectAllBeans();
////	    for(CategoryBean b : list_Category) {
////	    	System.out.println(b);
////	    }
////	    System.out.println();
//////	    	httpSession.setAttribute("list_Category", list_Category);
//////	    }
////	    System.out.println("-------------- command ------- end -------\n");
////	    
////	    
//////	    if(true) {
////	    OrderService orderService = new OrderService();
////	    List<OrderBean> list_Order = orderService.selectAllBeans();
////	    for(OrderBean b : list_Order) {
////	    	System.out.println(b);
////	    }
////	    System.out.println();
//////	    	httpSession.setAttribute("list_Order", list_Order);
//////	    }
////	    System.out.println("-------------- command :  ------- end -------\n");
////
////	    
//////	    if(true) {
////	    PlanService planService = new PlanService();
////	    List<PlanBean> list_Plan = planService.selectAllBeans();
////	    for(PlanBean b : list_Plan) {
////	    	System.out.println(b);
////	    }
////	    System.out.println();
//////	    	httpSession.setAttribute("list_Plan", list_Plan);
//////		}
////	    System.out.println("-------------- command :  ------- end -------\n");
////
////	    
//////	    if(true) {
////	    PostService postService = new PostService();
////	    List<PostBean> list_Post = postService.selectAllBeans();
////	    for(PostBean b : list_Post) {
////	    	System.out.println(b);
////	    }
////	    System.out.println();
//////	    	httpSession.setAttribute("list_Post", list_Post);
//////		}
////	    System.out.println("-------------- command :  ------- end -------\n");
////	    
//////////////////////////////////////////////////////////////////////
////	    if(true) {
////    	res.setContentType("application/json; charset=utf-8");
////    	
////    	ProposalService proposalService = new ProposalService();
////    	long currentMillis =  System.currentTimeMillis();
////    	List<ProposalBean> list_Proposal = proposalService.selectAllBeans();
////    	for(ProposalBean b : list_Proposal) {
////    		b.renewBean(proposalService);
////    	}
////    	PrintWriter out = res.getWriter();
////    	String proposalJsonString = new Gson().toJson(list_Proposal);
////    	System.out.println("proposalJsonString : " + proposalJsonString);
////    	out.write(proposalJsonString);
////    	out.flush();
////    	res.flushBuffer();
////    	System.out.println();
////	}
////	System.out.println("-------------- command :  ------- end -------\n");
//////////////////////////////////////////////////////////////////////////////////
////	    ??????????????????proposal??????
////	    if(true) {
////	    	res.setContentType("application/json; charset=utf-8");
////	    	
////	    	ProposalService proposalService = new ProposalService();
////	    	long currentMillis =  System.currentTimeMillis();
////	    	List<ProposalBean> list_Proposal = proposalService.selectAllBeans();
////	    	for(ProposalBean b : list_Proposal) {
////	    		b.renewBean(proposalService);
////	    	}
////	    	PrintWriter out = res.getWriter();
////	    	String proposalJsonString = new Gson().toJson(list_Proposal);
////	    	System.out.println("proposalJsonString : " + proposalJsonString);
////	    	out.write(proposalJsonString);
////	    	out.flush();
////	    	res.flushBuffer();
////	    	System.out.println();
////		}
////		System.out.println("-------------- command :  ------- end -------\n");
//////////////////////////////////////////////////////////////////////////////////
////		    
//////	    if(true) {
////	    	StatusService statusService = new StatusService();
////	    	List<StatusBean> list_Status = statusService.selectAllBeans();
////	    	for(StatusBean b : list_Status) {
////	    		System.out.println(b);
////	    	}
////	    	System.out.println();
//////	    	httpSession.setAttribute("list_Status", list_Status);
//////	    }
////	    System.out.println("-------------- command ------- end -------\n");
////	    
//	    
//	    
//
//
//	}


