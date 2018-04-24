/*
Navicat MySQL Data Transfer

Source Server         : sinon_lz
Source Server Version : 50173
Source Host           : 60.191.88.84:3306
Source Database       : reading

Target Server Type    : MYSQL
Target Server Version : 50173
File Encoding         : 65001

Date: 2017-12-05 17:17:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `category_label`
-- ----------------------------
DROP TABLE IF EXISTS `category_label`;
CREATE TABLE `category_label` (
  `CATEGORYLABEL_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `LABEL_ID` int(11) NOT NULL COMMENT '标签表主键id',
  `LABEL_CATEGORY_ID` int(11) NOT NULL COMMENT '标签分类表主键',
  PRIMARY KEY (`CATEGORYLABEL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='标签表和标签分类表的中间表';

-- ----------------------------
-- Records of category_label
-- ----------------------------
INSERT INTO `category_label` VALUES ('1', '1', '3');
INSERT INTO `category_label` VALUES ('2', '2', '4');
INSERT INTO `category_label` VALUES ('8', '3', '7');
INSERT INTO `category_label` VALUES ('9', '1', '3');
INSERT INTO `category_label` VALUES ('10', '2', '7');
INSERT INTO `category_label` VALUES ('11', '3', '5');
INSERT INTO `category_label` VALUES ('12', '4', '4');
INSERT INTO `category_label` VALUES ('13', '5', '1');
