package tibame.tga102.yokult.member.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import tibame.tga102.yokult.member.service.MemberService;
import tibame.tga102.yokult.member.vo.Member;
import tibame.tga102.yokult.member.vo.MemberResponse;
import tibame.tga102.yokult.util.YokultConstants;

@RestController
@RequestMapping(path = { YokultConstants.MEMBER_API })
public class MemberApiController {

	@Autowired  
	private MemberService memberService;

	@GetMapping(path = { "/{id}" })
	public ResponseEntity<?> getMemberInfo(@PathVariable(name = "id") String memID) {
		Member info = memberService.getOneByID(memID);
		System.out.println(info);
		if (info != null) {
			ResponseEntity<Member> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(info);
			return response;
		} else {
			// 204 (No content)
			ResponseEntity<?> response = ResponseEntity.noContent().build();
			return response;
		}
	}

	@PostMapping(path = { "/login" })
	public ResponseEntity<?> login(@RequestBody Member member) {
		MemberResponse memberResponse = new MemberResponse();
		String jwtToken = memberService.login(member);
		memberResponse.setMsg(jwtToken);
		if (jwtToken != null) {
			// 201 (Created)
			ResponseEntity<MemberResponse> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
					.body(memberResponse);
			return response;
		} else {
			// 204 (No content)
			ResponseEntity<?> response = ResponseEntity.noContent().build();
			return response;
		}
	}

	@PostMapping(path = { "/register" })
	public ResponseEntity<?> register(@RequestBody Member member) {
		MemberResponse memberResponse = new MemberResponse();
		String msg = this.registerValidation(member);
		if ("Pass".equals(msg)) {
			Integer status = memberService.register(member);
			if (status > 0) {
				memberResponse.setMsg("success");
				URI uri = URI.create(YokultConstants.MEMBER_API);
				// 201 (Created)
				ResponseEntity<MemberResponse> response = ResponseEntity.created(uri)
						.contentType(MediaType.APPLICATION_JSON).body(memberResponse);
				return response;
			} else {
				memberResponse.setMsg("internal error");
				// 500 (internal server error)
				ResponseEntity<MemberResponse> response = ResponseEntity.internalServerError()
						.contentType(MediaType.APPLICATION_JSON).body(memberResponse);
				return response;
			}
		} else {
			memberResponse.setMsg(msg);
			ResponseEntity<MemberResponse> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
					.body(memberResponse);
			return response;
		}
	}

	@GetMapping(path = { "/verify/{token}" })
	public ResponseEntity<?> verify(@PathVariable(name = "token") String token) {
		MemberResponse memberResponse = new MemberResponse();
		Member member = new Member();
		try {
			Claims claims = Jwts.parser().setSigningKey(YokultConstants.JWTKEY).parseClaimsJws(token).getBody();
			System.out.println("JWT payload:" + claims.toString());
			String memID = claims.getSubject();
			member.setMemID(memID);
			if (memberService.emailVerification(member)) {
				memberResponse.setMsg("verification pass!");
			} else {
				memberResponse.setMsg("verification fail!");
			}
		} catch (Exception e) {
			memberResponse.setMsg("verification fail!");
		}
		ResponseEntity<MemberResponse> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(memberResponse);
		return response;
	}

	@DeleteMapping(path = { "/delete/{id}" })
	public ResponseEntity<?> delete(@PathVariable(name = "id") String memID) {
		MemberResponse memberResponse = new MemberResponse();
		Member member = new Member();
		member.setMemID(memID);
		Integer status = memberService.remove(member);
		if (status > 0) {
			memberResponse.setMsg("member removed!");
		} else {
			memberResponse.setMsg("member removed fail!");
		}
		ResponseEntity<MemberResponse> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(memberResponse);
		return response;
	}

	@PutMapping(path = { "/modify" })
	public ResponseEntity<?> modify(@RequestBody Member member) {
		MemberResponse memberResponse = new MemberResponse();
		System.out.println(member);
		Integer status = memberService.modify(member);
		if (status > 0) {
			memberResponse.setMsg("success");
		} else {
			memberResponse.setMsg("fail");
		}
		ResponseEntity<MemberResponse> response = ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON)
				.body(memberResponse);
		return response;
	}

	private String registerValidation(Member member) {
		if (memberService.query(null, member.getMemID(), null).size() != 0) {
			return "Invalid member account";
		} else if (memberService.query(member.getMemEmail(), null, null).size() != 0) {
			return "Invalid member email";
		}
		return "Pass";
	}
}
