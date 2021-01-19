/*
 Navicat Premium Data Transfer

 Source Server         : 我的本地
 Source Server Type    : MySQL
 Source Server Version : 50711
 Source Host           : localhost:3306
 Source Schema         : websocket

 Target Server Type    : MySQL
 Target Server Version : 50711
 File Encoding         : 65001

 Date: 19/01/2021 14:10:27
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for msg_info
-- ----------------------------
DROP TABLE IF EXISTS `msg_info`;
CREATE TABLE `msg_info`  (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT '消息id',
  `from_user_id` int(6) NOT NULL COMMENT '消息发送者id',
  `from_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息发送者名称',
  `to_user_id` int(6) NOT NULL COMMENT '消息接收者id',
  `to_user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息接收者名称',
  `content` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '消息内容',
  `create_time` datetime(0) NOT NULL COMMENT '消息发送时间',
  `un_read_flag` int(1) NOT NULL COMMENT '是否已读（1 已读）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 264 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '消息表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for session_list
-- ----------------------------
DROP TABLE IF EXISTS `session_list`;
CREATE TABLE `session_list`  (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `user_id` int(6) NOT NULL COMMENT '所属用户',
  `to_user_id` int(6) NOT NULL COMMENT '到用户',
  `list_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '会话名称',
  `un_read_count` int(6) NOT NULL COMMENT '未读消息数',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 41 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '会话列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(6) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
