package container;
import java.util.*;
import entity.*;

/**
 * ������Ϣ����
 * @author
 * @version 1.0
 */
public class FlightInfoContainer {

	private Map<String,FlightInfo> container=null;
	
	/**
	 * ���췽��
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
