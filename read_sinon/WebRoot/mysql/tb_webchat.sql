/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:19:45
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_webchat`
-- ----------------------------
DROP TABLE IF EXISTS `tb_webchat`;
CREATE TABLE `tb_webchat` (
  `WEBCHAT_ID` int(10)  NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_ID` int(11) NOT NULL COMMENT 'SYS_USER表主键（外键）',
  `TYPE` varchar(13) DEFAULT NULL COMMENT '类型 1公众号 2订阅号',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `WEBCHAT_CODE` varchar(35) DEFAULT NULL COMMENT '微信号',
  `APPID` varchar(35) DEFAULT NULL COMMENT 'APPID',
  `APPSECRET` varchar(35) DEFAULT NULL COMMENT '秘钥  AppSecret',
  `SHOP_KEY` varchar(35) DEFAULT NULL COMMENT '商户KEY',
  `MCH_ID` varchar(35) DEFAULT NULL COMMENT '商户ID',
  `ACCESS_TOKEN` varchar(35) DEFAULT NULL COMMENT 'access_token',
  `PIC_URL` varchar(135) DEFAULT NULL COMMENT '公众号二维码地址',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  `SIGN_SWITCH` int(1) DEFAULT '0' COMMENT '签到开关（默认0开启1关闭）',
  `OPEN_DATE` varchar(31) DEFAULT NULL COMMENT '开启签到日期',
 
  `SETNAME` varchar(31) DEFAULT NULL COMMENT '站点名称',
  `KEYWORDS` varchar(131) DEFAULT NULL COMMENT '关键词',
  `CUSTOM_PHONE` varchar(13) DEFAULT NULL COMMENT '客服电话',
  `CUSTOM_QQ` varchar(31) DEFAULT NULL COMMENT '客服QQ',
  `CUSTOM_EMAIL` varchar(31) DEFAULT NULL COMMENT '客服EMAIL',
  `CUSTOM_WEBCHAT` varchar(31) DEFAULT NULL COMMENT '客服微信',
  `MEMO` varchar(1355) DEFAULT NULL COMMENT '站点描述',
  PRIMARY KEY (`WEBCHAT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_webchat
-- ----------------------------
