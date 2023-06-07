CREATE TABLE IF NOT EXISTS `user` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `name` varchar(200) DEFAULT NULL COMMENT '姓名',
    `token` varchar(200) DEFAULT NULL COMMENT 'tocken',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';

CREATE TABLE IF NOT EXISTS `chat` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT,
    `fromName` varchar(200) DEFAULT NULL,
    `toName` varchar(200) DEFAULT NULL,
    `time` BIGINT DEFAULT NULL,
    `msg` varchar(200) DEFAULT NULL,
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='聊天表';
