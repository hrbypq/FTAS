package view;

import container.*;
import controller.*;
import java.sql.*;
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
	public AppointmentContainer appointmentcontainer=null;
	
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
	private void init() throws ClassNotFoundException, SQLException {
		//生成容器类并从数据库中读取数据
		userinfocontainer=new UserInfoContainer();
		flightinfocontainer=new FlightInfoContainer();
		ticketcontainer=new TicketContainer();
		reservationcontainer=new ReservationContainer();
		appointmentcontainer=new AppointmentContainer();
		userinfocontainer.readDB();
		reservationcontainer.readDB();
		ticketcontainer.readDB();
		flightinfocontainer.readDB();
	}
	
	/**
	 * 程序终止
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void terminate() throws ClassNotFoundException, SQLException {
		userinfocontainer.writeDB();
		flightinfocontainer.writeDB();
		ticketcontainer.writeDB();
		reservationcontainer.writeDB();
	}
	
	/**
	 * 菜单
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void showMenu() throws ClassNotFoundException, SQLException {
		System.out.println("欢迎使用本系统!\n");
		
		//主菜单大循环
		while (true) {
			System.out.println("主界面:");
			System.out.println("1.管理员操作\n2.普通用户操作\n3.退出系统\n请输入选项:");
			int choice=0;
			choice=Tools.inputInteger(1,3);
			switch (choice) {
			
			//管理员
			case 1:{
			    UserInfoView userUI=new UserInfoView(userinfocontainer);
				userUI.showAdminMenu();           
				currentuser=new UserInfo("root","123456");            //管理员返回一用户名为root的UserInfo对象
				break; 
			}
			
			//用户
			case 2:{
				UserInfoView userUI=new UserInfoView(userinfocontainer);
				userUI.showUserMenu();
				currentuser=userUI.getUser();
				break;
			}
			
			//退出程序
			case 3:{
				this.terminate();
				return;
			}
			
			}
			//switch ends
			
			//管理员模式
			if(currentuser.getUsername().equals("root")) {
				//管理员菜单循环
				while(true) {
					System.out.println("管理员菜单:\n1.航班信息及票务管理\n2.发布延误信息\n3.返回主菜单");
					choice=Tools.inputInteger(1, 3);
					if(choice==3)    //返回主菜单
						break;
					switch(choice) {
					
					//航班信息及票务管理
					case 1:{
						FlightView flightUI=new FlightView(flightinfocontainer,currentuser,ticketcontainer);
						flightUI.showAdminMenu();
						break;
					}
					
					//发布延误信息
					case 2:{
						System.out.println("施工中");
						break;
					}
					
					}
					//switch ends
				}
			}
			//管理员菜单结束
			
			
			//普通用户模式
			else {
				//用户菜单循环
				while(true) {
					System.out.println("用户菜单:\n1.票务相关\n2.航班信息查询\n3.返回主菜单");
					choice=Tools.inputInteger(1, 3);
					if(choice==3)  //返回主菜单
						break;
					switch(choice) {
					
					//票务
					case 1:{
						TicketView ticketUI=new TicketView(flightinfocontainer,ticketcontainer,currentuser,appointmentcontainer,reservationcontainer);
						ticketUI.showMenu();
						break;
					}
					
					//航班信息
					case 2:{
						FlightView flightUI=new FlightView(flightinfocontainer,currentuser,ticketcontainer);
						flightUI.showTouristMenu();
						break;
					}
					
					}
					//switch ends
				}
			}
			//用户菜单结束
		}
		//主菜单循环结束
		
	}
}
