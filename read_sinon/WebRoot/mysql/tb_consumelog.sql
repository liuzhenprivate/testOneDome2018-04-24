/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:18:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_consumelog`
-- ----------------------------
DROP TABLE IF EXISTS `tb_consumelog`;
CREATE TABLE `tb_consumelog` (
  `CONSUMELOG_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USERID` int(11) NOT NULL COMMENT '用户表主键',
  `CHANNEL_ID` int(11) NOT NULL COMMENT '渠道表主键',
  `WEBCHAT_ID` int(11) NOT NULL COMMENT '微信公众表主键',
  `CREATE_TIME` varchar(32) DEFAULT NULL COMMENT '消费时间',
  `MONEY` int(10) DEFAULT NULL COMMENT '对应人民币',
  `CONSUMES` int(10) DEFAULT NULL COMMENT '扣除阅读币',
  PRIMARY KEY (`CONSUMELOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_consumelog
-- ----------------------------
INSERT INTO `tb_consumelog` VALUES ('1', '6666', '3123', '1323', '2017-11-08', '3123', '3123');
