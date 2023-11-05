drop table if exists editor_pick;

create table if not exists editor_pick
(
    editor_pick_id BIGINT PRIMARY KEY AUTO_INCREMENT comment '자동 채번된 ID',
    title          varchar(255) comment '기사 이름',
    category       varchar(255) comment '기사가 속한 카테고리',
    article_id     BIGINT comment '해당 기사 번호',
    modified_date  DATETIME comment '최종 수정 날짜',
    created_date   DATETIME comment '생성 날자'
);
