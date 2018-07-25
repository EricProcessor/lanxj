function imgPreview(fileDom){
    console.log(fileDom.id)
    $(fileDom).css("display", "none");	
    $(fileDom).next().css("display", "inline-block");

    //判断是否支持FileReader
    var n = fileDom.id
    if (window.FileReader) {
        var reader = new FileReader();
    } else {
        alert("您的设备不支持图片预览功能，如需该功能请升级您的设备！");
    }

    //获取文件
    var file = fileDom.files[0];
    var imageType = /^image\//;
    //是否是图片
    if (!imageType.test(file.type)) {
        alert("请选择图片！");
        return;
    }
    //读取完成
    reader.onload = function(e) {
        var img = ''
        var preview = 'preview'+n
        console.log(preview)
        $(preview).src = e.target.result;
        $(preview).parent().html()
        $(preview).parent().css("display", "inline-block");
        // document.getElementById(preview).src = e.target.result;
        // document.getElementById(preview).parentElement.html()
        // document.getElementById(preview).parentElement.css("display", "inline-block");
    };
    reader.readAsDataURL(file);
}