create table if not exists mission
(
    mission_id    BIGINT PRIMARY KEY AUTO_INCREMENT comment '자동 채번된 ID',
    mission_text  text comment '미션 글귀',
    modified_date DATETIME,
    created_date  DATETIME
);
