// 画像プレビュー機能を追加
function previewImage(previewId) {
    let preview = document.getElementById(previewId);
    let file = event.target.files[0];
    let reader = new FileReader();

    reader.onloadend = function() {
        preview.src = reader.result;
        preview.style.display = 'block'; // 画像を表示
    }

    if (file) {
        reader.readAsDataURL(file);
    } else {
        preview.src = "";
        preview.style.display = 'none'; // 画像がない場合は非表示
    }
}