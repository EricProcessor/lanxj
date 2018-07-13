var siteFlowStatisticJS = {

    /**
     * 查询当天的PV 按小时展示
     */
    showPVperHourInDay : function (sensitiveWord) {
        var url = "/pv/day";
        var param = {
            "sensitiveWord": sensitiveWord
        };
        $.post(url, param, function(data) {
            if(null!=data && "null"!=data && data.code == 0){
                $("#show").text(data.data);
            }else{
                alert(data.msg);
            }
        }, "json");
    },

    /**
     * 查询月PV统计 按日展示
     */
    showPVperDayInMonth : function (sensitiveWord) {
        var url = "/pv/month";
        var param = {
            "sensitiveWord": sensitiveWord
        };
        $.post(url, param, function(data) {
            if(null!=data && "null"!=data && data.code == 0){
                $("#show").text(data.data);
            }else{
                alert(data.msg);
            }
        }, "json");
    }
}