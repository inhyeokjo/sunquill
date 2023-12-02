create table if not exists video_info
(
    video_info_id BIGINT PRIMARY KEY AUTO_INCREMENT comment '자동 채번된 ID',
    iframe_src    varchar(255) comment 'Youtube embed iframe src',
    title         varchar(255) comment '비디오 이름',
    upload_date   DATETIME comment '영상 업로드 날자',
    modified_date DATETIME comment '최종 수정 날짜',
    created_date  DATETIME comment '생성 날자'
);
