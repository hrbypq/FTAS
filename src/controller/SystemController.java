package controller;


import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import container.Airline;
import entity.FlightInfo;

/**
 * ϵͳ������
 * @author lhy
 * @version 1.0
 */
/**
 *��ŵ����̾����ȵ���CompareTime/Order�Ƚ�Ȼ���getResultȡ�ý��
 *Ĭ��ֻ��תһ��
*/
public class SystemController {
	private Airline airline=null;
	private Map<String,FlightInfo> flightinfocontainer=null;
	private String midCity;//��ת���У��ٶ�ֻ��תһ��
	/**
	 *�����Ƽ�����Ľ������Ҫ�õ�����get����
	 */
	private FlightInfo result1=null;
	private FlightInfo result2=null;
	/**
	 * ���ݷ���ʱ��Ƚϵ��ڲ������࣬�������ȶ���
	 */
	class TimeOrder {
		private PriorityQueue<FlightInfo>thefirst=null;//��һ�κ���
		private PriorityQueue<FlightInfo>thesecond=null;//�ڶ��κ���
		public TimeOrder() {
			this.thefirst=new PriorityQueue<FlightInfo>(new TimeCompare());
			this.thesecond=new PriorityQueue<FlightInfo>(new TimeCompare());
		}
		public void addFirst(FlightInfo flight) {
			thefirst.add(flight);
		}
		public void addSecond(FlightInfo flight) {
			thesecond.add(flight);
		}
		public FlightInfo getFirst() {
			return thefirst.peek();
		}
		public FlightInfo getSecond() {
			return thesecond.peek();
		}
		class TimeCompare implements Comparator<FlightInfo>{
		public int compare(FlightInfo o1, FlightInfo o2) {
				double a1=Double.parseDouble(o1.getTakeofftime());
				double a2=Double.parseDouble(o1.getLandtime());
        		double b1=Double.parseDouble(o2.getTakeofftime());
        		double b2=Double.parseDouble(o2.getLandtime());
				double i =(a2-a1)-(b2-b1);
				return (int)i;
			}
		}
	
		
	}
	/**
	 * ���ݼ۸�Ƚϵ��ڲ�������
	 */
	class PriceOrder {
		private PriorityQueue<FlightInfo>thefirst=null;//��һ�κ���
		private PriorityQueue<FlightInfo>thesecond=null;//�ڶ��κ���
		public PriceOrder() {
			this.thefirst=new PriorityQueue<FlightInfo>(new PriceCompare());
			this.thesecond=new PriorityQueue<FlightInfo>(new PriceCompare());
		}
		public void addFirst(FlightInfo flight) {
			thefirst.add(flight);
		}
		public void addSecond(FlightInfo flight) {
			thesecond.add(flight);
		}
		public FlightInfo getFirst() {
			return thefirst.peek();
		}
		public FlightInfo getSecond() {
			return thesecond.peek();
		}
		class PriceCompare implements Comparator<FlightInfo>{
		public int compare(FlightInfo o1,FlightInfo o2) { //������С����
				double a=o1.getPrice();
				double b=o2.getPrice();
				double i=a-b;
				return (int)i;
			}
		}
	}
	/**
	 * ���췽��
	 */
	public SystemController(Airline airline,Map<String,FlightInfo> container) {
		this.airline=airline;
		this.flightinfocontainer=container;
		// TODO Auto-generated constructor stub
	}
	/**
	 * ȷ��ת�����ߣ�����ͼ�Ĺ����������
	 */
	public boolean sereachPath(String startCity,String endCity) {
		boolean whether_exist=false;
		Set<String>midCity=airline.getAdjacent(startCity);
		/**
		 * �ж��Ƿ񷵻ؿ�ֵ
		 */
		if(midCity.isEmpty())
			return whether_exist;
		/**
		 * ����midCity��ÿһ�����У����ж���Щ���е��ڽӱ��Ƿ����endCity
		 */
		for(String city:midCity) {
			whether_exist=airline.whetherHaveCity(city, endCity);
			if(whether_exist==true) {
				this.midCity=city;//�޸�midCityֵ
				return whether_exist;
			}
		}
		return whether_exist;
	}
	/**
	  * ����ʱ��Ƚ�,�ɹ��򷵻�true
	 * 
	 */
	public boolean compareTime(String start,String end) {
		TimeOrder tool=new TimeOrder();
		if(sereachPath(start, end)==false)
			return false;
		// ƥ����ɵص�ͽ���ص�
		for(FlightInfo flight:flightinfocontainer.values()) {
			if(start.equals(flight.getTakeofflocation())&&midCity.equals(flight.getLandlocation()))
				tool.addFirst(flight);
			if(midCity.equals(flight.getTakeofflocation())&&end.equals(flight.getLandlocation()))
				tool.addSecond(flight);
		}
		result1=tool.getFirst();
		result2=tool.getSecond();
		return true;
	}
	/**
	  * ���ݼ۸�Ƚ�,�ɹ��򷵻�true
	 * 
	 */
	public boolean comparePrice(String start,String end) {
		PriceOrder tool=new PriceOrder();
		if(sereachPath(start, end)==false)
			return false;
		//ƥ����ɵص�ͽ���ص�
		for(FlightInfo flight:flightinfocontainer.values()) {
			if(start.equals(flight.getTakeofflocation())&&midCity.equals(flight.getLandlocation()))
				tool.addFirst(flight);
			if(midCity.equals(flight.getTakeofflocation())&&end.equals(flight.getLandlocation()))
				tool.addSecond(flight);
		}
		result1=tool.getFirst();
		result2=tool.getSecond();
		return true;
	}
	/**
	 * ��ý���ĺ���
	 */
	public FlightInfo getResult1() {
		return result1;
	}
	public FlightInfo getResult2() {
		return result2;
	}
	public String getMidCity() {
		return midCity;
	}
}
