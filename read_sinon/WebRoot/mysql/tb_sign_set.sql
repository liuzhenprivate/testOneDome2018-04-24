/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:19:32
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_sign_set`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sign_set`;
CREATE TABLE `tb_sign_set` (
  `SIGN_SET_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `TIMES` int(10) DEFAULT '0' COMMENT '签到天数(默认0位每日签到)',
  `BOOK_CURRENCY` int(10) DEFAULT '0' COMMENT '赠送书币',
  `TYPE` int(1) DEFAULT '0' COMMENT '签到类型(默认0,1为连续签到)',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`SIGN_SET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_sign_set
-- ----------------------------
