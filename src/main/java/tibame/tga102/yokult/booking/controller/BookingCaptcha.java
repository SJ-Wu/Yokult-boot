package tibame.tga102.yokult.booking.controller;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tibame.tga102.yokult.util.YokultConstants;
//@WebServlet("/captcha")
@RestController
@RequestMapping(path = {YokultConstants.BOOKING_API})
public class BookingCaptcha extends HttpServlet {
	private static final long serialVersionUID = 1L;
    @Autowired
    ServletContext servletContext;
	
	public BookingCaptcha() {
        super();
    }
    
	@GetMapping(path = {"/bookingcaptcha"})
	public Map<String, Object> getPasswordImage() {
		String cpassword = String.valueOf((int)(Math.random()*(10000-999))+1000);
//		servletContext.setAttribute("cpassword", cpassword);
		System.out.println("[bookingcaptcha cpassword]" + cpassword);
        Map<String, Object> map = new HashedMap<String, Object>();

		Date expireDate = new Date(System.currentTimeMillis() + 20 * 60 * 1000);
		String jwtToken = Jwts.builder().setSubject(cpassword).setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, YokultConstants.JWTKEY).compact();
		System.out.println("cpassword jwtToken: " + jwtToken);
        
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			ImageIO.write(getPasswordImage(cpassword), "jpeg", baos);
			
			 map.put("pic",baos.toByteArray());
			 map.put("cpasswordjwtToken", jwtToken);
			 
			 return map;
			 
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
    }
    
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		response.setContentType("image/jpeg");
//		
//		String cpassword = String.valueOf((int)(Math.random()*(10000-999))+1000);
//
//		getServletContext().setAttribute("cpassword", cpassword);
//		System.out.println("[ServletContext setAttribute cpassword]" + cpassword);
//		
//		ImageIO.write(
//                getPasswordImage(cpassword), 
//                "JPG", 
//                response.getOutputStream()
//            );
//	}
	
	public BufferedImage getPasswordImage(String password) {

		BufferedImage bufferedImage = new BufferedImage(60, 25, BufferedImage.TYPE_INT_RGB);
		Graphics graphics = bufferedImage.getGraphics();
		graphics.setColor(Color.WHITE);
		
		Graphics2D g2d_word = (Graphics2D)graphics;
		AffineTransform trans = new AffineTransform();
		Random random = new Random();
		trans.rotate(random.nextInt(45) * 3.14 / 180, 15 * 1 + 8, 7);
		g2d_word.setTransform(trans);		
		
		graphics.drawString(password, 10, 15);

		return bufferedImage;
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
