package view;

import java.util.Scanner;

import org.omg.CORBA.PRIVATE_MEMBER;

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
	private Tools tools=null;
	/**
	 * 构造方法
	 */
	public FlightView(FlightInfoContainer flightinfocontainer,UserInfo currentuser) {
		// TODO Auto-generated constructor stub
		this.currentuser=currentuser;
		this.flightinfocontainer=flightinfocontainer;
		this.tools=new Tools();
		flightcontroller=new FlightInfoController(flightinfocontainer.getContainer());
	}
	
	/**
	 * 用户菜单
	 */
	public void showTouristMenu() {
	    while(true) {
	    	System.out.println("票务业务输入1，航班信息查询输入2,返回上层输入3\n");
		    int input=Tools.inputInteger(1, 3);
		    switch (input) {
		    case 1:
			   /*
			     *票务
			   */
			      break;
		     case 2:{
		    	 this.flightTourMenu();
		    	 break;
		     }
		     case 3:
		    	 return;
		     default:
		    	 break;
		     }
	    }
	}
	/**
	 * 管理员菜单
	 */
	public void showAdminMenu() {
		while(true) {
			System.out.println("航班信息管理输入1，票务管理输入2,返回上层输入3\n");
			int input=tools.inputInteger(1, 3);
			switch (input) {
			case 1:{
				this.flightInfoAdmin();
				break;
			}
			case 2:
				/*
				 * 票务管理
				 */
			default:
				break;
			}
		}
		
	}
	/**
	 * 用户查询航班信息
	 */
	private void flightTourMenu() {
	while (true) {
		System.out.println("根据航班号查询航班信息输入1，航班推荐输入2,返回上层输入3\n");
		int input=Tools.inputInteger(1, 3);
		switch (input) {
		case 1:
			this.searchFlight();
			break;
		case 2:{
			/*
			 * 推荐
			 */
			break;
		}
		case 3:
			return;
		default:
			break;
		}
	}	
	}
	/**
	 * 管理员处理航班信息
	 */
	private void flightInfoAdmin() {
		System.out.println("增加航班输入1，删除航班输入2,修改航班输入3,根据航班号查看航班输入4，退出输入5\n");
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
		default:
			break;
		}
	}
	
	/**
	 * 打印航班基本信息
	 */
	private void printInfo(FlightInfo flight) {
		System.out.println("航班号："+flight.getFlightname());
		System.out.println("起飞机场："+flight.getTakeofflocation());
		System.out.println("起飞时间："+flight.getTakeofftime());
		System.out.println("降落机场："+flight.getLandlocation());
		System.out.println("降落时间："+flight.getTakeofflocation());
		System.out.println("航空公司："+flight.getCompany());
		System.out.println("价格："+flight.getPrice());
	}
	/**
	 * 根据航班号查询航班
	 */
	private void searchFlight() {
		System.out.println("请输入要查询信息的航班号:\n");
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
	private void addFlight() {
		while(true) {
			Scanner scanner=new Scanner(System.in);
			System.out.println("请输入航班号\n");
			String flightname=scanner.nextLine();
			System.out.println("请输入航空公司\n");
			String company=scanner.nextLine();
			System.out.println("请输入价格\n ");
			double price=scanner.nextDouble(); 
			System.out.println("请输入起飞时间\n");
			String takeofftime=scanner.nextLine();
			System.out.println("请输入起飞机场 \n");
			String takeofflocation=scanner.nextLine();
			System.out.println("请输入降落时间\n");
			String landtime=scanner.nextLine();
			System.out.println("请输入降落地\n ");
			String landlocation=scanner.nextLine();
			System.out.println("请输入经停时间\n");
			String passtime=scanner.nextLine();
			System.out.println("请输入经停地\n");
			String passlocation=scanner.nextLine();
		    boolean whther_suceed=flightcontroller.createFlightInfo(flightname, company, price, takeofftime, takeofflocation, landtime, landlocation, passtime, passlocation);
		    if(whther_suceed)
		    	System.out.println("增加成功");
		    else {
				System.out.println("航班已存在");
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
				System.out.println("请输入航空公司\n");
				String company=scanner.nextLine();
				System.out.println("请输入价格\n ");
				double price=scanner.nextDouble(); 
				System.out.println("请输入起飞时间\n");
				String takeofftime=scanner.nextLine();
				System.out.println("请输入起飞机场 \n");
				String takeofflocation=scanner.nextLine();
				System.out.println("请输入降落时间\n");
				String landtime=scanner.nextLine();
				System.out.println("请输入降落地\n ");
				String landlocation=scanner.nextLine();
				System.out.println("请输入经停时间\n");
				String passtime=scanner.nextLine();
				System.out.println("请输入经停地\n");
				String passlocation=scanner.nextLine();
			    boolean whther_suceed=flightcontroller.updateFlightInfo(flightname, company, price, takeofftime, takeofflocation, landtime, landlocation, passtime, passlocation);
			    if(whther_suceed)
			    	System.out.println("修改成功");
			    else {
					System.out.println("修改失败");
				}
			}
			else {
				System.out.println("不存在此航班\n");
			}
		}
	}
		
	/**
	 * 删除航班
	 */
	@SuppressWarnings("resource")
	private void deleteFlight() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("请输入航班号\n");
		String flightname=scanner.nextLine();
		boolean whether_suceed=flightcontroller.deleteFlightInfo(flightname);
		if(whether_suceed)
			System.out.println("删除成功\n");
		else 
			System.out.println("删除失败\n");
	}

}
	
