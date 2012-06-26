create table tb_en_word(
 dict_id number not null,
 word_id number not null,
 word char(50) not null
)tablespace passing;
alter table tb_en_word
add constraint pk_tb_en_word primary key (dict_id,word_id);

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

create table tb_en_extd_word(
 dict_id number not null,
 word_id number not null,
 extd_word_id number not null,
 extd_word char(50) not null
)tablespace passing;
alter table tb_en_extd_word
add constraint pk_tb_en_extd_word primary key (dict_id,word_id,extd_word_id);

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

create table tb_en_extd_word_exmp(
 dict_id number not null,
 word_id number not null,
 extd_word_id number not null,
 extd_word_ptsp char(10) not null,
 extd_word_mean_num number not null,
 extd_word_exmp_num number not null,
 extd_word_exmp_extd_attr varchar2(500) not null,
 extd_word_exmp varchar2(1000) not null,
 extd_word_exmp_mean varchar2(1000) not null
)tablespace passing;
alter table tb_en_extd_word_exmp
add constraint pk_tb_en_extd_word_exmp primary key (dict_id,word_id,extd_word_id,extd_word_ptsp,extd_word_mean_num,extd_word_exmp_num);
