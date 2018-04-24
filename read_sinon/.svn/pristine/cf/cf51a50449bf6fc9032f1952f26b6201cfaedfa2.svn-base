/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:17:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `TB_SEARCHKEY`
-- ----------------------------
DROP TABLE IF EXISTS `TB_SEARCHKEY`;
CREATE TABLE `TB_SEARCHKEY` (
  `SEARCHKEY_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `KEYWORD` varchar(130) NOT NULL COMMENT '搜索关键词',
  `SEARCH_TYPE` int(1) DEFAULT '0' COMMENT '默认0热门搜索 1历史搜索',
  `CREATE_TIME` varchar(34) DEFAULT NULL COMMENT '搜索时间',
  PRIMARY KEY (`SEARCHKEY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
