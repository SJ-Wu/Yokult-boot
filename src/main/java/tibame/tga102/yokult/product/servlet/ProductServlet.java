//package web.product.servlet;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.Set;
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
//import web.member.service.MemberService;
//import web.member.service.impl.MemberServiceImpl;
//import web.member.vo.Member;
//import web.product.service.ProductService;
//import web.product.service.impl.ProductServiceImpl;
//import web.product.vo.Product;
//
//@WebServlet("/api/0.01/product/*")
//public class ProductServlet extends HttpServlet {
//	private static final long serialVersionUID = 1L;
//	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
////	private String pathInfo;
////	private String[] infos;
//	private ProductService service;
//
//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		resp.setCharacterEncoding("UTF-8");
//		setHeaders(resp);
//		JsonObject respObject = new JsonObject();
//
//		String category = req.getParameter("category");
//		String productName = req.getParameter("productName");
//
//		System.out.println(category);
//		try {
//			service = new ProductServiceImpl();
//			List<Product> products = service.getAll(category, productName);
//			if (products != null) {
//				respObject.addProperty("msg", "success");
//				respObject.add("products", gson.toJsonTree(products));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		resp.getWriter().append(gson.toJson(respObject));
//	}
//
//	@Override
//	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		req.setCharacterEncoding("UTF-8");
//		setHeaders(resp);
//		Product product = gson.fromJson(req.getReader(), Product.class);
//		System.out.println(product.toString());
//
//		try {
//			service = new ProductServiceImpl();
//			service.save(product);
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//
//		resp.getWriter().append(gson.toJson(""));
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
//
//}
