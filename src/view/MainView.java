package view;

import container.*;
import controller.*;
import java.sql.*;
import java.util.*;
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
	public List<Message> messagebox=null;       //延误消息容器
	
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
		messagebox=new ArrayList<Message>();
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
	 * 发布航班延误信息
	 */
	private void flightDelay() {
		System.out.println("请输入航班号:");
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		String flightname=scanner.nextLine();
		boolean find=flightinfocontainer.getContainer().containsKey(flightname);
		if(!find) {
			System.out.println("航班不存在! 返回上一级菜单");
			return;
		}
		System.out.println("请输入具体延误信息:");
		String info=scanner.nextLine();
		Message message=new Message(info,flightname);
		messagebox.add(message);
		System.out.println("延误信息已添加!");
	}
	
	/**
	 * 根据情况输出延误信息
	 */
	private void printDelayInfo() {
		List<Reservation> reservation=reservationcontainer.getContainer().get(currentuser.getUsername());
		boolean signal=false;
		for(Message delayflight:messagebox) {
			for(Reservation reserveflight:reservation) {
				if(reserveflight.getFlightname().equals(delayflight.getFlightname())) {
					signal=true;
					System.out.println(delayflight.getFlightname()+"航班延误");
					System.out.println(delayflight.getInfo());
					System.out.println();
					if(signal) {
						TicketController ticketcontroller=new TicketController(ticketcontainer.getContainer(),reservationcontainer.getContainer(),flightinfocontainer.getContainer(),appointmentcontainer.getContainer());
						String takeofflocation=flightinfocontainer.getContainer().get(delayflight.getFlightname()).getTakeofflocation();
						String landlocation=flightinfocontainer.getContainer().get(delayflight.getFlightname()).getLandlocation();
						ticketcontroller.recommendFlightView(takeofflocation, landlocation);
					}
				}
			}
		}
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
						this.flightDelay();
						break;
					}
					
					}
					//switch ends
				}
			}
			//管理员菜单结束
			
			
			//普通用户模式
			else {
				//先判断是否播报延误信息
				this.printDelayInfo();
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
