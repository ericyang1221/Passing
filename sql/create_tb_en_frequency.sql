create table TB_EN_FREQUENCY
(word_id number(8) primary key,
word char(50) not null,
frequency number(10) not null)
tablespace PASSING;