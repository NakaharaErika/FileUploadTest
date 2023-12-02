package response;

// 応答用のクラス
public class UploadResponse {
    private String message;
    private String view;
	

    public UploadResponse(String message, String view) {  
    	this.message = message;
        this.view = view;
    }
    
   

    public String getMessage() {
        return message;
    }

    public String getView() {
        return view;
    }
}