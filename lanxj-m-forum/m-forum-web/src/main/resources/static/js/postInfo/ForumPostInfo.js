
var forumPostInfoJS={

    /**
     * 查询帖子详情
     */
    queryPostInfoByPostId : function (postId) {
        var url = "/postInfo/queryPostInfoByPostId";
        var param = {
            "postId": postId
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

    queryPostInfoByPostIdOnclick : function () {
        var postId = $("#request").val();
        forumPostInfoJS.queryPostInfoByPostId(postId);
    },

    /**
     * 查询置顶帖子
     */
    queryTopPostsByTopicId : function (topicId) {
        var pageNum = 5;
        var pageSize = 10;
        var queryTopPostsByTopicIdMap = {
            "userId":topicId,
            "pageSize":pageSize,
            "pageNum":pageNum
        }
        var param = {
            "queryTopPostsByTopicIdMap" : JSON.stringify(queryTopPostsByTopicIdMap)
        }
        var url = "/postInfo/queryTopPostsByTopicId";
        $.post(url, param, function(data) {
            var data = JSON.stringify(data)
            if(null==data || "null"==data){
                alert("系统错误，请稍后重试！");
            }else{
                $("#show").val(data);
            }
        }, "json");
    },

    queryTopPostsByTopicIdOnClick : function () {
        var topicId = $("#request").val();
        forumPostInfoJS.queryTopPostsByTopicId(topicId);
    },


    /**
     * 查询非置顶帖子
     */
    queryNoTopPostsByTopicId : function (topicId) {
        var pageNum = 5;
        var pageSize = 10;
        var queryNoTopPostsByTopicIdMap = {
            "userId":topicId,
            "pageSize":pageSize,
            "pageNum":pageNum
        }
        var param = {
            "queryNoTopPostsByTopicIdMap" : JSON.stringify(queryNoTopPostsByTopicIdMap)
        }
        var url = "/postInfo/queryNoTopPostsByTopicId";
        $.post(url, param, function(data) {
            var data = JSON.stringify(data)
            if(null==data || "null"==data){
                alert("系统错误，请稍后重试！");
            }else{
                $("#show").val(data);
            }
        }, "json");
    },
    queryNoTopPostsByTopicIdOnClick : function () {
        var topicId = $("#request").val();
        forumPostInfoJS.queryNoTopPostsByTopicId(topicId);
    },

    /**
     * 新增帖子
     */
    addPostInfo : function (newPostInfo) {

        var url = "/postInfo/addPostInfo";
        var param = {
            "newPostInfo": newPostInfo
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
    addPostInfoOnClick : function () {
        var newPostInfo = $("#request").val();
        forumPostInfoJS.addPostInfo(newPostInfo);
    },


    /**
     * 查询员工动态列表
     */
    queryUserDynamicPostList : function (userId) {

        var pageNum = 5;
        var pageSize = 10;
        var queryUserDynamicPostMap = {
           "userId":userId,
           "pageSize":pageSize,
           "pageNum":pageNum
        }
         var param = {
             "queryUserDynamicPostMap" : JSON.stringify(queryUserDynamicPostMap)
         }

        var url = "/postInfo/queryUserDynamicPostList";
        $.post(url, param, function(data) {
            if(null != data && "null"!=data && data.code==0){
                $("#show").val(JSON.stringify(data));
            }else{
                alert(data.msg);
            }
        }, "json");
    },
    queryUserDynamicPostListOnclick : function () {
        var userId = $("#request").val();
        forumPostInfoJS.queryUserDynamicPostList(userId);
    }


}

