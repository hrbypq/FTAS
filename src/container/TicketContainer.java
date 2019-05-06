package container;
import java.util.*;

/**
 * 机票容器
 * @author
 * @version 1.0
 */
public class TicketContainer {

	private Map<String,Integer> container=null;   //key为航班号
	
	/**
	 * 构造方法
	 */
	public TicketContainer() {
		container=new TreeMap<String,Integer>();
	}

	public Map<String, Integer> getContainer() {
		return container;
	}

	public void setContainer(Map<String, Integer> container) {
		this.container = container;
	}

}
