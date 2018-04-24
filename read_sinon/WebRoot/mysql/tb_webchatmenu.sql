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
-- Table structure for `tb_webchatmenu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_webchatmenu`;
CREATE TABLE `tb_webchatmenu` (
  `WEBCHATMENU_ID` int(10)  NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `WEBCHAT_ID` int(11) NOT NULL COMMENT 'tb_webchat表主键（外键）',
  `TYPE` varchar(13) DEFAULT NULL COMMENT 'view或者click',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `MENUURL` varchar(535) DEFAULT NULL COMMENT '连接',
  `SOURT` int(2) DEFAULT 0 COMMENT '排序',
  `PID` varchar(53) DEFAULT 0 COMMENT '父节点',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`WEBCHATMENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_webchatmenu
-- ----------------------------
