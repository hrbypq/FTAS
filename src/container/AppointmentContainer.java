package container;
import java.util.*;

/**
 * ԤԼ��������
 * @author czq
 * @version 1.0
 */
public class AppointmentContainer {
	private Map<String,Queue<String>> container=null;
	//����� �� ����ԤԼ����
	//�����д��ԤԼ���û�
	/**
	 * ���췽��
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
