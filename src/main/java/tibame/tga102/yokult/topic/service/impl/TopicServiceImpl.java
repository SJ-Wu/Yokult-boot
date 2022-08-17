package tibame.tga102.yokult.topic.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.topic.dao.TopicDao;
import tibame.tga102.yokult.topic.service.TopicService;
import tibame.tga102.yokult.topic.vo.Topic;

@Service
@Transactional
public class TopicServiceImpl implements TopicService {
	@Autowired
	private TopicDao dao;

	@Override
	public List<Topic> selectAll() {
		return dao.selectAll();
	}

	@Override
	public Integer update(Topic topic) {
		return dao.insert(topic);
	}

	@Override
	public Integer modify(Topic topic) {
		// 1. check if there is any null column in the not-null column

		return dao.update(topic);
	}

	@Override
	public Integer remove(Topic topic) {

		return dao.delete(topic);
	}

	@Override
	public Integer updateview(Topic topic) {
		// TODO Auto-generated method stub
		return dao.updateview(topic);
	}

}
