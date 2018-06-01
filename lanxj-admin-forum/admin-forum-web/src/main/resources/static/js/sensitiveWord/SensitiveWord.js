
var sensitiveWordJS={

    /**
     * 新增敏感词
     */
    addSensitiveWord : function (sensitiveWord) {
        var url = "/sensitiveWord/add";
        var param = {
            "sensitiveWord": sensitiveWord
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

    addSensitiveWordOnclick : function () {
        var sensitiveWord = $("#request").val();
        forumPostInfoJS.addSensitiveWord(sensitiveWord);
    },

    /**
     * 修改敏感词
     */
    modifySensitiveWord : function (sensitiveWord) {
        var url = "/sensitiveWord/modify";
        var param = {
            "sensitiveWord": sensitiveWord
        };
        $.post(url, param, function(data) {
            if(null!=data && "null"==data && data.code == 0){
                $("#show").val(data.msg);
            }else{
                alert(data.msg);
            }
        }, "json");
    },

    modifySensitiveWordOnclick : function () {
        var sensitiveWord = $("#request").val();
        forumPostInfoJS.modifySensitiveWord(sensitiveWord);
    },

    /**
     * 删除敏感词
     */
    removeSensitiveWord : function (sensitiveWord) {
        var url = "/sensitiveWord/remove";
        var param = {
            "sensitiveWord": sensitiveWord
        };
        $.post(url, param, function(data) {
            if(null!=data && "null"==data && data.code == 0){
                $("#show").val(data.data);
            }else{
                $("#show").val(JSON.stringify(data.data));
            }
        }, "json");
    },

    removeSensitiveWordOnclick : function () {
        var sensitiveWord = $("#request").val();
        forumPostInfoJS.removeSensitiveWord(sensitiveWord);
    },


    /**
     * 模糊查询敏感词
     */
    querySensitiveWord : function (sensitiveWord) {
        var url = "/sensitiveWord/query";
        var param = {
            "sensitiveWord": sensitiveWord
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

    querySensitiveWordOnclick : function () {
        var sensitiveWord = $("#request").val();
        forumPostInfoJS.querySensitiveWord(sensitiveWord);
    }




}

