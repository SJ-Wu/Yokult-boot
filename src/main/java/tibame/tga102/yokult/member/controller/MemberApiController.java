package tibame.tga102.yokult.member.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tibame.tga102.yokult.member.service.MemberService;
import tibame.tga102.yokult.member.vo.Member;
import tibame.tga102.yokult.member.vo.MemberResponse;


@RestController
@RequestMapping(path = { "/api/0.02/member" })
public class MemberApiController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberResponse memberResponse;
	@Autowired
	private Member member;

	@PostMapping(path = { "/login" })
	@CrossOrigin
	public ResponseEntity<?> login(@RequestBody Member member) {
		Member login = memberService.login(member);
		memberResponse.setMember(login);
		memberResponse.setMsg("success");
		if (login != null) {
			URI uri = URI.create("/api/0.02/member/"+login.getMemID());
			// 201 (Created)
			ResponseEntity<MemberResponse> response = ResponseEntity
					.created(uri)
					.contentType(MediaType.APPLICATION_JSON)
					.body(memberResponse);
			return response;
		} else {
			// 204 (No content)
			ResponseEntity<?> response = ResponseEntity
					.noContent()
					.build();
			return response;
		}
	}
	
	@PostMapping(path= {"/register"})
	@CrossOrigin
	public ResponseEntity<?> register(@RequestBody Member member) {
		if (memberService.getOneByID(member.getMemID()) != null) {
			String json = "{'message': 'repeated'}";
			// 200
			ResponseEntity<?> response = ResponseEntity
					.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(json);
			return response;
		}
		Integer status = memberService.register(member);
		if (status > 0) {
			String json = "{'message': 'success'}";
			URI uri = URI.create("/api/0.02/member/");
			// 201 (Created)
			ResponseEntity<String> response = ResponseEntity
					.created(uri)
					.contentType(MediaType.APPLICATION_JSON)
					.body(json);
			return response;
		} else {
			String json = "{'message': 'server failure'}";
			// 500 (internal server error)
			ResponseEntity<String> response = ResponseEntity
					.internalServerError()
					.contentType(MediaType.APPLICATION_JSON)
					.body(json);
			return response;
		}
	}
	
	@GetMapping(path= {"/verify"})
	@CrossOrigin
	public ResponseEntity<?> verify(String code, String memID){
		member.setMemID(memID);
		if (memberService.emailVerification(code, member)) {
			memberResponse.setMsg("verification pass!");
		} else {
			memberResponse.setMsg("verification fail!");
		}
		ResponseEntity<MemberResponse> response = ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(memberResponse);
		return response;
	}
	
	@DeleteMapping(path= {"/delete/{id}"})
	@CrossOrigin
	public ResponseEntity<?> delete(@PathVariable(name="id") String memID) {
		member.setMemID(memID);
		Integer status = memberService.remove(member);
		if (status > 0) {
			memberResponse.setMsg("member removed!");
		} else {
			memberResponse.setMsg("member removed fail!");
		}
		ResponseEntity<MemberResponse> response = ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(memberResponse);
		return response;
	}
	
	@PutMapping(path= {"/modify"})
	@CrossOrigin
	public ResponseEntity<?> modify(@RequestBody Member member) {
		System.out.println(member);
		Integer status = memberService.modify(member);
		if (status > 0) {
			memberResponse.setMsg("member modified");
		} else {
			memberResponse.setMsg("member modified failed");
		}
		ResponseEntity<MemberResponse> response = ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(memberResponse);
		return response;
	}
}
