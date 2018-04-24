/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:19:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_recharge_log`
-- ----------------------------
DROP TABLE IF EXISTS `tb_recharge_log`;
CREATE TABLE `tb_recharge_log` (
  `RECHARGE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_ID` int(11) NOT NULL COMMENT '用户ID',
  `CHANNEL_ID` varchar(20) NOT NULL COMMENT '渠道  默认微信',
  `WEHCHAT_ID` varchar(255) NOT NULL COMMENT '微信公众号ID',
  `MONEY` int(11) DEFAULT NULL COMMENT '充值金额',
  `BOOK_CURRENCY` int(11) DEFAULT NULL COMMENT '对应书币',
  `BOOK_CURRENCY_GIFT` int(11) DEFAULT NULL COMMENT '赠送的书币',
  `BOOK_CURRENCY_ALL` int(11) DEFAULT NULL COMMENT '总书币',
  `CONSUME` int(11) DEFAULT NULL COMMENT '消费金额',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '充值时间',
  `STATE` int(1) DEFAULT NULL COMMENT '充值状态',
  `RESULT_PAY` varchar(35) DEFAULT NULL COMMENT '支付结果',
  `TIME_PAY` varchar(35) DEFAULT NULL COMMENT '支付完成时间',
  `RESULT_RECHARGE` varchar(135) DEFAULT NULL COMMENT '充值结果',
  `TIME_RECHARGE` varchar(35) DEFAULT NULL COMMENT '充值完成时间',
  `CHANNEL` varchar(35) DEFAULT NULL COMMENT '渠道',
  `CHANNEL_INCOME` int(10) DEFAULT NULL COMMENT '渠道分成',
  `INCOME` int(10) DEFAULT NULL COMMENT '平台收益',
  `UPSTREAM_INCOME` int(10) DEFAULT NULL COMMENT '上游收益',
  PRIMARY KEY (`RECHARGE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_recharge_log
-- ----------------------------
INSERT INTO `tb_recharge_log` VALUES ('1', '6666', '1231', '12312', '3123', '3123', '1323', '3123', '123', '2017-11-08', '5', '0', '2017-11-08', '32', '2017-11-08', '12312', '1323', '312', '3123');
