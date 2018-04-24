/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:19:02
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_count_week`
-- ----------------------------
DROP TABLE IF EXISTS `tb_count_week`;
CREATE TABLE `tb_count_week` (
  `COUNNT_WEEK_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `CHANNEL_ID` int(11) NOT NULL COMMENT '渠道ID',
  `WEHCHAT_ID` int(11) NOT NULL COMMENT '微信公众号ID',
  `USERS` int(10) DEFAULT '0' COMMENT '会员总数',
  `RECHARGES` int(10) DEFAULT '0' COMMENT '充值总数',
  `RECHARGETIMES` int(10) DEFAULT '0' COMMENT '充值笔数',
  `RECHARGEUSERS` int(10) DEFAULT '0' COMMENT '充值人数',
  `CONSUMES` int(10) DEFAULT '0' COMMENT '消费阅读币',
  `MONEY` int(10) DEFAULT '0' COMMENT '消费人民币',
  `CONSUMENUMBER` int(10) DEFAULT '0' COMMENT '消费笔数',
  `START_DATE` varchar(20) DEFAULT NULL COMMENT '开始日期',
  `END_DATE` varchar(20) DEFAULT NULL COMMENT '结束日期',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建日期',
  `UPDATE_TIME` varchar(35) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`COUNNT_WEEK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_count_week
-- ----------------------------
INSERT INTO `tb_count_week` VALUES ('1', '123456789', '123456789', '1', '200', '1', '1', '70', '200', '1', '2017-10-30', '2017-11-5', '2017-10-30 00:00:00', '2017-11-01 10:00:00');
