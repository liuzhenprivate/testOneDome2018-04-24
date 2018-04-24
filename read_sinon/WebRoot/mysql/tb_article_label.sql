/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:18:09
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_article_label`
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_label`;
CREATE TABLE `tb_article_label` (
  `ARTICLE_LABEL_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ARTICLE_ID` int(11) NOT NULL COMMENT '书籍ID',
  `LABEL_ID` int(11) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`ARTICLE_LABEL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_article_label
-- ----------------------------
