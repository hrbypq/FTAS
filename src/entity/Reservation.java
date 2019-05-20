package entity;

/**
 * 订单类
 * @author ypq
 * @version 1.0
 */
public class Reservation {
	
	private String username=null;     //用户名
	private String flightname=null;   //航班号
	private String time=null;         //下单时间

	/**
	 * 无参构造方法
	 */
	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 有参构造方法
	 * @param username
	 * @param flightname
	 * @param time
	 */
	public Reservation(String username, String flightname, String time) {
		super();
		this.username = username;
		this.flightname = flightname;
		this.time = time;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFlightname() {
		return flightname;
	}

	public void setFlightname(String flightname) {
		this.flightname = flightname;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

}
