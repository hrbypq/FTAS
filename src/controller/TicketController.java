package controller;
//import container.TicketContainer;
//import container.FlightInfoContainer;
//import container.ReservationContainer;

import java.text.SimpleDateFormat;
import java.util.*;
import entity.*;

/**
 * ��Ʊ������
 * @author
 * @version 1.0
 */
public class TicketController {

	private static Map<String,List<Reservation>> containerList=new TreeMap<String,List<Reservation>>();
	private static Map<String,Integer> container= new TreeMap<String,Integer>();
	private static Map<String,FlightInfo> containerFlight= new TreeMap<String,FlightInfo>();
	private static Map<String,Queue<String>> containerAppointment= new TreeMap<String,Queue<String>>();
	
	/** 
	 * ���췽��
	 */
	public TicketController(Map<String,Integer> container,Map<String,List<Reservation>> containerList,Map<String,FlightInfo> containerFlight,Map<String,Queue<String>> containerAppointment) {
		TicketController.container=container;
		TicketController.containerList=containerList;
		TicketController.containerFlight=containerFlight;
		TicketController.containerAppointment=containerAppointment;
	}

	/**
	 * ��ѯ��Ʊ�� 
	 * @param flightname �����
	 * @return ��Ʊ�� ��ѯʧ�ܷ���-1
	 * checked
	 */
	public int getTicketAmount(String flightname) {

		if(!checkFlight(flightname)) {
			return -1;
		}
		int ticket=container.get(flightname);
		return ticket;
	}
	
	/**
	 * ������Ա������Ʊ��
	 * @param flightname �����
	 * @param amount �µ���Ʊ��
	 * @return �Ƿ����óɹ�
	 */
	public boolean setTicketAmount(String flightname,int amount) {
		if(!checkFlight(flightname)) {
			System.out.println("δ��ѯ������");
			return false;}
		if(amount<0) {
			System.out.println("��Ʊ������Ϊ����");
			return false;}
		container.remove(flightname);
		container.put(flightname,amount);
		return true;
	}
	
	/**
	 * �ÿ���Ʊ
	 * @param flightname �����
	 * @return �Ƿ���ɹ�
	 * checked
	 */
	public boolean buyTicket(String flightname,String username) {
		if(!checkFlight(flightname)) {
			System.out.println("�ú��಻����");
			return false;}
		int amount=getTicketAmount(flightname);
		if(amount<0) {return false;}
		if(amount==0) {
			recommendFlightView(containerFlight.get(flightname).getTakeofflocation(), containerFlight.get(flightname).getLandlocation());
			return false;
		}
		setTicketAmount(flightname, amount-1);
		addReservation(flightname,username);
		return true;
	}
	
	/**
	 * ��ƱʱΪ����Ӷ���
	 * @param flightname
	 * @param username
	 * @return
	 * checked
	 */
	private boolean addReservation(String flightname, String username) {
		List<Reservation> obj=new ArrayList<Reservation>();
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		String datetime = tempDate.format(new java.util.Date()); 
		Reservation newReservation= new Reservation(username,flightname,datetime);
		if(containerList.containsKey(username)) {
			obj=containerList.get(username);
		}
		else {
			containerList.put(username, new ArrayList<Reservation>());
			obj=containerList.get(username);
		}
		obj.add(newReservation);
		return true;
	}

	/**
	 * ��Ʊ�� �Զ�Ϊ��һ����Ʊ
	 * @param flightname
	 * @return
	 * checked
	 */
	public boolean changeAppointment(String flightname) {
		Queue<String> queue=new LinkedList<String>();
		String username=null;
		if(!checkFlight(flightname)) {return false;}
		if(containerAppointment.containsKey(flightname)) {
			queue=containerAppointment.get(flightname);
			if(queue.isEmpty())
				return true;
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
	 * checked
	 */
	public void reserveTicket(String flightname,String username) {
		Queue<String> queue=new LinkedList<String>();
		if(containerAppointment.containsKey(flightname)) {
			queue=containerAppointment.get(flightname);
	    } 
		if(!containerAppointment.containsKey(flightname)) {
			containerAppointment.put(flightname,new LinkedList<String>());
			queue=containerAppointment.get(flightname);
			queue.add(username);
			return;
		}	
		queue.add(username);
		return;
	}
	
	/**
	 * ��ѯ����
	 * @param username �û���
	 * @return �����������ArrayList ��ѯʧ�ܷ���null
	 * checked
	 */
	public List<Reservation> searchReservation(String username){
		List<Reservation> obj=new ArrayList<Reservation>();
        if(!checkReservation(username)) {return null;}
        obj=containerList.get(username);
		return obj;
	}
	/*
	 * ��麽��źϷ�
	 */
	public boolean checkFlight(String flightname) {
		boolean check=container.containsKey(flightname);
		return check;
	}
	/*
	 * ����û����Ϸ�
	 */
	public boolean checkReservation(String username) {
		boolean check=containerList.containsKey(username);
		return check;
	}
	/*
	 * ��Ʊ
	 * checked
	 */
    public boolean refundTicket(String username, String flightname) {
    	List<Reservation> ticketlist=searchReservation(username);
    	if(ticketlist==null) {return false;}
    	boolean result=false;
    	for(int i=0;i<ticketlist.size();i++) {
    		Reservation target=ticketlist.get(i);
    		if(target.getFlightname().equals(flightname)) {
    			result=true;
    			ticketlist.remove(i);
    		}//����иú���� ��ɾ���ö���
    	}//���û�list����
    	if(result==false) {return false;}
    	containerList.remove(username);
    	containerList.put(username, ticketlist);//���������û�����
    	int newAmount=getTicketAmount(flightname);
    	newAmount+=1;
    	setTicketAmount(flightname,newAmount);//�ú�����Ʊ����һ
    	changeAppointment(flightname);
    	return true;
    }
	/*
	 * ������в�ѯ����
	 * checked
	 */
    @SuppressWarnings({ "null", "rawtypes" })
	public List<FlightInfo> searchFlightByCity(String takeofflocation,String landlocation) {
    	List<FlightInfo> targetflight=new ArrayList<FlightInfo>();
    	Iterator titer=containerFlight.keySet().iterator();
    	while(titer.hasNext()){
    		FlightInfo info=null;
    		info=containerFlight.get(titer.next());
    	   if(info.getLandlocation().equals(landlocation) && info.getTakeofflocation().equals(takeofflocation)) {
    		   targetflight.add(info);
    	   }
		}
    	return targetflight;
    }
	/*
	 * ��������
	 */
    public  List<FlightInfo> orderFlightByPrice(List<FlightInfo> targetflight) {
		if(targetflight.size()==0) {return null;}
        Collections.sort(targetflight, new Comparator<FlightInfo>(){
        	public int compare(FlightInfo o1, FlightInfo o2) {
				double i = o1.getPrice() - o2.getPrice();
				return (int)i;
        	}
        });
        return targetflight;
    }
    public  List<FlightInfo> orderFlightByAmount(List<FlightInfo> targetflight) {
		if(targetflight.size()==0) {return null;}
        Collections.sort(targetflight, new Comparator<FlightInfo>(){
        	public int compare(FlightInfo o1, FlightInfo o2) {
				int i = getTicketAmount(o1.getFlightname()) - getTicketAmount(o2.getFlightname());
				return -1*i;
        	}
        });
        return targetflight;
    }
    public List<FlightInfo> orderFlightByLandTime(List<FlightInfo> targetflight) {
		if(targetflight.size()==0) {return null;}
        Collections.sort(targetflight, new Comparator<FlightInfo>(){
        	public int compare(FlightInfo o1, FlightInfo o2) {
        		double a=Double.parseDouble(o1.getLandtime());
        		double b=Double.parseDouble(o2.getLandtime());
				double i =a-b;
				return (int)i;
        	}
        });
        return targetflight;
    }
    public  List<FlightInfo> orderFlightByTakeOffTime(List<FlightInfo> targetflight) {
		if(targetflight.size()==0) {return null;}
        Collections.sort(targetflight, new Comparator<FlightInfo>(){
        	public int compare(FlightInfo o1, FlightInfo o2) {
        		double a=Double.parseDouble(o1.getTakeofftime());
        		double b=Double.parseDouble(o2.getTakeofftime());
				double i =a-b;
				return (int)i;
        	}
        });
        return targetflight;
    }

    /**
         * �Ƽ�����
     * @param takeofflocation
     * @param landlocation
     */
	public void recommendFlightView(String takeofflocation, String landlocation) {
		List<FlightInfo> flight=new ArrayList<FlightInfo>();
		flight=searchFlightByCity(takeofflocation, landlocation);
		for(int i=0;i<flight.size();i++) {
			if(getTicketAmount(flight.get(i).getFlightname())==0) {
               flight.remove(flight.get(i));
			}
		}
		if(flight.size()==0) {
			System.out.println("����ֱ�ɺ�������ۿ�");
			return;
		}
		for(int i=0;i<flight.size();i++) {
			System.out.println("��"+(i+1)+"�Ƽ�������Ϣ���£�");
			printFlightInfo(flight.get(i));
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
		System.out.println("��Ʊ����"+container.get(flight.getFlightname()));
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

	//����ӻ�ɾ��������Ϣʱͬ������Ʊ���
	public void addFlight(String flightname) {
		container.put(flightname, 0);
	}
	
	public void deleteFlight(String flightname) {
		container.remove(flightname);
	}
}

