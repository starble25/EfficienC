--
-- DB에 값 넣고 DB커밋하기
--

-- 회원가입
CREATE TABLE USERS (
	id NUMBER PRIMARY KEY,
	email VARCHAR2(64) NOT NULL UNIQUE,
	pw VARCHAR2(64) NOT NULL,
	name VARCHAR2(32),
	tel VARCHAR2(32),
	jumin VARCHAR2(32),
	position_code VARCHAR2(32),
	dept_code VARCHAR2(32),
	created_at TIMESTAMP,
	updated_at TIMESTAMP,
	cmp_id NUMBER
);

CREATE SEQUENCE USERS_PK
START WITH 1
INCREMENT BY 1
NOCYCLE;

/*
	insert into users ( users_id, email, pw )
	values ( USERS_PK.nextval, '123@aaa.com', '11' );
*/

/*
-- USERS TABLE DB
BEGIN
    INSERT INTO USERS VALUES (
        USERS_PK.nextval, '111@aaa.com', '111', '장현', '010-1111-1234', 
        '111111-1111111', '사장', '개발팀', systimestamp, systimestamp, '100'
    );
    INSERT INTO USERS VALUES (
        USERS_PK.nextval, '222@bbb.com', '222', '김혜민', '010-2222-1234', 
        '111111-2222222', '팀장', '개발팀', systimestamp, systimestamp, '100'
    );
    INSERT INTO USERS VALUES (
        USERS_PK.nextval, '333@ccc.com', '333', '곽한규', '010-3333-1234', 
        '111111-3333333', '팀장', '개발팀', systimestamp, systimestamp, '100'
    );
    INSERT INTO USERS VALUES (
        USERS_PK.nextval, '444@ddd.com', '444', '김성진', '010-4444-1234', 
        '111111-4444444', '팀장', '개발팀', systimestamp, systimestamp, '100'
    );
    
    INSERT INTO USERS VALUES (
        USERS_PK.nextval, '555@eee.com', '555', '변준현', '010-0123-1111', 
        '222222-5555555', '사원', '개발2팀', systimestamp, systimestamp, '200'
    );
    INSERT INTO USERS VALUES (
        USERS_PK.nextval, '666@fff.com', '666', '김도현', '010-0123-2222', 
        '222222-5555555', '사원', '개발2팀', systimestamp, systimestamp, '200'
    );
    INSERT INTO USERS VALUES (
        USERS_PK.nextval, '777@ggg.com', '777', '박수민', '010-0123-3333', 
        '222222-5555555', '인턴', '개발2팀', systimestamp, systimestamp, '201'
    );
    INSERT INTO USERS VALUES (
        USERS_PK.nextval, '888@hhh.com', '888', '박철중', '010-0123-4444', 
        '222222-5555555', '인턴', '개발2팀', systimestamp, systimestamp, '202'
    );
    INSERT INTO USERS VALUES (
        USERS_PK.nextval, '999@ggg.com', '999', '황종원', '010-0123-5555', 
        '222222-5555555', '인턴', '개발2팀', systimestamp, systimestamp, '203'
    );
END;
/
*/

-- 주소록
CREATE TABLE ADDRESS (
	id NUMBER PRIMARY KEY,
    my_id NUMBER,
    add_id NUMBER,
	favorite VARCHAR(1)
);

CREATE SEQUENCE ADDRESS_PK
START WITH 1
INCREMENT BY 1
NOCYCLE;

/*
-- ADDRESS TABLE DB
BEGIN
    INSERT INTO address (id, my_id, add_id, favorite)
    VALUES (ADDRESS_PK.NEXTVAL, 1, (select id from users where cmp_id='201'), 'F');
    
    INSERT INTO address (id, my_id, add_id, favorite)
    VALUES (ADDRESS_PK.NEXTVAL, 1, (select id from users where cmp_id='202'), 'F');
    
    INSERT INTO address (id, my_id, add_id, favorite)
    VALUES (ADDRESS_PK.NEXTVAL, 1, (select id from users where cmp_id='203'), 'F');
END;
/
*/

-- 공지사항
CREATE TABLE NOTICE (
    id NUMBER PRIMARY KEY,
    title VARCHAR2(255),
    content VARCHAR2(255),
    author VARCHAR(32),
    reg_date VARCHAR2(10)
);

CREATE SEQUENCE NOTICE_PK
START WITH 1
INCREMENT BY 1
NOCYCLE;

/*
INSERT INTO NOTICE
VALUES (
    notice_pk.nextval, 
    '첫 공지사항', 
    '내용123123', 
    '김작성', 
    '2025-02-11'
);

INSERT INTO NOTICE
VALUES (
    notice_pk.nextval, 
    '오늘의 공지사항', 
    '오늘의 공지사항입니다~~ 12421412397213', 
    '이공지', 
    '2025-02-12'
);
*/