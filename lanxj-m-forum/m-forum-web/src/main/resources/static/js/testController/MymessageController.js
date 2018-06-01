
var MymessageControllerJS={


    /**
     * 我的消息详情插入
     */
    insertMymessageInfoBycondition : function (msgId,userId,postId,postuserId,postuserName,attentuserId,postprefixcontext) {
        var url = "/myMessageList/insertmessagedtl";
        var param = {
            "msgId": msgId,
            "userId" : userId,
            "postId" : postId,
            "postuserId" : postuserId,
            "postuserName" : postuserName,
            "attentuserId" : attentuserId,
            "postprefixcontext" : postprefixcontext
        };
        $.post(url, param, function(data) {
            var data = JSON.stringify(data)
            if(null==data || "null"==data){
                alert("系统错误，请稍后重试！");
            }else{
                $("#show").val(data);
            }
        }, "json");
    },

    insertMymessageInfoOnclick : function () {
        var msgId = $("#msgId").val();
        var userId = $("#userId").val();
        var postId = $("#postId").val();
        var postuserId = $("#postuserId").val();
        var postuserName = $("#postuserName").val();
        var attentuserId = $("#attentuserId").val();
        var postprefixcontext = $("#postprefixcontext").val();

        MymessageControllerJS.insertMymessageInfoBycondition(msgId,userId,postId,postuserId,postuserName,attentuserId,postprefixcontext);
    }





}

