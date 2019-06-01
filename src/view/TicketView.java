package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import container.*;
import controller.*;
import entity.*;

/**
 * 票务界面类
 * @author
 * @version 1.0
 */
public class TicketView {

	private FlightInfoContainer flightinfocontainer=null;
	private UserInfo currentuser=null;
	private TicketController ticketcontroller=null;
	private Tools tools=null;
	private TicketContainer ticketcontianer =null;
	private AppointmentContainer appointmentcontainer= null;
	private ReservationContainer reservationcontainer=null;
	
	/**
	 * 构造方法
	 */
	public TicketView(FlightInfoContainer flightinfocontainer,TicketContainer ticketcontainer,UserInfo currentuser,AppointmentContainer appointmentcontainer,ReservationContainer reservationcontainer) {
		this.flightinfocontainer=flightinfocontainer;
		this.ticketcontianer= ticketcontainer;
		this.currentuser=currentuser;
		this.appointmentcontainer=appointmentcontainer;
		ticketcontroller=new TicketController(ticketcontainer.getContainer(),reservationcontainer.getContainer(), flightinfocontainer.getContainer(), appointmentcontainer.getContainer());
	}

	/**
	 * 菜单
	 */
	public void showMenu() {
	    while(true) {
	    	System.out.println("1.查询余票量\n2.买票\n3.退票\n4.查询订单\n5.查询指定航线航班票务情况\n6.返回上一层");
		    int input=Tools.inputInteger(1, 6);
		    switch (input) {
		    case 1:
                  this.getTicketAmountMenu();
			      break;
		     case 2:{
		    	 this.buyTicketMenu();
		    	 break;
		     }
		     case 3:{
		    	 this.refundTicketMenu();
		    	 break;
		     }
		     case 4:{
		    	 this.searchReservationMenu();
		    	 break;
		     }
		     case 5:{
		    	 this.searchFlight();
		    	 break;
		     }
		     case 6:
		    	 return;
		     }
	    }
	}

	private void searchFlight() {
		List<FlightInfo> flight= new ArrayList<FlightInfo>();
		System.out.println("请输入起飞城市");
		@SuppressWarnings("resource")
		Scanner scanner =new Scanner(System.in);
		String takeofflocation=null;
		takeofflocation=scanner.nextLine();
		System.out.println("请输入降落城市");
	    String landlocation=null;
	    landlocation=scanner.nextLine();
	    flight=ticketcontroller.searchFlightByCity(takeofflocation, landlocation);
	    if(flight.size()==0) {
	    	System.out.println("无直飞航班");
	    	return;
	    }
    	System.out.println("选择排序方式：1.余票量 2.价格 3.起飞时间 4.降落时间");
	    int input=Tools.inputInteger(1, 4);
        if(input==1) {flight=ticketcontroller.orderFlightByAmount(flight);}
        if(input==2) {flight=ticketcontroller.orderFlightByPrice(flight);}
        if(input==3) {flight=ticketcontroller.orderFlightByTakeOffTime(flight);}
        if(input==4) {flight=ticketcontroller.orderFlightByLandTime(flight);}
        for(int i=0;i<flight.size();i++) {
        	printFlightInfo(flight.get(i));
        	System.out.println("\n");
        }
	}

	private void refundTicketMenu() {
		System.out.println("请输入要退票的航班号");
		@SuppressWarnings("resource")
		Scanner scanner =new Scanner(System.in);
		String flightname;
		flightname=scanner.nextLine();
		boolean result=ticketcontroller.refundTicket(currentuser.getUsername(),flightname);
		if(result==false) {
			System.out.println("您未购买该航班机票");
		}
		else {
			System.out.println("您已成功退票");
			//ticketcontroller.changeAppointment(flightname);
		}
		return;
	}

	private void searchReservationMenu() {
		List<Reservation> ticketlist=ticketcontroller.searchReservation(currentuser.getUsername());
		if(ticketlist==null) {
			System.out.println("您暂未购票");
			return;
		}
		System.out.println("您的订单如下：");
	   	for(int i=0;i<ticketlist.size();i++) {
    		Reservation target=ticketlist.get(i);
            printReservation(target);
    	}
	   	System.out.println();
	   	return;
    }

	private void buyTicketMenu() {
		System.out.println("请输入要购买的航班号");
		@SuppressWarnings("resource")
		Scanner scanner =new Scanner(System.in);
		String flightname;
		int choose;
		flightname=scanner.nextLine();
		boolean result=ticketcontroller.buyTicket(flightname, currentuser.getUsername());
		if(result==true) {
			System.out.println("您已成功购买"+flightname+"航班机票\n");
		}
		else {
            System.out.println("可为您预约抢票，预约请输入1，输入其他自动返回上一层");
            choose=scanner.nextInt();
            if(choose==1) {
            	ticketcontroller.reserveTicket(flightname, currentuser.getUsername());
            }
            else {
            	return;
            }
		}
		return;
	}

	private void getTicketAmountMenu() {
		System.out.println("请输入要查询的航班号");
		@SuppressWarnings("resource")
		Scanner scanner =new Scanner(System.in);
		String flightname=null;
		flightname=scanner.nextLine();
		int amount=0;
		amount=ticketcontroller.getTicketAmount(flightname);
		if(amount==-1) {
			System.out.println("你查询的航班不存在");
		}
		else {
			System.out.println("您所查询的航班余票量为："+amount);
			System.out.println();
		}
		return;
	}
  
	private void printFlightInfo(FlightInfo flight) {
		System.out.println("航班号："+flight.getFlightname());
		System.out.println("起飞城市："+flight.getTakeofflocation());
		System.out.println("起飞时间："+flight.getTakeofftime());
		System.out.println("降落城市："+flight.getLandlocation());
		System.out.println("降落时间："+flight.getLandtime());
		System.out.println("航空公司："+flight.getCompany());
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

	private void printReservation(Reservation reservation) {
		System.out.println("航班号："+reservation.getFlightname()+"   "+"下单时间"+reservation.getTime());
	}
}
