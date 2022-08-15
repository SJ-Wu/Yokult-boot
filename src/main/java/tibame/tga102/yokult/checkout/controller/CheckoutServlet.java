//package tibame.tga102.yokult.checkout.controller;
//
//import java.io.IOException;
//import java.io.UnsupportedEncodingException;
//
//import javax.naming.NamingException;
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.gson.Gson;
//import com.google.gson.JsonObject;
//
//import tibame.tga102.yokult.checkout.service.CheckoutService;
//import tibame.tga102.yokult.checkout.service.CheckoutServiceImpl;
//import tibame.tga102.yokult.checkout.vo.Checkout;
//
//@WebServlet("/Checkout")
//public class CheckoutServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private Gson gson = new Gson();
//	private String pathInfo;
//	private String[] infos;
//	private CheckoutService service;
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		setHeaders(req, resp);
//		req.setCharacterEncoding("UTF-8");
//		Checkout checkout = gson.fromJson(req.getReader(), Checkout.class);
//		System.out.println("[servlet checkout]" + checkout);
//		JsonObject respObject = new JsonObject();
//		try {
//			service = new CheckoutServiceImpl();
//			respObject.addProperty("msg", service.processCheckout(checkout));
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//		resp.getWriter().append(gson.toJson(respObject));
//	}
//
//	/*
//	 * 誇域
//	 */
//	@Override
//	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		setHeaders(req, resp);
//	}
//
//	private void setHeaders(HttpServletRequest req, HttpServletResponse resp) throws UnsupportedEncodingException {
//		req.setCharacterEncoding("UTF-8");
//		// 重要
//		resp.setContentType("application/json;charset=UTF-8");
//		resp.setHeader("Cache-control", "no-cache, no-store");
//		resp.setHeader("Pragma", "no-cache");
//		resp.setHeader("Expires", "-1");
//
//		// 重要
//		resp.addHeader("Access-Control-Allow-Origin", "*");
//		resp.addHeader("Access-Control-Allow-Methods", "*");
//		resp.addHeader("Access-Control-Allow-Headers", "*");
//		resp.addHeader("Access-Control-Max-Age", "86400");
//	}
//}