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
-- Table structure for `tb_webchatreply`
-- ----------------------------
DROP TABLE IF EXISTS `tb_webchatreply`;
CREATE TABLE `tb_webchatreply` (
  `WEBCHATREPLY_ID` int(10)  NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `WEBCHAT_ID` int(11) NOT NULL COMMENT 'tb_webchat表主键（外键）',
  `KEYWORDS` varchar(113) NOT NULL COMMENT '关键词',
  `CONTENT` varchar(1755) NOT NULL COMMENT '回复内容',
  `VALID_HOURS` int(3) DEFAULT NULL COMMENT '有效小时',
  `TYPE` int(1) DEFAULT 0 COMMENT '类型0自动回复 1关注回复',
  `LOSE_TIME` varchar(35) NOT NULL COMMENT '失效时间',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`WEBCHATREPLY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_webchatreply
-- ----------------------------
