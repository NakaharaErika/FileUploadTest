<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="./js/script.js"></script>
	<link rel="stylesheet" href="./css/style.css">
    <title>JavaDevelop</title>
</head>
<body>
    <main>
        <div class="container workspace">
        <%String message = (String)request.getAttribute("message"); %>
		<% if (message != null) {%>
			    <%=message %>
		<%} %>
		
		
            <form action="upload_file"  method="post" enctype="multipart/form-data">
            	
            	<input type="radio" id="dog" name="animalId" value="1" checked>
			    <label for="dog">犬</label>
			    <input type="radio" id="bird" name="animalId" value="2">
			    <label for="bird">鳥</label>
			    <input type="radio" id="cat" name="animalId" value="3">
			    <label for="cat">猫</label>
			    
			    <input type="hidden" name="getparam" value="取れてる?">
			    <br><br>
			    <label>ファイル 画像1：</label>
			    <input type="file" name="img1" onchange="previewImage('image-preview1');" />
			    <br>
			    <label>ファイル 画像2：</label>
			    <input type="file" name="img2" onchange="previewImage('image-preview2');" />
			    <br>
			    <input type="submit" />
			</form>
            <hr/>
            <div class="upload">
                <p>アップロードファイル：画像1</p>
                <img id="image-preview1" class="image-preview" alt="選択された画像プレビュー1"/>
            </div>
            <div class="upload">
                <p>アップロードファイル：画像2</p>
                <img id="image-preview2" class="image-preview" alt="選択された画像プレビュー2"/>
            </div>
            <hr/>
        </div>
    </main>
    <footer class="footer mt-auto fixed-bottom py-3 bg-secondary"></footer>
</body>
</html>
