-- test search enWordInfo(left join)-------------------
SELECT enword.word ,
  attr.extd_attr ,
  attr.mean ,
  enword.dict_id ,
  enword.word_id ,
  attr.part_of_speech ,
  attr.meaning_num ,
  exmp.example_num ,
  exmp.example_extd_attr ,
  exmp.en_exmp ,
  exmp.exmp_meaning
FROM tb_en_word enword
LEFT JOIN tb_en_word_attr attr
ON enword.word_id  = attr.word_id
AND enword.dict_id = attr.dict_id
LEFT JOIN tb_en_word_exmp exmp
ON attr.word_id         = exmp.word_id
AND attr.dict_id        = exmp.dict_id
AND attr.meaning_num    = exmp.meaning_num
AND attr.part_of_speech = exmp.part_of_speech
WHERE enword.word       like 'test%'


-- test search enWordInfo(inner join)-------------------
SELECT enword.word ,
  attr.extd_attr ,
  attr.mean ,
  exmp.dict_id ,
  exmp.word_id ,
  exmp.part_of_speech ,
  exmp.meaning_num ,
  exmp.example_num ,
  exmp.example_extd_attr ,
  exmp.en_exmp ,
  Exmp.Exmp_Meaning
From Tb_En_Word Enword,
  tb_en_word_attr attr,
  Tb_En_Word_Exmp Exmp
Where Enword.Word_Id  = Attr.Word_Id
AND enword.dict_id = attr.dict_id
AND attr.word_id         = exmp.word_id
AND attr.dict_id        = exmp.dict_id
AND attr.meaning_num    = exmp.meaning_num
And Attr.Part_Of_Speech = Exmp.Part_Of_Speech
and enword.word       like 'just%'

--test search enExtdWordInfo--------------------
SELECT ewd.extd_word ,
  ewda.extd_word_extd_attr ,
  ewda.extd_word_mean ,
  ewde.dict_id ,
  ewde.word_id ,
  ewde.extd_word_id ,
  ewde.extd_word_ptsp ,
  ewde.extd_word_mean_num ,
  ewde.extd_word_exmp_num ,
  ewde.extd_word_exmp_extd_attr ,
  ewde.extd_word_exmp ,
  Ewde.Extd_Word_Exmp_Mean
From Tb_En_Word Wd,
  Tb_En_Extd_Word Ewd,
  Tb_En_Extd_Word_Attr Ewda,
  Tb_En_Extd_Word_Exmp ewde
WHERE wd.dict_id            = ewd.dict_id
AND wd.word_id              = ewd.word_id
AND ewd.dict_id             = ewda.dict_id
AND ewd.word_id             = ewda.word_id
AND ewd.extd_word_id        = ewda.extd_word_id
AND ewda.dict_id            = ewde.dict_id
AND ewda.word_id            = ewde.word_id
AND ewda.extd_word_id       = ewde.extd_word_id
AND ewda.extd_word_ptsp     = ewde.extd_word_ptsp
AND ewda.extd_word_mean_num = ewde.extd_word_mean_num
AND wd.word                 = 'test'
