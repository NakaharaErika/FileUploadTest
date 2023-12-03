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

//各種入力チェック
function validateFile(file) {
    const allowedExtensions = 'jpg';
    const fileSizeLimit = 2 * 1024 * 1024; // 2MB in bytes
    const dimensionLimit = 3000;

    // 拡張子のチェック
    let extension = file.name.split('.').pop().toLowerCase();
    if (!allowedExtensions.includes(extension)) {
        return "ファイル形式はjpgのみ許可されています。";
    }
     // ファイルサイズのチェック
    if (file.size > fileSizeLimit) {
        return "ファイルサイズは2MB以下にしてください。";
    }
    // 画像サイズのチェック (非同期)
    return new Promise((resolve, reject) => {
        let img = new Image();
        img.src = URL.createObjectURL(file);
        img.onload = () => {
            if (img.width > dimensionLimit || img.height > dimensionLimit) {
                resolve("画像サイズは3000px * 3000px以下にしてください。");
            } else {
                resolve(null);
            }
        };
        img.onerror = reject;
    });
}
