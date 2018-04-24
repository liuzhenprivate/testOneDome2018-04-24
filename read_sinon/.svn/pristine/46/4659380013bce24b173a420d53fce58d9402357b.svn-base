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
-- Table structure for `TB_HTMLMODLE_DETAIL`
-- ----------------------------
DROP TABLE IF EXISTS `TB_HTMLMODLE_DETAIL`;
CREATE TABLE `TB_HTMLMODLE_DETAIL` (
  `HTMLMODLE_DETAIL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `HTMLMODLE_ID` int(11) NOT NULL COMMENT '页面配置模板ID',
   `TITLE` varchar(135) NOT NULL COMMENT '主标题',
   `SUBHEAD` varchar(135) DEFAULT NULL COMMENT '副标题',
   `CONTENT` varchar(1000) DEFAULT NULL COMMENT '内容',
   `IMG_URL` varchar(255) DEFAULT NULL COMMENT '图片路径',
   `HTML_TYPE` int(1) NOT NULL COMMENT '页面类型1系统页面 2其他',
   `SORT` int(3) NOT NULL COMMENT '排序',
   `ARTICLE_ID` int(11) DEFAULT 0 COMMENT '书籍ID',
   `HTML_URL` varchar(255) DEFAULT NULL COMMENT '页面连接',
  `MEMO` varchar(555) DEFAULT NULL COMMENT '描述说明',
  `CREATE_TIME` varchar(35) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`HTMLMODLE_DETAIL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of noticelog
-- ----------------------------
