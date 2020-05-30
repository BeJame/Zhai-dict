/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50723
Source Host           : localhost:3306
Source Database       : zhai_dict

Target Server Type    : MYSQL
Target Server Version : 50723
File Encoding         : 65001

Date: 2020-05-07 16:54:40
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `bo_id` int(11) NOT NULL,
  `bo_title` varchar(255) NOT NULL,
  `bo_description` varchar(255) DEFAULT NULL,
  `bo_image_url` varchar(255) DEFAULT NULL,
  `bo_total_words` int(11) NOT NULL,
  PRIMARY KEY (`bo_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_collect_word
-- ----------------------------
DROP TABLE IF EXISTS `t_collect_word`;
CREATE TABLE `t_collect_word` (
  `wo_id` int(11) NOT NULL,
  `wo_name` varchar(255) NOT NULL,
  `wo_ur_id` int(11) NOT NULL,
  `wo_status` int(11) NOT NULL,
  PRIMARY KEY (`wo_id`),
  KEY `fk_wo_ur_id` (`wo_ur_id`),
  CONSTRAINT `fk_wo_ur_id` FOREIGN KEY (`wo_ur_id`) REFERENCES `t_user` (`ur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_finish
-- ----------------------------
DROP TABLE IF EXISTS `t_finish`;
CREATE TABLE `t_finish` (
  `fi_id` int(11) NOT NULL,
  `fi_count` int(11) NOT NULL,
  `fi_ur_id` int(11) NOT NULL,
  PRIMARY KEY (`fi_id`),
  KEY `fk_fi_ur_id` (`fi_ur_id`),
  CONSTRAINT `fk_fi_ur_id` FOREIGN KEY (`fi_ur_id`) REFERENCES `t_user` (`ur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_image
-- ----------------------------
DROP TABLE IF EXISTS `t_image`;
CREATE TABLE `t_image` (
  `im_id` int(11) NOT NULL,
  `im_name` varchar(255) DEFAULT NULL,
  `im_url` varchar(255) NOT NULL,
  `im_ty_id` int(11) NOT NULL,
  PRIMARY KEY (`im_id`),
  KEY `fk_im_ty_id` (`im_ty_id`),
  CONSTRAINT `fk_im_ty_id` FOREIGN KEY (`im_ty_id`) REFERENCES `t_image_type` (`ty_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_image_type
-- ----------------------------
DROP TABLE IF EXISTS `t_image_type`;
CREATE TABLE `t_image_type` (
  `ty_id` int(11) NOT NULL,
  `ty_name` varchar(255) NOT NULL,
  `ty_description` varchar(255) DEFAULT NULL,
  `ty_ur_id` int(11) NOT NULL,
  `ty_status` int(11) NOT NULL,
  PRIMARY KEY (`ty_id`),
  KEY `fk_ty_ur_id` (`ty_ur_id`),
  CONSTRAINT `fk_ty_ur_id` FOREIGN KEY (`ty_ur_id`) REFERENCES `t_user` (`ur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_learned
-- ----------------------------
DROP TABLE IF EXISTS `t_learned`;
CREATE TABLE `t_learned` (
  `le_id` int(11) NOT NULL,
  `le_ur_id` int(11) NOT NULL,
  `le_bo_id` int(11) NOT NULL,
  `le_learned_num` int(11) NOT NULL,
  PRIMARY KEY (`le_id`),
  KEY `fk_le_ur_id` (`le_ur_id`),
  KEY `fk_le_bo_id` (`le_bo_id`),
  CONSTRAINT `fk_le_bo_id` FOREIGN KEY (`le_bo_id`) REFERENCES `t_book` (`bo_id`),
  CONSTRAINT `fk_le_ur_id` FOREIGN KEY (`le_ur_id`) REFERENCES `t_user` (`ur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_rank
-- ----------------------------
DROP TABLE IF EXISTS `t_rank`;
CREATE TABLE `t_rank` (
  `ra_id` int(11) NOT NULL,
  `ra_ur_id` int(11) NOT NULL,
  PRIMARY KEY (`ra_id`),
  KEY `fk_ra_ur_id` (`ra_ur_id`),
  CONSTRAINT `fk_ra_ur_id` FOREIGN KEY (`ra_ur_id`) REFERENCES `t_user` (`ur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_record
-- ----------------------------
DROP TABLE IF EXISTS `t_record`;
CREATE TABLE `t_record` (
  `re_id` int(11) NOT NULL,
  `re_word` varchar(255) NOT NULL,
  `re_date` datetime NOT NULL,
  `re_level` int(11) NOT NULL,
  `re_ur_id` int(11) NOT NULL,
  PRIMARY KEY (`re_id`),
  KEY `fk_re_ur_id` (`re_ur_id`),
  CONSTRAINT `fk_re_ur_id` FOREIGN KEY (`re_ur_id`) REFERENCES `t_user` (`ur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `ur_id` int(11) NOT NULL,
  `ur_name` varchar(255) NOT NULL,
  `ur_password` varchar(255) NOT NULL,
  `ur_avatar` varchar(255) DEFAULT NULL,
  `ur_daily_amount` int(11) NOT NULL,
  `ur_bo_id` int(11) NOT NULL,
  `ur_change_times` int(11) NOT NULL,
  `ur_keep_time` int(11) NOT NULL,
  `ur_tip_time` int(11) NOT NULL,
  PRIMARY KEY (`ur_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
