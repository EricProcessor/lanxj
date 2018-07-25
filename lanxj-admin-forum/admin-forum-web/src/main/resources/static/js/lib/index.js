// 配置路由
var app = angular.module('myApp', ['ngRoute', 'ngAnimate']);
app.config(function ($routeProvider, $httpProvider) {
    $routeProvider
        .when('/main', { //论坛
            templateUrl: 'tpl/main.html',
            controller: 'mainCtrl'
        })
        .when('/details', { //论坛 帖子详情
            templateUrl: 'tpl/details.html',
            controller: 'detailsCtrl'
        })
        // 通过postId获的跳转指定的帖子详情页
        .when('/details/:userid/:postId', { //论坛 帖子详情
            templateUrl: 'tpl/details.html',
            controller: 'detailsCtrl'
        })
        .when('/details/:userid/:postId/:type', { //论坛 帖子详情
            templateUrl: 'tpl/details.html',
            controller: 'detailsCtrl'
        })
        .when('/sendPost', { //发帖
            templateUrl: 'tpl/sendPost.html',
            controller: 'sendPostCtrl'
        })
        .when('/percenter', { //个人中心
            templateUrl: 'tpl/percenter.html',
            controller: 'percenterCtrl'
        })
        .when('/myMessage/:userid', { //我的消息
            templateUrl: 'tpl/myMessage.html',
            controller: 'myMessageCtrl'
        })
        .when('/praiseReceived/:userid/:msgTypeId', { //我的消息--收到的赞
            templateUrl: 'tpl/praiseReceived.html',
            controller: 'praiseReceivedCtrl'
        })
        .when('/followPost/:userid/:msgTypeId', { //我的消息--关注帖子的赞
            templateUrl: 'tpl/followPost.html',
            controller: 'followPostCtrl'
        })
        .when('/followPostComment/:userid/:msgTypeId', { //我的消息--关注帖子的评论
            templateUrl: 'tpl/followPostComment.html',
            controller: 'followPostCommentCtrl'
        })
        .when('/inviteComment/:userid/:msgTypeId', { //我的消息--关注帖子的邀请评论
            templateUrl: 'tpl/inviteComment.html',
            controller: 'inviteCommentCtrl'
        })
        .when('/commentsReceived/:userid/:msgTypeId', { //我的消息--收到的评论
            templateUrl: 'tpl/commentsReceived.html',
            controller: 'commentsReceivedCtrl'
        })
        .when('/receiveInvite/:userid/:msgTypeId', { //我的消息--收到的评论
            templateUrl: 'tpl/receiveInvite.html',
            controller: 'receiveInviteCtrl'
        })
        .when('/myConcern/:userid', { //我的关注
            templateUrl: 'tpl/myConcern.html',
            controller: 'myConcernCtrl'
        })
        .when('/myDynamic/:userid', { //我的动态
            templateUrl: 'tpl/myDynamic.html',
            controller: 'myDynamicCtrl'
        })
        .when('/plateAngement/:userid', { //板块设置
            templateUrl: 'tpl/plateAngement.html',
            controller: 'plateAngementCtrl'
        })
        .when('/error404', { //404
            templateUrl: 'tpl/error404.html',
            controller: 'error404Ctrl'
        })
        .when('/invitingComment/:userid/:postId/:type', { //邀请评论
            templateUrl: 'tpl/invitingComment.html',
            controller: 'invitingCommentCtrl'
        })


        .otherwise({
            redirectTo: '/main'
        });
});
//父控制器
app.controller('perentCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $scope.$on('haveMenum', function (ev, v) {
        $scope.haveMenum = v;
    });

}]);
//404控制器
app.controller('error404Ctrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'error404';

}]);
//论坛 main控制器
app.controller('mainCtrl', ['$scope', '$rootScope', '$http', '$routeParams', function ($scope, $rootScope, $http, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'main';
    //用户ID
    var userid = '1001';
    var userName = "张宇"; //用户名
    //1、搜索框 http
    // 搜索框获得焦点
    $scope.curFocus = function (event) {
        var target = $(event.target);
        target.val('');
    }
    //1-1、点击 搜索
    $scope.searchSth = function (event) {
        var target = $(event.target);
        var queryKey = target.siblings().val();
        var searchUrl = host + "/postInfo/queryPostByContent";
        var searchSthParam = {
            "pageSize": 1,
            "pageNum": 10,
            "topicId": 0,
            "queryKey": queryKey
        }
        $http({
            method: 'post',
            url: searchUrl,
            data: searchSthParam,
            dataType: "json",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj) {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            }
        }).success(function (data) {
            $scope.noTopData = (JSON.parse(data.data.noTopList));
            $scope.TopData = (JSON.parse(data.data.TopList));
            console.log($scope.noTopData, $scope.TopData)
            //非置顶帖子
            if ($scope.notTopData != null || $scope.notTopData != '') {
                $scope.notTopData = $scope.noTopData;
            } else {
                location.reload();
            }
            //置顶帖子
            if ($scope.TopData != null || $scope.TopData != '') {
                $scope.newsData = $scope.TopData;
            } else {
                location.reload();
            }
        });
    }
    //2、获得导航数据
    var userid = "1001",
        tabUrl = host + "/topicInfo/indextopicInfo?userId=" + userid;
    $http({
        method: 'post',
        url: tabUrl,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data)
        $scope.tabData = data.data;
    });

    //3、 置顶帖子数据
    var type = '0';
    var topParam = {
        "pageSize": 1,
        "pageNum": 10
    }
    var topUrl = host + "/postInfo/queryTopPostsByTopicId";
    $http({
        method: 'post',
        url: topUrl,
        data: topParam,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        $scope.newsData = data;
    });
    //点击跳转置顶帖子详情事件
    $scope.topGoPostDetail = function (event) {
        var target = $(event.target),
            postId = target.parents(".news").find(".postId").val(),
            type = '0';
        clicked("index.html#/details/" + userid + "/" + postId + "/" + type, false, false);

    }

    //4、 非置顶帖子 数据
    var noTopUrl = host + "/postInfo/queryPageNoTopPostsByTopicId";
    var queryNoTopPostsByTopicIdMap = {
        "topicId": 0,
        "pageNum": 1,
        "pageSize": 10
    }
    var notParam = {
        "queryNoTopPostsByTopicIdMap": JSON.stringify(queryNoTopPostsByTopicIdMap)
    }
    $http({
        method: 'post',
        url: noTopUrl,
        data: notParam,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data)
        $scope.noTopData = data.list;
    });

    //跳转非置顶帖子详情页
    $scope.goPostDetail = function (event) {
        var target = $(event.target);
        var postId = target.parents(".invitationBox").find(".postId").val(),
            type = '0';
        clicked("index.html#/details/" + userid + "/" + postId + "/" + type, false, false);
    }
    //4-1 点击不同模块 根据topicId 加载数据
    $scope.loadSelfInfo = function (event) {
        var target = $(event.target);
        var topId = target.siblings().val();
        $scope.noTopData = [];
        if (topId != '1006') {
            queryNoTopPostsByTopicIdMap = {
                "topicId": topId,
                "pageNum": 1,
                "pageSize": 10
            }
            notParam = {
                "queryNoTopPostsByTopicIdMap": JSON.stringify(queryNoTopPostsByTopicIdMap)
            }
            $http({
                method: 'post',
                url: noTopUrl,
                data: notParam,
                dataType: "json",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    }
                    return str.join("&");
                }
            }).success(function (data) {
                $scope.noTopData = data.list;
            });
        } else {
            $(".hotIcon").removeClass("hide");
            //榜单 问吧达人数据
            $http({
                method: 'post',
                url: host + "/postInfo/getUserLikedRank",
                dataType: "json",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    }
                    return str.join("&");
                }
            }).success(function (data) {
                $scope.userRank = data.data;
            });
            //榜单 一周热帖数据
            $http({
                method: 'post',
                url: host + "/postInfo/queryHotPost",
                dataType: "json",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    }
                    return str.join("&");
                }
            }).success(function (data) {
                $scope.hostPostData = data.data;
            });
        }

    }


    //5、 点赞
    $scope.thumbsUp = function (event) {
        var target = $(event.target);
        $scope.likeStatus = "1";
        var img = target.attr('src'),
            $this = target.parents(".thumbsBox").siblings(".thumbShow"),
            num = target.parent().siblings().text(),
            postId = target.parents(".thumbsBox").siblings(".infoText").find(".postId").val(),
            count = $this.find("b").text(),
            cordBox = $this.find("a").width(),
            thumbsUpUrl = host + "/postLC/insertPostLike";
        if (img == '../images/btn_laud_n@2x.png') {
            var par = {
                "postId": postId,
                "likeUserId": userid,
                "likeUserName": userName,
                "likeStatus": $scope.likeStatus
            }
            var zanParam = {
                "newPostLike": JSON.stringify(par)
            }
            $http({
                method: 'post',
                url: thumbsUpUrl,
                data: zanParam,
                dataType: "json",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    }
                    return str.join("&");
                }
            }).success(function (data) {
                console.log(data)
                $scope.likeStatus = data.data
                if (data.data == '0') {
                    num++;
                    count++;
                    target.attr("src", "../images/btn_laud_p@2x.png").parent().siblings().text(num);
                    $this.find(".records").show();
                    $this.find(".namelist").html(userName);
                    $this.find(".records").find("b").html(count);

                }

            });
        } else {
            thumbsDownUrl = host + "/postLC/cancelPostToNoLike";
            var cancleZanParam = {
                "postId": postId,
                "likeUserId": userid,
            }
            var cancelPostLike = {
                "cancelPostLike": JSON.stringify(cancleZanParam)
            }
            $http({
                method: 'post',
                url: thumbsDownUrl,
                data: cancelPostLike,
                dataType: "json",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    }
                    return str.join("&");
                }
            }).success(function (data) {
                console.log(data)
                if (data.data == '1') {
                    num--;
                    count--;
                    target.attr("src", "../images/btn_laud_n@2x.png").parent().siblings().text(num);
                    target.parents(".thumbsBox").siblings(".thumbShow").find(".records").removeClass("hide");
                    $this.find(".records").hide();
                    $this.find(".namelist").html('');
                    $this.find(".records").find("b").html(count);
                }

            });
        }


    }

    //6、点击留言 弹出留言框
    $scope.leaveWord = function (event) {
        var target = $(event.target);
        $that = target.parents(".thumbsBox").siblings(".thumbShow");
        $that.find(".inputBox").animate({
            bottom: "-2px"
        }, 200);
        $that.parents(".invitationBox").siblings(".shadowBox").fadeIn();
    }
    //6-1 取消留言 隐藏留言框
    $scope.cancleSendBtn = function (event) {
        var target = $(event.target);
        target.parents(".inputBox").animate({
            bottom: "-20rem"
        }, 200);
        target.parents(".invitationBox").siblings(".shadowBox").fadeOut();
    }
    //6-2 发表留言 隐藏留言框
    $scope.agreeSendBtn = function (event) {
        var target = $(event.target);
        var leaveMsgUrl = host + "/postLC/insertPostComment",
            count = target.siblings().html(),
            commentContent = target.parent().siblings("#inputVal").val(),
            postId = target.parents(".thumbShow").siblings(".infoText").find(".postId").val(),
            $msg = target.parents(".inputBox").siblings(".moreThumbs"),
            commentUserId = "0";
        console.log($msg)
        var msgParam = {
            "userId": userid,
            "userName": userName,
            "postId": postId,
            "commentUserId": commentUserId,
            "commentContent": commentContent
        }
        var parVal = {
            "newPostComment": JSON.stringify(msgParam)
        };
        if (commentContent != '') {
            $http({
                method: 'post',
                url: leaveMsgUrl,
                data: parVal,
                dataType: "json",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    }
                    return str.join("&");
                }
            }).success(function (data) {
                console.log(data)
                if (data.code == '0') {
                    count++;
                    target.siblings().html(count);
                    target.parents(".inputBox").animate({
                        bottom: "-20rem"
                    }, 200);
                    target.parents(".invitationBox").siblings(".shadowBox").fadeOut();
                    target.parents(".inputBox").animate({
                        bottom: "-20rem"
                    }, 200);
                    target.parents(".invitationBox").siblings(".shadowBox").fadeOut();
                    $msg.removeClass("hide");
                    $msg.find(".thumbName").html(userName).siblings(".thumbInfo").html(commentContent);
                }
            });
        } else {
            alert("输入内容")
        }


    }
    //7、页面出错，刷新
    $scope.reload = function (event) {
        var target = $(event.target);
        location.reload();
    }
    //循环结束以后，要操作的行为
    $scope.$on('ngRepeatFinished', function (ngRepeatFinishedEvent) {
        //给tab导航的第一个子元素加下划线
        $(".find_nav_list li").each(function () {
            $(".find_nav_list li").eq(0).addClass("find_nav_cur").siblings().removeClass("find_nav_cur");
        });
    });



}]);
//帖子详情
app.controller('detailsCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'details';
    //1、接 路由传参 获得对应数据
    $scope.userid = $routeParams.userid;
    $scope.postId = $routeParams.postId;
    $scope.type = $routeParams.type;
    $scope.inviteAuth = '0';
    if ($scope.type != '0') {
        $scope.inviteAuth = '1';
    }
    var userName = "耐克"
        
    var detailUrl = host + "/postInfo/queryPostInfoByPostIdandtype";
    var param = {
        "userId": $scope.userid,
        "postId": $scope.postId,
        "type": $scope.type
    }

    $http({
        method: 'post',
        url: detailUrl,
        data: param,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data)
        $scope.detailData = data.data;
        $scope.headUrl = $scope.detailData.headUrl; //用户头像
        $scope.inviteCommentAuth = $scope.detailData.inviteCommentAuth; //是否是邀请人 0 不是 1 是
        $scope.userName = $scope.detailData.userName //用户名
        $scope.createTime = $scope.detailData.createTime //创建时间
        $scope.likesCount = $scope.detailData.likesCount //点赞数量
        $scope.commentsCount = $scope.detailData.commentsCount //评论数量
        $scope.postCommentsRelateList = $scope.detailData.postCommentsRelateList //评论内容
        $scope.postContent = $scope.detailData.postContent //帖子内容
        $scope.postId = $scope.detailData.postId //帖子id
        $scope.postLikesRelateList = $scope.detailData.postLikesRelateList //点赞内容列表
        $scope.postPicUrlRelateList = $scope.detailData.postPicUrlRelateList //帖子图片
    });

    //2、点击评论 弹出留言框 
    $scope.detailLeaveMSgBtn = function (event) {
        var target = $(event.target);
        target.parents(".footerBox").siblings(".shadow").fadeIn().siblings(".inputBox").animate({
            bottom: 0
        }, 200);
    }
    //2-1、 点击 弹出框上的取消 隐藏弹出框
    $scope.cancleDelSendBtn = function (event) {
        var target = $(event.target);
        target.parents(".inputBox").animate({
            bottom: "-20rem"
        }, 200).siblings(".shadow").fadeOut();
    }
    //2-2、点击弹出框上的确定 提交数据 隐藏弹出框
    $scope.agreeDelSendBtn = function (event) {
        var target = $(event.target);
        var text = target.parents(".inputBox").find("#inputVal").val(), //评论内容
            commentUserId = "0",
            DelLeaveMsgUrl = host + "/postLC/insertPostComment";
        var sendParam = {
            "userName": userName,
            "userId":  $scope.userid,
            "postId": $scope.postId,
            "commentUserId": commentUserId,
            "commentContent": text,
            "inviteCommentsYn": $scope.inviteAuth
        }
        var newPostComment = {
            "newPostComment": JSON.stringify(sendParam)
        }
        if (text != '') {
            $http({
                method: 'post',
                url: DelLeaveMsgUrl,
                data: newPostComment,
                dataType: "json",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    }
                    return str.join("&");
                }
            }).success(function (data) {
                console.log(data)
                if (data.code == '0') {
                    target.parents(".inputBox").animate({
                        bottom: "-20rem"
                    }, 200).siblings(".shadow"), fadeOut();
                    location.reload();
                }

            });
        } else {
            alert("请输入内容..")
        }
    }
    //3、对该帖子点赞 
    $scope.detailthumbsBtn = function (event) {
        var target = $(event.target);
        var num = target.parents(".footerBox").siblings(".goodCommentBox").find(".commentNum").html(),
            imgUrl = $(".footList:last-child").find(".footImg").children().attr("src"),
            thumbsUpUrl = host + "/postLC/insertPostLike";
        txt = $(".footList:last-child").find(".footImg").siblings().html();
        $scope.likeStatus = '1';
        if (imgUrl == "../images/btn_laud_n@2x.png") {
            var par = {
                "postId": $scope.postId,
                "likeUserId":  $scope.userid,
                "likeUserName": userName,
                "likeStatus": $scope.likeStatus
            }
            var param = {
                "newPostLike": JSON.stringify(par)
            }
            $http({
                method: 'post',
                url: thumbsUpUrl,
                data: param,
                dataType: "json",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    }
                    return str.join("&");
                }
            }).success(function (data) {
                console.log(data)
                $scope.likeStatus = data.data
                if (data.data == '0') {
                    num++;
                    $(".footList:last-child").find(".footImg").children().attr("src", "../images/btn_laud_p@2x.png");
                    target.parents(".footerBox").siblings(".goodCommentBox").find(".commentNum").html(num);
                }

            });

        } else {
            thumbsDownUrl = host + "/postLC/cancelPostToNoLike";
            var param = {
                "postId": $scope.postId,
                "likeUserId":  $scope.userid,
            }
            var cancelPostLike = {
                "cancelPostLike": JSON.stringify(param)
            }
            $http({
                method: 'post',
                url: thumbsDownUrl,
                data: cancelPostLike,
                dataType: "json",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    }
                    return str.join("&");
                }
            }).success(function (data) {
                console.log(data)
                if (data.data == '1') {
                    num--;
                    $(".footList:last-child").find(".footImg").children().attr("src", "../images/btn_laud_n@2x.png");
                    target.parents(".footerBox").siblings(".goodCommentBox").find(".commentNum").html(num);
                }

            });
        }
    }
    //3-1、对该帖子的评论 点赞
    $scope.delMsgThumbsBtn = function (event) {
        var target = $(event.target);
        var count = target.parent().siblings().html(),
            imgUrl = target.attr("src");
        if (imgUrl == "../images/btn_laud_n@2x.png") {
            count++;
            target.attr("src", "../images/btn_laud_p@2x.png");
            target.parent().siblings().html(count);
        } else {
            count--;
            target.attr("src", "../images/btn_laud_n@2x.png");
            target.parent().siblings().html(count);
        }
        var commentId = target.attr("id");
        var sendParam = {
            "userId":  $scope.userid,
            "commentId":'commentId'
        }
        var thumbsUpUrl = host + "/postLC/commentLikeDeal";
        $http({
            method: 'post',
            url: thumbsUpUrl,
            data: sendParam,
            dataType: "json",
            // headers: {
            //     'Content-Type': 'application/x-www-form-urlencoded'
            // },
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj) {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            }
        }).success(function (data) {
            console.log(data)
            console.log(11)
            // $scope.likeStatus = data.data
            // if (data.data == '0') {
            //     num++;
            //     $(".footList:last-child").find(".footImg").children().attr("src", "../images/btn_laud_p@2x.png");
            //     target.parents(".footerBox").siblings(".goodCommentBox").find(".commentNum").html(num);
            // }

        });


    }
    //4、点击 邀请评论  跳转至邀请联系人页面
    $scope.goContactsBtn = function (event) {
        var target = $(event.target);
        clicked("index.html#/invitingComment/" + $scope.userid + "/" + $scope.postId + "/" + $scope.type, false, false);
    }
    //5、关注该条帖子
    $scope.collectBtn = function (event) {
        var target = $(event.target);
        var collectUrl = host + "/attentionpost/attentionpostbystatus",
            imgIcon = target.attr("src"),
            attentionStatus = "1";
        if (imgIcon == "../images/btn_follow_n@2x.png") {
            var param = {
                "userId": $scope.userid,
                "postId": $scope.postId,
                "attentionStatus": attentionStatus
            }
            $http({
                method: 'post',
                url: collectUrl,
                data: param,
                dataType: "json",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    }
                    return str.join("&");
                }
            }).success(function (data) {
                console.log(data)
                if (data.msg == 'ok' || data.msg == 'OK') {
                    target.addClass("hide").siblings().removeClass("hide");
                }

            });
        } else {
            attentionStatus = "0";
            var param = {
                "userId": $scope.userid,
                "postId": $scope.postId,
                "attentionStatus": attentionStatus
            }
            $http({
                method: 'post',
                url: collectUrl,
                data: param,
                dataType: "json",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    }
                    return str.join("&");
                }
            }).success(function (data) {
                console.log(data)
                if (data.msg == 'ok' || data.msg == 'OK') {
                    target.addClass("hide").siblings().removeClass("hide");
                }

            });
        }

    }
}]);
//发帖 
app.controller('sendPostCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'sendPost';
    //是否匿名发帖
    var targetIf = 0; //匿名状态 0、不匿名 1、匿名
    $scope.anonymous = function (event) {
        var target = $(event.target);
        var img = target.attr('src');
        if (img == '../images/btn_anonymous_n@2x.png') {
            target.attr("src", "../images/btn_anonymous_p@2x.png");
            targetIf = 1;
        } else {
            target.attr("src", "../images/btn_anonymous_n@2x.png");
            targetIf = 0;
        }
    }
    //点击切换选择发布版块

    $scope.toggleHeight = function (event) {
        $('.select').toggleClass('selectHeight')
    }
    //3. 选择版块
    $scope.choseModel = function (event) {
        var target = $(event.target);
        target.addClass('selectBlue').siblings().removeClass('selectBlue');
        $scope.selectName = target.text();
        console.log($scope.selectName)
        $scope.topic_id = target.attr('id');
    }
    //4. 上传图片
    $scope.sendImg = function (event) {
        //上传图片
        var param = {
            // "userid": userid
            // "userid": 1001
        };

        var urlimg = $(".up-section .up-img");
        //   console.log(urlimg[0].attr("src"));
        var urlArr = [];
        for (var i = 0; i < urlimg.length; i++) {
            var arr = $(urlimg[i])
                .attr("src")
                .split("/");
            urlArr.push(arr[3]);
        }
        //   console.log(arr);

        var PostContent = $scope.PostContent;
        var topic_id = $scope.topic_id;
        var anonymity_yn = '0';
        // console.log(urlArr);
        // console.log(PostContent);
        // console.log(topic_id);
        // console.log(anonymity_yn);
        //   var str
        var url = host + "/postInfo/uploadImage";
        var collectUrl =  host + "/postInfo/checkSensitiveWord";
        if(PostContent==undefined||PostContent==''){
            alert('请输入发布内容')
            return;
        }else if(topic_id==undefined||topic_id==''){
            alert('请选择发布模块')
            return;
        }
        var imgPage = $('.imgPage').text();


        var param = {
            postContent: PostContent, //帖子内容
        }
        var ifSendImg = 0;
        $http({
            method: 'post',
            url: collectUrl,
            data: param,
            dataType: "json",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj) {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            }
        }).success(function (data) {
            console.log(data)
            console.log(data.code);
            if(data.code==-1){
                console.log('aaaaa')
                $('#ifBack').removeClass('hide');
                return;
            }else{
                //如果上传图片数量是 0 ，直接上传帖子，否则先上传图片，再上传帖子
                if(imgPage==0){
                    addPostInfo(imgPage,PostContent, topic_id, anonymity_yn, '');
                }else{
                    $("#form").ajaxSubmit({
                        type: "post",
                        // xhrFields: { withCredentials: true },
                        url: url,
                        data: {
                            member_price_used: "1111",
                            multipartFiles: urlArr
                        },
                        // headers: {
                        //   "Content-Type": "application/x-www-form-urlencoded"
                        // },
                        success: function (res) {
                            //   var post2 = {
                            //     postPicUrlList: res.data
                            //   };
                            //   console.log(post2);
                            var imgUrl = JSON.parse(res);
                            var picUrl = imgUrl.data;
                            console.log(picUrl);
                            $(".shadow,.shadowInfo").fadeIn();
                            addPostInfo(imgPage,PostContent, topic_id, anonymity_yn, picUrl);
                        },
                        error: function (XmlHttpRequest, textStatus, errorThrown) {
                            alert('图片上传失败')
                        }
                    });
                }
            }
        });
        
    };
    
    $scope.sensitive = function (event) {
        $('#ifBack').addClass('hide')
    }
    //5. 新增帖子
    // $scope.sendImg = function (event) {
    function addPostInfo(imgPage,PostContent, topic_id, anonymity_yn, picUrl) {
        console.log(PostContent);
        console.log(topic_id);
        console.log(anonymity_yn);
        var postInfo = {
            userName: "小啊",
            userId: "1001",
            postContent: PostContent, //帖子内容
            topicId: topic_id, //帖子版块ID
            anonymityYn: anonymity_yn //是否匿名 0否  1是
        };
        //   postInfo = JSON.stringify(postInfo);

        var postParam = {};
        if(imgPage==0){
            postParam = {
                postInfo: JSON.stringify(postInfo)
              
            };
        }else{
            postParam = {
                postInfo: JSON.stringify(postInfo),
                postPicUrlList: JSON.stringify(picUrl)
              
            };
        }
        console.log("all param=" + postParam);
        var url = host + "/postInfo/addPostInfo";
        $http({
            method: "post",
            url: url,
            data: postParam,
            dataType: "json",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj) {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            }
        }).success(function (data) {
            $scope.newsData = data;
            $(".shadow,.shadowInfo").fadeOut();
            if (data.code == '0') {
                alert("发帖成功");
                back();
            }
        });
    }


}]);
//个人中心 ---我的
app.controller('percenterCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'percenter';
    // 数据请求
    var userid = "1001",
        msgUrl = host + "/myMessageList/queryMymessage?userid=" + userid;

    $http({
        method: 'post',
        url: msgUrl,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data)
        $scope.msgData = data.data;
        $scope.name = $scope.msgData.userName;
        $scope.userid = $scope.msgData.userId;
        $scope.headUrl = $scope.msgData.headUrl; //头像
        $scope.position = $scope.msgData.position; //职业
        if ($scope.position == null) {
            $scope.position = "保 密";
        }
        $scope.likeCount = $scope.msgData.postlikecount; //点赞数
        $scope.sendCount = $scope.msgData.sendCommentcount; //评论数
        $scope.postCount = $scope.msgData.postcount; //发帖数
        $scope.msgTipCount = $scope.msgData.myunreadmessagecount; //消息数量提示
    });


    //2、更换头像 请求
    $scope.selectPicHead = function (event) {
        var target = $(event.target);
        target.parents(".percenterInfo").siblings(".pictureBox").show();

    }
    //2-1、取消更换头像
    $scope.closeThisPic = function (event) {
        var target = $(event.target);
        target.parents(".pictureBox").hide();
    }
    //2-2、点击更像 提交请求
    $(".headPicBoxList").on("click", "li", function () {
        var oldSrc = $(this).parents(".pictureBox").siblings(".percenterInfo").find(".infoImg"),
            newSrc = $(this).children().attr("src"),
            headUrl = host + "/userInfo/modifyuserinfobyUserId?userId=" + userid + "&headUrl=" + newSrc;
        $http({
            url: headUrl,
            dataType: "json",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj) {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            }
        }).success(function (data) {
            // console.log(data)
            if (data.msg == 'OK' || data.msg == 'ok') {
                oldSrc.attr("src", newSrc);
                $(".pictureBox").hide();
            }
        });
    });
    //3 跳转页面
    $scope.jumpNextHtml = function (event) {
        var target = $(event.target);
        var h = target.text();
        if (h.indexOf("消息") != -1) {
            clicked("index.html#/myMessage/" + $scope.userid, false, false);
        } else if (h.indexOf("关注") != -1) {
            clicked("index.html#/myConcern/" + $scope.userid, false, false);
        } else if (h.indexOf("动态") != -1) {
            clicked("index.html#/myDynamic/" + $scope.userid, false, false);
        } else if (h.indexOf("设置") != -1) {
            clicked("index.html#/plateAngement/" + $scope.userid, false, false);
        }
    }
}]);

//我的消息 
app.controller('myMessageCtrl', ['$scope', '$rootScope', '$http', '$routeParams', function ($scope, $rootScope, $http, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'myMessage';
    // 数据请求
    var userid = "1001",
        msgListUrl = host + "/myMessageList/myMessagelist?userid=" + userid;
    $http({
        method: 'post',
        url: msgListUrl,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data)
        $scope.msgList = data.data;
    });
    //根据不同的msgTypeId跳转不同页
    $scope.toNextHtml = function (event) {
        var target = $(event.target);
        var msgTypeId = target.find(".msgTypeId").val();
        if (msgTypeId == '10') { //跳转收到的赞
            clicked("index.html#/praiseReceived/" + userid + "/" + msgTypeId, false, false);
        } else if (msgTypeId == '20') { //跳转收到的评论
            clicked("index.html#/commentsReceived/" + userid + "/" + msgTypeId, false, false);
        } else if (msgTypeId == '30') { //跳转关注帖子的赞
            clicked("index.html#/followPost/" + userid + "/" + msgTypeId, false, false);
        } else if (msgTypeId == '40') { //跳转关注帖子的评论
            clicked("index.html#/followPostComment/" + userid + "/" + msgTypeId, false, false);
        } else if (msgTypeId == '50') { //跳转关注帖子邀请评论
            clicked("index.html#/inviteComment/" + userid + "/" + msgTypeId, false, false);
        } else if (msgTypeId == '60') { //跳转收到的邀请评论
            clicked("index.html#/receiveInvite/" + userid + "/" + msgTypeId, false, false);
        }
    }

}]);
//我的消息 --收到的赞
app.controller('praiseReceivedCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'praiseReceived';
    $scope.userid = $routeParams.userid;
    $scope.msgId = $routeParams.msgTypeId;
    // 收到的赞
    var praiseUrl = host + "/myMessageList/queryunReadmessbymsgtypeanduserid?userid=" + $scope.userid + '&msgTypeId=' + $scope.msgId;
    $http({
        method: 'post',
        url: praiseUrl,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data)
        $scope.msgPraiseList = data.data;
    });
    //跳转帖子详情页
    $scope.toPostDetail = function (event) {
        var target = $(event.target);
        var postId = target.parents("li").find(".postId").val(),
            type = "0";
        if ($scope.msgId != '60') {
            clicked("index.html#/details/" + $scope.userid + "/" + postId + "/" + type, false, false);
        } else {
            type = "1";
            clicked("index.html#/details/" + $scope.userid + "/" + postId + "/" + type, false, false);
        }

    }



}]);
//我的消息 --收到的评论
app.controller('commentsReceivedCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'commentsReceived';
    $scope.userid = $routeParams.userid;
    $scope.msgId = $routeParams.msgTypeId;
    // 收到的评论
    var praiseUrl = host + "/myMessageList/queryunReadmessbymsgtypeanduserid?userid=" + $scope.userid + '&msgTypeId=' + $scope.msgId;
    $http({
        method: 'post',
        url: praiseUrl,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data)
        $scope.msgPraiseList = data.data;
    });
    //跳转帖子详情页
    $scope.toPostDetail = function (event) {
        var target = $(event.target);
        var postId = target.parents("li").find(".postId").val(),
            type = "0";
        if ($scope.msgId != '60') {
            clicked("index.html#/details/" + $scope.userid + "/" + postId + "/" + type, false, false);
        } else {
            type = "1";
            clicked("index.html#/details/" + $scope.userid + "/" + postId + "/" + type, false, false);
        }

    }

}]);
//我的消息 --关注帖子的赞
app.controller('followPostCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'followPost';
   
    $scope.userid = $routeParams.userid;
    $scope.msgId = $routeParams.msgTypeId;
    // 收到的评论
    var praiseUrl = host + "/myMessageList/queryunReadmessbymsgtypeanduserid?userid=" + $scope.userid + '&msgTypeId=' + $scope.msgId;
    $http({
        method: 'post',
        url: praiseUrl,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data)
        $scope.msgPraiseList = data.data;
    });
    //跳转帖子详情页
    $scope.toPostDetail = function (event) {
        var target = $(event.target);
        var postId = target.parents("li").find(".postId").val(),
            type = "0";
        if ($scope.msgId != '60') {
            clicked("index.html#/details/" + $scope.userid + "/" + postId + "/" + type, false, false);
        } else {
            type = "1";
            clicked("index.html#/details/" + $scope.userid + "/" + postId + "/" + type, false, false);
        }

    }

}]);

//我的消息 --关注帖子的评论
app.controller('followPostCommentCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'followPostComment';

    $scope.userid = $routeParams.userid;
    $scope.msgId = $routeParams.msgTypeId;
    // 收到的评论
    var praiseUrl = host + "/myMessageList/queryunReadmessbymsgtypeanduserid?userid=" + $scope.userid + '&msgTypeId=' + $scope.msgId;
    $http({
        method: 'post',
        url: praiseUrl,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data)

        $scope.msgPraiseList = data.data;
    });
    //跳转帖子详情页
    $scope.toPostDetail = function (event) {
        var target = $(event.target);
        var postId = target.parents("li").find(".postId").val(),
            type = "0";
        if ($scope.msgId != '60') {
            clicked("index.html#/details/" + $scope.userid + "/" + postId + "/" + type, false, false);
        } else {
            type = "1";
            clicked("index.html#/details/" + $scope.userid + "/" + postId + "/" + type, false, false);
        }

    }

}]);
//我的消息 --关注帖子的邀请评论
app.controller('inviteCommentCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'inviteComment';
    $scope.userid = $routeParams.userid;
    $scope.msgId = $routeParams.msgTypeId;
    // 收到的评论
    var praiseUrl = host + "/myMessageList/queryunReadmessbymsgtypeanduserid?userid=" + $scope.userid + '&msgTypeId=' + $scope.msgId;
    $http({
        method: 'post',
        url: praiseUrl,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data)
        $scope.msgPraiseList = data.data;
    });
    //跳转帖子详情页
    $scope.toPostDetail = function (event) {
        var target = $(event.target);
        var postId = target.parents("li").find(".postId").val(),
            type = "0";
        if ($scope.msgId != '60') {
            clicked("index.html#/details/" + $scope.userid + "/" + postId + "/" + type, false, false);
        } else {
            type = "1";
            clicked("index.html#/details/" + $scope.userid + "/" + postId + "/" + type, false, false);
        }

    }

}]);
//我的消息 --收到的邀请评论
app.controller('receiveInviteCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'receiveInvite';
    $scope.userid = $routeParams.userid;
    $scope.msgId = $routeParams.msgTypeId;
    // 收到的评论
    var praiseUrl = host + "/myMessageList/queryunReadmessbymsgtypeanduserid?userid=" + $scope.userid + '&msgTypeId=' + $scope.msgId;
    $http({
        method: 'post',
        url: praiseUrl,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data)
        $scope.msgPraiseList = data.data;
    });
    //跳转帖子详情页
    $scope.lookDetail = function (event) {
        var target = $(event.target);
        var postId = target.parents("li").find(".postId").val(),
            type = "0";
        if ($scope.msgId != '60') {
            clicked("index.html#/details/" + $scope.userid + "/" + postId + "/" + type, false, false);
        } else {
            type = "1";
            clicked("index.html#/details/" + $scope.userid + "/" + postId + "/" + type, false, false);
        }

    }

}]);
//我的关注 
app.controller('myConcernCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'myConcern';
    var myConUrl = host + "/attentionpost/attentionpostbystatus";
    

     //1、我的关注列表数据
     var topicId = "0";
     var noTopUrl = host + "/postInfo/getUserAttentionPosts";
     var notParam = {
        //  "topicId": topicId,
         "userId":'1001',
         "pageNum": 1,
         "pageSize" : 10
     }
    //  var notParam = {
    //      "queryNoTopPostsByTopicIdMap": JSON.stringify(queryNoTopPostsByTopicIdMap)
    //  }
     $http({
         method: 'post',
         url: noTopUrl,
         data: notParam,
         dataType: "json",
         headers: {
             'Content-Type': 'application/x-www-form-urlencoded'
         },
         transformRequest: function (obj) {
             var str = [];
             for (var p in obj) {
                 str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
             }
             return str.join("&");
         }
     }).success(function (data) {
         console.log(data)
         $scope.noTopData = data.list;
     });
 
     //跳转非置顶帖子详情页
     $scope.goPostDetail = function (event) {
         var target = $(event.target);
         var postId = target.parents(".invitationBox").find(".postId").val(),
             type = '0',
             userid = '1001'
         clicked("index.html#/details/" + userid + "/" + postId + "/" + type, false, false);
     }
 


    //取消关注
    $scope.collect = function (event) {
        var target = $(event.target);
        target.parents(".collectBox").siblings(".shadow,.cancelCollect").fadeIn();
    }

    // 点赞
    $scope.thumbsUp = function (event) {
        var target = $(event.target);
        var img = target.attr('src'),
            num = target.parent().siblings().text();
        if (img == '../../static/images/btn_laud_n@2x.png') {
            num++;
            target.attr("src", "../../static/images/btn_laud_p@2x.png").parent().siblings().text(num);
        } else {
            num--;
            target.attr("src", "../../static/images/btn_laud_n@2x.png").parent().siblings().text(num);
        }
    }
    // 查看评论
    $scope.linkHref = function (event) {
        var target = $(event.target);
        alert("我也不知道去哪");
    }
    // 取关 确认 取消
    $scope.sureBtn = function (event) {
        var target = $(event.target);
        alert("确定");
        target.parents(".cancelCollect").fadeOut().siblings(".shadow").fadeOut();
    }
    $scope.cancelBtn = function (event) {
        var target = $(event.target);
        target.parents(".cancelCollect").fadeOut().siblings(".shadow").fadeOut();
    }
}]);
//我的动态
app.controller('myDynamicCtrl', ['$scope', '$rootScope', '$http', '$routeParams', function ($scope, $rootScope, $http, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'myDynamic';
    //1、获得当前数据
    var userid = $routeParams.userid;
    var queryUserDynamicPostReq = {
        "userId": userid,
        "pageSize": 10,
        "pageNum": 1
    }
    var param = {
        "queryUserDynamicPostReq": JSON.stringify(queryUserDynamicPostReq)
    }
    var dMicUrl = host + "/postInfo/queryUserDynamicPostList";
    $http({
        method: 'post',
        url: dMicUrl,
        data: param,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data)
        $scope.DynamicData = data.data.list;
        console.log($scope.DynamicData)
    });
    //2 查看更多评论 
    // $scope.lookMoreInfo = function (event) {

    // }
}]);
//板块设置
app.controller('plateAngementCtrl', ['$scope', '$rootScope', '$http', '$routeParams', function ($scope, $rootScope, $http, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.pageClass = 'plateAngement';
    //1、获取模块数据
    var userid = "1001";
    var plateUrl = host + "/attentiontopic/myTopicList?userId=" + userid;
    $http({
        method: 'post',
        url: plateUrl,
        dataType: "json",
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        console.log(data.data)
        $scope.plateData = data.data.attenttopiclist;
        $scope.palteCancle = data.data.unattenttopiclist;
    });


    //2、取消关注事件
    $scope.cancelFollowed = function (event) {
        var target = $(event.target);
        target.siblings(".shadow,.cancelCollect").fadeIn();
    }
    //2-1、 确定取消关注事件
    $scope.sureBtn = function (event) {
        var target = $(event.target);
        var cancelUrl = host + "/attentiontopic/attentiontopicBystatus",
            topicId = target.parents(".cancelCollect").siblings(".topicId").val(),
            topicStatus = "0";
        var param = {
            "userId": userid,
            "topicId": topicId,
            "attentionStatus": topicStatus
        }
        $http({
            method: 'post',
            url: cancelUrl,
            data: param,
            dataType: "json",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj) {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            }
        }).success(function (data) {
            console.log(data)
            if (data.code == '0') {
                location.reload();
            }
        });
        target.parents(".cancelCollect").fadeOut().siblings(".shadow").fadeOut();
    }
    //2-2、 取消  取消关注事件
    $scope.cancelBtn = function (event) {
        var target = $(event.target);
        target.parents(".cancelCollect").fadeOut().siblings(".shadow").fadeOut();
    }
    //3、点击关注 关注模块
    $scope.clickFollow = function (event) {
        var target = $(event.target);
        alert("点击关注")
    }
}]);
//邀请评论
app.controller('invitingCommentCtrl', ['$scope', '$rootScope', '$http', '$location', '$routeParams', function ($scope, $rootScope, $http, $location, $routeParams) {
    $rootScope.$broadcast('haveMenum', '0');
    $scope.userid = $routeParams.userid;
    $scope.msgId = $routeParams.msgTypeId;
    $scope.postId = $routeParams.postId;
    $scope.pageClass = 'invitingComment';
    var userid = "1101",
        param = {
            "userid": userid,
            'pageSize': '20', //一页显示多少
            'pageNum': '1' //第几页
        }
    // $scope.userid = userid;

   
    // 1. 邀请评论
    linkMan();
    function linkMan (){
        var praiseUrl = host + "/inviteComments/inviteuserListbyMap?userid=" + $scope.userid;
    $http({
        method: 'post',
        url: praiseUrl,
        dataType: "json",
        data: param,
        headers: {
            'Content-Type': 'application/x-www-form-urlencoded'
        },
        transformRequest: function (obj) {
            var str = [];
            for (var p in obj) {
                str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
            }
            return str.join("&");
        }
    }).success(function (data) {
        // console.log(data)
        $scope.invitingCommentList = data.list;
        // console.log($scope.invitingCommentList)
    });
    }
    
    //2. 点击模糊搜索联系人
    $scope.searchPerson = function(event){
        var target = $(event.target);
        var searchPerson =  target.siblings('input').val();
        var url = host + "/inviteComments/getUserByQueryKey";
        $http({
            method: 'post',
            url: url,
            data: {
                "userid": $scope.userid,
                'pageSize': '20', //一页显示多少
                'pageNum': '1', //第几页
                 queryKey : searchPerson //联系人搜索的内容
            },
            dataType: "json",
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            transformRequest: function (obj) {
                var str = [];
                for (var p in obj) {
                    str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                }
                return str.join("&");
            }
        }).success(function (data) {
            $scope.newsData = data;
            $scope.invitingCommentList = data.data.list;
        })
    }
    //3. 点击选择邀请联系人
    $scope.choseComLi = function (event) {
        var target = $(event.target);
        var img = target.attr('src');
        // var List = [];//选择信息的数组
        // var b = {};
        if (img == '../images/btn_check_n@2x.png') {
            // target.attr("src", "../images/btn_check_p@2x.png");
            target.attr({"src": "../images/btn_check_p@2x.png","type":"1"});
            // console.log( target.attr('userName'))
            // console.log( target.attr('userId'))
            // target.sublings().attr('')
            // b = {'userName':target.attr('userName'),'userId':target.attr('userId')}
            // List.push(b);
            // console.log(List)
            
        } else {
            target.attr({"src": "../images/btn_check_n@2x.png","type":"0"});
        }

    }
    //4. 点击发送
    $scope.sendPeople = function (event) {
        var target = $(event.target);
        var $img = target.parents("header").siblings(".content").find(".checkedImg");
        var List = [];//选择信息的数组
        var userLi = {};
        $('.checkedImg').each(function () {
            userLi = {}
            // console.log($(this).attr('type'))
             if($(this).attr('type')==1){
                userLi = {'userName': $(this).attr('userName'),'userId': $(this).attr('userId')}
                List.push(userLi);
             }
            
         });
        //  console.log(List)
        // function addPostInfo(){
        var aaa = [{
            "postid": $scope.postId,
            "userid": $scope.userid,
            "list": List
        }]
        aaa = JSON.stringify(aaa)
        var data = {
            "message": aaa
        }
        var url = host + "/inviteComments/sendInviteRemind";
        if ($img.attr("src") == '../images/btn_check_p@2x.png') {
            $http({
                method: 'post',
                url: url,
                data: data,
                dataType: "json",
                headers: {
                    'Content-Type': 'application/x-www-form-urlencoded'
                },
                transformRequest: function (obj) {
                    var str = [];
                    for (var p in obj) {
                        str.push(encodeURIComponent(p) + "=" + encodeURIComponent(obj[p]));
                    }
                    return str.join("&");
                }
            }).success(function (data) {
                $scope.newsData = data;
                // console.log($scope.newsData);
                alert("发送成功");
                back();
            });
        } else {
            alert("请选择您要邀请的朋友..");
        }

    }


}]);


//定义当循环结束后执行
app.directive('onFinishRenderFilters', function ($timeout) {
    return {
        restrict: 'A',
        link: function (scope, element, attr) {
            if (scope.$last === true) {
                $timeout(function () {
                    scope.$emit('ngRepeatFinished');
                    tabSlider();
                });
            }
        }
    };
});
//加载更多
app.directive('whenScrolled', function () {
    return function (scope, elm, attr) {
        var raw = elm[0];
        elm.bind('scroll', function () {
            if (raw.scrollTop + raw.offsetHeight >= raw.scrollHeight) {
                scope.$apply(attr.whenScrolled);
            }
        });
    }
});