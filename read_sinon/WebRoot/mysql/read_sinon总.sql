/*
Navicat MySQL Data Transfer

Source Server         : lz_sinon
Source Server Version : 50525
Source Host           : localhost:3306
Source Database       : read_sinon

Target Server Type    : MYSQL
Target Server Version : 50525
File Encoding         : 65001

Date: 2018-01-09 17:23:51
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category_label
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

-- ----------------------------
-- Table structure for module_config
-- ----------------------------
DROP TABLE IF EXISTS `module_config`;
CREATE TABLE `module_config` (
  `MOULE_CONFIG_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `THEME` varchar(255) DEFAULT NULL COMMENT '主题名称',
  `THEME_TYPE` int(11) DEFAULT '0' COMMENT '主题类型',
  `STATE` int(11) DEFAULT '0' COMMENT '状态 默认0显示  1删除',
  `CONTENT` varchar(255) DEFAULT NULL COMMENT '主题内容',
  `PIC_URL` varchar(255) DEFAULT NULL COMMENT '主题图片地址',
  `THEME_URL` varchar(255) DEFAULT NULL COMMENT '主题链接',
  `BEGIN_DATE` varchar(255) DEFAULT NULL COMMENT '主题开始有效期',
  `END_TIME` varchar(255) DEFAULT NULL COMMENT '主题结束有效期',
  `CREATE_TIME` varchar(255) DEFAULT NULL COMMENT '创建日期',
  `DATA_TYPR` int(11) DEFAULT '0' COMMENT '默认0   ， 1书籍  2读者',
  PRIMARY KEY (`MOULE_CONFIG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of module_config
-- ----------------------------

-- ----------------------------
-- Table structure for module_config_info
-- ----------------------------
DROP TABLE IF EXISTS `module_config_info`;
CREATE TABLE `module_config_info` (
  `MOULE_CONFID_INFO_ID` int(11) NOT NULL COMMENT 'ID',
  `MOULE_CONFIG_ID` int(11) NOT NULL COMMENT '主题主键id',
  `MOULE_ID` int(11) NOT NULL COMMENT '主题详情id',
  `SORT_ID` int(11) DEFAULT NULL COMMENT '排序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module_config_info
-- ----------------------------

-- ----------------------------
-- Table structure for noticelog
-- ----------------------------
DROP TABLE IF EXISTS `noticelog`;
CREATE TABLE `noticelog` (
  `NOTICELOG_ID` int(11) NOT NULL AUTO_INCREMENT,
  `TITLE` varchar(35) NOT NULL COMMENT '公告标题',
  `CONTENT` varchar(1000) NOT NULL COMMENT '公告内容',
  `STATE` int(1) NOT NULL COMMENT '状态0等待推送，1已删除，2取消推送，3已推送',
  `SEND_TIME` varchar(34) DEFAULT NULL COMMENT '推送时间默认为0 立即推送',
  `ADMIN_ID` varchar(35) DEFAULT NULL COMMENT '管理员ID',
  `CTIME` varchar(35) NOT NULL COMMENT '创建时间',
  `PUSHMODE` int(11) DEFAULT '1' COMMENT '推送方式（1：立即推送  2：定时推送）',
  PRIMARY KEY (`NOTICELOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of noticelog
-- ----------------------------
INSERT INTO `noticelog` VALUES ('8', '测试1', '测试1', '3', '2017-11-29', '1', '2017-11-28 17:50:07', '1');
INSERT INTO `noticelog` VALUES ('9', '测试', '测试内容', '3', '2017-11-02', '1', '2017-11-29 13:50:54', '1');
INSERT INTO `noticelog` VALUES ('10', '测试', '测试', '1', '2017-11-06', '1', '2017-11-29 13:51:29', '1');

-- ----------------------------
-- Table structure for remittance_log
-- ----------------------------
DROP TABLE IF EXISTS `remittance_log`;
CREATE TABLE `remittance_log` (
  `REMITTANCE_LOG_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `USER_ID` int(11) NOT NULL COMMENT '渠道ID',
  `ACCOUNT_NAME` varchar(55) DEFAULT NULL COMMENT '收款银行卡名字',
  `CARD_NUM` varchar(20) DEFAULT NULL COMMENT '收款卡号',
  `OPEN_CARD` varchar(155) DEFAULT NULL COMMENT '收款开户行',
  `MONEY` int(11) DEFAULT NULL COMMENT '打款数额',
  `CHANNEL_ACCOUNT_NAME` varchar(55) DEFAULT NULL COMMENT '打款银行卡名字',
  `CHANNEL_CARD_NUM` varchar(20) DEFAULT NULL COMMENT '打款卡号',
  `STATE` int(11) DEFAULT NULL COMMENT '收款状态（0等待收款1确认收款2未收款）',
  `MEMO` varchar(5) DEFAULT NULL COMMENT '收款备注',
  `CHECK_TIME` varchar(32) DEFAULT NULL COMMENT '确认收款时间',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`REMITTANCE_LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of remittance_log
-- ----------------------------
INSERT INTO `remittance_log` VALUES ('1', '464646', '121', '12312312', '31231231', '1212', '1212', '21312312', '2', '3123', '1231', '3123');

-- ----------------------------
-- Table structure for sys_app_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_user`;
CREATE TABLE `sys_app_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `PHONE` varchar(100) DEFAULT NULL,
  `SFID` varchar(100) DEFAULT NULL,
  `START_TIME` varchar(100) DEFAULT NULL,
  `END_TIME` varchar(100) DEFAULT NULL,
  `YEARS` int(10) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_app_user
-- ----------------------------
INSERT INTO `sys_app_user` VALUES ('04762c0b28b643939455c7800c2e2412', 'dsfsd', 'f1290186a5d0b1ceab27f4e77c0c5d68', 'w', '', '55896f5ce3c0494fa6850775a4e29ff6', '', '', '1', '', '18766666666', '', '', '', '0', '001', '18766666666@qq.com');
INSERT INTO `sys_app_user` VALUES ('3faac8fe5c0241e593e0f9ea6f2d5870', 'dsfsdf', 'f1290186a5d0b1ceab27f4e77c0c5d68', 'wewe', '', '68f23fc0caee475bae8d52244dea8444', '', '', '1', '', '18767676767', '', '', '', '0', 'wqwe', 'qweqwe@qq.com');

-- ----------------------------
-- Table structure for sys_dictionaries
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionaries`;
CREATE TABLE `sys_dictionaries` (
  `ZD_ID` varchar(100) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `BIANMA` varchar(100) DEFAULT NULL,
  `ORDY_BY` int(10) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `JB` int(10) DEFAULT NULL,
  `P_BM` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ZD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_dictionaries
-- ----------------------------
INSERT INTO `sys_dictionaries` VALUES ('212a6765fddc4430941469e1ec8c8e6c', '人事部', '001', '1', 'c067fdaf51a141aeaa56ed26b70de863', '2', 'BM_001');
INSERT INTO `sys_dictionaries` VALUES ('3cec73a7cc8a4cb79e3f6ccc7fc8858c', '行政部', '002', '2', 'c067fdaf51a141aeaa56ed26b70de863', '2', 'BM_002');
INSERT INTO `sys_dictionaries` VALUES ('48724375640341deb5ef01ac51a89c34', '北京', 'dq001', '1', 'cdba0b5ef20e4fc0a5231fa3e9ae246a', '2', 'DQ_dq001');
INSERT INTO `sys_dictionaries` VALUES ('5a1547632cca449db378fbb9a042b336', '研发部', '004', '4', 'c067fdaf51a141aeaa56ed26b70de863', '2', 'BM_004');
INSERT INTO `sys_dictionaries` VALUES ('7f9cd74e60a140b0aea5095faa95cda3', '财务部', '003', '3', 'c067fdaf51a141aeaa56ed26b70de863', '2', 'BM_003');
INSERT INTO `sys_dictionaries` VALUES ('b861bd1c3aba4934acdb5054dd0d0c6e', '科技不', 'kj', '7', 'c067fdaf51a141aeaa56ed26b70de863', '2', 'BM_kj');
INSERT INTO `sys_dictionaries` VALUES ('c067fdaf51a141aeaa56ed26b70de863', '部门', 'BM', '1', '0', '1', 'BM');
INSERT INTO `sys_dictionaries` VALUES ('cdba0b5ef20e4fc0a5231fa3e9ae246a', '地区', 'DQ', '2', '0', '1', 'DQ');
INSERT INTO `sys_dictionaries` VALUES ('f184bff5081d452489271a1bd57599ed', '上海', 'SH', '2', 'cdba0b5ef20e4fc0a5231fa3e9ae246a', '2', 'DQ_SH');
INSERT INTO `sys_dictionaries` VALUES ('f30bf95e216d4ebb8169ff0c86330b8f', '客服部', '006', '6', 'c067fdaf51a141aeaa56ed26b70de863', '2', 'BM_006');

-- ----------------------------
-- Table structure for sys_gl_qx
-- ----------------------------
DROP TABLE IF EXISTS `sys_gl_qx`;
CREATE TABLE `sys_gl_qx` (
  `GL_ID` varchar(100) NOT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `FX_QX` int(10) DEFAULT NULL,
  `FW_QX` int(10) DEFAULT NULL,
  `QX1` int(10) DEFAULT NULL,
  `QX2` int(10) DEFAULT NULL,
  `QX3` int(10) DEFAULT NULL,
  `QX4` int(10) DEFAULT NULL,
  PRIMARY KEY (`GL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_gl_qx
-- ----------------------------
INSERT INTO `sys_gl_qx` VALUES ('1', '2', '1', '1', '1', '1', '1', '1');
INSERT INTO `sys_gl_qx` VALUES ('2', '1', '0', '0', '1', '1', '1', '1');
INSERT INTO `sys_gl_qx` VALUES ('55896f5ce3c0494fa6850775a4e29ff6', '7', '0', '0', '1', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('68f23fc0caee475bae8d52244dea8444', '7', '0', '0', '1', '1', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('7dfd8d1f7b6245d283217b7e63eec9b2', '1', '1', '1', '1', '0', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('b0c77c29dfa140dc9b14a29c056f824f', '7', '1', '0', '1', '1', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('e74f713314154c35bd7fc98897859fe3', '6', '1', '1', '1', '1', '0', '0');
INSERT INTO `sys_gl_qx` VALUES ('f944a9df72634249bbcb8cb73b0c9b86', '7', '1', '1', '1', '1', '0', '0');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `MENU_ID` int(11) NOT NULL,
  `MENU_NAME` varchar(255) DEFAULT NULL,
  `MENU_URL` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `MENU_ORDER` varchar(100) DEFAULT NULL,
  `MENU_ICON` varchar(30) DEFAULT NULL,
  `MENU_TYPE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '系统管理', '#', '0', '1', 'publicDeL10', '2');
INSERT INTO `sys_menu` VALUES ('2', '组织管理', 'role.do', '1', '2', null, '2');
INSERT INTO `sys_menu` VALUES ('4', '会员管理', 'happuser/listUsers.do', '1', '4', null, '2');
INSERT INTO `sys_menu` VALUES ('5', '系统用户', 'user/listUsers.do', '1', '3', null, '2');
INSERT INTO `sys_menu` VALUES ('6', '模板配置', '#', '0', '6', 'publicDeL4', '2');
INSERT INTO `sys_menu` VALUES ('7', '图片库', 'pic/list.do', '6', '1', null, '2');
INSERT INTO `sys_menu` VALUES ('8', '性能监控', 'druid/index.html', '9', '1', null, '1');
INSERT INTO `sys_menu` VALUES ('9', '系统工具', '#', '0', '2', 'publicDeL10', '1');
INSERT INTO `sys_menu` VALUES ('10', '接口测试', 'tool/interfaceTest.do', '9', '2', null, '1');
INSERT INTO `sys_menu` VALUES ('11', '发送邮件', 'tool/goSendEmail.do', '9', '3', null, '1');
INSERT INTO `sys_menu` VALUES ('12', '置二维码', 'tool/goTwoDimensionCode.do', '9', '4', null, '1');
INSERT INTO `sys_menu` VALUES ('13', '多级别树', 'tool/ztree.do', '9', '5', null, '1');
INSERT INTO `sys_menu` VALUES ('14', '地图工具', 'tool/map.do', '9', '6', null, '1');
INSERT INTO `sys_menu` VALUES ('15', '渠道管理', '#', '0', '3', 'publicDeL3', '2');
INSERT INTO `sys_menu` VALUES ('17', '渠道打款', 'remittancelog/list.do', '15', '4', null, '2');
INSERT INTO `sys_menu` VALUES ('18', '渠道列表', 'user/listUsers.do', '15', '3', null, '2');
INSERT INTO `sys_menu` VALUES ('20', '在线管理', 'onlinemanager/list.do', '1', '5', null, '1');
INSERT INTO `sys_menu` VALUES ('22', '标题库', 'title/list.do', '6', '2', null, '2');
INSERT INTO `sys_menu` VALUES ('23', '文章模板', '#', '6', '3', null, '2');
INSERT INTO `sys_menu` VALUES ('24', '引导关注模板', '#', '6', '4', null, '2');
INSERT INTO `sys_menu` VALUES ('25', '充值配置', '#', '0', '5', 'publicDeL5', '2');
INSERT INTO `sys_menu` VALUES ('26', '普通充值', 'rechargeset/list.do', '25', '1', null, '2');
INSERT INTO `sys_menu` VALUES ('27', 'VIP充值', 'rechargeset/listvip.do', '25', '2', null, '2');
INSERT INTO `sys_menu` VALUES ('28', '读者管理', '#', '0', '7', 'publicDeL6', '2');
INSERT INTO `sys_menu` VALUES ('29', '渠道粉丝详情', '#', '28', '1', null, '2');
INSERT INTO `sys_menu` VALUES ('30', '读者详情', '#', '28', '2', null, '2');
INSERT INTO `sys_menu` VALUES ('31', '充值管理', '#', '0', '8', 'publicDeL7', '');
INSERT INTO `sys_menu` VALUES ('32', '消费管理', '#', '0', '9', 'publicDeL8', '2');
INSERT INTO `sys_menu` VALUES ('33', '公告管理', 'noticelog/list.do', '15', '1', null, '2');
INSERT INTO `sys_menu` VALUES ('34', '充值记录', 'rechargelog/list.do', '31', '1', null, '');
INSERT INTO `sys_menu` VALUES ('35', '读者列表', 'wxuser/list.do', '28', '0', null, '2');
INSERT INTO `sys_menu` VALUES ('36', '资源管理', '#', '0', '4', 'publicDeL2', '2');
INSERT INTO `sys_menu` VALUES ('37', '书籍管理', 'article/list.do', '36', '2', null, '2');
INSERT INTO `sys_menu` VALUES ('38', '书籍章节', 'articlechapter/list.do', '36', '3', null, '2');
INSERT INTO `sys_menu` VALUES ('39', '书籍类目', 'articlecategory/list.do', '36', '1', null, '2');
INSERT INTO `sys_menu` VALUES ('40', '标签类目', 'labelcategory/list.do', '36', '4', null, '2');
INSERT INTO `sys_menu` VALUES ('41', '标签管理', 'label/list.do', '36', '5', null, '2');
INSERT INTO `sys_menu` VALUES ('42', '消费记录', 'consumelog/list.do', '32', '1', null, '2');
INSERT INTO `sys_menu` VALUES ('43', '数据统计', '#', '0', '0', 'publicDeL1', '2');
INSERT INTO `sys_menu` VALUES ('44', '公告管理', 'noticelog/list.do', '43', '2', null, '2');
INSERT INTO `sys_menu` VALUES ('45', '平台概况', 'countday/listData.do', '43', '1', null, '2');
INSERT INTO `sys_menu` VALUES ('46', '每月数据统计', 'countmonth/list.do', '43', '3', null, '2');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(100) NOT NULL,
  `ROLE_NAME` varchar(100) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `ADD_QX` varchar(255) DEFAULT NULL,
  `DEL_QX` varchar(255) DEFAULT NULL,
  `EDIT_QX` varchar(255) DEFAULT NULL,
  `CHA_QX` varchar(255) DEFAULT NULL,
  `QX_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', '140737485668342', '0', '1', '1', '1', '1', '1');
INSERT INTO `sys_role` VALUES ('2', '超级管理员', '140737485668342', '1', '4346504184054', '4398042743026', '4398043824118', '4346504216566', '2');
INSERT INTO `sys_role` VALUES ('4', '用户组', '118', '0', '0', '0', '0', '0', null);
INSERT INTO `sys_role` VALUES ('55896f5ce3c0494fa6850775a4e29ff6', '特级会员', '498', '7', '0', '0', '0', '0', '55896f5ce3c0494fa6850775a4e29ff6');
INSERT INTO `sys_role` VALUES ('6', '客户组', '18', '0', '1', '1', '1', '1', null);
INSERT INTO `sys_role` VALUES ('68f23fc0caee475bae8d52244dea8444', '中级会员', '498', '7', '0', '0', '0', '0', '68f23fc0caee475bae8d52244dea8444');
INSERT INTO `sys_role` VALUES ('7', '会员组', '498', '0', '0', '0', '0', '1', null);
INSERT INTO `sys_role` VALUES ('7dfd8d1f7b6245d283217b7e63eec9b2', '渠道商', '140737485668342', '1', '246', '0', '0', '0', '7dfd8d1f7b6245d283217b7e63eec9b2');
INSERT INTO `sys_role` VALUES ('b0c77c29dfa140dc9b14a29c056f824f', '高级会员', '498', '7', '0', '0', '0', '0', 'b0c77c29dfa140dc9b14a29c056f824f');
INSERT INTO `sys_role` VALUES ('e74f713314154c35bd7fc98897859fe3', '黄金客户', '18', '6', '1', '1', '1', '1', 'e74f713314154c35bd7fc98897859fe3');
INSERT INTO `sys_role` VALUES ('f944a9df72634249bbcb8cb73b0c9b86', '初级会员', '498', '7', '1', '1', '1', '1', 'f944a9df72634249bbcb8cb73b0c9b86');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` int(11) NOT NULL AUTO_INCREMENT,
  `USERID` varchar(35) NOT NULL,
  `COMPANY` varchar(35) NOT NULL,
  `STATE` int(1) DEFAULT '0',
  `DIVIDES` int(2) DEFAULT '0',
  `USERS` int(10) DEFAULT '0',
  `RECHARGES` int(10) DEFAULT '0',
  `INCOMES` int(10) DEFAULT '0',
  `CREATE_TIME` varchar(34) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `SKIN` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', '欣网卓信', '0', '0', '0', '0', '0', '2017-10-26 10:19:00', 'admin', 'de41b7fb99201d8334c23c014db35ecd92df81bc', '系统管理员', '1133671055321055258374707980945218933803269864762743594642571294', '1', '2018-01-09 17:04:57', '127.0.0.1', '0', null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('3', '2', '欣网卓信2', '0', '0', '0', '0', '0', null, 'liu', '2b2f2116d4bb3c9b9b078d50d71d7a1bc725174a', '刘', '', '7dfd8d1f7b6245d283217b7e63eec9b2', '', '', '0', '', 'default', '110@qq.com', '110', '13312345678');

-- ----------------------------
-- Table structure for sys_user_qx
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_qx`;
CREATE TABLE `sys_user_qx` (
  `U_ID` varchar(100) NOT NULL,
  `C1` int(10) DEFAULT NULL,
  `C2` int(10) DEFAULT NULL,
  `C3` int(10) DEFAULT NULL,
  `C4` int(10) DEFAULT NULL,
  `Q1` int(10) DEFAULT NULL,
  `Q2` int(10) DEFAULT NULL,
  `Q3` int(10) DEFAULT NULL,
  `Q4` int(10) DEFAULT NULL,
  PRIMARY KEY (`U_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_qx
-- ----------------------------
INSERT INTO `sys_user_qx` VALUES ('1', '1', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('2', '1', '1', '1', '1', '1', '1', '1', '1');
INSERT INTO `sys_user_qx` VALUES ('55896f5ce3c0494fa6850775a4e29ff6', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('68f23fc0caee475bae8d52244dea8444', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('7dfd8d1f7b6245d283217b7e63eec9b2', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('b0c77c29dfa140dc9b14a29c056f824f', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('e74f713314154c35bd7fc98897859fe3', '0', '0', '0', '0', '0', '0', '0', '0');
INSERT INTO `sys_user_qx` VALUES ('f944a9df72634249bbcb8cb73b0c9b86', '0', '0', '0', '0', '0', '0', '0', '0');

-- ----------------------------
-- Table structure for tb_account
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `ACCOUNT_ID` int(11) NOT NULL AUTO_INCREMENT,
  `ACCOUNT_NAME` varchar(55) DEFAULT NULL COMMENT '银行卡名字',
  `CARD_NUM` varchar(20) DEFAULT NULL COMMENT '卡号',
  `OPEN_CARD` varchar(155) DEFAULT NULL COMMENT '开户行',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_account
-- ----------------------------

-- ----------------------------
-- Table structure for tb_article
-- ----------------------------
DROP TABLE IF EXISTS `tb_article`;
CREATE TABLE `tb_article` (
  `ARTICLE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '书籍id',
  `ARTICLE_CATEGORY_ID` int(11) DEFAULT NULL COMMENT '书籍类目ID',
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
  `BOOK_COVER` varchar(255) DEFAULT NULL COMMENT '书籍封面',
  `RECOMMEND` int(11) DEFAULT '0' COMMENT '推荐指数',
  PRIMARY KEY (`ARTICLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_article
-- ----------------------------
INSERT INTO `tb_article` VALUES ('2', '1', '2', '凡人', '张三', '0', '1', null, '1', '1', '1111', '2', '1', '2017-09-19', '11', '10000', '1', '0', '2/367217ee83f04c069ce793a0704bc961.png', '0');
INSERT INTO `tb_article` VALUES ('3', '1', '3', '吞噬星空', 'aa', '0', '0', null, '1', '5654646', '0', '2', '1', '2017-12-07 16:03:49', '0', '0', '0', '0', '3/f066e40a9270459f930d7dc1e50045a4.jpg', '80');
INSERT INTO `tb_article` VALUES ('4', '1', '4', '星辰变', 'lll', '0', '0', null, '1', '4654656', '0', '0', '1', '2017-12-07 16:20:24', '0', '0', '0', '0', '4/e200b7d8cbc44ac78aa14c039119bbf8.jpg', '80');

-- ----------------------------
-- Table structure for tb_article_category
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

-- ----------------------------
-- Table structure for tb_article_chapter
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
  `CREATE_TIME` varchar(55) DEFAULT NULL COMMENT '添加时间',
  `NUMBER_CHAPTER` int(11) DEFAULT '0' COMMENT '章节字数',
  `CHAPTER_STATE` int(11) DEFAULT '0' COMMENT '章节状态（默认0显示 1隐藏 2已删除）',
  `UPDATE_TIME` varchar(35) DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ARTICLE_CHAPTER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_article_chapter
-- ----------------------------
INSERT INTO `tb_article_chapter` VALUES ('4', '2', '第一章 平凡小山村', '2', '2/4/第一章 平凡小山村.txt', '0', null, '2017-12-27 16:22:21', '0', '0', '2017-12-27 16:21:56');
INSERT INTO `tb_article_chapter` VALUES ('5', '3', '第一章 死亡？重生', '2', '3/5/第一章 死亡？重生.txt', '0', null, '2017-12-27 16:41:44', '784', '0', '2017-12-27 16:21:56');

-- ----------------------------
-- Table structure for tb_article_chapterlog
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_chapterlog`;
CREATE TABLE `tb_article_chapterlog` (
  `ARTICLE_CHAPTERLOG_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ARTICLE_ID` int(11) NOT NULL COMMENT '书籍表主键',
  `ARTICLELOG_ID` int(11) NOT NULL COMMENT '书籍阅读记录表主键',
  `CHAPTER_NAME` varchar(255) DEFAULT NULL COMMENT '章节名称',
  `USERID` int(11) NOT NULL COMMENT '用户表主键',
  `USER_ID` int(11) NOT NULL COMMENT '渠道表主键',
  `WEBCHAT_ID` int(11) NOT NULL COMMENT '微信公众表主键',
  `CREATE_TIME` varchar(32) DEFAULT NULL COMMENT '阅读时间',
  `FEE` int(10) DEFAULT '0' COMMENT '所需阅读币 默认为0免费',
  PRIMARY KEY (`ARTICLE_CHAPTERLOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_article_chapterlog
-- ----------------------------
INSERT INTO `tb_article_chapterlog` VALUES ('1', '2', '0', '第一章 平凡小山村', '1', '1', '0', '2017-12-06 11:25:00', '60');

-- ----------------------------
-- Table structure for tb_article_label
-- ----------------------------
DROP TABLE IF EXISTS `tb_article_label`;
CREATE TABLE `tb_article_label` (
  `ARTICLE_LABEL_ID` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `ARTICLE_ID` int(11) NOT NULL COMMENT '书籍ID',
  `LABEL_ID` int(11) NOT NULL COMMENT '标签ID',
  PRIMARY KEY (`ARTICLE_LABEL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_article_label
-- ----------------------------
INSERT INTO `tb_article_label` VALUES ('5', '2', '1');

-- ----------------------------
-- Table structure for tb_articlelog
-- ----------------------------
DROP TABLE IF EXISTS `tb_articlelog`;
CREATE TABLE `tb_articlelog` (
  `ARTICLELOG_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ARTICLE_ID` int(11) NOT NULL COMMENT '书籍ID',
  `ARTICLE_CHAPTER_ID` int(11) NOT NULL COMMENT '章节ID',
  `USERID` int(11) NOT NULL COMMENT '用户表主键',
  `USER_ID` int(11) NOT NULL COMMENT '渠道表主键',
  `WEBCHAT_ID` int(11) NOT NULL COMMENT '微信公众表主键',
  `CREATE_TIME` varchar(32) DEFAULT NULL COMMENT '阅读时间',
  PRIMARY KEY (`ARTICLELOG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_articlelog
-- ----------------------------

-- ----------------------------
-- Table structure for tb_consumelog
-- ----------------------------
DROP TABLE IF EXISTS `tb_consumelog`;
CREATE TABLE `tb_consumelog` (
  `CONSUMELOG_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USERID` int(11) NOT NULL COMMENT '用户表主键',
  `USER_ID` int(11) NOT NULL COMMENT '渠道表主键',
  `WEBCHAT_ID` int(11) NOT NULL COMMENT '微信公众表主键',
  `ARTICLE_ID` int(11) DEFAULT NULL COMMENT '书籍id',
  `ARTICLE_CHAPTER_ID` int(11) DEFAULT NULL COMMENT '章节表主键id',
  `ARTICLE_NAME` varchar(100) DEFAULT NULL COMMENT '书籍名称',
  `CHAPTER_NAME` varchar(255) DEFAULT NULL COMMENT '章节名称',
  `CREATE_TIME` varchar(32) DEFAULT NULL COMMENT '消费时间',
  `MONEY` int(10) DEFAULT NULL COMMENT '对应人民币',
  `CONSUMES` int(10) DEFAULT NULL COMMENT '扣除阅读币',
  PRIMARY KEY (`CONSUMELOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_consumelog
-- ----------------------------
INSERT INTO `tb_consumelog` VALUES ('1', '6666', '3123', '1323', null, null, null, null, '2017-11-08', '3123', '3123');

-- ----------------------------
-- Table structure for tb_count_day
-- ----------------------------
DROP TABLE IF EXISTS `tb_count_day`;
CREATE TABLE `tb_count_day` (
  `COUNT_DAY_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
  `USER_ID` int(11) NOT NULL COMMENT '渠道ID',
  `WEHCHAT_ID` int(11) NOT NULL COMMENT '微信公众号ID',
  `USERS` int(10) DEFAULT '0' COMMENT '会员总数',
  `RECHARGES` int(10) DEFAULT '0' COMMENT '充值总数',
  `RECHARGETIMES` int(10) DEFAULT '0' COMMENT '充值笔数',
  `RECHARGEUSERS` int(10) DEFAULT '0' COMMENT '充值人数',
  `CONSUMES` int(10) DEFAULT '0' COMMENT '消费阅读币',
  `MONEY` int(10) DEFAULT '0' COMMENT '消费人民币',
  `CONSUMENUMBER` int(10) DEFAULT '0' COMMENT '消费笔数',
  `COUNT_DAY` varchar(255) DEFAULT NULL COMMENT '统计日期',
  `CREATE_TIME` varchar(255) DEFAULT NULL COMMENT '创建日期',
  `UPDATE_TIME` varchar(255) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`COUNT_DAY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_count_day
-- ----------------------------
INSERT INTO `tb_count_day` VALUES ('1', '123456789', '123456789', '1', '200', '1', '1', '70', '200', '1', '2017-11-01', '2017-11-01 00:00:00', '2017-11-01 10:00:00');
INSERT INTO `tb_count_day` VALUES ('2', '6666666', '6666666', '1', '200', '1', '1', '70', '200', '1', '2017-10-01', '2017-10-01 00:00:00', '2017-10-01 10:00:00');

-- ----------------------------
-- Table structure for tb_count_month
-- ----------------------------
DROP TABLE IF EXISTS `tb_count_month`;
CREATE TABLE `tb_count_month` (
  `COUNNT_MONTH_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_ID` int(11) NOT NULL COMMENT '渠道ID',
  `WEHCHAT_ID` int(11) NOT NULL COMMENT '微信公众号ID',
  `USERS` int(10) DEFAULT '0' COMMENT '会员总数',
  `RECHARGES` int(10) DEFAULT '0' COMMENT '充值总数',
  `RECHARGETIMES` int(10) DEFAULT '0' COMMENT '充值笔数',
  `RECHARGEUSERS` int(10) DEFAULT '0' COMMENT '充值人数',
  `CONSUMES` int(10) DEFAULT '0' COMMENT '消费阅读币',
  `MONEY` int(10) DEFAULT '0' COMMENT '消费人民币 默认0 单位分',
  `CONSUMENUMBER` int(10) DEFAULT '0' COMMENT '消费笔数',
  `COUNT_MONTH` varchar(20) DEFAULT NULL COMMENT '统计月份',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建日期',
  `UPDATE_TIME` varchar(35) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`COUNNT_MONTH_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_count_month
-- ----------------------------
INSERT INTO `tb_count_month` VALUES ('1', '123456789', '123456789', '1', '200', '1', '1', '70', '200', '1', '2017-11-01', '2017-11-01 00:00:00\r\n', '2017-11-01 10:00:00\r\n');
INSERT INTO `tb_count_month` VALUES ('3', '123456789', '123456789', '1', '100', '1', '1', '70', '200', '1', '2017-10-01', '2017-10-01 00:00:00', '2017-10-01 00:00:00');

-- ----------------------------
-- Table structure for tb_count_week
-- ----------------------------
DROP TABLE IF EXISTS `tb_count_week`;
CREATE TABLE `tb_count_week` (
  `COUNNT_WEEK_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_ID` int(11) NOT NULL COMMENT '渠道ID',
  `WEHCHAT_ID` int(11) NOT NULL COMMENT '微信公众号ID',
  `USERS` int(10) DEFAULT '0' COMMENT '会员总数',
  `RECHARGES` int(10) DEFAULT '0' COMMENT '充值总数',
  `RECHARGETIMES` int(10) DEFAULT '0' COMMENT '充值笔数',
  `RECHARGEUSERS` int(10) DEFAULT '0' COMMENT '充值人数',
  `CONSUMES` int(10) DEFAULT '0' COMMENT '消费阅读币',
  `MONEY` int(10) DEFAULT '0' COMMENT '消费人民币',
  `CONSUMENUMBER` int(10) DEFAULT '0' COMMENT '消费笔数',
  `START_DATE` varchar(20) DEFAULT NULL COMMENT '开始日期',
  `END_DATE` varchar(20) DEFAULT NULL COMMENT '结束日期',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建日期',
  `UPDATE_TIME` varchar(35) DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`COUNNT_WEEK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_count_week
-- ----------------------------
INSERT INTO `tb_count_week` VALUES ('1', '123456789', '123456789', '1', '200', '1', '1', '70', '200', '1', '2017-10-30', '2017-11-5', '2017-10-30 00:00:00', '2017-11-01 10:00:00');

-- ----------------------------
-- Table structure for tb_label
-- ----------------------------
DROP TABLE IF EXISTS `tb_label`;
CREATE TABLE `tb_label` (
  `LABEL_ID` int(11) NOT NULL AUTO_INCREMENT,
  `LABEL_NAME` varchar(55) DEFAULT NULL COMMENT '标签名称',
  PRIMARY KEY (`LABEL_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_label
-- ----------------------------
INSERT INTO `tb_label` VALUES ('1', '修真');
INSERT INTO `tb_label` VALUES ('2', '升级');
INSERT INTO `tb_label` VALUES ('3', '星球大战');
INSERT INTO `tb_label` VALUES ('4', '官途');
INSERT INTO `tb_label` VALUES ('5', '热血');

-- ----------------------------
-- Table structure for tb_label_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_label_category`;
CREATE TABLE `tb_label_category` (
  `LABEL_CATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(55) DEFAULT NULL COMMENT '标签类目名称',
  PRIMARY KEY (`LABEL_CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_label_category
-- ----------------------------
INSERT INTO `tb_label_category` VALUES ('1', '动漫');
INSERT INTO `tb_label_category` VALUES ('2', '女生');
INSERT INTO `tb_label_category` VALUES ('3', '仙侠');
INSERT INTO `tb_label_category` VALUES ('4', '都市');
INSERT INTO `tb_label_category` VALUES ('5', '科技');
INSERT INTO `tb_label_category` VALUES ('6', '历史');
INSERT INTO `tb_label_category` VALUES ('7', '网游');

-- ----------------------------
-- Table structure for tb_pic
-- ----------------------------
DROP TABLE IF EXISTS `tb_pic`;
CREATE TABLE `tb_pic` (
  `PIC_ID` int(10) NOT NULL AUTO_INCREMENT,
  `PIC_CATEGORY_ID` int(10) NOT NULL COMMENT '图片类目ID',
  `PIC_NAME` varchar(55) DEFAULT NULL COMMENT '图片名称',
  `PIC_URL` varchar(555) NOT NULL COMMENT '图片保存路径',
  `USE_TIMES` int(5) DEFAULT '0' COMMENT '使用次数',
  `CREATE_TIME` varchar(35) NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`PIC_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_pic
-- ----------------------------
INSERT INTO `tb_pic` VALUES ('9', '1', '0414e59a1de448718e65a139028f0171.jpeg', 'uploadFiles/uploadImgs/20180105/0414e59a1de448718e65a139028f0171.jpeg', '0', '2018-01-05 16:41:01');
INSERT INTO `tb_pic` VALUES ('10', '1', 'd3923476b27b4bc0b563c4a5d3713ac3.jpg', 'uploadFiles/uploadImgs/20180105/d3923476b27b4bc0b563c4a5d3713ac3.jpg', '0', '2018-01-05 16:41:02');
INSERT INTO `tb_pic` VALUES ('11', '1', 'fbb8c4af5b154026b8d485bcc9956a23.jpg', 'uploadFiles/uploadImgs/20180105/fbb8c4af5b154026b8d485bcc9956a23.jpg', '0', '2018-01-05 16:41:02');
INSERT INTO `tb_pic` VALUES ('12', '1', '6882c48b21d0483e9dcfeced55c2a6ee.jpg', 'uploadFiles/uploadImgs/20180105/6882c48b21d0483e9dcfeced55c2a6ee.jpg', '0', '2018-01-05 16:41:02');
INSERT INTO `tb_pic` VALUES ('13', '1', 'abec663a1d3a48599dc311b049d2988f.jpg', 'uploadFiles/uploadImgs/20180105/abec663a1d3a48599dc311b049d2988f.jpg', '0', '2018-01-05 16:41:02');
INSERT INTO `tb_pic` VALUES ('14', '1', 'e91b91b1d76d4b029b45101fcc01b80a.jpg', 'uploadFiles/uploadImgs/20180105/e91b91b1d76d4b029b45101fcc01b80a.jpg', '0', '2018-01-05 16:41:02');
INSERT INTO `tb_pic` VALUES ('15', '1', 'a0675470b47e4b0c87a3995fffda96fc.jpg', 'uploadFiles/uploadImgs/20180105/a0675470b47e4b0c87a3995fffda96fc.jpg', '0', '2018-01-05 16:41:02');
INSERT INTO `tb_pic` VALUES ('16', '1', '89afd0b59a844252b532c57481c28cd8.jpg', 'uploadFiles/uploadImgs/20180105/89afd0b59a844252b532c57481c28cd8.jpg', '0', '2018-01-05 16:41:02');

-- ----------------------------
-- Table structure for tb_pic_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_pic_category`;
CREATE TABLE `tb_pic_category` (
  `PIC_CATEGORY_ID` int(10) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(55) DEFAULT NULL COMMENT '图片类目名称',
  `SORT` int(10) DEFAULT '0' COMMENT '排序',
  `STATE` int(1) DEFAULT '0' COMMENT '0正常1隐藏2删除',
  PRIMARY KEY (`PIC_CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_pic_category
-- ----------------------------
INSERT INTO `tb_pic_category` VALUES ('1', '动漫', '0', '0');
INSERT INTO `tb_pic_category` VALUES ('2', '女生', '0', '0');
INSERT INTO `tb_pic_category` VALUES ('3', '仙侠', '0', '0');
INSERT INTO `tb_pic_category` VALUES ('4', '都市', '0', '0');
INSERT INTO `tb_pic_category` VALUES ('5', '科技', '0', '0');
INSERT INTO `tb_pic_category` VALUES ('6', '历史', '0', '0');
INSERT INTO `tb_pic_category` VALUES ('7', '网游', '0', '0');

-- ----------------------------
-- Table structure for tb_pictures
-- ----------------------------
DROP TABLE IF EXISTS `tb_pictures`;
CREATE TABLE `tb_pictures` (
  `PICTURES_ID` varchar(100) NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL COMMENT '标题',
  `NAME` varchar(255) DEFAULT NULL COMMENT '文件名',
  `PATH` varchar(255) DEFAULT NULL COMMENT '路径',
  `CREATETIME` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `MASTER_ID` varchar(255) DEFAULT NULL COMMENT '属于',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`PICTURES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_pictures
-- ----------------------------

-- ----------------------------
-- Table structure for tb_recharge_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_recharge_log`;
CREATE TABLE `tb_recharge_log` (
  `RECHARGE_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USERID` int(11) NOT NULL COMMENT '用户ID',
  `USER_ID` int(11) NOT NULL COMMENT '渠道  默认微信',
  `WEHCHAT_ID` int(11) NOT NULL COMMENT '微信公众号ID',
  `MONEY` int(11) DEFAULT NULL COMMENT '充值金额',
  `BOOK_CURRENCY` int(11) DEFAULT NULL COMMENT '对应书币',
  `BOOK_CURRENCY_GIFT` int(11) DEFAULT NULL COMMENT '赠送的书币',
  `BOOK_CURRENCY_ALL` int(11) DEFAULT NULL COMMENT '总书币',
  `CONSUME` int(11) DEFAULT NULL COMMENT '消费金额',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '充值时间',
  `STATE` int(1) DEFAULT NULL COMMENT '充值状态',
  `RESULT_PAY` varchar(35) DEFAULT NULL COMMENT '支付结果',
  `TIME_PAY` varchar(35) DEFAULT NULL COMMENT '支付完成时间',
  `RESULT_RECHARGE` varchar(135) DEFAULT NULL COMMENT '充值结果',
  `TIME_RECHARGE` varchar(35) DEFAULT NULL COMMENT '充值完成时间',
  `CHANNEL` varchar(35) DEFAULT NULL COMMENT '渠道',
  `CHANNEL_INCOME` int(10) DEFAULT NULL COMMENT '渠道分成',
  `INCOME` int(10) DEFAULT NULL COMMENT '平台收益',
  `UPSTREAM_INCOME` int(10) DEFAULT NULL COMMENT '上游收益',
  `RECHARGE_TYPE` int(11) DEFAULT '0' COMMENT '充值类型(默认0 普通充值 1vip充值)',
  `EXP_DATE` varchar(255) DEFAULT NULL COMMENT '有效期',
  PRIMARY KEY (`RECHARGE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_recharge_log
-- ----------------------------
INSERT INTO `tb_recharge_log` VALUES ('1', '1', '1', '12312', '3123', '3123', '1323', '3123', '123', '2017-11-08', '5', '0', '2017-11-08', '32', '2017-11-08', '12312', '1323', '312', '3123', '0', '2018-12-30');

-- ----------------------------
-- Table structure for tb_recharge_set
-- ----------------------------
DROP TABLE IF EXISTS `tb_recharge_set`;
CREATE TABLE `tb_recharge_set` (
  `RECHARGE_SET_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `RECHARGE_TYPE` int(11) DEFAULT '0' COMMENT '充值类型(默认0 单独包月1连续包月)',
  `FREE_TIME` varchar(32) DEFAULT NULL COMMENT '免费时间段  22:00-6:00',
  `STATE` int(11) DEFAULT NULL COMMENT '状态 默认0正常1隐藏2删除',
  `MONEY` int(11) DEFAULT NULL COMMENT '充值金额',
  `BOOK_CURRENCY` int(11) DEFAULT NULL COMMENT '对应书币',
  `BOOK_CURRENCY_GIFT` int(11) DEFAULT NULL COMMENT '赠送书币',
  `BOOK_CURRENCY_ALL` int(11) DEFAULT NULL COMMENT '总书币',
  `USE_SCOPE` int(11) DEFAULT NULL COMMENT '可用范围',
  `BUY_LIMIT` int(35) DEFAULT '0' COMMENT '购买限制(默认0无限制  1vip读者)',
  `EXP_DATE` varchar(35) DEFAULT NULL COMMENT '有效期',
  `SORT_NUM` int(11) DEFAULT NULL COMMENT '排序',
  `MEMO` varchar(135) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  `UPDATE_TIME` varchar(35) DEFAULT NULL COMMENT '更新时间',
  `RECHARGE_NAME` varchar(35) DEFAULT NULL COMMENT '充值名称',
  PRIMARY KEY (`RECHARGE_SET_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_recharge_set
-- ----------------------------
INSERT INTO `tb_recharge_set` VALUES ('6', '0', '06:00:00-20:00:00', '0', '15', null, null, null, null, '0', '', '5', '畅读卡', '2017-12-21 10:34:09', null, '畅读卡');
INSERT INTO `tb_recharge_set` VALUES ('13', null, null, '0', '1000', '100000', '200', null, '0', '0', '', '10', '', '2017-12-27 16:58:14', null, null);
INSERT INTO `tb_recharge_set` VALUES ('20', null, null, '1', '13', '1300', '1', null, '0', '0', '', '14', '', '2017-12-27 17:04:44', '2017-12-28 14:47:24', null);
INSERT INTO `tb_recharge_set` VALUES ('21', null, null, '0', '13', '1300', '1', null, '0', '0', '', '15', '', '2017-12-27 17:04:47', null, null);

-- ----------------------------
-- Table structure for tb_sign_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_sign_log`;
CREATE TABLE `tb_sign_log` (
  `SIGN_LOG_ID` int(11) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USERID` int(11) NOT NULL COMMENT '用户ID 外键',
  `USER_ID` int(11) NOT NULL COMMENT '渠道',
  `WEHCHAT_ID` int(11) NOT NULL COMMENT '微信公众号ID 外键',
  `LOG_TYPE` int(11) DEFAULT '1' COMMENT '记录类型 默认1每日签到2领取奖励',
  `TIMES` int(11) DEFAULT NULL COMMENT '对应奖励次数  对应奖励设置表的times字段',
  `BOOK_CURRENCY` int(11) DEFAULT NULL COMMENT '获取的书币',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '获取时间',
  `SIGN_MONTH` varchar(25) DEFAULT NULL COMMENT '签到月份  YYYY-MM',
  PRIMARY KEY (`SIGN_LOG_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_sign_log
-- ----------------------------
INSERT INTO `tb_sign_log` VALUES ('1', '1', '1', '312', '1', '1', '52', '2017-01-02 17:11:00', '2018-01');

-- ----------------------------
-- Table structure for tb_sign_set
-- ----------------------------
DROP TABLE IF EXISTS `tb_sign_set`;
CREATE TABLE `tb_sign_set` (
  `SIGN_SET_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `TIMES` int(10) DEFAULT '0' COMMENT '签到天数(默认0位每日签到)',
  `BOOK_CURRENCY` int(10) DEFAULT '0' COMMENT '赠送书币',
  `TYPE` int(1) DEFAULT '0' COMMENT '签到类型(默认0,1为连续签到)',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`SIGN_SET_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_sign_set
-- ----------------------------

-- ----------------------------
-- Table structure for tb_title
-- ----------------------------
DROP TABLE IF EXISTS `tb_title`;
CREATE TABLE `tb_title` (
  `TITLE_ID` int(10) NOT NULL AUTO_INCREMENT,
  `TITLE_CATEGORY_ID` int(10) NOT NULL COMMENT '标题类目ID',
  `TITLE_NAME` varchar(55) DEFAULT NULL COMMENT '标题名称',
  `CONTENT` varchar(2555) NOT NULL COMMENT '标题内容',
  `USE_TIMES` int(5) DEFAULT '0' COMMENT '使用次数',
  `CREATE_TIME` varchar(35) NOT NULL COMMENT '上传时间',
  PRIMARY KEY (`TITLE_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_title
-- ----------------------------
INSERT INTO `tb_title` VALUES ('8', '2', null, '女生平道', '0', '2018-01-05 09:55:43');

-- ----------------------------
-- Table structure for tb_title_category
-- ----------------------------
DROP TABLE IF EXISTS `tb_title_category`;
CREATE TABLE `tb_title_category` (
  `TITLE_CATEGORY_ID` int(10) NOT NULL AUTO_INCREMENT,
  `CATEGORY_NAME` varchar(55) DEFAULT NULL COMMENT '标题类目名称',
  `SORT` int(10) DEFAULT '0' COMMENT '排序',
  `STATE` int(1) DEFAULT '0' COMMENT '0正常1隐藏2删除',
  PRIMARY KEY (`TITLE_CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_title_category
-- ----------------------------
INSERT INTO `tb_title_category` VALUES ('1', '动漫', '0', '0');
INSERT INTO `tb_title_category` VALUES ('2', '女生', '0', '0');
INSERT INTO `tb_title_category` VALUES ('3', '仙侠', '0', '0');
INSERT INTO `tb_title_category` VALUES ('4', '都市', '0', '0');
INSERT INTO `tb_title_category` VALUES ('5', '科技', '0', '0');
INSERT INTO `tb_title_category` VALUES ('6', '历史', '0', '0');
INSERT INTO `tb_title_category` VALUES ('7', '网游', '0', '0');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `USERID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_ID` varchar(20) NOT NULL COMMENT '渠道',
  `WEHCHAT_ID` varchar(255) NOT NULL COMMENT '微信公众号ID  外键',
  `USER_CODE` varchar(10) NOT NULL COMMENT '平台id',
  `OPENID` varchar(255) NOT NULL COMMENT 'OPENID来源',
  `NICKNAME` varchar(255) DEFAULT NULL COMMENT '微信昵称',
  `RECHARGE_MONEY` int(10) DEFAULT '0' COMMENT '累计充值金额',
  `SEX` int(1) DEFAULT NULL COMMENT '性别',
  `PROVINCE` varchar(20) DEFAULT NULL COMMENT '省份',
  `CITY` varchar(35) DEFAULT NULL COMMENT '城市',
  `COUNTRY` varchar(20) DEFAULT NULL COMMENT '国家',
  `HEADIMGURL` varchar(135) DEFAULT NULL COMMENT '微信头像 ',
  `PRIVILEGE` varchar(135) DEFAULT NULL COMMENT '用户特权信息',
  `UNIONID` varchar(135) DEFAULT NULL COMMENT 'UNIONID',
  `CTIME` varchar(35) DEFAULT NULL COMMENT '关注时间',
  `BOOK_CURRENCY` int(10) DEFAULT '0' COMMENT '使用阅读币',
  `LEVEL` varchar(20) DEFAULT NULL COMMENT '等级',
  `CUMULATIVE_CURRENCY` int(11) DEFAULT '0' COMMENT '累计阅读币',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '注册时间',
  `SOURCE` int(11) DEFAULT '0' COMMENT '读者来源（默认 0微信 ）',
  `LOGIN_TIME` varchar(35) DEFAULT NULL COMMENT '最新登陆时间',
  `FOLLOWSTATE` int(11) DEFAULT '0' COMMENT '关注状态 默认 0未关注 1已关注',
  `ISVIP` int(11) DEFAULT '0' COMMENT '是否是vip 默认 0不是 1是',
  `PHONE` int(11) DEFAULT NULL COMMENT '绑定手机号',
  PRIMARY KEY (`USERID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', '1', '2131', '1', '123', '测试1号', '131', '1', '312', '123', '3123', 'uploadFiles/wxuserImage/myPic50.png', '3213', '1231', '2017-11-08', '300', '5', '500', '2017-12-21 17:29:38', '0', null, '0', '0', null);
INSERT INTO `tb_user` VALUES ('2', '3', '2131', '1', '123', '测试2号', '0', '1', '312', '123', '3123', 'uploadFiles/wxuserImage/myPic50.png', '3213', '1231', '2017-11-08', '200', '2', '600', '2017-12-21 17:29:38', '0', null, '0', '1', null);

-- ----------------------------
-- Table structure for tb_webchat
-- ----------------------------
DROP TABLE IF EXISTS `tb_webchat`;
CREATE TABLE `tb_webchat` (
  `WEHCHAT_ID` int(10) unsigned NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `USER_ID` int(11) NOT NULL COMMENT 'SYS_USER表主键（外键）',
  `TYPE` varchar(13) DEFAULT NULL COMMENT '类型',
  `NAME` varchar(255) DEFAULT NULL COMMENT '名称',
  `WEBCHAT_CODE` varchar(35) DEFAULT NULL COMMENT '微信号',
  `APPID` varchar(35) DEFAULT NULL COMMENT 'APPID',
  `APPSECRET` varchar(35) DEFAULT NULL COMMENT '秘钥  AppSecret',
  `SHOP_KEY` varchar(35) DEFAULT NULL COMMENT '商户KEY',
  `MCH_ID` varchar(35) DEFAULT NULL COMMENT '商户ID',
  `ACCESS_TOKEN` varchar(35) DEFAULT NULL COMMENT 'access_token',
  `CREATE_TIME` varchar(35) DEFAULT NULL COMMENT '创建时间',
  `SIGN_SWITCH` int(1) DEFAULT '0' COMMENT '签到开关（默认0开启1关闭）',
  `OPEN_DATE` varchar(31) DEFAULT NULL COMMENT '开启签到日期',
  PRIMARY KEY (`WEHCHAT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of tb_webchat
-- ----------------------------
