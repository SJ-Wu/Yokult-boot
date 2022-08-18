package tibame.tga102.yokult.nurse.dao;

import java.util.List;

import javax.persistence.PersistenceContext;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.nurse.vo.Schedule;
import tibame.tga102.yokult.nurse.vo.ShiftSchedule;
import tibame.tga102.yokult.staff.vo.Staff;

@Repository
public class ShiftScheduleDao {

	@PersistenceContext
	Session session;

	public List<ShiftSchedule> selectAll() {
		return session.createQuery("SELECT a FROM ShiftSchedule a", ShiftSchedule.class).getResultList();
	}

	public List<ShiftSchedule> selectShiftScheduleByStaffId(String staffId) {
		String selectShiftSchedule = " SELECT ss.schedule_date, concat(ss.staff_id ,'_' , st.staff_name) as staff_id, "
				+ "shiftschedule_type_of_leave, shiftschedule_morningshift_nightshift, row_num "
				+ "FROM shift_schedule ss left join staff st on ss.staff_id = st.staff_id WHERE ss.staff_id='" + staffId + "' ";
		return session.createNativeQuery(selectShiftSchedule, ShiftSchedule.class).getResultList();
	}

	public List<ShiftSchedule> selectAllShiftSchedule(String staffId) {
		String selectShiftSchedule = " SELECT ss.schedule_date, concat(ss.staff_id ,'_' , st.staff_name) as staff_id, "
				+ "shiftschedule_type_of_leave, shiftschedule_morningshift_nightshift, row_num "
				+ "FROM shift_schedule ss left join staff st on ss.staff_id = st.staff_id";
		return session.createNativeQuery(selectShiftSchedule, ShiftSchedule.class).getResultList();
	}

	public List<ShiftSchedule> selectShiftScheduleByDate(String date) {
		String selectShiftScheduleData = "SELECT * FROM shift_schedule WHERE schedule_date like '" + date + "'";
		return session.createNativeQuery(selectShiftScheduleData, ShiftSchedule.class).getResultList();
	}

	public Staff selectByStaffId(String staffId) {
		return session.get(Staff.class, staffId);
	}

	@Transactional
	public void insert(ShiftSchedule shiftSchedule) {
		session.save(shiftSchedule);

	}

	@Transactional
	public Integer delete(String staffId, String scheduleDate) {
		String deleteAll = "DELETE FROM shift_schedule where staff_id ='" + staffId + "' and schedule_date like '"
				+ scheduleDate + "'";
		return session.createNativeQuery(deleteAll).executeUpdate();
	}
	
	@Transactional
	public Integer deleteAll(String scheduleDate) {
		String deleteAll = "DELETE FROM shift_schedule where schedule_date like '" + scheduleDate + "'";
		return session.createNativeQuery(deleteAll).executeUpdate();
	}

	public Integer getMaxRow() {
		String selectMaxId = "SELECT row_num FROM shift_schedule order by row_num desc LIMIT 1";
		List<?> list = session.createSQLQuery(selectMaxId).list();
		if (CollectionUtils.isEmpty(list)) {
			return 0;
		}
		return (Integer) list.get(0);
	}

	public Integer queryWorkDayCount(String staffId, String firstDate, String lastDate) {
		String selectWorkDayCount = "SELECT count(*) FROM schedule " + "where (morning_shift = '" + staffId
				+ "' or night_shift = '" + staffId + "') " + "and schedule_date BETWEEN '" + firstDate + "' AND '"
				+ lastDate + "'";
		Query<?> query = session.createSQLQuery(selectWorkDayCount);

		return ((Number) query.uniqueResult()).intValue();

	}

	@Transactional
	public void insertSchedule(Schedule schedule) {
		session.save(schedule);

	}

	@Transactional
	public Integer deleteSchedule(String scheduleDate) {
		String deleteAll = "DELETE FROM schedule where schedule_date like '" + scheduleDate + "'";
		return session.createNativeQuery(deleteAll).executeUpdate();
	}
}
