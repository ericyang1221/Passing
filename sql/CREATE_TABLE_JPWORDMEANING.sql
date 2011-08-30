create table JPWORDMEANING
(
	id number primary key,
	referencedwordid number,
	meaning varchar(500) not null,
	partofspeech varchar(20),
	FOREIGN KEY (referencedwordid) REFERENCES JPWORD(id)
)tablespace passing;