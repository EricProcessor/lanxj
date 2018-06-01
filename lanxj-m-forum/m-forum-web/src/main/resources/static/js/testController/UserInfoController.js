
var UserInfoControllerJS={

    /**
     * 用户查询by userid
     */
    selectUserInfoByuserId : function (userid) {
        var url = "/userInfo/userinfobyuserId";
        var param = {
            "userid": userid
        };
        $.post(url, param, function(data) {
            // alert("datastatus:"+data.status);
            // alert("datamsg:"+data.msg);
            // alert("datadata:"+data.data);
            // alert("datalength:"+data.length);
            // alert("datadatalength:"+data.data.length);
            // alert("userName:"+data.data.userName);
            // alert("userId:"+data.data.userId);
            // alert("position:"+data.data.position);
            // alert("userType:"+data.data.userType);
            // alert("inviteCommentAuth:"+data.data.inviteCommentAuth);
            // alert("id:"+data.data.id);

            var data = JSON.stringify(data)
            if(null==data || "null"==data){
                alert("系统错误，请稍后重试！");
            }else{
                $("#show").val(data);
            }
        }, "json");
    },

    selectUserInfoByuserIdOnclick : function () {
        var userid = $("#request").val();
        UserInfoControllerJS.selectUserInfoByuserId(userid);
    },

    /**
     * 用户查询by 岗位position
     */
    selectUserInfoByposition : function (position) {
        var url = "/userInfo/userinfobyPosition";
        var param = {
            "position": position
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
            //     // console.log(data.data[i].userName);
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
    selectUserInfoBypositionOnclick : function () {
        var position = $("#request").val();
        UserInfoControllerJS.selectUserInfoByposition(position);
    },


    /**
     * 用户查询by userName
     */
    selectUserInfoByuserName : function (userName) {
        var url = "/userInfo/userinfobyuserName";
        var param = {
            "userName": userName
        };
        $.post(url, param, function(data) {
            var data = JSON.stringify(data)
            alert(data);
            if(null==data || "null"==data){
                alert("系统错误，请稍后重试！");
            }else{
                $("#show").val(data);
            }
        }, "json");
    },

    selectUserInfoByuserNameOnclick : function () {
        var userName = $("#request").val();
        UserInfoControllerJS.selectUserInfoByuserName(userName);
    },

    /**
     * 用户查询by 条件查询
     */
    selectUserInfoBycondition : function (userId,userName,userType,position,inviteCommentAuth) {
        var url = "/userInfo/userinfobycondition";
        var param = {
            "userId": userId,
            "userName" : userName,
            "userType" : userType,
            "position" : position,
            "inviteCommentAuth" : inviteCommentAuth
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

    selectUserInfoByconditionOnclick : function () {
        var userId = $("#userId").val();
        var userName = $("#userName").val();
        var userType = $("#userType").val();
        var position = $("#position").val();
        var inviteCommentAuth = $("#inviteCommentAuth").val();
        UserInfoControllerJS.selectUserInfoBycondition(userId,userName,userType,position,inviteCommentAuth);
    }





}

