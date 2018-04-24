/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:18:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_bookshelf`
-- ----------------------------
DROP TABLE IF EXISTS `tb_bookshelf`;
CREATE TABLE `tb_bookshelf` (
  `BOOKSHELF_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ARTICLE_ID` int(11) NOT NULL COMMENT '书籍id',
  `USERID` int(11) NOT NULL COMMENT '用户id',
  `USER_ID` int(11) NOT NULL COMMENT '渠道id',
  `ARTICLE_CHAPTER_ID` int(11) NOT NULL COMMENT '章节id（用户阅读的最新章节）',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` varchar(35) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`BOOKSHELF_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT
