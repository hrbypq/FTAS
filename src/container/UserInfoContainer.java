package container;
import java.util.*;
import entity.*;

/**
 * �û���Ϣ����
 * @author
 * @version 1.0
 */
public class UserInfoContainer {

	private Map<String,UserInfo> container=null;
	
	/**
	 * ���췽��
	 */
	public UserInfoContainer() {
		container=new TreeMap<String,UserInfo>();
	}
	
	public Map<String, UserInfo> getContainer() {
		return container;
	}
	public void setContainer(Map<String, UserInfo> container) {
		this.container = container;
	}

}
