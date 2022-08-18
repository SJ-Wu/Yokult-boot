package tibame.tga102.yokult.fundraising.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import tibame.tga102.yokult.fundraising.service.OrderService;
import tibame.tga102.yokult.fundraising.service.PlanService;
import tibame.tga102.yokult.fundraising.service.PostNumberService;
import tibame.tga102.yokult.fundraising.service.PostService;
import tibame.tga102.yokult.fundraising.service.ProposalService;
import tibame.tga102.yokult.fundraising.vo.OrderBean;
import tibame.tga102.yokult.fundraising.vo.PlanBean;
import tibame.tga102.yokult.fundraising.vo.PostBean;
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
	
//	=======================================  查詢全部proposal  =====================================
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

		
//	===================================  僅查詢一個proposal + 一個plan + post + 會員資訊 =================================
	@PostMapping(path = "/ProposalGetOne")
	public Map<String ,Object> ProposalGetOne(@RequestBody ProposalBean pb) {
		ProposalBean proposalQueryResult = proposalService.selectBean(Integer.parseInt(pb.getPage()));
		System.out.println("proposalBean : " + proposalQueryResult);
		
		List<PlanBean> planQueryResult = planService.selectBeansByProposal(proposalQueryResult);
		
//		List<PostBean> postQueryResult = postService.selectAllBeansByMemberID(member.getMemID());
		List<PostBean> postQueryResult = postService.selectAllBeansByMemberID("TGA001");
		
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
	
	
//	===================================  僅查詢自己的proposal + plan + 會員資訊 =================================
	@PostMapping(path = "/ProposalGetMine")
	public Map<String ,Object> ProposalGetMine() throws IOException {

//    	List<ProposalBean> allMyProposalBeans = proposalService.selectMyAllBeans(member.getMemID());
    	List<ProposalBean> allMyProposalBeans = proposalService.selectMyAllBeans("TGA001");
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
	
	
//	===================================  查詢自己訂單的proposal + plan + 會員資訊 =================================
	@PostMapping(path = "/ProposalGetMyOrder")
	public Map<String ,Object> ProposalGetMyOrder() {
//	    List<OrderBean> list_Order = orderService.selectAllBeans(member.getMemID());
	    List<OrderBean> list_Order = orderService.selectMyOrderBeans("TGA001");
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
	
	
//	    
////	    == 檢查前端form資料 ==
////	   	如果有的話，建立檢視template並逐個列印參數，但不含upload file！！！
//	    Enumeration<String> names = req.getParameterNames();
//	    while(names.hasMoreElements()) {
//	    	String key = names.nextElement();
//	    	System.out.println(String.format(template, key, req.getParameter(key)));				
//	    }
//	    
//	    
//	    
////	    =========================================  主程式開始  =========================================
//	    
////	    =======================================   查詢系列初始化   ========================================
//////	    如果是JSON格式就先抓看看有沒有自訂的指令參數
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
//////    =======================================  查詢全部proposal  =====================================
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
//	    	System.out.println();    //間隔一行
//	    }
//	    
//////    ===================================  僅查詢一個proposal + 一個plan + 會員資訊 =================================
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
//	    	System.out.println();    //間隔一行
//	    }
//	    
//	    
//////    ===================================  僅查詢自己的proposal + plan + 會員資訊 =================================
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
//	    	System.out.println();    //間隔一行
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
//////    測試用輸入
////    table = "Order";
////    action = "Insert";
//////    =========================================  新增一個order  =========================================
////    if("Order".equals(req.getParameter("table")) && "Insert".equals(req.getParameter("action"))){	    
//////    if("Order".equals(table) && "Insert".equals(action)){	    
////		res.setContentType("text/html; charset=UTF-8");
//////    	標註起始
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
//////    	執行存入(MySQL)
////    	System.out.println("new orderService");
////    	try {
//////	    	== 將前端 newOrder頁面 form參數傳入對應的bean資訊 ==
////    		orderService.insertBean(bean);
////		} catch (Exception e) {
////		} finally {
////			System.out.println("insertBean");
//////	    	標註結束
////			FundraisingUtil.getTableActionEnd(req, table, action);
////		}
////    }
//	    
//	    
//	    
//	    
//////    測試用輸入
////    table = "Post";
////    action = "Insert";
////    =========================================  新增一個post  =========================================
//////    if("Post".equals(req.getParameter("table")) && "Insert".equals(req.getParameter("action"))){	    
////    if("Post".equals(table) && "Insert".equals(action)){	    
////		res.setContentType("text/html; charset=UTF-8");
//////    	標註起始
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
//////    	執行存入(MySQL)
////    	PostService postService = new PostService();
////    	System.out.println("new postService");
////    	try {
//////	    	== 將前端 newPost頁面 form參數傳入對應的bean資訊 ==
////    		postService.insertBean(bean);
////		} catch (Exception e) {
////		} finally {
////			System.out.println("insertBean");
//////	    	標註結束
////			FundraisingUtil.getTableActionEnd(req, table, action);
////		}
////    }
//
//	    
//	    
//////	    =========================================  新增一個proposal  =========================================
////	    if("Proposal".equals(req.getParameter("table")) && "Insert".equals(req.getParameter("action"))){	    
////    		res.setContentType("text/html; charset=UTF-8");
//////    		單獨列印newProposal頁面upload file資訊
////	    	System.out.println("=== proposalFile ===");
////	    	ProposalBean bean = new ProposalBean();
//////	    	預設會員帳號TGA001
//////	    	標註起始
////	    	FundraisingUtil.getTableActionStart(req, table, action);
//////	    	儲存會員上傳的檔案
////	    	for (Part part : req.getParts()) {
//////	    		newProposal頁面
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
//////	    	執行存入(MySQL)
////	    	ProposalService proposalService = new ProposalService();
////	    	System.out.println("new proposalService");
////	    	try {
//////		    	== 將前端 newProposal頁面 form參數傳入對應的bean資訊 ==
////	    		proposalService.insertBean(bean);
////			} catch (Exception e) {
////			} finally {
////				System.out.println("insertBean");
//////		    	標註結束
////				FundraisingUtil.getTableActionEnd(req, table, action);
////			}
////	    }
//
//	    
//	    
////	    =========================================  新增一個plan  =========================================
////	    if("Plan".equals(req.getParameter("table")) && "Insert".equals(req.getParameter("action"))) {
////	    	
//////    		單獨列印newPlan頁面upload file資訊
////	    	System.out.println("=== planPicture ===");
////	    	String pictureName = req.getPart("planPicture").getSubmittedFileName();
////	    	System.out.println(String.format(template, "planPicture", pictureName));
//////	    	預設會員帳號TGA001
////	    	String memberUploadPath = getServletContext().getRealPath("/") + "uploaded\\proposal\\plan" + "\\" + memberID;
////	    	String memberUploadPicturePath = memberUploadPath + "\\" + pictureName;
//////	    	儲存會員上傳的檔案
////	    	for (Part part : req.getParts()) {
//////	    		newPlan頁面
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
//////	    	標註起始
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
//////	    	執行存入(MySQL)
////	    	PlanService planService = new PlanService();
////	    	System.out.println("new planService");
////	    	try {
////	    		planService.insertBean(bean);
////			} catch (Exception e) {
////			} finally {
////				System.out.println("insertBean");
//////		    	標註結束
////				FundraisingUtil.getTableActionEnd(req, table, action);
////			}
////	    	
////	    }
//	    
//	    
//////	    =========================================  其他(查詢)  =========================================
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
////	    只取一個，按proposal編號
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

}
