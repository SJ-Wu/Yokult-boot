package tibame.tga102.yokult.staff.service;

import java.util.Date;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import tibame.tga102.yokult.staff.dao.StaffDao;
import tibame.tga102.yokult.staff.vo.Staff;
import tibame.tga102.yokult.util.YokultConstants;

@Service
@Transactional
public class StaffServiceImpl implements StaffService{
	@Autowired
	private StaffDao dao;
	
	@Override
	public String login(Staff staff) {
		String jwtToken = null;
		String account = staff.getStaff_id();
		String password = staff.getStaff_idnumber();
//			System.out.println(account + " " + password);
		if (!checkValue(account) || !checkValue(password)) {
			System.out.println("帳號或密碼錯誤");
			return null;
		}
		staff = dao.selectByStaffIdAndPassword(staff);
		if (staff != null) {
			Date expireDate = new Date(System.currentTimeMillis() + 30 * 60 * 1000);
			jwtToken = Jwts.builder().setSubject(staff.getStaff_id()).setExpiration(expireDate)
					.signWith(SignatureAlgorithm.HS512, YokultConstants.JWTKEY).compact();
			System.out.println("jwtToken: " + jwtToken);
		}
		return jwtToken;
		
	}
	private boolean checkValue(String value) {
		if (value == null || Objects.equals(value, "")) {
			System.out.println(value);
			return false;
		}
		return true;
	}
}
