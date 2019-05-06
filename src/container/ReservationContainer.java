package container;
import java.util.*;
import entity.*;

/**
 * 订单容器
 * @author
 * @version 1.0
 */
public class ReservationContainer {

	private Map<String,List<Reservation>> container=null;
	
	/**
	 * 构造方法
	 */
	public ReservationContainer() {
		container=new TreeMap<String,List<Reservation>>();
	}

	public Map<String, List<Reservation>> getContainer() {
		return container;
	}

	public void setContainer(Map<String, List<Reservation>> container) {
		this.container = container;
	}

}
