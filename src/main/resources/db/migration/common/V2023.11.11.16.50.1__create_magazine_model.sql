drop table if exists magazine;

create table if not exists magazine
(
    magazine_id       BIGINT PRIMARY KEY AUTO_INCREMENT comment '자동 채번된 ID',
    volume_number     int comment '잡지 권 번호',
    publish_date      date comment '잡지 출판 날자',
    volume_cover_link varchar(255) comment '잡지 표지 이미지 주소',
    file_link         varchar(255) comment '잡지 파일 링크(PDF)',
    modified_date     DATETIME comment '최종 수정 날짜',
    created_date      DATETIME comment '생성 날자'
);
