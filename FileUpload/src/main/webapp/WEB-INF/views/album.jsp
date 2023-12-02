<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>アルバム</title>
    <style>
        .photo {
            width: 300px;
            height: 300px;
            object-fit: cover; /* 画像のアスペクト比を保持しつつ、指定されたサイズに合わせる */
            margin: 10px; /* 画像間の余白 */
        }
        .photo-container {
            display: flex;
            flex-wrap: wrap; /* 複数行に渡って表示 */
        }
    </style>
</head>
<body>
    <main>
        <div class="container workspace">
            <div class="photo-container">
                <% 
                // 写真のリストを取得
                List<String> photos = (List<String>) request.getAttribute("photos");
                if (photos != null) {
                    for (String photo : photos) {
                    	String imagePath = request.getContextPath() + "/upload/" + photo + ".jpg";
                        %>
                        <img src="<%= imagePath %>" class="photo" alt="写真">
                        <%
                    }
                }
                %>
            </div>
        </div>
    </main>
    <footer class="footer mt-auto fixed-bottom py-3 bg-secondary"></footer>
</body>
</html>
