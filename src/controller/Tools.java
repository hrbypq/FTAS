package controller;

import java.util.*;

/**
 * 工具类
 * @author
 * @version 1.0
 */
public class Tools {

	/**
	 * 构造方法
	 */
	public Tools() {
	}

	/**
	 * 输入指定范围的整数  用来选择菜单中的选项
	 * @param lo 起始数字
	 * @param hi 终止数字
	 * @return 输入值
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
				System.out.println("输入有误，请重新输入");
				scanner=new Scanner(System.in);
				continue;
			}
			break;
		}
		//scanner.close();
		return input;
	}
	
	
}
