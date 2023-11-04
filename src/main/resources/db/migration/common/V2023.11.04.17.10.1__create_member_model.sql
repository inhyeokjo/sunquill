CREATE TABLE member
(
    member_id           BIGINT PRIMARY KEY AUTO_INCREMENT,
    name                VARCHAR(255),
    profile_picture_url VARCHAR(255),
    email               VARCHAR(255),
    role                VARCHAR(255),
    phone               VARCHAR(255)
);

