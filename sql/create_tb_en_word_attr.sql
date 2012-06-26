create table tb_en_word_attr(
 dict_id number not null,
 word_id number not null,
 part_of_speech char(10) not null,
 extd_attr varchar2(500) not null,
 meaning_num number not null,
 mean varchar2(1000) not null
)tablespace passing;
alter table tb_en_word_attr
add constraint pk_tb_en_word_attr primary key (dict_id,word_id,part_of_speech,meaning_num);