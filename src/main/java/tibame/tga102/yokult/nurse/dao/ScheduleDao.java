package tibame.tga102.yokult.nurse.dao;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.nurse.vo.Schedule;
import tibame.tga102.yokult.staff.vo.Staff;
@Repository
public class ScheduleDao {
	@PersistenceContext
	Session session;
	
	final String SELECTALL = "SELECT sc.schedule_date, st1.staff_name as morning_shift, st2.staff_name as night_shift, st3.staff_name as day_off1, st4.staff_name as day_off2 FROM schedule sc "
							+ "join staff st1 on sc.morning_shift = st1.staff_id "
							+ "join staff st2 on sc.night_shift = st2.staff_id "
							+ "left join staff st3 on sc.day_off1 = st3.staff_id "
							+ "left join staff st4 on sc.day_off2 = st4.staff_id ";

	public List<Schedule> selectAll() {
		return session.createNativeQuery(SELECTALL, Schedule.class).getResultList();
	}


	public Integer insert(Schedule schedule) {
		return -1;
	}
	
	public Integer update(Schedule schedule) {
		return -1;
	}

	
	public Staff selectByStaffId(String StaffId) {
		return null;
	}

}
