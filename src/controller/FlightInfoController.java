package controller;
import entity.*;

/**
 * 航班信息控制类
 * @author
 * @version 1.0
 */
public class FlightInfoController {

	/**
	 * 构造方法
	 */
	public FlightInfoController() {
		// TODO Auto-generated constructor stub
	}

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
		return true;
	}
	
	/**
	 * 查询航班信息
	 * @param flightname 航班号
	 * @return 若查询成功返回一航班实体对象 失败返回null
	 */
	public FlightInfo searchFlightInfo(String flightname) {
		FlightInfo obj=null;
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
		return true;
	}
	
	/**
	 * 删除航班信息
	 * @param flightname 航班号
	 * @return 是否删除成功
	 */
	public boolean deleteFlightInfo(String flightname) {
		return true;
	}
	
}
