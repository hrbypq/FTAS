package entity;

/**
 * �û���Ϣʵ����
 * @author ypq
 * @version 1.0
 */
public class UserInfo implements Cloneable{

	private String username=null;   //�û���
	private String passwd=null;     //����
	
	/**
	 * �޲ι��췽��
	 */
	public UserInfo() {
	}
	
	/**
	 * �вι��췽��
	 * @param username �û���
	 * @param passwd ����
	 */
	public UserInfo(String username,String passwd) {
		this.username=username;
		this.passwd=passwd;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
}
