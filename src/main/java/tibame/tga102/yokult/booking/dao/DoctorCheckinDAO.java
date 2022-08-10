package tibame.tga102.yokult.booking.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import tibame.tga102.yokult.booking.vo.CheckinVO;

@Repository
public interface DoctorCheckinDAO extends MongoRepository<CheckinVO, String>{
	

}
