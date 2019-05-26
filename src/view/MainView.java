package view;

import container.*;
import controller.*;

import java.io.Reader;
import java.sql.*;
import java.util.Scanner;

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
	public Tools tool=null;//�����������ݵĹ�����
	
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
	public void init() throws ClassNotFoundException, SQLException {
		userinfocontainer=new UserInfoContainer();
		flightinfocontainer=new FlightInfoContainer();
		ticketcontainer=new TicketContainer();
		reservationcontainer=new ReservationContainer();
		/**
		 * �����ݿ��ȡ����
		 */
		userinfocontainer.readDB();
		reservationcontainer.readDB();
		ticketcontainer.readDB();
		flightinfocontainer.readDB();
		/**
		 * ��ʼ��������Ϳ�����
		 */
	     tool=new Tools();
	}
	/**
	 * �˵�
	 */
	public void showMenu() {
		while (true) {
			System.out.println("����Ա�������롰1������ͨ�ÿͲ������롰2��\n");
			int choice=0;
			choice=Tools.inputInteger(1, 2);
			switch (choice) {
			//����Ա����
			case 1:{
			    UserInfoView userUI=new UserInfoView(userinfocontainer, null);
				userUI.showAdminMenu();
				currentuser=userUI.getUser();
				break;
				}
			case 2:{
				UserInfoView userUI=new UserInfoView(userinfocontainer, null);
				userUI.showTouristMenu();
				currentuser=userUI.getUser();
				/**
				 * ���currentuser��null��ղŽ��е����û���½��ֱ�ӻ���
				 * �������棬��֮�������һ������
				 */
				if(currentuser==null)
					break;
				else {
					FlightView fightUI=new FlightView(flightinfocontainer, currentuser);
					fightUI.showTouristMenu();
				}
			}
			default:
				break;
			}
		}
	}
}
