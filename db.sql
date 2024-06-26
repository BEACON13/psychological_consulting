/*
 Navicat Premium Data Transfer

 Source Server         : mysql
 Source Server Type    : MySQL
 Source Server Version : 80024
 Source Host           : localhost:3306
 Source Schema         : psyconsult

 Target Server Type    : MySQL
 Target Server Version : 80024
 File Encoding         : 65001

 Date: 19/06/2021 20:18:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for add_consult
-- ----------------------------
DROP TABLE IF EXISTS `add_consult`;
CREATE TABLE `add_consult`  (
  `add_c_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `s_id` bigint(0) NOT NULL,
  `c_id` bigint(0) NULL DEFAULT NULL,
  `tp_id` smallint(0) NULL DEFAULT NULL,
  `times` int(0) NOT NULL,
  `is_finished` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`add_c_id`) USING BTREE,
  INDEX `FK_Reference_35`(`c_id`) USING BTREE,
  INDEX `FK_stu_add_consult`(`s_id`) USING BTREE,
  CONSTRAINT `FK_Reference_35` FOREIGN KEY (`c_id`) REFERENCES `person` (`p_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_stu_add_consult` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of add_consult
-- ----------------------------
BEGIN;
INSERT INTO `add_consult` VALUES (3, 4, 5, 1, 3, 0), (4, 2, 1, 4, 3, 0), (5, 2, 1, 4, 3, 0), (6, 2, 1, 4, 3, 0), (7, 2, 1, 4, 3, 0), (8, 2, 1, 4, 3, 0);
COMMIT;

-- ----------------------------
-- Table structure for closing_report
-- ----------------------------
DROP TABLE IF EXISTS `closing_report`;
CREATE TABLE `closing_report`  (
  `closing_report_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `s_id` bigint(0) NOT NULL,
  `c_id` bigint(0) NULL DEFAULT NULL,
  `problem_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `consult_num` int(0) NULL DEFAULT NULL,
  `consult_effect_self` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`closing_report_id`) USING BTREE,
  INDEX `FK_Reference_34`(`c_id`) USING BTREE,
  INDEX `FK_stu_closing_report`(`s_id`) USING BTREE,
  CONSTRAINT `FK_Reference_34` FOREIGN KEY (`c_id`) REFERENCES `person` (`p_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_stu_closing_report` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of closing_report
-- ----------------------------
BEGIN;
INSERT INTO `closing_report` VALUES (1, 1, 1, '轻度焦虑症', 8, '经过八次咨询后，学生状态有明显的好转');
COMMIT;

-- ----------------------------
-- Table structure for consult_apply
-- ----------------------------
DROP TABLE IF EXISTS `consult_apply`;
CREATE TABLE `consult_apply`  (
  `consult_apply_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `s_id` bigint(0) NOT NULL,
  `stu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `emergency_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `danger_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `problem_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `tp_id1` smallint(0) NULL DEFAULT NULL,
  `tp_id2` smallint(0) NULL DEFAULT NULL,
  `tp_id3` smallint(0) NULL DEFAULT NULL,
  `num` int(0) NULL DEFAULT NULL,
  `is_finished` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`consult_apply_id`) USING BTREE,
  INDEX `FK_Reference_42`(`tp_id1`) USING BTREE,
  INDEX `FK_Reference_43`(`tp_id2`) USING BTREE,
  INDEX `FK_Reference_44`(`tp_id3`) USING BTREE,
  INDEX `FK_stu_consult_apply`(`s_id`) USING BTREE,
  CONSTRAINT `FK_Reference_42` FOREIGN KEY (`tp_id1`) REFERENCES `time_period` (`tp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_43` FOREIGN KEY (`tp_id2`) REFERENCES `time_period` (`tp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_44` FOREIGN KEY (`tp_id3`) REFERENCES `time_period` (`tp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_stu_consult_apply` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of consult_apply
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for consult_appointment_record
-- ----------------------------
DROP TABLE IF EXISTS `consult_appointment_record`;
CREATE TABLE `consult_appointment_record`  (
  `consult_appoint_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `s_id` bigint(0) NOT NULL,
  `tp_id` smallint(0) NULL DEFAULT NULL,
  `location_id` bigint(0) NULL DEFAULT NULL,
  `c_id` bigint(0) NULL DEFAULT NULL,
  `is_deleted` tinyint(1) NULL DEFAULT 0,
  `date` date NULL DEFAULT NULL,
  `is_finished` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`consult_appoint_id`) USING BTREE,
  INDEX `FK_Reference_27`(`c_id`) USING BTREE,
  INDEX `FK_Reference_28`(`tp_id`) USING BTREE,
  INDEX `FK_Reference_29`(`location_id`) USING BTREE,
  INDEX `FK_stu_consult_appoint`(`s_id`) USING BTREE,
  CONSTRAINT `FK_Reference_27` FOREIGN KEY (`c_id`) REFERENCES `person` (`p_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_28` FOREIGN KEY (`tp_id`) REFERENCES `time_period` (`tp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_29` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_stu_consult_appoint` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of consult_appointment_record
-- ----------------------------
BEGIN;
INSERT INTO `consult_appointment_record` VALUES (1, 1, 1, 3, 1, 0, '2020-06-08', 1), (2, 1, 1, 3, 1, 0, '2020-06-15', 1), (3, 1, 1, 3, 1, 0, '2020-06-22', 1), (4, 1, 1, 3, 1, 0, '2020-06-29', 1), (5, 1, 1, 3, 1, 0, '2020-07-06', 1), (6, 1, 1, 3, 1, 0, '2020-07-13', 1), (7, 1, 1, 3, 1, 0, '2020-07-13', 1), (8, 1, 1, 3, 1, 0, '2020-07-20', 1), (9, 2, 8, 5, 5, 0, '2021-05-17', 1), (10, 2, 8, 5, 5, 0, '2021-05-24', 1), (11, 2, 8, 3, 5, 0, '2021-05-31', 1), (12, 2, 8, 5, 5, 0, '2021-06-07', 1), (13, 2, 8, 5, 5, 0, '2021-06-14', 0), (14, 2, 8, 5, 5, 0, '2021-06-21', 0), (15, 2, 8, 5, 5, 0, '2021-06-28', 0), (16, 2, 9, 5, 5, 0, '2021-07-06', 0), (58, 4, 1, 4, 5, 0, '2021-06-21', 0), (59, 4, 1, 4, 5, 0, '2021-06-28', 0), (60, 4, 1, 4, 5, 0, '2021-07-05', 0), (61, 4, 1, 4, 5, 0, '2021-07-12', 0), (62, 4, 1, 4, 5, 0, '2021-07-19', 0), (63, 4, 1, 4, 5, 0, '2021-07-26', 0), (64, 4, 1, 4, 5, 0, '2021-08-02', 0), (65, 4, 1, 4, 5, 0, '2021-08-09', 0), (66, 4, 1, 4, 5, 0, '2021-06-21', 0), (67, 4, 1, 4, 5, 0, '2021-06-28', 0), (68, 4, 1, 4, 5, 0, '2021-07-05', 0), (69, 4, 1, 4, 5, 0, '2021-07-12', 0), (70, 4, 1, 4, 5, 0, '2021-07-19', 0), (71, 4, 1, 4, 5, 0, '2021-07-26', 0), (72, 4, 1, 4, 5, 0, '2021-08-02', 0), (73, 4, 1, 4, 5, 0, '2021-08-09', 0), (74, 4, 1, 4, 5, 0, '2021-06-21', 0), (75, 4, 1, 4, 5, 0, '2021-06-28', 0), (76, 4, 1, 4, 5, 0, '2021-07-05', 0), (77, 4, 1, 4, 5, 0, '2021-07-12', 0), (78, 4, 1, 4, 5, 0, '2021-07-19', 0), (79, 4, 1, 4, 5, 0, '2021-07-26', 0), (80, 4, 1, 4, 5, 0, '2021-08-02', 0), (81, 4, 1, 4, 5, 0, '2021-08-09', 0), (82, 4, 1, 4, 5, 0, '2021-06-21', 0), (83, 4, 1, 4, 5, 0, '2021-06-28', 0), (84, 4, 1, 4, 5, 0, '2021-07-05', 0), (85, 4, 1, 4, 5, 0, '2021-07-12', 0), (86, 4, 1, 4, 5, 0, '2021-07-19', 0), (87, 4, 1, 4, 5, 0, '2021-07-26', 0), (88, 4, 1, 4, 5, 0, '2021-08-02', 0), (89, 4, 1, 4, 5, 0, '2021-08-09', 0), (90, 4, 1, 4, 5, 0, '2021-06-21', 0), (91, 4, 1, 4, 5, 0, '2021-06-28', 0), (92, 4, 1, 4, 5, 0, '2021-07-05', 0), (93, 4, 1, 4, 5, 0, '2021-07-12', 0), (94, 4, 1, 4, 5, 0, '2021-07-19', 0), (95, 4, 1, 4, 5, 0, '2021-07-26', 0), (96, 4, 1, 4, 5, 0, '2021-08-02', 0), (97, 4, 1, 4, 5, 0, '2021-08-09', 0), (98, 4, 1, 4, 5, 0, '2021-06-21', 0), (99, 4, 1, 4, 5, 0, '2021-06-28', 0), (100, 4, 1, 4, 5, 0, '2021-07-05', 0), (101, 4, 1, 4, 5, 0, '2021-07-12', 0), (102, 4, 1, 4, 5, 0, '2021-07-19', 0), (103, 4, 1, 4, 5, 0, '2021-07-26', 0), (104, 4, 1, 4, 5, 0, '2021-08-02', 0), (105, 4, 1, 4, 5, 0, '2021-08-09', 0);
COMMIT;

-- ----------------------------
-- Table structure for consult_appointment_report
-- ----------------------------
DROP TABLE IF EXISTS `consult_appointment_report`;
CREATE TABLE `consult_appointment_report`  (
  `car_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `consult_appoint_id` bigint(0) NULL DEFAULT NULL,
  `s_id` bigint(0) NULL DEFAULT NULL,
  `tp_id` smallint(0) NULL DEFAULT NULL,
  `c_id` bigint(0) NULL DEFAULT NULL,
  `consult_result` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  PRIMARY KEY (`car_id`) USING BTREE,
  INDEX `FK_Reference_39`(`s_id`) USING BTREE,
  INDEX `FK_Reference_40`(`tp_id`) USING BTREE,
  INDEX `FK_Reference_41`(`c_id`) USING BTREE,
  INDEX `fk_consult_appoint_id`(`consult_appoint_id`) USING BTREE,
  CONSTRAINT `fk_consult_appoint_id` FOREIGN KEY (`consult_appoint_id`) REFERENCES `consult_appointment_record` (`consult_appoint_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_39` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_40` FOREIGN KEY (`tp_id`) REFERENCES `time_period` (`tp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_41` FOREIGN KEY (`c_id`) REFERENCES `person` (`p_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of consult_appointment_report
-- ----------------------------
BEGIN;
INSERT INTO `consult_appointment_report` VALUES (1, 1, 1, 5, 1, '完成咨询', '2020-06-08'), (2, 2, 1, 5, 1, '完成咨询', '2020-06-15'), (3, 3, 1, 5, 1, '完成咨询', '2020-06-22'), (4, 4, 1, 5, 1, '完成咨询', '2020-06-29'), (5, 5, 1, 5, 1, '完成咨询', '2020-07-06'), (6, 6, 1, 5, 1, '完成咨询', '2020-07-13'), (7, 7, 1, 5, 1, '完成咨询', '2020-07-20'), (8, 8, 1, 5, 1, '结案', '2020-07-27'), (9, 9, 2, 8, 5, '完成咨询', '2021-05-17'), (10, 10, 2, 8, 5, '完成咨询', '2021-05-24'), (11, 11, 2, 8, 5, '完成咨询', '2021-05-31'), (12, 12, 2, 8, 5, '完成咨询', '2021-06-07');
COMMIT;

-- ----------------------------
-- Table structure for consultant_duty
-- ----------------------------
DROP TABLE IF EXISTS `consultant_duty`;
CREATE TABLE `consultant_duty`  (
  `cd_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `tp_id` smallint(0) NOT NULL,
  `location_id` bigint(0) NOT NULL,
  `c_id` bigint(0) NULL DEFAULT NULL,
  `free_time` date NULL DEFAULT NULL,
  PRIMARY KEY (`cd_id`) USING BTREE,
  INDEX `FK_Reference_33`(`c_id`) USING BTREE,
  INDEX `FK_consultant_loc`(`location_id`) USING BTREE,
  INDEX `FK_time_cd`(`tp_id`) USING BTREE,
  CONSTRAINT `FK_consultant_loc` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_33` FOREIGN KEY (`c_id`) REFERENCES `person` (`p_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_time_cd` FOREIGN KEY (`tp_id`) REFERENCES `time_period` (`tp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of consultant_duty
-- ----------------------------
BEGIN;
INSERT INTO `consultant_duty` VALUES (1, 1, 3, 1, '2021-06-21'), (2, 2, 3, 1, '2021-06-14'), (3, 3, 3, 1, '2021-06-14'), (4, 4, 3, 1, '2021-06-14'), (5, 1, 4, 5, '2021-08-16'), (6, 2, 4, 5, '2021-06-14'), (7, 3, 4, 5, '2021-06-14'), (8, 4, 4, 5, '2021-06-14'), (9, 5, 3, 1, '2021-06-15'), (10, 6, 3, 1, '2021-06-15'), (11, 7, 5, 5, '2021-06-15'), (12, 8, 5, 5, '2021-07-13'), (13, 9, 4, 5, '2021-06-16'), (14, 10, 4, 5, '2021-06-16'), (15, 11, 3, 1, '2021-06-16'), (16, 12, 3, 1, '2021-06-16'), (17, 13, 3, 1, '2021-06-17'), (18, 14, 3, 1, '2021-06-17'), (19, 15, 3, 1, '2021-06-17'), (20, 16, 3, 1, '2021-06-17'), (21, 15, 4, 5, '2021-06-17'), (22, 16, 4, 5, '2021-06-17'), (23, 17, 5, 5, '2021-06-18'), (24, 18, 5, 5, '2021-06-18'), (25, 19, 5, 5, '2021-06-18'), (26, 20, 5, 5, '2021-06-18'), (27, 19, 4, 1, '2021-06-18'), (28, 20, 4, 1, '2021-06-18');
COMMIT;

-- ----------------------------
-- Table structure for first_apply
-- ----------------------------
DROP TABLE IF EXISTS `first_apply`;
CREATE TABLE `first_apply`  (
  `fa_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `s_id` bigint(0) NOT NULL,
  `tp_id` smallint(0) NULL DEFAULT NULL,
  `score` int(0) NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `emergency_phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `physical_illness` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_diagnosed` tinyint(1) NULL DEFAULT NULL,
  `emergency_level` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `problem_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `consult_expectation` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `consult_history` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_finished` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`fa_id`) USING BTREE,
  INDEX `FK_Reference_26`(`tp_id`) USING BTREE,
  INDEX `FK_stu_first_apply2`(`s_id`) USING BTREE,
  CONSTRAINT `FK_Reference_26` FOREIGN KEY (`tp_id`) REFERENCES `time_period` (`tp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_stu_first_apply2` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of first_apply
-- ----------------------------
BEGIN;
INSERT INTO `first_apply` VALUES (1, 1, 1, 60, '范若曦', '15529080856', '四川大学江安校区', '13289075672', '无', 1, '较紧急', '焦虑', '希望能让我内心平静', '无', 1), (2, 2, 2, 32, '王琳', '13256783961', '郫都西路', '18765097314', '无', 1, '轻度', '社恐', '克服社恐', '无', 1), (3, 3, 3, 45, '张达', '18976280862', '春熙路23号', '15609871265', '患有心脏病', 0, '较紧急', '经常性紧张', '希望克服紧张', '以前在第一医院做过咨询', 1), (4, 4, 4, 78, '李小萌', '19987209803', '江安小区', '15528907543', '无', 0, '紧急', '抑郁', '洗希望恢复正常生活', '无', 1);
COMMIT;

-- ----------------------------
-- Table structure for first_visit_record
-- ----------------------------
DROP TABLE IF EXISTS `first_visit_record`;
CREATE TABLE `first_visit_record`  (
  `fvr_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `s_id` bigint(0) NOT NULL,
  `tp_id` smallint(0) NULL DEFAULT NULL,
  `location_id` bigint(0) NULL DEFAULT NULL,
  `fv_id` bigint(0) NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  `is_deleted` tinyint(1) NULL DEFAULT 0,
  `is_finished` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`fvr_id`) USING BTREE,
  INDEX `FK_Reference_24`(`tp_id`) USING BTREE,
  INDEX `FK_Reference_25`(`location_id`) USING BTREE,
  INDEX `FK_Reference_32`(`fv_id`) USING BTREE,
  INDEX `FK_stu_fvr`(`s_id`) USING BTREE,
  CONSTRAINT `FK_Reference_24` FOREIGN KEY (`tp_id`) REFERENCES `time_period` (`tp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_25` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_32` FOREIGN KEY (`fv_id`) REFERENCES `person` (`p_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_stu_fvr` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of first_visit_record
-- ----------------------------
BEGIN;
INSERT INTO `first_visit_record` VALUES (1, 1, 1, 1, 2, '2020-06-01', 0, 1), (2, 2, 2, 2, 3, '2021-05-31', 0, 1), (3, 3, 20, 6, 2, '2021-06-18', 0, 0), (4, 4, 4, 2, 3, '2021-06-14', 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for first_visit_report
-- ----------------------------
DROP TABLE IF EXISTS `first_visit_report`;
CREATE TABLE `first_visit_report`  (
  `fvreport_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `fvr_id` bigint(0) NULL DEFAULT NULL,
  `s_id` bigint(0) NULL DEFAULT NULL,
  `tp_id` smallint(0) NULL DEFAULT NULL,
  `fv_id` bigint(0) NULL DEFAULT NULL,
  `danger_level` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `problem_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `conclusion` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `date` date NULL DEFAULT NULL,
  PRIMARY KEY (`fvreport_id`) USING BTREE,
  INDEX `FK_Reference_36`(`s_id`) USING BTREE,
  INDEX `FK_Reference_37`(`tp_id`) USING BTREE,
  INDEX `FK_Reference_38`(`fv_id`) USING BTREE,
  INDEX `fk_fvr_id`(`fvr_id`) USING BTREE,
  CONSTRAINT `fk_fvr_id` FOREIGN KEY (`fvr_id`) REFERENCES `first_visit_record` (`fvr_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_36` FOREIGN KEY (`s_id`) REFERENCES `student` (`s_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_37` FOREIGN KEY (`tp_id`) REFERENCES `time_period` (`tp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_38` FOREIGN KEY (`fv_id`) REFERENCES `person` (`p_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of first_visit_report
-- ----------------------------
BEGIN;
INSERT INTO `first_visit_report` VALUES (1, 1, 1, 1, 2, '中', '焦虑症', '安排咨询', '2021-06-07'), (2, 2, 2, 2, 3, '轻', '社交恐惧症', '安排咨询', '2021-05-31'), (12, 4, 4, 4, 3, '中', '抑郁', '安排咨询', '2021-06-14');
COMMIT;

-- ----------------------------
-- Table structure for first_visitor_duty
-- ----------------------------
DROP TABLE IF EXISTS `first_visitor_duty`;
CREATE TABLE `first_visitor_duty`  (
  `fvd_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `tp_id` smallint(0) NOT NULL,
  `location_id` bigint(0) NOT NULL,
  `fv_id` bigint(0) NULL DEFAULT NULL,
  `is_available` tinyint(1) NULL DEFAULT 1,
  PRIMARY KEY (`fvd_id`) USING BTREE,
  INDEX `FK_Reference_31`(`fv_id`) USING BTREE,
  INDEX `FK_fv_loc`(`location_id`) USING BTREE,
  INDEX `FK_fvd_time`(`tp_id`) USING BTREE,
  CONSTRAINT `FK_fv_loc` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_fvd_time` FOREIGN KEY (`tp_id`) REFERENCES `time_period` (`tp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `FK_Reference_31` FOREIGN KEY (`fv_id`) REFERENCES `person` (`p_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of first_visitor_duty
-- ----------------------------
BEGIN;
INSERT INTO `first_visitor_duty` VALUES (1, 1, 1, 2, 1), (2, 2, 1, 2, 1), (3, 3, 1, 2, 1), (4, 4, 1, 2, 1), (5, 1, 2, 3, 1), (6, 2, 2, 3, 1), (7, 3, 2, 3, 1), (8, 4, 2, 3, 1), (9, 5, 6, 2, 1), (10, 6, 6, 2, 1), (11, 7, 2, 3, 1), (12, 8, 2, 3, 1), (13, 9, 2, 3, 1), (14, 10, 2, 3, 1), (15, 11, 1, 2, 1), (16, 12, 1, 2, 1), (17, 13, 6, 2, 1), (18, 14, 6, 2, 1), (19, 15, 1, 2, 1), (20, 16, 1, 2, 1), (21, 15, 6, 3, 1), (22, 16, 6, 3, 1), (23, 17, 2, 3, 1), (24, 18, 2, 3, 1), (25, 19, 2, 3, 1), (26, 20, 2, 3, 1), (27, 19, 1, 2, 1), (28, 20, 6, 2, 0), (30, 5, 2, 3, 1);
COMMIT;

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location`  (
  `location_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `location_type` int(0) NOT NULL,
  `location_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  PRIMARY KEY (`location_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of location
-- ----------------------------
BEGIN;
INSERT INTO `location` VALUES (1, 1, '江安校区二基楼B203'), (2, 1, '江安校区二基楼B507'), (3, 2, '江安校区一教A203'), (4, 2, '望江校区基教C104'), (5, 2, '江安校区一教D307'), (6, 1, '华西一基楼B201');
COMMIT;

-- ----------------------------
-- Table structure for person
-- ----------------------------
DROP TABLE IF EXISTS `person`;
CREATE TABLE `person`  (
  `p_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `job` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `age` smallint(0) NULL DEFAULT NULL,
  `info` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `is_deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of person
-- ----------------------------
BEGIN;
INSERT INTO `person` VALUES (1, 'yanghl', '123', '杨慧林', '13278091782', '女', '心理咨询师', 43, '资深心理咨询师，从业15年，经验丰富', '四川省成都市双流区', '1089728@qq.com', 0), (2, 'admin', '12345678', '唐林', '18937092834', '男', '心理学教师', 35, '心理学教师，擅长管理', '四川省成都市郫都区润水花园', '927392739@126.com', 0), (3, 'wangq', '123', '王青青', '13290870832', '女', '心理学教师', 28, '心理学硕士毕业', '成都西路', '29073826@qq.com', 0), (4, 'sunwei', '123', '孙维', '18907832568', '男', '实习助理', 23, '心理学研究生在读', '成都春熙路', '2903263782@qq.com', 0), (5, 'taodn', '123', '陶大娘', '15589082764', '女', '心理咨询师', 50, '外聘心理咨询师，从业25年', '北京南路', '29083729@qq.com', 0);
COMMIT;

-- ----------------------------
-- Table structure for person_type
-- ----------------------------
DROP TABLE IF EXISTS `person_type`;
CREATE TABLE `person_type`  (
  `pt_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `p_id` bigint(0) NOT NULL,
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pt_id`) USING BTREE,
  INDEX `FK_Reference_30`(`p_id`) USING BTREE,
  CONSTRAINT `FK_Reference_30` FOREIGN KEY (`p_id`) REFERENCES `person` (`p_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of person_type
-- ----------------------------
BEGIN;
INSERT INTO `person_type` VALUES (1, 1, '咨询师'), (2, 2, '中心管理员'), (3, 2, '初访员'), (4, 3, '初访员'), (5, 4, '心理助理'), (6, 5, '咨询师');
COMMIT;

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student`  (
  `s_id` bigint(0) NOT NULL AUTO_INCREMENT,
  `code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `college` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade` int(0) NULL DEFAULT NULL,
  `gender` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `birth_date` date NULL DEFAULT NULL,
  `is_qualified` tinyint(1) NULL DEFAULT NULL,
  `password` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '学生表';

-- ----------------------------
-- Records of student
-- ----------------------------
BEGIN;
INSERT INTO `student` VALUES (1, '2018141411216', '范若曦', '15529080856', '软件学院', 2018, '女', '2000-05-16', 0, '123'), (2, '2017151638792', '王琳', '13256783961', '经济学院', 2017, '女', '1999-03-11', 1, '123456'), (3, '2019151663516', '张达', '18976280862', '数学学院', 2019, '男', '2001-06-10', 0, '123'), (4, '2018141411397', '李小萌', '19987209803', '计算机学院', 2020, '女', '2002-04-27', 1, '123'), (5, '2019151728901', '王鹏', '13289074892', '化学学院', 2019, '男', '2000-01-12', 0, '123'), (6, '2018141463095', '张锋', '15598370973', '电子信息学院', 2018, '男', '2000-11-22', 0, '123'), (7, '2017131878972', '李雯雯', '19878047823', '文学与新闻学院', 2017, '女', '1999-04-08', 0, '123'), (8, '2018141411396', '毛毛', '13736665310', '软件学院', 2018, '女', '2000-04-29', 0, '3777');
COMMIT;

-- ----------------------------
-- Table structure for time_period
-- ----------------------------
DROP TABLE IF EXISTS `time_period`;
CREATE TABLE `time_period`  (
  `tp_id` smallint(0) NOT NULL AUTO_INCREMENT,
  `start_time` time(0) NULL DEFAULT NULL,
  `duration` int(0) NULL DEFAULT NULL,
  `weekday` smallint(0) NULL DEFAULT NULL,
  `is_deleted` tinyint(1) NULL DEFAULT 0,
  PRIMARY KEY (`tp_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of time_period
-- ----------------------------
BEGIN;
INSERT INTO `time_period` VALUES (1, '08:00:00', 60, 1, 0), (2, '10:00:00', 60, 1, 0), (3, '14:00:00', 60, 1, 0), (4, '16:00:00', 60, 1, 0), (5, '08:00:00', 60, 2, 0), (6, '10:00:00', 60, 2, 0), (7, '14:00:00', 60, 2, 0), (8, '16:00:00', 60, 2, 0), (9, '08:00:00', 60, 3, 0), (10, '10:00:00', 60, 3, 0), (11, '14:00:00', 60, 3, 0), (12, '16:00:00', 60, 3, 0), (13, '08:00:00', 60, 4, 0), (14, '10:00:00', 60, 4, 0), (15, '14:00:00', 60, 4, 0), (16, '16:00:00', 60, 4, 0), (17, '08:00:00', 60, 5, 0), (18, '10:00:00', 60, 5, 0), (19, '14:00:00', 60, 5, 0), (20, '16:00:00', 60, 5, 0);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;

CREATE TABLE `evaluation_table` (
                                    `id` bigint NOT NULL AUTO_INCREMENT COMMENT '评测表id',
                                    `evaluation_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评测表名称',
                                    `description` varchar(255) DEFAULT NULL COMMENT '评测表描述',
                                    `is_deleted` tinyint(1) DEFAULT '0',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


