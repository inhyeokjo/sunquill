drop table if exists article;

CREATE TABLE if not exists article
(
    article_id    BIGINT PRIMARY KEY AUTO_INCREMENT,
    title         VARCHAR(255),
    picture_url   VARCHAR(255),
    category      VARCHAR(255),
    contents      LONGTEXT,
    invisible     BIT,
    author_id     BIGINT,
    modified_date DATETIME,
    created_date  DATETIME
)
