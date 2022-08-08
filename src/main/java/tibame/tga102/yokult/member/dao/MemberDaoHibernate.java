package tibame.tga102.yokult.member.dao;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.member.vo.Member;

@Repository
public class MemberDaoHibernate implements MemberDao {
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}

	@Override
	public Integer insert(Member member) {
		Member temp = (Member)this.getSession().get(Member.class, member.getMemID());
		if (temp == null) {
			this.getSession().save(member);
			return 1;
		}
		return -1;
	}

	@Override
	public Member selectByMemberID(String account) {
		Member resultMember = (Member)this.getSession().get(Member.class, account);
		return (resultMember != null) ? resultMember : null;
	}
	
	@Override
	public Member selectByMemberIdAndPassword(Member member) {
		Member resultMember = (Member)this.getSession().get(Member.class, member.getMemID());
		if (resultMember != null) {
			if (resultMember.getMemPassword().equals(member.getMemPassword())) {
				return resultMember;
			}
		}
		return null;
	}

	@Override
	public List<Member> selectAll() {
		return (List<Member>) this.getSession().createQuery("from Member", Member.class).list();
	}

	@Override
	public Integer update(Member member) {
		Member update = (Member)this.getSession().get(Member.class, member.getMemID());
		if (update != null) {
			update.setMemEmail(member.getMemEmail());
			update.setMemName(member.getMemName());
			update.setMemCellPhone(member.getMemCellPhone());
			update.setMemBirth(member.getMemBirth());
			update.setMemAddress(member.getMemAddress());
			return 1;
		}
		return -1;
	}

	@Override
	public Integer delete(Member member) {
		Member delete = (Member)this.getSession().get(Member.class, member.getMemID());
		if (delete != null) {
			this.getSession().delete(delete);
			return 1;
		}
		return 0;
	}

	@Override
	public List<Member> selectByMemberName(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member selectByMemberEmail(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Member selectByMemberCellPhone(String string) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer updateStatus(Member member) {
		System.out.println(member);
		return this.getSession().createQuery(
			"update Member " + 
			"set memStatus = :newStatus " +
			"where memID = :updateId")
		.setParameter("newStatus", member.getMemStatus())
		.setParameter("updateId", member.getMemID())
		.executeUpdate();
	}

}
