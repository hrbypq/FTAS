package controller;
import container.*;
import java.util.*;
import entity.*;

/**
 * 机票控制类
 * @author
 * @version 1.0
 */
public class TicketController {

	/**
	 * 构造方法
	 */
	public TicketController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * 查询余票量 
	 * @param flightname 航班号
	 * @return 余票量 查询失败返回-1
	 */
	public int getTicketAmount(String flightname) {
		return 0;
	}
	
	/**
	 * 供管理员设置余票量
	 * @param flightname 航班号
	 * @param amount 新的余票量
	 * @return 是否设置成功
	 */
	public boolean setTicketAmount(String flightname,int amount) {
		return true;
	}
	
	/**
	 * 旅客买票
	 * @param flightname 航班号
	 * @return 是否购买成功
	 */
	public boolean buyTicket(String flightname) {
		return true;
	}
	
	/**
	 * 预约购票
	 * @param flightname 航班号
	 */
	public void reserveTicket(String flightname) {
		
	}
	
	/**
	 * 查询订单
	 * @param username 用户名
	 * @return 包含订单类的ArrayList 查询失败返回null
	 */
	public List<Reservation> searchReservation(String username){
		List<Reservation> obj=null;
		return obj;
	}
}
