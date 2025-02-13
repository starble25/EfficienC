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
	insert into users
	values (
	    USERS_PK.nextval,
	    '123@aaa.com',
	    '123',
	    '홍길동',
	    '010-1234-5678',
	    '123456-7777777',
	    'POS',
	    'DEPT',
	    systimestamp,
	    systimestamp,
	    '101'
	);
	
	insert into users
	values (
	    USERS_PK.nextval,
	    '456@aaa.com',
	    '456',
	    '김이름',
	    '010-1111-2222',
	    '123456-7777777',
	    'POS-2',
	    'DEPT-2',
	    systimestamp,
	    systimestamp,
	    '102'
	);
*/

-- 주소록
CREATE TABLE ADDRESS (
	id NUMBER PRIMARY KEY,
    my_id NUMBER REFERENCES USERS(id) ON DELETE CASCADE,
    add_id NUMBER REFERENCES USERS(id) ON DELETE CASCADE,
	favorite VARCHAR(1)
);

CREATE SEQUENCE ADDRESS_PK
START WITH 1
INCREMENT BY 1
NOCYCLE;


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





