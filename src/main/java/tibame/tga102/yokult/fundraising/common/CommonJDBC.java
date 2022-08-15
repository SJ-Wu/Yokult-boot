package tibame.tga102.yokult.fundraising.common;

public class CommonJDBC {
	// MySQL 8之後連線URL需加上SSL與時區設定
	public final static String URL = "jdbc:mysql://localhost:3306/YOKULT?useUnicode=yes&characterEncoding=utf8&useSSL=true&serverTimezone=Asia/Taipei";
	// MySQL 8之前
	// public final static String URL = "jdbc:mysql://localhost:3306/bookshop_jdbc";
	
	public final static String USER = "root";
	public final static String PASSWORD = "1qaz@WSX";
}
