package entity;

/**
 * 消息实体类
 * @author
 * @version 1.0
 */
public class Message {

	private String info=null;               //延误信息 由管理员手写 
	private String flightname=null;         //航班号
	
	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getFlightname() {
		return flightname;
	}

	public void setFlightname(String flightname) {
		this.flightname = flightname;
	}

	/**
	 * 构造方法
	 */
	public Message(String info,String flightname) {
		this.info=info;
		this.flightname=flightname;
	}

}
