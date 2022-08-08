package tibame.tga102.yokult.member.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tibame.tga102.yokult.member.service.MemberService;
import tibame.tga102.yokult.member.vo.Member;

@CrossOrigin
@RestController
@RequestMapping(path = { "/api/0.01/member" })
public class MemberApiController {

	@Autowired
	private MemberService memberService;

	@PostMapping(path = { "/login" })
	public void login(@RequestBody Member member) {
		Member login = memberService.login(member);
		System.out.println(login);
	}
}
