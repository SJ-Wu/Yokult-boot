package tibame.tga102.yokult.booking.service;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/captcha")
public class BookingCaptcha extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public BookingCaptcha() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("image/jpeg");
		
		String cpassword = String.valueOf((int)(Math.random()*(10000-999))+1000);

		getServletContext().setAttribute("cpassword", cpassword);
		System.out.println("[ServletContext setAttribute cpassword]" + cpassword);
		
		ImageIO.write(
                getPasswordImage(cpassword), 
                "JPG", 
                response.getOutputStream()
            );
	}
	
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
