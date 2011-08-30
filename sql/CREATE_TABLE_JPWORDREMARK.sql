create table JPWORDREMARK
(
	id number primary key,
	referencedwordid number,
	remark varchar(1000),
	FOREIGN KEY (referencedwordid) REFERENCES JPWORD(id)
)tablespace passing;
	