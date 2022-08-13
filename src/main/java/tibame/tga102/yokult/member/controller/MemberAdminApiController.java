package tibame.tga102.yokult.member.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tibame.tga102.yokult.member.service.MemberService;
import tibame.tga102.yokult.member.vo.Member;
import tibame.tga102.yokult.util.YokultConstants;

@RestController
@RequestMapping(path = { YokultConstants.MEMBER_ADMIN_API })
public class MemberAdminApiController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping
	public ResponseEntity<?> selectAll() {
		List<Member> members = memberService.getAll();
		if (members != null) {
			ResponseEntity<List<Member>> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
					.body(members);
			return response;
		} else {
			// 204 (No content)
			ResponseEntity<?> response = ResponseEntity.noContent().build();
			return response;
		}
	}
	
	@GetMapping(path = { "/query" })
	public ResponseEntity<?> selectByCondition(String memEmail, String memID, String memName) {
		List<Member> members = memberService.query(memEmail, memID, memName);
		if (members != null) {
			ResponseEntity<List<Member>> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
					.body(members);
			return response;
		} else {
			// 204 (No content)
			ResponseEntity<?> response = ResponseEntity.noContent().build();
			return response;
		}
	}

}
