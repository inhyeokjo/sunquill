create table if not exists admin_user
(
    id bigint auto_increment comment '어드민 사용자 ID' primary key,
    mail          varchar(255) not null comment 'email',
    password      varchar(255) not null comment '비밀번호',
    name          varchar(255) not null comment '이름',
    retired       bool         not null default false comment '은퇴 여부'
) comment 'ADMIN 서비스를 사용할 사용자 테이블';
