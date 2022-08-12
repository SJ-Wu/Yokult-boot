package tibame.tga102.yokult.member.controller;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

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

@CrossOrigin
@RestController
@RequestMapping(path = { "/api/0.02/member" })
public class MemberApiController {

	@Autowired
	private MemberService memberService;
	@Autowired
	private MemberResponse memberResponse;
	@Autowired
	private Member member;
	@Autowired
	private HttpServletRequest request;

	@GetMapping
	public ResponseEntity<?> selectAll(){
		List<Member> members = memberService.getAll();
		if (members != null) {
			ResponseEntity<List<Member>> response = ResponseEntity
					.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(members);
			return response;
		} else {
			// 204 (No content)
			ResponseEntity<?> response = ResponseEntity
					.noContent()
					.build();
			return response;
		}
	}
	
	@GetMapping(path= {"/{id}"})
	public ResponseEntity<?> getMemberInfo(@PathVariable(name="id") String memID){
		Member info = memberService.getOneByID(memID);
		System.out.println(info);
		if (info != null) {
			ResponseEntity<Member> response = ResponseEntity
					.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(info);
			return response;
		} else {
			// 204 (No content)
			ResponseEntity<?> response = ResponseEntity
					.noContent()
					.build();
			return response;
		}
	}
	
	@GetMapping(path= {"/query"})
	public ResponseEntity<?> selectByCondition(String memEmail, String memID, String memName) {
//		System.out.println(request.getRequestURI());
//		System.out.println(memEmail);
//		System.out.println(memID);
//		System.out.println(memName);
		List<Member> members = memberService.query(memEmail, memID, memName);
//		System.out.println(members);
		if (members != null) {
			ResponseEntity<List<Member>> response = ResponseEntity
					.ok()
					.contentType(MediaType.APPLICATION_JSON)
					.body(members);
			return response;
		} else {
			// 204 (No content)
			ResponseEntity<?> response = ResponseEntity
					.noContent()
					.build();
			return response;
		}
	}
	
	@PostMapping(path = { "/login" })
	public ResponseEntity<?> login(@RequestBody Member member) {
		String jwtToken = memberService.login(member);
		memberResponse.setMsg(jwtToken);
		if (jwtToken != null) {
			// 201 (Created)
			ResponseEntity<MemberResponse> response = ResponseEntity
					.ok()
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
	public ResponseEntity<?> modify(@RequestBody Member member) {
		System.out.println(member);
		Integer status = memberService.modify(member);
		if (status > 0) {
			memberResponse.setMsg("success");
		} else {
			memberResponse.setMsg("fail");
		}
		ResponseEntity<MemberResponse> response = ResponseEntity
				.ok()
				.contentType(MediaType.APPLICATION_JSON)
				.body(memberResponse);
		return response;
	}
}
