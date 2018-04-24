/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:19:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_sign_log`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sign_log`;
CREATE TABLE `tb_sign_log` (
  `SIGN_LOG_ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_ID` int(11) NOT NULL COMMENT '用户ID 外键',
  `CHANNEL_ID` varchar(20) NOT NULL COMMENT '渠道',
  `WEHCHAT_ID` varchar(255) NOT NULL COMMENT '微信公众号ID 外键',
  `LOG_TYPE` int(11) DEFAULT '1' COMMENT '记录类型 默认1每日签到2领取奖励',
  `TIMES` int(11) DEFAULT NULL COMMENT '对应奖励次数  对应奖励设置表的times字段',
  `BOOK_CURRENCY` int(11) DEFAULT NULL COMMENT '获取的书币',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '获取时间',
  `SIGN_MONTH` varchar(25) DEFAULT NULL COMMENT '签到月份  YYYY-MM',
  PRIMARY KEY (`SIGN_LOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_sign_log
-- ----------------------------
