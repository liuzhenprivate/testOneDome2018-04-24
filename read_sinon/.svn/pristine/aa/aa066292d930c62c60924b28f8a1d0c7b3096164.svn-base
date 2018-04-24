/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:17:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_account`
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `ACCOUNT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL ,
  `ACCOUNT_NAME` varchar(55) NOT NULL COMMENT '银行卡名字',
  `CARD_NUM` varchar(20) NOT NULL COMMENT '卡号',
  `OPEN_CARD` varchar(155) NOT NULL COMMENT '开户行',
  `PHONE` varchar(12) DEFAULT NULL COMMENT '手机号',
  `QQ` varchar(35) DEFAULT NULL COMMENT 'QQ号',
  `WEBCHAT` varchar(35) DEFAULT NULL COMMENT '微信号',
   `ADDRESS` varchar(255) DEFAULT NULL COMMENT '地址',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_account
-- ----------------------------
