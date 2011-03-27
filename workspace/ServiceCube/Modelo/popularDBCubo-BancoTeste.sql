INSERT INTO cubo  values (900,'Vendas','eingrid005.unigranrio.br','jdbc:postgresql://eingrid005.unigranrio.br:5432/bancoteste','kim','kim','org.postgresql.Driver',30000);

INSERT INTO tabela  values (1,'t');
INSERT INTO tabela  values (2,'teste');
INSERT INTO tabela  values (3,'tteste');


INSERT INTO fato VALUES (1,900);

INSERT INTO dimensao VALUES (2);
INSERT INTO dimensao VALUES (3);

--atributo: idatributo,nome,tipo,tamanho,precisao,idtabela,chaveprimaria


INSERT INTO atributo VALUES (1,'id','int4',10,0,1,true);
INSERT INTO atributo VALUES (2,'numero','int4',10,0,1,false);
INSERT INTO atributo VALUES (3,'descricao','varchar',30,0,1,false);


INSERT INTO atributo VALUES (4,'id','int4',10,0,2,true);
INSERT INTO atributo VALUES (5,'numero','int4',10,0,2,false);
INSERT INTO atributo VALUES (6,'texto','varchar',100,0,2,false);

INSERT INTO atributo VALUES (7,'id','int4',10,0,3,true);
INSERT INTO atributo VALUES (8,'numero','int4',10,0,3,false);
INSERT INTO atributo VALUES (9,'descricao','varchar',30,0,3,false);

INSERT INTO chaveestrangeira VALUES (1,1,2);
INSERT INTO chaveestrangeira VALUES (2,1,3);

INSERT INTO ligacao VALUES (1,1,1,4);
INSERT INTO ligacao VALUES (2,2,1,7);

INSERT INTO cubo  values (1000,'Vendas 2','eingrid005.unigranrio.br','jdbc:postgresql://eingrid005.unigranrio.br:5432/bancoteste','kim','kim','org.postgresql.Driver',30000);

INSERT INTO tabela  values (4,'t');
INSERT INTO tabela  values (5,'teste');
INSERT INTO tabela  values (6,'tteste');


INSERT INTO fato VALUES (4,1000);

INSERT INTO dimensao VALUES (5);
INSERT INTO dimensao VALUES (6);

--atributo: idatributo,nome,tipo,tamanho,precisao,idtabela,chaveprimaria


INSERT INTO atributo VALUES (10,'id','int4',10,0,4,true);
INSERT INTO atributo VALUES (11,'numero','int4',10,0,4,false);
INSERT INTO atributo VALUES (12,'descricao','varchar',30,0,4,false);


INSERT INTO atributo VALUES (13,'id','int4',10,0,5,true);
INSERT INTO atributo VALUES (14,'numero','int4',10,0,5,false);
INSERT INTO atributo VALUES (15,'texto','varchar',100,0,5,false);

INSERT INTO atributo VALUES (16,'id','int4',10,0,6,true);
INSERT INTO atributo VALUES (17,'numero','int4',10,0,6,false);
INSERT INTO atributo VALUES (18,'descricao','varchar',30,0,6,false);

INSERT INTO chaveestrangeira VALUES (3,4,5);
INSERT INTO chaveestrangeira VALUES (4,4,6);

INSERT INTO ligacao VALUES (3,3,10,13);
INSERT INTO ligacao VALUES (4,4,10,16);



--CUBO Livros em eingrid001.unigranrio.br

INSERT INTO cubo VALUES (30,'Livros','eingrid001.unigranrio.br','jdbc:postgresql://eingrid001.unigranrio.br:5432/ogsadai','ogsadai','ogsadai','org.postgresql.Driver',30000);

INSERT INTO tabela VALUES (7,'littleblackbook');
INSERT INTO tabela VALUES (8,'mytable');

INSERT INTO fato VALUES (7,30);
INSERT INTO atributo VALUES (19,'id','int4',10,0,7,true);
INSERT INTO atributo VALUES (20,'name','varchar',64,0,7,false);
INSERT INTO atributo VALUES (21,'address','varchar',128,0,7,false);
INSERT INTO atributo VALUES (22,'phone','varchar',20,0,7,false);


INSERT INTO dimensao VALUES (8);
INSERT INTO atributo VALUES (23,'id','int4',10,0,8,true);
INSERT INTO atributo VALUES (24,'phone','varchar',20,0,8,false);


INSERT INTO chaveestrangeira VALUES (5,7,8);
INSERT INTO ligacao VALUES (5,5,19,23);

