package tibame.tga102.yokult.staff.dao;

import tibame.tga102.yokult.staff.vo.Staff;

public interface StaffDao {
	Staff selectByStaffIdAndPassword(Staff staff);
}
