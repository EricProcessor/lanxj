
var InviteCommentsControllerJS={

    selectinviteCommentByuserId : function (userid) {
        var url = "/inviteComments/inviteuserList";
        var param = {
            "userid": userid
        };
        $.post(url, param, function(data) {
            // alert("datastatus:"+data.status);
            // alert("datamsg:"+data.msg);
            // alert("datadata:"+data.data);
            // alert("datadatalength:"+data.data.length);
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

    selectinviteCommentByuserIdOnclick : function () {
        var userid = $("#request").val();
        InviteCommentsControllerJS.selectinviteCommentByuserId(userid);
    },



    selectinviteCommentfenyeByuserId : function (userid) {
        var url = "/inviteComments/inviteuserListbyMap";
        var pageNum = 3;
        var pageSize = 5;
        var queryinviteUserMap = {
            "userid": userid,
            "pageSize":pageSize,
            "pageNum":pageNum
        };
        var param = {
            "queryinviteUserMap" : JSON.stringify(queryinviteUserMap)
        }
        $.post(url, param, function(data) {
            // alert("datastatus:"+data.status);
            // alert("datamsg:"+data.msg);
            // alert("datadata:"+data.data);
            // alert("datadatalength:"+data.data.length);
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

    selectinviteCommentfenyeByuserIdOnclick : function () {
        var userid = $("#request").val();
        InviteCommentsControllerJS.selectinviteCommentfenyeByuserId(userid);
    }



}

