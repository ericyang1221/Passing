create table tb_en_word(
 dict_id number not null,
 word_id number not null,
 word char(50) not null
)tablespace passing;
alter table tb_en_word
add constraint pk_tb_en_word primary key (dict_id,word_id);