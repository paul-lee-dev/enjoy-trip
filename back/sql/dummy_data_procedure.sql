DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$

DELIMITER $$
DROP PROCEDURE IF EXISTS loopInsert$$

CREATE PROCEDURE loopInsert()
BEGIN
    DECLARE i INT DEFAULT 1;
    WHILE i <= 100 DO
        insert into plan(user_id, title)
values (1+(i%2), concat('title', i));
        SET i = i + 1;
    END WHILE;
END$$

DELIMITER ;

call loopinsert();

-- 더미 데이터 insert 쿼리; 위의 DO 바로 밑 insert문 위치에 사용
-- plan
insert into plan(user_id, title)
values (1+(i%2), concat('title', i));

-- planlist
insert into plan_list(plan_id, attraction_id, dayNum)
values(i, 125405, 1);

-- article
insert into article(user_id, subject, content)
values(1+(i%2), concat('testSubject', i), concat('testContent', i));

-- comment
insert into comment(user_id, content, article_id)
values(1+(i%2), concat('testContent', i), i);
insert into comment(user_id, content, article_id, parent_comment_id)
values(1+(i%2), concat('testContent', i), i, (101-i));