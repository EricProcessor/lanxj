
var postLikesComments = {

    /**
     * 新增点赞
     */
    insertPostLike : function(newPostLike){
        var url = "/postLC/insertPostLike";
        var param = {
            "newPostLike": newPostLike
        };
        $.post(url, param, function(data) {
            var data = JSON.stringify(data)
            if(null==data && "null"==data && data.code ==0){
                alert(data.msg);
            }else{
                $("#show").val(data.msg);
            }
        }, "json");
    },
    insertPostLikeOnclick : function() {
        var newPostLike = $("#request").val();
        postLikesComments.insertPostLike(newPostLike);
    },

    /**
     * 取消点赞
     */
    cancelPostToNoLike : function (cancelPostLike) {
        var url = "/postLC/cancelPostToNoLike";
        var param = {
            "cancelPostLike": cancelPostLike
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
    cancelPostToNoLikeOnclick : function() {
        var cancelPostLike = $("#request").val();
        postLikesComments.cancelPostToNoLike(cancelPostLike);
    },

    /**
     * 增加评论
     */
    insertPostComment : function (newPostComment) {
        var url = "/postLC/insertPostComment";
        // var delPostComment = {
        //     "postId" : postId,
        //     "commentUserId" : commentUserId,
        //     "commentContent" : commentContent
        // }
        var param = {
            "newPostComment": newPostComment
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
    insertPostCommentOnClick : function() {
        var newPostComment = $("#request").val();
        postLikesComments.insertPostComment(newPostComment);
    },

    /**
     * 取消评论
     */
    deletePostComment : function (delPostComment) {
        var url = "/postLC/deletePostComment";
        // var delPostComment = {
        //     "postId" : postId,
        //     "commentUserId" : commentUserId
        // }
        var param = {
            "delPostComment": delPostComment
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
    deletePostCommentOnclick : function() {
        var delPostComment = $("#request").val();
        postLikesComments.deletePostComment(delPostComment);
    }


}