package container;
import java.util.*;
import entity.*;

/**
 * 航班信息容器
 * @author
 * @version 1.0
 */
public class FlightInfoContainer {

	private Map<String,FlightInfo> container=null;
	
	/**
	 * 构造方法
	 */
	public FlightInfoContainer() {
		container=new TreeMap<String,FlightInfo>();
	}

	public Map<String, FlightInfo> getContainer() {
		return container;
	}

	public void setContainer(Map<String, FlightInfo> container) {
		this.container = container;
	}

}
