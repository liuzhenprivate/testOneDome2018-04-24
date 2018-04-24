/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:18:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_count_day`
-- ----------------------------
DROP TABLE IF EXISTS `tb_count_day`;
CREATE TABLE `tb_count_day` (
  `COUNT_DAY_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `CHANNEL_ID` int(11) NOT NULL COMMENT '渠道ID',
  `WEHCHAT_ID` int(11) NOT NULL COMMENT '微信公众号ID',
  `USERS` int(10) DEFAULT '0' COMMENT '会员总数',
  `RECHARGES` int(10) DEFAULT '0' COMMENT '充值总数',
  `RECHARGETIMES` int(10) DEFAULT '0' COMMENT '充值笔数',
  `RECHARGEUSERS` int(10) DEFAULT '0' COMMENT '充值人数',
  `CONSUMES` int(10) DEFAULT '0' COMMENT '消费阅读币',
  `MONEY` int(10) DEFAULT '0' COMMENT '消费人民币',
  `CONSUMENUMBER` int(10) DEFAULT '0' COMMENT '消费笔数',
  `COUNT_DAY` varchar(255) DEFAULT NULL COMMENT '统计日期',
  `CREATE_TIME` varchar(255) DEFAULT NULL COMMENT '创建日期',
  `UPDATE_TIME` varchar(255) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`COUNT_DAY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_count_day
-- ----------------------------
INSERT INTO `tb_count_day` VALUES ('1', '123456789', '123456789', '1', '200', '1', '1', '70', '200', '1', '2017-11-01', '2017-11-01 00:00:00', '2017-11-01 10:00:00');
INSERT INTO `tb_count_day` VALUES ('2', '6666666', '6666666', '1', '200', '1', '1', '70', '200', '1', '2017-10-01', '2017-10-01 00:00:00', '2017-10-01 10:00:00');
