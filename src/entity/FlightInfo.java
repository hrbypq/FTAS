package entity;

/**
 * ������Ϣʵ����
 * @author ypq
 * @version 1.0
 */
public class FlightInfo {

	private String flightname=null;  //�����
	private String company=null;     //���չ�˾
	private double price=0;          //Ʊ��
	private String takeofftime=null; //���ʱ��
	private String takeofflocation=null;  //��ɳ���
	private String landtime=null;    //���ʱ��
	private String landlocation=null; //��س���
	private String passtime=null;    //��ͣʱ��
	private String passlocation=null; //��ͣ����
	
	/**
	 * ���췽��
	 */
	public FlightInfo() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * �вι��췽��
	 * @param flightname
	 * @param company
	 * @param price
	 * @param takeofftime
	 * @param takeofflocation
	 * @param landtime
	 * @param landlocation
	 * @param passtime
	 * @param passlocation
	 */
	public FlightInfo(String flightname, String company, double price, String takeofftime, String takeofflocation,
			String landtime, String landlocation, String passtime, String passlocation) {
		super();
		this.flightname = flightname;
		this.company = company;
		this.price = price;
		this.takeofftime = takeofftime;
		this.takeofflocation = takeofflocation;
		this.landtime = landtime;
		this.landlocation = landlocation;
		this.passtime = passtime;
		this.passlocation = passlocation;
	}

	public String getFlightname() {
		return flightname;
	}

	public void setFlightname(String flightname) {
		this.flightname = flightname;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getTakeofftime() {
		return takeofftime;
	}

	public void setTakeofftime(String takeofftime) {
		this.takeofftime = takeofftime;
	}

	public String getTakeofflocation() {
		return takeofflocation;
	}

	public void setTakeofflocation(String takeofflocation) {
		this.takeofflocation = takeofflocation;
	}

	public String getLandtime() {
		return landtime;
	}

	public void setLandtime(String landtime) {
		this.landtime = landtime;
	}

	public String getLandlocation() {
		return landlocation;
	}

	public void setLandlocation(String landlocation) {
		this.landlocation = landlocation;
	}

	public String getPasstime() {
		return passtime;
	}

	public void setPasstime(String passtime) {
		this.passtime = passtime;
	}

	public String getPasslocation() {
		return passlocation;
	}

	public void setPasslocation(String passlocation) {
		this.passlocation = passlocation;
	}
	
	
}
