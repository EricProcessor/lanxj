var forumTopicInfoJS = {

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
        forumTopicInfoJS.insertUserTopicAuth();
    },

    /**
     * 版块 发帖审核开关
     */
    topicCheckPostSwitch : function (topicId) {
        var url = "/topicInfo/topicCheckPostSwitch";
        var param = {
            "topicId": 1,
            "checkYn":0
        };
        $.post(url, param, function(data) {
            if(null!=data && "null"==data && data.code == 0){
                $("#show").val(data.data);
            }else{
                $("#show").val(JSON.stringify(data.data));
            }
        }, "json");
    },

    topicCheckPostSwitchOnclick : function () {
        var topicId = $("#request").val();
        forumTopicInfoJS.topicCheckPostSwitch(topicId);
    },

    /**
     * 版块 匿名发帖开关
     */
        topicAnonymitySwitch : function (topicId) {
        var url = "/topicInfo/topicAnonymitySwitch";
        var param = {
            "topicId": topicId,
            "anonymityYn":0
        };
        $.post(url, param, function(data) {
            if(null!=data && "null"==data && data.code == 0){
                $("#show").val(data.data);
            }else{
                $("#show").val(JSON.stringify(data.data));
            }
        }, "json");
    },

    topicAnonymitySwitchOnclick : function () {
        var topicId = $("#request").val();
        forumTopicInfoJS.topicAnonymitySwitch(topicId);
    },


    /**
     * 版块开关
     */
    topicStatusSwitch : function (topicId) {
        var url = "/topicInfo/topicStatusSwitch";
        var param = {
            "topicId": topicId,
            "topicStatus":0
        };
        $.post(url, param, function(data) {
            if(null!=data && "null"==data && data.code == 0){
                $("#show").val(data.data);
            }else{
                $("#show").val(JSON.stringify(data.data));
            }
        }, "json");
    },

    topicStatusSwitchOnclick : function () {
        var topicId = $("#request").val();
        forumTopicInfoJS.topicStatusSwitch(topicId);
    },

    /**
     * 版块信息修改
     */
    updateForumTopicInfo : function (topicId) {
        var url = "/topicInfo/updateForumTopicInfo";
        var param = {
            "topicId": topicId,
            "topicName":"管理类"
        };
        $.post(url, param, function(data) {
            if(null!=data && "null"==data && data.code == 0){
                $("#show").val(data.data);
            }else{
                $("#show").val(JSON.stringify(data.data));
            }
        }, "json");
    },

    updateForumTopicInfoOnclick : function () {
        var topicId = $("#request").val();
        forumTopicInfoJS.updateForumTopicInfo(topicId);
    },

    /**
     * 新增版块
     */
    addTopicInfo : function (topicId) {
        var topicName = "设计类";
        var iconUrl = "/static/image/system_topic"
        var topic_status = 1;
        var anonymity_yn =1;
        var check_yn = 1;
        var topic_order = 2;
        var url = "/topicInfo/addTopicInfo";
        var param = {
            "topicName":topicName,
            "iconUrl":iconUrl,
            "topicStatus":topic_status,
            "anonymityYn":anonymity_yn,
            "checkYn":check_yn,
            "topicOrder":topic_order
        };

        console.log(param);
        $.post(url, param, function(data) {
            if(null!=data && "null"==data && data.code == 0){
                $("#show").val(data.data);
            }else{
                $("#show").val(JSON.stringify(data.data));
            }
        }, "json");
    },

    addTopicInfoOnclick : function() {
        var topicId = $("#request").val();
        forumTopicInfoJS.addTopicInfo(topicId);
    }

}