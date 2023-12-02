create table if not exists top_article
(
    top_article_id BIGINT PRIMARY KEY AUTO_INCREMENT comment '자동 채번된 ID',
    file_api       varchar(255) comment 'server에서 file을 가져올 수 있는 Rest Api 주소',
    title          varchar(255) comment '기사 이름',
    summary        varchar(255) comment '기사 요약',
    article_id     BIGINT comment '해당 기사 번호',
    modified_date  DATETIME comment '최종 수정 날짜',
    created_date   DATETIME comment '생성 날자'
);

