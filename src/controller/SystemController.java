package controller;


import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;
import container.Airline;
import entity.FlightInfo;

/**
 * 系统控制类
 * @author lhy
 * @version 1.0
 */
/**
 *大概的流程就是先调用CompareTime/Order比较然后调getResult取得结果
 *默认只中转一次
*/
public class SystemController {
	private Airline airline=null;
	private Map<String,FlightInfo> flightinfocontainer=null;
	private String midCity;//中转城市，假定只中转一次
	/**
	 *保存推荐航班的结果，想要用调最后的get函数
	 */
	private FlightInfo result1=null;
	private FlightInfo result2=null;
	/**
	 * 根据飞行时间比较的内部工具类，用了优先队列
	 */
	class TimeOrder {
		private PriorityQueue<FlightInfo>thefirst=null;//第一段航班
		private PriorityQueue<FlightInfo>thesecond=null;//第二段航班
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
	 * 根据价格比较的内部工具类
	 */
	class PriceOrder {
		private PriorityQueue<FlightInfo>thefirst=null;//第一段航班
		private PriorityQueue<FlightInfo>thesecond=null;//第二段航班
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
		public int compare(FlightInfo o1,FlightInfo o2) { //这里是小根堆
				double a=o1.getPrice();
				double b=o2.getPrice();
				double i=a-b;
				return (int)i;
			}
		}
	}
	/**
	 * 构造方法
	 */
	public SystemController(Airline airline,Map<String,FlightInfo> container) {
		this.airline=airline;
		this.flightinfocontainer=container;
		// TODO Auto-generated constructor stub
	}
	/**
	 * 确定转机航线，利用图的广度优先搜索
	 */
	public boolean sereachPath(String startCity,String endCity) {
		boolean whether_exist=false;
		Set<String>midCity=airline.getAdjacent(startCity);
		/**
		 * 判断是否返回空值
		 */
		if(midCity.isEmpty())
			return whether_exist;
		/**
		 * 遍历midCity中每一个城市，并判断这些城市的邻接表是否存在endCity
		 */
		for(String city:midCity) {
			whether_exist=airline.whetherHaveCity(city, endCity);
			if(whether_exist==true) {
				this.midCity=city;//修改midCity值
				return whether_exist;
			}
		}
		return whether_exist;
	}
	/**
	  * 根据时间比较,成功则返回true
	 * 
	 */
	public boolean compareTime(String start,String end) {
		TimeOrder tool=new TimeOrder();
		if(sereachPath(start, end)==false)
			return false;
		// 匹配起飞地点和降落地点
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
	  * 根据价格比较,成功则返回true
	 * 
	 */
	public boolean comparePrice(String start,String end) {
		PriceOrder tool=new PriceOrder();
		if(sereachPath(start, end)==false)
			return false;
		//匹配起飞地点和降落地点
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
	 * 获得结果的函数
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
