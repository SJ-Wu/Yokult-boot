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
			if (member.getMemPassword().equals(resultMember.getMemPassword())) {
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
	public Integer updateStatus(Member member) {
		return this.getSession().createQuery(
			"update Member " + 
			"set memStatus = :newStatus " +
			"where memID = :updateId")
		.setParameter("newStatus", member.getMemStatus())
		.setParameter("updateId", member.getMemID())
		.executeUpdate();
	}

	@Override
	public List<Member> queryByMemberID(String memID) {
		String query = "%"+memID+"%";
		String hql = "from Member where memID like :query ";
		return this.getSession().createQuery(hql, Member.class).setParameter("query", query).list();
	}

	@Override
	public List<Member> queryByMemberEmail(String memEmail) {
		String query = "%"+memEmail+"%";
		String hql = "from Member where memEmail like :query ";
		return this.getSession().createQuery(hql, Member.class).setParameter("query", query).list();
	}
	
	@Override
	public List<Member> queryByMemberName(String memName) {
		String query = "%"+memName+"%";
		String hql = "from Member where memName like :query ";
		return this.getSession().createQuery(hql, Member.class).setParameter("query", query).list();
	}

}
