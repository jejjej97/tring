SET SESSION FOREIGN_KEY_CHECKS=0;

/* Drop Tables */

DROP TABLE IF EXISTS tbl_alarm;
DROP TABLE IF EXISTS tbl_company_approval;
DROP TABLE IF EXISTS tbl_department_authority;
DROP TABLE IF EXISTS tbl_department_folder_data;
DROP TABLE IF EXISTS tbl_department_folder;
DROP TABLE IF EXISTS tbl_company_department;
DROP TABLE IF EXISTS tbl_meeting_authority;
DROP TABLE IF EXISTS tbl_memo_authority;
DROP TABLE IF EXISTS tbl_user_adress;
DROP TABLE IF EXISTS tbl_user_folder_data;
DROP TABLE IF EXISTS tbl_user_folder;
DROP TABLE IF EXISTS tbl_user;
DROP TABLE IF EXISTS tbl_company;
DROP TABLE IF EXISTS tbl_meeting_file;
DROP TABLE IF EXISTS tbl_meeting;
DROP TABLE IF EXISTS tbl_memo;
DROP TABLE IF EXISTS tbl_product;
DROP TABLE IF EXISTS tbl_product_use_history;




/* Create Tables */

CREATE TABLE tbl_alarm
(
	user_email varchar(125),
	alarm_content text,
	alarm_regdate timestamp,
	-- meeting_uid
	-- memo_uid
	alarm_data_uid varchar(50) COMMENT 'meeting_uid
memo_uid',
	-- 0 - 미확인
	-- 1 - 확인
	alarm_check int COMMENT '0 - 미확인
1 - 확인',
	-- 0 - 오늘 회의
	-- 1 - 회의 공유
	-- 2 - 메모 공유
	alarm_type int COMMENT '0 - 오늘 회의
1 - 회의 공유
2 - 메모 공유'
);


CREATE TABLE tbl_company
(
	company_code varchar(15) NOT NULL,
	company_name varchar(125),
	company_email varchar(125),
	company_phone varchar(125),
	company_fax varchar(125),
	company_logo varchar(125),
	PRIMARY KEY (company_code)
);


CREATE TABLE tbl_company_approval
(
	apv_user_uid varchar(50) NOT NULL,
	company_code varchar(15) NOT NULL,
	apv_user_email varchar(125) NOT NULL,
	apv_user_password varchar(125),
	apv_department_code varchar(50),
	apv_user_position varchar(125),
	apv_user_name varchar(125),
	apv_user_phone varchar(125),
	apv_user_regdate timestamp,
	-- 0 - 대기
	-- 1 - 승인
	-- 2 - 거절
	apv_user_status int COMMENT '0 - 대기
1 - 승인
2 - 거절',
	-- 요청에 따른 상태 변화시간 업데이트
	apv_status_regdate timestamp COMMENT '요청에 따른 상태 변화시간 업데이트',
	PRIMARY KEY (apv_user_uid)
);


CREATE TABLE tbl_company_department
(
	department_code varchar(50) NOT NULL,
	company_code varchar(15) NOT NULL,
	department_name varchar(125),
	department_mail varchar(125),
	department_phone varchar(125),
	PRIMARY KEY (department_code)
);


CREATE TABLE tbl_department_authority
(
	department_code varchar(50) NOT NULL,
	user_uid varchar(50) NOT NULL
);


CREATE TABLE tbl_department_folder
(
	department_folder_uid varchar(50) NOT NULL,
	department_code varchar(50) NOT NULL,
	department_folder_name varchar(125),
	department_folder_highrank varchar(50),
	department_folder_level int,
	department_folder_regdate timestamp,
	department_folder_del int,
	department_folder_del_regdate timestamp,
	-- 0 - 생성 폴더
	-- 1 - 기본 폴더(회사 부서 추가시 자동 생성)
	department_folder_type int COMMENT '0 - 생성 폴더
1 - 기본 폴더(회사 부서 추가시 자동 생성)',
	PRIMARY KEY (department_folder_uid)
);


CREATE TABLE tbl_department_folder_data
(
	department_folder_uid varchar(50) NOT NULL,
	meeting_uid varchar(50)
);


CREATE TABLE tbl_meeting
(
	meeting_uid varchar(50) NOT NULL,
	meeting_name text,
	meeting_place text,
	meeting_startDate timestamp,
	meeting_endDate timestamp,
	meeting_memo text,
	meeting_content text,
	meeting_before varchar(50),
	-- 0 - 자동저장
	-- 1 - 임시저장
	-- 2 - 저장
	save_status int COMMENT '0 - 자동저장
1 - 임시저장
2 - 저장',
	department_code varchar(50),
	PRIMARY KEY (meeting_uid)
);


CREATE TABLE tbl_meeting_authority
(
	meeting_uid varchar(50) NOT NULL,
	user_uid varchar(50) NOT NULL,
	user_email varchar(125),
	-- 0 - 생성자
	-- 1 - 참석자
	-- 2 - 공유자
	meeting_authority  int COMMENT '0 - 생성자
1 - 참석자
2 - 공유자'
);


CREATE TABLE tbl_meeting_file
(
	meeting_uid varchar(50) NOT NULL,
	original_fileName text,
	save_fileName text,
	file_size int
);


CREATE TABLE tbl_memo
(
	memo_uid varchar(50) NOT NULL,
	memo_title text,
	memo_content text,
	PRIMARY KEY (memo_uid)
);


CREATE TABLE tbl_memo_authority
(
	user_uid varchar(50) NOT NULL,
	memo_uid varchar(50) NOT NULL,
	user_email varchar(125),
	-- 0 - 생성자
	-- 1 - 공유자
	memo_authority  int COMMENT '0 - 생성자
1 - 공유자'
);


CREATE TABLE tbl_product
(
	product_code varchar(50) NOT NULL,
	product_name text,
	product_introduce text,
	product_price int,
	product_img text,
	-- 0 - 파일 용량
	-- 1 - 회의 분석
	-- 2 - 리포트 양식
	product_type int COMMENT '0 - 파일 용량
1 - 회의 분석
2 - 리포트 양식',
	PRIMARY KEY (product_code)
);


CREATE TABLE tbl_product_use_history
(
	user_email varchar(125),
	company_code varchar(15),
	pruduct_code varchar(50),
	product_name text,
	product_price int,
	-- 0 - 사용 중
	-- 1 - 기간 만료
	product_status int COMMENT '0 - 사용 중
1 - 기간 만료',
	buy_date timestamp,
	use_period timestamp
);


CREATE TABLE tbl_user
(
	user_uid varchar(50) NOT NULL,
	company_code varchar(15),
	user_email varchar(125),
	user_password varchar(125),
	department_code varchar(50),
	user_position varchar(125),
	user_name varchar(125),
	user_phone varchar(125),
	user_regdate timestamp,
	-- 1 - 마스터
	-- 2 - 사원
	-- 3 - 개인
	user_authority int COMMENT '1 - 마스터
2 - 사원
3 - 개인',
	user_logdate timestamp,
	-- Google
	-- Kakao
	-- Naver
	-- Company
	user_login_type varchar(15) COMMENT 'Google
Kakao
Naver
Company',
	-- 0 - 기본
	-- 1 - icon1
	-- 2 - icon2
	-- ...
	user_icon int default 0 COMMENT '0 - 기본
1 - icon1
2 - icon2
...',
	PRIMARY KEY (user_uid)
);


CREATE TABLE tbl_user_adress
(
	user_uid varchar(50) NOT NULL,
	adress_user_uid varchar(50),
	adress_user_email varchar(125),
	adress_user_name varchar(125),
	adress_user_phone varchar(125),
	adress_user_company varchar(125),
	adress_user_department varchar(125),
	adress_user_position varchar(125)
);


CREATE TABLE tbl_user_folder
(
	user_folder_uid varchar(50) NOT NULL,
	user_uid varchar(50) NOT NULL,
	user_folder_name varchar(125),
	user_folder_highrank varchar(50),
	user_folder_level int,
	user_folder_regdate timestamp,
	-- 0 - 생성 폴더
	-- 1 - 기본 폴더(데이터 자동 저장 폴더)
	-- 2 - 공유 폴더(공유 데이터 자동 저장 폴더)
	-- 
	user_folder_type int COMMENT '0 - 생성 폴더
1 - 기본 폴더(데이터 자동 저장 폴더)
2 - 공유 폴더(공유 데이터 자동 저장 폴더)
',
	-- 회의록 - 0
	-- 메모 - 1
	user_folder_dataType int COMMENT '회의록 - 0
메모 - 1',
	user_folder_del int,
	user_folder_del_regdate timestamp,
	PRIMARY KEY (user_folder_uid)
);


CREATE TABLE tbl_user_folder_data
(
	user_folder_uid varchar(50) NOT NULL,
	data_uid varchar(50)
);



/* Create Foreign Keys */

ALTER TABLE tbl_company_approval
	ADD FOREIGN KEY (company_code)
	REFERENCES tbl_company (company_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_company_department
	ADD FOREIGN KEY (company_code)
	REFERENCES tbl_company (company_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_user
	ADD FOREIGN KEY (company_code)
	REFERENCES tbl_company (company_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_department_authority
	ADD FOREIGN KEY (department_code)
	REFERENCES tbl_company_department (department_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_department_folder
	ADD FOREIGN KEY (department_code)
	REFERENCES tbl_company_department (department_code)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_department_folder_data
	ADD FOREIGN KEY (department_folder_uid)
	REFERENCES tbl_department_folder (department_folder_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_meeting_authority
	ADD FOREIGN KEY (meeting_uid)
	REFERENCES tbl_meeting (meeting_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_meeting_file
	ADD FOREIGN KEY (meeting_uid)
	REFERENCES tbl_meeting (meeting_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_memo_authority
	ADD FOREIGN KEY (memo_uid)
	REFERENCES tbl_memo (memo_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_department_authority
	ADD FOREIGN KEY (user_uid)
	REFERENCES tbl_user (user_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_meeting_authority
	ADD FOREIGN KEY (user_uid)
	REFERENCES tbl_user (user_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_memo_authority
	ADD FOREIGN KEY (user_uid)
	REFERENCES tbl_user (user_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_user_adress
	ADD FOREIGN KEY (user_uid)
	REFERENCES tbl_user (user_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_user_folder
	ADD FOREIGN KEY (user_uid)
	REFERENCES tbl_user (user_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;


ALTER TABLE tbl_user_folder_data
	ADD FOREIGN KEY (user_folder_uid)
	REFERENCES tbl_user_folder (user_folder_uid)
	ON UPDATE RESTRICT
	ON DELETE RESTRICT
;



