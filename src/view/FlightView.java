package view;

import java.util.Scanner;
import container.*;
import controller.*;
import entity.*;

/**
 * 航班信息界面类
 * @author
 * @version 1.0
 */
public class FlightView {
	
	private FlightInfoContainer flightinfocontainer=null;
	private UserInfo currentuser=null;
	private FlightInfoController flightcontroller=null;
	private TicketController ticketcontroller=null;
	//private TicketContainer ticketcontainer=null;
	private Airline airline=null;
	
	/**
	 * 构造方法
	 */
	public FlightView(FlightInfoContainer flightinfocontainer,UserInfo currentuser,TicketContainer ticketcontainer) {
		this.currentuser=currentuser;
		this.flightinfocontainer=flightinfocontainer;
		ticketcontroller=new TicketController(ticketcontainer.getContainer(),null,flightinfocontainer.getContainer(),null);
		flightcontroller=new FlightInfoController(flightinfocontainer.getContainer());
		airline=new Airline(flightinfocontainer.getContainer());
	}
	
	/**
	 * 用户菜单
	 */
	public void showTouristMenu() {
	    this.flightTourMenu();
	}
	
	/**
	 * 管理员菜单
	 */
	public void showAdminMenu() {
		while(true) {
			System.out.println("1.航班信息管理\n2.票务管理\n3.返回上一层");
			int input=Tools.inputInteger(1, 3);
			if(input==3)     //返回上一层
				break;
			switch (input) {
			case 1:{
				this.flightInfoAdmin();
				break;
			}
			case 2:{
				this.setTicketMenu();
				break;
			}
			}
		}
		
	}
	
	@SuppressWarnings("resource")
	private void setTicketMenu() {
		System.out.println("请输入要设置的航班号");
		Scanner scanner= new Scanner(System.in);
		String flightname=scanner.nextLine();
		System.out.println("请输入要设置的余票量");
		int amount=scanner.nextInt();
		if(ticketcontroller.setTicketAmount(flightname, amount)) {
			System.out.println("设置成功!");
		}
		else {
			System.out.println("设置失败!");
		}
	}

	/**
	 * 用户查询航班信息
	 */
	private void flightTourMenu() {
		while (true) {
			System.out.println("1.根据航班号查询航班信息\n2.航班推荐(针对无直达的航线)\n3.返回上一层");
			int input=Tools.inputInteger(1, 3);
			switch (input) {
			case 1:
				this.searchFlight();
				break;
			case 2:{
				this.recommendFlight();
				break;
			}
			case 3:
				return;
			}
		}	
	}
	
	/**
	 * 管理员处理航班信息
	 */
	private void flightInfoAdmin() {
		System.out.println("1.增加航班\n2.删除航班\n3.修改航班信息\n4.查看指定航班信息\n5.返回上一层");
		int input=Tools.inputInteger(1, 5);
		switch (input) {
		case 1:{
			this.addFlight();
			break;
		}
		case 2:{
			this.deleteFlight();
			break;
		}
		case 3:{
			this.updateFlight();
			break;
		}
		case 4:{
			this.searchFlight();
			break;
		}
		case 5:
			return;
		}
	}
	
	/**
	 * 打印航班基本信息
	 */
	private void printInfo(FlightInfo flight) {
		System.out.println("航班号："+flight.getFlightname());
		System.out.println("航空公司："+flight.getCompany());
		System.out.println("起飞城市："+flight.getTakeofflocation());
		System.out.println("起飞时间："+flight.getTakeofftime());
		System.out.println("降落城市："+flight.getLandlocation());
		System.out.println("降落时间："+flight.getLandtime());
		System.out.println("价格："+flight.getPrice());
		if(flight.getPasslocation()==null||flight.getPasstime()==null||flight.getPasslocation().equals("null")||flight.getPasstime().equals("null")) {
			System.out.println("经停城市：无");
			System.out.println("经停时间：无");
		}
		else {
			System.out.println("经停城市："+flight.getPasslocation());
			System.out.println("经停时间："+flight.getPasstime());
		}
		System.out.println();
	}
	
	/**
	 * 根据航班号查询航班
	 */
	private void searchFlight() {
		System.out.println("请输入要查询信息的航班号:");
		@SuppressWarnings("resource")
		Scanner scanner =new Scanner(System.in);
		String flightname=scanner.nextLine();
		FlightInfo flight=flightcontroller.searchFlightInfo(flightname);
		if(flight==null) {
			System.out.println("航班不存在\n");
		}
		else {
			this.printInfo(flight);
		}
		}
	
	/**
	 * 增加航班
	 */
	@SuppressWarnings("resource")
	private void addFlight() {
		Scanner scanner=new Scanner(System.in);
		while(true) {
			System.out.println("请输入航班号:");
			String flightname=scanner.nextLine();
			
			System.out.println("请输入航空公司:");
			String company=scanner.nextLine();
			
			System.out.println("请输入起飞城市:");
			String takeofflocation=scanner.nextLine();
			
			System.out.println("请输入起飞时间:");
			String takeofftime=scanner.nextLine();
			
			System.out.println("请输入降落城市:");
			String landlocation=scanner.nextLine();
			
			System.out.println("请输入降落时间:");
			String landtime=scanner.nextLine();
			
			System.out.println("请输入价格:");
			double price=scanner.nextDouble();
			scanner=new Scanner(System.in);
			
			System.out.println("请输入经停城市:(若无请输入111)");
			String passlocation=scanner.nextLine();
			if(passlocation.equals("111"))
				passlocation=null;
			
			System.out.println("请输入经停时间:(若无请输入111)");
			String passtime=scanner.nextLine();
			if(passtime.equals("111"))
				passtime=null;
			
		    boolean whther_suceed=flightcontroller.createFlightInfo(flightname, company, price, takeofftime, takeofflocation, landtime, landlocation, passtime, passlocation);
		    if(whther_suceed) {
		    	System.out.println("增加成功");
		    	ticketcontroller.addFlight(flightname);
		    	break;
		    }
		    else {
				System.out.println("航班已存在");
				break;
			}
		}
	}
	
	/**
	 * 修改航班
	 */
	private void updateFlight() {
		while(true) {
			@SuppressWarnings("resource")
			Scanner scanner=new Scanner(System.in);
			System.out.println("请输入航班号\n");
			String flightname=scanner.nextLine();
			
			if(flightcontroller.searchFlightInfo(flightname)!=null) {
				System.out.println("请输入航空公司:");
				String company=scanner.nextLine();
				
				System.out.println("请输入起飞城市:");
				String takeofflocation=scanner.nextLine();
				
				System.out.println("请输入起飞时间:");
				String takeofftime=scanner.nextLine();
				
				System.out.println("请输入降落城市:");
				String landlocation=scanner.nextLine();
				
				System.out.println("请输入降落时间:");
				String landtime=scanner.nextLine();
				
				System.out.println("请输入价格:");
				double price=scanner.nextDouble();
				scanner=new Scanner(System.in);
				
				System.out.println("请输入经停城市:(若无请输入111)");
				String passlocation=scanner.nextLine();
				if(passlocation.equals("111"))
					passlocation=null;
				
				System.out.println("请输入经停时间:(若无请输入111)");
				String passtime=scanner.nextLine();
				if(passtime.equals("111"))
					passtime=null;
				
			    boolean whther_suceed=flightcontroller.updateFlightInfo(flightname, company, price, takeofftime, takeofflocation, landtime, landlocation, passtime, passlocation);
			    if(whther_suceed) {
			    	System.out.println("修改成功");
			    	break;
			    }
			    else {
					System.out.println("修改失败");
					break;
				}
			}
			else {
				System.out.println("不存在此航班");
				break;
			}
		}
	}
		
	/**
	 * 删除航班
	 */
	@SuppressWarnings("resource")
	private void deleteFlight() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("请输入航班号:");
		String flightname=scanner.nextLine();
		boolean whether_suceed=flightcontroller.deleteFlightInfo(flightname);
		if(whether_suceed) {
			System.out.println("删除成功\n");
			ticketcontroller.deleteFlight(flightname);
		}
		else 
			System.out.println("删除失败\n");
	}

	private void recommendFlight() {
		SystemController systemcontroller=new SystemController(airline,flightinfocontainer.getContainer());
		System.out.println("请输入起飞城市:");
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		String takeoffcity=scanner.nextLine();
		System.out.println("请输入目的地城市:");
		String landcity=scanner.nextLine();
		if(!systemcontroller.comparePrice(takeoffcity, landcity)) {
			System.out.println("两城市间无航线!");
			return;
		}
		System.out.println("按价格计算的推荐航班：");
		this.printInfo(systemcontroller.getResult1());
		System.out.println("中转城市："+systemcontroller.getMidCity()+'\n');
		this.printInfo(systemcontroller.getResult2());
		System.out.println();
		systemcontroller.compareTime(takeoffcity, landcity);
		System.out.println("按时间计算的推荐航班：");
		this.printInfo(systemcontroller.getResult1());
		System.out.println("中转城市："+systemcontroller.getMidCity());
		this.printInfo(systemcontroller.getResult2());
		System.out.println();
	}
	
}
	
