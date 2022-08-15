//package tibame.tga102.yokult.orderlist.servlet;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.naming.NamingException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonObject;
//
//import tibame.tga102.yokult.orderlist.service.OrderlistService;
//import tibame.tga102.yokult.orderlist.service.impl.OrderlistServiceImpl;
//import tibame.tga102.yokult.orderlist.vo.Orderlist;
//import tibame.tga102.yokult.orderlist.vo.OrderlistView;
//
//
///**
// * Servlet implementation class OrderlistServlet
// */
//@WebServlet("/api/0.01/orderlist/*")
//public class OrderlistServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//	private String pathInfo;
//	private String[] infos;
//	private OrderlistService service;
//
//	// 新增: deprecated
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setCharacterEncoding("UTF-8");
//		setHeaders(response);
//		JsonObject respObject = new JsonObject();
//		Orderlist orderlist = gson.fromJson(request.getReader(), Orderlist.class);
//		System.out.println("orderList:" + orderlist);
//		try {
//			service = new OrderlistServiceImpl();
//			pathInfo = request.getPathInfo();
//			infos = pathInfo.split("/");
//			if ("checkout".equals(infos[1])) {
//				Integer status = service.insertOrderlist(orderlist);
//				if (status > 0) {
//					respObject.addProperty("msg", "success");
//				} else {
//					respObject.addProperty("msg", "fail");
//				}
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		response.getWriter().append(gson.toJson(respObject));
//	}
//
//	
//	// 查詢
//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		response.setCharacterEncoding("UTF-8");
//		setHeaders(response);
//		JsonObject respObject = new JsonObject();
//		
//		String ordId = request.getParameter("ordId");
//		System.out.println("id=="+ordId);
//		try {
//			service = new OrderlistServiceImpl();
//			pathInfo = request.getPathInfo();
//			infos = pathInfo.split("/");
//			OrderlistView orderlistView = new OrderlistView();
//			orderlistView.setOrdid(ordId); //order id
//			List<OrderlistView> returnLists = new ArrayList<OrderlistView>();
//			returnLists = service.searchOrderlistViewByOrdid(orderlistView.getOrdid());
//			if (returnLists.size() > 0) {
//				respObject.addProperty("msg", "success");
//				respObject.add("Orderlists", gson.toJsonTree(returnLists));
//			} else {
//				respObject.addProperty("msg", "fail");
//				System.out.println("Fail: can not find the corresponed orderlists");
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		response.getWriter().append(gson.toJson(respObject));
//	}
//
//
//	// 刪除
//	@Override
//	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setCharacterEncoding("UTF-8");
//		setHeaders(resp);
//		pathInfo = req.getPathInfo();
//		infos = pathInfo.split("/");
//		JsonObject respObject = new JsonObject();
//		
//		if ("delete".equals(infos[1])) {
//			try {
//				OrderlistService service = new OrderlistServiceImpl();
//				Orderlist orderlist = new Orderlist();
//				orderlist.setOrdid(infos[2]);
//				Integer status = service.deleteOrderlist(orderlist);
//				if (status > 0) {
//					respObject.addProperty("msg", "success");
//				} else {
//					respObject.addProperty("msg", "fail");
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			resp.getWriter().append(gson.toJson(respObject));
//		}
//	}
//	
//	// 修改
//	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		setHeaders(resp);
//		pathInfo = req.getPathInfo();
//		infos = pathInfo.split("/");
//		JsonObject respObject = new JsonObject();
//		Orderlist orderlist = gson.fromJson(req.getReader(), Orderlist.class);
//		if ("modify".equals(infos[1])) {
//			try {
//				OrderlistService service = new OrderlistServiceImpl();
//				if (service.modifyOrderlist(orderlist) > 0) {
//					respObject.addProperty("msg", "success");
//				} else {
//					respObject.addProperty("msg", "fail");
//				}
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//			System.out.println(gson.toJson(respObject));
//			resp.getWriter().append(gson.toJson(respObject));
//		}
//	}
//
//
//	/*
//	 * 誇域
//	 */
//	@Override
//	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		setHeaders(resp);
//	}
//
//	private void setHeaders(HttpServletResponse response) {
//		// 重要
//		response.setContentType("application/json;charset=UTF-8");
//		response.setHeader("Cache-control", "no-cache, no-store");
//		response.setHeader("Pragma", "no-cache");
//		response.setHeader("Expires", "-1");
//
//		// 重要
//		response.addHeader("Access-Control-Allow-Origin", "*");
//		response.addHeader("Access-Control-Allow-Methods", "*");
//		response.addHeader("Access-Control-Allow-Headers", "*");
//		response.addHeader("Access-Control-Max-Age", "86400");
//	}
//}
