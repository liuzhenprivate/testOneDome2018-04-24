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
-- Table structure for `TB_HTML`
-- ----------------------------
DROP TABLE IF EXISTS `TB_HTML`;
CREATE TABLE `TB_HTML` (
  `HTML_ID` int(11) NOT NULL AUTO_INCREMENT,
  `PLACE_TYPE` int(3) NOT NULL COMMENT '位置类型1书城 2男生 3女生 4VIP',
   `NAME` varchar(35) NOT NULL COMMENT '页面名称',
  `HTML_TYPE` int(1) NOT NULL COMMENT '页面类型1系统页面 2其他',
  `STATE` int(1) NOT NULL COMMENT '页面状态 1上线 2未上线',
  `HTML_URL` varchar(255) DEFAULT NULL COMMENT '页面链接',
  `PVS` int(11) DEFAULT 0 COMMENT '访问量',
  `MEMO` varchar(555) DEFAULT NULL COMMENT '描述说明',
  `CREATE_TIME` varchar(35) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`HTML_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of noticelog
-- ----------------------------
