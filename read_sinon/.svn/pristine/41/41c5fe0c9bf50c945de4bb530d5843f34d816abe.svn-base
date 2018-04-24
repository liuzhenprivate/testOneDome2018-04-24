/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:18:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `noticelog`
-- ----------------------------
DROP TABLE IF EXISTS `noticelog`;
CREATE TABLE `noticelog` (
  `NOTICELOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(35) NOT NULL COMMENT '公告标题',
  `CONTENT` varchar(1000) NOT NULL COMMENT '公告内容',
  `STATE` int(1) NOT NULL COMMENT '状态0等待推送，1已删除，2取消推送，3已推送',
  `SEND_TIME` varchar(34) DEFAULT NULL COMMENT '推送时间默认为0 立即推送',
  `ADMIN_ID` varchar(35) DEFAULT NULL COMMENT '管理员ID',
  `CTIME` varchar(35) NOT NULL COMMENT '创建时间',
  `PUSHMODE` int(11) DEFAULT '1' COMMENT '推送方式（1：立即推送  2：定时推送）',
  PRIMARY KEY (`NOTICELOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of noticelog
-- ----------------------------
INSERT INTO `noticelog` VALUES ('8', '测试1', '测试1', '3', '2017-11-29', '1', '2017-11-28 17:50:07', '1');
INSERT INTO `noticelog` VALUES ('9', '测试', '测试内容', '3', '2017-11-02', '1', '2017-11-29 13:50:54', '1');
INSERT INTO `noticelog` VALUES ('10', '测试', '测试', '1', '2017-11-06', '1', '2017-11-29 13:51:29', '1');
