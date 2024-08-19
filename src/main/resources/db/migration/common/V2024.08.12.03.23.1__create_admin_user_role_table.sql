create table if not exists admin_user_role
(
    id        bigint auto_increment primary key comment '사용자 역할 고유 ID',
    role_id   bigint not null comment '역할 ID',
    admin_user_id bigint not null comment '사용자 ID'
) comment '사용자별 역할 관리 매핑 테이블'
