lvonehw

글번호 - no
제목 - title
작성자ID - id
작성내용 - contents
작성날짜 - save_date
비밀번호 - con_pw

create table MANAGER
(
no bigint not null auto_increment comment '글번호',
id varchar(100) not null comment '작성자ID',
title varchar(500) not null comment '제목',
contents varchar(500) not null comment '작성내용',
con_pw varchar(255) not null comment '비밀번호',
save_date date not null comment '작성날짜',
primary key (no)
);