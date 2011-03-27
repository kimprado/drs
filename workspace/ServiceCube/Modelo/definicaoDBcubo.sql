CREATE TABLE cubo
(
  idcubo integer NOT NULL,
  nome character varying(50),
  servidor character varying(60),
  conexao_url character varying(200),
  conexao_usuario character varying(50),
  conexao_senha character varying(50),
  conexao_driver character varying(50),
  tempo_refresh integer,
  CONSTRAINT pk_cubo_idcubo PRIMARY KEY (idcubo)
);
--ALTER TABLE cubo OWNER TO globus;


CREATE TABLE tabela
(
  idtabela integer NOT NULL,
  nome character varying(50),
  CONSTRAINT pk_tabela_idtabela PRIMARY KEY (idtabela)
);
--ALTER TABLE tabela OWNER TO globus;


CREATE TABLE fato
(
  idfato integer NOT NULL,
  idcubo integer NOT NULL,
  CONSTRAINT pk_fato_idfato PRIMARY KEY (idfato),
  CONSTRAINT fk_fato_idfato FOREIGN KEY (idfato)
      REFERENCES tabela (idtabela) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_fato_idcubo FOREIGN KEY (idcubo)
      REFERENCES cubo (idcubo) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
--ALTER TABLE fato OWNER TO globus;


CREATE TABLE dimensao
(
  iddimensao integer NOT NULL,
  CONSTRAINT pk_dimensao_idimensao_idtabela PRIMARY KEY (iddimensao),
  CONSTRAINT fk_dimensao_idtabela FOREIGN KEY (iddimensao)
      REFERENCES tabela (idtabela) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
--ALTER TABLE dimensao OWNER TO globus;



CREATE TABLE chaveestrangeira
(
  idchaveestrangeira integer NOT NULL,
  idfato integer NOT NULL,
  iddimensao integer NOT NULL,
  CONSTRAINT pk_chaveestrangeira_idchaveestrangeira PRIMARY KEY (idchaveestrangeira),
  CONSTRAINT fk_chaveestrangeira_dimensao FOREIGN KEY (iddimensao)
      REFERENCES dimensao (iddimensao) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_chaveestrangeira_fato FOREIGN KEY (idfato)
      REFERENCES fato (idfato) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
--ALTER TABLE chaveestrangeira OWNER TO globus;


CREATE TABLE atributo
(
  idatributo integer NOT NULL,
  nome character varying(50),
  tipo character varying(50),
  tamanho smallint,
  precisao smallint,
  idtabela integer NOT NULL,
  chaveprimaria boolean NOT NULL,
  CONSTRAINT pk_atributo_idatributo PRIMARY KEY (idatributo),
  CONSTRAINT fk_atributo_idtabela FOREIGN KEY (idtabela)
      REFERENCES tabela (idtabela) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
--ALTER TABLE atributo OWNER TO globus;


CREATE TABLE ligacao
(
  idligacao integer NOT NULL,
  idchaveestrangeira integer NOT NULL,
  idatributofato integer NOT NULL, -- Chave Primária da tabela de Fatos
  idatributodimensao integer NOT NULL, -- Chave Estrangeira de uma tabela de Dimensão
  CONSTRAINT pk_ligacao_idligacao PRIMARY KEY (idligacao),
  CONSTRAINT fk_ligacao_chaveestrangeira FOREIGN KEY (idchaveestrangeira)
      REFERENCES chaveestrangeira (idchaveestrangeira) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_ligacao_atributofato FOREIGN KEY (idatributofato)
      REFERENCES atributo (idatributo) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT,
  CONSTRAINT fk_ligacao_atributodimensao FOREIGN KEY (idatributodimensao)
      REFERENCES atributo (idatributo) MATCH SIMPLE
      ON UPDATE RESTRICT ON DELETE RESTRICT
);
--ALTER TABLE ligacao OWNER TO globus;