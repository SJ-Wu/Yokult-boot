package tibame.tga102.yokult.nurse.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import tibame.tga102.yokult.nurse.service.StaffService;
import tibame.tga102.yokult.staff.vo.Staff;

@RestController
public class StaffController {

	@Autowired
	StaffService staffService;

	@RequestMapping(value = "/getStaffAllData", method = RequestMethod.POST)
	public List<Staff> getStaffAllData(@RequestBody Staff staff) {
		System.out.println("this staff");
		return staffService.getAllData(staff);
	}

	// 新 修
	@RequestMapping(value = "/addOrModify", method = RequestMethod.POST)
	public Map<String, String> addOrModify(@RequestBody Staff staff) {
		return staffService.addOrModify(staff);
	}

	// 刪除
	@RequestMapping(value = "/deleteStaff", method = RequestMethod.POST)
	protected String doDelete(@RequestBody Staff staff) {
		return staffService.remove(staff);
	}

}
