drop table if exists photo_journal;

create table if not exists photo_journal
(
    photo_journal_id BIGINT PRIMARY KEY AUTO_INCREMENT comment '자동 채번된 ID',
    description      varchar(255) comment '사진에 대한 짧은 문구',
    volume_number    int comment '잡지 권 번호',
    photographer_id  bigint comment '사진 작가의 멤버 번호',
    photo_link       varchar(255) comment '이미지 링크',
    modified_date    DATETIME comment '최종 수정 날짜',
    created_date     DATETIME comment '생성 날자'
);

