package tibame.tga102.yokult.topic.dao;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import tibame.tga102.yokult.topic.vo.Topic;

@SpringBootTest
public class TopicDaoHibernateTests {

	@Autowired
	private TopicDao topicDao;

	@Test
	public void testInsert() {
		System.out.println("========TestRegister========");
		Topic insert = new Topic();
		insert.setTopid(999);
		insert.setTitle("123");
		insert.setForeword("abc@gmail.com");
		insert.setContent("Hou Kevin");
		insert.setSortid(100);
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		insert.setPosttime(ts);
		Integer result = topicDao.insert(insert);
		System.out.println(result);
	}

	@Test
	public void testSelectAll() {
		System.out.println("========TestListAll========");
		List<Topic> list = topicDao.selectAll();
		for (Topic m : list) {
			System.out.println(m);
		}
	}
	
	@Test
	public void testdelete() {

		System.out.println("=======Testdelete");
		
	}
	
	@Test
	public void testupdateview() {
		System.out.println("=======testupdateview");
		Topic updateview = new Topic();
		updateview.setTopid(5);
		updateview.setViews(999);
		Integer viewresult = topicDao.updateview(updateview);
		System.out.println("view: " + viewresult);
	}
	
	
	
	
}
