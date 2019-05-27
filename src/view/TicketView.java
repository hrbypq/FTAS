package view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import container.*;
import controller.*;
import entity.*;

/**
 * Ʊ�������
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
	 * ���췽��
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
	 * �˵�
	 */
	public void showMenu() {
	    while(true) {
	    	System.out.println("��ѯ��Ʊ������1,��Ʊ����2,��Ʊ����3,��ѯ��������4,��ѯָ�����ߺ���Ʊ���������5,�����ϲ�����6\n");
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
		System.out.println("��������ɳ���");
		Scanner scanner =new Scanner(System.in);
		String takeofflocation;
		takeofflocation=scanner.nextLine();
		System.out.println("�����뽵�����");
	    String landlocation;
	    landlocation=scanner.nextLine();
	    flight=ticketcontroller.searchFlightByCity(takeofflocation, landlocation);
	    if(flight==null) {
	    	System.out.println("��ֱ�ɺ���");
	    	scanner.close();
	    	return;
	    }
    	System.out.println("����ʽ����Ʊ��1,�۸�2,���ʱ��3,����ʱ��4\n");
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
		System.out.println("������Ҫ��Ʊ�ĺ����");
		Scanner scanner =new Scanner(System.in);
		String flightname;
		flightname=scanner.nextLine();
		boolean result=ticketcontroller.refundTicket(flightname, currentuser.getUsername());
		if(result==false) {
			System.out.println("��δ����ú����Ʊ");
		}
		else {
			System.out.println("���ѳɹ���Ʊ");
			ticketcontroller.changeAppointment(flightname);
		}
		scanner.close();
		return;
	}

	private void searchReservationMenu() {
		// TODO Auto-generated method stub
		List<Reservation> ticketlist=ticketcontroller.searchReservation(currentuser.getUsername());
		if(ticketlist==null) {
			System.out.println("����δ��Ʊ");
			return;
		}
		System.out.println("���Ķ������£�");
	   	for(int i=0;i<ticketlist.size();i++) {
    		Reservation target=ticketlist.get(i);
            printReservation(target);
    		}
	   	return;
    	}

	private void buyTicketMenu() {
		// TODO Auto-generated method stub
		System.out.println("������Ҫ����ĺ����");
		Scanner scanner =new Scanner(System.in);
		String flightname;
		int choose;
		flightname=scanner.nextLine();
		boolean result=ticketcontroller.buyTicket(flightname, currentuser.getUsername());
		if(result==true) {
			System.out.println("���ѳɹ�����"+flightname+"�����Ʊ");
		}
		else {
            System.out.println("��Ϊ��ԤԼ��Ʊ��ԤԼ������1�����������Զ�������һ��");
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
		System.out.println("������Ҫ��ѯ�ĺ����");
		Scanner scanner =new Scanner(System.in);
		String flightname;
		flightname=scanner.nextLine();
		int amount;
		flightname=scanner.nextLine();
		amount=ticketcontroller.getTicketAmount(flightname);
		if(amount==-1) {
			System.out.println("���ѯ�ĺ��಻����");
		}
		else {
			System.out.println("������ѯ�ĺ�����Ʊ��Ϊ��"+amount);
		}
		scanner.close();
		return;
	}
  


	
	
	private void printFlightInfo(FlightInfo flight) {
	System.out.println("����ţ�"+flight.getFlightname());
	System.out.println("��ɻ�����"+flight.getTakeofflocation());
	System.out.println("���ʱ�䣺"+flight.getTakeofftime());
	System.out.println("���������"+flight.getLandlocation());
	System.out.println("����ʱ�䣺"+flight.getTakeofflocation());
	System.out.println("���չ�˾��"+flight.getCompany());
	System.out.println("�۸�"+flight.getPrice());
    }

	private void printReservation(Reservation reservation) {
	System.out.println("����ţ�"+reservation.getFlightname()+"   "+"�µ�ʱ��"+reservation.getTime());
	}
}
