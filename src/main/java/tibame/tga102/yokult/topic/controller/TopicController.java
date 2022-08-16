package tibame.tga102.yokult.topic.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tibame.tga102.yokult.member.vo.Member;
import tibame.tga102.yokult.member.vo.MemberResponse;
import tibame.tga102.yokult.topic.service.TopicService;
import tibame.tga102.yokult.topic.vo.Topic;
import tibame.tga102.yokult.util.YokultConstants;

@RestController
@RequestMapping(path= {YokultConstants.TOPIC_API})
public class TopicController {

	@Autowired
	private TopicService topicService;
	
	@GetMapping
	public ResponseEntity<?> selectAll() {
		List<Topic> topics = topicService.selectAll();
		if (topics != null) {
			ResponseEntity<List<Topic>> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
					.body(topics);
			return response;
		} else {
			// 204 (No content)
			ResponseEntity<?> response = ResponseEntity.noContent().build();
			return response;
		}
	}
	
	@PostMapping(path= {"/update"})
	public ResponseEntity<?> update(@RequestBody Topic topic) {
		Integer status = topicService.update(topic);
		if (status > 0) {
			URI uri = URI.create(YokultConstants.TOPIC_API);
			// 201 (Created)
			ResponseEntity<?> response = ResponseEntity.created(uri).build();
			return response;
		} else {
			// 500 (internal server error)
			ResponseEntity<?> response = ResponseEntity.noContent().build();
			return response;
		}
	}
	
	@PutMapping(path="/modify")
	public ResponseEntity<?> modify(@RequestBody Topic topic) {
		System.out.println(topic);
		Integer status = topicService.modify(topic);
		if (status > 0) {
			ResponseEntity<?> response = ResponseEntity.ok().build();
			return response;
		} else {
			ResponseEntity<?> response = ResponseEntity.noContent().build();
			return response;
		}
		
		
	}
	
	
	@PostMapping(path="/updateview")
	public ResponseEntity<?> updateview(@RequestBody Topic topic) {
		Integer status = topicService.updateview(topic);
		if (status > 0) {
			URI uri = URI.create(YokultConstants.TOPIC_API);
			// 201 (Created)
			ResponseEntity<?> response = ResponseEntity.created(uri).build();
			return response;
		} else {
			// 500 (internal server error)
			ResponseEntity<?> response = ResponseEntity.noContent().build();
			return response;
		}
		
		
	}
	
	
}
