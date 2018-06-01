/*
Navicat MySQL Data Transfer

Source Server         : 116.196.107.233
Source Server Version : 50721
Source Host           : 116.196.107.233:3306
Source Database       : lanxj_forum

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-05-04 11:15:21
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `attention_post`
-- ----------------------------
DROP TABLE IF EXISTS `attention_post`;
CREATE TABLE `attention_post` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `post_id` bigint(20) DEFAULT NULL COMMENT '帖子id',
  `attention_status` bigint(10) DEFAULT NULL COMMENT '关注状态(0取消关注 1关注)',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='帖子关注表';

-- ----------------------------
-- Records of attention_post
-- ----------------------------
INSERT INTO `attention_post` VALUES ('1', '1001', '10001', '1', '2018-05-03 15:34:57');
INSERT INTO `attention_post` VALUES ('3', '1002', '10001', '1', '2018-05-03 15:53:41');

-- ----------------------------
-- Table structure for `attention_topic`
-- ----------------------------
DROP TABLE IF EXISTS `attention_topic`;
CREATE TABLE `attention_topic` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `topic_id` bigint(20) DEFAULT NULL,
  `attention_status` bigint(10) DEFAULT NULL COMMENT '关注状态(0取消关注 1关注)',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of attention_topic
-- ----------------------------

-- ----------------------------
-- Table structure for `forum_post_info`
-- ----------------------------
DROP TABLE IF EXISTS `forum_post_info`;
CREATE TABLE `forum_post_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `post_id` bigint(20) NOT NULL COMMENT '帖子ID',
  `user_id` bigint(20) NOT NULL COMMENT '发帖人ID',
  `user_name` varchar(60) DEFAULT '' COMMENT '发帖人姓名',
  `topic_id` bigint(10) DEFAULT '0' COMMENT '帖子版块ID',
  `post_status` bigint(10) DEFAULT NULL COMMENT '帖子状态（0草稿 10待审 20正常 30拒绝 40删除）',
  `anonymity_yn` tinyint(10) DEFAULT NULL COMMENT '是否匿名 （0否 1是）',
  `top_yn` bigint(10) DEFAULT NULL COMMENT '是否置顶 (0未置顶、1已置顶）',
  `likes_count` bigint(10) DEFAULT '0' COMMENT '点赞数量',
  `comments_count` bigint(10) DEFAULT '0' COMMENT '评论数量',
  `page_view` bigint(10) DEFAULT '0' COMMENT '浏览量统计',
  `post_content` varchar(3000) DEFAULT NULL COMMENT '帖子内容',
  `refuse_reason` varchar(300) DEFAULT NULL COMMENT '拒绝原因',
  `create_time` datetime DEFAULT NULL COMMENT '发帖时间',
  `delete_time` datetime DEFAULT NULL COMMENT '删帖时间',
  `top_start_time` datetime DEFAULT NULL COMMENT '置顶时间',
  `top_valid_time` datetime DEFAULT NULL COMMENT '置顶有效时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10003 DEFAULT CHARSET=utf8 COMMENT='帖子信息表';

-- ----------------------------
-- Records of forum_post_info
-- ----------------------------
INSERT INTO `forum_post_info` VALUES ('1', '100001', '1001', '李宁', '1', '20', '1', '1', '1', '1', '1', '生活就是要充满激情，除了上班，我们还有生活；除了生活，我们还有上班', '', '2018-04-28 17:45:40', '2018-04-28 17:45:46', '2018-04-28 17:45:52', '2018-04-28 17:46:37', null);
INSERT INTO `forum_post_info` VALUES ('2', '100002', '1002', '张宇', '2', '20', '1', '0', '1', '1', '1', '实干方得真知', '', '2018-04-28 17:45:40', '2018-04-28 17:45:46', '2018-04-28 17:45:52', '2018-04-28 17:46:37', null);

-- ----------------------------
-- Table structure for `forum_topic_info`
-- ----------------------------
DROP TABLE IF EXISTS `forum_topic_info`;
CREATE TABLE `forum_topic_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `topic_id` bigint(20) NOT NULL COMMENT '版块id',
  `topic_name` varchar(100) DEFAULT NULL COMMENT '版块名字',
  `topic_status` bigint(10) DEFAULT NULL COMMENT '版块状态（0关闭 1正常）',
  `anonymity_yn` bigint(10) DEFAULT NULL COMMENT '是否可匿名（0否 1是）',
  `check_yn` bigint(10) DEFAULT NULL COMMENT '审核开关（0关闭 1开启）',
  `topic_order` bigint(10) DEFAULT NULL COMMENT '版块顺序',
  `topic_likes_count` bigint(30) DEFAULT NULL COMMENT '版块块点赞统计',
  `topic_comments_count` bigint(30) DEFAULT NULL COMMENT '版块评论统计',
  `topic_page_view` bigint(30) DEFAULT NULL COMMENT '版块浏览量统计',
  `create_user` varchar(30) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_user` varchar(30) DEFAULT NULL COMMENT '修改人',
  `modify_time` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='版块信息表';

-- ----------------------------
-- Records of forum_topic_info
-- ----------------------------
INSERT INTO `forum_topic_info` VALUES ('1', '1', '生活类', '1', '1', '1', '1', '1', '1', '1', '100001', '2018-05-02 16:44:54', null, '2018-05-02 16:45:07');

-- ----------------------------
-- Table structure for `my_message_detail`
-- ----------------------------
DROP TABLE IF EXISTS `my_message_detail`;
CREATE TABLE `my_message_detail` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `msg_id` bigint(20) DEFAULT NULL COMMENT '消息ID',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `read_yn` bigint(10) DEFAULT NULL COMMENT '是否已读',
  `post_id` bigint(20) DEFAULT NULL COMMENT '帖子ID',
  `post_prefix _context` varchar(90) DEFAULT NULL COMMENT '帖子前缀内容（帖子前十个字）',
  `create_time` datetime DEFAULT NULL,
  `remark` varchar(60) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的消息详情表';

-- ----------------------------
-- Records of my_message_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `my_message_list`
-- ----------------------------
DROP TABLE IF EXISTS `my_message_list`;
CREATE TABLE `my_message_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `msg_type_id` bigint(20) DEFAULT NULL COMMENT '消息类型ID',
  `msg_type_name` varchar(60) DEFAULT NULL COMMENT '消息类型名称',
  `msg_template` varchar(300) DEFAULT NULL COMMENT '消息模板',
  `remark` varchar(60) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='我的消息列表';

-- ----------------------------
-- Records of my_message_list
-- ----------------------------

-- ----------------------------
-- Table structure for `post_comments_relate`
-- ----------------------------
DROP TABLE IF EXISTS `post_comments_relate`;
CREATE TABLE `post_comments_relate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `post_id` bigint(20) NOT NULL COMMENT '帖子ID',
  `comment_user_id` bigint(20) NOT NULL COMMENT '评论人ID',
  `comment_user_name` varchar(60) DEFAULT NULL COMMENT '评论人名称',
  `comment_content` varchar(3000) DEFAULT NULL COMMENT '评论内容',
  `comment_status` bigint(10) DEFAULT '1' COMMENT '评论状态（0无效 1有效 ）',
  `comment_time` datetime DEFAULT NULL COMMENT '评论时间',
  `delete_user` bigint(20) DEFAULT NULL COMMENT '删除人',
  `delete_time` datetime DEFAULT NULL COMMENT '删除时间',
  `invite_comments_yn` int(4) NOT NULL COMMENT '是否邀请评论（0否 1是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='帖子评论关联表';
-- ----------------------------
-- Records of post_comments_relate
-- ----------------------------

-- ----------------------------
-- Table structure for `post_likes_relate`
-- ----------------------------
DROP TABLE IF EXISTS `post_likes_relate`;
CREATE TABLE `post_likes_relate` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `post_id` bigint(20) DEFAULT NULL COMMENT '帖子ID',
  `like_status` bigint(10) DEFAULT NULL COMMENT '点赞状态（0取消 1正常）',
  `like_user_id` bigint(20) DEFAULT NULL COMMENT '点赞人ID',
  `like_user_name` varchar(60) DEFAULT NULL COMMENT '点赞人姓名',
  `like_time` datetime DEFAULT NULL COMMENT '点赞时间',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='帖子点赞关联表';

-- ----------------------------
-- Records of post_likes_relate
-- ----------------------------

-- ----------------------------
-- Table structure for `user_black_list`
-- ----------------------------
DROP TABLE IF EXISTS `user_black_list`;
CREATE TABLE `user_black_list` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '发帖人ID',
  `user_name` varchar(120) DEFAULT NULL COMMENT '用户姓名',
  `remark` varchar(300) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户黑名单';

-- ----------------------------
-- Records of user_black_list
-- ----------------------------

-- ----------------------------
-- Table structure for `user_info`
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '发帖人ID',
  `user_name` varchar(60) DEFAULT NULL COMMENT '发帖人姓名',
  `user_type` bigint(10) DEFAULT NULL COMMENT '用户类型（0普通用户 1管理员 2 子管理员）',
  `position` varchar(120) DEFAULT NULL COMMENT '员工岗位',
  `invite_comment_auth` bigint(10) DEFAULT NULL COMMENT '邀请评论权限(0无 1有)',
  `remark` varchar(60) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户信息表';

-- ----------------------------
-- Records of user_info
-- ----------------------------
INSERT INTO `user_info` VALUES ('1', '1001', 'gcr', '0', '技术研发岗', '0', null);
INSERT INTO `user_info` VALUES ('2', '1002', '李宁', '0', '技术研发岗', '1', null);
INSERT INTO `user_info` VALUES ('3', '1003', 'gcr128', '1', '技术研发岗', '1', 'qqq');
INSERT INTO `user_info` VALUES ('4', '1004', '天天', '0', '技术研发岗', '1', '33');
