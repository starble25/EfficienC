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

