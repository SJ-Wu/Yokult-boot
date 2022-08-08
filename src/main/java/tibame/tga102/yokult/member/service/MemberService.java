package tibame.tga102.yokult.member.service;

import java.util.List;
import java.util.Map;

import tibame.tga102.yokult.member.vo.Member;

public interface MemberService {
	Member getOneByID(String account);
	
	List<Member> getAll();
	
	Integer register(Member member);

	Member login(Member member);

	Integer modify(Member member);

	Integer remove(Member member);
	
	List<Member> searchByType(Map<String, String>map);
	
	boolean emailVerification(String code, Member member);
}
