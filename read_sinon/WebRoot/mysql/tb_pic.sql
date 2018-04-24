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
DROP TABLE IF EXISTS `tb_pic`;
CREATE TABLE `tb_pic` (
  `PIC_ID` int(10) NOT NULL AUTO_INCREMENT,
  `PIC_CATEGORY_ID` int(10) NOT NULL COMMENT '图片类目ID',
  `PIC_NAME` varchar(55) DEFAULT NULL COMMENT '图片名称',
  `PIC_URL` varchar(555) NOT NULL COMMENT '图片保存路径',
  `USE_TIMES` int(5) DEFAULT 0 COMMENT '使用次数',
  `CREATE_TIME` varchar(35) NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`PIC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

 
