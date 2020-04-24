package conn;
import java.sql.*;
public class Conexion {

	private static final String jdbc_driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/final_181030?useSSL=false";
	private static final String user = "root";
	private static final String pass = "wandy5555";
	private static Driver driver = null;
	
	public static synchronized Connection obteneConeccion()
			throws SQLException{
	
		if(driver == null) {
			try {
				Class jdbcdriverclass = Class.forName(jdbc_driver);
				driver = (Driver) jdbcdriverclass.newInstance();
				DriverManager.registerDriver(driver);
			}catch(Exception l) {
				l.printStackTrace();
			}
		}
		return DriverManager.getConnection(url,user,pass);
		
	}
	
}
