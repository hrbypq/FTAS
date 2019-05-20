package controller;
import java.util.Map;

import container.*;
import entity.*;

/**
 * 航班信息控制类
 * @author
 * @version 1.0
 */
public class FlightInfoController {
	private Map<String,FlightInfo> container=null;//用于操作容器的引用
	/**
	 * 构造方法
	 */
	public FlightInfoController(Map<String,FlightInfo> container) {
		this.container=container;
		// TODO Auto-generated constructor stub
	}
	//参数列表里的container 从FlightInfoContainer的get方法获得

	/**
	 * 增加航班信息
	 * @param flightname 航班号 不可重复
	 * @param company 航班公司
	 * @param price 票价
	 * @param takeofftime 起飞时间
	 * @param takeofflocation 起飞城市
	 * @param landtime 落地时间
	 * @param landlocation 落地城市
	 * @param pastime 经停时间
	 * @param passlocation 经停城市
	 * @return 若添加成功返回true
	 */
	public boolean createFlightInfo(String flightname,String company,double price,String takeofftime,String takeofflocation,String landtime,String landlocation,String pastime,String passlocation) {
		FlightInfo new_flight=new FlightInfo (flightname,company,price,takeofftime,takeofflocation,landtime,landlocation,pastime,passlocation);
		if(!container.containsKey(flightname)) {
			container.put(flightname,new_flight);
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * 查询航班信息
	 * @param flightname 航班号
	 * @return 若查询成功返回一航班实体对象 失败返回null
	 */
	public FlightInfo searchFlightInfo(String flightname) {
		FlightInfo obj=null;
		obj=container.get(flightname);
		return obj;
	}
	
	/**
	 * 更新航班信息
	 * @param flightname 航班号
	 * @param company 航班公司
	 * @param price 票价
	 * @param takeofftime 起飞时间
	 * @param takeofflocation 起飞城市
	 * @param landtime 落地时间
	 * @param landlocation 落地城市
	 * @param pastime 经停时间
	 * @param passlocation 经停城市
	 * @return 若更新成功返回true
	 */
	public boolean updateFlightInfo(String flightname,String company,double price,String takeofftime,String takeofflocation,String landtime,String landlocation,String pastime,String passlocation) {
		if(!container.containsKey(flightname))
			return false;
		else {
			FlightInfo update_flight= new FlightInfo(flightname,company,price,takeofftime,takeofflocation,landtime,landlocation,pastime,passlocation);
			container.put(flightname, update_flight);
			return true;
		}
	}
	
	/**
	 * 删除航班信息
	 * @param flightname 航班号
	 * @return 是否删除成功
	 */
	public boolean deleteFlightInfo(String flightname) {
		if(container.containsKey(flightname)) {
			container.remove(flightname);
			return true;
		}
		else
		    return false;
	}
	
}
