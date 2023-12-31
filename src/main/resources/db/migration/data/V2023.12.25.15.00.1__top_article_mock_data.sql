insert into top_article (file_api, title, summary, article_id, modified_date, created_date)
select picture_url, title, 'summary of article(this is test summary)', article_id, NOW(), NOW()
from article
where invisible = false
limit 1;
