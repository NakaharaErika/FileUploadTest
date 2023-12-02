package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class WorkDaoJDBC {
	
	//汎用メソッド(削除、更新、作成)
	public static void executeUpdate(Connection conn, String sql, List<Object> params) throws ClassNotFoundException, SQLException {
			PreparedStatement stmt = conn.prepareStatement(sql);
			setParameters(stmt, params);
			stmt.executeUpdate();
	}
	
	//汎用メソッド（読み出し）
	//今回はResultsetを返すパターンで練習
	public static ResultSet executeQuery(Connection conn, String sql, List<Object> params) throws SQLException, ClassNotFoundException{
			PreparedStatement stmt = conn.prepareStatement(sql);
			setParameters(stmt, params);
			 return stmt.executeQuery();
	}
	
	//変数の格納
	private static void setParameters(PreparedStatement stmt, List<Object> params) throws SQLException{
		for (int i = 0; i < params.size(); i++) {
			stmt.setObject(i + 1, params.get(i));
		}
	}
	
}