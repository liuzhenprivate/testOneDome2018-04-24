/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:19:39
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `USER_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `CHANNEL_ID` varchar(20) NOT NULL COMMENT '渠道',
  `WEHCHAT_ID` varchar(255) NOT NULL COMMENT '微信公众号ID  外键',
  `USER_CODE` varchar(10) NOT NULL COMMENT '用户编号ID',
  `OPENID` varchar(255) NOT NULL COMMENT '微信OPENID',
  `NICKNAME` varchar(255) DEFAULT NULL COMMENT '微信昵称',
  `RECHARGE_MONEY` int(10) DEFAULT '0' COMMENT '累计充值金额',
  `SEX` int(1) DEFAULT NULL COMMENT '性别',
  `PROVINCE` varchar(20) DEFAULT NULL COMMENT '省份',
  `CITY` varchar(35) DEFAULT NULL COMMENT '城市',
  `COUNTRY` varchar(20) DEFAULT NULL COMMENT '国家',
  `HEADIMGURL` varchar(135) DEFAULT NULL COMMENT '微信头像 （用户头像，最后一个数值代表正方形头像大小（有0、46、64、96、132数值可选，0代表640*640正方形头像），用户没有头像时该项为空。若用户更换头像，原有头像URL将失效。）',
  `PRIVILEGE` varchar(135) DEFAULT NULL COMMENT '用户特权信息',
  `UNIONID` varchar(135) DEFAULT NULL COMMENT 'UNIONID',
  `CTIME` varchar(35) DEFAULT NULL COMMENT '关注时间',
  `BOOK_CURRENCY` int(10) DEFAULT '0' COMMENT '书币',
  `LEVEL` varchar(20) DEFAULT NULL COMMENT '等级',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '666666', '2131', '123', '123', '231', '131', '1', '312', '123', '3123', '123', '3213', '1231', '2017-11-08', '3123', '5');
