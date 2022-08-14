package tibame.tga102.yokult.booking.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.PersistenceContext;
import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.booking.vo.Doctor;
@Repository
public class DoctorDAOImpl implements DoctorDAO {

	@PersistenceContext
	private Session session;

	public DoctorDAOImpl() {
		super();
	}
	//	到時候Spring課程會把session宣告成屬性
	public Session getSession() {
		return session;
	}


//*******
	@Override
	public String selectDoctorNameById(int doctorId) {
		String hql="select doctorName from Doctor where doctorId = :id";
		Query<String> query = this.getSession().createQuery(hql, String.class);
		query.setParameter("id", doctorId);
		String result = query.uniqueResult();
		if (result != null) {
			return result;
		}
//		String sql = "SELECT DOCTOR_NAME FROM DOCTOR WHERE DOCTOR_ID = ? ;";
//		try ( Connection connection =  dataSource.getConnection();){
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setInt(1, doctorId);
//			ResultSet rs = ps.executeQuery();
//			rs.next();
//			return rs.getString(1);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return null;
	}

	//select *
	@Override
	public Doctor selectOne(Doctor doctor) {
		if(doctor != null) {
			String hql ="from Doctor where doctorId = :pk";
			Query<Doctor> query = this.getSession().createQuery(hql, Doctor.class);
			query.setParameter("pk", doctor.getDoctorId());
			Doctor vo = query.uniqueResult();
			if(vo!= null) {
				return vo;
			}
		}
//		String sql = "SELECT DOCTOR_ALPHABET, DOCTOR_ID,  DOCTOR_NAME, DOCTOR_PHOTO, DOCTOR_CERTIFICATE, DOCTOR_EMAIL ,DOCTOR_PASSWARD FROM DOCTOR WHERE DOCTOR_ID = ? ;";
//		try ( Connection connection =  dataSource.getConnection();){
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ps.setInt(1, doctor.getDoctorId());
//			ResultSet rs = ps.executeQuery();
//						
//			Doctor vo = new Doctor();	
//			while(rs.next()) {
//				vo.setDoctorAlphabet(rs.getString(1));
//				vo.setDoctorId(rs.getInt(2));
//				vo.setDoctorName(rs.getString(3));
//				vo.setDoctorPhoto(rs.getBytes(4));
//				vo.setDoctorCertificate(rs.getString(5));
//				vo.setDoctorEmail(rs.getString(6));
//				vo.setDoctorPassword(rs.getString(7));
//			}
//			System.out.println("DAO: get Doctor by id success");
//			return vo;
//		} catch (SQLException e) {
//			System.out.println("DAO: get Doctor by id Exception");
//			e.printStackTrace();
//		}
		System.out.println("DAO: get Doctor by id failure");
		return null;
	}
	
	@Override
	public List<Doctor> selectAll() {
		String hql = "from Doctor";
		Query<Doctor> query = this.getSession().createQuery(hql, Doctor.class);
		List<Doctor> list = query.list();
		if (list != null && list.size() != 0) {
			return list;
		}
//		String sql = "SELECT DOCTOR_ALPHABET, DOCTOR_ID,  DOCTOR_NAME, DOCTOR_PHOTO, DOCTOR_CERTIFICATE, DOCTOR_EMAIL ,DOCTOR_PASSWARD FROM DOCTOR ;";
//		try ( Connection connection =  dataSource.getConnection();){
//			PreparedStatement ps = connection.prepareStatement(sql);
//			ResultSet rs = ps.executeQuery();
//			List<Doctor> list = new ArrayList<Doctor>();
//			while(rs.next()) {
//				Doctor vo = new Doctor();	
//				vo.setDoctorAlphabet(rs.getString(1));
//				vo.setDoctorId(rs.getInt(2));
//				vo.setDoctorName(rs.getString(3));
//				vo.setDoctorPhoto(rs.getBytes(4));
//				vo.setDoctorCertificate(rs.getString(5));
//				vo.setDoctorEmail(rs.getString(6));
//				vo.setDoctorPassword(rs.getString(7));
//				list.add(vo);
//			}
//			return list;
//		} catch (SQLException e) {
//			System.out.println("get Doctor all failure");
//			e.printStackTrace();
//		}
		return null;
	}


//	@Override
//	public int update(Doctor doctor) {
//		//DOCTOR_ALPHABET = ?, DOCTOR_ID = ?, 沒有 因為自動填入
//		String sql = "UPDATE DOCTOR SET   DOCTOR_NAME = ?, DOCTOR_PHOTO = ?, DOCTOR_CERTIFICATE = ?, DOCTOR_EMAIL = ? ,DOCTOR_PASSWARD = ? WHERE DOCTOR_ID = ? ; ";
//		try ( Connection connection =  dataSource.getConnection();){
//		PreparedStatement ps = connection.prepareStatement(sql);
////		ps.setString(1, doctor.getDoctorAlphabet());
////		ps.setInt(2, doctor.getDoctorId());
//		ps.setString(1, doctor.getDoctorName());
//		ps.setBytes(2, doctor.getDoctorPhoto());
//		ps.setString(3, doctor.getDoctorCertificate());
//		ps.setString(4, doctor.getDoctorEmail());
//		ps.setString(5, doctor.getDoctorPassword());
//		
//		int rowcount = ps.executeUpdate();
//		
//		return rowcount;
//		} catch (SQLException e) {
//			System.out.println("update Doctor failure in dao");
//			e.printStackTrace();
//		}
//		
//		
//		return -1;
//	}
	
	//更新姓名和照片
	@Override
	public int updateDr(Doctor doctor) {
		Doctor doctorOld = this.getSession().get(Doctor.class, doctor.getDoctorId());
		doctorOld.setDoctorName(doctor.getDoctorName());
		doctorOld.setDoctorPhoto(doctor.getDoctorPhoto());
		//DOCTOR_ALPHABET = ?, DOCTOR_ID = ?, 沒有 因為自動填入
		//, DOCTOR_CERTIFICATE = ?, DOCTOR_EMAIL = ? ,DOCTOR_PASSWARD = ? 欄位刪減
//		String sql = "UPDATE DOCTOR SET   DOCTOR_NAME = ?, DOCTOR_PHOTO = ? WHERE DOCTOR_ID = ? ; ";
//		try ( Connection connection =  dataSource.getConnection();){
//		PreparedStatement ps = connection.prepareStatement(sql);
////		ps.setString(1, doctor.getDoctorAlphabet());
//		ps.setString(1, doctor.getDoctorName());
//		ps.setBytes(2, doctor.getDoctorPhoto());
//		ps.setInt(3, doctor.getDoctorId());
////		ps.setString(3, doctor.getDoctorCertificate());
////		ps.setString(4, doctor.getDoctorEmail());
////		ps.setString(5, doctor.getDoctorPassword());
//		
//		int rowcount = ps.executeUpdate();
//		System.out.println("doctorDao: updateDr success");
//		return rowcount;
//		} catch (SQLException e) {
//			System.out.println("update Doctor failure in dao");
//			e.printStackTrace();
//		}
		
		
		return 1;
	}

	@Override
	public int insert(Doctor doctor) {
		Serializable result = this.getSession().save(doctor);
		if(result != null) {
			return 1;
		}
//		//DOCTOR_ALPHABET, DOCTOR_ID,這兩個沒有 會自動填入
//		String sql = "INSERT INTO DOCTOR(  DOCTOR_NAME, DOCTOR_PHOTO, DOCTOR_CERTIFICATE, DOCTOR_EMAIL ,DOCTOR_PASSWARD) VALUES ( ?, ?, ?, ?,?) ";
//		try ( Connection connection =  dataSource.getConnection();){
//		PreparedStatement ps = connection.prepareStatement(sql);
////		ps.setString(1, doctor.getDoctorAlphabet());
////		ps.setInt(2, doctor.getDoctorId());
//		ps.setString(1, doctor.getDoctorName());
//		ps.setBytes(2, doctor.getDoctorPhoto());
//		ps.setString(3, doctor.getDoctorCertificate());
//		ps.setString(4, doctor.getDoctorEmail());
//		ps.setString(5, doctor.getDoctorPassword());
//		
//		int rowcount = ps.executeUpdate();
//		return rowcount;
//		} catch (SQLException e) {
//			System.out.println("insert Doctor failure in dao");
//			e.printStackTrace();
//		}
		return -1;
	}


}
