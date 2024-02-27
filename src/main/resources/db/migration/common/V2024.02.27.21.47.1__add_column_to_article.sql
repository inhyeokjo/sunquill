alter table article add column if not exists view_count bigint not null default 0;
