package tibame.tga102.yokult.topic.dao;

import java.util.List;

import tibame.tga102.yokult.topic.vo.Topic;

public interface TopicDao {
	Integer insert (Topic topic);
	List<Topic> selectAll();
	Integer update(Topic topic);
	Integer delete(Topic topic);
	Integer updateview(Topic topic);
	
}
