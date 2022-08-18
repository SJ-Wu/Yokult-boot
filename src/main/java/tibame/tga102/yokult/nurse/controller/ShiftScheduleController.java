package tibame.tga102.yokult.nurse.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tibame.tga102.yokult.nurse.service.ShiftScheduleService;
import tibame.tga102.yokult.nurse.vo.ShiftScheduleReq;
import tibame.tga102.yokult.staff.vo.Staff;

@RestController
public class ShiftScheduleController {

	@Autowired
	ShiftScheduleService shiftScheduleService;

//新增畫假
	@RequestMapping(value = "/insertShiftSchedule", method = RequestMethod.POST)
	protected String insertShiftSchedule(@RequestBody ShiftScheduleReq shiftScheduleReq) {
		System.out.println("this insertShiftSchedule");
		return shiftScheduleService.insert(shiftScheduleReq);
	}

//畫假紀錄
	@RequestMapping(value = "/getStaffLevenData", method = RequestMethod.POST)
	protected Map<String, Object> getStaffLevenData(@RequestBody Staff staff) {
		System.out.println("this getStaffLevenData");
		return shiftScheduleService.getStaffData(staff.getStaff_id());
	}

//自動產生班表
	@RequestMapping(value = "/autoMakeSchedule", method = RequestMethod.POST)
	protected String autoMakeSchedule(@RequestBody ShiftScheduleReq shiftScheduleReq) {
		System.out.println("this autoMakeSchedule");
		shiftScheduleService.insert(shiftScheduleReq);
		return shiftScheduleService.makeSchedule();
	}

}
