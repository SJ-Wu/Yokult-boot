package tibame.tga102.yokult.member.service;

import java.util.List;

import tibame.tga102.yokult.member.vo.Member;

public interface MemberService {
	Member getOneByID(String account);
	
	List<Member> getAll();
	
	Integer register(Member member);

	String login(Member member);

	Integer modify(Member member);

	Integer remove(Member member);
	
	List<Member> query(String memEmail, String memID, String memName);
	
	boolean emailVerification(String code, Member member);
}
