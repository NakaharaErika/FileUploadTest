package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{
	//DB接続のための共有メソッド
		public static Connection createConnection() throws ClassNotFoundException, SQLException {
			String dbUrl = "jdbc:mysql://localhost/presen";
			String dbUser = "root";
			String dbPassword = "";
			// JDBCドライバーをロード
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		}
}