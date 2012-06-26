create table tb_en_extd_word(
 dict_id number not null,
 word_id number not null,
 extd_word_id number not null,
 extd_word char(50) not null
)tablespace passing;
alter table tb_en_extd_word
add constraint pk_tb_en_extd_word primary key (dict_id,word_id,extd_word_id);