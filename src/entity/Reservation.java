package entity;

/**
 * ������
 * @author ypq
 * @version 1.0
 */
public class Reservation {
	
	private String username=null;     //�û���
	private String flightname=null;   //�����
	private String time=null;         //�µ�ʱ��

	/**
	 * �޲ι��췽��
	 */
	public Reservation() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * �вι��췽��
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
