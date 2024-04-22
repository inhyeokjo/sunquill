alter table article
    add column if not exists author_name varchar(255) after author_id;

update article a
    join member m on m.member_id = a.author_id
set author_name = m.name
where a.author_name is null;

alter table article
    modify column author_name varchar(255)
        not null
        comment '기자 이름';


