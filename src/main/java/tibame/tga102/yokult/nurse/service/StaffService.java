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
		if ("tga000".equals(staff.getStaff_id())) { // 老闆
			return staffDao.findAll();
		} else {
			List<Staff> staffList = new ArrayList<Staff>();
			staffList.add(staffDao.selectByStaffId(staff.getStaff_id()));
			return staffList;
		}
	}
	
	// 登入
	public Staff login(Staff staff) {
		if (StringUtils.isBlank(staff.getStaff_id()) || StringUtils.isBlank(staff.getStaff_idnumber())) {
			System.out.println("員工編號或密碼錯誤");
			return null;
		}
		staff = staffDao.selectByStaff_idAndstaff_idnumber(staff);
		if (staff == null) {
			return null;
		}
		return staff;
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
				staffDao.update(staff);
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
