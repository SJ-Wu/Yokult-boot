package tibame.tga102.yokult.member.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tibame.tga102.yokult.member.vo.Member;

@SpringBootTest
public class MemberDaoHibernateTests {
	@Test
	void contextLoads() {
		
	}
	
	@Autowired
	private MemberDao memberDao;
	
//	@Test
//	public void testSelectAll() {
//		List<Member> list = memberDao.selectAll();
//		for (Member m:list) {
//			System.out.println(m);
//		}
//	}
	
	@Test
	public void testInsert() {
		// Insert practice.
		Member insert = new Member();
		insert.setMemID("TGA1993");
		insert.setMemPassword("123");
		insert.setMemEmail("abc@gmail.com");
		insert.setMemName("Hou Kevin");
		insert.setMemCellPhone("0912345678");
		Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());	
		insert.setMemBirth(sqlDate);
		Integer result = memberDao.insert(insert);
		System.out.println(result);
	}
	
	@Test
	public void testQuery() {
		String query = "吳";
		System.out.println("========TestQuery========");
		List<Member> list = memberDao.queryByMemberName(query);
		for (Member m: list) {
			System.out.println(m);
		}
	}
}
