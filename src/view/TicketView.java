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
	    	System.out.println("1.��ѯ��Ʊ��\n2.��Ʊ\n3.��Ʊ\n4.��ѯ����\n5.��ѯָ�����ߺ���Ʊ�����\n6.������һ��");
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
		System.out.println("��������ɳ���");
		@SuppressWarnings("resource")
		Scanner scanner =new Scanner(System.in);
		String takeofflocation=null;
		takeofflocation=scanner.nextLine();
		System.out.println("�����뽵�����");
	    String landlocation=null;
	    landlocation=scanner.nextLine();
	    flight=ticketcontroller.searchFlightByCity(takeofflocation, landlocation);
	    if(flight.size()==0) {
	    	System.out.println("��ֱ�ɺ���");
	    	return;
	    }
    	System.out.println("ѡ������ʽ��1.��Ʊ�� 2.�۸� 3.���ʱ�� 4.����ʱ��");
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
		System.out.println("������Ҫ��Ʊ�ĺ����");
		@SuppressWarnings("resource")
		Scanner scanner =new Scanner(System.in);
		String flightname;
		flightname=scanner.nextLine();
		boolean result=ticketcontroller.refundTicket(currentuser.getUsername(),flightname);
		if(result==false) {
			System.out.println("��δ����ú����Ʊ");
		}
		else {
			System.out.println("���ѳɹ���Ʊ");
			//ticketcontroller.changeAppointment(flightname);
		}
		return;
	}

	private void searchReservationMenu() {
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
	   	System.out.println();
	   	return;
    }

	private void buyTicketMenu() {
		System.out.println("������Ҫ����ĺ����");
		@SuppressWarnings("resource")
		Scanner scanner =new Scanner(System.in);
		String flightname;
		int choose;
		flightname=scanner.nextLine();
		boolean result=ticketcontroller.buyTicket(flightname, currentuser.getUsername());
		if(result==true) {
			System.out.println("���ѳɹ�����"+flightname+"�����Ʊ\n");
		}
		else {
            System.out.println("��Ϊ��ԤԼ��Ʊ��ԤԼ������1�����������Զ�������һ��");
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
		System.out.println("������Ҫ��ѯ�ĺ����");
		@SuppressWarnings("resource")
		Scanner scanner =new Scanner(System.in);
		String flightname=null;
		flightname=scanner.nextLine();
		int amount=0;
		amount=ticketcontroller.getTicketAmount(flightname);
		if(amount==-1) {
			System.out.println("���ѯ�ĺ��಻����");
		}
		else {
			System.out.println("������ѯ�ĺ�����Ʊ��Ϊ��"+amount);
			System.out.println();
		}
		return;
	}
  
	private void printFlightInfo(FlightInfo flight) {
		System.out.println("����ţ�"+flight.getFlightname());
		System.out.println("��ɳ��У�"+flight.getTakeofflocation());
		System.out.println("���ʱ�䣺"+flight.getTakeofftime());
		System.out.println("������У�"+flight.getLandlocation());
		System.out.println("����ʱ�䣺"+flight.getLandtime());
		System.out.println("���չ�˾��"+flight.getCompany());
		System.out.println("�۸�"+flight.getPrice());
		if(flight.getPasslocation()==null||flight.getPasstime()==null||flight.getPasslocation().equals("null")||flight.getPasstime().equals("null")) {
			System.out.println("��ͣ���У���");
			System.out.println("��ͣʱ�䣺��");
		}
		else {
			System.out.println("��ͣ���У�"+flight.getPasslocation());
			System.out.println("��ͣʱ�䣺"+flight.getPasstime());
		}
		System.out.println();
    }

	private void printReservation(Reservation reservation) {
		System.out.println("����ţ�"+reservation.getFlightname()+"   "+"�µ�ʱ��"+reservation.getTime());
	}
}
