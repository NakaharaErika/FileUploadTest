package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import service.UploadFileByMul;

@WebServlet("/upload_file")
@MultipartConfig
public class UploadFileServletByMul extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int MAX_IMAGES = 2;
    //ECサイトの商品ページに写真を登録したい
    //管理者が商品登録・編集時に商品idを渡して管理できる
    //極力if文をサーブレット内で使わない
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	request.setAttribute("message", "写真をアップロードできます");
    	String view = "/WEB-INF/views/uploadFile.jsp";
        request.getRequestDispatcher(view).forward(request, response);   
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       //なぜか、hiddenを入れておかないと他のパラメータが取得できなかった。謎。
        String getparam = request.getParameter("getparam");
        int animalId = Integer.parseInt(request.getParameter("animalId"));
        List<Part> parts = new ArrayList<>();
        //ここでif文使っちゃうけど、次のメソッドでnullチェックするのでOK
        for (int i = 1; i <= MAX_IMAGES; i++) {
            Part imgPart = request.getPart("img" + i);
            if (imgPart != null && imgPart.getSize() > 0) {
                parts.add(imgPart);
            }
        }
       
        //リストをDBに登録し、プロジェクト内にも保存するメソッド
        String message = UploadFileByMul.processImageUpload(animalId, parts);


        request.setAttribute("message", message);
        String view = "/WEB-INF/views/uploadFile.jsp";
        request.getRequestDispatcher(view).forward(request, response);  
    }
}