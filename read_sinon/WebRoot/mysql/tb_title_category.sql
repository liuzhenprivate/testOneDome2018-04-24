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
-- Table structure for `tb_title_category`
-- ----------------------------
DROP TABLE IF EXISTS `tb_title_category`;
CREATE TABLE `tb_title_category` (
  `TITLE_CATEGORY_ID` int(10) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(55) DEFAULT NULL COMMENT '标题类目名称',
  `SORT` int(10) DEFAULT 0 COMMENT '排序',
  `STATE` int(1) DEFAULT 0 COMMENT '0正常1隐藏2删除',
  PRIMARY KEY (`TITLE_CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

 -- ----------------------------
-- Records of tb_title_category
-- ----------------------------
INSERT INTO `tb_title_category` VALUES ('1', '动漫',0,0);
INSERT INTO `tb_title_category` VALUES ('2', '女生',0,0);
INSERT INTO `tb_title_category` VALUES ('3', '仙侠',0,0);
INSERT INTO `tb_title_category` VALUES ('4', '都市',0,0);
INSERT INTO `tb_title_category` VALUES ('5', '科技',0,0);
INSERT INTO `tb_title_category` VALUES ('6', '历史',0,0);
INSERT INTO `tb_title_category` VALUES ('7', '网游',0,0);
