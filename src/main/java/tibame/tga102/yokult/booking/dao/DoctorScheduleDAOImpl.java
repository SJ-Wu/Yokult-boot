package tibame.tga102.yokult.booking.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.booking.vo.Doctor;
import tibame.tga102.yokult.booking.vo.DoctorSchedule;
@Repository
public class DoctorScheduleDAOImpl implements DoctorScheduleDAO {
	@PersistenceContext
	private Session session;
	
	public DoctorScheduleDAOImpl( ) {
		super();
	}
	public Session getSession() {
		return session;
	}
		

	@Override
	public List<DoctorSchedule> selectDoctorSchedule(Date date1, Date date2, Integer doctorId) {
		String hql = "select doctorScheduleDate, doctorAmpm from DoctorSchedule where doctorId = :id and "
				+ "doctorStatus = 1 and doctorScheduleDate between :day1 and :day2 order by doctorScheduleDate";
		Query<Object[]> query = this.getSession().createQuery(hql, Object[].class);
		query.setParameter("id", doctorId);
		query.setParameter("day1", date1);
		query.setParameter("day2", date2);
		
		List<Object[]> listresult = query.list();
//		System.out.println(listresult);
		List<DoctorSchedule> listOfDoctorSchedule = new ArrayList<DoctorSchedule>();
		for(Object[] arr : listresult) {
			DoctorSchedule vo = new DoctorSchedule();
			vo.setDoctorScheduleDate((Date)arr[0]);
			vo.setDoctorAmpm((String)arr[1]);
			listOfDoctorSchedule.add(vo);
		}
		if(listOfDoctorSchedule.size() != 0) {
			return listOfDoctorSchedule;
		}
		
//		String sql = "SELECT DOCTOR_SCHEDULE_DATE, DOCTOR_AMPM FROM  DOCTOR_SCHEDULE WHERE DOCTOR_ID = ? "
//				+ "AND DOCTOR_STATUS = 1 AND DOCTOR_SCHEDULE_DATE BETWEEN ? AND ? ORDER BY DOCTOR_SCHEDULE_DATE;";
//		try (Connection connection = dataSource.getConnection()){
//			PreparedStatement ps = connection.prepareStatement(sql);
//			System.out.println("data1= "+ date1);
//			System.out.println("data2= "+ date2);
//			ps.setInt(1, doctorId);
//			ps.setDate(2, date1);
//			ps.setDate(3, date2);
//			ResultSet rs = ps.executeQuery();
//			List<DoctorSchedule> list = new ArrayList<DoctorSchedule>();
//			while(rs.next()) {
//				DoctorSchedule ds = new DoctorSchedule();
//				ds.setDoctorScheduleDate(rs.getDate(1));
//				ds.setDoctorAmpm(rs.getString(2));
//				list.add(ds);
//			}
//			return list;
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return null;
	}


	@Override
	public int insert(DoctorSchedule doctorSchedule) {
		Serializable result = this.getSession().save(doctorSchedule);
		if(result != null) {
			return 1;
		}
	
		
//		String sql = "INSERT INTO DOCTOR_SCHEDULE(DOCTOR_ID, DOCTOR_SCHEDULE_DATE, DOCTOR_AMPM ,DOCTOR_STATUS) VALUES(?, ?, ?, ?);";
//		try ( Connection connection =  dataSource.getConnection();){
//		PreparedStatement ps = connection.prepareStatement(sql);
//		ps.setInt(1, doctorSchedule.getDoctorId());
//		ps.setDate(2, doctorSchedule.getDoctorScheduleDate());
//		ps.setString(3, doctorSchedule.getDoctorAmpm());
//		ps.setInt(4, doctorSchedule.getDoctorStatus());
//		
//		int rowcount = ps.executeUpdate();
//		return rowcount;
//		} catch (SQLException e) {
//			System.out.println("insert DoctorSchedule failure in dao");
//			e.printStackTrace();
//		}
		return -1;
	}
	
	@Override
	public int selectSerialNum(DoctorSchedule doctorSchedule) {
		String hql = "select serialNumber from DoctorSchedule where doctorId = :id and doctorScheduleDate = :day and doctorAmpm = :ampm";
		Query<Integer> query = this.getSession().createQuery(hql, Integer.class);
		query.setParameter("id", doctorSchedule.getDoctorId());
		query.setParameter("day", doctorSchedule.getDoctorScheduleDate());
		query.setParameter("ampm", doctorSchedule.getDoctorAmpm());
		Integer snum = query.uniqueResult();

		if(snum != null) {
			return snum;
		}
		
//		String sql = "select * from DOCTOR_SCHEDULE where  DOCTOR_ID= ? and DOCTOR_SCHEDULE_DATE = ? and DOCTOR_AMPM = ? ;";
//		try ( Connection connection =  dataSource.getConnection();){
//		PreparedStatement ps = connection.prepareStatement(sql);
//		ps.setInt(1, doctorSchedule.getDoctorId());
//		ps.setDate(2, doctorSchedule.getDoctorScheduleDate());
//		ps.setString(3, doctorSchedule.getDoctorAmpm());
//		ResultSet rs = ps.executeQuery();
//		DoctorSchedule ds = new DoctorSchedule();
//		if(rs.next()) {
//			return rs.getInt(1);
//		}
//
//		} catch (SQLException e) {
//			System.out.println("insert DoctorSchedule failure in dao");
//			e.printStackTrace();
//		}
		return -1;
	}


//update status
	@Override
	public int update(DoctorSchedule doctorSchedule) {
		Integer pk = this.selectSerialNum(doctorSchedule);
		if(pk != null) {
			DoctorSchedule doctorScheduleOld = this.getSession().get(DoctorSchedule.class, pk);
			doctorScheduleOld.setDoctorStatus(doctorSchedule.getDoctorStatus());
			return 1;
		}
		
		//		String sql ="UPDATE DOCTOR_SCHEDULE SET DOCTOR_STATUS = ? WHERE DOCTOR_ID= ? and DOCTOR_SCHEDULE_DATE = ? and DOCTOR_AMPM = ? ;";
//		try ( Connection connection =  dataSource.getConnection();){
//		PreparedStatement ps = connection.prepareStatement(sql);
//		ps.setInt(1, doctorSchedule.getDoctorStatus());
//		ps.setInt(2, doctorSchedule.getDoctorId());
//		ps.setDate(3, doctorSchedule.getDoctorScheduleDate());
//		ps.setString(4, doctorSchedule.getDoctorAmpm());
//		
//		int rowcount = ps.executeUpdate();
//		return rowcount;
//		} catch (SQLException e) {
//			System.out.println("update DoctorSchedule failure in dao");
//			e.printStackTrace();
//		}
		return -1;
	}


	@Override
	public DoctorSchedule selectOne(DoctorSchedule doctorSchedule) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

//	@Override
//	public List<Entry<Doctor, DoctorSchedule>> selectDoctorSchedule(Date date1, Date date2, Integer doctorId) {
//		try (Connection connection = dataSource.getConnection()){
//			PreparedStatement ps = connection.prepareStatement(SELECT_DOCTOR_SCHEDULE);
//			ps.setInt(1, doctorId);
//			ps.setDate(2, date1);
//			ps.setDate(3, date2);
//			ResultSet rs = ps.executeQuery();
//			List<Entry<Doctor, DoctorSchedule>> list = new ArrayList<>();
//			while(rs.next()) {
//				DoctorSchedule dsbean = new DoctorSchedule();
//				Doctor drbean = new Doctor();
//				drbean.setDoctorName(rs.getString("DOCTOR_NAME"));
//				dsbean.setDoctorScheduleDate(rs.getDate("DOCTOR_SCHEDULE_DATE"));
//				dsbean.setDoctorAmpm(rs.getString("DOCTOR_AMPM"));
//				Entry<Doctor, DoctorSchedule> entry = Map.entry(drbean, dsbean);
//				list.add(entry);
//			}
//			System.out.println("selectDoctorSchedule success");
//			System.out.println(list);
//			return list;
//		} catch (SQLException e) {
//			System.out.println("selectDoctorSchedule mistake");
//			e.printStackTrace();
//		}
//		return null;
//	}


}
