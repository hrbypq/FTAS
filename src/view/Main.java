package view;

import java.sql.SQLException;

/**
 * 主类 包含主函数
 * @author ypq
 * @version 1.0
 */
public class Main {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		MainView mainview=new MainView();
		mainview.showMenu();
	}
}
