select * from cubo c,fato f,tabela t, atributo a WHERE c.idcubo=f.idcubo and t.idtabela=f.idfato and a.idtabela=t.idtabela;
SELECT * FROM ((cubo As c INNER JOIN fato AS f ON c.idcubo = f.idcubo) INNER JOIN tabela t ON f.idfato = t.idtabela) INNER JOIN  atributo AS a ON t.idtabela = a.idtabela;

--Pegar atributos das dimensoes e os atributos que fazem parte das ligações
SELECT a.idatributo,idligacao,idatributoprimario,idatributoestrangeiro FROM (((fato AS f INNER JOIN chaveestrangeira AS ch ON f.idfato = ch.idfato) INNER JOIN tabela AS t ON ch.iddimensao = t.idtabela) INNER JOIN atributo AS a ON t.idtabela = a.idtabela) LEFT OUTER JOIN ligacao AS l ON a.idatributo = l.idatributoprimario;