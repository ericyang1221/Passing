create table JPWORDEXAMPLE
(
	id number primary key,
	referencedmeaningid number,
	example varchar(1000),
	examplemeaning varchar(1000),
	FOREIGN KEY (referencedmeaningid) REFERENCES JPWORDMEANING(id)
)tablespace passing;