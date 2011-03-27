--CUBO Vendas em eingrid005.unigranrio.br
/*


*/
INSERT INTO cubo  values (5,'Vendas','eingrid005.unigranrio.br','jdbc:postgresql://eingrid005.unigranrio.br:5432/vendas','kim','kim','org.postgresql.Driver',30000);


INSERT INTO tabela VALUES (10,'vendas');
INSERT INTO tabela VALUES (11,'tempo');
INSERT INTO tabela VALUES (12,'produto');
INSERT INTO tabela VALUES (13,'loja');

--atributo: idatributo,nome,tipo,tamanho,precisao,idtabela,chaveprimaria
--Vendas
INSERT INTO fato VALUES (10,5);
INSERT INTO atributo VALUES (31,'id_vendas','int4',10,0,10,true);
INSERT INTO atributo VALUES (32,'quantidade','int4',10,0,10,false);
INSERT INTO atributo VALUES (33,'preco_unidade','double',10,3,10,false);
INSERT INTO atributo VALUES (34,'preco_total','double',10,3,10,false);

--Tempo
INSERT INTO dimensao VALUES (11);
INSERT INTO atributo VALUES (41,'id_tempo','int4',10,0,11,true);
INSERT INTO atributo VALUES (42,'dia','int4',10,0,11,false);
INSERT INTO atributo VALUES (43,'mes','int4',10,0,11,false);
INSERT INTO atributo VALUES (44,'ano','int4',10,0,11,false);
INSERT INTO atributo VALUES (45,'data','date',20,0,11,false);
INSERT INTO atributo VALUES (46,'id_vendas_dimensao','int4',10,0,11,false); --Chave Estrangeira para fato vendas

INSERT INTO chaveestrangeira VALUES (6,10,11);
INSERT INTO ligacao VALUES (6,6,31,46);


--Produto
INSERT INTO dimensao VALUES (12);
INSERT INTO atributo VALUES (51,'id_produto','int4',10,0,12,true);
INSERT INTO atributo VALUES (52,'descricao','varchar',300,0,12,false);
INSERT INTO atributo VALUES (53,'marca','varchar',100,0,12,false);
INSERT INTO atributo VALUES (54,'categoria','varchar',150,0,12,false);
INSERT INTO atributo VALUES (55,'id_vendas_dimensao','int4',10,0,12,false); --Chave Estrangeira para fato vendas

INSERT INTO chaveestrangeira VALUES (7,10,12);
INSERT INTO ligacao VALUES (7,7,31,55);


--Loja
INSERT INTO dimensao VALUES (13);
INSERT INTO atributo VALUES (71,'id_loja','int4',10,0,13,true);
INSERT INTO atributo VALUES (72,'loja_nome','varchar',150,0,13,false);
INSERT INTO atributo VALUES (73,'endereco','varchar',200,0,13,false);
INSERT INTO atributo VALUES (74,'cidade','varchar',150,0,13,false);
INSERT INTO atributo VALUES (75,'estado','varchar',100,0,13,false);
INSERT INTO atributo VALUES (76,'id_vendas_dimensao','int4',10,0,13,false); --Chave Estrangeira para fato vendas

INSERT INTO chaveestrangeira VALUES (8,10,13);
INSERT INTO ligacao VALUES (8,8,31,76);

