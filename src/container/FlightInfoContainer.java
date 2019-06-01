package container;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
import entity.*;

/**
 * 航班信息容器
 * @author ypq
 * @version 1.0
 */
public class FlightInfoContainer {

	private Map<String,FlightInfo> container=null;      //key为航班号
	
	/**
	 * 构造方法
	 */
	public FlightInfoContainer() {
		container=new TreeMap<String,FlightInfo>();
	}

	public Map<String, FlightInfo> getContainer() {
		return container;
	}

	public void setContainer(Map<String, FlightInfo> container) {
		this.container = container;
	}

	public void readDB() throws ClassNotFoundException, SQLException {
		Connection connection=DBAccess.getConnection();
		Statement statement=connection.createStatement();
		String search="select * from t_flightinfo";
		ResultSet resultset=statement.executeQuery(search);
		while(resultset.next()) {
			String key=resultset.getString("flightname");
			FlightInfo obj=new FlightInfo(key,resultset.getString("company"),resultset.getDouble("price"),resultset.getString("takeofftime"),resultset.getString("takeofflocation"),
					resultset.getString("landtime"),resultset.getString("landlocation"),resultset.getString("passtime"),resultset.getString("passlocation"));
			this.container.put(key, obj);
		}
		resultset.close();
		connection.close();
	}
	
	public void writeDB() throws ClassNotFoundException, SQLException {
		Connection connection=DBAccess.getConnection();
		Statement statement=connection.createStatement();
		String delete="delete from t_flightinfo";
		statement.executeUpdate(delete);
		for(String key:this.container.keySet()) {
			String flightname=key;
			String company=this.container.get(key).getCompany();
			double price=this.container.get(key).getPrice();
			String takeofftime=this.container.get(key).getTakeofftime();
			String takeofflocation=this.container.get(key).getTakeofflocation();
			String landtime=this.container.get(key).getLandtime();
			String landlocation=this.container.get(key).getLandlocation();
			String passtime=this.container.get(key).getPasstime();
			String passlocation=this.container.get(key).getPasslocation();
			String insertion="insert into t_flightinfo values (\'"+flightname+"\',\'"+company+"\',"+price+",\'"+takeofftime+"\',\'"+takeofflocation+"\',\'"+landtime+"\',\'"+
			landlocation+"\',\'"+passtime+"\',\'"+passlocation+"\')";
			statement.executeUpdate(insertion);
		}
		connection.close();
	}
}
