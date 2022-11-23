/*
 Navicat Premium Data Transfer

 Source Server         : my
 Source Server Type    : MySQL
 Source Server Version : 50709
 Source Host           : localhost:3306
 Source Schema         : management

 Target Server Type    : MySQL
 Target Server Version : 50709
 File Encoding         : 65001

 Date: 23/11/2022 17:49:24
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for pre_circle
-- ----------------------------
DROP TABLE IF EXISTS `pre_circle`;
CREATE TABLE `pre_circle`  (
  `circle_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `circle_title` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `circle_like` int(10) NOT NULL DEFAULT 0,
  `circle_user_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `circle_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pre_circle
-- ----------------------------
INSERT INTO `pre_circle` VALUES (NULL, '2022-09-26 14:50:35', '2022-09-26 14:50:35', '123', 0, '', '');
INSERT INTO `pre_circle` VALUES (NULL, '2022-09-26 14:51:15', '2022-09-26 14:51:15', '12312', 0, '', '');
INSERT INTO `pre_circle` VALUES (NULL, '2022-09-26 14:58:05', '2022-09-26 14:58:05', '12312', 0, '', '');
INSERT INTO `pre_circle` VALUES ('这是内容', '2022-09-26 14:59:04', '2022-09-26 14:59:04', '这是个普通的话题', 0, '', '');
INSERT INTO `pre_circle` VALUES ('3123', '2022-09-26 17:00:31', '2022-09-26 17:00:31', '21312', 0, '1551931091311030273', '1574323017187790850');
INSERT INTO `pre_circle` VALUES ('NWL', '2022-09-26 22:36:57', '2022-09-26 22:36:57', 'jdxb.JDSRHFEIWUF', 0, '1551931091311030273', '1574407683374780417');
INSERT INTO `pre_circle` VALUES ('ui方面还需要细化', '2022-09-26 22:38:07', '2022-09-26 22:38:07', '做的还不错', 0, '1551931091311030273', '1574407978003664898');
INSERT INTO `pre_circle` VALUES ('22222', '2022-09-26 22:41:07', '2022-09-26 22:41:07', '111111', 0, '1551919659702022146', '1574408733271986178');

-- ----------------------------
-- Table structure for pre_comment
-- ----------------------------
DROP TABLE IF EXISTS `pre_comment`;
CREATE TABLE `pre_comment`  (
  `comment_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `like` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `comment_content` varchar(2000) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `comment_user_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0)
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pre_comment
-- ----------------------------

-- ----------------------------
-- Table structure for pre_dict
-- ----------------------------
DROP TABLE IF EXISTS `pre_dict`;
CREATE TABLE `pre_dict`  (
  `dict_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `type_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `value` varchar(48) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pre_dict
-- ----------------------------

-- ----------------------------
-- Table structure for pre_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `pre_dict_type`;
CREATE TABLE `pre_dict_type`  (
  `type_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of pre_dict_type
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '内容',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '类型'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('user', 'el-icon-user-solid', 'icon');
INSERT INTO `sys_dict` VALUES ('user', 'el-icon-user', 'icon');
INSERT INTO `sys_dict` VALUES ('el-icon-s-grid', 'el-icon-s-grid', 'icon');
INSERT INTO `sys_dict` VALUES ('el-icon-s-opportunity', 'el-icon-s-opportunity', 'icon');
INSERT INTO `sys_dict` VALUES ('el-icon-document-copy', 'el-icon-document-copy', 'icon');
INSERT INTO `sys_dict` VALUES ('el-icon-monitor', 'el-icon-monitor', 'icon');
INSERT INTO `sys_dict` VALUES ('el-icon-data-line', 'el-icon-data-line', 'icon');
INSERT INTO `sys_dict` VALUES ('el-icon-postcard', 'el-icon-postcard', 'icon');

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件名称',
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件类型',
  `size` bigint(20) NULL DEFAULT NULL COMMENT '文件大小',
  `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '下载链接',
  `is_delete` tinyint(1) NULL DEFAULT 0 COMMENT '是否删除',
  `file_address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件的本地地址',
  `enable` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用链接',
  `md5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '文件md5',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('1553374068273238017', NULL, 'v2-07b5dc350a9ab8509ebeff670b8f893b_720w.jpg', 'jpg', 55, 'http://localhost:9090/file/38264a8f1f494f848d625d38743beaaa.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/38264a8f1f494f848d625d38743beaaa.jpg', 1, 'ed68ffbcd1be576b85cfe04a4ae52c00');
INSERT INTO `sys_file` VALUES ('1553374079312646146', NULL, 'v2-c3793eea260b402c0e847288c807c7dd_720w.jpg', 'jpg', 77, 'http://localhost:9090/file/a990c2496fc3496ba644c24b5942876e.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/a990c2496fc3496ba644c24b5942876e.jpg', 1, 'f39d3b1a1c16a5bcc9b16f6ad77c17b6');
INSERT INTO `sys_file` VALUES ('1553582934063767554', NULL, 'v2-29c615a14037b9898e3641f5523e8ca2_720w.jpg', 'jpg', 57, 'http://localhost:9090/file/44f26e9e630d47c58432bc4d7aa0dab7.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/44f26e9e630d47c58432bc4d7aa0dab7.jpg', 1, '90cf58cdd12fff47fedef46604928741');
INSERT INTO `sys_file` VALUES ('1553599264800305153', NULL, 'v2-4415d3f06cc918bc4243c665e712c3f0_720w.jpg', 'jpg', 56, 'http://localhost:9090/file/d666fb88188446c6a7e06b3610fb7e1c.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/d666fb88188446c6a7e06b3610fb7e1c.jpg', 1, '821c88a30e42b3a023cbbf7a65d330e1');
INSERT INTO `sys_file` VALUES ('1553919291025465346', NULL, 'v2-95ed6ea0f78292c9cad905ad117c7fcc_720w.jpg', 'jpg', 73, 'http://localhost:9090/file/14a3a7c294bb4913b0066beba24a5814.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/14a3a7c294bb4913b0066beba24a5814.jpg', 1, 'f75d7dcdec9bfe91b0f1488ee6830728');
INSERT INTO `sys_file` VALUES ('1553919321509666817', NULL, 'v2-950216178099d7b34875e803aa048b5f_720w.jpg', 'jpg', 62, 'http://localhost:9090/file/73fd7fe8708e4b499488ddfcf0be2a89.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/73fd7fe8708e4b499488ddfcf0be2a89.jpg', 1, 'f6fc244294cd9ec3f51e8acf8712c9db');
INSERT INTO `sys_file` VALUES ('1553923168978223105', NULL, 'v2-48855e323969befacf4f407726306e6f_720w.jpg', 'jpg', 25, 'http://localhost:9090/file/efa6c572491b49f9aa552332f0a76685.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/efa6c572491b49f9aa552332f0a76685.jpg', 1, '3d6d4c20815245a864491445d99c0d8f');
INSERT INTO `sys_file` VALUES ('1553923837575446529', NULL, 'v2-64176df43629b8aebcab92e2b8212083_720w.jpg', 'jpg', 61, 'http://localhost:9090/file/8ef23382d39f418e85b1ff67634a3cbc.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/8ef23382d39f418e85b1ff67634a3cbc.jpg', 1, '949f4c3fb22133f1b97c5fce80d9f43c');
INSERT INTO `sys_file` VALUES ('1555002539994501121', NULL, 'v2-c31ba19ec15ad12345b2c53f7bae94e3_720w.jpg', 'jpg', 38, 'http://localhost:9090/file/8dadb15cb3e54d86a1646c2b7957df8e.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/8dadb15cb3e54d86a1646c2b7957df8e.jpg', 1, '3769bfb6da893185393df64e52f315ca');
INSERT INTO `sys_file` VALUES ('1556883213613473793', NULL, 'v2-5045d5eb0f77e3adbe2df6091f23528d_720w.jpg', 'jpg', 31, 'http://localhost:9090/file/bd17cc4afef147f68498385c54e6a371.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/bd17cc4afef147f68498385c54e6a371.jpg', 1, 'a7c424fbfc355be99be54f898c563e6c');
INSERT INTO `sys_file` VALUES ('1556944465995948033', NULL, 'v2-b6dfcc3c00478268ddada7bd8c79d187_720w.jpg', 'jpg', 74, 'http://localhost:9090/file/1e8af290ea0f4ab7b7b2a5e452dcad61.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/1e8af290ea0f4ab7b7b2a5e452dcad61.jpg', 1, '1aadc401af2bd2ede656c1632a40b286');
INSERT INTO `sys_file` VALUES ('1557181157822648322', NULL, 'v2-194397505ddabc39a47e9532fc4aef90_720w.jpg', 'jpg', 65, 'http://localhost:9090/file/ca86f49aafc642caa0da7df2b78c33d5.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/ca86f49aafc642caa0da7df2b78c33d5.jpg', 1, 'cd5ed984d84bebd4cb0ad21795b9f90f');
INSERT INTO `sys_file` VALUES ('1573610293655429121', NULL, 'v2-e1c0cd5aebfa62318f12b1295d7004f5_720w.jpg', 'jpg', 67, 'http://localhost:9090/file/599f4c0ad44f48f485f92135db5be58f.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/599f4c0ad44f48f485f92135db5be58f.jpg', 1, 'e26909495b77d34f30251f38fd5f311c');
INSERT INTO `sys_file` VALUES ('1573627305953763329', NULL, '22.jpg', 'jpg', 256, 'http://localhost:9090/file/45eb4718ba90437aa41529e22b0d6eb7.jpg', 0, 'C:/Users/YLY/Desktop/用户管理系统/存储文件/45eb4718ba90437aa41529e22b0d6eb7.jpg', 1, 'b28a94863940249274c1ca2854874f7e');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NULL DEFAULT '' COMMENT '名称',
  `path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '路径',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '图标',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `pid` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '父id',
  `component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '前端组件名',
  `level` int(32) NULL DEFAULT NULL COMMENT '菜单级别',
  `has_children` int(1) NULL DEFAULT NULL COMMENT '是否有子级菜单',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('12312321', '个', NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES ('1553946279870197762', '后台', '/home', 'el-icon-postcard', '', NULL, 'pages/Home', 1, 1);
INSERT INTO `sys_menu` VALUES ('1554006821175451649', '系统管理', '/home/system', 'el-icon-monitor', '', '1553946279870197762', 'pages/System', 2, 1);
INSERT INTO `sys_menu` VALUES ('1554018748417122305', '用户管理', '/home/system/user', 'el-icon-user-solid', NULL, '1554006821175451649', 'pages/User', 3, 0);
INSERT INTO `sys_menu` VALUES ('1554018860333735937', '菜单管理', '/home/system/menu', 'el-icon-s-grid', NULL, '1554006821175451649', 'pages/Menu', 3, 0);
INSERT INTO `sys_menu` VALUES ('1554018916440940546', '文件管理', '/home/system/file', 'el-icon-document-copy', NULL, '1554006821175451649', 'pages/File', 3, 0);
INSERT INTO `sys_menu` VALUES ('1554018975559655425', '角色管理', '/home/system/role', 'el-icon-user', NULL, '1554006821175451649', 'pages/Role', 3, 0);
INSERT INTO `sys_menu` VALUES ('1557285268542881794', '关于', '/about', 'el-icon-s-opportunity', '关于我们', '1553946279870197762', 'pages/About', 2, 0);
INSERT INTO `sys_menu` VALUES ('1573122046227116033', '后台首页', '/home/index', 'el-icon-monitor', NULL, '1553946279870197762', 'pages/Main', 2, 0);
INSERT INTO `sys_menu` VALUES ('1573604462293606401', '个人信息', '/home/person', 'el-icon-s-grid', '', '1553946279870197762', 'pages/Person', 2, 0);
INSERT INTO `sys_menu` VALUES ('312321312', ' ', NULL, NULL, NULL, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '名称',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '描述',
  `flag` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '唯一标识',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1553601048193175553', '超级管理员', '超级管理员', 'admin');
INSERT INTO `sys_role` VALUES ('1555018406497046529', '普通用户', '普通用户', 'member');

-- ----------------------------
-- Table structure for sys_role_menu_relation
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu_relation`;
CREATE TABLE `sys_role_menu_relation`  (
  `roleid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'role_id',
  `menuid` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT 'menu_id',
  PRIMARY KEY (`roleid`, `menuid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu_relation
-- ----------------------------
INSERT INTO `sys_role_menu_relation` VALUES ('1553601048193175553', '1553946279870197762');
INSERT INTO `sys_role_menu_relation` VALUES ('1553601048193175553', '1554006821175451649');
INSERT INTO `sys_role_menu_relation` VALUES ('1553601048193175553', '1554018748417122305');
INSERT INTO `sys_role_menu_relation` VALUES ('1553601048193175553', '1554018860333735937');
INSERT INTO `sys_role_menu_relation` VALUES ('1553601048193175553', '1554018916440940546');
INSERT INTO `sys_role_menu_relation` VALUES ('1553601048193175553', '1554018975559655425');
INSERT INTO `sys_role_menu_relation` VALUES ('1553601048193175553', '1557285268542881794');
INSERT INTO `sys_role_menu_relation` VALUES ('1553601048193175553', '1572572491123658754');
INSERT INTO `sys_role_menu_relation` VALUES ('1553601048193175553', '1573122046227116033');
INSERT INTO `sys_role_menu_relation` VALUES ('1553601048193175553', '1573604462293606401');
INSERT INTO `sys_role_menu_relation` VALUES ('1553601048193175553', '1574024157555462145');
INSERT INTO `sys_role_menu_relation` VALUES ('1553601048193175553', '1574031371997437954');
INSERT INTO `sys_role_menu_relation` VALUES ('1555018406497046529', '1553946279870197762');
INSERT INTO `sys_role_menu_relation` VALUES ('1555018406497046529', '1557285268542881794');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `nickname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `iphone` varchar(56) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `email` varchar(56) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `create_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0),
  `update_time` timestamp(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0),
  `avatar_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT 'member' COMMENT '绑定角色',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('123', NULL, NULL, NULL, NULL, NULL, NULL, '2022-08-11 10:44:06', '2022-08-11 10:44:06', NULL, 'member');
INSERT INTO `sys_user` VALUES ('1551919659702022146', 'xiao', '123', '12311', '2312', '31231', '312', '2022-07-26 21:17:34', '2022-08-10 15:40:58', 'http://localhost:9090/file/38264a8f1f494f848d625d38743beaaa.jpg', 'admin');
INSERT INTO `sys_user` VALUES ('1551931091311030273', 'admin', '123456', 'xxxx', '21', '111', '121', '2022-07-26 22:03:00', '2022-08-05 16:54:03', 'http://localhost:9090/file/45eb4718ba90437aa41529e22b0d6eb7.jpg', 'admin');
INSERT INTO `sys_user` VALUES ('1552106622832332802', 'yaoliyan1', '112233', NULL, NULL, NULL, NULL, '2022-07-27 09:40:30', '2022-08-11 10:44:27', 'http://localhost:9090/file/d666fb88188446c6a7e06b3610fb7e1c.jpg', 'member');
INSERT INTO `sys_user` VALUES ('1552658100974657537', 'yly', '112233', NULL, NULL, NULL, NULL, '2022-07-28 22:11:52', '2022-08-11 10:44:27', NULL, 'member');
INSERT INTO `sys_user` VALUES ('1552661056864321538', 'xixi', '111', NULL, NULL, NULL, NULL, '2022-07-28 22:23:37', '2022-08-11 10:44:27', 'http://localhost:9090/file/005cdd041ad54af7b037f810315a0949.jpg', 'member');
INSERT INTO `sys_user` VALUES ('1552661848144953345', 'jiji', '111', NULL, NULL, NULL, NULL, '2022-07-28 22:26:46', '2022-08-11 10:44:27', 'http://localhost:9090/file/005cdd041ad54af7b037f810315a0949.jpg', 'member');
INSERT INTO `sys_user` VALUES ('1552957998806237186', 'vae', NULL, 'songsong', '12313123', 'songsong.com', 'ss', '2022-07-29 18:03:34', '2022-08-11 10:44:27', NULL, 'member');
INSERT INTO `sys_user` VALUES ('1552958085502500866', 'sad', NULL, 'asd', 'adasd', 'asds', 'as', '2022-07-29 18:03:54', '2022-08-11 10:44:27', NULL, 'member');

SET FOREIGN_KEY_CHECKS = 1;
