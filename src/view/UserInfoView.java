package view;
import controller.*;
import java.util.Scanner;

import javax.tools.Tool;

import container.*;
import entity.*;

/**
 * �û�������
 * @author
 * @version 1.0
 */
public class UserInfoView {
     private UserInfoContainer userinfoconatiner=null;
     private UserInfo currentuser=null;
     private UserInfoController usercontroller=null;
     private Tools tool =null;
	/**
	 * ���췽��
	 */
	public UserInfoView(UserInfoContainer userinfoconatiner,UserInfo currentuser) {
		// TODO Auto-generated constructor stub;
		this.currentuser=currentuser;
		this.userinfoconatiner=userinfoconatiner;
		this.usercontroller=new UserInfoController(userinfoconatiner.getContainer());
		this.tool=new Tools();
	}
	
	/**
	 * ��½����
	 */
	@SuppressWarnings("resource")
	private void logIn() {
		while(true) {
			String name,passwd;
			Scanner reader=new Scanner(System.in);
		    System.out.println("�������û���:\n");
		    name=reader.nextLine();
		    System.out.println("����������:\n");
		    passwd=reader.nextLine();
		    currentuser=usercontroller.logIn(name,passwd);
		    reader.close();
		    if(currentuser!=null)
		    	return;
		}
	}
	/**
	 * ע�᷽����ע�����˵�������
	 */
	private void register() {
		while(true) {
			String name,passwd;
			Scanner reader=new Scanner(System.in);
		    System.out.println("�������û���:\n");
		    name=reader.nextLine();
		    System.out.println("����������:\n");
		    passwd=reader.nextLine();
		    boolean whether_succeed=usercontroller.createAccount(name, passwd);
		    reader.close();
		    if(whether_succeed)
		    	return;
		}
	}
	/**
	 * �����ѵ�¼���û�
	 */
	public UserInfo getUser() {
		return currentuser;
	}
	/**
	 * �˵�
	 */
	public void showTouristMenu() {
		System.out.println("����1��½�û�������2ע���û�");
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
		System.out.println("��ӭ��½");
		this.logIn();
	}
}
