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
import response.UploadResponse;
import service.UploadFileByMul;

@WebServlet("/upload_file")
@MultipartConfig
public class UploadFileServletByMul extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final int MAX_IMAGES = 2;
    //ECサイトの商品ページに写真を登録したい
    //管理者が商品登録・編集時に商品idを渡して管理できる
    //Junitテストのために、全力でif文を使わない(Beans化)
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
        
        for (int i = 1; i <= MAX_IMAGES; i++) {
            parts.add(request.getPart("img" + i));
        }

        // UploadFileByMulに処理を飛ばす
        UploadResponse uploadResponse = UploadFileByMul.handleUploadRequest(animalId, parts);

        // 結果に基づいて応答を設定
        request.setAttribute("message", uploadResponse.getMessage());
        request.getRequestDispatcher(uploadResponse.getView()).forward(request, response);
    }
}