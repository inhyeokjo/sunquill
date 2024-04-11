create table if not exists api_access_log
(
    access_log_id              bigint auto_increment comment '로그 고유 ID' primary key,
    request_id      varchar(255) comment '고유 요청 ID(요청의 고유 식별 GUID)',
    request_time    timestamp default current_timestamp()   not null comment '요청 시간',
    response_time   timestamp default '0000-00-00 00:00:00' not null comment '응답 시간',
    execution_time  bigint comment '실행 시간(ms)',
    method          varchar(255) comment '요청 method',
    uri             varchar(255) comment 'API URI',
    response_code   varchar(10) comment '응답 코드',
    client_ip       varchar(255) comment '요청 IP',
    user_agent      text comment 'user_agent header 값',
    stack_trace     text comment '오류 발생시 StackTrace',
    request_header  text comment '요청 헤더',
    request_body    text comment '요청 본문',
    response_header text comment '응답 헤더',
    response_body   text comment '응답 바디'
);

create index if not exists idx_request_id on api_access_log (request_id);

