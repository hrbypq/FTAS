package controller;
import entity.*;

/**
 * ������Ϣ������
 * @author
 * @version 1.0
 */
public class FlightInfoController {

	/**
	 * ���췽��
	 */
	public FlightInfoController() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * ���Ӻ�����Ϣ
	 * @param flightname ����� �����ظ�
	 * @param company ���๫˾
	 * @param price Ʊ��
	 * @param takeofftime ���ʱ��
	 * @param takeofflocation ��ɳ���
	 * @param landtime ���ʱ��
	 * @param landlocation ��س���
	 * @param pastime ��ͣʱ��
	 * @param passlocation ��ͣ����
	 * @return ����ӳɹ�����true
	 */
	public boolean createFlightInfo(String flightname,String company,double price,String takeofftime,String takeofflocation,String landtime,String landlocation,String pastime,String passlocation) {
		return true;
	}
	
	/**
	 * ��ѯ������Ϣ
	 * @param flightname �����
	 * @return ����ѯ�ɹ�����һ����ʵ����� ʧ�ܷ���null
	 */
	public FlightInfo searchFlightInfo(String flightname) {
		FlightInfo obj=null;
		return obj;
	}
	
	/**
	 * ���º�����Ϣ
	 * @param flightname �����
	 * @param company ���๫˾
	 * @param price Ʊ��
	 * @param takeofftime ���ʱ��
	 * @param takeofflocation ��ɳ���
	 * @param landtime ���ʱ��
	 * @param landlocation ��س���
	 * @param pastime ��ͣʱ��
	 * @param passlocation ��ͣ����
	 * @return �����³ɹ�����true
	 */
	public boolean updateFlightInfo(String flightname,String company,double price,String takeofftime,String takeofflocation,String landtime,String landlocation,String pastime,String passlocation) {
		return true;
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param flightname �����
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public boolean deleteFlightInfo(String flightname) {
		return true;
	}
	
}
