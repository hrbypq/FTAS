package entity;

/**
 * 用户信息实体类
 * @author ypq
 * @version 1.0
 */
public class UserInfo implements Cloneable{

	private String username=null;   //用户名
	private String passwd=null;     //密码
	
	/**
	 * 无参构造方法
	 */
	public UserInfo() {
	}
	
	/**
	 * 有参构造方法
	 * @param username 用户名
	 * @param passwd 密码
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
