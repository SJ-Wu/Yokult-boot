package tibame.tga102.yokult.nurse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tibame.tga102.yokult.nurse.service.ScheduleService;
import tibame.tga102.yokult.nurse.vo.ResultSchedule;

@RestController
public class ScheduleController {

	@Autowired
	ScheduleService sheduleService;

	// 取得班表資料
	@RequestMapping(value = "/getScheduleAllData", method = RequestMethod.POST)
	protected List<ResultSchedule> scheduleAllData() {
		System.out.println("this Schedule");
		return sheduleService.getAll();

	}

}
