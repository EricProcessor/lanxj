var TEST_HOST = "http://192.168.191.25:8080",
    PORT_HOST = "",    isTest = true,
    host = isTest ? TEST_HOST : PORT_HOST;
var forumPostInfoJS = {
    /**
     * 查询帖子详情
     */
    queryPostInfoByPostId: function (postId) {
        var url = host + "/postInfo/queryPostInfoByPostId";
        var param = {
            "postId": postId
        };
        $.post(url, param, function (data) {
            var data = JSON.stringify(data)
            if (null == data || "null" == data) {
                alert("系统错误，请稍后重试！");
            } else {
                $("#show").val(data);
            }
        }, "json");
    },

    queryPostInfoByPostIdOnclick: function () {
        var postId = $("#request").val();
        forumPostInfoJS.queryPostInfoByPostId(postId);
    },

    /**
     * 查询置顶帖子
     */
    queryTopPostsByTopicId: function (topicId) {
        // debugger;
        var pageNum = 5;
        var pageSize = 10;

        var param = {
            "topicId": topicId,
            "pageSize": pageSize,
            "pageNum": pageNum
        }
        var url = host + "/postInfo/queryTopPostsByTopicId";
        $.post(url, param, function (data) {
            var data = JSON.stringify(data)
            console.log(data.length);
        });
    },

    queryTopPostsByTopicIdOnClick: function () {
        var topicId = $("#request").val();
        forumPostInfoJS.queryTopPostsByTopicId(topicId);
    },


    /**
     * 查询非置顶帖子
     */
    queryNoTopPostsByTopicId: function (topicId) {
        var pageNum = 5;
        var pageSize = 10;
        var param = {
            "topicId": topicId,
            "pageSize": pageSize,
            "pageNum": pageNum
        }
        var url = host +  "/postInfo/queryNoTopPostsByTopicId";
        $.post(url, param, function (data) {
            var data = JSON.stringify(data)
            if (null == data || "null" == data) {
                alert("系统错误，请稍后重试！");
            } else {
                $("#show").val(data);
            }
        }, "json");
    },
    queryNoTopPostsByTopicIdOnClick: function () {
        var topicId = $("#request").val();
        forumPostInfoJS.queryNoTopPostsByTopicId(topicId);
    },

    /**
     * 新增帖子
     */
    addPostInfo: function (newPostInfo) {

        var url = host + "/postInfo/addPostInfo";
        var param = {
            "newPostInfo": newPostInfo
        };
        $.post(url, param, function (data) {
            var data = JSON.stringify(data)
            if (null == data || "null" == data) {
                alert("系统错误，请稍后重试！");
            } else {
                $("#show").val(data);
            }
        }, "json");
    },
    addPostInfoOnClick: function () {
        var newPostInfo = $("#request").val();
        forumPostInfoJS.addPostInfo(newPostInfo);
    },


    /**
     * 查询员工动态列表
     */
    queryUserDynamicPostList: function (userId) {

        var pageNum = 5;
        var pageSize = 10;
        var queryUserDynamicPostMap = {
            "userId": userId,
            "pageSize": pageSize,
            "pageNum": pageNum
        }
        var param = {
            "queryUserDynamicPostMap": JSON.stringify(queryUserDynamicPostMap)
        }

        var url = host + "/postInfo/queryUserDynamicPostList";
        $.post(url, param, function (data) {
            if (null != data && "null" != data && data.code == 0) {
                $("#show").val(JSON.stringify(data));
            } else {
                alert(data.msg);
            }
        }, "json");
    },
    queryUserDynamicPostListOnclick: function () {
        var userId = $("#request").val();
        forumPostInfoJS.queryUserDynamicPostList(userId);
    },

    /**
     * 帖子搜索
     */
    queryPostByContent: function (queryKey) {

        var pageNum = 5;
        var pageSize = 10;
        var queryPostContentMap = {
            "queryKey": queryKey,
            "pageSize": pageSize,
            "pageNum": pageNum
        }
        var param = {
            "queryPostContentMap": JSON.stringify(queryPostContentMap)
        }

        var url = host + "/postInfo/queryPostByContent";
        $.post(url, param, function (data) {
            if (null != data && "null" != data && data.code == 0) {
                $("#show").val(JSON.stringify(data.data));
            } else {
                alert(data.msg);
            }
        }, "json");
    },
    queryPostByContentOnclick: function () {
        var queryKey = $("#request").val();
        forumPostInfoJS.queryPostByContent(queryKey);
    },

    /**
     * 查询gcr非置顶帖子
     */
    queryPageNoTopPostsByTopicId : function (topicId) {
        var pageNum = 2;
        var pageSize = 5;
        var queryPageNoTopPostsByTopicIdMap = {
            "topicId":topicId,
            "pageSize":pageSize,
            "pageNum":pageNum
        }
        var param = {
            "queryNoTopPostsByTopicIdMap" : JSON.stringify(queryPageNoTopPostsByTopicIdMap)
        }
        var url = "/postInfo/queryPageNoTopPostsByTopicId";
        $.post(url, param, function(data) {
            var data = JSON.stringify(data)
            if(null==data || "null"==data){
                alert("系统错误，请稍后重试！");
            }else{
                $("#show").val(data);
            }
        }, "json");
    },
    queryPageNoTopPostsByTopicIdOnClick : function () {
        var topicId = $("#request").val();
        forumPostInfoJS.queryPageNoTopPostsByTopicId(topicId);
    }

}