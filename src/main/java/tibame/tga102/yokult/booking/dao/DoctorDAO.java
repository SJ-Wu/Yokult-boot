package tibame.tga102.yokult.booking.dao;

import java.util.List;

import tibame.tga102.yokult.booking.vo.Doctor;

public interface DoctorDAO {
	//select*
	public Doctor selectOne(Doctor doctor);
	
//	public int update(Doctor doctor);
	
	public int insert(Doctor doctor);
	
	//給醫師編號 拿醫師姓名
	public String selectDoctorNameById(int doctorId);

	public List<Doctor> selectAll();

		
	public int updateDr(Doctor doctor);	
	

}
