package controller;
import entity.*;

/**
 * �û���Ϣ������
 * @author
 * @version 1.0
 */
public class UserInfoController {

	/**
	 * ���췽��
	 */
	public UserInfoController() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * �����˻�
	 * @param name �û��� �����ظ�
	 * @param passwd ����
	 * @return �������ɹ�����true
	 */
	public boolean createAccount(String name,String passwd) {
		return true;
	}
	
	/**
	 * �û���¼
	 * @param name �û���
	 * @param passwd ����
	 * @return ����½�ɹ�����һ�û�ʵ����� ��ʧ�ܷ���null
	 */
	public UserInfo logIn(String name,String passwd) {
		UserInfo obj=null;
		return obj;
	}
	
}
