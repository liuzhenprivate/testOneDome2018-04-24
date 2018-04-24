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
-- Table structure for `TB_BOARD`
-- ----------------------------
DROP TABLE IF EXISTS `TB_BOARD`;
CREATE TABLE `TB_BOARD` (
  `BOARD_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '榜单主键id',
  `BOARD_NO` int(2) NOT NULL COMMENT '榜单排序规则',
  `BOARD_NAME` varchar(35) NOT NULL COMMENT '榜单名',
  `BOARD_IMG` varchar(255) DEFAULT NULL COMMENT '榜单图标',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  `SORT` int(11) DEFAULT NULL COMMENT '榜单排序',
  PRIMARY KEY (`BOARD_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8
-- ----------------------------
-- Records of noticelog
-- ----------------------------
