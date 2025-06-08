create database if not exists `daka`;

use `daka`;

drop table if exists exercise_record;

create table `exercise_record`
(
    id            bigint auto_increment comment 'id' primary key comment 'id',
    `user_id`     int(11)  default 123               not null comment '用户id',
    `create_time` datetime default current_timestamp not null comment '创建时间',
    `description` varchar(255)                       not null comment '描述'
) engine = innodb
  default charset = utf8mb4;

insert into exercise_record(`user_id`, `description`)
values (123, '卧推深蹲硬拉');
select *
from exercise_record;

