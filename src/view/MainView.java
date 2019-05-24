package view;

import container.*;
import java.sql.*;
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
	}
	
	/**
	 * �˵�
	 */
	public void showMenu() {
		
	}
}
