package tibame.tga102.yokult.staff.dao;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.staff.vo.Staff;

@Repository
public class StaffDaoHibernate implements StaffDao{
	@PersistenceContext
	private Session session;

	public Session getSession() {
		return this.session;
	}
	
	@Override
	public Staff selectByStaffIdAndPassword(Staff staff) {
		Staff resultStaff = (Staff)this.getSession().get(Staff.class, staff.getStaff_id());
		if (resultStaff != null) {
			if (staff.getStaff_idnumber().equals(resultStaff.getStaff_idnumber())) {
				return resultStaff;
			}
		}
		return null;
	}

}
