/*
Navicat MySQL Data Transfer

Source Server         : nichijou
Source Server Version : 80023
Source Host           : localhost:3306
Source Database       : oes

Target Server Type    : MYSQL
Target Server Version : 80023
File Encoding         : 65001

Date: 2022-08-04 21:37:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for auth_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_permission`;
CREATE TABLE `auth_permission` (
  `id` bigint NOT NULL DEFAULT '0' COMMENT '权限id',
  `pid` bigint NOT NULL DEFAULT '0' COMMENT '所属父权限id',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `type` tinyint NOT NULL DEFAULT '0' COMMENT '类型(0:菜单,1:按钮)',
  `permission_value` varchar(50) DEFAULT NULL COMMENT '权限值',
  `path` varchar(100) DEFAULT NULL COMMENT '访问路径',
  `component` varchar(100) DEFAULT NULL COMMENT '组件路径',
  `redirect` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '重定向地址',
  `icon` varchar(50) DEFAULT NULL COMMENT '图标',
  `hidden` tinyint(1) DEFAULT '0' COMMENT '是否隐藏路由1（true）隐藏，0（false）不隐藏',
  `sort` int DEFAULT '0' COMMENT '菜单排序',
  `is_enable` tinyint(1) DEFAULT '1' COMMENT '是否启用（1：启用，0：禁止）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_pid` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限';

-- ----------------------------
-- Records of auth_permission
-- ----------------------------
INSERT INTO `auth_permission` VALUES ('1', '0', '全部数据', '0', null, null, null, null, null, '0', '0', '1', '2019-11-15 17:13:06', '2019-11-15 17:13:06');
INSERT INTO `auth_permission` VALUES ('1095350299365969922', '1', '科目管理', '1', null, '/subject', 'Layout', '/subject/list', 'el-icon-notebook-1', '0', '3', '1', '2019-11-15 22:38:15', '2022-07-30 10:06:32');
INSERT INTO `auth_permission` VALUES ('1095350831744782337', '1', '课程管理', '1', null, '/course', 'Layout', '/course/list', 'el-icon-s-platform', '0', '4', '1', '2019-11-15 22:40:21', '2022-07-30 10:06:21');
INSERT INTO `auth_permission` VALUES ('1195349439240048642', '1', '讲师管理', '1', null, '/teacher', 'Layout', '/teacher/list', 'el-icon-s-custom', '0', '2', '1', '2022-07-27 09:55:59', '2022-07-30 10:06:06');
INSERT INTO `auth_permission` VALUES ('1195349699995734017', '1195349439240048642', '讲师列表', '1', null, 'list', '/edu/teacher/list', null, 'el-icon-tickets', '0', '0', '1', '2022-07-27 09:55:59', '2022-07-27 09:55:59');
INSERT INTO `auth_permission` VALUES ('1195349810561781761', '1195349439240048642', '添加讲师', '1', null, 'save', '/edu/teacher/save', null, 'el-icon-plus', '0', '1', '1', '2022-07-27 10:07:42', '2022-07-28 13:03:41');
INSERT INTO `auth_permission` VALUES ('1195350117270261762', '1195349699995734017', '讲师修改', '2', 'teacher.update', 'edit/:id', '/edu/teacher/save', null, null, '1', '0', '1', '2022-07-27 10:10:29', '2022-07-27 11:10:51');
INSERT INTO `auth_permission` VALUES ('1195350188359520258', '1195349699995734017', '讲师删除', '2', 'teacher.delete', '', '', null, null, '1', '0', '1', '2022-07-27 10:08:04', '2022-07-27 11:10:56');
INSERT INTO `auth_permission` VALUES ('1195350500512206850', '1095350299365969922', '科目管理', '1', null, 'list', '/edu/subject/list', null, 'el-icon-notebook-1', '0', '0', '1', '2022-07-27 10:13:51', '2022-07-27 10:13:51');
INSERT INTO `auth_permission` VALUES ('1195350687590748161', '1195350500512206850', '添加顶级科目', '2', 'subject.addTopSubject', '', '', null, null, '1', '1', '1', '2019-11-15 22:39:47', '2022-07-27 10:21:51');
INSERT INTO `auth_permission` VALUES ('1195350919074385921', '1095350831744782337', '课程列表', '1', null, 'list', '/edu/course/list', null, 'el-icon-tickets', '0', '0', '1', '2019-11-15 22:40:42', '2022-07-27 10:20:51');
INSERT INTO `auth_permission` VALUES ('1195351020463296513', '1095350831744782337', '添加课程', '1', null, 'courseSave', '/edu/course/info', null, 'el-icon-info', '0', '1', '1', '2019-11-15 22:41:06', '2022-07-27 10:20:34');
INSERT INTO `auth_permission` VALUES ('1195351159672246274', '1195350919074385921', '课程发布', '2', 'course.publish', 'publish/:id', '/edu/course/publish', null, null, '1', '3', '1', '2019-11-15 22:41:40', '2022-07-27 10:25:58');
INSERT INTO `auth_permission` VALUES ('1195351326706208770', '1195350919074385921', '课程信息编辑', '2', 'course.updateBasic', 'info/:id', '/edu/course/info', null, null, '1', '1', '1', '2019-11-15 22:42:19', '2022-07-27 10:25:50');
INSERT INTO `auth_permission` VALUES ('1195351566221938690', '1195350919074385921', '课程大纲编辑', '2', 'chapter.updateChapter', 'chapter/:id', '/edu/course/chapter', null, null, '1', '2', '1', '2019-11-15 22:43:17', '2022-07-27 10:25:54');
INSERT INTO `auth_permission` VALUES ('1195352547621965825', '1', 'banner管理', '1', null, '/banner', 'Layout', '/banner/list', 'el-icon-picture', '0', '8', '1', '2019-11-15 22:47:11', '2022-07-30 10:06:11');
INSERT INTO `auth_permission` VALUES ('1195354076890370050', '1', '订单管理', '1', null, '/order', 'Layout', '/order/have', 'el-icon-phone', '0', '9', '1', '2019-11-15 22:53:15', '2022-07-30 10:06:08');
INSERT INTO `auth_permission` VALUES ('1195354153482555393', '1195354076890370050', '已支付', '1', null, 'have', '/order/have', null, 'el-icon-s-goods', '0', '0', '1', '2019-11-15 22:53:33', '2022-07-27 10:40:25');
INSERT INTO `auth_permission` VALUES ('1195354315093282817', '1195354153482555393', '已支付查询', '2', 'order.haveQuery', '', '', null, null, '1', '0', '1', '2019-11-15 22:54:12', '2022-07-27 10:41:48');
INSERT INTO `auth_permission` VALUES ('1295351862889254913', '1', '数据统计', '1', null, '/statistics', 'Layout', '/statistics/chart', 'el-icon-pie-chart', '0', '7', '1', '2019-11-15 22:44:27', '2022-07-30 10:06:13');
INSERT INTO `auth_permission` VALUES ('1552105569295273985', '1', '权限管理', '1', null, '/auth', 'Layout', '/auth/user/list', 'el-icon-view', '0', '1', '1', '2022-07-27 09:36:19', '2022-07-30 10:06:02');
INSERT INTO `auth_permission` VALUES ('1552106542772260866', '1552105569295273985', '用户管理', '1', null, 'user/list', '/auth/user/list', null, 'el-icon-coordinate', '0', '0', '1', '2022-07-27 09:40:11', '2022-07-28 10:58:08');
INSERT INTO `auth_permission` VALUES ('1552107666724098050', '1552105569295273985', '角色管理', '1', null, 'role/list', '/auth/role/list', null, 'el-icon-s-check', '0', '1', '1', '2022-07-27 09:44:39', '2022-07-27 10:53:59');
INSERT INTO `auth_permission` VALUES ('1552107881950613506', '1552105569295273985', '菜单管理', '1', null, 'menu/list', '/auth/menu/list', null, 'el-icon-menu', '0', '2', '1', '2022-07-27 09:45:30', '2022-07-28 10:58:12');
INSERT INTO `auth_permission` VALUES ('1552115228722958337', '1195350500512206850', '添加子科目', '2', 'subject.addChildSubject', '', '', null, null, '1', '2', '1', '2022-07-27 10:14:42', '2022-07-27 10:21:54');
INSERT INTO `auth_permission` VALUES ('1552115810363871234', '1195350500512206850', '修改科目', '2', 'subject.update', '', '', null, null, '1', '3', '1', '2022-07-27 10:17:01', '2022-07-27 10:22:00');
INSERT INTO `auth_permission` VALUES ('1552116130569621505', '1195350500512206850', '删除科目', '2', 'subject.delete', '', '', null, null, '1', '4', '1', '2022-07-27 10:18:17', '2022-07-27 10:22:05');
INSERT INTO `auth_permission` VALUES ('1552117011172134914', '1195350500512206850', '科目查询', '2', 'subject.query', '', '', null, null, '1', '0', '1', '2022-07-27 10:21:47', '2022-07-27 10:21:47');
INSERT INTO `auth_permission` VALUES ('1552117569593380865', '1195350919074385921', '课程查询', '2', 'course.query', '', '', null, null, '1', '0', '1', '2022-07-27 10:24:00', '2022-07-27 10:24:00');
INSERT INTO `auth_permission` VALUES ('1552118302266986497', '1', '用户管理', '1', null, '/member', 'Layout', '/member/list', 'el-icon-user', '0', '5', '1', '2022-07-27 10:26:55', '2022-07-30 10:06:18');
INSERT INTO `auth_permission` VALUES ('1552118455799484417', '1552118302266986497', '用户列表', '1', null, 'list', '/ucenter/member/list', '', 'el-icon-user', '0', '0', '1', '2022-07-27 10:27:31', '2022-07-27 10:27:31');
INSERT INTO `auth_permission` VALUES ('1552118565908353025', '1552118455799484417', '用户查询', '2', 'member.query', '', '', null, null, '1', '0', '1', '2022-07-27 10:27:58', '2022-07-27 10:27:58');
INSERT INTO `auth_permission` VALUES ('1552118698767126529', '1552118455799484417', '用户禁启用', '2', 'member.endisable', '', '', null, null, '1', '1', '1', '2022-07-27 10:28:29', '2022-07-27 10:39:01');
INSERT INTO `auth_permission` VALUES ('1552119010651377665', '1', '评论管理', '1', null, '/comment', 'Layout', '/comment/list', 'el-icon-chat-dot-round', '0', '6', '1', '2022-07-27 10:29:44', '2022-07-30 10:06:16');
INSERT INTO `auth_permission` VALUES ('1552119253216366593', '1552119010651377665', '评论列表', '1', null, 'list', '/edu/comment/list', '', 'el-icon-tickets', '0', '0', '1', '2022-07-27 10:30:42', '2022-07-27 10:30:42');
INSERT INTO `auth_permission` VALUES ('1552119309122244609', '1552119253216366593', '评论查询', '2', 'comment.query', '', '', null, null, '1', '0', '1', '2022-07-27 10:30:55', '2022-07-27 10:30:55');
INSERT INTO `auth_permission` VALUES ('1552119375572602881', '1552119253216366593', '评论删除', '2', 'comment.delete', '', '', null, null, '1', '1', '1', '2022-07-27 10:31:11', '2022-07-27 10:31:11');
INSERT INTO `auth_permission` VALUES ('1552119865739939842', '1295351862889254913', '数据统计', '1', null, 'chart', '/statistics/daily/chart', '', 'el-icon-pie-chart', '0', '0', '1', '2022-07-27 10:33:08', '2022-07-27 10:33:08');
INSERT INTO `auth_permission` VALUES ('1552120362748186626', '1195352547621965825', 'banner列表', '1', null, 'list', '/cms/banner/list', '', 'el-icon-tickets', '0', '0', '1', '2022-07-27 10:35:06', '2022-07-27 10:35:06');
INSERT INTO `auth_permission` VALUES ('1552120500229083138', '1195352547621965825', '添加banner', '1', null, 'save', '/cms/banner/save', '', 'el-icon-circle-plus-outline', '0', '1', '1', '2022-07-27 10:35:39', '2022-07-27 10:35:39');
INSERT INTO `auth_permission` VALUES ('1552120580319318018', '1552120500229083138', '添加banner', '2', 'banner.add', '', '', null, null, '1', '0', '1', '2022-07-27 10:35:58', '2022-07-27 10:35:58');
INSERT INTO `auth_permission` VALUES ('1552120637546401793', '1552120362748186626', 'banner查询', '2', 'banner.query', '', '', null, null, '1', '0', '1', '2022-07-27 10:36:12', '2022-07-27 10:36:12');
INSERT INTO `auth_permission` VALUES ('1552120764914831362', '1552120362748186626', 'banner修改', '2', 'banner.edit', 'edit/:id', '/cms/banner/save', null, null, '1', '1', '1', '2022-07-27 10:36:42', '2022-07-27 10:36:42');
INSERT INTO `auth_permission` VALUES ('1552120843109240833', '1552120362748186626', 'banner删除', '2', 'banner.delete', '', '', null, null, '1', '3', '1', '2022-07-27 10:37:01', '2022-07-27 10:38:34');
INSERT INTO `auth_permission` VALUES ('1552121108243779585', '1552120362748186626', 'banner启禁用', '2', 'banner.endisable', '', '', null, null, '1', '2', '1', '2022-07-27 10:38:04', '2022-07-27 10:38:31');
INSERT INTO `auth_permission` VALUES ('1552121830221910018', '1195354076890370050', '未支付', '1', null, 'not', '/order/not', '', 'el-icon-goods', '0', '1', '1', '2022-07-27 10:40:56', '2022-07-27 10:40:56');
INSERT INTO `auth_permission` VALUES ('1552122003710906369', '1552121830221910018', '未支付查询', '2', 'order.notQuery', '', '', null, null, '1', '0', '1', '2022-07-27 10:41:37', '2022-07-27 10:41:37');
INSERT INTO `auth_permission` VALUES ('1552127189862887425', '1552107881950613506', '菜单添加', '2', 'menuPermission.add', '', '', null, null, '1', '0', '1', '2022-07-27 11:02:14', '2022-07-27 11:02:14');
INSERT INTO `auth_permission` VALUES ('1552127268241846274', '1552107881950613506', '菜单修改', '2', 'menuPermission.update', '', '', null, null, '1', '1', '1', '2022-07-27 11:02:33', '2022-07-27 11:02:38');
INSERT INTO `auth_permission` VALUES ('1552127352056623105', '1552107881950613506', '菜单删除', '2', 'menu.delete', '', '', null, null, '1', '4', '1', '2022-07-27 11:02:53', '2022-07-27 11:03:31');
INSERT INTO `auth_permission` VALUES ('1552127408507760641', '1552107881950613506', '按钮权限添加', '2', 'buttonPermission.add', '', '', null, null, '1', '2', '1', '2022-07-27 11:03:06', '2022-07-27 11:03:39');
INSERT INTO `auth_permission` VALUES ('1552127491701780481', '1552107881950613506', '按钮权限修改', '2', 'buttonPermission.update', '', '', null, null, '1', '3', '1', '2022-07-27 11:03:26', '2022-07-27 11:03:26');
INSERT INTO `auth_permission` VALUES ('1552127662758080514', '1552107666724098050', '角色查询', '2', 'role.query', '', '', null, null, '1', '0', '1', '2022-07-27 11:04:07', '2022-07-27 11:04:07');
INSERT INTO `auth_permission` VALUES ('1552128051196768258', '1552107666724098050', '角色修改', '2', 'role.update', 'role/edit/:id', '/auth/fole/form', null, null, '1', '2', '1', '2022-07-27 11:05:39', '2022-07-27 11:06:38');
INSERT INTO `auth_permission` VALUES ('1552128263273361410', '1552107666724098050', '角色添加', '2', 'role.add', 'role/add', '/auth/role/form', null, null, '1', '1', '1', '2022-07-27 11:06:30', '2022-07-27 11:06:30');
INSERT INTO `auth_permission` VALUES ('1552128424296886274', '1552107666724098050', '角色删除', '2', 'role.delete', '', '', null, null, '1', '3', '1', '2022-07-27 11:07:08', '2022-07-27 11:07:08');
INSERT INTO `auth_permission` VALUES ('1552128623962533890', '1552106542772260866', '用户添加', '2', 'user.add', '', '', null, null, '1', '1', '1', '2022-07-27 11:07:56', '2022-07-27 21:17:11');
INSERT INTO `auth_permission` VALUES ('1552128803453579265', '1552106542772260866', '用户修改', '2', 'user.update', '', '', null, null, '1', '2', '1', '2022-07-27 11:08:39', '2022-07-27 21:17:17');
INSERT INTO `auth_permission` VALUES ('1552128908076298241', '1552106542772260866', '用户删除', '2', 'user.delete', '', '', null, null, '1', '3', '1', '2022-07-27 11:09:04', '2022-07-27 11:09:04');
INSERT INTO `auth_permission` VALUES ('1552128995976327169', '1552106542772260866', '用户查询', '2', 'user.query', '', '', null, null, '1', '0', '1', '2022-07-27 11:09:24', '2022-07-27 11:09:24');
INSERT INTO `auth_permission` VALUES ('1552129230840573954', '1552106542772260866', '角色分配', '2', 'user.assignRole', '', '', null, null, '1', '4', '1', '2022-07-27 11:10:20', '2022-07-27 21:17:21');
INSERT INTO `auth_permission` VALUES ('1553200173847519234', '1', '仪表盘', '1', null, '/', 'Layout', '/dashboard', '', '0', '0', '1', '2022-07-30 10:05:53', '2022-07-30 10:07:15');
INSERT INTO `auth_permission` VALUES ('1553200478379155457', '1553200173847519234', '仪表盘', '1', null, 'dashboard', '/dashboard/index', '', 'dashboard', '0', '0', '1', '2022-07-30 10:07:06', '2022-07-30 10:07:06');
INSERT INTO `auth_permission` VALUES ('1553942062053785601', '1195350500512206850', '导出学科列表', '2', 'subject.export', '', '', null, null, '1', '5', '1', '2022-08-01 11:13:53', '2022-08-01 11:13:53');
INSERT INTO `auth_permission` VALUES ('1553943312572940289', '1552106542772260866', '用户禁启用', '2', 'user.enableOrDisable', '', '', null, null, '1', '5', '1', '2022-08-01 11:18:51', '2022-08-01 11:18:51');

-- ----------------------------
-- Table structure for auth_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_role`;
CREATE TABLE `auth_role` (
  `id` bigint NOT NULL DEFAULT '0' COMMENT '角色id',
  `role_name` varchar(20) NOT NULL DEFAULT '' COMMENT '角色名称',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `is_enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用（1：启用，0：禁止）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_role
-- ----------------------------
INSERT INTO `auth_role` VALUES ('1', '普通管理员', null, '1', '2022-07-27 10:29:17', '2022-07-30 10:00:17');
INSERT INTO `auth_role` VALUES ('1193757683205607426', '课程管理员', '课程管理的角色', '1', '2022-07-27 10:29:17', '2022-07-30 10:00:22');
INSERT INTO `auth_role` VALUES ('1196300996034977794', '师资运营管理员', null, '1', '2022-07-27 10:29:17', '2022-07-30 10:00:24');
INSERT INTO `auth_role` VALUES ('1543234745457025026', 'CMS管理', null, '1', '2022-07-27 10:29:17', '2022-07-30 10:09:35');
INSERT INTO `auth_role` VALUES ('1544508753213128705', '系统管理员', null, '1', '2022-07-27 10:29:17', '2022-07-30 10:28:27');

-- ----------------------------
-- Table structure for auth_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `auth_role_permission`;
CREATE TABLE `auth_role_permission` (
  `id` bigint NOT NULL DEFAULT '0' COMMENT '主键id',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色id',
  `permission_id` bigint NOT NULL DEFAULT '0' COMMENT '权限id',
  `is_enable` tinyint(1) DEFAULT '1' COMMENT '是否启用（1：启用，0：禁止）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_permission_id` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限';

-- ----------------------------
-- Records of auth_role_permission
-- ----------------------------
INSERT INTO `auth_role_permission` VALUES ('1552251960722206722', '1543234745457025026', '1', '1', '2022-07-27 19:18:02', '2022-07-30 10:09:35');
INSERT INTO `auth_role_permission` VALUES ('1552251960734789634', '1543234745457025026', '1195352547621965825', '1', '2022-07-27 19:18:02', '2022-07-30 10:09:35');
INSERT INTO `auth_role_permission` VALUES ('1552251960734789635', '1543234745457025026', '1552120362748186626', '1', '2022-07-27 19:18:02', '2022-07-30 10:09:35');
INSERT INTO `auth_role_permission` VALUES ('1552251960734789636', '1543234745457025026', '1552120637546401793', '1', '2022-07-27 19:18:02', '2022-07-30 10:09:35');
INSERT INTO `auth_role_permission` VALUES ('1552251960734789637', '1543234745457025026', '1552120764914831362', '1', '2022-07-27 19:18:02', '2022-07-30 10:09:35');
INSERT INTO `auth_role_permission` VALUES ('1552251960734789638', '1543234745457025026', '1552120843109240833', '1', '2022-07-27 19:18:02', '2022-07-30 10:09:35');
INSERT INTO `auth_role_permission` VALUES ('1552251960734789639', '1543234745457025026', '1552120500229083138', '1', '2022-07-27 19:18:02', '2022-07-30 10:09:35');
INSERT INTO `auth_role_permission` VALUES ('1552251960734789640', '1543234745457025026', '1552120580319318018', '1', '2022-07-27 19:18:02', '2022-07-30 10:09:35');
INSERT INTO `auth_role_permission` VALUES ('1552280424145813506', '1196300996034977794', '1', '1', '2022-07-27 21:11:08', '2022-07-30 10:00:24');
INSERT INTO `auth_role_permission` VALUES ('1552280424145813507', '1196300996034977794', '1195349439240048642', '1', '2022-07-27 21:11:08', '2022-07-30 10:00:24');
INSERT INTO `auth_role_permission` VALUES ('1552280424145813508', '1196300996034977794', '1195349699995734017', '1', '2022-07-27 21:11:08', '2022-07-30 10:00:24');
INSERT INTO `auth_role_permission` VALUES ('1552280424145813509', '1196300996034977794', '1195350117270261762', '1', '2022-07-27 21:11:08', '2022-07-30 10:00:24');
INSERT INTO `auth_role_permission` VALUES ('1552280424145813510', '1196300996034977794', '1195350188359520258', '1', '2022-07-27 21:11:08', '2022-07-30 10:00:24');
INSERT INTO `auth_role_permission` VALUES ('1552280424145813511', '1196300996034977794', '1195349810561781761', '1', '2022-07-27 21:11:08', '2022-07-30 10:00:24');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133505', '1', '1', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133506', '1', '1195349439240048642', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133507', '1', '1195349699995734017', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133508', '1', '1195350117270261762', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133509', '1', '1195350188359520258', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133510', '1', '1195349810561781761', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133511', '1', '1095350299365969922', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133512', '1', '1195350500512206850', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133513', '1', '1552117011172134914', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133514', '1', '1195350687590748161', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133515', '1', '1552115228722958337', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133516', '1', '1552115810363871234', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133517', '1', '1552116130569621505', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133518', '1', '1095350831744782337', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133519', '1', '1195350919074385921', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133520', '1', '1552117569593380865', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133521', '1', '1195351326706208770', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133522', '1', '1195351566221938690', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133523', '1', '1195351159672246274', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133524', '1', '1195351020463296513', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133525', '1', '1552118302266986497', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133526', '1', '1552118455799484417', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133527', '1', '1552118565908353025', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133528', '1', '1552118698767126529', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133529', '1', '1552119010651377665', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133530', '1', '1552119253216366593', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133531', '1', '1552119309122244609', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133532', '1', '1552119375572602881', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133533', '1', '1295351862889254913', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133534', '1', '1552119865739939842', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133535', '1', '1195352547621965825', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133536', '1', '1552120362748186626', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133537', '1', '1552120637546401793', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133538', '1', '1552120764914831362', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133539', '1', '1552121108243779585', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133540', '1', '1552120843109240833', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133541', '1', '1552120500229083138', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133542', '1', '1552120580319318018', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133543', '1', '1195354076890370050', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133544', '1', '1195354153482555393', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133545', '1', '1195354315093282817', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133546', '1', '1552121830221910018', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1552280497546133547', '1', '1552122003710906369', '1', '2022-07-27 21:11:25', '2022-07-30 10:00:17');
INSERT INTO `auth_role_permission` VALUES ('1553943850156937217', '1193757683205607426', '1', '1', '2022-08-01 11:20:59', '2022-08-01 11:20:59');
INSERT INTO `auth_role_permission` VALUES ('1553943850156937218', '1193757683205607426', '1552105569295273985', '1', '2022-08-01 11:20:59', '2022-08-01 11:20:59');
INSERT INTO `auth_role_permission` VALUES ('1553943850156937219', '1193757683205607426', '1552106542772260866', '1', '2022-08-01 11:20:59', '2022-08-01 11:20:59');
INSERT INTO `auth_role_permission` VALUES ('1553943850156937220', '1193757683205607426', '1552128995976327169', '1', '2022-08-01 11:20:59', '2022-08-01 11:20:59');
INSERT INTO `auth_role_permission` VALUES ('1553943850156937221', '1193757683205607426', '1552128623962533890', '1', '2022-08-01 11:20:59', '2022-08-01 11:20:59');
INSERT INTO `auth_role_permission` VALUES ('1553943850156937222', '1193757683205607426', '1552128803453579265', '1', '2022-08-01 11:20:59', '2022-08-01 11:20:59');
INSERT INTO `auth_role_permission` VALUES ('1553943850156937223', '1193757683205607426', '1552128908076298241', '1', '2022-08-01 11:20:59', '2022-08-01 11:20:59');
INSERT INTO `auth_role_permission` VALUES ('1553943850156937224', '1193757683205607426', '1552129230840573954', '1', '2022-08-01 11:20:59', '2022-08-01 11:20:59');
INSERT INTO `auth_role_permission` VALUES ('1553943850156937225', '1193757683205607426', '1553943312572940289', '1', '2022-08-01 11:20:59', '2022-08-01 11:20:59');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718018', '1544508753213128705', '1', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718019', '1544508753213128705', '1552105569295273985', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718020', '1544508753213128705', '1552106542772260866', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718021', '1544508753213128705', '1552107881950613506', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718022', '1544508753213128705', '1553200173847519234', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718023', '1544508753213128705', '1553200478379155457', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718024', '1544508753213128705', '1552128995976327169', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718025', '1544508753213128705', '1552128623962533890', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718026', '1544508753213128705', '1552128803453579265', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718027', '1544508753213128705', '1552129230840573954', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718028', '1544508753213128705', '1552107666724098050', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718029', '1544508753213128705', '1552127662758080514', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718030', '1544508753213128705', '1552128263273361410', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718031', '1544508753213128705', '1552128051196768258', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718032', '1544508753213128705', '1552128424296886274', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718033', '1544508753213128705', '1552127189862887425', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718034', '1544508753213128705', '1552127268241846274', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718035', '1544508753213128705', '1552127408507760641', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');
INSERT INTO `auth_role_permission` VALUES ('1555181022981718036', '1544508753213128705', '1552127491701780481', '1', '2022-08-04 21:17:04', '2022-08-04 21:17:04');

-- ----------------------------
-- Table structure for auth_user
-- ----------------------------
DROP TABLE IF EXISTS `auth_user`;
CREATE TABLE `auth_user` (
  `id` bigint NOT NULL DEFAULT '0' COMMENT '后台管理用户id',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `password` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '密码',
  `is_enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用（1：启用，0：禁止）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- ----------------------------
-- Records of auth_user
-- ----------------------------
INSERT INTO `auth_user` VALUES ('1', 'admin', '$2a$10$ezlrlPHY3UrPgyTTd9RiNunZmpIBZRzW/ddTbNxxKoGP.LfomqAq.', '1', '2019-11-01 10:39:47', '2022-07-04 20:07:09');
INSERT INTO `auth_user` VALUES ('2', 'mulrole', '$2a$10$2QoSOuQfNFrzqe48NzTCpuni8LzyZRcVKMJc5t0KKfJV4u5pMHr4S', '1', '2019-11-01 16:36:07', '2022-07-27 21:44:26');
INSERT INTO `auth_user` VALUES ('1543891383046131713', 'test1', '$2a$10$qTZrMJdrZovY5v/vXYTAeuYjskDH4lqZWRE1R0DJReIhKd7oB68cO', '1', '2022-07-04 17:36:04', '2022-07-06 10:30:25');
INSERT INTO `auth_user` VALUES ('1544509242046676993', 'sys', '$2a$10$8POw/Nb.EG69AdReKjMkBemZfUpg32lK7mZT.fXbL5EN21vtxZVR.', '1', '2022-07-06 10:31:14', '2022-07-06 10:33:12');
INSERT INTO `auth_user` VALUES ('1552517678151880706', '测试', '$2a$10$ffejyBQ2xr3JHnFjk/qJRO1bJ6KF1KQwjaRfSOnm4MdFWjQTn2ffW', '0', '2022-07-28 12:53:54', '2022-07-30 10:33:42');
INSERT INTO `auth_user` VALUES ('1553947923857833986', 'test', '$2a$10$8POw/Nb.EG69AdReKjMkBemZfUpg32lK7mZT.fXbL5EN21vtxZVR.', '1', '2022-08-01 11:37:11', '2022-08-01 11:37:11');

-- ----------------------------
-- Table structure for auth_user_role
-- ----------------------------
DROP TABLE IF EXISTS `auth_user_role`;
CREATE TABLE `auth_user_role` (
  `id` bigint NOT NULL DEFAULT '0' COMMENT '主键id',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色id',
  `user_id` bigint NOT NULL DEFAULT '0' COMMENT '用户id',
  `is_enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用（1：启用，0：禁止）',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_role_id` (`role_id`),
  KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of auth_user_role
-- ----------------------------
INSERT INTO `auth_user_role` VALUES ('1544509072462577665', '1196300996034977794', '1543891383046131713', '1', '2022-07-06 10:30:33', '2022-07-06 10:30:33');
INSERT INTO `auth_user_role` VALUES ('1552290425946046466', '1543234745457025026', '2', '1', '2022-07-27 21:50:52', '2022-07-27 21:50:52');
INSERT INTO `auth_user_role` VALUES ('1552290426000572417', '1', '2', '1', '2022-07-27 21:50:52', '2022-07-27 21:50:52');
INSERT INTO `auth_user_role` VALUES ('1553193510235107329', '1544508753213128705', '1552517678151880706', '0', '2022-07-30 09:39:24', '2022-07-30 10:33:42');
INSERT INTO `auth_user_role` VALUES ('1553944422901731329', '1544508753213128705', '1544509242046676993', '1', '2022-08-01 11:23:16', '2022-08-01 11:23:16');

-- ----------------------------
-- Table structure for cms_banner
-- ----------------------------
DROP TABLE IF EXISTS `cms_banner`;
CREATE TABLE `cms_banner` (
  `id` bigint NOT NULL DEFAULT '0' COMMENT '主键id',
  `title` varchar(20) DEFAULT '' COMMENT '标题',
  `image_url` varchar(500) NOT NULL DEFAULT '' COMMENT '图片地址',
  `link_url` varchar(500) DEFAULT '' COMMENT '链接地址',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_enable` tinyint unsigned NOT NULL DEFAULT '1' COMMENT '是否启用 1（true）已启用，  0（false）已禁用',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_name` (`title`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='首页banner表';

-- ----------------------------
-- Records of cms_banner
-- ----------------------------
INSERT INTO `cms_banner` VALUES ('1194607458461216769', 'Banner3', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/9dbca3ac71a411bcc851eb2c2c15aab9fc1d519b3af.jpg', '/teacher', '1', '1', '2019-11-13 21:26:27', '2022-07-26 10:47:19');
INSERT INTO `cms_banner` VALUES ('1544134598873591809', 'Banner2', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/c63782588ea67477019_p0.jpg', '', '4', '1', '2022-07-05 09:42:32', '2022-07-25 14:19:30');
INSERT INTO `cms_banner` VALUES ('1544149959601827842', 'Banners1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/cef7f209701fbc45fa8fc5d5e731e7f545edddfe17b.png', '', '3', '1', '2022-07-05 10:43:34', '2022-07-25 14:20:25');

-- ----------------------------
-- Table structure for edu_chapter
-- ----------------------------
DROP TABLE IF EXISTS `edu_chapter`;
CREATE TABLE `edu_chapter` (
  `id` bigint NOT NULL COMMENT '章节ID',
  `course_id` bigint NOT NULL COMMENT '课程ID',
  `title` varchar(50) NOT NULL COMMENT '章节名称',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '显示排序',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程';

-- ----------------------------
-- Records of edu_chapter
-- ----------------------------
INSERT INTO `edu_chapter` VALUES ('15', '18', '第一章：Java入门', '1', '2019-01-01 12:27:40', '2022-07-24 18:20:36');
INSERT INTO `edu_chapter` VALUES ('32', '18', '第二章：控制台输入和输出', '2', '2019-01-01 12:27:40', '2022-07-24 18:20:41');
INSERT INTO `edu_chapter` VALUES ('44', '18', '第三章：控制流', '3', '2019-01-01 12:27:40', '2022-07-24 18:20:45');
INSERT INTO `edu_chapter` VALUES ('48', '18', '第四章：类的定义', '4', '2019-01-01 12:27:40', '2022-07-24 18:20:47');
INSERT INTO `edu_chapter` VALUES ('63', '18', '第五章：数组', '5', '2019-01-01 12:27:40', '2022-07-24 18:20:51');
INSERT INTO `edu_chapter` VALUES ('64', '18', '第六章：继承', '6', '2019-01-01 12:27:40', '2022-07-24 18:21:32');
INSERT INTO `edu_chapter` VALUES ('1181729226915577857', '18', '第七章：I/O流', '7', '2019-10-09 08:32:58', '2022-07-24 18:21:36');
INSERT INTO `edu_chapter` VALUES ('1192252428399751169', '1192252213659774977', '第一章节', '0', '2019-01-01 09:28:25', '2019-11-07 09:28:25');
INSERT INTO `edu_chapter` VALUES ('1542078732305547266', '1542078687770427394', '第一章', '1', '2022-06-29 17:33:15', '2022-07-06 15:01:00');
INSERT INTO `edu_chapter` VALUES ('1542350339225681921', '1542350261626863618', '第一章', '0', '2022-06-30 11:32:31', '2022-06-30 11:32:31');
INSERT INTO `edu_chapter` VALUES ('1542350852163895298', '1542350261626863618', '第一章', '1', '2022-06-30 11:34:33', '2022-06-30 11:34:53');
INSERT INTO `edu_chapter` VALUES ('1544573403531358209', '1544573219212668930', '第一章', '1', '2022-07-06 14:46:11', '2022-07-06 14:46:11');
INSERT INTO `edu_chapter` VALUES ('1544574323715518466', '1544574274847682562', '第一章', '1', '2022-07-06 14:49:50', '2022-07-06 14:49:50');
INSERT INTO `edu_chapter` VALUES ('1544575828455632897', '1544575798948704258', '第一章', '1', '2022-07-06 14:55:49', '2022-07-06 14:55:49');
INSERT INTO `edu_chapter` VALUES ('1544576223538098177', '1544253113710534658', '第一章', '1', '2022-07-06 14:57:23', '2022-07-06 14:57:23');
INSERT INTO `edu_chapter` VALUES ('1544576412147560450', '1544251400744198146', '第一章', '1', '2022-07-06 14:58:08', '2022-07-06 14:58:08');
INSERT INTO `edu_chapter` VALUES ('1544576543467024386', '1544249768534876161', '第一章', '1', '2022-07-06 14:58:39', '2022-07-06 14:58:39');
INSERT INTO `edu_chapter` VALUES ('1544576682814386177', '1543881346387124226', '第一章', '1', '2022-07-06 14:59:13', '2022-07-06 14:59:13');
INSERT INTO `edu_chapter` VALUES ('1544576794835857410', '1543879917765238785', '第一章', '1', '2022-07-06 14:59:39', '2022-07-06 14:59:39');
INSERT INTO `edu_chapter` VALUES ('1544576915078164482', '1543879751771463681', '第一章', '1', '2022-07-06 15:00:08', '2022-07-06 15:00:08');
INSERT INTO `edu_chapter` VALUES ('1544577014143430658', '1542321527846887426', '第一章', '1', '2022-07-06 15:00:32', '2022-07-06 15:00:32');
INSERT INTO `edu_chapter` VALUES ('1545215098040094722', '1545215056076083201', '第一章', '0', '2022-07-08 09:16:03', '2022-07-08 09:16:03');

-- ----------------------------
-- Table structure for edu_comment
-- ----------------------------
DROP TABLE IF EXISTS `edu_comment`;
CREATE TABLE `edu_comment` (
  `id` bigint NOT NULL COMMENT '评论id',
  `course_id` bigint NOT NULL COMMENT '课程id',
  `member_id` bigint NOT NULL COMMENT '会员id',
  `nickname` varchar(50) DEFAULT NULL COMMENT '会员昵称',
  `avatar` varchar(255) DEFAULT NULL COMMENT '会员头像',
  `content` varchar(500) DEFAULT NULL COMMENT '评论内容',
  `star` tinyint DEFAULT '5' COMMENT '评论星级',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论';

-- ----------------------------
-- Records of edu_comment
-- ----------------------------
INSERT INTO `edu_comment` VALUES ('1194499162790211585', '1192252213659774977', '1', '小三123', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '课程很好', '5', '0', '2019-11-13 14:16:08', '2019-11-13 14:16:08');
INSERT INTO `edu_comment` VALUES ('1194898406466420738', '1192252213659774977', '1', '小三123', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '11', '2', '0', '2019-11-14 16:42:35', '2019-11-14 16:42:35');
INSERT INTO `edu_comment` VALUES ('1194898484388200450', '1192252213659774977', '1', '小三123', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '111', '5', '0', '2019-11-14 16:42:53', '2019-11-14 16:42:53');
INSERT INTO `edu_comment` VALUES ('1195251020861317122', '1192252213659774977', '1', '狗剩儿', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '2233', '5', '0', '2019-11-15 16:03:45', '2019-11-15 16:03:45');
INSERT INTO `edu_comment` VALUES ('1195251382720700418', '1192252213659774977', '1', '狗剩儿', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '4455', '5', '0', '2019-11-15 16:05:11', '2019-11-15 16:05:11');
INSERT INTO `edu_comment` VALUES ('1195252819177570306', '1192252213659774977', '1', '小三1231', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '55', '1', '0', '2019-11-15 16:10:53', '2019-11-15 16:10:53');
INSERT INTO `edu_comment` VALUES ('1195252899448160258', '1192252213659774977', '1', '小三1231', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '55', '5', '0', '2019-11-15 16:11:13', '2019-11-15 16:11:13');
INSERT INTO `edu_comment` VALUES ('1195252920587452417', '1192252213659774977', '1', '小三1231', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '223344', '5', '0', '2019-11-15 16:11:18', '2019-11-15 16:11:18');
INSERT INTO `edu_comment` VALUES ('1196264505170767874', '1192252213659774977', '1', '小三1231', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', '666666', '5', '0', '2019-11-18 11:10:58', '2019-11-18 11:10:58');
INSERT INTO `edu_comment` VALUES ('1543977997285986305', '1542350261626863618', '1543964673588637698', '你痴', 'https://img.51miz.com/Element/00/88/08/84/72f298b9_E880884_d0f63115.png', '课程评论', '5', '0', '2022-07-04 23:20:15', '2022-07-04 23:20:15');
INSERT INTO `edu_comment` VALUES ('1544500240986476545', '1192252213659774977', '1544496704110297089', '狗剩儿', 'https://cquoes.oss-cn-hangzhou.aliyuncs.com/2022/07/06/16f86566616爷只属于你.jpg', '嗨嗨嗨\n', '5', '0', '2022-07-06 09:55:28', '2022-07-06 09:55:28');
INSERT INTO `edu_comment` VALUES ('1544500479797563393', '1543879917765238785', '1544496704110297089', '狗剩儿', 'https://cquoes.oss-cn-hangzhou.aliyuncs.com/2022/07/06/16f86566616爷只属于你.jpg', '怎么要花钱啊', '5', '0', '2022-07-06 09:56:24', '2022-07-06 09:56:24');
INSERT INTO `edu_comment` VALUES ('1544510827887915009', '1542078687770427394', '1543964673588637698', '你痴', 'https://img.51miz.com/Element/00/88/08/84/72f298b9_E880884_d0f63115.png', '1111', '5', '0', '2022-07-06 10:37:32', '2022-07-06 10:37:32');
INSERT INTO `edu_comment` VALUES ('1545034878322479106', '1192252213659774977', '1545034536738373633', '好起来了', 'https://img.51miz.com/Element/00/88/08/84/72f298b9_E880884_d0f63115.png', '怎么要收费啊', '5', '0', '2022-07-07 21:19:55', '2022-07-07 21:19:55');
INSERT INTO `edu_comment` VALUES ('1551621140647084034', '15', '1545034536738373633', '好起来了', 'https://img.51miz.com/Element/00/88/08/84/72f298b9_E880884_d0f63115.png', '评论测试', '5', '0', '2022-07-26 01:31:22', '2022-07-26 01:31:22');

-- ----------------------------
-- Table structure for edu_course
-- ----------------------------
DROP TABLE IF EXISTS `edu_course`;
CREATE TABLE `edu_course` (
  `id` bigint NOT NULL COMMENT '课程ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程标题',
  `teacher_id` bigint NOT NULL COMMENT '课程讲师ID',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程封面图片路径',
  `description` text NOT NULL COMMENT '课程描述',
  `subject_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '具体学科下的课程类别',
  `type_id` char(19) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '课程专业ID',
  `price` decimal(10,2) unsigned NOT NULL DEFAULT '0.00' COMMENT '课程销售价格，设置为0则可免费观看',
  `lesson_num` int unsigned NOT NULL DEFAULT '0' COMMENT '总课时',
  `sale_count` bigint unsigned NOT NULL DEFAULT '0' COMMENT '销售数量',
  `view_count` bigint unsigned NOT NULL DEFAULT '0' COMMENT '浏览数量',
  `version` bigint unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '课程状态 0未发布 1已发布',
  `is_deleted` tinyint DEFAULT NULL COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_title` (`title`),
  KEY `idx_subject_id` (`type_id`),
  KEY `idx_teacher_id` (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程';

-- ----------------------------
-- Records of edu_course
-- ----------------------------
INSERT INTO `edu_course` VALUES ('15', 'HTML5页面前端设计', '1543883782568910850', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/1716d50712eSnipaste_2022-07-25_14-41-21.jpg', '<p style=\"margin: 0px; padding: 0px; outline: none; color: #666666; font-family: \'Helvetica Neue\', Helvetica, Arial, sans-serif; font-size: 12px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-transform: none; white-space: pre-wrap; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; text-indent: 28px; line-height: 27px;\"><span style=\"overflow-wrap: break-word; white-space: pre-wrap; font-size: 14px; color: black;\">新一轮科技革命与信息技术革命的到来，推动了产业机构调整与经济转型升级发展新业态的出现</span><span style=\"overflow-wrap: break-word; white-space: pre-wrap; font-size: 14px; color: black;\">。从PC端到移动端，再到新显示技术、智能机器人等新兴领域，都需要使用Web前端开发技术，Web前端开发人员人才需求量巨大。&nbsp;&nbsp;</span></p>\n<p style=\"margin: 0px; padding: 0px; outline: none; color: #666666; font-family: \'Helvetica Neue\', Helvetica, Arial, sans-serif; font-size: 12px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-transform: none; white-space: pre-wrap; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; text-indent: 28px; line-height: 27px;\"><span style=\"overflow-wrap: break-word; white-space: pre-wrap; font-size: 14px; color: black;\">&nbsp;HTML5</span><span style=\"overflow-wrap: break-word; white-space: pre-wrap; font-size: 14px; color: black;\">是目前主流的Web前端语言。《HTML5网页前端设计》课程在Web前端开发（群）（网页设计与制作、移动网页制作与设计、HTML5开发、JavaScript开发、jQuery框架应用）职业能力分析基础上选取教学内容，全面覆盖Web前端初级岗位所需技能，按照&ldquo;搭建网页结构&mdash;&mdash;美化网页效果&mdash;&mdash;添加网页交互&rdquo;的网站设计工作过程，精心设计 &ldquo;HTML5、CSS3、JavaScript和jQuery\"3个教学模块，涵盖&ldquo;网页设计、移动端响应式网页制作、CSS3高级应用、JavaScript和jQuery页面交互&rdquo;这些实用技能知识。</span></p>\n<p style=\"margin: 0px; padding: 0px; outline: none; color: #666666; font-family: \'Helvetica Neue\', Helvetica, Arial, sans-serif; font-size: 12px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-transform: none; white-space: pre-wrap; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; text-indent: 28px; line-height: 27px;\"><span style=\"overflow-wrap: break-word; white-space: pre-wrap; font-size: 14px; color: black;\">课程由基础知识点+实践开发案例结合组成，由浅入深，规划出&ldquo;知识点应用场景&mdash;知识点基础学习&mdash;典型案例练习知识点&mdash;单元案例综合知识点&mdash;Web前端开发职业技能等级考试实操项目强化技能点&mdash;企业级应用案例拓展知识点&rdquo;的渐进式学习路线，<span style=\"overflow-wrap: break-word; white-space: pre-wrap; color: #000000; text-indent: 28px;\">带领您实现Web前端开发从入门到实战。</span>课程组精心设计由&ldquo;知识型案例、单元案例、职业技能等级考试实操项目、企业级应用案例&rdquo;组成的四级案例体系，知识点均内嵌于案例中，可以由案例快速入门、带动知识点学习，培养较强的实践能力，所有案例源代码均可以在线下载。</span></p>\n<p style=\"margin: 0px; padding: 0px; outline: none; color: #666666; font-family: \'Helvetica Neue\', Helvetica, Arial, sans-serif; font-size: 12px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-transform: none; white-space: pre-wrap; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; text-indent: 28px; line-height: 27px;\"><span style=\"overflow-wrap: break-word; white-space: pre-wrap; font-size: 14px; color: black;\">课程支持个性化学习方案,面向两种不同基础的学习者。每一单元由基础知识&mdash;&mdash;Web2.0基础知识（HTML4、CSS2、JavaScript）和进阶知识（HTML5、CSS3、jQuery）组成。零基础的学习者，建议从头开始学习；有一定Web2.0基础的同学，可以在每一单元中直接进入HTML5、CSS3、jQuery新内容部分学习，重点领略HTML5、CSS3、jQuery新技术的魅力。</span></p>\n<p style=\"margin: 0px; padding: 0px; outline: none; color: #666666; font-family: \'Helvetica Neue\', Helvetica, Arial, sans-serif; font-size: 12px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-transform: none; white-space: pre-wrap; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; text-indent: 28px; line-height: 27px;\"><span style=\"overflow-wrap: break-word; white-space: pre-wrap; font-size: 14px; color: black;\">通过课程学习，学习者掌握Web前端开发基础知识，具备静态网站搭建、移动端网站开发、网页美化、网页交互设计等能力，胜任Web前端开发初级岗位。</span></p>', '1178214681181483010', '1178585108454121473', '0.00', '20', '0', '51', '17', '1', '0', '2018-04-02 18:34:32', '2022-07-25 14:42:48');
INSERT INTO `edu_course` VALUES ('18', 'Java语言程序', '1189390295668469762', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p>本套Java视频完全针对零基础学员，课堂实录，自发布以来，好评如潮！Java视频中注重与学生互动，讲授幽默诙谐、细致入微，覆盖Java基础所有核心知识点，同类Java视频中也是代码量大、案例多、实战性强的。同时，本Java视频教程注重技术原理剖析，深入JDK源码，辅以代码实战贯穿始终，用实践驱动理论，并辅以必要的代码练习。</p>\n<p>------------------------------------</p>\n<p>视频特点：</p>\n<p>通过学习本Java视频教程，大家能够真正将Java基础知识学以致用、活学活用，构架Java编程思想，牢牢掌握\"源码级\"的Javase核心技术，并为后续JavaWeb等技术的学习奠定扎实基础。<br /><br />1.通俗易懂，细致入微：每个知识点高屋建瓴，深入浅出，简洁明了的说明问题<br />2.具实战性：全程真正代码实战，涵盖上百个企业应用案例及练习<br />3.深入：源码分析，更有 Java 反射、动态代理的实际应用等<br />4.登录尚硅谷官网，技术讲师免费在线答疑</p>', '1178214681118568449', '1178214681139539969', '0.01', '20', '151', '737', '6', '1', '0', '2018-04-02 21:28:46', '2022-07-25 14:43:30');
INSERT INTO `edu_course` VALUES ('1192252213659774977', 'JavaWeb', '1192327476087115778', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '</span></span></strong></h5>', '1178214681118568449', '1178214681139539969', '0.01', '2', '4', '387', '1', '1', '0', '2019-11-07 09:27:33', '2022-07-06 16:15:45');
INSERT INTO `edu_course` VALUES ('1542078687770427394', '生物大数据', '1189426464967995393', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p style=\"margin: 8px 0px 0px; padding: 0px; outline: none; color: #666666; font-family: \'Helvetica Neue\', Helvetica, Arial, sans-serif; font-size: 12px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-transform: none; white-space: pre-wrap; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; text-indent: 32px; line-height: 27px;\"><span style=\"overflow-wrap: break-word; white-space: pre-wrap; font-size: 16px; font-family: 楷体;\">由省级优秀教师、中科院专家及上市企业技术专家担纲主讲，系统介绍当前生命科学研究及资本追逐的热词&mdash;生物大数据，讲授其所蕴含的价值与挖掘利用技术。课程特色之一是内容新颖。本课程是应生物基因组测序技术的发展而创建，面向新一代测序技术所获得的生物大数据，国内外尚无同类课程上线，而一些大型公司更是将其作为高端收费类培训课程。课程特色之二是主讲教师结构合理。由高校教师、中科院研究人员和企业技术人员组成，强强联合、优势互补，使课程内容既具理论高度，又具实用价值。课程特色之三是兼具通识与专业。本课程涵盖通识类知识点和专业类知识点，通识类知识有助于大众学生了解生物大数据的特点、价值与应用，专业类知识帮助学生掌握生物大数据的分析技能。</span></p>\n<p>&nbsp;</p>', '1178214681483472898', '1178214681529610242', '0.00', '12', '0', '0', '1', '1', '0', '2022-06-29 17:33:04', '2022-07-06 16:22:44');
INSERT INTO `edu_course` VALUES ('1542321527846887426', '大数据算法', '1541715434795991042', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;大数据算法这门课程旨在通过讲授一些大数据上基本算法设计思想，包括概率算法、I/O有效算法和并行算法，让听课的同学们接触到和传统算法课程不一样的算法设计与分析思路，并且以最新的研究成果为导向，让参与这门课程学习的同学了解大数据算法的前沿知识。通过这门课程的学习，同学可以掌握大数据算法设计的基本思想，并通过本门课程的作业，掌握大数据算法设计与分析的技术。</p>', '1178214681483472898', '1178214681504444418', '0.00', '10', '0', '0', '1', '1', '0', '2022-06-30 09:38:02', '2022-07-06 16:22:20');
INSERT INTO `edu_course` VALUES ('1542350261626863618', '数据库原理', '1541683208956002306', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<div id=\"j-rectxt2\" class=\"course-heading-intro_intro\" style=\"margin: 0px 0px 10px; padding: 0px; outline: none; font-size: 14px; color: #333333; font-family: \'Microsoft YaHei\', 微软雅黑, Helvetica, \'sans-serif\'; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: start; text-indent: 0px; text-transform: none; white-space: normal; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial;\">诚如一句名言所说：&ldquo;信息社会的生存就是数字化生存&rdquo;，而数字化符号的本质则在于富有价值的&ldquo;数据&rdquo;。可以说，&ldquo;数据&rdquo;已成为人类生存的依据和时代的象征。 这也就是我们为什么要学习《数据库原理及应用》这样一门课程的原由所在。数字化的生存决定了我们要生活在充满数据的时代，即所谓的大数据时代。数据知识，即数据理论和数据技术，构成了这个时代的新的知识模式和生活模式。而数据库，就是人们为了描述和掌握这个数据的世界所建立的一门学问和一门技术。《数据库原理及技术》是有关数据的筛选、贮存、调用、运算、分布、保全的理论与技术，是从信息科学、计算机科学发展出来的、适应数据时代社会需求的一门基础课程。</div>', '1178214681399586817', '1178214681428946945', '0.00', '20', '0', '1', '1', '1', '0', '2022-06-30 11:32:13', '2022-07-06 16:16:24');
INSERT INTO `edu_course` VALUES ('1543879751771463681', '人工智能概论', '1541683545473400833', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p>智创未来，未来已来，这是一个人工智能的时代。</p>\n<p>《人工智能概论》课程是一门引领学生进入人工智能领域的基础课程，让学生了解人工智能的概念和发展简史，理解人工智能三大流派的主要特点，并熟悉人工智能的主要研究内容和应用领域，包括计算机视觉、语音智能与自然语言处理等。同时，本课程对人工智能中使用的主要关键技术进行了介绍，主要包括：知识表示、专家系统、知识图谱、搜索技术、机器学习、人工神经网络和深度学习、机器人与智能驾驶等等。课程突出应用性，并提供了多个实践环节的演示视频。</p>\n<p>本课程可以供人文社科、理工类等学科、专业的学生学习。</p>', '1178214681554776066', '1178214681584136193', '0.00', '30', '0', '0', '1', '1', '0', '2022-07-04 16:49:51', '2022-07-06 16:21:52');
INSERT INTO `edu_course` VALUES ('1543879917765238785', '人工智能原理', '1540507162831323137', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;人工智能是国内外著名大学计算机专业设置的骨干课之一，也是国内外著名高校和研究机构的主要研究方向之一。人工智能研究如何用计算机软件和硬件去实现Agent的感知、决策与智能行为，其理论基础表现为搜索、推理、规划和学习，应用领域包括计算机视觉、图像分析、模式识别、专家系统、自动规划、智能搜索、计算机博弈、智能控制、机器人学、自然语言处理、社交网络、数据挖掘、虚拟现实等。</p>\n<p>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;本课程在系统回顾人工智能发展历程的基础上，重点介绍人工智能的核心思想、基本理论，基本方法与部分应用。 课程以该英文原版教材为主，并根据人工智能、特别是机器学习领域的发展和变化，编撰和充实了大量的内容。本课程共有12讲，采用双语教学，即中英文PPT和中英文作业等、中文讲授和交流。</p>', '1178214681554776066', '1178214681584136193', '1.00', '24', '0', '0', '1', '1', '0', '2022-07-04 16:50:31', '2022-07-06 16:21:34');
INSERT INTO `edu_course` VALUES ('1543881346387124226', '电子商务前端开发技术', '1542781526742257665', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p>本课程以移动商务客户端开发为载体，围绕移动电商交易过程，设计、制作相关前端页面。</p>\n<p>通过本课程实践，学生将完成列表、分类、搜索、购物车、订单、我的等页面制作，构建一个属于自己的移动电商小程序。</p>\n<p>课程目标：带领学习者制作移动电商小程序，建议学习者，对照下面目标定向学习。</p>', '1178214681181483010', '1178214681210843137', '0.00', '56', '0', '0', '1', '1', '0', '2022-07-04 16:56:12', '2022-07-06 16:18:33');
INSERT INTO `edu_course` VALUES ('1544249768534876161', 'Web前端开发', '1542782691915714562', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p>&ldquo;Web系统&rdquo;就是基于网站形式的具有一定功能的系统，例如新浪、淘宝、携程等。这些系统中以网页形式呈现给用户的部分，就是&ldquo;前端&rdquo;。随着Web系统的普遍应用，包括移动端APP的快速发展，&ldquo;Web前端开发&rdquo;已经成为近些年最为重要的技术之一，人才需求量巨大。本门课程将介绍Web系统前端开发的核心技术。</p>\n<p>课程内容涵盖Web系统前端开发技术的HTML5、CSS3、JS、JQuery、Bootstrap等组成部分，其中HTML决定网页的结构，CSS决定网页的样式，JS决定网页的交互。课程采用其最新技术标准与应用。</p>\n<p><br />课程以校企联合主讲的形式，两位主讲教师分别来自于北京林业大学与达内时代科技集团，更好地融合了学院派的理论与企业级的应用，让课程深入浅出的由基础过渡到实际应用。</p>\n<p>课程由百度前端技术学院负责人提供技术支持，在课程最后将针对未来的学习路线与前端工程师需要具备技能与素质等问题进行讲解。</p>\n<p>课程顾问团队包括三位教师，以及助理研究生一名，将对课程内容、教学、练习、答疑、考试、评价等环节进行全方位支持与服务。</p>\n<p>课程包括知识点、知识型案例、综合案例与企业实际案例，可支持两种主线进行学习，一种是以知识点为主，循序渐进的方式；一种案例式教学，课程中所有知识点均内嵌于案例中，可以由案例快速入门，带动知识点系统学习。在第二种方式下，也可以将部分知识点作为SPOC中，课下学习的部分。所有案例的源代码均可以在线下载。</p>\n<p>课程知识点按照难易程度，分为两个等级，支持两种不同基础的学习。例如零基础的同学，建议从&ldquo;Web前端开发&rdquo;概论部分开始学习，有一定基础的同学，可以直接进入HTML部分学习，从而可以有足够学时延伸到JQuery、HTML5部分。</p>', '1178214681181483010', '1178585108454121473', '0.00', '10', '0', '0', '1', '1', '0', '2022-07-05 17:20:10', '2022-07-06 16:18:19');
INSERT INTO `edu_course` VALUES ('1544251400744198146', '数据库系统概论（高级篇）', '1192249914833055746', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p>&ldquo;数据库系统概论&rdquo;是计算机科学与技术专业、软件工程专业、信息系统与信息管理等专业重要的专业基础课程，也是非计算机专业学生的信息技术必选课程。</p>\n<p>本课程将系统全面地讲述数据库系统的基础理论、基本技术和基本方法。本课程的知识内容和技术方法，对从事现代大数据管理技术的应用、开发和研究的人员都是重要而必备的基础。</p>\n<p>本课程内容丰富全面，分为基础篇（2022年2月21日开课）和高级篇2部分讲解，作为2门课程考核和计分。此外，本课程还开设了新技术篇（2022年3月21日开课），从数据管理和数据分析的角度讨论数据库新技术与大数据技术。</p>\n<p>通过数据库系统概论（基础篇）的学习，学员可以系统地掌握数据库系统的基本原理，能熟练使用SQL语言在某一个数据库管理系统上进行数据库检索和操作，掌握数据库安全性和完整性的基本概念和基本方法，并能够在某一个数据库管理系统上进行实验，培养学员熟练使用数据库的能力。</p>\n<p>通过数据库系统概论（高级篇）的学习，学员可以系统地掌握数据库规范化理论和数据库设计的方法与步骤，具有设计和开发数据库应用系统的基本能力；初步掌握数据库查询处理和优化的概念、掌握数据库事务处理、并发控制与恢复的基本技术，并能够在某一个数据库管理系统上进行实验验证，培养学员管理、维护和优化大型数据库系统的能力。</p>\n<p>通过数据库系统概论（新技术篇）的学习，学员可以系统地掌握传统数据库技术的最新发展，大数据管理与系统的新概念、新技术和新应用。</p>\n<p>数据库系统概论（高级篇）从应用或使用数据库，从管理DBMS的角度来讲解关系数据理论、数据库设计方法、数据库编程和DBMS中有关查询处理、事务管理的基本概念和基本技术。课程分为数据库设计与应用开发、DBMS系统两大部分。前者讲解在开发应用系统中如何在已经选定的DBMS基础上设计数据库，如何基于数据库系统编程。作为数据库设计的重要指南，在这一部分开始，首先讲解了关系数据理论。后者则从使用和管理DBMS的角度，讨论DBMS的查询处理，事务管理，数据库恢复和并发控制等基本概念和基本技术，没有涉及具体的实现技术细节。</p>\n<p>本课程的特点是，理论联系实际。我们不仅希望学员通过阅读和书面习题掌握本课程的内容，还要求学员完成实验项目。为此我们针对课程知识点设置了相应的实验，锻炼学员实际动手能力，启发学员对理论知识的思考和理解，达到理论联系实际的教学效果。</p>', '1178214681399586817', '1178214681428946945', '0.00', '12', '0', '0', '1', '1', '0', '2022-07-05 17:26:39', '2022-07-06 16:18:05');
INSERT INTO `edu_course` VALUES ('1544253113710534658', '数据库系统概论（基础篇）', '1542781858721419266', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p>&ldquo;数据库系统概论&rdquo;是计算机科学与技术专业、软件工程专业、信息系统与信息管理等专业重要的专业基础课程。</p>\n<p>本课程将系统讲述数据库系统的基础理论、基本技术和基本方法。本课程的知识内容和技术方法，对从事现代数据管理技术的应用、开发和研究的人员都是重要而必备的基础。</p>\n<p>本课程内容丰富全面，分为基础篇和高级篇（2022年3月7日开课）2部分讲解，作为2门课程考核和计分。此外，本课程还开设了新技术篇（2022年3月21日开课），从数据管理和数据分析的角度讨论数据库新技术与大数据技术。</p>\n<p>通过数据库系统概论（基础篇）的学习，学员可以系统地掌握数据库系统的基本原理，能熟练使用SQL语言在某一个数据库管理系统上进行数据库检索和操作，掌握数据库安全性和完整性的基本概念和基本方法。并能够在某一个数据库管理系统上进行实验。</p>\n<p>通过数据库系统概论（高级篇）的学习，学员可以系统地掌握数据库规范化理论和数据库设计的方法与步骤，具有设计和开发数据库应用系统的基本能力；掌握数据库事务处理、并发控制与恢复的基本技术、初步掌握数据库查询处理和优化的概念。并能够在某一个数据库管理系统上进行实验验证。</p>\n<p>通过数据库系统概论（新技术篇）的学习，学员可以系统地掌握传统数据库技术的最新发展，大数据管理与系统的新概念、新技术和新应用。</p>\n<p>本课程的特点是，理论联系实际。我们不仅希望学员通过阅读和书面习题掌握本课程的内容，还要求学员完成实验项目。为此我们针对课程知识点设置了相应的实验，锻炼学员实际动手能力，启发学员对理论知识的思考和理解，达到理论联系实际的教学效果。</p>', '1178214681399586817', '1178214681428946945', '0.00', '10', '0', '0', '1', '1', '0', '2022-07-05 17:33:28', '2022-07-06 16:17:49');
INSERT INTO `edu_course` VALUES ('1544573219212668930', '云计算导论', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p><span style=\"color: #4f4f4f; font-family: 宋体; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: pre-wrap; widows: 1; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">&nbsp;&nbsp;&nbsp;了解云计算的基本概念、了解虚拟化技术的基本概念、了解虚拟化技术、了解云部署模式、理解云计算机制。通过课堂讲解与讨论、案例分析等促进学生对所学的理论和运用，以培养其实际操作技能。在课程内容选择上，本课程涉及很多与计算技术的基础知识，其中云计算的关键技术，云计算关键技术包括虚拟化技术、分布式海量存储技术、云平台技术。</span></p>', '1178214681231814658', '1178214681252786178', '0.00', '30', '0', '0', '1', '1', '0', '2022-07-06 14:45:27', '2022-07-06 16:17:34');
INSERT INTO `edu_course` VALUES ('1544574274847682562', '云计算基础架构', '1189426464967995393', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p><span style=\"color: #000000; font-family: 宋体; font-size: 16px; font-style: normal; font-variant-ligatures: normal; font-variant-caps: normal; font-weight: 400; letter-spacing: normal; orphans: 2; text-align: left; text-indent: 0px; text-transform: none; white-space: pre-wrap; widows: 2; word-spacing: 0px; -webkit-text-stroke-width: 0px; background-color: #ffffff; text-decoration-thickness: initial; text-decoration-style: initial; text-decoration-color: initial; display: inline !important; float: none;\">&nbsp;&nbsp;&nbsp;通过本课程的学习，使学生能够在已有的计算机基础知识的基础上，对云计算基础架构有一个系统的、全面的了解，掌握架构一个私有云所必需的基本理论、基本知识和基本技能，从而能成功架构一个企业的私用云。通过线上线下相结合的混合模式，让学生能够自主调整学习进度，根据自身学情更好的进行学习。</span></p>', '1178214681231814658', '1178214681294729217', '0.00', '30', '0', '0', '1', '1', '0', '2022-07-06 14:49:39', '2022-07-06 16:17:20');
INSERT INTO `edu_course` VALUES ('1544575798948704258', '面向对象程序设计——Java语言', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p>用对象思考，用类来写作。这门课又可以被称作《Java语言程序设计进阶》。</p>\n<p>Java语言是一种面向对象语言，是业界使用最为广泛的语言，十二年前就占据了1/4的编程语言份额，到今天仍然以1/5的比例站在编程语言排行榜的前列。</p>\n<p>本课程将以Java语言为背景来介绍面向对象程序设计的知识、理念和手段。</p>', '1178214681613496321', '1178214681626079234', '0.00', '10', '0', '0', '1', '1', '0', '2022-07-06 14:55:42', '2022-07-06 16:16:42');
INSERT INTO `edu_course` VALUES ('1545215056076083201', 'C语言程序设计', '1542782691915714562', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '<p>程序设计是一门基础课程。对于计算机相关专业而言，程序设计是专业基础知识，是进一步学习其他专业知识的第一步阶梯；对于非计算机专业而言，程序设计的学习有助于理解计算机的能力所在，理解哪些是计算机擅长解决的问题，怎样的方式方法是计算机擅长的手段，从而能更好地利用计算机来解决本专业领域内的问题。</p>\n<p>C语言是古老而长青的编程语言，它具备了现代程序设计的基础要求，它的语法是很多其他编程语言的基础，在系统程序、嵌入式系统等领域依然是无可替代的编程语言，在各类编程语言排行榜上常年占据前两名的位置。</p>', '1178214681613496321', '1545214695441436674', '0.00', '10', '0', '0', '1', '1', '0', '2022-07-08 09:15:53', '2022-07-08 09:16:26');

-- ----------------------------
-- Table structure for edu_course_collect
-- ----------------------------
DROP TABLE IF EXISTS `edu_course_collect`;
CREATE TABLE `edu_course_collect` (
  `id` bigint NOT NULL COMMENT '收藏ID',
  `course_id` bigint NOT NULL COMMENT '课程id',
  `member_id` bigint NOT NULL COMMENT '会员id',
  `is_deleted` tinyint NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程收藏';

-- ----------------------------
-- Records of edu_course_collect
-- ----------------------------
INSERT INTO `edu_course_collect` VALUES ('1196269345666019330', '1192252213659774977', '1', '1', '2019-11-18 11:30:12', '2019-11-18 11:30:12');

-- ----------------------------
-- Table structure for edu_subject
-- ----------------------------
DROP TABLE IF EXISTS `edu_subject`;
CREATE TABLE `edu_subject` (
  `id` bigint NOT NULL COMMENT '课程类别ID',
  `pid` bigint NOT NULL DEFAULT '0' COMMENT '父课程ID',
  `title` varchar(10) NOT NULL COMMENT '类别名称',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_parent_id` (`pid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程科目';

-- ----------------------------
-- Records of edu_subject
-- ----------------------------
INSERT INTO `edu_subject` VALUES ('1178214681118568449', '0', '后端开发', '1', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681139539969', '1178214681118568449', 'Java', '1', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681181483010', '0', '前端开发', '3', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681210843137', '1178214681181483010', 'JavaScript', '4', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681231814658', '0', '云计算', '5', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681252786178', '1178214681231814658', 'Docker', '5', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681294729217', '1178214681231814658', 'Linux', '6', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681324089345', '0', '系统/运维', '7', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681353449473', '1178214681324089345', 'Linux', '7', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681382809602', '1178214681324089345', 'Windows', '8', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681399586817', '0', '数据库', '9', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681428946945', '1178214681399586817', 'MySQL', '9', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681454112770', '1178214681399586817', 'MongoDB', '10', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681483472898', '0', '大数据', '11', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681504444418', '1178214681483472898', 'Hadoop', '11', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681529610242', '1178214681483472898', 'Spark', '12', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681554776066', '0', '人工智能', '13', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681584136193', '1178214681554776066', 'Python', '13', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681613496321', '0', '编程语言', '14', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178214681626079234', '1178214681613496321', 'Java', '14', '2019-09-29 15:47:25', '2019-09-29 15:47:25');
INSERT INTO `edu_subject` VALUES ('1178585108407984130', '1178214681118568449', 'Python', '2', '2019-09-30 16:19:22', '2019-09-30 16:19:22');
INSERT INTO `edu_subject` VALUES ('1178585108454121473', '1178214681181483010', 'HTML/CSS', '3', '2019-09-30 16:19:22', '2019-09-30 16:19:22');
INSERT INTO `edu_subject` VALUES ('1541947066874388482', '1178214681181483010', 'vue', '0', '2022-06-29 08:50:03', '2022-06-29 08:50:03');
INSERT INTO `edu_subject` VALUES ('1541947067008606209', '1178214681181483010', 'jquery', '0', '2022-06-29 08:50:03', '2022-06-29 08:50:03');
INSERT INTO `edu_subject` VALUES ('1541947067142823938', '1178214681118568449', 'c++', '0', '2022-06-29 08:50:03', '2022-06-29 08:50:03');
INSERT INTO `edu_subject` VALUES ('1541947067142823939', '0', '数据库开发', '0', '2022-06-29 08:50:03', '2022-06-29 08:50:03');
INSERT INTO `edu_subject` VALUES ('1541947067142823940', '1541947067142823939', 'mysql', '0', '2022-06-29 08:50:03', '2022-06-29 08:50:03');
INSERT INTO `edu_subject` VALUES ('1545214695374327809', '1178214681324089345', 'MacOs', '0', '2022-07-08 09:14:27', '2022-07-08 09:14:27');
INSERT INTO `edu_subject` VALUES ('1545214695441436674', '1178214681613496321', 'C语言', '0', '2022-07-08 09:14:27', '2022-07-08 09:14:27');
INSERT INTO `edu_subject` VALUES ('2000000000000000000', '1541947067142823940', '索引', '0', '2022-07-23 10:34:31', '2022-07-23 17:00:25');

-- ----------------------------
-- Table structure for edu_teacher
-- ----------------------------
DROP TABLE IF EXISTS `edu_teacher`;
CREATE TABLE `edu_teacher` (
  `id` bigint NOT NULL COMMENT '讲师ID',
  `name` varchar(20) NOT NULL COMMENT '讲师姓名',
  `career` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '讲师资历,一句话说明讲师',
  `intro` varchar(500) NOT NULL DEFAULT '' COMMENT '讲师简介',
  `level` int unsigned NOT NULL COMMENT '头衔 1高级讲师 2首席讲师',
  `avatar` varchar(255) DEFAULT NULL COMMENT '讲师头像',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='讲师';

-- ----------------------------
-- Records of edu_teacher
-- ----------------------------
INSERT INTO `edu_teacher` VALUES ('1', '慕彩红', '西安电子科技大学 - 教授', '慕彩红，博士，西安电子科技大学教授，博士生导师。目前是西安电子科技大学人工智能学院、智能感知与图像理解教育部重点实验室、智能感知与计算国际联合研究中心、陕西省量子信息协同创新中心成员，国家“111”计划创新引智基地成员，美国电气电子工程师协会(IEEE)会员，曾任ACM Computing survey，Knowledge-Based Systems，IEEE Geoscience and Remote Sensing Letters 等国际期刊审稿人。主持国家自然科学基金3项。发表学术论文20余篇，获授权专利十余项。承担《人工智能概论》课程的本科教学及《计算智能》课程的研究生教学工作。', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/fa576b20e312658dfa61f27441daeb3a0055460c5e8.JPG', '0', '0', '2019-10-30 14:18:46', '2022-07-22 22:12:49');
INSERT INTO `edu_teacher` VALUES ('18633187491921', '何钦铭', '浙江大学 - 教授', '浙江大学计算机科学与技术学院教授，教育部大学计算机课程教学指导委员会副主任委员、浙江省高校计算机类专业教学指导委员会主任委员。主要研究方向为数据挖掘、虚拟计算系统技术，主讲“程序设计基础”、“数据结构基础”、“高级数据结构与算法分析”等课程。为国家精品课程与精品资源共享课程“程序设计基础”负责人、国家十一五及十二五规划教材“C语言程序设计”负责人。曾获国家优秀教学成果二等奖3项、浙江省优秀教学成果一等奖5项，以及霍英东优秀青年教师奖、宝钢优秀教师奖、浙江省高等学校教学名师等荣誉。', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/c7f0c8e7eebRpA4UJ2jvvShdL3EP6WLnQ==_659214395474149838.jpg', '0', '0', '2022-06-24 16:47:46', '2022-07-22 22:28:14');
INSERT INTO `edu_teacher` VALUES ('1189390295668469762', '陈璞花', '西安电子科技大学 - 讲师', '2009年7月获得电子科技大学环境工程专业学士学位，2016年12月获得西安电子科技大学电路与系统专业博士学位。 现为西安电子科技大学人工智能学院讲师，人工智能与模式识别研究所骨干成员，智能感知与图像处理教育部重点实验室骨干成员。主讲研究生课程《人工智能》；本科生专业必修课《人工智能概论》，通识课《人工智能》，专业选修课《模式识别》、《离散数学》、《智能传感技术》、《人工智能前沿导论》。已发表国际高水平论文十余篇，申请多项国家发明专利，主持省部级项目五项及企业合作项目2项。', '2', '\r\nhttps://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/00fd8c81c43chenpuhua.jpg', '0', '0', '2019-10-30 11:55:19', '2022-07-22 21:30:38');
INSERT INTO `edu_teacher` VALUES ('1189426437876985857', '刘若辰', '西安电子科技大学 - 教授', '刘若辰，博士，西安电子科技大学教授，博士生导师，国家教育部重点实验室骨干成员，教育部创新团队骨干成员，国家\"111计划\"创新引智基地骨干成员，校\"智能信息处理优秀创新团队\"骨干成员。现为IEEE会员，IEEE CIS 西安分部成员，中国计算机学会高级会员、中国电子学会会员。自2001年以来一直从事计算智能及其相关领域的相关研究，研究成果包括在国内外学术刊物发表论文50余篇, 主持国家自然科学基金项目、陕西省自然科学基金等多项科研项目。\n主讲的研究生课程：《自然计算》、 《人工智能及其应用》；主讲的本科课程：《人工智能概论》、《概率论与数理统计》、《离散数学》。', '2', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/0cc08aa0dbb2e93d6533bb348aeb8723e414d77edac.jpg', '0', '0', '2019-10-30 14:18:56', '2022-07-22 22:23:38');
INSERT INTO `edu_teacher` VALUES ('1189426464967995393', '谢海斌', '国防科技大学 - 副教授', '谢海斌，国防科技大学智能科学学院智能科学技术系副主任、副教授，硕士生导师，主要从事《人工智能基础》本科教学与《人工智能》研究生课程教学任务，主要研究方向为智能无人系统，机器人等。', '2', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/7388f4be3d8ca81e3a72f3b4ce8b2d7fca32f67978c.jpg', '0', '0', '2019-10-30 14:19:02', '2022-07-22 22:25:36');
INSERT INTO `edu_teacher` VALUES ('1192249914833055746', '李明', '国防科技大学 - 副教授', '国防科技大学智能科学学院副教授，从事脑与人工智能方向的教学和科研工作，“认知与智能系统”湖南省科技创新团队核心成员，“脑科学与认知科学”湖南省研究生优秀导师团队核心成员，国防科技大学首届青年拔尖人才培养对象，智能科学学院优秀教师。主讲《人工智能基础》、《人工智能导论》等课程，相关课程被评为湖南省一流本科课程、军队在线职业教育精品课程。在Science Advances、IEEE Transaction 系列 等杂志上发表论文二十余篇；教学科研成果获全国自动化学会教学成果奖、湖南省教学成果奖、教育部自然科学奖等奖项。', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/e60aff5a83649E89BEA3F1B3F1AC788F5F94C4A457F.png', '0', '0', '2019-11-07 09:18:25', '2022-07-22 22:26:22');
INSERT INTO `edu_teacher` VALUES ('1192327476087115778', '车万翔', '哈尔滨工业大学 - 教授、博士生导师', '车万翔博士，哈尔滨工业大学计算机科学与技术学院教授，博士生导师，斯坦福大学访问学者。', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/03099372b9e4262883788D369C4B9952BFBE7C0A008.jpeg', '0', '0', '2019-11-07 14:26:37', '2022-07-22 22:26:56');
INSERT INTO `edu_teacher` VALUES ('1195337453429129218', '王宏志', '哈尔滨工业大学 - 教授、博士生导师', '王宏志，哈尔滨工业大学软件与理论学科教授，博士生导师。美国加州大学欧文分校博士后、澳大利亚新南威尔士大学访问学者、微软亚洲研究院“铸星计划”访问学者。研究方向为大数据管理。发表学术论文150余篇，出版学术专著2本，拥有3项软件著作权，SCI/EI检索80余次，他引400余次。获得微软学者、中国优秀数据库工程师、IBM博士英才等称号，获得黑龙江省自然科学奖1项，博士论文获得中国计算机学会优秀博士论文。', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/562fb4d3366Gz4SBTAAD1pkeq_vmiNGTw==_6608183630190318381.jpg', '0', '0', '2019-11-15 21:47:12', '2022-07-22 22:27:31');
INSERT INTO `edu_teacher` VALUES ('1540507162831323137', '礼欣', '北京理工大学 - 副教授', '礼欣，北京理工大学计算机学院副教授，博士生导师、吉林大学学士、硕士、香港浸会大学博士。IEEE会员，中国计算机学会会员。研究方向为：机器学习、（深度）强化学习理论及其应用。', '2', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/8705ed6db7dGKDotuNmatQcVFBUzRkDzQ==_6608785063050967912.jpg', '0', '0', '2022-06-25 09:28:24', '2022-07-22 22:28:58');
INSERT INTO `edu_teacher` VALUES ('1541683208956002306', '黄天羽', '北京理工大学 - 教授', '黄天羽，北京理工大学计算机学院教授，北京市高等学校青年教学名师，博导，美国宾夕法尼亚大学公派访问学者，2007 年 4 月获得北京理工大学计算机应用专业博士学位，留校任教北京理工大学。主讲课程为《Python语言程序设计》、《软件工程基础训练》、《动画原理与实现》、《虚拟现实技术与应用》、《数字表演基础与应用》、《人群建模与仿真》，主持建设2门国家级一流课程，获北京市教育教学成果奖2项，指导学生获得全国“互联网+”比赛金奖、“挑战杯”比赛银奖。研究方向包括虚拟现实、计算机仿真、数字表演等。', '2', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/7381fedb730292b6c4da29d428b8aa727c335d3b44b.jpg', '1', '0', '2022-06-28 15:21:35', '2022-07-22 22:29:19');
INSERT INTO `edu_teacher` VALUES ('1541683545473400833', '嵩天', '北京理工大学 - 教授', '嵩天, 博士, 教授, 博士生导师, 北京理工大学网络空间安全学院副院长, \"网络空间安全\"和\"计算机科学与技术\"双学科博士生导师, 北京市青年教学名师, 北京理工大学教学名师. 美国圣路易斯华盛顿大学(Washington University in St.Louis)公派访问学者. 2002年, 毕业于东北大学计算机系, 获学士学位, 校98尖子班(实验班)成员, 同年保送至清华大学. 2008年1月, 毕业于清华大学计算机系, 获工学博士学位. 同年, 进入北京理工大学计算机学院工作. ACM/IEEE会员, 中国计算机学会高级会员, 中国计算机学会体系结构专委会常务委员、教育专委会委员. 全国高校计算机教育研究会常务理事、青年教师工作委员会主任. ', '2', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/e60aff5a83649E89BEA3F1B3F1AC788F5F94C4A457F.png', '1', '0', '2022-06-28 15:22:55', '2022-07-06 15:21:14');
INSERT INTO `edu_teacher` VALUES ('1541699548563464194', '老宋计算机二级', '网易大学Nanjing University of Science and Technology1', '快去学Office金牌讲师、金山WPS教育培训专家、金山最有价值专家、微软听听文档专家认证讲师、微软办公软件国际大师级认证、MOS中国区大赛优秀指导教师、北部湾大学第二课堂特邀讲师，中国石油、国家电网、中粮集团等世界500强Office培训师', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/e60aff5a83649E89BEA3F1B3F1AC788F5F94C4A457F.png', '1', '0', '2022-06-28 16:26:30', '2022-07-06 15:19:15');
INSERT INTO `edu_teacher` VALUES ('1541715434795991042', '刘经纬', '网易大学Nanjing University of Science and Technology1 - 副教授', '刘经纬，副教授，硕士生导师，工学博士，信息系统项目管理师（高级），PMP（国际项目管理专家认证）。 2014 年毕业于北京工业大学电子信息与控制工程学院，获工学博士学位，现任首都经济贸易大学管理工程学院教师、党支部书记、软件研发中心主任。      ', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/e60aff5a83649E89BEA3F1B3F1AC788F5F94C4A457F.png', '0', '0', '2022-06-28 17:29:38', '2022-07-06 15:19:31');
INSERT INTO `edu_teacher` VALUES ('1542781526742257665', '何华勤', '福建农林大学 - 教授', '	何华勤  博士  教授、博士生导师，现任福建农林大学生命科学学院副院长。长期从事植物生物信息学与蛋白质组学的教学与研究工作。\n       正在主持建设国家级精品在线开放课程一门、福建省中青年教师教育研究项目1项；主编教材2部、撰写教改论文10多篇。主持研究的教学成果先后获福建省高校多媒体课件大赛二等奖1次、福建农林大学高等教育教学成果一等奖2次。先后获教育部在线教育研究中心 “智慧教学之星”、福建省“优秀教师”、福建农林大学 “教学名师”等称号。\n       \n', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/e60aff5a83649E89BEA3F1B3F1AC788F5F94C4A457F.png', '1', '0', '2022-07-01 16:05:54', '2022-07-06 15:13:24');
INSERT INTO `edu_teacher` VALUES ('1542781858721419266', '陈志德', '福建师范大学 - 教授', '男，1976年12月生，博士，教授，硕士生导师。福建师范大学数学与计算机科学学院/福建省网络安全与密码技术重点实验室。现任福建省网络安全与密码技术重点实验室副主任。', '2', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/e60aff5a83649E89BEA3F1B3F1AC788F5F94C4A457F.png', '3', '0', '2022-07-01 16:07:13', '2022-07-06 15:31:28');
INSERT INTO `edu_teacher` VALUES ('1542782691915714562', '翁凯', '计算机博士，浙江大学计算机学院教师，ACM-ICPC优秀教练奖得主，2011世界总决赛金牌教练', '主要讲授包括C、C++、Java程序设计，程序设计语言原理，计算机体系结构，嵌入式系统等课程。\n\n专业方向为嵌入式操作系统和嵌入式系统应用，是国内Arduino和树莓派应用的鼓吹者，翻译出版了多本相关书籍，在创客界交友广泛。\n\n业余爱好无线电，1988年开始玩业余电台，电台呼号BA5AG，主要玩莫尔斯码、短波、数据通信和卫星通信。是浙大传统毅行的发起者和早期组织者之一。', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/e60aff5a83649E89BEA3F1B3F1AC788F5F94C4A457F.png', '2', '0', '2022-07-01 16:10:32', '2022-07-22 22:30:31');
INSERT INTO `edu_teacher` VALUES ('1543883782568910850', '苗彩霞', '中国科学技术大学山东省青年骨干教师国内访问学者', '中国科学技术大学山东省青年骨干教师国内访问学者。讲授《HTML5网页前端设计》、《数据库应用技术》、《ASP.NET程序设计》等课程，发表论文10余篇。主持建设省精品课《.Net Web开发及应用》；主持完成山东省职业教育与成人教育科研“十二五”规划课题《基于职业岗位能力的专业核心课程体系建设研究》、主持完成山东省教育厅教改课题《基于校企合作的高职实践教学体系建设研究》、《“以学生为中心”的高职信息化教学模式的构建与应用研究》。', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/cd045471ea6file.png', '1', '0', '2022-07-04 17:05:52', '2022-07-22 23:01:02');
INSERT INTO `edu_teacher` VALUES ('1545215475917524993', '王青', '北京大学 - 北京理工大学特别副研究员/助理教授 北京大学博士后', '\n王青，北京理工大学人文与社会科学学院特别副研究员/助理教授，硕士生导师。北京大学教育学院博士后，美国普渡大学教育学博士', '1', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/22/e60aff5a83649E89BEA3F1B3F1AC788F5F94C4A457F.png', '1', '0', '2022-07-08 09:17:33', '2022-07-08 09:17:33');

-- ----------------------------
-- Table structure for edu_video
-- ----------------------------
DROP TABLE IF EXISTS `edu_video`;
CREATE TABLE `edu_video` (
  `id` bigint NOT NULL COMMENT '视频ID',
  `course_id` bigint NOT NULL COMMENT '课程ID',
  `chapter_id` bigint NOT NULL COMMENT '章节ID',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '小节(视频)名称',
  `video_source_id` varchar(100) DEFAULT NULL COMMENT '云端视频资源',
  `video_original_name` varchar(100) DEFAULT NULL COMMENT '原始文件名称',
  `sort` int unsigned NOT NULL DEFAULT '0' COMMENT '排序字段',
  `play_count` bigint unsigned NOT NULL DEFAULT '0' COMMENT '播放次数',
  `is_free` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '是否可以试听：0收费 1免费',
  `duration` float NOT NULL DEFAULT '0' COMMENT '视频时长（秒）',
  `status` tinyint NOT NULL DEFAULT '0' COMMENT '0未上传 1转码中  2正常',
  `size` bigint unsigned NOT NULL DEFAULT '0' COMMENT '视频源文件大小（字节）',
  `version` bigint unsigned NOT NULL DEFAULT '1' COMMENT '乐观锁',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_chapter_id` (`chapter_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci ROW_FORMAT=COMPACT COMMENT='课程视频';

-- ----------------------------
-- Records of edu_video
-- ----------------------------
INSERT INTO `edu_video` VALUES ('17', '18', '15', '第一节：Java简介', 'cc03514545394a79b111e37eb86e855c', '1', '1', '1000', '1', '100', '0', '0', '1', '2019-01-01 13:08:57', '2019-10-11 11:26:39');
INSERT INTO `edu_video` VALUES ('18', '18', '15', '第二节：表达式和赋值语句', 'cc03514545394a79b111e37eb86e855c', '7 - How Do I Find Time for My ', '2', '999', '1', '100', '0', '0', '1', '2019-01-01 13:09:02', '2019-03-08 03:30:27');
INSERT INTO `edu_video` VALUES ('19', '18', '15', '第三节：String类', 'cc03514545394a79b111e37eb86e855c', 'eae2b847ef8503b81f5d5593d769dde2.mp4', '3', '888', '0', '100', '0', '0', '1', '2019-01-01 13:09:05', '2019-11-12 12:50:45');
INSERT INTO `edu_video` VALUES ('20', '18', '15', '第四节：程序风格', 'cc03514545394a79b111e37eb86e855c', '00-day10总结.avi', '4', '666', '0', '100', '0', '0', '1', '2019-01-01 13:09:05', '2022-07-24 18:20:16');
INSERT INTO `edu_video` VALUES ('1182499307429339137', '18', '32', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '0', '0', '0', '0', '0', '0', '1', '2019-10-11 11:32:59', '2019-10-11 11:57:38');
INSERT INTO `edu_video` VALUES ('1189434737808990210', '18', '44', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2019-10-30 14:51:55', '2022-07-24 18:20:00');
INSERT INTO `edu_video` VALUES ('1189476403626409986', '18', '1181729226915577857', '第一节', 'cc03514545394a79b111e37eb86e855c', '视频.mp4', '0', '0', '0', '0', '0', '0', '1', '2019-10-30 17:37:29', '2019-10-30 17:37:29');
INSERT INTO `edu_video` VALUES ('1192252824606289921', '1192252213659774977', '1192252428399751169', '第一课时', 'cc03514545394a79b111e37eb86e855c', 'eae2b847ef8503b81f5d5593d769dde2.mp4', '0', '0', '0', '0', '0', '0', '1', '2019-11-07 09:29:59', '2019-11-07 09:29:59');
INSERT INTO `edu_video` VALUES ('1192628092797730818', '1192252213659774977', '1192252428399751169', '第二课时', 'cc03514545394a79b111e37eb86e855c', 'eae2b847ef8503b81f5d5593d769dde2.mp4', '0', '0', '1', '0', '0', '0', '1', '2019-11-08 10:21:10', '2019-11-08 10:21:22');
INSERT INTO `edu_video` VALUES ('1192632495013380097', '1192252213659774977', '1192252428399751169', '第三课时', 'cc03514545394a79b111e37eb86e855c', 'eae2b847ef8503b81f5d5593d769dde2.mp4', '0', '0', '1', '0', '0', '0', '1', '2019-11-08 10:38:40', '2019-11-08 10:38:40');
INSERT INTO `edu_video` VALUES ('1194117638832111617', '1192252213659774977', '1192252428399751169', '第四课时', 'cc03514545394a79b111e37eb86e855c', 'eae2b847ef8503b81f5d5593d769dde2.mp4', '0', '0', '0', '0', '0', '0', '1', '2019-11-12 13:00:05', '2019-11-12 13:00:05');
INSERT INTO `edu_video` VALUES ('1196263770832023554', '1192252213659774977', '1192252428399751169', '第五课时', 'cc03514545394a79b111e37eb86e855c', 'eae2b847ef8503b81f5d5593d769dde2.mp4', '5', '0', '0', '0', '0', '0', '1', '2019-11-18 11:08:03', '2019-11-18 11:08:03');
INSERT INTO `edu_video` VALUES ('1542078915852484610', '1542078687770427394', '1542078732305547266', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-06-29 17:33:59', '2022-07-06 15:01:13');
INSERT INTO `edu_video` VALUES ('1542350440908193794', '1542350261626863618', '1542350339225681921', 'faw', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-06-30 11:32:55', '2022-06-30 11:32:55');
INSERT INTO `edu_video` VALUES ('1544573788031594497', '1544573219212668930', '1544573403531358209', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-07-06 14:47:43', '2022-07-06 14:47:43');
INSERT INTO `edu_video` VALUES ('1544574407354134529', '1544574274847682562', '1544574323715518466', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-07-06 14:50:10', '2022-07-06 14:50:10');
INSERT INTO `edu_video` VALUES ('1544575879991046145', '1544575798948704258', '1544575828455632897', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-07-06 14:56:01', '2022-07-06 14:56:01');
INSERT INTO `edu_video` VALUES ('1544576279146180609', '1544253113710534658', '1544576223538098177', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-07-06 14:57:36', '2022-07-06 14:57:36');
INSERT INTO `edu_video` VALUES ('1544576465247449090', '1544251400744198146', '1544576412147560450', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-07-06 14:58:21', '2022-07-06 14:58:21');
INSERT INTO `edu_video` VALUES ('1544576591642800129', '1544249768534876161', '1544576543467024386', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-07-06 14:58:51', '2022-07-06 14:58:51');
INSERT INTO `edu_video` VALUES ('1544576732311367681', '1543881346387124226', '1544576682814386177', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-07-06 14:59:24', '2022-07-06 14:59:24');
INSERT INTO `edu_video` VALUES ('1544576836745342978', '1543879917765238785', '1544576794835857410', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-07-06 14:59:49', '2022-07-06 14:59:49');
INSERT INTO `edu_video` VALUES ('1544576958120112129', '1543879751771463681', '1544576915078164482', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-07-06 15:00:18', '2022-07-06 15:00:18');
INSERT INTO `edu_video` VALUES ('1544577052181573633', '1542321527846887426', '1544577014143430658', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-07-06 15:00:41', '2022-07-06 15:00:45');
INSERT INTO `edu_video` VALUES ('1545215160421978113', '1545215056076083201', '1545215098040094722', '第一节', 'cc03514545394a79b111e37eb86e855c', '6 - What If I Want to Move Faster.mp4', '1', '0', '0', '0', '0', '0', '1', '2022-07-08 09:16:18', '2022-07-08 09:16:18');

-- ----------------------------
-- Table structure for pay_log
-- ----------------------------
DROP TABLE IF EXISTS `pay_log`;
CREATE TABLE `pay_log` (
  `id` bigint NOT NULL COMMENT '主键id',
  `order_no` bigint NOT NULL COMMENT '订单号',
  `pay_time` datetime DEFAULT NULL COMMENT '支付完成时间',
  `total_fee` decimal(10,2) DEFAULT '0.01' COMMENT '支付金额（分）',
  `transaction_id` varchar(30) DEFAULT NULL COMMENT '交易流水号',
  `trade_state` char(20) DEFAULT NULL COMMENT '交易状态',
  `pay_type` tinyint NOT NULL DEFAULT '0' COMMENT '支付类型（1：微信 2：支付宝）',
  `attr` text COMMENT '其他属性',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_order_no` (`order_no`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='支付日志表';

-- ----------------------------
-- Records of pay_log
-- ----------------------------
INSERT INTO `pay_log` VALUES ('1194498446013001730', '1194498300579704832', '2019-11-13 14:13:17', '1.00', '4200000469201911130676624386', 'SUCCESS', '1', '{\"transaction_id\":\"4200000469201911130676624386\",\"nonce_str\":\"2Lc23ILl231It53M\",\"trade_state\":\"SUCCESS\",\"bank_type\":\"CFT\",\"openid\":\"oNpSGwR-QGG5DaZtDkh2UZlsFDQE\",\"sign\":\"5404850AA3ED0E844DE104651477F07A\",\"return_msg\":\"OK\",\"fee_type\":\"CNY\",\"mch_id\":\"1473426802\",\"cash_fee\":\"1\",\"out_trade_no\":\"1194498300579704832\",\"cash_fee_type\":\"CNY\",\"appid\":\"wx8397f8696b538317\",\"total_fee\":\"1\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"attach\":\"\",\"time_end\":\"20191113141314\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}', '0', '2019-11-13 14:13:17', '2019-11-13 14:13:17');
INSERT INTO `pay_log` VALUES ('1195253787449430017', '1195253049260314624', '2019-11-15 16:14:44', '1.00', '4200000454201911150981874895', 'SUCCESS', '1', '{\"transaction_id\":\"4200000454201911150981874895\",\"nonce_str\":\"MAM5UM4Xhv1lItvO\",\"trade_state\":\"SUCCESS\",\"bank_type\":\"CFT\",\"openid\":\"oNpSGwR-QGG5DaZtDkh2UZlsFDQE\",\"sign\":\"7DBDCAF4A078B30BB3EF073E6A238C20\",\"return_msg\":\"OK\",\"fee_type\":\"CNY\",\"mch_id\":\"1473426802\",\"cash_fee\":\"1\",\"out_trade_no\":\"1195253049260314624\",\"cash_fee_type\":\"CNY\",\"appid\":\"wx8397f8696b538317\",\"total_fee\":\"1\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"attach\":\"\",\"time_end\":\"20191115161440\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}', '0', '2019-11-15 16:14:44', '2019-11-15 16:14:44');
INSERT INTO `pay_log` VALUES ('1196264321397342210', '1196264005255872512', '2019-11-18 11:10:14', '1.00', '4200000453201911184025781554', 'SUCCESS', '1', '{\"transaction_id\":\"4200000453201911184025781554\",\"nonce_str\":\"D1dHexCLIFIxAAg2\",\"trade_state\":\"SUCCESS\",\"bank_type\":\"CFT\",\"openid\":\"oNpSGwR-QGG5DaZtDkh2UZlsFDQE\",\"sign\":\"C9F5CA1EE49EA7891736D73BEB423962\",\"return_msg\":\"OK\",\"fee_type\":\"CNY\",\"mch_id\":\"1473426802\",\"cash_fee\":\"1\",\"out_trade_no\":\"1196264005255872512\",\"cash_fee_type\":\"CNY\",\"appid\":\"wx8397f8696b538317\",\"total_fee\":\"1\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"attach\":\"\",\"time_end\":\"20191118111011\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}', '0', '2019-11-18 11:10:14', '2019-11-18 11:10:14');
INSERT INTO `pay_log` VALUES ('1544232719079215106', '20220705161151271', '2022-07-05 16:12:25', '0.01', '4200001513202207058146868181', 'SUCCESS', '1', '{\"transaction_id\":\"4200001513202207058146868181\",\"nonce_str\":\"baj0cWBDokazqAVT\",\"trade_state\":\"SUCCESS\",\"bank_type\":\"OTHERS\",\"openid\":\"oHwsHuMouAUSmdA45hGqvn9yXoCc\",\"sign\":\"82C5BF65169DB5FF2F874031B59A4260\",\"return_msg\":\"OK\",\"fee_type\":\"CNY\",\"mch_id\":\"1558950191\",\"cash_fee\":\"1\",\"out_trade_no\":\"20220705161151271\",\"cash_fee_type\":\"CNY\",\"appid\":\"wx74862e0dfcf69954\",\"total_fee\":\"1\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"attach\":\"\",\"time_end\":\"20220705161223\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}', '0', '2022-07-05 16:12:25', '2022-07-05 16:12:25');
INSERT INTO `pay_log` VALUES ('1544602166193360898', '20220706163854999', '2022-07-06 16:40:28', '0.01', '4200001509202207061156508023', 'SUCCESS', '1', '{\"transaction_id\":\"4200001509202207061156508023\",\"nonce_str\":\"Z6kemIKvr6WZwnCd\",\"trade_state\":\"SUCCESS\",\"bank_type\":\"OTHERS\",\"openid\":\"oHwsHuBnXMXFO7OGpfiGQXuGHk7g\",\"sign\":\"9449233D181D85805BAFDFF437B879A4\",\"return_msg\":\"OK\",\"fee_type\":\"CNY\",\"mch_id\":\"1558950191\",\"cash_fee\":\"1\",\"out_trade_no\":\"20220706163854999\",\"cash_fee_type\":\"CNY\",\"appid\":\"wx74862e0dfcf69954\",\"total_fee\":\"1\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"attach\":\"\",\"time_end\":\"20220706164027\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}', '0', '2022-07-06 16:40:28', '2022-07-06 16:40:28');
INSERT INTO `pay_log` VALUES ('1545035119285338114', '20220707212017850', '2022-07-07 21:20:52', '0.01', '4200001461202207074952256298', 'SUCCESS', '1', '{\"transaction_id\":\"4200001461202207074952256298\",\"nonce_str\":\"VGeuo3BafcxavHOS\",\"trade_state\":\"SUCCESS\",\"bank_type\":\"OTHERS\",\"openid\":\"oHwsHuJ7a47w7We-9bjR-gcuakWI\",\"sign\":\"370CE25626EF0873413AD7AEDB9A4620\",\"return_msg\":\"OK\",\"fee_type\":\"CNY\",\"mch_id\":\"1558950191\",\"cash_fee\":\"1\",\"out_trade_no\":\"20220707212017850\",\"cash_fee_type\":\"CNY\",\"appid\":\"wx74862e0dfcf69954\",\"total_fee\":\"1\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"attach\":\"\",\"time_end\":\"20220707212049\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}', '0', '2022-07-07 21:20:52', '2022-07-07 21:20:52');
INSERT INTO `pay_log` VALUES ('1552608303333363713', '20220728185000822', '2022-07-28 18:54:00', '0.01', '4200001547202207283295974035', 'SUCCESS', '1', '{\"transaction_id\":\"4200001547202207283295974035\",\"nonce_str\":\"OQqDKoOQBhitmvxO\",\"trade_state\":\"SUCCESS\",\"bank_type\":\"OTHERS\",\"openid\":\"oHwsHuJ7a47w7We-9bjR-gcuakWI\",\"sign\":\"48FC748F03552DB6BAFEA5BF777E8047\",\"return_msg\":\"OK\",\"fee_type\":\"CNY\",\"mch_id\":\"1558950191\",\"cash_fee\":\"1\",\"out_trade_no\":\"20220728185000822\",\"cash_fee_type\":\"CNY\",\"appid\":\"wx74862e0dfcf69954\",\"total_fee\":\"1\",\"trade_state_desc\":\"支付成功\",\"trade_type\":\"NATIVE\",\"result_code\":\"SUCCESS\",\"attach\":\"\",\"time_end\":\"20220728185358\",\"is_subscribe\":\"N\",\"return_code\":\"SUCCESS\"}', '0', '2022-07-28 18:54:00', '2022-07-28 18:54:00');

-- ----------------------------
-- Table structure for pay_order
-- ----------------------------
DROP TABLE IF EXISTS `pay_order`;
CREATE TABLE `pay_order` (
  `id` bigint NOT NULL COMMENT '主键id',
  `order_no` bigint NOT NULL COMMENT '订单号',
  `course_id` bigint NOT NULL COMMENT '课程id',
  `member_id` bigint NOT NULL COMMENT '会员id',
  `course_title` varchar(100) DEFAULT NULL COMMENT '课程名称',
  `course_cover` varchar(255) DEFAULT NULL COMMENT '课程封面',
  `teacher_name` varchar(20) DEFAULT NULL COMMENT '讲师名称',
  `nickname` varchar(50) DEFAULT NULL COMMENT '会员昵称',
  `mobile` varchar(11) DEFAULT NULL COMMENT '会员手机',
  `total_fee` decimal(10,2) DEFAULT '0.01' COMMENT '订单金额（分）',
  `pay_type` tinyint DEFAULT NULL COMMENT '支付类型（1：微信 2：支付宝）',
  `status` tinyint DEFAULT NULL COMMENT '订单状态（0：未支付 1：已支付）',
  `is_deleted` tinyint unsigned NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `ux_order_no` (`order_no`),
  KEY `idx_course_id` (`course_id`),
  KEY `idx_member_id` (`member_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单';

-- ----------------------------
-- Records of pay_order
-- ----------------------------
INSERT INTO `pay_order` VALUES ('1195693605513891841', '1195693605555834880', '18', '1', 'Java语言程序设计', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '陈璞花', '小三1231', '13700000001', '1.00', '2', '0', '0', '2019-11-16 21:22:25', '2019-11-16 21:22:25');
INSERT INTO `pay_order` VALUES ('1196264007411744769', '1196264005255872512', '1192252213659774977', '1', 'JavaWeb', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '车万翔', '小三1231', '13700000001', '1.00', '2', '1', '0', '2019-11-18 11:09:00', '2019-11-18 11:10:14');
INSERT INTO `pay_order` VALUES ('1543977577285267457', '20220704231834397', '18', '1543964673588637698', 'Java语言程序设计', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '陈璞花', '你痴', '13509432595', '0.01', '1', '0', '0', '2022-07-04 23:18:35', '2022-07-04 23:18:35');
INSERT INTO `pay_order` VALUES ('1544233105030680578', '20220705161357323', '1543879917765238785', '1543964673588637698', '人工智能原理', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '礼欣', '你痴', '13509432595', '0.01', '1', '0', '0', '2022-07-05 16:13:57', '2022-07-05 16:13:57');
INSERT INTO `pay_order` VALUES ('1544511061061918722', '20220706103826422', '1192252213659774977', '1543964673588637698', 'JavaWeb', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '车万翔', '你痴', '13509432595', '0.01', '1', '0', '0', '2022-07-06 10:38:27', '2022-07-06 10:38:27');
INSERT INTO `pay_order` VALUES ('1544511334689923074', '20220706103932650', '1543879917765238785', '1543964673588637698', '人工智能原理', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '礼欣', '你痴', '13509432595', '0.01', '1', '0', '0', '2022-07-06 10:39:32', '2022-07-06 10:39:32');
INSERT INTO `pay_order` VALUES ('1544596344532963330', '20220706161719558', '1543879917765238785', '1543964673588637698', '人工智能原理', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '礼欣', '你痴', '13509432595', '0.01', '1', '0', '0', '2022-07-06 16:17:20', '2022-07-06 16:17:20');
INSERT INTO `pay_order` VALUES ('1544596426535800833', '20220706161739724', '1543879917765238785', '1543964673588637698', '人工智能原理', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '礼欣', '你痴', '13509432595', '0.01', '1', '0', '0', '2022-07-06 16:17:40', '2022-07-06 16:17:40');
INSERT INTO `pay_order` VALUES ('1544601772813783041', '20220706163854999', '1543879917765238785', '1', '人工智能原理', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '礼欣', '小三123', '13700000001', '0.01', '1', '1', '0', '2022-07-06 16:38:55', '2022-07-06 16:40:28');
INSERT INTO `pay_order` VALUES ('1544603875800059905', '20220706164716135', '1192252213659774977', '1543964673588637698', 'JavaWeb', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '车万翔', '你痴', '13509432595', '0.01', '1', '0', '0', '2022-07-06 16:47:16', '2022-07-06 16:47:16');
INSERT INTO `pay_order` VALUES ('1544606800991555585', '20220706165853618', '18', '1', 'Java语言程序设计', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '陈璞花', '小三123', '13700000001', '0.01', '1', '0', '0', '2022-07-06 16:58:53', '2022-07-06 16:58:53');
INSERT INTO `pay_order` VALUES ('1544644868205195265', '20220706193008162', '1543879917765238785', '1542309609698574338', '人工智能原理', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '礼欣', '哄哄', '', '0.01', '1', '0', '0', '2022-07-06 19:30:09', '2022-07-06 19:30:09');
INSERT INTO `pay_order` VALUES ('1544645998507220993', '20220706193438732', '1543879917765238785', '1542309609698574338', '人工智能原理', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '礼欣', '哄哄', '', '1.00', '1', '0', '0', '2022-07-06 19:34:39', '2022-07-06 19:34:39');
INSERT INTO `pay_order` VALUES ('1545034974007230466', '20220707212017850', '1192252213659774977', '1545034536738373633', 'JavaWeb', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '车万翔', '好起来了', '13509432595', '0.01', '1', '1', '0', '2022-07-07 21:20:18', '2022-07-07 21:20:52');
INSERT INTO `pay_order` VALUES ('1545213637050814465', '20220708091013277', '1192252213659774977', '1542309609698574338', 'JavaWeb', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '车万翔', '哄哄', '', '0.01', '1', '0', '0', '2022-07-08 09:10:14', '2022-07-08 09:10:14');
INSERT INTO `pay_order` VALUES ('1552606675347726337', '20220728184732180', '18', '1545034536738373633', 'Java语言程序', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '陈璞花', '好起来了', '13509432595', '0.01', '1', '0', '0', '2022-07-28 18:47:32', '2022-07-28 18:47:32');
INSERT INTO `pay_order` VALUES ('1552606770227077121', '20220728184754359', '18', '1545034536738373633', 'Java语言程序', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '陈璞花', '好起来了', '13509432595', '0.01', '1', '0', '0', '2022-07-28 18:47:55', '2022-07-28 18:47:55');
INSERT INTO `pay_order` VALUES ('1552606830528585730', '20220728184809756', '18', '1545034536738373633', 'Java语言程序', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '陈璞花', '好起来了', '13509432595', '0.01', '1', '0', '0', '2022-07-28 18:48:09', '2022-07-28 18:48:09');
INSERT INTO `pay_order` VALUES ('1552607298902319106', '20220728185000822', '18', '1545034536738373633', 'Java语言程序', 'https://nichijoux-oes.oss-cn-chengdu.aliyuncs.com/2022/07/25/670403fc85fSnipaste_2022-07-25_14-43-19.jpg', '陈璞花', '好起来了', '13509432595', '0.01', '1', '1', '0', '2022-07-28 18:50:01', '2022-07-28 18:54:00');

-- ----------------------------
-- Table structure for statistics_daily
-- ----------------------------
DROP TABLE IF EXISTS `statistics_daily`;
CREATE TABLE `statistics_daily` (
  `id` bigint NOT NULL COMMENT '主键id',
  `date_calculated` varchar(20) NOT NULL COMMENT '统计日期',
  `register_num` int NOT NULL DEFAULT '0' COMMENT '注册人数',
  `login_num` int NOT NULL DEFAULT '0' COMMENT '登录人数',
  `video_view_num` int NOT NULL DEFAULT '0' COMMENT '每日播放视频数',
  `course_num` int NOT NULL DEFAULT '0' COMMENT '每日新增课程数',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `statistics_day` (`date_calculated`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站统计日数据';

-- ----------------------------
-- Records of statistics_daily
-- ----------------------------
INSERT INTO `statistics_daily` VALUES ('1165', '2018-01-01', '583', '26', '236', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1166', '2018-01-02', '583', '26', '236', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1167', '2018-01-03', '584', '26', '236', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1168', '2018-01-04', '584', '26', '237', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1169', '2018-01-05', '585', '26', '237', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1170', '2018-01-06', '585', '26', '237', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1171', '2018-01-07', '586', '26', '237', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1172', '2018-01-08', '586', '26', '237', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1173', '2018-01-09', '587', '26', '238', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1174', '2018-01-10', '587', '26', '238', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1175', '2018-01-11', '588', '27', '238', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1176', '2018-01-12', '588', '27', '238', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1177', '2018-01-13', '589', '27', '238', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1178', '2018-01-14', '589', '27', '239', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1179', '2018-01-15', '590', '27', '239', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1180', '2018-01-16', '590', '27', '239', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1181', '2018-01-17', '591', '27', '239', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1182', '2018-01-18', '591', '27', '239', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1183', '2018-01-19', '592', '27', '240', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1184', '2018-01-20', '592', '27', '240', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1185', '2018-01-21', '593', '27', '240', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1186', '2018-01-22', '593', '27', '240', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1187', '2018-01-23', '594', '27', '240', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1188', '2018-01-24', '594', '27', '241', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1189', '2018-01-25', '595', '27', '241', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1190', '2018-01-26', '595', '27', '241', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1191', '2018-01-27', '596', '27', '241', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1192', '2018-01-28', '596', '27', '241', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1193', '2018-01-29', '597', '27', '242', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1194', '2018-01-30', '597', '27', '242', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1195', '2018-01-31', '598', '27', '242', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1196', '2018-02-01', '598', '27', '242', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1197', '2018-02-02', '599', '27', '242', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1198', '2018-02-03', '599', '27', '243', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1199', '2018-02-04', '600', '27', '243', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1200', '2018-02-05', '600', '27', '243', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1201', '2018-02-06', '601', '27', '243', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1202', '2018-02-07', '601', '27', '243', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1203', '2018-02-08', '602', '27', '244', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1204', '2018-02-09', '602', '27', '244', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1205', '2018-02-10', '603', '27', '244', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1206', '2018-02-11', '603', '27', '244', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1207', '2018-02-12', '604', '27', '244', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1208', '2018-02-13', '604', '27', '245', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1209', '2018-02-14', '605', '27', '245', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1210', '2018-02-15', '605', '27', '245', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1211', '2018-02-16', '606', '27', '245', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1212', '2018-02-17', '606', '27', '245', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1213', '2018-02-18', '607', '27', '246', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1214', '2018-02-19', '607', '27', '246', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1215', '2018-02-20', '608', '27', '246', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1216', '2018-02-21', '608', '27', '246', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1217', '2018-02-22', '609', '27', '246', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1218', '2018-02-23', '609', '27', '247', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1219', '2018-02-24', '610', '27', '247', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1220', '2018-02-25', '610', '27', '247', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1221', '2018-02-26', '611', '27', '247', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1222', '2018-02-27', '611', '27', '247', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1223', '2018-02-28', '612', '27', '248', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1224', '2018-03-01', '612', '27', '248', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1225', '2018-03-02', '613', '28', '248', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1226', '2018-03-03', '613', '28', '248', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1227', '2018-03-04', '614', '28', '248', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1228', '2018-03-05', '614', '28', '249', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1229', '2018-03-06', '615', '28', '249', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1230', '2018-03-07', '615', '28', '249', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1231', '2018-03-08', '616', '28', '249', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1232', '2018-03-09', '616', '28', '249', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1233', '2018-03-10', '617', '28', '250', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1234', '2018-03-11', '617', '28', '250', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1235', '2018-03-12', '618', '28', '250', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1236', '2018-03-13', '618', '28', '250', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1237', '2018-03-14', '619', '28', '250', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1238', '2018-03-15', '619', '28', '251', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1239', '2018-03-16', '620', '28', '251', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1240', '2018-03-17', '620', '28', '251', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1241', '2018-03-18', '621', '28', '251', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1242', '2018-03-19', '621', '28', '251', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1243', '2018-03-20', '622', '28', '252', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1244', '2018-03-21', '622', '28', '252', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1245', '2018-03-22', '623', '28', '252', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1246', '2018-03-23', '623', '28', '252', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1247', '2018-03-24', '624', '28', '252', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1248', '2018-03-25', '624', '28', '253', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1249', '2018-03-26', '625', '28', '253', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1250', '2018-03-27', '625', '28', '253', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1251', '2018-03-28', '626', '28', '253', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1252', '2018-03-29', '626', '28', '253', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1253', '2018-03-30', '627', '28', '254', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1254', '2018-03-31', '627', '28', '254', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1255', '2018-04-01', '628', '28', '254', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1256', '2018-04-02', '628', '28', '254', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1257', '2018-04-03', '629', '28', '254', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1258', '2018-04-04', '629', '28', '255', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1259', '2018-04-05', '630', '28', '255', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1260', '2018-04-06', '630', '28', '255', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1261', '2018-04-07', '631', '28', '255', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1262', '2018-04-08', '631', '28', '255', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1263', '2018-04-09', '632', '28', '256', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1264', '2018-04-10', '632', '28', '256', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1265', '2018-04-11', '633', '28', '256', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1266', '2018-04-12', '633', '28', '256', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1267', '2018-04-13', '634', '28', '256', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1268', '2018-04-14', '634', '28', '257', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1269', '2018-04-15', '635', '28', '257', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1270', '2018-04-16', '635', '28', '257', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1271', '2018-04-17', '636', '28', '257', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1272', '2018-04-18', '636', '28', '257', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1273', '2018-04-19', '637', '28', '258', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1274', '2018-04-20', '637', '28', '258', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1275', '2018-04-21', '638', '29', '258', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1276', '2018-04-22', '638', '29', '258', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1277', '2018-04-23', '639', '29', '258', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1278', '2018-04-24', '639', '29', '259', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1279', '2018-04-25', '640', '29', '259', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1280', '2018-04-26', '640', '29', '259', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1281', '2018-04-27', '641', '29', '259', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1282', '2018-04-28', '641', '29', '259', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1283', '2018-04-29', '642', '29', '260', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1284', '2018-04-30', '642', '29', '260', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1285', '2018-05-01', '643', '29', '260', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1286', '2018-05-02', '643', '29', '260', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1287', '2018-05-03', '644', '29', '260', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1288', '2018-05-04', '644', '29', '261', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1289', '2018-05-05', '645', '29', '261', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1290', '2018-05-06', '645', '29', '261', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1291', '2018-05-07', '646', '29', '261', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1292', '2018-05-08', '646', '29', '261', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1293', '2018-05-09', '647', '29', '262', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1294', '2018-05-10', '647', '29', '262', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1295', '2018-05-11', '648', '29', '262', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1296', '2018-05-12', '648', '29', '262', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1297', '2018-05-13', '649', '29', '262', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1298', '2018-05-14', '649', '29', '263', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1299', '2018-05-15', '5', '29', '263', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1300', '2018-05-16', '650', '29', '263', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1301', '2018-05-17', '651', '29', '263', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1302', '2018-05-18', '651', '29', '263', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1303', '2018-05-19', '652', '29', '264', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1304', '2018-05-20', '652', '29', '264', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1305', '2018-05-21', '653', '29', '264', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1306', '2018-05-22', '1', '29', '264', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1307', '2018-05-23', '654', '29', '264', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1308', '2018-05-24', '654', '29', '265', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1309', '2018-05-25', '1', '29', '265', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1310', '2018-05-26', '655', '29', '265', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1311', '2018-05-27', '656', '29', '265', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1312', '2018-05-28', '656', '29', '265', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1313', '2018-05-29', '657', '29', '266', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1314', '2018-05-30', '657', '29', '266', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1315', '2018-05-31', '658', '29', '266', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1316', '2018-06-01', '658', '29', '266', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1317', '2018-06-02', '659', '29', '266', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1318', '2018-06-03', '659', '29', '267', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1319', '2018-06-04', '660', '29', '267', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1320', '2018-06-05', '660', '29', '267', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1321', '2018-06-06', '661', '29', '267', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1322', '2018-06-07', '661', '29', '267', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1323', '2018-06-08', '662', '29', '268', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1324', '2018-06-09', '662', '29', '268', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1325', '2018-06-10', '663', '30', '268', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1326', '2018-06-11', '663', '30', '268', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1327', '2018-06-12', '664', '30', '268', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1328', '2018-06-13', '664', '30', '269', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1329', '2018-06-14', '665', '30', '269', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1330', '2018-06-15', '665', '30', '269', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1331', '2018-06-16', '666', '30', '269', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1332', '2018-06-17', '666', '30', '269', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1333', '2018-06-18', '667', '30', '270', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1334', '2018-06-19', '667', '30', '270', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1335', '2018-06-20', '668', '30', '270', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1336', '2018-06-21', '668', '30', '270', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1337', '2018-06-22', '669', '30', '270', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1338', '2018-06-23', '669', '30', '271', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1339', '2018-06-24', '670', '30', '271', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1340', '2018-06-25', '670', '30', '271', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1341', '2018-06-26', '671', '30', '271', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1342', '2018-06-27', '671', '30', '271', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1343', '2018-06-28', '672', '30', '272', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1344', '2018-06-29', '672', '30', '272', '14', '2018-01-25 06:38:19', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1345', '2018-06-30', '673', '30', '272', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1346', '2018-07-01', '673', '30', '272', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1347', '2018-07-02', '674', '30', '272', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1348', '2018-07-03', '674', '30', '273', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1349', '2018-07-04', '675', '30', '273', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1350', '2018-07-05', '675', '30', '273', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1351', '2018-07-06', '676', '30', '273', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1352', '2018-07-07', '676', '30', '273', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1353', '2018-07-08', '677', '30', '274', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1355', '2018-07-10', '678', '30', '274', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1356', '2018-07-11', '678', '30', '274', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1357', '2018-07-12', '679', '30', '274', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1358', '2018-07-13', '679', '30', '275', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1359', '2018-07-14', '680', '30', '275', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1360', '2018-07-15', '680', '30', '275', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1361', '2018-07-16', '681', '30', '275', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1362', '2018-07-17', '681', '30', '275', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1363', '2018-07-18', '682', '30', '276', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1365', '2018-07-20', '683', '30', '276', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1366', '2018-07-21', '683', '30', '276', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1367', '2018-07-22', '684', '30', '276', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1368', '2018-07-23', '684', '30', '277', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1369', '2018-07-24', '685', '30', '277', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1370', '2018-07-25', '685', '30', '277', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1371', '2018-07-26', '686', '30', '277', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1372', '2018-07-27', '686', '30', '277', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1373', '2018-07-28', '687', '30', '278', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1374', '2018-07-29', '687', '30', '278', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1375', '2018-07-30', '688', '31', '278', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1376', '2018-07-31', '688', '31', '278', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1377', '2018-08-01', '689', '31', '278', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1378', '2018-08-02', '689', '31', '279', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1379', '2018-08-03', '690', '31', '279', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1380', '2018-08-04', '690', '31', '279', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1381', '2018-08-05', '691', '31', '279', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1382', '2018-08-06', '691', '31', '279', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1383', '2018-08-07', '692', '31', '280', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1384', '2018-08-08', '692', '31', '280', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1385', '2018-08-09', '1', '31', '280', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1386', '2018-08-10', '693', '31', '280', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1387', '2018-08-11', '694', '31', '280', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1388', '2018-08-12', '694', '31', '281', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1389', '2018-08-13', '695', '31', '281', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1390', '2018-08-14', '695', '31', '281', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1391', '2018-08-15', '696', '31', '281', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1392', '2018-08-16', '696', '31', '281', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1393', '2018-08-17', '697', '31', '282', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1394', '2018-08-18', '697', '31', '282', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1395', '2018-08-19', '698', '31', '282', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1396', '2018-08-20', '698', '31', '282', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1397', '2018-08-21', '699', '31', '282', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1398', '2018-08-22', '699', '31', '283', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1399', '2018-08-23', '700', '31', '283', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1400', '2018-08-24', '700', '31', '283', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1401', '2018-08-25', '701', '31', '283', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1402', '2018-08-26', '701', '31', '283', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1403', '2018-08-27', '702', '31', '284', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1404', '2018-08-28', '702', '31', '284', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1405', '2018-08-29', '703', '31', '284', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1406', '2018-08-30', '703', '31', '284', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1407', '2018-08-31', '704', '31', '284', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1408', '2018-09-01', '1', '31', '285', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1409', '2018-09-02', '705', '31', '285', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1410', '2018-09-03', '705', '31', '285', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1411', '2018-09-04', '706', '31', '285', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1412', '2018-09-05', '706', '31', '285', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1413', '2018-09-06', '707', '31', '286', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1414', '2018-09-07', '707', '31', '1', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1415', '2018-09-08', '708', '31', '1', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1416', '2018-09-09', '1', '31', '1', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1417', '2018-09-10', '1', '31', '2', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1418', '2018-09-11', '709', '31', '287', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1419', '2018-09-12', '710', '31', '287', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1420', '2018-09-13', '710', '31', '287', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1421', '2018-09-14', '711', '31', '287', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1422', '2018-09-15', '711', '31', '287', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1423', '2018-09-16', '712', '31', '288', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1424', '2018-09-17', '712', '31', '288', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1425', '2018-09-18', '713', '32', '288', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1426', '2018-09-19', '713', '32', '288', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1427', '2018-09-20', '714', '32', '288', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1428', '2018-09-21', '714', '32', '289', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1429', '2018-09-22', '715', '32', '289', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1430', '2018-09-23', '715', '32', '289', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1431', '2018-09-24', '716', '32', '1', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1432', '2018-09-25', '716', '32', '289', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1433', '2018-09-26', '717', '32', '290', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1434', '2018-09-27', '717', '32', '290', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1435', '2018-09-28', '718', '32', '290', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1436', '2018-09-29', '718', '32', '1', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1437', '2018-09-30', '719', '32', '290', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1438', '2018-10-01', '719', '32', '291', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1439', '2018-10-02', '720', '32', '291', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1440', '2018-10-03', '720', '32', '291', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1441', '2018-10-04', '721', '32', '291', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1442', '2018-10-05', '721', '32', '291', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1443', '2018-10-06', '722', '32', '292', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1444', '2018-10-07', '722', '32', '292', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1445', '2018-10-08', '1', '32', '292', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1446', '2018-10-09', '723', '32', '292', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1447', '2018-10-10', '724', '32', '292', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1448', '2018-10-11', '724', '32', '293', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1449', '2018-10-12', '725', '32', '293', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1450', '2018-10-13', '725', '32', '293', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1451', '2018-10-14', '726', '32', '4', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1452', '2018-10-15', '726', '32', '293', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1453', '2018-10-16', '727', '32', '294', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1454', '2018-10-17', '727', '32', '294', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1455', '2018-10-18', '728', '32', '294', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1456', '2018-10-19', '728', '32', '294', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1457', '2018-10-20', '729', '32', '294', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1458', '2018-10-21', '729', '32', '295', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1459', '2018-10-22', '730', '32', '1', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1460', '2018-10-23', '730', '32', '295', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1461', '2018-10-24', '731', '32', '295', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1462', '2018-10-25', '731', '32', '295', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1463', '2018-10-26', '732', '32', '296', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1464', '2018-10-27', '732', '32', '296', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1465', '2018-10-28', '733', '32', '296', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1466', '2018-10-29', '2', '32', '3', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1467', '2018-10-30', '734', '32', '296', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1468', '2018-10-31', '734', '32', '297', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1469', '2018-11-01', '735', '32', '297', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1470', '2018-11-02', '735', '32', '297', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1471', '2018-11-03', '1', '32', '297', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1472', '2018-11-04', '736', '32', '1', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1473', '2018-11-05', '737', '32', '298', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1474', '2018-11-06', '737', '32', '298', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1475', '2018-11-07', '738', '33', '298', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1476', '2018-11-08', '738', '33', '169', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1477', '2018-11-09', '1', '33', '298', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1478', '2018-11-10', '739', '33', '78', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1479', '2018-11-11', '740', '33', '299', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1480', '2018-11-12', '740', '33', '299', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1481', '2018-11-13', '741', '33', '299', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1482', '2018-11-14', '741', '33', '299', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1483', '2018-11-15', '742', '33', '300', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1484', '2018-11-16', '742', '33', '35', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1485', '2018-11-17', '743', '33', '300', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1486', '2018-11-18', '743', '33', '300', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1487', '2018-11-19', '744', '33', '300', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1488', '2018-11-20', '744', '33', '301', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1489', '2018-11-21', '745', '33', '234', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1490', '2018-11-22', '456', '33', '301', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1491', '2018-11-23', '746', '33', '301', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1492', '2018-11-24', '746', '33', '301', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1493', '2018-11-25', '747', '33', '302', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1494', '2018-11-26', '747', '4', '302', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1495', '2018-11-27', '748', '33', '302', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1496', '2018-11-28', '748', '33', '36', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1497', '2018-11-29', '749', '33', '302', '2', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1498', '2018-11-30', '749', '3', '303', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1499', '2018-12-01', '750', '33', '303', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1500', '2018-12-02', '234', '33', '303', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1501', '2018-12-03', '751', '33', '303', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1502', '2018-12-04', '751', '33', '303', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1503', '2018-12-05', '752', '6', '304', '33', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1505', '2018-12-07', '753', '33', '55', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1506', '2018-12-08', '753', '33', '304', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1507', '2018-12-09', '754', '33', '304', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1508', '2018-12-10', '754', '1', '305', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1509', '2018-12-11', '755', '33', '305', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1510', '2018-12-12', '755', '33', '305', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1511', '2018-12-13', '756', '33', '305', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1512', '2018-12-14', '366', '33', '305', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1513', '2018-12-15', '757', '33', '66', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1514', '2018-12-16', '757', '33', '306', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1515', '2018-12-17', '758', '33', '306', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1516', '2018-12-18', '758', '2', '306', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1517', '2018-12-19', '759', '33', '306', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1518', '2018-12-20', '564', '33', '307', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1519', '2018-12-21', '760', '33', '307', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1520', '2018-12-22', '760', '33', '307', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1521', '2018-12-23', '761', '33', '307', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1522', '2018-12-24', '761', '33', '307', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1527', '2018-12-29', '764', '34', '308', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1528', '2018-12-30', '764', '34', '309', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1529', '2018-12-31', '765', '34', '309', '14', '2018-01-25 06:38:20', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1554', '2018-01-01', '333', '34', '314', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1555', '2018-01-02', '778', '34', '314', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1556', '2018-01-03', '778', '34', '314', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1557', '2018-01-04', '779', '34', '314', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1558', '2018-01-05', '779', '34', '315', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1559', '2018-01-06', '780', '34', '315', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1560', '2018-01-07', '780', '34', '315', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1561', '2018-01-08', '781', '34', '315', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1562', '2018-01-09', '781', '34', '315', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1563', '2018-01-10', '782', '34', '316', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1564', '2018-01-11', '782', '34', '316', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1565', '2018-01-12', '783', '34', '316', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1566', '2018-01-13', '783', '34', '316', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1567', '2018-01-14', '784', '34', '316', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1568', '2018-01-15', '784', '34', '317', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1569', '2018-01-16', '23', '34', '317', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1570', '2018-01-17', '785', '34', '317', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1571', '2018-01-18', '786', '34', '317', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1572', '2018-01-19', '786', '34', '317', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1573', '2018-01-20', '787', '2', '318', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1574', '2018-01-21', '787', '2', '318', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1575', '2018-01-22', '788', '1', '318', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1576', '2018-01-23', '788', '35', '318', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1577', '2018-01-24', '789', '35', '318', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1578', '2018-01-25', '789', '1', '319', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1579', '2018-01-26', '790', '1', '319', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1580', '2018-01-27', '531', '2', '319', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1581', '2018-01-28', '791', '2', '319', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1582', '2018-01-29', '791', '2', '319', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1583', '2018-01-30', '2', '4', '2', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1584', '2018-01-31', '792', '35', '320', '14', '2018-02-02 08:58:31', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1586', '2018-02-01', '793', '4', '3', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1587', '2018-02-02', '794', '2', '1', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1588', '2018-02-03', '794', '1', '4', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1589', '2018-02-04', '795', '35', '321', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1590', '2018-02-05', '795', '35', '321', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1591', '2018-02-06', '796', '35', '321', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1592', '2018-02-07', '796', '35', '321', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1593', '2018-02-08', '797', '35', '322', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1594', '2018-02-09', '797', '35', '322', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1595', '2018-02-10', '99', '35', '322', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1596', '2018-02-11', '798', '35', '322', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1597', '2018-02-12', '799', '35', '322', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1598', '2018-02-13', '799', '35', '323', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1599', '2018-02-14', '800', '35', '323', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1600', '2018-02-15', '800', '35', '323', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1601', '2018-02-16', '89', '35', '90', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1602', '2018-02-17', '801', '35', '323', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1603', '2018-02-18', '802', '23', '324', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1604', '2018-02-19', '802', '2', '324', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1605', '2018-02-20', '803', '56', '324', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1606', '2018-02-21', '45', '35', '89', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1607', '2018-02-22', '804', '35', '324', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1608', '2018-02-23', '804', '35', '325', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1609', '2018-02-24', '805', '1', '3', '14', '2018-02-25 10:54:34', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1610', '2018-05-15', '0', '0', '0', '7', '2018-05-16 00:25:27', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1611', '2018-10-01', '0', '0', '0', '7', '2018-10-24 16:17:15', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1612', '2018-10-02', '0', '0', '0', '7', '2018-10-24 16:17:15', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1613', '2018-10-03', '0', '0', '0', '7', '2018-10-24 16:17:15', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1614', '2018-10-04', '0', '0', '0', '7', '2018-10-24 16:17:15', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1615', '2018-10-05', '0', '0', '0', '7', '2018-10-24 16:17:15', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1078490017163833345', '2018-12-28', '0', '0', '154', '170', '2018-12-28 11:17:12', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1087142127818768386', '2019-01-02', '2', '0', '167', '177', '2019-01-21 08:17:36', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1087198321809457153', '2019-01-01', '1', '0', '130', '189', '2019-01-21 12:00:54', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1087198383973236738', '2019-01-03', '0', '0', '114', '130', '2019-01-21 12:01:09', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1087451681764982785', '2019-01-04', '0', '0', '118', '155', '2019-01-22 04:47:39', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1087455336471785473', '2019-01-05', '0', '0', '184', '186', '2019-01-22 05:02:11', '2019-09-09 12:12:12');
INSERT INTO `statistics_daily` VALUES ('1105339962460491777', '2019-03-01', '0', '143', '168', '136', '2019-03-12 13:29:18', '2019-03-12 13:29:18');
INSERT INTO `statistics_daily` VALUES ('1105339977027309569', '2019-03-02', '0', '165', '171', '158', '2019-03-12 13:29:21', '2019-03-12 13:29:21');
INSERT INTO `statistics_daily` VALUES ('1105339990738489346', '2019-03-03', '0', '143', '147', '194', '2019-03-12 13:29:25', '2019-03-12 13:29:25');
INSERT INTO `statistics_daily` VALUES ('1105340000544772098', '2019-03-04', '0', '155', '106', '153', '2019-03-12 13:29:27', '2019-03-12 13:29:27');
INSERT INTO `statistics_daily` VALUES ('1105340011244441602', '2019-03-05', '0', '186', '102', '155', '2019-03-12 13:29:30', '2019-03-12 13:29:30');
INSERT INTO `statistics_daily` VALUES ('1105340020929089538', '2019-03-06', '0', '140', '192', '129', '2019-03-12 13:29:32', '2019-03-12 13:29:32');
INSERT INTO `statistics_daily` VALUES ('1105340029800042497', '2019-03-07', '0', '186', '139', '116', '2019-03-12 13:29:34', '2019-03-12 13:29:34');
INSERT INTO `statistics_daily` VALUES ('1105340038696161282', '2019-03-08', '0', '120', '166', '112', '2019-03-12 13:29:36', '2019-03-12 13:29:36');
INSERT INTO `statistics_daily` VALUES ('1105340049441968129', '2019-03-09', '0', '182', '147', '119', '2019-03-12 13:29:39', '2019-03-12 13:29:39');
INSERT INTO `statistics_daily` VALUES ('1105340059738984449', '2019-03-10', '0', '199', '141', '103', '2019-03-12 13:29:41', '2019-03-12 13:29:41');
INSERT INTO `statistics_daily` VALUES ('1105340070438653953', '2019-03-11', '0', '127', '137', '156', '2019-03-12 13:29:44', '2019-03-12 13:29:44');
INSERT INTO `statistics_daily` VALUES ('1105340080307851266', '2019-03-12', '0', '167', '123', '132', '2019-03-12 13:29:46', '2019-03-12 13:29:46');
INSERT INTO `statistics_daily` VALUES ('1105340090047025153', '2019-03-13', '0', '106', '132', '103', '2019-03-12 13:29:48', '2019-03-12 13:29:48');
INSERT INTO `statistics_daily` VALUES ('1105340100075606017', '2019-03-14', '0', '166', '180', '118', '2019-03-12 13:29:51', '2019-03-12 13:29:51');
INSERT INTO `statistics_daily` VALUES ('1105340110511034370', '2019-03-15', '0', '114', '151', '185', '2019-03-12 13:29:53', '2019-03-12 13:29:53');
INSERT INTO `statistics_daily` VALUES ('1105340121412030466', '2019-03-16', '0', '134', '105', '126', '2019-03-12 13:29:56', '2019-03-12 13:29:56');
INSERT INTO `statistics_daily` VALUES ('1105340132833120258', '2019-03-17', '0', '169', '106', '131', '2019-03-12 13:29:59', '2019-03-12 13:29:59');
INSERT INTO `statistics_daily` VALUES ('1105340145659301890', '2019-03-18', '0', '120', '106', '163', '2019-03-12 13:30:02', '2019-03-12 13:30:02');
INSERT INTO `statistics_daily` VALUES ('1105340153578147842', '2019-03-19', '0', '146', '155', '153', '2019-03-12 13:30:03', '2019-03-12 13:30:03');
INSERT INTO `statistics_daily` VALUES ('1105340162436517890', '2019-03-20', '0', '127', '181', '186', '2019-03-12 13:30:06', '2019-03-12 13:30:06');
INSERT INTO `statistics_daily` VALUES ('1105340171517186050', '2019-03-21', '0', '106', '134', '145', '2019-03-12 13:30:08', '2019-03-12 13:30:08');
INSERT INTO `statistics_daily` VALUES ('1105340181034061825', '2019-03-22', '0', '161', '182', '143', '2019-03-12 13:30:10', '2019-03-12 13:30:10');
INSERT INTO `statistics_daily` VALUES ('1105340190072786946', '2019-03-23', '0', '183', '101', '182', '2019-03-12 13:30:12', '2019-03-12 13:30:12');
INSERT INTO `statistics_daily` VALUES ('1105340199426084865', '2019-03-24', '0', '117', '100', '102', '2019-03-12 13:30:14', '2019-03-12 13:30:14');
INSERT INTO `statistics_daily` VALUES ('1105340209261727745', '2019-03-25', '0', '116', '103', '160', '2019-03-12 13:30:17', '2019-03-12 13:30:17');
INSERT INTO `statistics_daily` VALUES ('1105340217935548418', '2019-03-26', '0', '101', '119', '150', '2019-03-12 13:30:19', '2019-03-12 13:30:19');
INSERT INTO `statistics_daily` VALUES ('1105340225967640577', '2019-03-27', '0', '129', '141', '181', '2019-03-12 13:30:21', '2019-03-12 13:30:21');
INSERT INTO `statistics_daily` VALUES ('1105340234075230209', '2019-03-28', '0', '113', '179', '158', '2019-03-12 13:30:23', '2019-03-12 13:30:23');
INSERT INTO `statistics_daily` VALUES ('1105340242837131265', '2019-03-29', '0', '117', '131', '101', '2019-03-12 13:30:25', '2019-03-12 13:30:25');
INSERT INTO `statistics_daily` VALUES ('1105340252395950082', '2019-03-30', '0', '153', '187', '174', '2019-03-12 13:30:27', '2019-03-12 13:30:27');
INSERT INTO `statistics_daily` VALUES ('1105340261958963201', '2019-03-31', '0', '179', '135', '199', '2019-03-12 13:30:29', '2019-03-12 13:30:29');
INSERT INTO `statistics_daily` VALUES ('1105372743634898945', '2019-01-06', '0', '113', '143', '148', '2019-03-12 15:39:34', '2019-03-12 15:39:34');
INSERT INTO `statistics_daily` VALUES ('1105372754380705793', '2019-01-07', '0', '173', '158', '153', '2019-03-12 15:39:36', '2019-03-12 15:39:36');
INSERT INTO `statistics_daily` VALUES ('1105372762526044162', '2019-01-08', '0', '135', '199', '131', '2019-03-12 15:39:38', '2019-03-12 15:39:38');
INSERT INTO `statistics_daily` VALUES ('1105372771229224961', '2019-01-09', '0', '161', '107', '162', '2019-03-12 15:39:40', '2019-03-12 15:39:40');
INSERT INTO `statistics_daily` VALUES ('1105372780922261505', '2019-01-10', '0', '182', '196', '135', '2019-03-12 15:39:42', '2019-03-12 15:39:42');
INSERT INTO `statistics_daily` VALUES ('1105372790103592961', '2019-01-11', '0', '123', '194', '102', '2019-03-12 15:39:45', '2019-03-12 15:39:45');
INSERT INTO `statistics_daily` VALUES ('1105372798626418689', '2019-01-12', '0', '185', '106', '180', '2019-03-12 15:39:47', '2019-03-12 15:39:47');
INSERT INTO `statistics_daily` VALUES ('1183991737299263490', '2019-10-01', '0', '145', '149', '153', '2019-10-15 14:23:22', '2019-10-15 14:23:22');
INSERT INTO `statistics_daily` VALUES ('1183999299272904705', '2019-10-03', '0', '125', '102', '153', '2019-10-15 14:53:25', '2019-10-15 14:53:25');
INSERT INTO `statistics_daily` VALUES ('1183999309477646338', '2019-10-04', '0', '103', '194', '161', '2019-10-15 14:53:28', '2019-10-15 14:53:28');
INSERT INTO `statistics_daily` VALUES ('1183999318919024642', '2019-10-05', '0', '173', '174', '147', '2019-10-15 14:53:30', '2019-10-15 14:53:30');
INSERT INTO `statistics_daily` VALUES ('1183999331409661954', '2019-10-06', '0', '129', '197', '173', '2019-10-15 14:53:33', '2019-10-15 14:53:33');
INSERT INTO `statistics_daily` VALUES ('1183999342897860610', '2019-10-07', '0', '184', '125', '169', '2019-10-15 14:53:36', '2019-10-15 14:53:36');
INSERT INTO `statistics_daily` VALUES ('1183999351588458498', '2019-10-08', '0', '173', '143', '138', '2019-10-15 14:53:38', '2019-10-15 14:53:38');
INSERT INTO `statistics_daily` VALUES ('1183999360316805122', '2019-10-09', '0', '161', '158', '117', '2019-10-15 14:53:40', '2019-10-15 14:53:40');
INSERT INTO `statistics_daily` VALUES ('1183999367480676353', '2019-10-10', '0', '190', '166', '135', '2019-10-15 14:53:42', '2019-10-15 14:53:42');
INSERT INTO `statistics_daily` VALUES ('1184030117693284353', '2019-10-11', '0', '182', '199', '193', '2019-10-15 16:55:53', '2019-10-15 16:55:53');
INSERT INTO `statistics_daily` VALUES ('1185373213064482818', '2019-01-19', '6', '135', '126', '167', '2019-10-19 09:52:52', '2019-10-19 09:52:52');
INSERT INTO `statistics_daily` VALUES ('1186536619937435650', '2019-10-02', '0', '187', '183', '108', '2019-10-22 14:55:50', '2019-10-22 14:55:50');
INSERT INTO `statistics_daily` VALUES ('1192253597226455042', '2019-11-01', '0', '129', '122', '167', '2019-11-07 09:33:03', '2019-11-07 09:33:03');
INSERT INTO `statistics_daily` VALUES ('1192253902756335617', '2019-11-02', '0', '170', '192', '192', '2019-11-07 09:34:16', '2019-11-07 09:34:16');
INSERT INTO `statistics_daily` VALUES ('1194060032935493633', '2019-11-11', '0', '121', '121', '122', '2019-11-12 09:11:11', '2019-11-12 09:11:11');
INSERT INTO `statistics_daily` VALUES ('1194060301425475585', '2019-11-10', '0', '155', '187', '140', '2019-11-12 09:12:15', '2019-11-12 09:12:15');
INSERT INTO `statistics_daily` VALUES ('1542778443513462786', '2018-07-19', '0', '126', '150', '102', '2022-07-01 15:53:39', '2022-07-01 15:53:39');
INSERT INTO `statistics_daily` VALUES ('1542778598606241794', '2022-07-21', '0', '125', '154', '167', '2022-07-01 15:54:16', '2022-07-01 15:54:16');
INSERT INTO `statistics_daily` VALUES ('1542778722480816130', '2022-07-19', '0', '143', '127', '129', '2022-07-01 15:54:46', '2022-07-01 15:54:46');
INSERT INTO `statistics_daily` VALUES ('1542780041534898178', '2022-07-01', '0', '128', '112', '139', '2022-07-01 16:00:00', '2022-07-01 16:00:00');
INSERT INTO `statistics_daily` VALUES ('1543146607464169473', '2018-07-09', '0', '169', '165', '159', '2022-07-02 16:16:36', '2022-07-02 16:16:36');
INSERT INTO `statistics_daily` VALUES ('1543886389232398337', '2022-07-03', '0', '140', '176', '155', '2022-07-04 17:16:14', '2022-07-04 17:16:14');
INSERT INTO `statistics_daily` VALUES ('1544610113724190722', '2022-06-29', '0', '170', '147', '135', '2022-07-06 17:12:03', '2022-07-06 17:12:03');
INSERT INTO `statistics_daily` VALUES ('1544612206711246849', '2022-06-28', '0', '108', '135', '100', '2022-07-06 17:20:22', '2022-07-06 17:20:22');
INSERT INTO `statistics_daily` VALUES ('1544612293176823810', '2022-07-05', '0', '107', '146', '103', '2022-07-06 17:20:43', '2022-07-06 17:20:43');
INSERT INTO `statistics_daily` VALUES ('1544652391683457025', '2022-07-06', '1', '144', '141', '171', '2022-07-06 20:00:03', '2022-07-06 20:00:03');
INSERT INTO `statistics_daily` VALUES ('1545044971172745217', '2022-07-07', '1', '110', '138', '132', '2022-07-07 22:00:01', '2022-07-07 22:00:01');
INSERT INTO `statistics_daily` VALUES ('1545054346822111234', '2019-07-10', '0', '158', '132', '148', '2022-07-07 22:37:17', '2022-07-07 22:37:17');
INSERT INTO `statistics_daily` VALUES ('1545216306901528577', '2022-07-04', '0', '116', '126', '194', '2022-07-08 09:20:51', '2022-07-08 09:20:51');
INSERT INTO `statistics_daily` VALUES ('1545377159609081857', '2022-07-08', '0', '123', '180', '174', '2022-07-08 20:00:01', '2022-07-08 20:00:01');

-- ----------------------------
-- Table structure for student_member
-- ----------------------------
DROP TABLE IF EXISTS `student_member`;
CREATE TABLE `student_member` (
  `id` bigint NOT NULL COMMENT '会员id',
  `openid` varchar(128) DEFAULT NULL COMMENT '微信openid',
  `mobile` varchar(11) DEFAULT '' COMMENT '手机号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `sex` tinyint unsigned DEFAULT NULL COMMENT '性别 1 女，2 男',
  `age` tinyint unsigned DEFAULT NULL COMMENT '年龄',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT 'https://img.51miz.com/Element/00/88/08/84/72f298b9_E880884_d0f63115.png' COMMENT '用户头像',
  `sign` varchar(100) DEFAULT NULL COMMENT '用户签名',
  `is_enable` tinyint(1) NOT NULL DEFAULT '1' COMMENT '是否启用 1（true）已启用，  0（false）已禁用',
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '逻辑删除 1（true）已删除， 0（false）未删除',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='会员表';

-- ----------------------------
-- Records of student_member
-- ----------------------------
INSERT INTO `student_member` VALUES ('1', null, '13700000001', '96e79218965eb72c92a549dd5a330112', '小三123', '1', '5', 'https://cquoes.oss-cn-hangzhou.aliyuncs.com/2022/07/05/8ca729a81fbNo.Game.No(2).jpg', '我的个人签名', '1', '0', '2019-01-01 12:11:33', '2022-07-26 10:11:31');
INSERT INTO `student_member` VALUES ('1080736474267144193', null, '13700000011', '96e79218965eb72c92a549dd5a330112', '用户XJtDfaYeKk', '1', '19', 'https://img.51miz.com/Element/00/88/08/84/72f298b9_E880884_d0f63115.png', null, '1', '0', '2019-01-02 12:12:45', '2019-01-02 12:12:56');
INSERT INTO `student_member` VALUES ('1080736474355224577', null, '13700000002', '96e79218965eb72c92a549dd5a330112', '用户wUrNkzAPrc', '1', '27', 'https://img.51miz.com/Element/00/88/08/84/72f298b9_E880884_d0f63115.png', null, '1', '0', '2019-01-02 12:13:56', '2019-01-02 12:14:07');
INSERT INTO `student_member` VALUES ('1086387099449442306', null, '13520191388', '96e79218965eb72c92a549dd5a330112', '用户XTMUeHDAoj', '2', '20', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', null, '1', '0', '2019-01-19 06:17:23', '2019-01-19 06:17:23');
INSERT INTO `student_member` VALUES ('1086387099520745473', null, '13520191389', '96e79218965eb72c92a549dd5a330112', '用户vSdKeDlimn', '1', '21', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', null, '1', '0', '2019-01-19 06:17:23', '2019-01-19 06:17:23');
INSERT INTO `student_member` VALUES ('1086387099608825858', null, '13520191381', '96e79218965eb72c92a549dd5a330112', '用户EoyWUVXQoP', '1', '18', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', null, '1', '0', '2019-01-19 06:17:23', '2019-01-19 06:17:23');
INSERT INTO `student_member` VALUES ('1086387099701100545', null, '13520191382', '96e79218965eb72c92a549dd5a330112', '用户LcAYbxLNdN', '2', '24', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', null, '1', '0', '2019-01-19 06:17:23', '2019-01-19 06:17:23');
INSERT INTO `student_member` VALUES ('1086387099776598018', null, '13520191383', '96e79218965eb72c92a549dd5a330112', '用户dZdjcgltnk', '2', '25', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', null, '1', '0', '2019-01-19 06:17:23', '2019-01-19 06:17:23');
INSERT INTO `student_member` VALUES ('1086387099852095490', null, '13520191384', '96e79218965eb72c92a549dd5a330112', '用户wNHGHlxUwX', '2', '23', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', null, '1', '0', '2019-01-19 06:17:23', '2019-01-19 06:17:23');
INSERT INTO `student_member` VALUES ('1106746895272849410', 'o1R-t5u2TfEVeVjO9CPGdHPNw-to', null, '96e79218965eb72c92a549dd5a330112', '檀梵\'', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/zZfLXcetf2Rpsibq6HbPUWKgWSJHtha9y1XBeaqluPUs6BYicW1FJaVqj7U3ozHd3iaodGKJOvY2PvqYTuCKwpyfQ/132', null, '1', '0', '2019-03-16 10:39:57', '2019-03-16 10:39:57');
INSERT INTO `student_member` VALUES ('1106822699956654081', null, null, '96e79218965eb72c92a549dd5a330112', '12', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', null, '1', '0', '2019-03-16 15:41:10', '2019-03-16 15:41:10');
INSERT INTO `student_member` VALUES ('1106823035660357634', 'o1R-t5i4gENwHYRb5lVFy98Z0bdk', null, null, 'GaoSir', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJI53RcCuc1no02os6ZrattWGiazlPnicoZQ59zkS7phNdLEWUPDk8fzoxibAnXV1Sbx0trqXEsGhXPw/132', null, '1', '0', '2019-03-16 15:42:30', '2019-03-16 15:42:30');
INSERT INTO `student_member` VALUES ('1106823041599492098', null, null, '96e79218965eb72c92a549dd5a330112', '13', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', null, '1', '0', '2019-03-16 15:42:32', '2019-03-16 15:42:32');
INSERT INTO `student_member` VALUES ('1106823115788341250', 'o1R-t5l_3rnbZbn4jWwFdy6Gk6cg', null, '', '换个网名哇、', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/jJHyeM0EN2jhB70LntI3k8fEKe7W6CwykrKMgDJM4VZqCpcxibVibX397p0vmbKURGkLS4jxjGB0GpZfxCicgt07w/132', null, '1', '0', '2019-03-16 15:42:49', '2019-03-16 15:42:49');
INSERT INTO `student_member` VALUES ('1106826046730227714', 'o1R-t5gyxumyBqt0CWcnh0S6Ya1g', null, '', '我是Helen', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKDRfib8wy7A2ltERKh4VygxdjVC1x5OaOb1t9hot4JNt5agwaVLdJLcD9vJCNcxkvQnlvLYIPfrZw/132', null, '1', '0', '2019-03-16 15:54:28', '2019-03-16 15:54:28');
INSERT INTO `student_member` VALUES ('1106828185829490690', 'o1R-t5nNlou5lRwBVgGNJFm4rbc4', null, '', ' 虎头', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTKxCqRzuYWQmpwiaqQEjNxbC7WicebicXQusU306jgmfoOzUcFg1qaDq5BStiblwBjw5dUOblQ2gUicQOQ/132', null, '1', '0', '2019-03-16 16:02:58', '2019-03-16 16:02:58');
INSERT INTO `student_member` VALUES ('1106830599651442689', 'o1R-t5hZHQB1cbX7HZJsiM727_SA', null, '', '是吴啊', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/Q0j4TwGTfTJ9CsqApybcs7f3Dyib9IxIh0sBqJb7LicbjU4WticJFF0PVwFvHgtbFdBwfmk3H2t3NyqmEmVx17tRA/132', null, '1', '0', '2019-03-16 16:12:34', '2019-03-16 16:12:34');
INSERT INTO `student_member` VALUES ('1106830976199278593', 'o1R-t5meKOoyEJ3-IhWRCBKFcvzU', null, null, '我才是Helen', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83epMicP9UT6mVjYWdno0OJZkOXiajG0sllJTbGJ9DYiceej2XvbDSGCK8LCF7jv1PuG2uoYlePWic9XO8A/132', null, '1', '0', '2019-03-16 16:14:03', '2019-03-16 16:14:03');
INSERT INTO `student_member` VALUES ('1106831936900415490', 'o1R-t5jXYSWakGtnUBnKbfVT5Iok', null, null, '文若姬', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/3HEmJwpSzguqqAyzmBwqT6aicIanswZibEOicQInQJI3ZY1qmu59icJC6N7SahKqWYv24GvX5KH2fibwt0mPWcTJ3fg/132', null, '1', '0', '2019-03-16 16:17:52', '2019-03-16 16:17:52');
INSERT INTO `student_member` VALUES ('1106832491064442882', 'o1R-t5sud081Qsa2Vb2xSKgGnf_g', null, null, 'Peanut', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', null, '1', '0', '2019-03-16 16:20:04', '2019-03-16 16:20:04');
INSERT INTO `student_member` VALUES ('1106833021442510849', 'o1R-t5lsGc3I8P5bDpHj7m_AIRvQ', null, null, '食物链终结者', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/MQ7qUmCprK9am16M1Ia1Cs3RK0qiarRrl9y8gsssBjIZeS2GwKSrnq7ZYhmrzuzDwBxSMMAofrXeLic9IBlW4M3Q/132', null, '1', '0', '2019-03-16 16:22:11', '2019-03-16 16:22:11');
INSERT INTO `student_member` VALUES ('1191600824445046786', null, '15210078344', '96e79218965eb72c92a549dd5a330112', 'IT妖姬', '1', '5', 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', null, '1', '0', '2019-11-05 14:19:10', '2019-11-08 18:04:43');
INSERT INTO `student_member` VALUES ('1191616288114163713', null, '17866603606', '96e79218965eb72c92a549dd5a330112', 'xiaowu', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', null, '1', '0', '2019-11-05 15:20:37', '2019-11-05 15:20:37');
INSERT INTO `student_member` VALUES ('1195187659054329857', null, '15010546384', '96e79218965eb72c92a549dd5a330112', 'qy', null, null, 'http://thirdwx.qlogo.cn/mmopen/vi_32/DYAIOgq83eoj0hHXhgJNOTSOFsS4uZs8x1ConecaVOB8eIl115xmJZcT4oCicvia7wMEufibKtTLqiaJeanU2Lpg3w/132', null, '1', '0', '2019-11-15 11:51:58', '2019-11-15 11:51:58');
INSERT INTO `student_member` VALUES ('1542307507052015618', 'o3_SC599cgeV8i75dWrlqrkGUA2I', '', null, '卫天', null, null, 'https://thirdwx.qlogo.cn/mmopen/vi_32/rhwWwianRPt65VVJXL1TXZmPaCt6GbKGz6LSdGwbCZWP0pUgEl96BDwEDaSzRIHyw1A7LrfJVrLWZ29pF51bRyg/132', null, '1', '0', '2022-06-30 08:42:19', '2022-06-30 08:42:19');
INSERT INTO `student_member` VALUES ('1542309609698574338', 'o3_SC5zRydLw97xk_TEBQMRS3NWI', '', null, '哄哄', '2', '10', 'https://thirdwx.qlogo.cn/mmopen/vi_32/Q3auHgzwzM5JBpZdGzrpibAypSwaQPeBgXuGHN3INUxrMJHXc9fypebib8iaibYibjtjf1868OwYAPEgEWpYjXPibbSQ/132', '在学习zhong', '1', '0', '2022-06-30 08:50:40', '2022-07-08 09:08:57');
INSERT INTO `student_member` VALUES ('1545034536738373633', null, '13509432595', '96e79218965eb72c92a549dd5a330112', '好起来了', '2', '20', 'https://img.51miz.com/Element/00/88/08/84/72f298b9_E880884_d0f63115.png', '我的个性签名', '1', '0', '2022-07-07 21:18:34', '2022-07-26 10:39:29');
