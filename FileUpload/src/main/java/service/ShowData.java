package service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import dao.DBConnection;
import dao.WorkDaoJDBC;

public class ShowData {
	
	//動物Idをキーにして、DB上のファイル名一覧を取得
	public static List<String> serchIdList(int animalId) {
		if(animalId < 0) {
			return null;
		}
		
       String serchSQL = "SELECT file_name FROM photo WHERE animal_id = ?";
       List<Object> params = Arrays.asList(animalId);
	   	try (Connection conn = DBConnection.createConnection();
	  		 ResultSet rs = WorkDaoJDBC.executeQuery(conn, serchSQL, params)) {
	   		//得られた結果にファイルパスを繋げてリストに格納	
	   		List<String> photos = new ArrayList<>();
		        while (rs.next()) {
		        	String file = rs.getString("file_name");
		        	photos.add(file);
		        }
		        return photos;
	   	} catch (ClassNotFoundException | SQLException e) {
				e.printStackTrace();
		}
	   	
	return null;
	}
}

