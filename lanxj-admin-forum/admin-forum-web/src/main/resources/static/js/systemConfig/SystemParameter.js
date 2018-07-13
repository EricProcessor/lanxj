
var systemParameterJS = {

    /**
     * 查询禁止发帖时间
     */
    queryPostForbiddenTime : function () {
        var url = "/systemParam/queryPostForbiddenTime";
        var param = {};
        $.post(url, param, function(data) {
            console.log(data)
            if(null!=data && "null"!=data && data.code == 0){
                $("#show").val(data.data);
            }else{
                alert(data.msg);
            }
        }, "json");
    },

    queryPostForbiddenTimeOnclick : function () {
        systemParameterJS.queryPostForbiddenTime();
    },

    /**
     * 设置禁止发帖时间
     */
    setPostForbiddenTime : function (postForbiddenTime) {
        var url = "/systemParam/setPostForbiddenTime";
        var param = {
            "postForbiddenTime":postForbiddenTime
        };
        $.post(url, param, function(data) {
            console.log(data)
            if(null!=data && "null"!=data && data.code == 0){
                $("#show").val(data.msg);
            }else{
                alert(data.msg);
            }
        }, "json");
    },

    setPostForbiddenTimeOnclick : function () {
        var postForbiddenTime = $("#request").val();
        systemParameterJS.setPostForbiddenTime(postForbiddenTime);
    }
}