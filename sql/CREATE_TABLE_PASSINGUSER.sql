create table PASSINGUSER
(
	id number primary key,
	username varchar(20) not null unique,
	password varchar(20) not null,
	nickname varchar(20),
	emailaddress varchar(50) not null unique,
	sex number not null,
	birthday date,
	fgtpwdproblem varchar(50),
	fgtpwdanswer varchar(50),
	country varchar(50),
	city varchar(50),
	university varchar(50),
	technicalschool varchar(50),
	highschool varchar(50),
	middleschool varchar(50),
	elementaryschool varchar(50),
	company varchar(50),
	qq number unique,
	msn varchar(50) unique,
	telphone number unique,
	personalweb varchar(50)
)tablespace passing;
