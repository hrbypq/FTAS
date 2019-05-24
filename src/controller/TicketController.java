package controller;
import container.TicketContainer;
import container.FlightInfoContainer;
import container.ReservationContainer;

import java.text.SimpleDateFormat;
import java.util.*;
import entity.*;

/**
 * ��Ʊ������
 * @author
 * @version 1.0
 */
public class TicketController {

	//private static  Map<String,Integer> container=null;
	private static Map<String,List<Reservation>> containerList=new TreeMap<String,List<Reservation>>();
	private static Map<String,Integer> container= new TreeMap<String,Integer>();
	private static Map<String,FlightInfo> containerFlight= new TreeMap<String,FlightInfo>();
	private static Map<String,Queue<String>> containerAppointment= new TreeMap<String,Queue<String>>();
	/** 
	 * ���췽��
	 */
	public TicketController(Map<String,Integer> container) {
		//this.container=container;
		// TODO Auto-generated constructor stub
		
	}

	/**
	 * ��ѯ��Ʊ�� 
	 * @param flightname �����
	 * @return ��Ʊ�� ��ѯʧ�ܷ���-1
	 */
	public static int getTicketAmount(String flightname) {

		if(!checkFlight(flightname)) {
			return -1;
		};
		int ticket=container.get(flightname);
		return ticket;
	}
	
	/**
	 * ������Ա������Ʊ��
	 * @param flightname �����
	 * @param amount �µ���Ʊ��
	 * @return �Ƿ����óɹ�
	 */
	public static boolean setTicketAmount(String flightname,int amount) {
		if(!checkFlight(flightname)) {return false;}
		container.remove(flightname);
		container.put(flightname,amount);
		return true;
	}
	
	/**
	 * �ÿ���Ʊ
	 * @param flightname �����
	 * @return �Ƿ���ɹ�
	 */
	public static boolean buyTicket(String flightname,String username) {
		if(!checkFlight(flightname)) {return false;}
		int amount=getTicketAmount(flightname);
		if(amount<=0) {return false;}
		setTicketAmount(flightname, amount-1);
		addReservation(flightname,username);
		return true;
	}
	/**
	 * ��ƱʱΪ����Ӷ���
	 * @param flightname
	 * @param username
	 * @return
	 */
	private static boolean addReservation(String flightname, String username) {
		List<Reservation> obj=new ArrayList<Reservation>();
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String datetime = tempDate.format(new java.util.Date()); 
		Reservation newReservation= new Reservation(username,flightname,datetime);
		if(containerList.containsKey(username)) {
			obj=containerList.get(username);
		}
		else {obj=null;}
		obj.add(newReservation);
		containerList.put(username, obj);
		return true;
	}

	/**
	 * ��Ʊ�� �Զ�Ϊ��һ����Ʊ
	 * @param flightname
	 * @return
	 */
	public static boolean changeAppointment(String flightname) {
		Queue<String> queue=new LinkedList<String>();
		String username=null;
		if(!checkFlight(flightname)) {return false;}
		if(containerAppointment.containsKey(flightname)) {
			queue=containerAppointment.get(flightname);
			username=queue.element();
			queue.poll();
			buyTicket(flightname,username);
		}
		return true;	
	}
	/**
	 * ԤԼ��Ʊ
	 * * @param flightname
	 * @param username
	 * @return
	 */

	public static boolean reserveTicket(String flightname,String username) {
		Queue<String> queue=new LinkedList<String>();
		if(!checkFlight(flightname)) {return false;}
		if(containerAppointment.containsKey(flightname)) {
			queue=containerAppointment.get(flightname);
		}
		if(!containerAppointment.containsKey(flightname)) {
			queue=null;
		}
		queue.add(username);
		containerAppointment.put(flightname, queue);
		return true;
	}
	
	/**
	 * ��ѯ����
	 * @param username �û���
	 * @return �����������ArrayList ��ѯʧ�ܷ���null
	 */
	public static List<Reservation> searchReservation(String username){
		List<Reservation> obj=new ArrayList<Reservation>();
        if(!checkReservation(username)) {return null;}
        obj=containerList.get(username);
		return obj;
	}
	/*
	 * ��麽��źϷ�
	 */
	public static boolean checkFlight(String flightname) {
		boolean check=container.containsKey(flightname);
		return check;
	}
	/*
	 * ����û����Ϸ�
	 */
	public static boolean checkReservation(String username) {
		boolean check=containerList.containsKey(username);
		return check;
	}
	/*
	 * ��Ʊ
	 */
    public static boolean refundTicket(String username, String flightname) {
    	List<Reservation> ticketlist=searchReservation(username);
    	if(ticketlist==null) {return false;}
    	for(int i=0;i<ticketlist.size();i++) {
    		Reservation target=ticketlist.get(i);
    		if(target.getFlightname()==flightname) {
    			ticketlist.remove(i);
    		}//����иú���� ��ɾ���ö���
    	}//���û�list����
    	containerList.remove(username);
    	containerList.put(username, ticketlist);//���������û�����
    	int newAmount=getTicketAmount(flightname);
    	newAmount+=1;
    	setTicketAmount(flightname,newAmount);//�ú�����Ʊ����һ
    	return true;
    }
	/*
	 * ������в�ѯ����
	 */
    @SuppressWarnings("null")
	public static List<FlightInfo> searchFlightByCity(String takeofflocation,String landlocation) {
    	List<FlightInfo> targetflight=new ArrayList<FlightInfo>();
    	Iterator titer=containerFlight.keySet().iterator();
    	while(titer.hasNext()){
    		FlightInfo info=null;
    		info=containerFlight.get(titer.next());
    	   if(info.getLandlocation()==landlocation && info.getTakeofflocation()==takeofflocation) {
    		   targetflight.add(info);
    	   }
		}
    	return targetflight;
    }
	/*
	 * ��������
	 */
    public static boolean orderFlightByPrice(List<FlightInfo> targetflight) {
		if(targetflight.size()==0) {return false;}
        Collections.sort(targetflight, new Comparator<FlightInfo>(){
        	public int compare(FlightInfo o1, FlightInfo o2) {
				double i = o1.getPrice() - o2.getPrice();
				return (int)i;
        	}
        });
        return true;
    }
    public static boolean orderFlightByAmount(List<FlightInfo> targetflight) {
		if(targetflight.size()==0) {return false;}
        Collections.sort(targetflight, new Comparator<FlightInfo>(){
        	public int compare(FlightInfo o1, FlightInfo o2) {
				int i = getTicketAmount(o1.getFlightname()) - getTicketAmount(o2.getFlightname());
				return i;
        	}
        });
        return true;
    }
    public static boolean orderFlightByLandTime(List<FlightInfo> targetflight) {
		if(targetflight.size()==0) {return false;}
        Collections.sort(targetflight, new Comparator<FlightInfo>(){
        	public int compare(FlightInfo o1, FlightInfo o2) {
        		double a=Double.parseDouble(o1.getLandtime());
        		double b=Double.parseDouble(o2.getLandtime());
				double i =a-b;
				return (int)i;
        	}
        });
        return true;
    }
    public static boolean orderFlightByTakeOffTime(List<FlightInfo> targetflight) {
		if(targetflight.size()==0) {return false;}
        Collections.sort(targetflight, new Comparator<FlightInfo>(){
        	public int compare(FlightInfo o1, FlightInfo o2) {
        		double a=Double.parseDouble(o1.getTakeofftime());
        		double b=Double.parseDouble(o2.getTakeofftime());
				double i =a-b;
				return (int)i;
        	}
        });
        return true;
    }

}
