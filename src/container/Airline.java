package container;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import entity.FlightInfo;

/**
 * 航线容器
 * @author
 * @version 1.0
 */
public class Airline {
	/*
	class Edge{
		String adjacentCity;//相邻的城市名称
		Edge next;
	}*/
	class Node{
		String city;//城市名称
		//Edge next;
		Set<String>adjacentNodeSet;	
		public Node(String city) {
			this.adjacentNodeSet=new TreeSet<String>();
			this.city=city;
		}
	}
	public Map<String, Node> nodeMap=null;//不设成public控制类没法用
	private Map<String,FlightInfo> flightinfocontainer=null;
	/**
	 * 构造方法
	 */
	public Airline(Map<String,FlightInfo> flightinfo) {
		// TODO Auto-generated constructor stub
		this.flightinfocontainer=flightinfo;
		this.initCity();
		this.initAirLine();
	}
	/**
	 * 初始化城市列表
	 */
	private void initCity() {
		this.nodeMap=new TreeMap<String, Airline.Node>();
		for(String flightName:flightinfocontainer.keySet()) {
			FlightInfo flight=flightinfocontainer.get(flightName);
			Node cityNode=new Node(flight.getTakeofflocation());
			nodeMap.put(flight.getTakeofflocation(),cityNode);
		}
	}
	/**
	 * 初始化每座城市出发的航线
	 */
	private void initAirLine() {
		for(String city:nodeMap.keySet()) {
			Node cityNode=nodeMap.get(city);
			for(String flightName:flightinfocontainer.keySet()) {
				FlightInfo flight=flightinfocontainer.get(flightName);
				if(city.equals(flight.getTakeofflocation())) {
					cityNode.adjacentNodeSet.add(flight.getLandlocation());
				}
			}
		}
	}
	/**
	 * 给定一个城市名称返回他的邻接的城市Set
	 */ 
	public Set<String>getAdjacent(String City){
		Node cityNode=nodeMap.get(City);
		return cityNode.adjacentNodeSet;
	}
	/**
	 * 确定邻接表里是否存在给定城市名
	 */
	public boolean whetherHaveCity(String head,String city) {
		boolean flag=false;
		Node cityNode=nodeMap.get(head);
		if(cityNode==null)
			return flag;
		flag=cityNode.adjacentNodeSet.contains(city);
		return flag;
	}
	/**
	 * 用于测试的方法
	 */
	public void testPrint() {
		for(String city:nodeMap.keySet()) {
			Node cityNode=nodeMap.get(city);
			System.out.println(cityNode.city);
			for(String adjCity:cityNode.adjacentNodeSet) {
				System.out.println(adjCity);
				}
			}
		}
	
}
