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
-- Table structure for `TB_ZANLOG`
-- ----------------------------
DROP TABLE IF EXISTS `TB_ZANLOG`;
CREATE TABLE `TB_ZANLOG` (
  `ZANLOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ARTICLE_ID` int(11) NOT NULL COMMENT '书籍ID',
  `DISCUSS_ID` int(11) NOT NULL  COMMENT '评论ID',
  `LEVELS` int(1) DEFAULT 0 COMMENT '几星评价',
  `USERID` int(1) NOT NULL COMMENT '读者ID',
  `CREATE_TIME` varchar(35) NOT NULL COMMENT '点赞时间',
  PRIMARY KEY (`ZANLOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of noticelog
-- ----------------------------
