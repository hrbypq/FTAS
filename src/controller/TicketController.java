package controller;
import container.*;
import java.util.*;
import entity.*;

/**
 * ��Ʊ������
 * @author
 * @version 1.0
 */
public class TicketController {

	/**
	 * ���췽��
	 */
	public TicketController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ��ѯ��Ʊ�� 
	 * @param flightname �����
	 * @return ��Ʊ�� ��ѯʧ�ܷ���-1
	 */
	public int getTicketAmount(String flightname) {
		return 0;
	}
	
	/**
	 * ������Ա������Ʊ��
	 * @param flightname �����
	 * @param amount �µ���Ʊ��
	 * @return �Ƿ����óɹ�
	 */
	public boolean setTicketAmount(String flightname,int amount) {
		return true;
	}
	
	/**
	 * �ÿ���Ʊ
	 * @param flightname �����
	 * @return �Ƿ���ɹ�
	 */
	public boolean buyTicket(String flightname) {
		return true;
	}
	
	/**
	 * ԤԼ��Ʊ
	 * @param flightname �����
	 */
	public void reserveTicket(String flightname) {
		
	}
	
	/**
	 * ��ѯ����
	 * @param username �û���
	 * @return �����������ArrayList ��ѯʧ�ܷ���null
	 */
	public List<Reservation> searchReservation(String username){
		List<Reservation> obj=null;
		return obj;
	}
}
