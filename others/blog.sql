/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2019-06-16 23:51:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `exchange_list`
-- ----------------------------
DROP TABLE IF EXISTS `exchange_list`;
CREATE TABLE `exchange_list` (
  `id` int(5) NOT NULL,
  `Date` date DEFAULT NULL,
  `time` time DEFAULT NULL,
  `buy_count` int(11) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of exchange_list
-- ----------------------------
INSERT INTO `exchange_list` VALUES ('1', '2019-06-16', '13:58:24', '12', 'admin', 'admin', 'admin');

-- ----------------------------
-- Table structure for `goods`
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `category` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `stock` int(3) DEFAULT NULL,
  `price` float(10,0) DEFAULT NULL,
  `PIC` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES ('1', '海鲜', '大闸蟹', '587', '98', 'https://g-search3.alicdn.com/img/bao/uploaded/i4/i4/3256293296/O1CN01AWea5j1aDbyDfcwoK_!!0-item_pic.jpg_250x250.jpg_.webp');
INSERT INTO `goods` VALUES ('2', '水果', '青苹果', '1000', '30', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/i3/2485762378/O1CN01Oew9LE1TRANHwFzkn_!!0-item_pic.jpg_250x250.jpg_.webp');
INSERT INTO `goods` VALUES ('3', '海鲜', '鲍鱼', '10', '100', 'https://g-search1.alicdn.com/img/bao/uploaded/i4/i3/1579528508/TB2.TgkDmBYBeNjy0FeXXbnmFXa_!!1579528508-0-item_pic.jpg_250x250.jpg_.webp');
INSERT INTO `goods` VALUES ('4', '海鲜', '龙虾', '120', '40', 'https://g-search2.alicdn.com/img/bao/uploaded/i4/i1/2290606715/O1CN01qfC1WQ1zTW6LhD3yB_!!0-item_pic.jpg_250x250.jpg_.webp');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `group` int(5) NOT NULL,
  `name` varchar(20) NOT NULL,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `mobile` varchar(15) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', 'admin', 'admin', 'admin', '12345678912');
INSERT INTO `user` VALUES ('2', '1', 'root', 'root', 'root', '78945612378');
INSERT INTO `user` VALUES ('3', '2', '123', '123', '123', '14785236987');
