package service;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

import dao.DBConnection;
import dao.WorkDaoJDBC;
import jakarta.servlet.http.Part;

public class UploadFileByMul {
	public static String processImageUpload(int animalId, List<Part> parts) {
        if (parts.isEmpty()) {
            return "写真をアップロードしてください";
        }

        for (Part part : parts) {
            int nextPhotoId = getLatestNum(animalId);
            String imgName = setNextName(nextPhotoId, animalId);
            fileUpload(part, imgName);
            registerPhoto(nextPhotoId, animalId, imgName);
        }
        return "写真を登録しました";
    }

		
	public static Integer getLatestNum(int animalId) {
		if(animalId <= 0) {
			return null;
		}
		//最新番号を探索するSQL
		String latestNumberSQL = "SELECT photo_id FROM photo WHERE animal_id = ? ORDER BY photo_id DESC limit 1";
		List<Object> params = Arrays.asList(animalId);
    	try (Connection conn = DBConnection.createConnection();
   		     ResultSet rs = WorkDaoJDBC.executeQuery(conn, latestNumberSQL, params)) {
    		
    		int currentPhotoId = 0;
	        if (rs.next()) {
	        	//先頭の値をインクリメントして返す
	        	currentPhotoId = rs.getInt("photo_id");
	        	int photoId = currentPhotoId + 1;
	        	return photoId;
	        }
	        return currentPhotoId;
    	} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//DB接続して、転送されたファイルを一意の名前で保存
	//動物id:01+写真番号:001 = 01001の5桁.jpg　
	public static String setNextName (int nextPhotoId,int animalId) {
		if(nextPhotoId < 0 || animalId < 0) {
			return null;
		}
        	//001形式に変換
        	String formatPId = String.format("%03d", nextPhotoId);
        	//動物idを01形式に変換
        	String formatAId = String.format("%02d", animalId);
        	
        	return formatAId + formatPId;
	}
	
	
	//画像をプロジェクト内に格納(jpgに変換)
	public static void fileUpload(Part part,String imgName) {
		if (part == null || "".equals(part.getSubmittedFileName())) {
            return;
        }
		 try {
		    // フルパスじゃないと上手く読み込まれないみたい
		    String filePath = "/Users/nakahara.erika/git/FileUploadTest/FileUpload/src/main/webapp/upload/" + imgName + ".jpg";
		    // ファイルを保存
			part.write(filePath);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//新しいファイル名をDBに登録
	public static void registerPhoto(int photoId,int animalId,String fileName) {
		//更新するSQL
		String registSQL = "INSERT INTO photo ("
	            + "photo_id, animal_id, file_name"
	            + ") VALUES (?, ?, ?);";
		List<Object> params = Arrays.asList(photoId,animalId,fileName);
    	try(Connection conn = DBConnection.createConnection()){
    		WorkDaoJDBC.executeUpdate(conn, registSQL, params);
    	} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
	}

}

