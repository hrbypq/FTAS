package entity;

/**
 * ��Ϣʵ����
 * @author
 * @version 1.0
 */
public class Message {

	private String info=null;               //������Ϣ �ɹ���Ա��д 
	private String flightname=null;         //�����
	
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
	 * ���췽��
	 */
	public Message(String info,String flightname) {
		this.info=info;
		this.flightname=flightname;
	}

}
