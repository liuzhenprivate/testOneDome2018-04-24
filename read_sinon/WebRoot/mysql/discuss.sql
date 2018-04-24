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
-- Table structure for `TB_DISCUSS`
-- ----------------------------
DROP TABLE IF EXISTS `TB_DISCUSS`;
CREATE TABLE `TB_DISCUSS` (
  `DISCUSS_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ARTICLE_ID` int(11) NOT NULL COMMENT '公告标题',
  `USERID` int(11) NOT NULL COMMENT '读者ID',
  `ZANS` int(11) NOT NULL COMMENT '点赞数',
  `LEVELS` int(2) NOT NULL COMMENT '几星',
  `CONTENT` varchar(2255) NOT NULL COMMENT '评论内容',
  `STATE` int(1) NOT NULL COMMENT '状态0未审核1审核通过2审核失败3删除',
  `USER_CODE` varchar(35) DEFAULT NULL COMMENT '平台ID',
  `CREATE_TIME` varchar(35) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`DISCUSS_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of noticelog
-- ----------------------------
