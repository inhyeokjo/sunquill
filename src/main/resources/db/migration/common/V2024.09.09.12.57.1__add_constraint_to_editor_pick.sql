alter table editor_pick
    drop key if exists category_unique_key,
    add constraint category_unique_key unique (category)
