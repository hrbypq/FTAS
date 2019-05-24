package container;
import java.util.*;

import entity.Reservation;

public class AppointmentContainer {
	private Map<String,Queue<String>> container=null;
	//航班号 和 航班预约队列
	//队列中存放预约的用户
	/**
	 * 构造方法
	 */
	public AppointmentContainer() {
		container=new TreeMap<String,Queue<String>>();
	}

	public Map<String, Queue<String>> getContainer() {
		return container;
	}

	public void setContainer(Map<String, Queue<String>> container) {
		this.container = container;
	}

}
