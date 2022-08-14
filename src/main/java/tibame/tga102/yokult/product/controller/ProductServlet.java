package tibame.tga102.yokult.product.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import tibame.tga102.yokult.product.service.ProductService;
import tibame.tga102.yokult.product.service.impl.ProductServiceImpl;
import tibame.tga102.yokult.product.vo.Product;

@Controller
@CrossOrigin
@ResponseBody
@RequestMapping(path = {"/api/0.01/product"})
//@WebServlet("/api/0.01/product/*")
public class ProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
//	private Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
//	private String pathInfo;
//	private String[] infos;
	
	@Autowired
	private ProductService service;

	@GetMapping
	public Map<String, Object> getAll(@RequestParam String category, @RequestParam String productName){
		
		List<Product> products = null;
		Map<String, Object> respObject = new HashedMap<String, Object>();
		try {
			products = service.getAll(category, productName);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (products != null) {
			respObject.put("msg","success");
			respObject.put("products", products);
		}
		return respObject;
	}
	
	@PostMapping(path = {""})
	public void save(@RequestBody Product product){
		try {
			service.save(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	@PostMapping(path= {"/uploadImage"})

//

	@RequestMapping(value = "/uploadImage")
	public void imagesUploaded(@RequestBody Product product) {
		System.out.println(product);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		JsonObject respObject = new JsonObject();
		String pathInfo = req.getPathInfo();
		System.out.println(pathInfo);
		if (pathInfo != null && pathInfo != "") {
			String[] infos = pathInfo.split("/");
			for (String info: infos) {
				System.out.println(info);
			}
			if ("uploadImage".equals(infos[1])) {
				final String saveDirectory = "/images_uploaded";
				req.setCharacterEncoding("UTF-8");
				String realpath = getServletContext().getRealPath(saveDirectory);
				File fSaveDirectory = new File(realpath);
				if (!fSaveDirectory.exists()) {
					fSaveDirectory.mkdir();
				}
				String imagePath = "";
				Collection<Part> parts = req.getParts();
				for (Part part : parts) {
					SimpleDateFormat sDateFormat = new SimpleDateFormat("yyMMddHHmmss");
					String filename = sDateFormat.format(new Date()) + "_" + part.getSubmittedFileName();
					File f = new File(fSaveDirectory, filename);
					part.write(f.toString());
					imagePath = req.getContextPath()+ saveDirectory + "/" + filename;
					System.out.println("image path: " + imagePath);
				}

				respObject.addProperty("msg", "success");
				respObject.addProperty("proPicture", imagePath);
			}
		}
		else {
			Product product = gson.fromJson(req.getReader(), Product.class);
			try {
				service = new ProductServiceImpl();
				Integer status = service.add(product);
				if (status > 0) {
					respObject.addProperty("msg", "success");
				}
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
		resp.getWriter().append(gson.toJson(respObject));
	}

	/*
	 * 誇域
	 */
//	@Override
//	protected void doOptions(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//		setHeaders(resp);
//	}

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

}
