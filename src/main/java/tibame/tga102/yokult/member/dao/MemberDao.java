package tibame.tga102.yokult.member.dao;

import java.util.List;

import tibame.tga102.yokult.member.vo.Member;

public interface MemberDao {
	Integer insert(Member member);

	Integer delete(Member member);

	Integer update(Member member);

	List<Member> selectAll();

	List<Member> queryByMemberID(String memID);
	
	List<Member> queryByMemberEmail(String memEmail);
	
	List<Member> queryByMemberName(String memName);

	Member selectByMemberID(String account);

	Member selectByMemberIdAndPassword(Member member);
	
    Integer updateStatus(Member member);

}
