/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:17:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_article_category`
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_category`;
CREATE TABLE `tb_article_category` (
  `ARTICLE_CATEGORY_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `CATEGORY` varchar(25) DEFAULT NULL COMMENT '类目名称',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `STATE` int(11) DEFAULT NULL COMMENT '状态 0正常1隐藏2删除',
  PRIMARY KEY (`ARTICLE_CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_article_category
-- ----------------------------
INSERT INTO `tb_article_category` VALUES ('1', '科幻', '1', '0');
