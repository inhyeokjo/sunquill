ALTER TABLE `snuquill`.`top_article`
    CHANGE COLUMN if exists file_api picture_url
        varchar(255) null comment 'article 대표 이미지';
