package container;
import java.sql.*;

/**
 * 数据库连接类
 * @author ypq
 * @version 1.0
 */
public class DBAccess {

	private static final String drivername="org.sqlite.JDBC";
	private static final String url="jdbc:sqlite:FTAS.db";
	
	public DBAccess() {
	}
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(drivername);
		Connection conn=DriverManager.getConnection(url);
		return conn;
	}
	
}
