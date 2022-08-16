package tibame.tga102.yokult.nurse.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tibame.tga102.yokult.nurse.dao.ShiftScheduleDao;
import tibame.tga102.yokult.nurse.dao.StaffDao;
import tibame.tga102.yokult.nurse.vo.Schedule;
import tibame.tga102.yokult.nurse.vo.ShiftSchedule;
import tibame.tga102.yokult.nurse.vo.ShiftScheduleReq;
import tibame.tga102.yokult.staff.vo.Staff;

@Service
public class ShiftScheduleService {

	@Autowired
	private ShiftScheduleDao shiftScheduleDao;
	@Autowired
	private StaffDao staffDao;

	public List<ShiftSchedule> getAll() {
		return shiftScheduleDao.selectAll();
	}

	// 取id 拿畫假資料
	public Map<String, Object> getStaffData(String StaffId) {
		if (StringUtils.isNotBlank(StaffId)) {
			Map<String, Object> map = new HashMap<String, Object>();
			if (!"tga000".equals(StaffId)) {
				map.put("shiftSchedule", shiftScheduleDao.selectShiftScheduleByStaffId(StaffId));
				map.put("staff", shiftScheduleDao.selectByStaffId(StaffId));
			} else {
				map.put("shiftSchedule", shiftScheduleDao.selectAllShiftSchedule(StaffId));
				map.put("staff", shiftScheduleDao.selectByStaffId(StaffId));
			}
			return map;
		}
		return null;
	}

	// 新增
	public String insert(ShiftScheduleReq shiftScheduleReq) {
		String staffId = shiftScheduleReq.getStaffId();
		List<String> eventIds = shiftScheduleReq.getEventId();
		String annual = shiftScheduleReq.getAnnual();
		String personal = shiftScheduleReq.getPersonal();
		String official = shiftScheduleReq.getOfficial();
		Staff leave = staffDao.selectByStaffId(staffId);
		leave.setAnnual_leave(annual);
		leave.setPersonal_leave(personal);
		leave.setOfficial_leave(official);
		leave.setStaff_id(staffId);
		staffDao.update(leave);

		System.out.println(staffId);
		System.out.println(eventIds);
		// 刪除員工當月整批畫假資料
		deleteData(staffId);
		ShiftSchedule shiftSchedule = new ShiftSchedule();
		Integer maxRow = shiftScheduleDao.getMaxRow();
		for (String evenId : eventIds) {
			try {
				if (checkInsertDate(evenId)) {
					maxRow++;
					shiftSchedule = handleEventIdString(staffId, evenId, maxRow);
					// 新增
					shiftScheduleDao.insert(shiftSchedule);
				}
			} catch (ParseException e) {
				e.printStackTrace();
				return "fail";
			}
		}
		return "success";

	}

//刪除
	private void deleteData(String staffId) {

		if (StringUtils.isNotBlank(staffId)) {
			Date nextDate = localDateToUtilDate(LocalDate.now().plusMonths(1));
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
			String nextMonth = sdf.format(nextDate);

			// 日期取年月後+% 給SQL 查詢用 例:2022-08-01 > 2022-08%
			String shiftDateString = nextMonth.concat("%");
			if ("tga000".equals(staffId)) {
				shiftScheduleDao.deleteAll(shiftDateString);
			} else {
				shiftScheduleDao.delete(staffId, shiftDateString);
			}
		}
	}

//日期轉型
	private ShiftSchedule handleEventIdString(String staffId, String eventString, Integer rowId) throws ParseException {

		// tga001陳花花2022-08-01amb
		String shiftDateString = eventString.substring(9, 19);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date ShiftDate = sdf.parse(shiftDateString);

		String apm = eventString.substring(19, 21);
		String levenType = eventString.substring(eventString.length() - 1);
		String insertStaffId = "";
		if ("tga000".equals(staffId)) {
			insertStaffId = eventString.substring(0, 6);
		} else {
			insertStaffId = staffId;
		}

//畫假資料
		ShiftSchedule shiftSchedule = new ShiftSchedule();
		shiftSchedule.setRow(rowId);
		shiftSchedule.setStaffId(insertStaffId);
		shiftSchedule.setScheduleDate(ShiftDate);
		shiftSchedule.setShiftScheduleMorningshiftNightshift(apm);
		shiftSchedule.setShiftScheduleTypeOfLeave(levenType);
		return shiftSchedule;
	}

	// 判斷是不是當月畫架資料
	public boolean checkInsertDate(String eventString) {
		Date nextDate = localDateToUtilDate(LocalDate.now().plusMonths(1));
		SimpleDateFormat yyyyMM = new SimpleDateFormat("yyyy-MM");
		String nextMonth = yyyyMM.format(nextDate);
		System.out.println(eventString);
		String shiftDateString = eventString.substring(9, 16);

		if (shiftDateString.equals(nextMonth)) {
			return true;
		} else {
			return false;
		}
	}

	// 自動產班表
	public String makeSchedule() {
		try {
			List<Date> utilDateList = getDatesForMonth();
			List<Staff> staffList = staffDao.findAll();
			List<ShiftSchedule> shiftScheduleList = getShiftScheduleData();
			for (Date date : utilDateList) {
				Schedule schedule = new Schedule();
				schedule.setSchedule_date(date);
				Map<String, String> workStaffMap = switchStaff(date, staffList, shiftScheduleList);
				schedule.setMorning_shift(workStaffMap.get("morningShift"));
				schedule.setNight_shift(workStaffMap.get("nightShift"));
				schedule.setDay_off1(workStaffMap.get("dayOff1"));
				schedule.setDay_off2(workStaffMap.get("dayOff2"));
				shiftScheduleDao.insertSchedule(schedule);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}

		return "success";
	}

	// 取畫假月份全部日期
	public List<Date> getDatesForMonth() {
		LocalDate nextMonthDate = LocalDate.now().plusMonths(1);
		LocalDate firstDate = nextMonthDate.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDate = nextMonthDate.with(TemporalAdjusters.lastDayOfMonth()).plusDays(1);
		Stream<LocalDate> stream = firstDate.datesUntil(lastDate);
		List<LocalDate> datesForMonth = stream.collect(Collectors.toList());

		List<Date> utilDateList = new ArrayList<Date>();
		for (LocalDate localDate : datesForMonth) {
			utilDateList.add(localDateToUtilDate(localDate));
		}
		return utilDateList;
	}

	// 取得畫假資料
	public List<ShiftSchedule> getShiftScheduleData() {
		Date nextDate = localDateToUtilDate(LocalDate.now().plusMonths(1));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		String nextMonth = sdf.format(nextDate);
		// 刪除新班表
		shiftScheduleDao.deleteSchedule(nextMonth + "%");
		List<ShiftSchedule> shiftScheduleList = shiftScheduleDao.selectShiftScheduleByDate(nextMonth + "%");

		return shiftScheduleList;
	}

	// 挑選上班人員
	public Map<String, String> switchStaff(Date date, List<Staff> staffList, List<ShiftSchedule> shiftScheduleList) {
		Map<String, String> map = new HashMap<String, String>();
		Staff[] staffArray = staffList.toArray(new Staff[0]);

		for (ShiftSchedule shiftSchedule : shiftScheduleList) {
			if (date.equals(shiftSchedule.getScheduleDate())) {
				for (int i = 0; i < staffArray.length; i++) {
					if (shiftSchedule.getStaffId().equals(staffArray[i].getStaff_id())) {
						putDayOff(map, shiftSchedule.getStaffId());
						staffArray = ArrayUtils.remove(staffArray, i);
					}
				}
			}
		}
		randomStaff(date, map, staffArray);

		return map;
	}

	public void randomStaff(Date date, Map<String, String> map, Staff[] staffArray) {
		int r = 0;
		String staffId = "";
		for (int i = 0; i < 2; i++) {
			r = (int) (Math.random() * staffArray.length);
			staffId = staffArray[r].getStaff_id();
			if (checkWorkOver7Days(staffId, date)) {
				if (StringUtils.isBlank(map.get("morningShift"))) {
					map.put("morningShift", staffId);
					staffArray = ArrayUtils.remove(staffArray, r);
				} else {
					map.put("nightShift", staffId);
					staffArray = ArrayUtils.remove(staffArray, r);
					break;
				}
			} else {
				putDayOff(map, staffId);
				staffArray = ArrayUtils.remove(staffArray, r);
				i--;
			}

		}

		for (Staff staff : staffArray) {
			putDayOff(map, staff.getStaff_id());
		}

	}

	public boolean checkWorkOver7Days(String staffId, Date date) {
		LocalDate first = utilDateToLocalDate(date).minusDays(1);
		LocalDate last = first.minusDays(5);

		int workDayCount = shiftScheduleDao.queryWorkDayCount(staffId, last.toString(), first.toString());
		System.out.println("workDayCount : " + workDayCount);
		if (workDayCount >= 6) {
			return false;
		} else {
			return true;
		}
	}

	public void putDayOff(Map<String, String> map, String staffId) {
		if (StringUtils.isBlank(map.get("dayOff1"))) {
			map.put("dayOff1", staffId);
		} else {
			map.put("dayOff2", staffId);
		}
	}

	// Util.Date to LocalDate
	public LocalDate utilDateToLocalDate(Date dateToConvert) {
		return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}

	// LocalDate to Util.Date
	public Date localDateToUtilDate(LocalDate dateToConvert) {
		return java.util.Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
}