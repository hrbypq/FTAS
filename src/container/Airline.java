package container;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import entity.FlightInfo;

/**
 * ��������
 * @author
 * @version 1.0
 */
public class Airline {
	/*
	class Edge{
		String adjacentCity;//���ڵĳ�������
		Edge next;
	}*/
	class Node{
		String city;//��������
		//Edge next;
		Set<String>adjacentNodeSet;	
		public Node(String city) {
			this.adjacentNodeSet=new TreeSet<String>();
			this.city=city;
		}
	}
	public Map<String, Node> nodeMap=null;//�����public������û����
	private Map<String,FlightInfo> flightinfocontainer=null;
	/**
	 * ���췽��
	 */
	public Airline(Map<String,FlightInfo> flightinfo) {
		// TODO Auto-generated constructor stub
		this.flightinfocontainer=flightinfo;
		this.initCity();
		this.initAirLine();
	}
	/**
	 * ��ʼ�������б�
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
	 * ��ʼ��ÿ�����г����ĺ���
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
	 * ����һ���������Ʒ��������ڽӵĳ���Set
	 */ 
	public Set<String>getAdjacent(String City){
		Node cityNode=nodeMap.get(City);
		return cityNode.adjacentNodeSet;
	}
	/**
	 * ȷ���ڽӱ����Ƿ���ڸ���������
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
	 * ���ڲ��Եķ���
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
