/*
 Navicat Premium Data Transfer

 Source Server         : 本地MySQL
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : books

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 06/05/2020 09:21:16
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for remember
-- ----------------------------
DROP TABLE IF EXISTS `remember`;
CREATE TABLE `remember`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '单词',
  `definition` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '翻译',
  `pron` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '音标',
  `create_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of remember
-- ----------------------------
INSERT INTO `remember` VALUES (1, 'abate', 'v. 缓和, 减弱', 'ə\'beɪt', '2020-05-06 09:08:33');
INSERT INTO `remember` VALUES (2, 'abbey', 'n. 修道院，修道院教堂', '\'æbi', '2020-05-06 09:08:58');
INSERT INTO `remember` VALUES (3, 'abbreviation', 'n. 缩写', 'əˌbriːvi\'eɪʃn', '2020-05-06 09:12:48');

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book`  (
  `bo_id` int(11) NOT NULL,
  `bo_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `bo_num` int(11) NOT NULL,
  `bo_img_addr` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`bo_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_book
-- ----------------------------
INSERT INTO `t_book` VALUES (0, '名称', 2, '飒飒大苏打');
INSERT INTO `t_book` VALUES (2, '3333', 33, '手动阀手动阀');

-- ----------------------------
-- Table structure for t_image
-- ----------------------------
DROP TABLE IF EXISTS `t_image`;
CREATE TABLE `t_image`  (
  `im_id` int(11) NOT NULL,
  `im_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `im_addr` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `im_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `im_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`im_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_mark
-- ----------------------------
DROP TABLE IF EXISTS `t_mark`;
CREATE TABLE `t_mark`  (
  `ma_id` int(11) NOT NULL,
  `ma_user_id` int(11) NOT NULL,
  `ma_wo_id` int(11) NOT NULL,
  `ma_bo_id` int(11) NOT NULL,
  `ma_date` datetime(0) NOT NULL,
  PRIMARY KEY (`ma_id`) USING BTREE,
  INDEX `fk_ma_user_id`(`ma_user_id`) USING BTREE,
  INDEX `fk_ma_wo_id`(`ma_wo_id`) USING BTREE,
  INDEX `fk_ma_bo_id`(`ma_bo_id`) USING BTREE,
  CONSTRAINT `fk_ma_bo_id` FOREIGN KEY (`ma_bo_id`) REFERENCES `t_book` (`bo_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_ma_user_id` FOREIGN KEY (`ma_user_id`) REFERENCES `t_user` (`ur_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_ma_wo_id` FOREIGN KEY (`ma_wo_id`) REFERENCES `t_word` (`wo_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_notebook
-- ----------------------------
DROP TABLE IF EXISTS `t_notebook`;
CREATE TABLE `t_notebook`  (
  `no_id` int(11) NOT NULL,
  `no_wo_id` int(11) NOT NULL,
  `no_bo_id` int(11) NOT NULL,
  `no_user_id` int(11) NOT NULL,
  `no_status` int(11) NOT NULL,
  PRIMARY KEY (`no_id`) USING BTREE,
  INDEX `fk_no_wo_id`(`no_wo_id`) USING BTREE,
  INDEX `fk_no_raw_id`(`no_bo_id`) USING BTREE,
  INDEX `fk_no_user_id`(`no_user_id`) USING BTREE,
  CONSTRAINT `fk_no_bo_id` FOREIGN KEY (`no_bo_id`) REFERENCES `t_book` (`bo_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_no_user_id` FOREIGN KEY (`no_user_id`) REFERENCES `t_user` (`ur_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `fk_no_wo_id` FOREIGN KEY (`no_wo_id`) REFERENCES `t_word` (`wo_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_rank
-- ----------------------------
DROP TABLE IF EXISTS `t_rank`;
CREATE TABLE `t_rank`  (
  `ra_id` int(11) NOT NULL,
  `ra_user_id` int(11) NOT NULL,
  PRIMARY KEY (`ra_id`) USING BTREE,
  INDEX `fk_ra_user_id`(`ra_user_id`) USING BTREE,
  CONSTRAINT `fk_ra_user_id` FOREIGN KEY (`ra_user_id`) REFERENCES `t_user` (`ur_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `ur_id` int(11) NOT NULL,
  `ur_name` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ur_password` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ur_avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ur_daily_plan` int(5) NOT NULL,
  `ur_learned` int(5) NOT NULL,
  `ur_bo_id` int(11) NOT NULL,
  PRIMARY KEY (`ur_id`) USING BTREE,
  INDEX `fk_ur_bo_id`(`ur_bo_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_word
-- ----------------------------
DROP TABLE IF EXISTS `t_word`;
CREATE TABLE `t_word`  (
  `wo_id` int(11) NOT NULL,
  `wo_word` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `wo_explain` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `wo_level` int(2) NULL DEFAULT NULL,
  PRIMARY KEY (`wo_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
