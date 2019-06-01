package controller;

import java.util.*;

/**
 * ������
 * @author
 * @version 1.0
 */
public class Tools {

	/**
	 * ���췽��
	 */
	public Tools() {
	}

	/**
	 * ����ָ����Χ������  ����ѡ��˵��е�ѡ��
	 * @param lo ��ʼ����
	 * @param hi ��ֹ����
	 * @return ����ֵ
	 */
	public static int inputInteger(int lo,int hi) {
		int input=0;
		@SuppressWarnings("resource")
		Scanner scanner=new Scanner(System.in);
		while(true) {
			try {
				input=scanner.nextInt();
				if(input<lo||input>hi) 
					throw new Exception();
			}
			catch(Exception e) {
				System.out.println("������������������");
				scanner=new Scanner(System.in);
				continue;
			}
			break;
		}
		//scanner.close();
		return input;
	}
	
	
}
