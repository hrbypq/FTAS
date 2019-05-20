package container;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;


/**
 * 机票容器
 * @author ypq
 * @version 1.0
 */
public class TicketContainer {

	private Map<String,Integer> container=null;   //key为航班号
	
	/**
	 * 构造方法
	 */
	public TicketContainer() {
		container=new TreeMap<String,Integer>();
	}

	public Map<String, Integer> getContainer() {
		return container;
	}

	public void setContainer(Map<String, Integer> container) {
		this.container = container;
	}

	public void readDB() throws ClassNotFoundException, SQLException {
		Connection connection=DBAccess.getConnection();
		Statement statement=connection.createStatement();
		String search="select * from t_ticket";
		ResultSet resultset=statement.executeQuery(search);
		while(resultset.next()) {
			String key=resultset.getString("flightname");
			this.container.put(key, resultset.getInt("remaining"));
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
			String flightname=key;
			Integer remaining=this.container.get(key);
			String insertion="insert into t_ticket values ("+flightname+","+remaining+")";
			statement.executeUpdate(insertion);
		}
		connection.close();
	}
}
