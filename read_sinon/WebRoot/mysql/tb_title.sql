/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:19:13
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_pic`
-- ----------------------------
DROP TABLE IF EXISTS `tb_title`;
CREATE TABLE `tb_title` (
  `TITLE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `TITLE_CATEGORY_ID` int(10) NOT NULL COMMENT '标题类目ID',
  `TITLE_NAME` varchar(55) DEFAULT NULL COMMENT '标题名称',
  `CONTENT` varchar(2555) NOT NULL COMMENT '标题内容',
  `USE_TIMES` int(5) DEFAULT 0 COMMENT '使用次数',
  `CREATE_TIME` varchar(35) NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`TITLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

 
