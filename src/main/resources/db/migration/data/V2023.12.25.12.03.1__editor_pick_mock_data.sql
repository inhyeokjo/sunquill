INSERT INTO editor_pick (title, category, article_id, modified_date, created_date)
select title, category, article_id, NOW(), NOW()
from article
where category = 'SHORT_ARTICLES'
limit 1;

INSERT INTO editor_pick (title, category, article_id, modified_date, created_date)
select title, category, article_id, NOW(), NOW()
from article
where category = 'ARTS_CULTURE'
limit 1;

INSERT INTO editor_pick (title, category, article_id, modified_date, created_date)
select title, category, article_id, NOW(), NOW()
from article
where category = 'OPINION'
limit 1;

INSERT INTO editor_pick (title, category, article_id, modified_date, created_date)
select title, category, article_id, NOW(), NOW()
from article
where category = 'SNU_SOCIETY'
limit 1;

INSERT INTO editor_pick (title, category, article_id, modified_date, created_date)
select title, category, article_id, NOW(), NOW()
from article
where category = 'FEATURES'
limit 1;

