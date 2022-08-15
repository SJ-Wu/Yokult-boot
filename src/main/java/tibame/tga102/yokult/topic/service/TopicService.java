package tibame.tga102.yokult.topic.service;

import java.util.List;

import tibame.tga102.yokult.topic.vo.Topic;

public interface TopicService {
	List<Topic> selectAll();
	
	Integer update(Topic topic);
	
	Integer modify(Topic topic);

	Integer remove(Topic topic);
	
	Integer updateview(Topic topic);
}
