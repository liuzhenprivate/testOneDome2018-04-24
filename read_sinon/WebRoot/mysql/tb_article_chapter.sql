/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:17:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_article_chapter`
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_chapter`;
CREATE TABLE `tb_article_chapter` (
  `ARTICLE_CHAPTER_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '书籍章节表ID',
  `ARTICLE_ID` int(11) NOT NULL COMMENT '书籍表主键',
  `CHAPTER_NAME` varchar(100) DEFAULT NULL COMMENT '章节名称',
  `CHAPTER_NO` varchar(20) DEFAULT NULL COMMENT '章节序号',
  `CONTENT_URL` varchar(100) DEFAULT NULL COMMENT '章节内容保存路径',
  `IS_FREE` int(11) DEFAULT '0' COMMENT '是否收费(默认0免费 1收费)',
  `CONSUMES` int(11) DEFAULT '0' COMMENT '阅读币',
  PRIMARY KEY (`ARTICLE_CHAPTER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_article_chapter
-- ----------------------------
INSERT INTO `tb_article_chapter` VALUES ('1', '666', '123', '123', '312', '0', '12');
