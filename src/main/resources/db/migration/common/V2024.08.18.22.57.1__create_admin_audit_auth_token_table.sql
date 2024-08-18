create table if not exists admin_audit_auth_token
(
    id            bigint auto_increment primary key comment '인증 토큰 감사 고유 ID',
    admin_user_id bigint not null comment '사용자 ID',
    auth_token_hash varchar(200) not null comment 'Access Token 해쉬',
    auth_token_issue_date timestamp not null comment 'Access Token 발급 시간',
    refresh_token_hash varchar(200) not null comment 'Refresh Token 해쉬',
    refresh_token_issue_date timestamp not null comment 'Refresh Token 발급 시간'
);

