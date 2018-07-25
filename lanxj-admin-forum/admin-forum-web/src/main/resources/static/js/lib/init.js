/**
 * Created by wj-jdyy on 2017/5/18.
 */

$.ajax({
    type: 'GET',
    url: '../js/lib/config.json',
    dataType: 'json',
    aysnc: false,
    success: function (data) {
        host = data.host;
        webType = data.webType;
        // if (webType != "app") {
        //     var _hmt = _hmt || [];
        //     (function () {
        //         var hm = document.createElement("script");
        //         hm.src = "https://hm.baidu.com/hm.js?a49e48494cd334d11806f2e21fff8beb";
        //         var s = document.getElementsByTagName("script")[0];
        //         s.parentNode.insertBefore(hm, s);

        //         var hm1 = document.createElement("script");
        //         hm1.src = "https://hm.baidu.com/hm.js?f35013cc03597f3881c6fe4208b132cf";
        //         var s1 = document.getElementsByTagName("script")[0];
        //         s1.parentNode.insertBefore(hm1, s1);

        //     })();
        // }
    },
    error: function (xhr, type) {

    }
});
