create table tb_en_extd_word_attr(
 dict_id number not null,
 word_id number not null,
 extd_word_id number not null,
 extd_word_ptsp char(10) not null,
 extd_word_extd_attr varchar2(500) not null,
 extd_word_mean_num number not null,
 extd_word_mean varchar2(1000) not null
)tablespace passing;
alter table tb_en_extd_word_attr
add constraint pk_tb_en_extd_word_attr primary key (dict_id,word_id,extd_word_id,extd_word_ptsp,extd_word_mean_num);