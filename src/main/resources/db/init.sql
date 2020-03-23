-- ----------------------------
-- Table structure for t_department
-- ----------------------------
DROP TABLE IF EXISTS `t_department`;
CREATE TABLE `t_department`
(
    `id`              bigint(13)  NOT NULL AUTO_INCREMENT COMMENT '资源主键',
    `department_id`   varchar(20) NOT NULL COMMENT '部门id',
    `department_name` varchar(50) NOT NULL DEFAULT '' COMMENT '部门名称',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 15
  DEFAULT CHARSET = utf8;

-- ----------------------------
-- Table structure for t_employee
-- ----------------------------
DROP TABLE IF EXISTS `t_employee`;
CREATE TABLE `t_employee`
(
    `id`            bigint(13)  NOT NULL AUTO_INCREMENT COMMENT '资源主键',
    `employee_id`   varchar(20) NOT NULL DEFAULT '' COMMENT '员工id',
    `employee_name` varchar(50) NOT NULL DEFAULT '' COMMENT '员工名称',
    `department_id` varchar(50) NOT NULL DEFAULT '' COMMENT '部门id',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 11
  DEFAULT CHARSET = utf8;