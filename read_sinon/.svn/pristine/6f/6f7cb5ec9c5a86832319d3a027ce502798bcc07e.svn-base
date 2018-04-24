/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:17:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_article`
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `ARTICLE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '书籍id',
  `ARTICLE_CATEGORY_ID` int(11) NOT NULL COMMENT '书籍类目ID',
  `ARTICLE_CODE` varchar(10) DEFAULT NULL COMMENT '书籍编号',
  `ARTICLE_NAME` varchar(100) DEFAULT NULL COMMENT '书籍名称',
  `AUTHOR` varchar(50) DEFAULT NULL COMMENT '作者',
  `FEE_TYPE` int(11) DEFAULT '0' COMMENT '付费类型(默认0免费 1付费)',
  `PAY_WAY` int(11) DEFAULT '0' COMMENT '付费方式(默认0阅读币购买阅读1 VIP免费指定章节免费时间段阅读)',
  `PAY_CONSUMES` int(11) DEFAULT '0' COMMENT '总阅读币',
  `IS_HOT` int(11) DEFAULT '0' COMMENT '是否热门(默认0否 1是)',
  `SUMMARY` varchar(300) DEFAULT NULL COMMENT '简介',
  `COUNT_LETTER` int(11) DEFAULT '0' COMMENT '总字数',
  `COUNT_CHAPTERS` int(11) DEFAULT '0' COMMENT '总章节',
  `STATE` int(11) DEFAULT '0' COMMENT '状态(默认0未上架 1已上架 2下架 -1删除)',
  `CREATE_TIME` varchar(34) DEFAULT NULL COMMENT '添加时间',
  `READERS` int(11) DEFAULT '0' COMMENT '阅读人数',
  `COUNT_CONSUMES` int(11) DEFAULT '0' COMMENT '购买书籍总阅读币',
  `CHANNEL_TYPE` int(11) DEFAULT '0' COMMENT '频道  0男频1女频',
  `SERIAL_STATE` int(11) DEFAULT '0' COMMENT '连载状态（0：连载中 1：已完结）',
  PRIMARY KEY (`ARTICLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('2', '1', '1', '测试', '张三', '0', '1', '1000', '1', '1', '1111', '12', '2', '2017-09-19', '11', '10000', '1', '0');
