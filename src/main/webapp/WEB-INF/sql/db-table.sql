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

---캘린더

-- 📌 1. 새로운 캘린더 테이블 생성
CREATE TABLE calendar_events (
    id NUMBER PRIMARY KEY,                  -- 고유 일정 ID
    title VARCHAR2(255) NOT NULL,           -- 일정 제목
    start_date TIMESTAMP NOT NULL,          -- 일정 시작 날짜 및 시간
    end_date TIMESTAMP,                     -- 일정 종료 날짜 및 시간 (NULL 가능)
    category VARCHAR2(32),                  -- 일정 카테고리 (예: 업무, 개인, 공휴일 등)
    user_email VARCHAR2(64) NOT NULL        -- 일정 소유자 (사용자 이메일)
);

-- 📌 2. 일정 ID 자동 증가 시퀀스 생성
CREATE SEQUENCE calendar_events_seq 
START WITH 1 
INCREMENT BY 1 
NOCYCLE;

-- 📌 샘플 사용자 데이터 삽입
INSERT INTO USERS (id, email, pw, name, tel, jumin, position_code, dept_code, created_at, updated_at, cmp_id) 
VALUES (USERS_PK.NEXTVAL, 'user1@example.com', 'pass1234', '김철수', '010-1111-2222', '900101-1234567', 'P001', 'D001', SYSTIMESTAMP, SYSTIMESTAMP, 1);

INSERT INTO USERS (id, email, pw, name, tel, jumin, position_code, dept_code, created_at, updated_at, cmp_id) 
VALUES (USERS_PK.NEXTVAL, 'user2@example.com', 'pass5678', '홍길동', '010-3333-4444', '910202-2345678', 'P002', 'D002', SYSTIMESTAMP, SYSTIMESTAMP, 1);

INSERT INTO USERS (id, email, pw, name, tel, jumin, position_code, dept_code, created_at, updated_at, cmp_id) 
VALUES (USERS_PK.NEXTVAL, 'user3@example.com', 'pass9876', '이영희', '010-5555-6666', '920303-3456789', 'P003', 'D003', SYSTIMESTAMP, SYSTIMESTAMP, 2);


-------ID가 1번인 김철수의 calendar_events 데이터 3개를 추가할 수 있어요~

INSERT INTO calendar_events (id, title, start_date, end_date, category, user_email)
VALUES (calendar_events_seq.NEXTVAL, '팀 회의', TO_TIMESTAMP('2025-02-15 10:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2025-02-15 11:00:00', 'YYYY-MM-DD HH24:MI:SS'), '회의', 'user1@example.com');

INSERT INTO calendar_events (id, title, start_date, end_date, category, user_email)
VALUES (calendar_events_seq.NEXTVAL, '프로젝트 마감일', TO_TIMESTAMP('2025-02-20 23:59:59', 'YYYY-MM-DD HH24:MI:SS'), NULL, '마감일', 'user1@example.com');

INSERT INTO calendar_events (id, title, start_date, end_date, category, user_email)
VALUES (calendar_events_seq.NEXTVAL, '외부 미팅', TO_TIMESTAMP('2025-02-25 14:00:00', 'YYYY-MM-DD HH24:MI:SS'), TO_TIMESTAMP('2025-02-25 16:00:00', 'YYYY-MM-DD HH24:MI:SS'), '외부 일정', 'user1@example.com');





