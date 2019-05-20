package container;
import java.util.*;
import java.sql.*;
import entity.*;

/**
 * 订单容器
 * @author ypq
 * @version 1.0
 */
public class ReservationContainer {

	private Map<String,List<Reservation>> container=null;    //key为用户名
	
	/**
	 * 构造方法
	 */
	public ReservationContainer() {
		container=new TreeMap<String,List<Reservation>>();
	}

	public Map<String, List<Reservation>> getContainer() {
		return container;
	}

	public void setContainer(Map<String, List<Reservation>> container) {
		this.container = container;
	}

	public void readDB() throws ClassNotFoundException, SQLException {
		Connection connection=DBAccess.getConnection();
		Statement statement=connection.createStatement();
		String search="select * from t_reservation";
		ResultSet resultset=statement.executeQuery(search);
		while(resultset.next()) {
			String key=resultset.getString("username");
			Reservation obj=new Reservation(key,resultset.getString("flightname"),resultset.getString("time"));
			if(this.container.keySet().contains(key)) {            //如果该用户在map中 将订单对象插入该用户对应的ArrayList
				this.container.get(key).add(obj);
				continue;
			}
			this.container.put(key, new ArrayList<Reservation>());        //若不在  先新建一个ArrayList
			this.container.get(key).add(obj);                             //插入订单对象
		}
		resultset.close();
		connection.close();
	}
	
	public void writeDB() throws ClassNotFoundException, SQLException {
		Connection connection=DBAccess.getConnection();
		Statement statement=connection.createStatement();
		String delete="delete from t_reservation";
		statement.executeUpdate(delete);
		for(String key:this.container.keySet()) {
			for(Reservation obj:this.container.get(key)) {          //遍历当前user对应的ArrayList
				String username=key;
				String flightname=obj.getFlightname();
				String time=obj.getTime();
				String insertion="insert into t_reservation values ("+username+","+flightname+","+time+")";
				statement.executeUpdate(insertion);
			}
		}
		connection.close();
	}
}
