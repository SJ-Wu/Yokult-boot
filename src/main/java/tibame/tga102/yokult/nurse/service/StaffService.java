package tibame.tga102.yokult.nurse.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tibame.tga102.yokult.nurse.dao.StaffDao;
import tibame.tga102.yokult.staff.vo.Staff;

@Service
@Transactional
public class StaffService {

	@Autowired
	private StaffDao staffDao;

	// 查詢員工資料
	public List<Staff> getAllData(Staff staff) {
		 // 老闆取所有人
		if ("tga000".equals(staff.getStaff_id())) {
			return staffDao.findAll();
		} else {
			List<Staff> staffList = new ArrayList<Staff>();
			staffList.add(staffDao.selectByStaffId(staff.getStaff_id()));
			return staffList;
		}
	}

	// 新，修
	public Map<String, String> addOrModify(Staff staff) {
		Map<String, String> map = new HashMap<String, String>();
		if (StringUtils.isBlank(staff.getStaff_id())) {// 新增
			map.put("type", "insert");
			try {
				String maxId = staffDao.getMaxId();
				System.out.println("目前最新員工編號 : " + maxId);
				int maxNum = Integer.parseInt(maxId.substring(3));
				maxNum++;
				String laftAdd0 = "%03d";
				String add0 = String.format("tga" + laftAdd0, maxNum);
				System.out.println("預計新增員工編號 : " + add0);
				staff.setStaff_id(add0);
				staffDao.insert(staff);
				map.put("msg", "success");
			} catch (NumberFormatException e) {
				e.printStackTrace();
				map.put("msg", "fail");
			}
		} else {// 修改
			map.put("type", "update");
			try {
				Staff oldStaff = staffDao.selectByStaffId(staff.getStaff_id());
				oldStaff.setStaff_name(staff.getStaff_name());
				oldStaff.setStaff_email(staff.getStaff_email());
				oldStaff.setStaff_idnumber(staff.getStaff_idnumber());
				oldStaff.setStaff_birthday(staff.getStaff_birthday());
				oldStaff.setStaff_phone(staff.getStaff_phone());
				oldStaff.setStaff_picture(staff.getStaff_picture());
				
				staffDao.update(oldStaff);
				map.put("msg", "success");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("msg", "fail");
			}
		}
		return map;
	}

	// 刪除
	public String remove(Staff staff) {
		try {
			staffDao.delete(staff);
			return "success";
		} catch (Exception e) {
			e.printStackTrace();
			return "fail";
		}
	}

}
