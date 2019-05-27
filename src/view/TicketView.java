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
		// TODO Auto-generated constructor stub
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
	    	System.out.println("查询余票量输入1,买票输入2,退票输入3,查询订单输入4,查询指定航线航班票务情况输入5,返回上层输入6\n");
		    int input=Tools.inputInteger(1, 5);
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
		     }
		     case 4:{
		    	 this.searchReservationMenu();
		     }
		     case 5:{
		    	 this.searchFlight();
		     }
		     case 6:
		    	 return;
		     default:
		    	 break;
		     }
	    }
	}

	private void searchFlight() {
		// TODO Auto-generated method stub
		List<FlightInfo> flight= new ArrayList<FlightInfo>();
		System.out.println("请输入起飞城市");
		Scanner scanner =new Scanner(System.in);
		String takeofflocation;
		takeofflocation=scanner.nextLine();
		System.out.println("请输入降落城市");
	    String landlocation;
	    landlocation=scanner.nextLine();
	    flight=ticketcontroller.searchFlightByCity(takeofflocation, landlocation);
	    if(flight==null) {
	    	System.out.println("无直飞航班");
	    	scanner.close();
	    	return;
	    }
    	System.out.println("排序方式：余票量1,价格2,起飞时间3,降落时间4\n");
	    int input=Tools.inputInteger(1, 4);
        if(input==1) {flight=ticketcontroller.orderFlightByAmount(flight);}
        if(input==2) {flight=ticketcontroller.orderFlightByPrice(flight);}
        if(input==3) {flight=ticketcontroller.orderFlightByTakeOffTime(flight);}
        if(input==4) {flight=ticketcontroller.orderFlightByLandTime(flight);}
        for(int i=0;i<flight.size();i++) {
        	printFlightInfo(flight.get(i));
        	System.out.println("\n");
        }
        scanner.close();
        return;
	}

	private void refundTicketMenu() {
		// TODO Auto-generated method stub
		System.out.println("请输入要退票的航班号");
		Scanner scanner =new Scanner(System.in);
		String flightname;
		flightname=scanner.nextLine();
		boolean result=ticketcontroller.refundTicket(flightname, currentuser.getUsername());
		if(result==false) {
			System.out.println("您未购买该航班机票");
		}
		else {
			System.out.println("您已成功退票");
			ticketcontroller.changeAppointment(flightname);
		}
		scanner.close();
		return;
	}

	private void searchReservationMenu() {
		// TODO Auto-generated method stub
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
	   	return;
    	}

	private void buyTicketMenu() {
		// TODO Auto-generated method stub
		System.out.println("请输入要购买的航班号");
		Scanner scanner =new Scanner(System.in);
		String flightname;
		int choose;
		flightname=scanner.nextLine();
		boolean result=ticketcontroller.buyTicket(flightname, currentuser.getUsername());
		if(result==true) {
			System.out.println("您已成功购买"+flightname+"航班机票");
		}
		else {
            System.out.println("可为您预约抢票，预约请输入1，输入其他自动返回上一层");
            choose=scanner.nextInt();
            if(choose==1) {
            	ticketcontroller.reserveTicket(flightname, currentuser.getUsername());
            }
            else {
            	scanner.close();
            	return;
            }
		}
		scanner.close();
		return;
	}

	private void getTicketAmountMenu() {
		// TODO Auto-generated method stub
		System.out.println("请输入要查询的航班号");
		Scanner scanner =new Scanner(System.in);
		String flightname;
		flightname=scanner.nextLine();
		int amount;
		flightname=scanner.nextLine();
		amount=ticketcontroller.getTicketAmount(flightname);
		if(amount==-1) {
			System.out.println("你查询的航班不存在");
		}
		else {
			System.out.println("您所查询的航班余票量为："+amount);
		}
		scanner.close();
		return;
	}
  


	
	
	private void printFlightInfo(FlightInfo flight) {
	System.out.println("航班号："+flight.getFlightname());
	System.out.println("起飞机场："+flight.getTakeofflocation());
	System.out.println("起飞时间："+flight.getTakeofftime());
	System.out.println("降落机场："+flight.getLandlocation());
	System.out.println("降落时间："+flight.getTakeofflocation());
	System.out.println("航空公司："+flight.getCompany());
	System.out.println("价格："+flight.getPrice());
    }

	private void printReservation(Reservation reservation) {
	System.out.println("航班号："+reservation.getFlightname()+"   "+"下单时间"+reservation.getTime());
	}
}
