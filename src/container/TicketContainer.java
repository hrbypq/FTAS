package container;
import java.util.*;

/**
 * ��Ʊ����
 * @author
 * @version 1.0
 */
public class TicketContainer {

	private Map<String,Integer> container=null;   //keyΪ�����
	
	/**
	 * ���췽��
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
