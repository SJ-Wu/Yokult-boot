package tibame.tga102.yokult.nurse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tibame.tga102.yokult.nurse.dao.ScheduleDao;
import tibame.tga102.yokult.nurse.vo.ResultSchedule;
import tibame.tga102.yokult.nurse.vo.Schedule;

@Service
public class ScheduleService {

	@Autowired
	private ScheduleDao scheduleDao;

	public List<ResultSchedule> getAll() {

		// 取得班表資料
		List<Schedule> schedules = scheduleDao.selectAll();

		List<ResultSchedule> resultScheduleList = new ArrayList<ResultSchedule>();
		// 拆分成早晚班
		for (Schedule schedule : schedules) {
			// 早班
			ResultSchedule resultSchedule = new ResultSchedule();
			resultSchedule.setSchedule_date(schedule.getSchedule_date());
			resultSchedule.setWorkStaff(schedule.getMorning_shift());
			resultSchedule.setDayOffstaff1(schedule.getDay_off1());
			resultSchedule.setDayOffstaff2(schedule.getDay_off2());
			resultSchedule.setApm("a");
			resultScheduleList.add(resultSchedule);
			// 晚班
			resultSchedule = new ResultSchedule();
			resultSchedule.setSchedule_date(schedule.getSchedule_date());
			resultSchedule.setWorkStaff(schedule.getNight_shift());
			resultSchedule.setDayOffstaff1(schedule.getDay_off1());
			resultSchedule.setDayOffstaff2(schedule.getDay_off2());
			resultSchedule.setApm("p");
			resultScheduleList.add(resultSchedule);
		}
		return resultScheduleList;
	}

}
