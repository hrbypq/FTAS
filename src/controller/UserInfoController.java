package controller;
import java.util.Map;

import container.*;
import entity.*;
/**
 * �û���Ϣ������
 * @author
 * @version 1.0
 */
public class UserInfoController {
	private Map<String,UserInfo> container=null;
	
	/**
	 * ���췽��
	 */
	public UserInfoController( Map<String,UserInfo> container) {
		this.container=container;
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * �����˻�
	 * @param name �û��� �����ظ�
	 * @param passwd ����
	 * @return �������ɹ�����true
	 */
	public boolean createAccount(String name,String passwd) {
		if(container.containsKey(name))
			return false;
		else {
			UserInfo new_user=new UserInfo(name,passwd);
			container.put(name, new_user);
			return true;
		}
		
	}
	
	/**
	 * �û���¼
	 * @param name �û���
	 * @param passwd ����
	 * @return ����½�ɹ�����һ�û�ʵ����� ��ʧ�ܷ���null
	 */
	public UserInfo logIn(String name,String passwd) {
		UserInfo obj=null;
		obj=container.get(name);
		if(obj.getPasswd()!=passwd)
			obj=null;//������󣬵�½ʧ��
		return obj;
	}
	
}
