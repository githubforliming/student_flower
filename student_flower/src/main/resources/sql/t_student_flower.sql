CREATE TABLE `t_student_flower` (
                                    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键（自增）',
                                    `classroom_id` bigint(20) NOT NULL COMMENT '每堂课的唯一标识',
                                    `student_id` bigint(20) NOT NULL COMMENT '学生唯一标识',
                                    `flower_num` bigint(20) NOT NULL DEFAULT '0' COMMENT '学生收到的花数量',
                                    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8