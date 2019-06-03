package view;

import container.*;
import controller.*;
import java.sql.*;
import java.util.*;
import entity.*;

/**
 * ��������
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
	public List<Message> messagebox=null;       //������Ϣ����
	
	/**
	 * ���췽��
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public MainView() throws ClassNotFoundException, SQLException {
		this.init();
	}
	
	/**
	 * �����ʼ��
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	private void init() throws ClassNotFoundException, SQLException {
		//���������ಢ�����ݿ��ж�ȡ����
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
	 * ������ֹ
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
	 * ��������������Ϣ
	 */
	private void flightDelay() {
		System.out.println("�����뺽���:");
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		String flightname=scanner.nextLine();
		boolean find=flightinfocontainer.getContainer().containsKey(flightname);
		if(!find) {
			System.out.println("���಻����! ������һ���˵�");
			return;
		}
		System.out.println("���������������Ϣ:");
		String info=scanner.nextLine();
		Message message=new Message(info,flightname);
		messagebox.add(message);
		System.out.println("������Ϣ�����!");
	}
	
	/**
	 * ����������������Ϣ
	 */
	private void printDelayInfo() {
		List<Reservation> reservation=reservationcontainer.getContainer().get(currentuser.getUsername());
		boolean signal=false;
		for(Message delayflight:messagebox) {
			for(Reservation reserveflight:reservation) {
				if(reserveflight.getFlightname().equals(delayflight.getFlightname())) {
					signal=true;
					System.out.println(delayflight.getFlightname()+"��������");
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
	 * �˵�
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public void showMenu() throws ClassNotFoundException, SQLException {
		System.out.println("��ӭʹ�ñ�ϵͳ!\n");
		
		//���˵���ѭ��
		while (true) {
			System.out.println("������:");
			System.out.println("1.����Ա����\n2.��ͨ�û�����\n3.�˳�ϵͳ\n������ѡ��:");
			int choice=0;
			choice=Tools.inputInteger(1,3);
			switch (choice) {
			
			//����Ա
			case 1:{
			    UserInfoView userUI=new UserInfoView(userinfocontainer);
				userUI.showAdminMenu();           
				currentuser=new UserInfo("root","123456");            //����Ա����һ�û���Ϊroot��UserInfo����
				break; 
			}
			
			//�û�
			case 2:{
				UserInfoView userUI=new UserInfoView(userinfocontainer);
				userUI.showUserMenu();
				currentuser=userUI.getUser();
				break;
			}
			
			//�˳�����
			case 3:{
				this.terminate();
				return;
			}
			
			}
			//switch ends
			
			//����Աģʽ
			if(currentuser.getUsername().equals("root")) {
				//����Ա�˵�ѭ��
				while(true) {
					System.out.println("����Ա�˵�:\n1.������Ϣ��Ʊ�����\n2.����������Ϣ\n3.�������˵�");
					choice=Tools.inputInteger(1, 3);
					if(choice==3)    //�������˵�
						break;
					switch(choice) {
					
					//������Ϣ��Ʊ�����
					case 1:{
						FlightView flightUI=new FlightView(flightinfocontainer,currentuser,ticketcontainer);
						flightUI.showAdminMenu();
						break;
					}
					
					//����������Ϣ
					case 2:{
						this.flightDelay();
						break;
					}
					
					}
					//switch ends
				}
			}
			//����Ա�˵�����
			
			
			//��ͨ�û�ģʽ
			else {
				//���ж��Ƿ񲥱�������Ϣ
				this.printDelayInfo();
				//�û��˵�ѭ��
				while(true) {
					System.out.println("�û��˵�:\n1.Ʊ�����\n2.������Ϣ��ѯ\n3.�������˵�");
					choice=Tools.inputInteger(1, 3);
					if(choice==3)  //�������˵�
						break;
					switch(choice) {
					
					//Ʊ��
					case 1:{
						TicketView ticketUI=new TicketView(flightinfocontainer,ticketcontainer,currentuser,appointmentcontainer,reservationcontainer);
						ticketUI.showMenu();
						break;
					}
					
					//������Ϣ
					case 2:{
						FlightView flightUI=new FlightView(flightinfocontainer,currentuser,ticketcontainer);
						flightUI.showTouristMenu();
						break;
					}
					
					}
					//switch ends
				}
			}
			//�û��˵�����
		}
		//���˵�ѭ������
		
	}
}
