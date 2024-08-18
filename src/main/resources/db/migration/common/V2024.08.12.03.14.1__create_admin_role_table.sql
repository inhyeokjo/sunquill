create table if not exists admin_role
(
    id          bigint auto_increment primary key comment '역할 고유 ID',
    role_cd     varchar(50)  not null comment '역할 코드',
    role_name   varchar(30)  not null comment '역할 이름',
    description varchar(100) not null comment '역할 설멍'
) comment '역할 관리'
