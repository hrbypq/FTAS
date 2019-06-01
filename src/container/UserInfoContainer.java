package container;
import java.util.*;
import java.sql.*;
import entity.*;

/**
 * 用户信息容器
 * @author ypq
 * @version 1.0
 */
public class UserInfoContainer {
	
	private Map<String,UserInfo> container=null;    //key为用户名
	
	/**
	 * 构造方法
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public UserInfoContainer() throws ClassNotFoundException, SQLException {
		container=new TreeMap<String,UserInfo>();
		this.readDB();
	}
	
	public Map<String, UserInfo> getContainer() {
		return container;
	}
	
	public void setContainer(Map<String, UserInfo> container) {
		this.container = container;
	}

	public void readDB() throws ClassNotFoundException, SQLException {
		Connection connection=DBAccess.getConnection();
		Statement statement=connection.createStatement();
		String search="select * from t_userinfo";
		ResultSet resultset=statement.executeQuery(search);
		while(resultset.next()) {
			String key=resultset.getString("name");
			UserInfo obj=new UserInfo(key,resultset.getString("passwd"));
			this.container.put(key, obj);
		}
		resultset.close();
		connection.close();
	}
	
	public void writeDB() throws ClassNotFoundException, SQLException {
		Connection connection=DBAccess.getConnection();
		Statement statement=connection.createStatement();
		String delete="delete from t_userinfo";
		statement.executeUpdate(delete);
		for(String key:this.container.keySet()) {
			String username=key;
			String passwd=this.container.get(key).getPasswd();
			String insertion="insert into t_userinfo values (\'"+username+"\',\'"+passwd+"\')";
			statement.executeUpdate(insertion);
		}
		connection.close();
	}
}
