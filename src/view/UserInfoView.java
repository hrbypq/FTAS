package view;

import controller.*;
import java.util.Scanner;
import container.*;
import entity.*;

/**
 * �û�������
 * @author
 * @version 1.0
 */
public class UserInfoView {
     private UserInfoContainer userinfocontainer=null;
     private UserInfo currentuser=null;
     private UserInfoController usercontroller=null;
     
	/**
	 * ���췽��
	 */
	public UserInfoView(UserInfoContainer userinfocontainer) {
		this.userinfocontainer=userinfocontainer;
		this.usercontroller=new UserInfoController(userinfocontainer.getContainer());
	}
	
	/**
	 * ��¼
	 */
	private void logIn() {
		String name=null;
		String passwd=null;
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		while(true) {
		    System.out.println("�������û���:");
		    name=scanner.nextLine();
		    System.out.println("����������:");
		    passwd=scanner.nextLine();
		    currentuser=usercontroller.logIn(name,passwd);
		    if(currentuser!=null) {
		    	System.out.println("���ã�"+currentuser.getUsername());
		    	break;
		    }
		    System.out.println("���������û��������ڣ�����������!");
		}
	}
	
	/**
	 * ע��
	 */
	@SuppressWarnings("resource")
	private void register() {
		String name=null;
		String passwd=null;
		Scanner reader=new Scanner(System.in);
		while(true) {
		    System.out.println("�������û���:");
		    name=reader.nextLine();
		    System.out.println("����������:");
		    passwd=reader.nextLine();
		    boolean whether_succeed=usercontroller.createAccount(name, passwd);
		    if(whether_succeed) {
		    	currentuser=userinfocontainer.getContainer().get(name);	
		    	break;
		    }
		    System.out.println("�û����Ѵ���!");
		}
	}
	
	/**
	 * �����ѵ�¼���û�
	 */
	public UserInfo getUser() {
		return currentuser;
	}
	
	/**
	 * �û��˵�
	 */
	public void showUserMenu() {
		System.out.println("1.ע�����û�\n2.�û���¼\n������ѡ��:");
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
	 * ����Ա�˵�
	 */
	public void showAdminMenu() {
		System.out.println("���������Ա����:");
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		String input=null; 
		while(true) {
			input=scanner.nextLine();
			if(input.equals("123456"))
				break;
			System.out.println("�����������������");
		}
		//scanner.close();
	}
}
