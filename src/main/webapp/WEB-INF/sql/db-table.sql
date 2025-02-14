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


--할 일 목록
CREATE TABLE tasks (
    id NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, --GENERATED ALWAYS AS IDENTITY 자동증가되는 값 가짐을 의미
    title VARCHAR2(255) NOT NULL,
    description CLOB, --CLOB : 대용량의 텍스트 데이터를 저장
    status VARCHAR2(20) DEFAULT 'TODO' CHECK (status IN ('TODO', 'IN_PROGRESS', 'DONE')) --기본값:TODO, check제약 조건때문에 값은 'TODO', 'IN_PROGRESS', 'DONE' 중 하나여야 함. 
);

CREATE SEQUENCE tasks_PK
START WITH 1
INCREMENT BY 1
NOCYCLE;


--게시판 보드
CREATE TABLE board (
    ID         NUMBER GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    TITLE      VARCHAR2(255) NOT NULL,
    CONTENT    CLOB NOT NULL,
    FILE_NAME  VARCHAR2(255),
    CREATED_AT DATE DEFAULT SYSDATE
);

CREATE SEQUENCE board_PK
START WITH 1
INCREMENT BY 1
NOCYCLE;


