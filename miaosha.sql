/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50727
 Source Host           : 127.0.0.1:3306
 Source Schema         : miaosha

 Target Server Type    : MySQL
 Target Server Version : 50727
 File Encoding         : 65001

 Date: 10/09/2019 11:42:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for item
-- ----------------------------
DROP TABLE IF EXISTS `item`;
CREATE TABLE `item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `price` double(10,2) NOT NULL DEFAULT '0.00',
  `description` varchar(500) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `sales` int(11) NOT NULL DEFAULT '0',
  `img_url` varchar(500) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of item
-- ----------------------------
BEGIN;
INSERT INTO `item` VALUES (3, 'iphone6s', 3000.00, '苹果手机降价了，赶紧来买吧', 0, 'https://img.pconline.com.cn/images/product/6353/635388/2018zz.jpg');
INSERT INTO `item` VALUES (4, 'iphone6s', 3000.00, '苹果手机降价了，赶紧来买吧', 0, 'https://img.pconline.com.cn/images/product/6353/635388/2018zz.jpg');
INSERT INTO `item` VALUES (5, 'iphone8', 6000.30, '苹果手机8比7好用多了', 0, 'https://img.pconline.com.cn/images/product/6353/635388/2018zz.jpg');
INSERT INTO `item` VALUES (6, 'iphonex', 5000.00, '乞丐版本，每个月都在降价', 12, 'https://img.pconline.com.cn/images/product/6353/635388/2018zz.jpg');
INSERT INTO `item` VALUES (7, 'iphonex max', 6500.00, '性能还不错，值得拥有，有点贵', 1, 'https://img.pconline.com.cn/images/product/6353/635388/2018zz.jpg');
COMMIT;

-- ----------------------------
-- Table structure for item_stock
-- ----------------------------
DROP TABLE IF EXISTS `item_stock`;
CREATE TABLE `item_stock` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `stock` int(11) NOT NULL DEFAULT '0',
  `item_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of item_stock
-- ----------------------------
BEGIN;
INSERT INTO `item_stock` VALUES (4, 30, 3);
INSERT INTO `item_stock` VALUES (5, 29, 4);
INSERT INTO `item_stock` VALUES (6, 80, 5);
INSERT INTO `item_stock` VALUES (7, 9982, 6);
INSERT INTO `item_stock` VALUES (8, 33, 7);
COMMIT;

-- ----------------------------
-- Table structure for order_info
-- ----------------------------
DROP TABLE IF EXISTS `order_info`;
CREATE TABLE `order_info` (
  `id` varchar(32) COLLATE utf8_unicode_ci NOT NULL,
  `user_id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `item_price` double(10,2) NOT NULL,
  `amount` int(255) NOT NULL,
  `order_price` double(10,2) NOT NULL,
  `promo_id` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of order_info
-- ----------------------------
BEGIN;
INSERT INTO `order_info` VALUES ('20190823000000', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000001', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000002', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000003', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000004', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000005', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000006', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000007', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000008', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000009', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000010', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000011', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000012', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000013', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190823000014', 49, 6, 5000.00, 1, 5000.00, 0);
INSERT INTO `order_info` VALUES ('20190828000015', 49, 7, 100.00, 1, 100.00, 1);
COMMIT;

-- ----------------------------
-- Table structure for promo
-- ----------------------------
DROP TABLE IF EXISTS `promo`;
CREATE TABLE `promo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` datetime NOT NULL,
  `promo_name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `item_id` int(11) NOT NULL,
  `promo_item_price` double(255,0) NOT NULL,
  `end_date` datetime NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of promo
-- ----------------------------
BEGIN;
INSERT INTO `promo` VALUES (1, '2019-08-28 11:54:00', 'iphone4抢购活动', 7, 100, '2019-08-28 18:00:00');
COMMIT;

-- ----------------------------
-- Table structure for sequence_info
-- ----------------------------
DROP TABLE IF EXISTS `sequence_info`;
CREATE TABLE `sequence_info` (
  `name` varchar(255) COLLATE utf8_unicode_ci NOT NULL,
  `current_value` int(255) NOT NULL,
  `step` int(255) NOT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of sequence_info
-- ----------------------------
BEGIN;
INSERT INTO `sequence_info` VALUES ('order_info', 16, 1);
COMMIT;

-- ----------------------------
-- Table structure for user_info
-- ----------------------------
DROP TABLE IF EXISTS `user_info`;
CREATE TABLE `user_info` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `gender` tinyint(4) NOT NULL DEFAULT '0',
  `age` int(11) NOT NULL DEFAULT '0',
  `telphone` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `register_mode` varchar(255) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `third_party_id` varchar(64) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `telephone_unique_index` (`telphone`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_info
-- ----------------------------
BEGIN;
INSERT INTO `user_info` VALUES (1, 'jason', 1, 30, '17621912789', 'phone', '123');
INSERT INTO `user_info` VALUES (27, 'longge', 1, 28, '17321912789', 'byphone', '');
INSERT INTO `user_info` VALUES (28, '文德丰', 1, 27, '17621912784', 'byphone', '');
INSERT INTO `user_info` VALUES (29, '潘如意', 1, 28, '17621912782', 'byphone', '');
INSERT INTO `user_info` VALUES (30, '黄帅', 1, 27, '17621912785', 'byphone', '');
INSERT INTO `user_info` VALUES (31, '黄丹丹', 0, 27, '17621912786', 'byphone', '');
INSERT INTO `user_info` VALUES (32, '白会鹏', 1, 28, '17621912783', 'byphone', '');
INSERT INTO `user_info` VALUES (33, '赵亚飞', 1, 27, '17621912282', 'byphone', '');
INSERT INTO `user_info` VALUES (34, '李坤鹏', 1, 27, '17621913709', 'byphone', '');
INSERT INTO `user_info` VALUES (35, '豪豪', 1, 25, '17621911789', 'byphone', '');
INSERT INTO `user_info` VALUES (36, '胡勇', 1, 24, '17621212789', 'byphone', '');
INSERT INTO `user_info` VALUES (37, '小胖', 1, 25, '17621312789', 'byphone', '');
INSERT INTO `user_info` VALUES (38, '忠威', 1, 28, '17621512789', 'byphone', '');
INSERT INTO `user_info` VALUES (39, '俊峰', 1, 27, '17621612789', 'byphone', '');
INSERT INTO `user_info` VALUES (40, '博姐', 0, 22, '17621912780', 'byphone', '');
INSERT INTO `user_info` VALUES (41, '前端', 0, 25, '14621912784', 'byphone', '');
INSERT INTO `user_info` VALUES (43, 'tina', 0, 16, '18838190645', 'byphone', '');
INSERT INTO `user_info` VALUES (45, '234', 1, 23, '219387923874', 'byphone', '');
INSERT INTO `user_info` VALUES (46, 'wo', 1, 18, '32974192374', 'byphone', '');
INSERT INTO `user_info` VALUES (49, '无奈的手机号', 1, 28, '17621912700', 'byphone', '');
COMMIT;

-- ----------------------------
-- Table structure for user_password
-- ----------------------------
DROP TABLE IF EXISTS `user_password`;
CREATE TABLE `user_password` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `encrpt_password` varchar(128) COLLATE utf8_unicode_ci NOT NULL DEFAULT '',
  `user_id` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

-- ----------------------------
-- Records of user_password
-- ----------------------------
BEGIN;
INSERT INTO `user_password` VALUES (1, 'asdlfjasldfj', 1);
INSERT INTO `user_password` VALUES (19, 'xMpCOKC5I4INzFCab3WEmw==', 27);
INSERT INTO `user_password` VALUES (20, 'xMpCOKC5I4INzFCab3WEmw==', 28);
INSERT INTO `user_password` VALUES (21, 'PFncBI6IUCQ76AeaXHTQeQ==', 29);
INSERT INTO `user_password` VALUES (22, 'Wh46Wu3hbUOMOIYsrBp42w==', 30);
INSERT INTO `user_password` VALUES (23, 'VcYBexCpdV7zaBsJzLAelA==', 31);
INSERT INTO `user_password` VALUES (24, '+UOJ7cjpwMwdK8/lKekTaA==', 32);
INSERT INTO `user_password` VALUES (25, '+UOJ7cjpwMwdK8/lKekTaA==', 33);
INSERT INTO `user_password` VALUES (26, 'yXreR0RGo7ayYHL2GCBi9w==', 34);
INSERT INTO `user_password` VALUES (27, 'pg77+1Rc98Yf3mRQU0ggGQ==', 35);
INSERT INTO `user_password` VALUES (28, 'pg77+1Rc98Yf3mRQU0ggGQ==', 36);
INSERT INTO `user_password` VALUES (29, 'pg77+1Rc98Yf3mRQU0ggGQ==', 37);
INSERT INTO `user_password` VALUES (30, 'pg77+1Rc98Yf3mRQU0ggGQ==', 38);
INSERT INTO `user_password` VALUES (31, 'pg77+1Rc98Yf3mRQU0ggGQ==', 39);
INSERT INTO `user_password` VALUES (32, 'pg77+1Rc98Yf3mRQU0ggGQ==', 40);
INSERT INTO `user_password` VALUES (33, 'SElRmvRDRyHkdJCeBl/CLw==', 41);
INSERT INTO `user_password` VALUES (34, 'JQz4tRx3Pz+NyLS+hnqaAg==', 43);
INSERT INTO `user_password` VALUES (35, '5No7f7vOI0XXdysGdKMY1Q==', 45);
INSERT INTO `user_password` VALUES (36, '7MvIfktc4v4oMI/Z8qe68w==', 46);
INSERT INTO `user_password` VALUES (37, 'ICy5YqxZB1uWSwcVLSNLcA==', 49);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
