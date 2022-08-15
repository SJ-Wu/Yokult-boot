//package tibame.tga102.yokult.booking.dao;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.naming.NamingException;
//
//import org.bson.Document;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import com.mongodb.client.MongoClient;
//import com.mongodb.client.MongoCollection;
//@Component
//public class CheckinUtil {
//	@Autowired
//	private static MongoClient mongoClient = CheckinUtil.getMongoClient();
//	
//	public static MongoClient getMongoClient() {
//		Context context ;
//		MongoClient mongoClient = null;
//		try {
//			context = new InitialContext();
//			mongoClient = (MongoClient) context.lookup("java:comp/env/mongodb/MyMongoClient");
//		} catch (NamingException e) {
//			e.printStackTrace();
//		}
//		return mongoClient;
//	}
//	
//	public static MongoCollection<Document> getCollection() {
//		return mongoClient.getDatabase("patient").getCollection("checkin");
//		
//	}
//	 public static void closeMongoClient() {
//		 mongoClient.close();
//		 
//	 }
//	
//	
//}
