package view;

import java.util.Scanner;
import container.*;
import controller.*;
import entity.*;

/**
 * ������Ϣ������
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
	 * ���췽��
	 */
	public FlightView(FlightInfoContainer flightinfocontainer,UserInfo currentuser,TicketContainer ticketcontainer) {
		this.currentuser=currentuser;
		this.flightinfocontainer=flightinfocontainer;
		ticketcontroller=new TicketController(ticketcontainer.getContainer(),null,flightinfocontainer.getContainer(),null);
		flightcontroller=new FlightInfoController(flightinfocontainer.getContainer());
		airline=new Airline(flightinfocontainer.getContainer());
	}
	
	/**
	 * �û��˵�
	 */
	public void showTouristMenu() {
	    this.flightTourMenu();
	}
	
	/**
	 * ����Ա�˵�
	 */
	public void showAdminMenu() {
		while(true) {
			System.out.println("1.������Ϣ����\n2.Ʊ�����\n3.������һ��");
			int input=Tools.inputInteger(1, 3);
			if(input==3)     //������һ��
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
		System.out.println("������Ҫ���õĺ����");
		Scanner scanner= new Scanner(System.in);
		String flightname=scanner.nextLine();
		System.out.println("������Ҫ���õ���Ʊ��");
		int amount=scanner.nextInt();
		if(ticketcontroller.setTicketAmount(flightname, amount)) {
			System.out.println("���óɹ�!");
		}
		else {
			System.out.println("����ʧ��!");
		}
	}

	/**
	 * �û���ѯ������Ϣ
	 */
	private void flightTourMenu() {
		while (true) {
			System.out.println("1.���ݺ���Ų�ѯ������Ϣ\n2.�����Ƽ�(�����ֱ��ĺ���)\n3.������һ��");
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
	 * ����Ա��������Ϣ
	 */
	private void flightInfoAdmin() {
		System.out.println("1.���Ӻ���\n2.ɾ������\n3.�޸ĺ�����Ϣ\n4.�鿴ָ��������Ϣ\n5.������һ��");
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
	 * ��ӡ���������Ϣ
	 */
	private void printInfo(FlightInfo flight) {
		System.out.println("����ţ�"+flight.getFlightname());
		System.out.println("���չ�˾��"+flight.getCompany());
		System.out.println("��ɳ��У�"+flight.getTakeofflocation());
		System.out.println("���ʱ�䣺"+flight.getTakeofftime());
		System.out.println("������У�"+flight.getLandlocation());
		System.out.println("����ʱ�䣺"+flight.getLandtime());
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
	
	/**
	 * ���ݺ���Ų�ѯ����
	 */
	private void searchFlight() {
		System.out.println("������Ҫ��ѯ��Ϣ�ĺ����:");
		@SuppressWarnings("resource")
		Scanner scanner =new Scanner(System.in);
		String flightname=scanner.nextLine();
		FlightInfo flight=flightcontroller.searchFlightInfo(flightname);
		if(flight==null) {
			System.out.println("���಻����\n");
		}
		else {
			this.printInfo(flight);
		}
		}
	
	/**
	 * ���Ӻ���
	 */
	@SuppressWarnings("resource")
	private void addFlight() {
		Scanner scanner=new Scanner(System.in);
		while(true) {
			System.out.println("�����뺽���:");
			String flightname=scanner.nextLine();
			
			System.out.println("�����뺽�չ�˾:");
			String company=scanner.nextLine();
			
			System.out.println("��������ɳ���:");
			String takeofflocation=scanner.nextLine();
			
			System.out.println("���������ʱ��:");
			String takeofftime=scanner.nextLine();
			
			System.out.println("�����뽵�����:");
			String landlocation=scanner.nextLine();
			
			System.out.println("�����뽵��ʱ��:");
			String landtime=scanner.nextLine();
			
			System.out.println("������۸�:");
			double price=scanner.nextDouble();
			scanner=new Scanner(System.in);
			
			System.out.println("�����뾭ͣ����:(����������111)");
			String passlocation=scanner.nextLine();
			if(passlocation.equals("111"))
				passlocation=null;
			
			System.out.println("�����뾭ͣʱ��:(����������111)");
			String passtime=scanner.nextLine();
			if(passtime.equals("111"))
				passtime=null;
			
		    boolean whther_suceed=flightcontroller.createFlightInfo(flightname, company, price, takeofftime, takeofflocation, landtime, landlocation, passtime, passlocation);
		    if(whther_suceed) {
		    	System.out.println("���ӳɹ�");
		    	ticketcontroller.addFlight(flightname);
		    	break;
		    }
		    else {
				System.out.println("�����Ѵ���");
				break;
			}
		}
	}
	
	/**
	 * �޸ĺ���
	 */
	private void updateFlight() {
		while(true) {
			@SuppressWarnings("resource")
			Scanner scanner=new Scanner(System.in);
			System.out.println("�����뺽���\n");
			String flightname=scanner.nextLine();
			
			if(flightcontroller.searchFlightInfo(flightname)!=null) {
				System.out.println("�����뺽�չ�˾:");
				String company=scanner.nextLine();
				
				System.out.println("��������ɳ���:");
				String takeofflocation=scanner.nextLine();
				
				System.out.println("���������ʱ��:");
				String takeofftime=scanner.nextLine();
				
				System.out.println("�����뽵�����:");
				String landlocation=scanner.nextLine();
				
				System.out.println("�����뽵��ʱ��:");
				String landtime=scanner.nextLine();
				
				System.out.println("������۸�:");
				double price=scanner.nextDouble();
				scanner=new Scanner(System.in);
				
				System.out.println("�����뾭ͣ����:(����������111)");
				String passlocation=scanner.nextLine();
				if(passlocation.equals("111"))
					passlocation=null;
				
				System.out.println("�����뾭ͣʱ��:(����������111)");
				String passtime=scanner.nextLine();
				if(passtime.equals("111"))
					passtime=null;
				
			    boolean whther_suceed=flightcontroller.updateFlightInfo(flightname, company, price, takeofftime, takeofflocation, landtime, landlocation, passtime, passlocation);
			    if(whther_suceed) {
			    	System.out.println("�޸ĳɹ�");
			    	break;
			    }
			    else {
					System.out.println("�޸�ʧ��");
					break;
				}
			}
			else {
				System.out.println("�����ڴ˺���");
				break;
			}
		}
	}
		
	/**
	 * ɾ������
	 */
	@SuppressWarnings("resource")
	private void deleteFlight() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("�����뺽���:");
		String flightname=scanner.nextLine();
		boolean whether_suceed=flightcontroller.deleteFlightInfo(flightname);
		if(whether_suceed) {
			System.out.println("ɾ���ɹ�\n");
			ticketcontroller.deleteFlight(flightname);
		}
		else 
			System.out.println("ɾ��ʧ��\n");
	}

	private void recommendFlight() {
		SystemController systemcontroller=new SystemController(airline,flightinfocontainer.getContainer());
		System.out.println("��������ɳ���:");
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		String takeoffcity=scanner.nextLine();
		System.out.println("������Ŀ�ĵس���:");
		String landcity=scanner.nextLine();
		if(!systemcontroller.comparePrice(takeoffcity, landcity)) {
			System.out.println("�����м��޺���!");
			return;
		}
		System.out.println("���۸������Ƽ����ࣺ");
		this.printInfo(systemcontroller.getResult1());
		System.out.println("��ת���У�"+systemcontroller.getMidCity()+'\n');
		this.printInfo(systemcontroller.getResult2());
		System.out.println();
		systemcontroller.compareTime(takeoffcity, landcity);
		System.out.println("��ʱ�������Ƽ����ࣺ");
		this.printInfo(systemcontroller.getResult1());
		System.out.println("��ת���У�"+systemcontroller.getMidCity());
		this.printInfo(systemcontroller.getResult2());
		System.out.println();
	}
	
}
	
