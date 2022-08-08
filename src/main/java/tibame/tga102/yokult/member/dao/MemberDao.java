package tibame.tga102.yokult.member.dao;

import java.util.List;

import tibame.tga102.yokult.member.vo.Member;

public interface MemberDao {
	Integer insert(Member member);

	Integer delete(Member member);

	Integer update(Member member);

	List<Member> selectAll();

	List<Member> selectByMemberName(String string);

	Member selectByMemberID(String account);

	Member selectByMemberIdAndPassword(Member member);

	Member selectByMemberEmail(String string);

	Member selectByMemberCellPhone(String string);
	
    Integer updateStatus(Member member);

}
