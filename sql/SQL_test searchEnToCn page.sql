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
WHERE enword.word       = 'test'