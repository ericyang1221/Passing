create tablespace passing 
datafile 'C:\app\weishijie\product\11.2.0\oradata\passing\passing.dbf'
size 50m AutoExtend On Next 10m Maxsize 2048m
extent management local
segment space management auto;
alter user passing quota unlimited on passing;
alter user passing default tablespace passing;