
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
                // $("#show").val(data);
                console.log(data)
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
    },




    MymessageInfo : function (userid) {
        var url = "/myMessageList/queryMymessage";
        var param = {
            "userid": userid
            //"queryinviteUserMap" : JSON.stringify(queryinviteUserMap)
        }
        $.post(url, param, function(data) {
            console.log(data);
            console.log(data.data);
            // for(var i=0;i<data.data.length;i++){
            //     alert("userName:"+data.data[i].userName);
            //     alert("userId:"+data.data[i].userId);
            //     alert("position:"+data.data[i].position);
            //     alert("userType:"+data.data[i].userType);
            //     alert("inviteCommentAuth:"+data.data[i].inviteCommentAuth);
            //     alert("id:"+data.data[i].id);
            //    // console.log(data.data[i].userName);
            //     console.log(data.data[i].toString());
            // }
            var data = JSON.stringify(data)
            if(null==data || "null"==data){
                alert("系统错误，请稍后重试！");
            }else{
                $("#show").val(data);
            }
        }, "json");
    },

    MymessageInfoOnclick:function () {
        var userid = $("#request").val();
        MymessageControllerJS.MymessageInfo(userid);
    },


    MymessageList : function (userid) {
        var url = "/myMessageList/myMessagelist";
        var param = {
            "userid": userid
        }
        $.post(url, param, function(data) {
            console.log(data);
            console.log(data.data);
            var data = JSON.stringify(data)
            if(null==data || "null"==data){
                alert("系统错误，请稍后重试！");
            }else{
                $("#show").val(data);
            }
        }, "json");
    },

    MymessageListOnclick:function () {
        var userid = $("#request").val();
        MymessageControllerJS.MymessageList(userid);
    },

    MymessageListmsgid : function (userid,msgTypeId) {
        var url = "/myMessageList/queryunReadmessbymsgtypeanduserid";
        var param = {
            "userid": userid,
            "msgTypeId": msgTypeId
        }
        $.post(url, param, function(data) {
            console.log(data);
            console.log(data.data);
            var data = JSON.stringify(data)
            if(null==data || "null"==data){
                alert("系统错误，请稍后重试！");
            }else{
                $("#show").val(data);
            }
        }, "json");
    },

    MymessageListmsgidOnclick:function () {
        var userid = $("#request").val();
        var msgTypeId=10;
        MymessageControllerJS.MymessageListmsgid(userid,msgTypeId);
    },


}

