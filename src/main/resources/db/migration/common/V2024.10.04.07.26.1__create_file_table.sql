create table if not exists file
(
    id                 bigint auto_increment primary key comment '사용자 역할 고유 ID',
    original_file_name varchar(255) not null comment '원본 파일명',
    stored_file_name   varchar(255) not null comment '저장된 파일명',
    file_endpoint      varchar(255) not null comment '파일을 다운받을 수 있는 EndPoint',
    upload_user        bigint       not null comment '업로드한 사람 (admin_user)',
    modified_date      DATETIME comment '최종 수정 날짜',
    created_date       DATETIME comment '생성 날자'
) comment '파일 메타 데이터 테이블'
