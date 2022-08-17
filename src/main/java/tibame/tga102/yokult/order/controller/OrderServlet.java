package tibame.tga102.yokult.order.controller;
//package tibame.tga102.yokult.order.servlet;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.List;
//
//import javax.naming.NamingException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.google.gson.JsonObject;
//import com.google.gson.reflect.TypeToken;
//
//import web.order.service.OrderService;
//import web.order.service.OrderServiceImpl;
//import web.order.vo.Order;
//
///**
// * Servlet implementation class OrderServlet
// */
//@WebServlet("/api/0.01/order/*")
//public class OrderServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//	private String pathInfo;
//	private String[] infos;
//    
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		
//		Gson gson = new Gson();
//		JsonObject respObject = new JsonObject();
//		setHeaders(resp);
//		
//		//回傳全部訂單
//		try {
//			OrderService service = new OrderServiceImpl();
//			List<Order> orders = service.searchOrders();
//			if (orders != null) {
//				respObject.addProperty("msg", "success");
//				respObject.add("orders", gson.toJsonTree(orders));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		resp.getWriter().append(gson.toJson(respObject));
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		pathInfo = request.getPathInfo();
//		infos = pathInfo.split("/");
//		//開啟跨網域，html才能接收到servlet傳出的東西
//		setHeaders(response);
//		BufferedReader br = request.getReader();
//		PrintWriter out = response.getWriter();
//
////依據訂單編號回傳一筆訂單 /api/0.01/order/selectOrderid
//		if ("selectOrderid".equals(infos[1])) {
//			Order order = gson.fromJson(br, Order.class);
//			JsonObject jsonObject = new JsonObject();
//			try {
//				OrderService orderService = new OrderServiceImpl();
//				Order vo = orderService.selectOrderid(order.getOrdid());
//				if (vo == null) {
//					jsonObject.addProperty("msg", "no this order");
//				} else {
//					jsonObject.addProperty("msg", "success");
//					jsonObject.add("order", gson.toJsonTree(vo));
//				}
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//			out.append(gson.toJson(jsonObject));
//			br.close();
//			out.close();
//			
//			return;
//依據類別回傳多筆訂單 /api/0.01/order/selectOrderStatus
//		} else if("selectOrderStatus".equals(infos[1])) {
//			Order order = gson.fromJson(br, Order.class);
//			JsonObject jsonObject = new JsonObject();
//			try {
//				OrderService orderService = new OrderServiceImpl();
//				List<Order> list = orderService.selectOrderStatus(order.getOrderstatus());
//				if (list == null) {
//					jsonObject.addProperty("msg", "no this order");
//				} else {
//					jsonObject.addProperty("msg", "success");
//					jsonObject.add("order", gson.toJsonTree(list, new TypeToken<List<Order>>() {}.getType())); //google
//				}
//			} catch (NamingException e) {
//				e.printStackTrace();
//			}
//			out.append(gson.toJson(jsonObject));
//			br.close();
//			out.close();
//			
//			return;
//			
//			
//			
//		}
//	}

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
