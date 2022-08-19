package tibame.tga102.yokult.nurse.dao;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.staff.vo.Staff;

@Repository
public class StaffDao {

	@PersistenceContext
	Session session;

	public List<Staff> findAll() {
		return session.createQuery("SELECT a FROM Staff a WHERE staff_id <> 'tga000'", Staff.class).getResultList();
	}

	public Staff selectByStaffId(String staffId) {
		return session.get(Staff.class, staffId);
	}

	public String getMaxId() {
		String selectMaxId = "SELECT staff_id FROM staff order by staff_id desc LIMIT 1";
		return (String) session.createSQLQuery(selectMaxId).list().get(0);
	}

	@Transactional
	public void insert(Staff staff) {
		if (staff != null) {
			session.save(staff);
		}
	}

	@Transactional
	public void update(Staff staff) {
		session.update(staff);
	}

	public void delete(Staff staff) {
		session.delete(session.merge(staff));
	}

}
