var HeaderObj = {
    clickNavgat: function () {
        $(".nav").on("click", "li", function () {
            $(this).addClass("active").siblings().removeClass("active");
            var url = $(this).attr("data-url");
            $("#external-frame").attr("src",url);
            setIframeHeight(document.getElementById('external-frame'));
        })
    }
}

    function setIframeHeight(iframe) {
        if (iframe) {
            var iframeWin = iframe.contentWindow || iframe.contentDocument.parentWindow;
            if (iframeWin.document.body) {
                iframe.height = iframeWin.document.documentElement.scrollHeight || iframeWin.document.body.scrollHeight;
            }
        }
    };

    // window.onload = function () {
    //     setIframeHeight(document.getElementById('external-frame'));
    // };


$(function () {
    HeaderObj.clickNavgat();
})