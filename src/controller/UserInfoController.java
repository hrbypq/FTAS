package controller;
import entity.*;

/**
 * 用户信息控制类
 * @author
 * @version 1.0
 */
public class UserInfoController {

	/**
	 * 构造方法
	 */
	public UserInfoController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 创建账户
	 * @param name 用户名 不可重复
	 * @param passwd 密码
	 * @return 若创建成功返回true
	 */
	public boolean createAccount(String name,String passwd) {
		return true;
	}
	
	/**
	 * 用户登录
	 * @param name 用户名
	 * @param passwd 密码
	 * @return 若登陆成功返回一用户实体对象 若失败返回null
	 */
	public UserInfo logIn(String name,String passwd) {
		UserInfo obj=null;
		return obj;
	}
	
}
