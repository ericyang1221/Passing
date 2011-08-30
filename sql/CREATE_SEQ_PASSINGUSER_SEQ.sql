create sequence PASSINGUSER_SEQ;

create or replace trigger BIFER_PASSINGUSER_ID_PK
before insert
on PASSINGUSER
for each row
begin
select PASSINGUSER_SEQ.nextval into :new.id from dual;
end;
/
