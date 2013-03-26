/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50523
Source Host           : localhost:3306
Source Database       : springtest

Target Server Type    : MYSQL
Target Server Version : 50523
File Encoding         : 65001

Date: 2012-05-12 16:52:24
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `account`
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `status` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of account
-- ----------------------------
INSERT INTO `account` VALUES ('1', 'admin', 'admin', '1');

-- ----------------------------
-- Table structure for `dept`
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept` (
  `deptid` bigint(10) NOT NULL AUTO_INCREMENT,
  `dname` varchar(40) DEFAULT NULL,
  PRIMARY KEY (`deptid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES ('1', '行政部');
INSERT INTO `dept` VALUES ('2', '开发部');
INSERT INTO `dept` VALUES ('3', '销售部');
INSERT INTO `dept` VALUES ('4', '企划部');

-- ----------------------------
-- Table structure for `emp`
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp` (
  `empid` bigint(10) NOT NULL AUTO_INCREMENT,
  `ename` varchar(40) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `dname` bigint(10) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `createdate` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`empid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES ('1', 'wangxiaoxu', 'F', '2', '15000814267', null);
INSERT INTO `emp` VALUES ('2', '张三', 'F', '3', '15000814266', null);

-- ----------------------------
-- Table structure for `outkeytb`
-- ----------------------------
DROP TABLE IF EXISTS `outkeytb`;
CREATE TABLE `outkeytb` (
  `outkeyid` bigint(6) NOT NULL AUTO_INCREMENT,
  `outkeyname` varchar(30) DEFAULT NULL,
  `outkeytable` varchar(30) DEFAULT NULL,
  `outkeysql` varchar(500) DEFAULT NULL,
  `outkeydesc` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`outkeyid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of outkeytb
-- ----------------------------
INSERT INTO `outkeytb` VALUES ('1', 'parentnode', 'treenode', 'select nodeid,text from treenode where leaf=\'0\'', '目录节点');
INSERT INTO `outkeytb` VALUES ('2', 'dept', 'dept', 'select deptid,dname from dept', '部门');

-- ----------------------------
-- Table structure for `treenode`
-- ----------------------------
DROP TABLE IF EXISTS `treenode`;
CREATE TABLE `treenode` (
  `nodeid` bigint(6) NOT NULL AUTO_INCREMENT,
  `text` varchar(40) DEFAULT NULL,
  `leaf` varchar(20) DEFAULT NULL,
  `href` varchar(40) DEFAULT NULL,
  `parentid` bigint(6) DEFAULT NULL,
  PRIMARY KEY (`nodeid`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of treenode
-- ----------------------------
INSERT INTO `treenode` VALUES ('1', 'root', '0', null, null);
INSERT INTO `treenode` VALUES ('2', '菜单管理', '1', 'accountMng/addnode.jsp', '1');
INSERT INTO `treenode` VALUES ('3', 'empManager', '0', '', '1');
INSERT INTO `treenode` VALUES ('4', '添加员工', '1', 'accountMng/addaccount.jsp', '3');
INSERT INTO `treenode` VALUES ('5', '员工信息列表', '1', 'accountMng/accountList.jsp', '3');
