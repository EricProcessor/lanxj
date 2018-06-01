
var forumPostInfoJS={

    /**
     * 帖子设为置顶
     */
    setPostToTop : function (postId) {
        var url = "/postInfo/setPostToTop";
        var param = {
            "postId": postId
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

    setPostToTopOnclick : function () {
        var postId = $("#request").val();
        forumPostInfoJS.setPostToTop(postId);
    },

    /**
     * 帖子取消置顶
     */
    cancelPostTop : function (postId) {
        var url = "/postInfo/cancelPostTop";
        var param = {
            "postId": postId
        };
        $.post(url, param, function(data) {
            if(null!=data && "null"==data && data.code == 0){
                $("#show").val(data.msg);
            }else{
                alert(data.msg);
            }
        }, "json");
    },

    cancelPostTopOnclick : function () {
        var postId = $("#request").val();
        forumPostInfoJS.cancelPostTop(postId);
    },

    /**
     * 查询所有版块
     */
    queryAllForumTopicList : function (postId) {
        var url = "/topicInfo/queryAllForumTopicList";
        var param = {
            "postId": postId
        };
        $.post(url, param, function(data) {
            console.log(data.data)
            if(null!=data && "null"==data && data.code == 0){
                $("#show").val(data.data);
            }else{
                $("#show").val(JSON.stringify(data.data));
            }
        }, "json");
    },

    queryAllForumTopicListOnclick : function () {
        var postId = $("#request").val();
        forumPostInfoJS.queryAllForumTopicList(postId);
    },

    /**
     * 版主 版块权限维护
     */
    insertUserTopicAuth : function () {

        var addUserAuthTopicList = new Array();
        var addUserAuthTopic={};
        addUserAuthTopic.userId='333';
        addUserAuthTopic.topicId=66666;
        addUserAuthTopicList.push(addUserAuthTopic);
        var addUserAuthTopic={};
        addUserAuthTopic.userId='333';
        addUserAuthTopic.topicId=777;
        addUserAuthTopicList.push(addUserAuthTopic);

        var deleUserAuthTopicList = new Array();
        var deleUserAuthTopic={};
        deleUserAuthTopic.userId='333';
        deleUserAuthTopic.topicId=555;
        deleUserAuthTopicList.push(deleUserAuthTopic);

        var url = "/user/insertUserTopicAuth";
        var param = {
            "addUserAuthTopicList": JSON.stringify(addUserAuthTopicList),
            "deleUserAuthTopicList": JSON.stringify(deleUserAuthTopicList)
        };
        alert(JSON.stringify(param));
        $.post(url, param, function(data) {
            console.log(data.data)
            if(null!=data && "null"==data && data.code == 0){
                $("#show").val(data.data);
            }else{
                $("#show").val(JSON.stringify(data.data));
            }
        }, "json");
    },

    insertUserTopicAuthOnclick : function () {
        forumPostInfoJS.insertUserTopicAuth();
    }

}

