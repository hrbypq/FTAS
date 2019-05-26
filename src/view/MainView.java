package view;

import container.*;
import controller.*;

import java.io.Reader;
import java.sql.*;
import java.util.Scanner;

import entity.*;

/**
 * 主界面类
 * @author
 * @version 1.0
 */
public class MainView {
	public UserInfoContainer userinfocontainer=null;
	public FlightInfoContainer flightinfocontainer=null;
	public TicketContainer ticketcontainer=null;
	public ReservationContainer reservationcontainer=null;
	public UserInfo currentuser=null;
	public Tools tool=null;//用于输入数据的工具类
	
	/**
	 * 构造方法
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public MainView() throws ClassNotFoundException, SQLException {
		this.init();
	}
	/**
	 * 程序初始化
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void init() throws ClassNotFoundException, SQLException {
		userinfocontainer=new UserInfoContainer();
		flightinfocontainer=new FlightInfoContainer();
		ticketcontainer=new TicketContainer();
		reservationcontainer=new ReservationContainer();
		/**
		 * 从数据库读取数据
		 */
		userinfocontainer.readDB();
		reservationcontainer.readDB();
		ticketcontainer.readDB();
		flightinfocontainer.readDB();
		/**
		 * 初始化工具类和控制类
		 */
	     tool=new Tools();
	}
	/**
	 * 菜单
	 */
	public void showMenu() {
		while (true) {
			System.out.println("管理员操作输入“1”，普通旅客操作输入“2”\n");
			int choice=0;
			choice=Tools.inputInteger(1, 2);
			switch (choice) {
			//管理员情形
			case 1:{
			    UserInfoView userUI=new UserInfoView(userinfocontainer, null);
				userUI.showAdminMenu();
				currentuser=userUI.getUser();
				break;
				}
			case 2:{
				UserInfoView userUI=new UserInfoView(userinfocontainer, null);
				userUI.showTouristMenu();
				currentuser=userUI.getUser();
				/**
				 * 如果currentuser是null则刚才进行的是用户登陆，直接回退
				 * 到主界面，反之则进行下一步操作
				 */
				if(currentuser==null)
					break;
				else {
					FlightView fightUI=new FlightView(flightinfocontainer, currentuser);
					fightUI.showTouristMenu();
				}
			}
			default:
				break;
			}
		}
	}
}
