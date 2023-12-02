package servlet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import service.ShowData;

@WebServlet("/get_album")
public class ShowDataServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    //DBから値を受け取ってファイルパスと結合してリスト化
    //リストjspに表示させる
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	int animalId = 1;// 例えば、ECサイトならクリックされた商品Idを格納
    	//idを受け取ってsqlを発行,ファイル名を取得
    	List<String> photos = new ArrayList<>(ShowData.serchIdList(animalId));
    	
    	request.setAttribute("photos", photos);
    	String view = "/WEB-INF/views/album.jsp";
        request.getRequestDispatcher(view).forward(request, response);   
    }
}