package view;
import controller.*;
import java.util.Scanner;

import javax.tools.Tool;

import container.*;
import entity.*;

/**
 * 用户界面类
 * @author
 * @version 1.0
 */
public class UserInfoView {
     private UserInfoContainer userinfoconatiner=null;
     private UserInfo currentuser=null;
     private UserInfoController usercontroller=null;
     private Tools tool =null;
	/**
	 * 构造方法
	 */
	public UserInfoView(UserInfoContainer userinfoconatiner,UserInfo currentuser) {
		// TODO Auto-generated constructor stub;
		this.currentuser=currentuser;
		this.userinfoconatiner=userinfoconatiner;
		this.usercontroller=new UserInfoController(userinfoconatiner.getContainer());
		this.tool=new Tools();
	}
	
	/**
	 * 登陆方法
	 */
	@SuppressWarnings("resource")
	private void logIn() {
		while(true) {
			String name,passwd;
			Scanner reader=new Scanner(System.in);
		    System.out.println("请输入用户名:\n");
		    name=reader.nextLine();
		    System.out.println("请输入密码:\n");
		    passwd=reader.nextLine();
		    currentuser=usercontroller.logIn(name,passwd);
		    reader.close();
		    if(currentuser!=null)
		    	return;
		}
	}
	/**
	 * 注册方法，注册后回退到主界面
	 */
	private void register() {
		while(true) {
			String name,passwd;
			Scanner reader=new Scanner(System.in);
		    System.out.println("请输入用户名:\n");
		    name=reader.nextLine();
		    System.out.println("请输入密码:\n");
		    passwd=reader.nextLine();
		    boolean whether_succeed=usercontroller.createAccount(name, passwd);
		    reader.close();
		    if(whether_succeed)
		    	return;
		}
	}
	/**
	 * 返回已登录的用户
	 */
	public UserInfo getUser() {
		return currentuser;
	}
	/**
	 * 菜单
	 */
	public void showTouristMenu() {
		System.out.println("输入1登陆用户，输入2注册用户");
		Scanner reader=new Scanner(System.in);
		int input=tool.inputInteger(1, 2);
		switch (input) {
		case 1:
			this.logIn();
			break;
		case 2:
			this.register();
		default:
			break;
		}
	}
	public void showAdminMenu() {
		System.out.println("欢迎登陆");
		this.logIn();
	}
}
