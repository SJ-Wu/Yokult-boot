package tibame.tga102.yokult.booking.controller;
//package web.booking.servlet;
//
//import java.io.IOException;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpFilter;
//import javax.servlet.http.HttpServletRequest;
//
//import com.google.gson.JsonObject;
//
//@WebFilter(urlPatterns = {"/api/0.01/booking/bookingQuery"})
//public class BookingFilter extends HttpFilter implements Filter {
//    public BookingFilter() {
//        super();
//    }
//	public void destroy() {
//	}
//
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//		String cpassword = (String)getServletContext().getAttribute("cpassword");
//		System.out.println("[bookginservlet getAttribute]" + cpassword);
//		System.out.println("[doFilter]"+cpassword);
//		HttpServletRequest req = (HttpServletRequest)request;
//		if(!cpassword.equals(req.getParameter("cinput"))) {
//			return ;
//		}
//		chain.doFilter(request, response);
//		getServletContext().removeAttribute("cpasswordSessionId");
//
//	}
//
//	/**
//	 * @see Filter#init(FilterConfig)
//	 */
//	public void init(FilterConfig fConfig) throws ServletException {
//		// TODO Auto-generated method stub
//	}
//
//}
