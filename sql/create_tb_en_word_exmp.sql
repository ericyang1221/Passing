create table tb_en_word_exmp(
 dict_id number not null,
 word_id number not null,
 part_of_speech char(10) not null,
 meaning_num number not null,
 example_num number not null,
 example_extd_attr varchar2(500) not null,
 en_exmp varchar2(1000) not null,
 exmp_meaning varchar2(1000) not null
)tablespace passing;
alter table tb_en_word_exmp
add constraint pk_tb_en_word_exmp primary key (dict_id,word_id,part_of_speech,meaning_num,example_num);