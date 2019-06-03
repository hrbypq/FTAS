package controller;
//import container.TicketContainer;
//import container.FlightInfoContainer;
//import container.ReservationContainer;

import java.text.SimpleDateFormat;
import java.util.*;
import entity.*;

/**
 * 机票控制类
 * @author
 * @version 1.0
 */
public class TicketController {

	private static Map<String,List<Reservation>> containerList=new TreeMap<String,List<Reservation>>();
	private static Map<String,Integer> container= new TreeMap<String,Integer>();
	private static Map<String,FlightInfo> containerFlight= new TreeMap<String,FlightInfo>();
	private static Map<String,Queue<String>> containerAppointment= new TreeMap<String,Queue<String>>();
	
	/** 
	 * 构造方法
	 */
	public TicketController(Map<String,Integer> container,Map<String,List<Reservation>> containerList,Map<String,FlightInfo> containerFlight,Map<String,Queue<String>> containerAppointment) {
		TicketController.container=container;
		TicketController.containerList=containerList;
		TicketController.containerFlight=containerFlight;
		TicketController.containerAppointment=containerAppointment;
	}

	/**
	 * 查询余票量 
	 * @param flightname 航班号
	 * @return 余票量 查询失败返回-1
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
	 * 供管理员设置余票量
	 * @param flightname 航班号
	 * @param amount 新的余票量
	 * @return 是否设置成功
	 */
	public boolean setTicketAmount(String flightname,int amount) {
		if(!checkFlight(flightname)) {
			System.out.println("未查询到航班");
			return false;}
		if(amount<0) {
			System.out.println("余票量不得为负数");
			return false;}
		container.remove(flightname);
		container.put(flightname,amount);
		return true;
	}
	
	/**
	 * 旅客买票
	 * @param flightname 航班号
	 * @return 是否购买成功
	 * checked
	 */
	public boolean buyTicket(String flightname,String username) {
		if(!checkFlight(flightname)) {
			System.out.println("该航班不存在");
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
	 * 购票时为其添加订单
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
	 * 退票后 自动为第一个抢票
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
	 * 预约买票
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
	 * 查询订单
	 * @param username 用户名
	 * @return 包含订单类的ArrayList 查询失败返回null
	 * checked
	 */
	public List<Reservation> searchReservation(String username){
		List<Reservation> obj=new ArrayList<Reservation>();
        if(!checkReservation(username)) {return null;}
        obj=containerList.get(username);
		return obj;
	}
	/*
	 * 检查航班号合法
	 */
	public boolean checkFlight(String flightname) {
		boolean check=container.containsKey(flightname);
		return check;
	}
	/*
	 * 检查用户名合法
	 */
	public boolean checkReservation(String username) {
		boolean check=containerList.containsKey(username);
		return check;
	}
	/*
	 * 退票
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
    		}//如果有该航班号 则删除该订单
    	}//该用户list遍历
    	if(result==false) {return false;}
    	containerList.remove(username);
    	containerList.put(username, ticketlist);//重新设置用户订单
    	int newAmount=getTicketAmount(flightname);
    	newAmount+=1;
    	setTicketAmount(flightname,newAmount);//该航班余票量加一
    	changeAppointment(flightname);
    	return true;
    }
	/*
	 * 输入城市查询航班
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
	 * 航班排序
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
         * 推荐航班
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
			System.out.println("所有直飞航班均已售空");
			return;
		}
		for(int i=0;i<flight.size();i++) {
			System.out.println("第"+(i+1)+"推荐航班信息如下：");
			printFlightInfo(flight.get(i));
		}
		return;
	}
	
	private void printFlightInfo(FlightInfo flight) {
		System.out.println("航班号："+flight.getFlightname());
		System.out.println("起飞城市："+flight.getTakeofflocation());
		System.out.println("起飞时间："+flight.getTakeofftime());
		System.out.println("降落城市："+flight.getLandlocation());
		System.out.println("降落时间："+flight.getLandtime());
		System.out.println("航空公司："+flight.getCompany());
		System.out.println("价格："+flight.getPrice());
		System.out.println("余票量："+container.get(flight.getFlightname()));
		if(flight.getPasslocation()==null||flight.getPasstime()==null||flight.getPasslocation().equals("null")||flight.getPasstime().equals("null")) {
			System.out.println("经停城市：无");
			System.out.println("经停时间：无");
		}
		else {
			System.out.println("经停城市："+flight.getPasslocation());
			System.out.println("经停时间："+flight.getPasstime());
		}
		System.out.println();
    }

	//在添加或删除航班信息时同步更新票务表
	public void addFlight(String flightname) {
		container.put(flightname, 0);
	}
	
	public void deleteFlight(String flightname) {
		container.remove(flightname);
	}
}

