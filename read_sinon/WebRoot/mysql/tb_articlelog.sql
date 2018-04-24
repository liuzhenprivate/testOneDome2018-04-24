/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:18:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_articlelog`
-- ----------------------------
DROP TABLE IF EXISTS `tb_articlelog`;
CREATE TABLE `tb_articlelog` (
  `ARTICLELOG_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ARTICLE_ID` int(11) NOT NULL COMMENT '书籍ID',
  `ARTICLE_CHAPTER_ID` int(11) NOT NULL COMMENT '章节ID',
  `USERID` int(11) NOT NULL COMMENT '用户表主键',
  `CHANNEL_ID` int(11) NOT NULL COMMENT '渠道表主键',
  `WEBCHAT_ID` int(11) NOT NULL COMMENT '微信公众表主键',
  `CREATE_TIME` varchar(32) DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`ARTICLELOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_articlelog
-- ----------------------------
