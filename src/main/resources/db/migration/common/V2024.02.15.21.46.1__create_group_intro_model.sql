create table if not exists group_intro
(
    group_intro_id BIGINT PRIMARY KEY AUTO_INCREMENT comment '자동 채번된 ID',
    introduction   text comment '소개글',
    group_img_link varchar(255) comment 'SNU Quill 대표사진 링크',
    modified_date  DATETIME,
    created_date   DATETIME
);
