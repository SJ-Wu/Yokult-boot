package tibame.tga102.yokult.booking.dao;
import static com.mongodb.MongoClientSettings.getDefaultCodecRegistry;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Sorts.ascending;
import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

import org.bson.Document;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.InsertOneResult;

import tibame.tga102.yokult.booking.vo.Doctor;
import tibame.tga102.yokult.booking.vo.Patient;

public class DoctorCheckinDAOImpl {
//	private  MongoClient mongoClient;
    private CodecProvider pojoCodecProvider = PojoCodecProvider.builder().automatic(true).build();
    private CodecRegistry pojoCodecRegistry = fromRegistries(getDefaultCodecRegistry(), fromProviders(pojoCodecProvider));
//	private final String uri = "mongodb+srv://vicky:tibame888@cluster0.sw4btkx.mongodb.net/?retryWrites=true&w=majority";
//    private MongoDatabase database;
    
//    private MongoCollection<Document> collection = CheckinUtil.getCollection() ;

	public DoctorCheckinDAOImpl() {
		super();
//		Context context = new InitialContext();
//		this.mongoClient =(MongoClient) context.lookup("java:comp/env/mongodb/MyMongoClient");
//		this.database = mongoClient.getDatabase("patient");
//		this.collection = database.getCollection("checkin");
		
	}
		
	//新增一筆資料
	public int insertOne(Patient patient) {
        Document doc1 = new Document("patientIdcard", patient.getPatientIdcard()).append("bookingNumber", patient.getBookingNumber()).append("doctorId", patient.getBookingNumber());

//        	InsertOneResult result = collection.insertOne(doc1);
//        	if(result.getInsertedId().asObjectId().getValue().toString().length() != 0) {
//        		System.out.println(result.getInsertedId().asObjectId().getValue());
//        		return 1;
//        	}
        	
       
        return -1;
	}
	
	//醫師後台 查詢第一筆資料
	public Document selectOne(Doctor doctor) {
//		MongoDatabase database = mongoClient.getDatabase("patient").withCodecRegistry(pojoCodecRegistry);
//        MongoCollection<Patient> collection = database.getCollection("checkin", Patient.class);
//        Patient patient = collection.find(eq("doctorId", 1)).sort(ascending("bookingNumber")).first();
//        System.out.println(patient);
                
        //得到document方式

//        Document doc = collection.find(eq("doctorId", doctor.getDoctorId())).sort(ascending("bookingNumber")).first();
//        System.out.println("checkinDAO selectOne success");

		
		//        DeleteResult dr = collection.deleteOne(doc);
//		if(dr.getDeletedCount() > 0) {
//			return 1;
//		}else {
//			return -1;
//		}
//        return doc;
		
		
		return null;
	}
	
	//刪除一筆資料
	public int deleteOne(Document doc) {
//		String stringId = doc.get("_id").toString();
//		Bson filter = Filters.eq("_id", stringId);
//		DeleteResult dr = collection.deleteOne(filter);
		
		
//		DeleteResult dr = collection.deleteOne(doc);
//		if(dr.getDeletedCount() > 0) {
//			return 1;
//		}else {
//			return -1;
//		}
		
		return -1;
	}

	
	
}
