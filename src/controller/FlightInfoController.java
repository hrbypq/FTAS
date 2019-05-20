package controller;
import java.util.Map;

import container.*;
import entity.*;

/**
 * ������Ϣ������
 * @author
 * @version 1.0
 */
public class FlightInfoController {
	private Map<String,FlightInfo> container=null;//���ڲ�������������
	/**
	 * ���췽��
	 */
	public FlightInfoController(Map<String,FlightInfo> container) {
		this.container=container;
		// TODO Auto-generated constructor stub
	}
	//�����б����container ��FlightInfoContainer��get�������

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
		FlightInfo new_flight=new FlightInfo (flightname,company,price,takeofftime,takeofflocation,landtime,landlocation,pastime,passlocation);
		if(!container.containsKey(flightname)) {
			container.put(flightname,new_flight);
			return true;
		}
		else 
			return false;
	}
	
	/**
	 * ��ѯ������Ϣ
	 * @param flightname �����
	 * @return ����ѯ�ɹ�����һ����ʵ����� ʧ�ܷ���null
	 */
	public FlightInfo searchFlightInfo(String flightname) {
		FlightInfo obj=null;
		obj=container.get(flightname);
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
		if(!container.containsKey(flightname))
			return false;
		else {
			FlightInfo update_flight= new FlightInfo(flightname,company,price,takeofftime,takeofflocation,landtime,landlocation,pastime,passlocation);
			container.put(flightname, update_flight);
			return true;
		}
	}
	
	/**
	 * ɾ��������Ϣ
	 * @param flightname �����
	 * @return �Ƿ�ɾ���ɹ�
	 */
	public boolean deleteFlightInfo(String flightname) {
		if(container.containsKey(flightname)) {
			container.remove(flightname);
			return true;
		}
		else
		    return false;
	}
	
}
