INSERT INTO article(title, content) VALUES ('11', 'aa');
INSERT INTO article(title, content) VALUES ('22', 'bb');
INSERT INTO article(title, content) VALUES ('33', 'cc');

-- article table 데이터 추가
INSERT INTO article(title, content) VALUES ('당신의 인생 영화는?', '댓글 고');
INSERT INTO article(title, content) VALUES ('당신의 소울 푸드는?', '댓글 고고');
INSERT INTO article(title, content) VALUES ('당신의 취미는?', '댓글 고고고');

-- article( id = 4 )인 게시글의 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'Park', '굿 윌 헌팅');
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'Kim', '쇼생크 탈출');
INSERT INTO comment(article_id, nickname, body) VALUES (4, 'Lee', '뷰티풀 라이프');

-- article( id = 5 )인 게시글의 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES (5, 'Park', '치킨');
INSERT INTO comment(article_id, nickname, body) VALUES (5, 'Kim', '피자');
INSERT INTO comment(article_id, nickname, body) VALUES (5, 'Lee', '된장찌개');

-- article( id = 6)인 게시글의 댓글 추가
INSERT INTO comment(article_id, nickname, body) VALUES (6, 'Park', '잠');
INSERT INTO comment(article_id, nickname, body) VALUES (6, 'Kim', '여행');
INSERT INTO comment(article_id, nickname, body) VALUES (6, 'Lee', '스포츠');
