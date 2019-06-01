package view;

import controller.*;
import java.util.Scanner;
import container.*;
import entity.*;

/**
 * 用户界面类
 * @author
 * @version 1.0
 */
public class UserInfoView {
     private UserInfoContainer userinfocontainer=null;
     private UserInfo currentuser=null;
     private UserInfoController usercontroller=null;
     
	/**
	 * 构造方法
	 */
	public UserInfoView(UserInfoContainer userinfocontainer) {
		this.userinfocontainer=userinfocontainer;
		this.usercontroller=new UserInfoController(userinfocontainer.getContainer());
	}
	
	/**
	 * 登录
	 */
	private void logIn() {
		String name=null;
		String passwd=null;
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		while(true) {
		    System.out.println("请输入用户名:");
		    name=scanner.nextLine();
		    System.out.println("请输入密码:");
		    passwd=scanner.nextLine();
		    currentuser=usercontroller.logIn(name,passwd);
		    if(currentuser!=null) {
		    	System.out.println("您好，"+currentuser.getUsername());
		    	break;
		    }
		    System.out.println("密码错误或用户名不存在，请重新输入!");
		}
	}
	
	/**
	 * 注册
	 */
	@SuppressWarnings("resource")
	private void register() {
		String name=null;
		String passwd=null;
		Scanner reader=new Scanner(System.in);
		while(true) {
		    System.out.println("请输入用户名:");
		    name=reader.nextLine();
		    System.out.println("请输入密码:");
		    passwd=reader.nextLine();
		    boolean whether_succeed=usercontroller.createAccount(name, passwd);
		    if(whether_succeed) {
		    	currentuser=userinfocontainer.getContainer().get(name);	
		    	break;
		    }
		    System.out.println("用户名已存在!");
		}
	}
	
	/**
	 * 返回已登录的用户
	 */
	public UserInfo getUser() {
		return currentuser;
	}
	
	/**
	 * 用户菜单
	 */
	public void showUserMenu() {
		System.out.println("1.注册新用户\n2.用户登录\n请输入选项:");
		int input=Tools.inputInteger(1, 2);
		switch (input) {
		case 1:
			this.register();
			break;
		case 2:
			this.logIn();
			break;
		}
	}
	
	/**
	 * 管理员菜单
	 */
	public void showAdminMenu() {
		System.out.println("请输入管理员密码:");
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		String input=null; 
		while(true) {
			input=scanner.nextLine();
			if(input.equals("123456"))
				break;
			System.out.println("密码错误，请重新输入");
		}
		//scanner.close();
	}
}
