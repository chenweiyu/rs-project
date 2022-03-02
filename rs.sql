/*
Navicat MySQL Data Transfer

Source Server         : myconnection
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : rs

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2022-02-11 11:18:01
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `leave_staff`
-- ----------------------------
DROP TABLE IF EXISTS `leave_staff`;
CREATE TABLE `leave_staff` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `staff_id` bigint(20) NOT NULL,
  `leave_time` date NOT NULL,
  `leave_type` varchar(50) DEFAULT NULL,
  `leave_go` varchar(100) DEFAULT NULL,
  `is_into` varchar(50) DEFAULT NULL,
  `remark` varchar(400) DEFAULT NULL,
  `staff_name` varchar(20) DEFAULT NULL,
  `section_name` varchar(50) DEFAULT NULL,
  `position_name` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `员工编号` (`staff_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of leave_staff
-- ----------------------------
INSERT INTO `leave_staff` VALUES ('5', '414', '2021-08-12', '退休', '北京', '否', '241234134', 'focza', '技术部', '总经理');
INSERT INTO `leave_staff` VALUES ('6', '443', '2021-07-22', '退休', '2341234', '否', '41234132', 'jtmdb', '技术部', '技术人员');
INSERT INTO `leave_staff` VALUES ('7', '18', '2021-07-29', '退休', '上海', '否', '3241324123', 'syeku', '技术部', '技术人员');
INSERT INTO `leave_staff` VALUES ('10', '150', '2021-09-30', '休假', 'ew2341234', '是', '23412341', 'owfll', '技术部', '助理');
INSERT INTO `leave_staff` VALUES ('16', '409', '2021-09-02', '辞职', '412341234', '是', '412341234132', 'cbeue', '采购部', '架构师');

-- ----------------------------
-- Table structure for `new_staff`
-- ----------------------------
DROP TABLE IF EXISTS `new_staff`;
CREATE TABLE `new_staff` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `staff_id` bigint(10) NOT NULL,
  `join_time` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of new_staff
-- ----------------------------

-- ----------------------------
-- Table structure for `ontrial`
-- ----------------------------
DROP TABLE IF EXISTS `ontrial`;
CREATE TABLE `ontrial` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `status` varchar(20) NOT NULL COMMENT '试用期状态',
  `begin_time` date DEFAULT NULL COMMENT '试用期开始时间',
  `end_time` date DEFAULT NULL COMMENT '试用期结束时间',
  `staff_id` bigint(20) NOT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ontrial
-- ----------------------------
INSERT INTO `ontrial` VALUES ('1', '正常', '2021-07-20', '2021-09-25', '18');
INSERT INTO `ontrial` VALUES ('2', '正常', '2021-07-01', '2022-01-01', '31');
INSERT INTO `ontrial` VALUES ('3', '正常', '2021-07-20', '2021-11-01', '165');
INSERT INTO `ontrial` VALUES ('4', '延期', '2021-07-20', '2021-10-01', '273');
INSERT INTO `ontrial` VALUES ('5', '正常', '2021-07-25', '2021-11-25', '52');
INSERT INTO `ontrial` VALUES ('6', '延期', '2021-07-20', '2021-09-01', '493');
INSERT INTO `ontrial` VALUES ('7', '正常', '2021-07-20', '2021-11-20', '391');
INSERT INTO `ontrial` VALUES ('8', '延期', '2021-07-30', '2021-10-30', '164');
INSERT INTO `ontrial` VALUES ('9', '不录用', '2021-07-01', '2021-07-20', '408');
INSERT INTO `ontrial` VALUES ('10', '正常', '2021-07-20', '2021-10-08', '443');
INSERT INTO `ontrial` VALUES ('11', '延期', '2021-07-31', '2021-11-30', '414');
INSERT INTO `ontrial` VALUES ('12', '录用', '2021-07-16', '2021-06-01', '150');
INSERT INTO `ontrial` VALUES ('13', '正常', '2021-07-20', '2021-11-01', '294');
INSERT INTO `ontrial` VALUES ('14', '延期', '2021-07-01', '2021-11-01', '460');
INSERT INTO `ontrial` VALUES ('15', '不录用', '2021-07-01', '2021-05-01', '275');
INSERT INTO `ontrial` VALUES ('16', '正常', '2021-07-24', '2021-10-01', '11101');
INSERT INTO `ontrial` VALUES ('17', '正常', '2021-07-14', '2021-10-14', '341324124');
INSERT INTO `ontrial` VALUES ('18', '正常', '2021-07-29', '2021-10-08', '1313');
INSERT INTO `ontrial` VALUES ('19', '正常', '2021-07-21', '2021-07-31', '4234123');
INSERT INTO `ontrial` VALUES ('20', '正常', '2021-07-30', '2021-11-11', '12312341');
INSERT INTO `ontrial` VALUES ('21', '正常', '2021-07-14', '2021-11-11', '4132414');
INSERT INTO `ontrial` VALUES ('22', '正常', '2021-07-07', '2021-10-01', '13241234123');
INSERT INTO `ontrial` VALUES ('23', '正常', '2021-09-08', '2021-09-30', '3243124123');
INSERT INTO `ontrial` VALUES ('24', '正常', '2021-09-02', '2021-09-17', '42341234');
INSERT INTO `ontrial` VALUES ('25', '正常', '2021-09-08', '2021-09-30', '2341324123');

-- ----------------------------
-- Table structure for `position`
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT,
  `position_name` varchar(50) NOT NULL,
  `position_num` varchar(20) NOT NULL,
  `position_type` varchar(50) DEFAULT NULL,
  `position_describe` varchar(200) DEFAULT NULL,
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES ('1', '总经理', '001', '岗位', '高层管理', '2021-07-20 00:00:00');
INSERT INTO `position` VALUES ('2', '技术人员', '002', '岗位', '提供技术', '2021-07-07 00:00:00');
INSERT INTO `position` VALUES ('3', '助理', '003', '岗位', '听从上级', '2021-07-01 00:00:00');
INSERT INTO `position` VALUES ('4', '架构师', '004', '岗位', '设计架构', '2021-06-01 00:00:00');

-- ----------------------------
-- Table structure for `section`
-- ----------------------------
DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `section_place` varchar(50) NOT NULL,
  `section_num` varchar(50) NOT NULL,
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '部门id',
  `section_name` varchar(100) NOT NULL COMMENT '部门名称',
  `commit_time` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of section
-- ----------------------------
INSERT INTO `section` VALUES ('五楼507', '001', '1', '推销部门', '2021-07-20 00:00:00');
INSERT INTO `section` VALUES ('二楼218', '002', '2', '技术部', '2021-07-15 00:00:00');
INSERT INTO `section` VALUES ('三楼356', '003', '3', '人事部', '2021-07-07 00:00:00');
INSERT INTO `section` VALUES ('一楼127', '004', '4', '采购部', '2021-07-01 00:00:00');

-- ----------------------------
-- Table structure for `staff`
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff` (
  `id` bigint(10) unsigned NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `staff_id` bigint(10) NOT NULL COMMENT '职工编号',
  `staff_name` varchar(50) NOT NULL,
  `sex` varchar(20) DEFAULT NULL COMMENT '职位id',
  `position_id` bigint(10) NOT NULL,
  `staff_phone` varchar(20) DEFAULT NULL,
  `section_id` bigint(10) NOT NULL COMMENT '部门id',
  `into_time` date NOT NULL COMMENT '入职日期',
  `birthday` date NOT NULL,
  `identity` varchar(20) NOT NULL,
  `attend_time` date NOT NULL COMMENT '参加工作日期',
  `work_form` varchar(50) NOT NULL COMMENT '用工形式',
  `people_source` varchar(50) NOT NULL COMMENT '人员来源',
  `political_outlook` varchar(50) DEFAULT NULL COMMENT '政治面貌',
  `nation` varchar(50) DEFAULT NULL COMMENT '民族',
  `native_place` varchar(50) DEFAULT NULL COMMENT '籍贯',
  `email` varchar(50) DEFAULT NULL,
  `height` double(20,0) DEFAULT NULL,
  `blood_type` varchar(20) DEFAULT NULL COMMENT '血型',
  `marital_status` varchar(20) DEFAULT NULL COMMENT '婚姻状况',
  `birth_place` varchar(100) DEFAULT NULL COMMENT '出生地',
  `registered_residence` varchar(100) DEFAULT NULL COMMENT '户口所在地',
  `highest_education` varchar(20) DEFAULT NULL COMMENT '最高学历',
  `highest_degree` varchar(20) DEFAULT NULL COMMENT '最高学位',
  `graduate_school` varchar(100) DEFAULT NULL COMMENT '毕业院校',
  `major` varchar(100) DEFAULT NULL COMMENT '所学专业',
  `graduate_time` date DEFAULT NULL COMMENT '毕业日期',
  `is_ontrial` varchar(10) NOT NULL COMMENT '是否有试用期',
  `avatar` varchar(255) DEFAULT NULL COMMENT '图片路径',
  PRIMARY KEY (`id`),
  UNIQUE KEY `员工编号` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES ('12', '493', 'sudox', 'irwpv', '4', 'gfebs', '3', '2021-03-04', '1989-07-23', '7896896789', '2021-03-04', '正式员工', '社会招聘', null, null, null, null, null, null, null, null, null, '本科', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('13', '391', 'trvwj', 'ahmpy', '4', 'gjghr', '1', '2021-04-07', '1990-08-29', '87068', '2021-04-07', '正式员工', '社会招聘', null, null, null, null, null, null, null, null, null, '研究生', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('14', '164', 'orshd', 'eojtn', '2', 'xobtf', '2', '2021-03-09', '1991-09-30', '6895685', '2021-03-09', '正式员工', '社会招聘', null, null, null, null, null, null, null, null, null, '本科', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('15', '408', 'xdmqx', 'uuqqh', '2', 'wpjic', '2', '2021-06-08', '1989-08-12', '5787694', '2021-06-08', '正式员工', '校园招聘', null, null, null, null, null, null, null, null, null, '研究生', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('17', '276', 'gkqvk', 'xlbbi', '4', 'dtggs', '4', '2021-05-21', '1995-08-19', '423234', '2021-05-21', '正式员工', '校园招聘', null, null, null, null, null, null, null, null, null, '研究生', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('18', '211', 'npzal', 'emesx', '2', 'pvirt', '2', '2021-08-03', '1993-05-12', '46688', '2021-08-03', '临时员工', '校园招聘', null, null, null, null, null, null, null, null, null, '大专', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('19', '387', 'nghol', 'sikao', '3', 'xbzcl', '1', '2021-08-11', '1992-12-15', '796898', '2021-08-11', '临时员工', '校园招聘', null, null, null, null, null, null, null, null, null, '大专', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('20', '366', 'kzvbi', 'vshpl', '1', 'pclow', '3', '2021-09-17', '1989-11-18', '9657657', '2021-09-17', '正式员工', '校园招聘', null, null, null, null, null, null, null, null, null, '大专', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('22', '275', 'dpxkg', 'lgfer', '4', 'lkqmi', '2', '2021-05-18', '1992-12-10', '43635635', '2021-05-18', '临时员工', '校园招聘', null, null, null, null, null, null, null, null, null, '大专', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('23', '460', 'rfrhr', 'cuuit', '4', 'vzyuo', '2', '2021-07-18', '1993-05-19', '56464', '2021-07-18', '正式员工', '校园招聘', null, null, null, null, null, null, null, null, null, '本科', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('27', '494', 'tmecp', 'mwbjy', '2', 'qxxrq', '4', '2021-06-17', '1980-07-19', '12335', '2021-06-17', '正式员工', '其它', null, null, null, null, null, null, null, null, null, '本科', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('32', '155', 'lqayw', 'rbvcl', '4', 'vyphu', '1', '2021-04-27', '1982-06-18', '543254', '2021-04-27', '正式员工', '其它', null, null, null, null, null, null, null, null, null, '本科', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('33', '91', 'tpjrk', 'fkuru', '2', 'fwyjx', '1', '2021-08-13', '1989-08-23', '3426', '2021-08-13', '临时员工', '其它', null, null, null, null, null, null, null, null, null, '本科', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('34', '341', 'hztfi', 'rtxxp', '4', 'eptgf', '3', '2021-09-25', '1982-08-18', '685', '2021-09-25', '正式员工', '其它', null, null, null, null, null, null, null, null, null, '本科', null, null, null, null, '否', null);
INSERT INTO `staff` VALUES ('35', '1111', '1111', '男', '3', '1111', '3', '2021-07-24', '1995-07-30', '1111', '2021-07-24', '正式员工', '其它', '', '', null, '', null, '', '', '', '', '本科', '学士', '', '', null, '否', null);
INSERT INTO `staff` VALUES ('36', '1110', '唐家玺', '男', '2', '4143124124', '2', '2021-07-24', '1989-07-30', '123124135353451311', '2021-07-28', '正式员工', '校园招聘', '团员', '汉族', '四川省成都市', '123@qq.com', '176', 'A 型', '未婚', '四川省成都市', '四川省成都市', '本科', '学士', '中山电科大', '计算机专业', '2022-07-23', '是', null);
INSERT INTO `staff` VALUES ('38', '11101', '唐家玺', '男', '2', '4143124124', '2', '2021-07-24', '1986-07-30', '123124135353451311', '2021-07-28', '正式员工', '校园招聘', '团员', '汉族', '四川省成都市', '123@qq.com', '176', 'A 型', '未婚', '四川省成都市', '四川省成都市', '本科', '学士', '中山电科大', '计算机专业', '2022-07-23', '是', null);
INSERT INTO `staff` VALUES ('40', '123312', '13123', '男', '2', '43424', '1', '2021-07-05', '1992-07-06', '313143412341234', '2021-06-29', '临时员工', '其它', '', '', null, '', null, '', '', '', '', '高中及以下', '学士', '', '', null, '否', null);
INSERT INTO `staff` VALUES ('41', '12331', '13123', '男', '2', '43424', '1', '2021-07-05', '1987-07-06', '313143412341234', '2021-06-29', '临时员工', '其它', '', '', null, '', null, '', '', '', '', '高中及以下', '学士', '', '', null, '否', null);
INSERT INTO `staff` VALUES ('42', '31341324', '13412', '男', '2', '23414314', '1', '2021-06-28', '1990-05-20', '2341234143', '2021-06-28', '正式员工', '校园招聘', '', '', null, '', null, '', '', '', '', '高中及以下', '无学位', '', '', null, '否', null);
INSERT INTO `staff` VALUES ('43', '431412341', '341241', '男', '2', '12341234', '1', '2021-06-28', '1996-08-28', '42423414342341', '2021-06-29', '正式员工', '校园招聘', '', '', null, '', null, '', '', '', '', '大专', '学士', '', '', null, '否', null);
INSERT INTO `staff` VALUES ('44', '43141234', '341241', '男', '2', '12341234', '1', '2021-06-28', '1989-06-28', '42423414342341', '2021-06-29', '正式员工', '校园招聘', '', '', null, '', null, '', '', '', '', '大专', '学士', '', '', null, '否', null);
INSERT INTO `staff` VALUES ('47', '13214123', '13412', '男', '1', '3412341', '1', '2021-06-29', '1997-06-29', '4123412341', '2021-06-27', '临时员工', '社会招聘', '', '', null, '', null, '', '', '', '', '高中及以下', '无学位', '', '', null, '否', 'upload/avatar/1627260572064.jpg');
INSERT INTO `staff` VALUES ('48', '13412414', '2341', '男', '2', '4213414', '1', '2021-06-28', '1985-06-28', '413413412341234', '2021-06-29', '正式员工', '社会招聘', '', '', null, '', null, '', '', '', '', '大专', '学士', '', '', null, '否', 'upload/avatar/1627572246270.jpg');
INSERT INTO `staff` VALUES ('50', '12431414134', '2341', '男', '2', '13241222', '1', '2021-07-06', '1999-07-06', '413241234123412341', '2021-07-04', '正式员工', '校园招聘', '', '', null, '', null, '', '', '', '', '本科', '双学士', '', '', null, '否', 'upload/avatar/1627572229458.jpg');
INSERT INTO `staff` VALUES ('54', '1313', '风格VS安', '男', '2', '341414', '1', '2021-06-28', '2021-07-08', '1232313412341', '2021-06-29', '正式员工', '校园招聘', '团员', '汉族', '4123413241', '123@qq.com', '176', 'B 型', '未婚', '4312412314', '2134131431', '本科', '学士', '而法国v个', '2124134', '2021-07-23', '是', 'upload/avatar/1627608026268.jpg');
INSERT INTO `staff` VALUES ('55', '4234123', '4134123', '男', '2', '123413411', '1', '2021-07-12', '2021-07-06', '234123412342341', '2021-07-05', '临时员工', '校园招聘', '', '', null, '', null, '', '', '', '', '高中及以下', '学士', '', '', null, '是', 'upload/avatar/1627609033103.jpg');
INSERT INTO `staff` VALUES ('56', '12312341', '234124', '男', '2', '341234124', '2', '2021-07-05', '2021-07-08', '12412341324', '2021-07-05', '正式员工', '社会招聘', '', '', null, '', null, '', '', '', '', '本科', '学士', '', '', null, '是', null);
INSERT INTO `staff` VALUES ('57', '4132414', '12412', '男', '1', '4123414', '2', '2021-07-06', '2021-07-08', '412341234123', '2021-07-12', '正式员工', '社会招聘', '', '', null, '', null, '', '', '', '', '本科', '学士', '', '', null, '是', 'blob:http://localhost:8081/167992af-c99c-4d3b-a4a1-840ab5d26c66');
INSERT INTO `staff` VALUES ('58', '13241234123', '41241234', '男', '2', '12341234', '1', '2021-07-06', '2021-07-07', '234123414', '2021-07-06', '正式员工', '校园招聘', '', '', null, '', null, '', '', '', '', '本科', '学士', '', '', null, '是', 'upload/avatar/1630506477622.jpg');
INSERT INTO `staff` VALUES ('59', '3243124123', '412341234', '男', '1', '412341234', '1', '2021-09-14', '2021-09-08', '21341234123', '2021-09-15', '正式员工', '社会招聘', '', '', null, '', null, '', '', '', '', '高中及以下', '无学位', '', '', null, '是', 'upload/avatar/1630506453840.jpg');
INSERT INTO `staff` VALUES ('60', '42341234', '231412341', '男', '1', '23412341234', '1', '2021-09-02', '2021-09-02', '34123412341', '2021-09-01', '临时员工', '校园招聘', '', '', null, '', null, '', '', '', '', '高中及以下', '无学位', '', '', null, '是', null);
INSERT INTO `staff` VALUES ('61', '2341324123', '4123412341', '男', '2', '412341241', '1', '2021-09-07', '2021-09-02', '431241241', '2021-09-02', '正式员工', '社会招聘', '', '', null, '', null, '', '', '', '', '大专', '无学位', '', '', null, '是', 'upload/avatar/1630589405035.jpg');

-- ----------------------------
-- Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_perms` varchar(2000) DEFAULT NULL COMMENT '角色权限标志码',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('9', '部门主管角色', 'xs;xs;xs;xs;xs', '41234134');
INSERT INTO `sys_role` VALUES ('10', '人事资源管理角色', '\"yx;yx;xyx\"', '123412341 234');
INSERT INTO `sys_role` VALUES ('11', '普通角色', 'js;js;js;', '2341341241');
INSERT INTO `sys_role` VALUES ('12', '超级管理员角色', 'bjz;bjzj;zjbz;', '3333');
INSERT INTO `sys_role` VALUES ('13', '423412341', '', '4213412');

-- ----------------------------
-- Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `UK_USERNAME` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('36', 'admin', '1111111', 'e10adc3949ba59abbe56e057f20f883e', '2021-07-08', '2021-07-22 15:21:16');
INSERT INTO `sys_user` VALUES ('37', '1111', '41234141', 'd1488e742a1629bfb84d6b3fbf3551cd', '2021-07-01', '2021-07-28 20:55:18');
INSERT INTO `sys_user` VALUES ('38', '21341234', '132412341', '62f8928975f8bd615b73ffd9b3c49dcd', '2021-07-08', '2021-07-30 23:23:25');

-- ----------------------------
-- Table structure for `sys_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('97', '36', '12');
INSERT INTO `sys_user_role` VALUES ('98', '36', '10');
INSERT INTO `sys_user_role` VALUES ('99', '37', '12');
INSERT INTO `sys_user_role` VALUES ('100', '37', '11');
INSERT INTO `sys_user_role` VALUES ('104', '38', '9');
INSERT INTO `sys_user_role` VALUES ('105', '38', '10');

-- ----------------------------
-- Table structure for `transfer_position`
-- ----------------------------
DROP TABLE IF EXISTS `transfer_position`;
CREATE TABLE `transfer_position` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `position_after` varchar(100) NOT NULL,
  `position_name` varchar(100) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `transfer_time` date NOT NULL,
  `transfer_type` varchar(50) DEFAULT NULL,
  `staff_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `员工编号` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transfer_position
-- ----------------------------
INSERT INTO `transfer_position` VALUES ('15', '总经理', '助理', '341234', '21341234', '2021-07-29', '升职', '414');
INSERT INTO `transfer_position` VALUES ('17', '技术人员', '总经理', '23124124', '132412341', '2021-07-29', '降职', '443');
INSERT INTO `transfer_position` VALUES ('18', '助理', '总经理', '2412341', '132412341', '2021-07-29', '降职', '273');
INSERT INTO `transfer_position` VALUES ('19', '助理', '技术人员', '42342134123', '', '2021-09-24', '升职', '165');
INSERT INTO `transfer_position` VALUES ('20', '技术人员', '总经理', '3421234124', '12341234', '2021-09-02', '升职', '164');

-- ----------------------------
-- Table structure for `transfer_section`
-- ----------------------------
DROP TABLE IF EXISTS `transfer_section`;
CREATE TABLE `transfer_section` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `section_after` varchar(50) NOT NULL,
  `reason` varchar(200) NOT NULL,
  `remark` varchar(200) DEFAULT NULL,
  `begin_time` date NOT NULL,
  `end_time` date DEFAULT NULL,
  `staff_id` bigint(20) NOT NULL,
  `section_name` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of transfer_section
-- ----------------------------
INSERT INTO `transfer_section` VALUES ('8', '采购部', '3241341', '32412341', '2021-07-29', '2023-07-13', '273', '人事部');
INSERT INTO `transfer_section` VALUES ('9', '技术部', '213124312', '34123412', '2021-07-30', '2021-11-25', '443', '推销部门');
INSERT INTO `transfer_section` VALUES ('10', '人事部', '213123134', '', '2021-09-02', '2021-09-24', '165', '技术部');
INSERT INTO `transfer_section` VALUES ('11', '技术部', '323123', '12412341', '2021-09-02', '2021-09-30', '164', '人事部');
INSERT INTO `transfer_section` VALUES ('12', '人事部', '41234123', '412341234', '2021-09-02', '2021-09-30', '493', '技术部');
