insert into photo_journal (description, volume_number, photographer_id, photo_link, modified_date, created_date)
select 'test_image', 78, (select m.member_id from member m limit 1), 'https://picsum.photos/1280/646', now(), now();
insert into photo_journal (description, volume_number, photographer_id, photo_link, modified_date, created_date)
select 'test_image', 78, (select m.member_id from member m limit 1), 'https://picsum.photos/1280/646', now(), now();
insert into photo_journal (description, volume_number, photographer_id, photo_link, modified_date, created_date)
select 'test_image', 78, (select m.member_id from member m limit 1), 'https://picsum.photos/1280/646', now(), now();
