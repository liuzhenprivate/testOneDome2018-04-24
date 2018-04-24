/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:19:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_recharge_set`
-- ----------------------------
DROP TABLE IF EXISTS `tb_recharge_set`;
CREATE TABLE `tb_recharge_set` (
  `RECHARGE_SET_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `RECHARGE_TYPE` int(11) DEFAULT '0' COMMENT '充值类型(默认0 普通充值1月卡2年卡)',
  `FREE_TIME` varchar(32) DEFAULT NULL COMMENT '免费时间段  22:00-6:00',
  `STATE` int(11) DEFAULT NULL COMMENT '状态 默认0正常1隐藏2删除',
  `MONEY` int(11) DEFAULT NULL COMMENT '充值金额',
  `BOOK_CURRENCY` int(11) DEFAULT NULL COMMENT '对应书币',
  `BOOK_CURRENCY_GIFT` int(11) DEFAULT NULL COMMENT '赠送书币',
  `BOOK_CURRENCY_ALL` int(11) DEFAULT NULL COMMENT '总书币',
  `USE_SCOPE` int(11) DEFAULT NULL COMMENT '可用范围',
  `BUY_LIMIT` varchar(35) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '购买限制',
  `EXP_DATE` varchar(35) DEFAULT NULL COMMENT '有效期',
  `SORT_NUM` int(11) DEFAULT NULL COMMENT '排序',
  `MEMO` varchar(135) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`RECHARGE_SET_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_recharge_set
-- ----------------------------
INSERT INTO `tb_recharge_set` VALUES ('1', '0', '12', '0', '131', '231', '312', '3123', '31', '1', '1', '1', '1', '1');
