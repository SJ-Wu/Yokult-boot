package tibame.tga102.yokult.topic.dao.impl;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.member.vo.Member;
import tibame.tga102.yokult.topic.dao.TopicDao;
import tibame.tga102.yokult.topic.vo.Topic;

@Repository
public class TopicDaoHibernate implements TopicDao {
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}

	@Override
	public Integer insert(Topic topic) {
		this.getSession().save(topic);
		return 1;
	}

	@Override
	public List<Topic> selectAll() {
		return (List<Topic>) this.getSession().createQuery("from Topic", Topic.class).list();
	}

	@Override
	public Integer update(Topic topic) {
		Topic update = (Topic)this.getSession().get(Topic.class, topic.getTopid());
		if (update != null) {
			update.setTitle(topic.getTitle());
			update.setForeword(topic.getForeword());
			update.setContent(topic.getContent());
			update.setViews(topic.getViews());
			update.setPosttime(topic.getPosttime());
			return 1;
		}
		return -1;
		
	}
	
	@Override
	public Integer updateview(Topic topic) {
		System.out.println("UpdateID: " + topic.getTopid() + " view: " + topic.getViews());
		return this.getSession().createQuery(
				"update Topic " + 
				"set views = :newViews " +
				"where topid = :updateId")
				.setParameter("newViews", topic.getViews())
				.setParameter("updateId", topic.getTopid())
				.executeUpdate();
	}
	

	@Override
	public Integer delete(Topic topic) {
		Topic delete = (Topic)this.getSession().get(Topic.class, topic.getTopid());
		if (delete != null) {
			this.getSession().delete(delete);
			return 1;
		}
		return 0;
	}

}
