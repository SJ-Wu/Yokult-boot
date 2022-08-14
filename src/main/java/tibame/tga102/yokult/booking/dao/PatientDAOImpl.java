package tibame.tga102.yokult.booking.dao;

import java.sql.Date;
import java.util.List;

import javax.persistence.PersistenceContext;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.booking.vo.Patient;
@Repository
public class PatientDAOImpl implements PatientDAO {
	@PersistenceContext
	private Session session;
	
	public PatientDAOImpl() {
		super();
	}

	public Session getSession() {
		return session;
	}
	
	@Override
	public int insertBookingIntoPatient(Patient patient) {
	  	Integer result = (Integer)this.getSession().save(patient);
	  	if(result > 0) {
	  		return 1;
	  	}
		
//		String sql = "INSERT INTO PATIENT(MEMID, PATIENT_IDCARD, BOOKING_DATE, AMPM, BOOKING_NUMBER, DOCTOR_ID) VALUES (?, ?, ?, ?, ?, ?);";
//			try(Connection connection = dataSource.getConnection();
//					PreparedStatement ps = connection.prepareStatement(sql);) {
//				
//				ps.setString(1, patient.getMemID());
//				ps.setString(2, patient.getPatientIdcard());
//				ps.setDate(3, patient.getBookingDate());
//				ps.setString(4, patient.getAmPm());
//				ps.setInt(5, patient.getBookingNumber());
//				ps.setInt(6, patient.getDoctorId());
//				return ps.executeUpdate();
//
//			} catch (SQLException e) {
//				System.out.println("insertBookingIntoPatient failure");
//				e.printStackTrace();
//				return -1;
//			}
		return -1;
	}
	
	@Override
	public List<Patient> selectPatientByIdcard(Patient patient) {
		String hql = "from Patient where patientIdcard = :id order by bookingDate";
		Query<Patient> query = this.getSession().createQuery(hql, Patient.class);
		query.setParameter("id", patient.getPatientIdcard());
		List<Patient> listOfPatient = query.list();
		if(listOfPatient.size() != 0) {
			return listOfPatient;
		}
//		String sql = "SELECT SERIAL_NUMBER, MEMID, PATIENT_IDCARD, BOOKING_DATE, AMPM, BOOKING_NUMBER, DOCTOR_ALPHABET, DOCTOR_ID, CHECKIN_CONDITION ,CHART FROM PATIENT WHERE PATIENT_IDCARD = ? ORDER BY BOOKING_DATE";
//			try (Connection connection = dataSource.getConnection();
//					PreparedStatement ps = connection.prepareStatement(sql);
//					){
//				ps.setString(1, patient.getPatientIdcard());
//				ResultSet rs = ps.executeQuery();
//				List<Patient> list = new ArrayList<>(); 
//				while(rs.next()) {
//					Patient vo = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
//					list.add(vo);
//				}
//				
//				return list;
//			} catch (SQLException e) {
//				System.out.println("selectPatientBypatientIdcard failure");
//				e.printStackTrace();
//			}
			return null;
			
	}
	
	//查詢列出病患身份證字號為? getCheckinCondition=?的病患所有欄位
	@Override
	public List<Patient> selectPatientByIdcardAndCheckinCondition(Patient patient) {
		String hql = "from Patient where patientIdcard = :id and checkinCondition = :condition";
		Query query = this.getSession().createQuery(hql, Patient.class);
		query.setParameter("id", patient.getPatientIdcard());
		query.setParameter("condition", patient.getCheckinCondition());
		List<Patient> listOfPatient = query.list();
		if( listOfPatient.size()!= 0) {
			return listOfPatient;
		}
		
//		String sql = "SELECT SERIAL_NUMBER, MEMID, PATIENT_IDCARD, BOOKING_DATE, AMPM, BOOKING_NUMBER, DOCTOR_ALPHABET, DOCTOR_ID, CHECKIN_CONDITION ,CHART FROM PATIENT WHERE PATIENT_IDCARD = ? AND CHECKIN_CONDITION = ? ORDER BY BOOKING_DATE";
//			try (Connection connection = dataSource.getConnection();
//					PreparedStatement ps = connection.prepareStatement(sql);
//					){
//				ps.setString(1, patient.getPatientIdcard());
//				ps.setInt(2, patient.getCheckinCondition());
//				ResultSet rs = ps.executeQuery();
//				List<Patient> list = new ArrayList<>(); 
//				while(rs.next()) {
//					Patient vo = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
//					list.add(vo);
//				}
//				
//				return list;
//			} catch (SQLException e) {
//				System.out.println("selectPatientBypatientIdcard failure");
//				e.printStackTrace();
//			}
			return null;
			
	}

	@Override
	//(刪除/修改)病患身份證字號為A123456788 看診預約日期為=?? 預約狀態為=?
	// 刪除病患身份證字號為A123456788 看診預約日期為??=? 預約狀態為? =1
	// 修改病患身份證字號為A123456788 看診預約日期為 =今天 預約狀態為=1
	public int updatePatientCheckinConditionByBookingDate(Patient patient) {
		String hql = "from Patient where patientIdcard = :id and bookingDate = :day";
		Patient vo = this.getSession().createQuery(hql, Patient.class)
						.setParameter("id", patient.getPatientIdcard())
						.setParameter("day", patient.getBookingDate())
						.uniqueResult();
		System.out.println("==> vo at patientDAO"+ vo);
		System.out.println("==> patient.getCheckinCondition()="+ patient.getCheckinCondition());
		if(vo != null) {
//			this.getSession().merge(patient);
			vo.setCheckinCondition(patient.getCheckinCondition());
			return 1;
		}
//		String sql = "UPDATE PATIENT SET CHECKIN_CONDITION = ? WHERE PATIENT_IDCARD = ? AND BOOKING_DATE = ? ;";
//			try (Connection connection = dataSource.getConnection();
//					PreparedStatement ps = connection.prepareStatement(sql);){
//
//				ps.setInt(1, patient.getCheckinCondition());
//				ps.setString(2, patient.getPatientIdcard());
//				ps.setDate(3, patient.getBookingDate());
//				int rowcount = ps.executeUpdate();
//				System.out.println("updatePatientCheckinConditionByBookingDate for : "+ rowcount +"-" + patient);
//				return rowcount;
//			} catch (SQLException e) {
//				System.out.println("selectPatientBypatientIdcard failure");
//				e.printStackTrace();
//			}
			return -1;
			
	}

	@Override
	public int selectCountByDoctor(Date date, int doctorId) {
		String hql = "select count(1) from Patient where doctorId = :drid and bookingDate = :day";
		Long result = this.getSession().createQuery(hql, Long.class)
			.setParameter("drid", doctorId)
			.setParameter("day", date)
			.uniqueResult();
		if(result != null) {
			
			return Integer.valueOf(result.toString());
		}
		
//SELECT COUNT(1) FROM PATIENT WHERE DOCTOR_ID = ? AND BOOKING_DATE = ? ;
//		String sql = "SELECT COUNT(1) FROM PATIENT WHERE DOCTOR_ID = ? AND BOOKING_DATE = ? ;";
//		try( Connection connection = dataSource.getConnection();
//		PreparedStatement ps = connection.prepareStatement(sql);){
//			ps.setInt(1, doctorId);
//			ps.setDate(2, date);
//			ResultSet rs = ps.executeQuery();
//			rs.next();
//			int count = rs.getInt(1);
//			return count;
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
		return -1;
	}

	//查詢列出病患會員為? 的病患所有欄位
	@Override
	public List<Patient> selectPatientBymemID(Patient patient) {
		String hql = "from Patient where memID = :memid";
		 List<Patient> listOfPatients = this.getSession().createQuery(hql, Patient.class)
												 	.setParameter("memid", patient.getMemID())
												 	.list();
		 if(listOfPatients.size() != 0) {
			 return listOfPatients;
		 }
//		String sql = "SELECT SERIAL_NUMBER, MEMID, PATIENT_IDCARD, BOOKING_DATE, AMPM, BOOKING_NUMBER, DOCTOR_ALPHABET, DOCTOR_ID, CHECKIN_CONDITION ,CHART FROM PATIENT WHERE MEMID = ?";
//		try(Connection connection = dataSource.getConnection();
//				PreparedStatement ps = connection.prepareStatement(sql)) {
//			ps.setString(1, patient.getMemID());
//			ResultSet rs = ps.executeQuery();
//			List<Patient> list = new ArrayList<Patient>();
//			while(rs.next()) {
//				Patient vo = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
//				list.add(vo);
//			}
//			return list;
//			
//		} catch (SQLException e) {
//			System.out.println("selectPatientBymemID failure");
//			e.printStackTrace();
//		}
				
		return null;
	}

	@Override
	public List<Patient> selectAll() {
		String hql = "from Patient";
		List<Patient> listOfPatients = this.getSession().createQuery(hql, Patient.class).list();
		if(listOfPatients != null) {
			return listOfPatients;
		}
//		String sql = "SELECT SERIAL_NUMBER, MEMID, PATIENT_IDCARD, BOOKING_DATE, AMPM, BOOKING_NUMBER, DOCTOR_ALPHABET, DOCTOR_ID, CHECKIN_CONDITION ,CHART FROM PATIENT ";
//		try(Connection connection = dataSource.getConnection();
//			PreparedStatement ps = connection.prepareStatement(sql)) {
//			ResultSet rs = ps.executeQuery();
//			List<Patient> list = new ArrayList<Patient>();
//			while(rs.next()) {
//				Patient vo = new Patient(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4), rs.getString(5), rs.getInt(6), rs.getString(7), rs.getInt(8), rs.getInt(9), rs.getString(10));
//				list.add(vo);
//			}
//			return list;
//			
//		} catch (SQLException e) {
//			System.out.println("selectPatientALL failure");
//			e.printStackTrace();
//		}
				
		return null;
	}

	@Override
	public int updateChart(Patient patient) {
		Patient vo = this.selectOne(patient);
		if(vo != null) {
			vo.setChart(patient.getChart());
			return 1;
		}
//		String sql = "UPDATE PATIENT SET CHART = ?  WHERE PATIENT_IDCARD = ? AND BOOKING_DATE = ?;";
//		try (Connection connection = dataSource.getConnection();
//				PreparedStatement ps = connection.prepareStatement(sql);){
//			ps.setString(1, patient.getChart());
//			ps.setString(2, patient.getPatientIdcard());
//			ps.setDate(3, patient.getBookingDate());
//			int rowcount = ps.executeUpdate();
//			System.out.println("updateChart for : "+ rowcount +"-" + patient);
//			return rowcount;
//		} catch (SQLException e) {
//			System.out.println("updateChart failure");
//			e.printStackTrace();
//		}
		return -1;
	}
	
	public Patient selectOne(Patient patient) {
		String hql = "from Patient where patientIdcard = :id and bookingDate = :day";
		 Patient vo = this.getSession().createQuery(hql, Patient.class)
		 	.setParameter("id", patient.getPatientIdcard())
		 	.setParameter("day", patient.getBookingDate())
		 	.uniqueResult();
		 if(vo != null) {
			 return vo;
		 }
		return null;
	}


}
