package view;

import java.util.Scanner;

import org.omg.CORBA.PRIVATE_MEMBER;

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
	private Tools tools=null;
	/**
	 * ���췽��
	 */
	public FlightView(FlightInfoContainer flightinfocontainer,UserInfo currentuser) {
		// TODO Auto-generated constructor stub
		this.currentuser=currentuser;
		this.flightinfocontainer=flightinfocontainer;
		this.tools=new Tools();
		flightcontroller=new FlightInfoController(flightinfocontainer.getContainer());
	}
	
	/**
	 * �û��˵�
	 */
	public void showTouristMenu() {
	    while(true) {
	    	System.out.println("Ʊ��ҵ������1��������Ϣ��ѯ����2,�����ϲ�����3\n");
		    int input=Tools.inputInteger(1, 3);
		    switch (input) {
		    case 1:
			   /*
			     *Ʊ��
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
	 * ����Ա�˵�
	 */
	public void showAdminMenu() {
		while(true) {
			System.out.println("������Ϣ��������1��Ʊ���������2,�����ϲ�����3\n");
			int input=tools.inputInteger(1, 3);
			switch (input) {
			case 1:{
				this.flightInfoAdmin();
				break;
			}
			case 2:
				/*
				 * Ʊ�����
				 */
			default:
				break;
			}
		}
		
	}
	/**
	 * �û���ѯ������Ϣ
	 */
	private void flightTourMenu() {
	while (true) {
		System.out.println("���ݺ���Ų�ѯ������Ϣ����1�������Ƽ�����2,�����ϲ�����3\n");
		int input=Tools.inputInteger(1, 3);
		switch (input) {
		case 1:
			this.searchFlight();
			break;
		case 2:{
			/*
			 * �Ƽ�
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
	 * ����Ա��������Ϣ
	 */
	private void flightInfoAdmin() {
		System.out.println("���Ӻ�������1��ɾ����������2,�޸ĺ�������3,���ݺ���Ų鿴��������4���˳�����5\n");
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
	 * ��ӡ���������Ϣ
	 */
	private void printInfo(FlightInfo flight) {
		System.out.println("����ţ�"+flight.getFlightname());
		System.out.println("��ɻ�����"+flight.getTakeofflocation());
		System.out.println("���ʱ�䣺"+flight.getTakeofftime());
		System.out.println("���������"+flight.getLandlocation());
		System.out.println("����ʱ�䣺"+flight.getTakeofflocation());
		System.out.println("���չ�˾��"+flight.getCompany());
		System.out.println("�۸�"+flight.getPrice());
	}
	/**
	 * ���ݺ���Ų�ѯ����
	 */
	private void searchFlight() {
		System.out.println("������Ҫ��ѯ��Ϣ�ĺ����:\n");
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
	private void addFlight() {
		while(true) {
			Scanner scanner=new Scanner(System.in);
			System.out.println("�����뺽���\n");
			String flightname=scanner.nextLine();
			System.out.println("�����뺽�չ�˾\n");
			String company=scanner.nextLine();
			System.out.println("������۸�\n ");
			double price=scanner.nextDouble(); 
			System.out.println("���������ʱ��\n");
			String takeofftime=scanner.nextLine();
			System.out.println("��������ɻ��� \n");
			String takeofflocation=scanner.nextLine();
			System.out.println("�����뽵��ʱ��\n");
			String landtime=scanner.nextLine();
			System.out.println("�����뽵���\n ");
			String landlocation=scanner.nextLine();
			System.out.println("�����뾭ͣʱ��\n");
			String passtime=scanner.nextLine();
			System.out.println("�����뾭ͣ��\n");
			String passlocation=scanner.nextLine();
		    boolean whther_suceed=flightcontroller.createFlightInfo(flightname, company, price, takeofftime, takeofflocation, landtime, landlocation, passtime, passlocation);
		    if(whther_suceed)
		    	System.out.println("���ӳɹ�");
		    else {
				System.out.println("�����Ѵ���");
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
				System.out.println("�����뺽�չ�˾\n");
				String company=scanner.nextLine();
				System.out.println("������۸�\n ");
				double price=scanner.nextDouble(); 
				System.out.println("���������ʱ��\n");
				String takeofftime=scanner.nextLine();
				System.out.println("��������ɻ��� \n");
				String takeofflocation=scanner.nextLine();
				System.out.println("�����뽵��ʱ��\n");
				String landtime=scanner.nextLine();
				System.out.println("�����뽵���\n ");
				String landlocation=scanner.nextLine();
				System.out.println("�����뾭ͣʱ��\n");
				String passtime=scanner.nextLine();
				System.out.println("�����뾭ͣ��\n");
				String passlocation=scanner.nextLine();
			    boolean whther_suceed=flightcontroller.updateFlightInfo(flightname, company, price, takeofftime, takeofflocation, landtime, landlocation, passtime, passlocation);
			    if(whther_suceed)
			    	System.out.println("�޸ĳɹ�");
			    else {
					System.out.println("�޸�ʧ��");
				}
			}
			else {
				System.out.println("�����ڴ˺���\n");
			}
		}
	}
		
	/**
	 * ɾ������
	 */
	@SuppressWarnings("resource")
	private void deleteFlight() {
		Scanner scanner=new Scanner(System.in);
		System.out.println("�����뺽���\n");
		String flightname=scanner.nextLine();
		boolean whether_suceed=flightcontroller.deleteFlightInfo(flightname);
		if(whether_suceed)
			System.out.println("ɾ���ɹ�\n");
		else 
			System.out.println("ɾ��ʧ��\n");
	}

}
	
