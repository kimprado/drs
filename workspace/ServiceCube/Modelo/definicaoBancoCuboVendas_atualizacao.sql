CREATE TABLE tempo
(
  id_tempo integer NOT NULL,
  dia integer,
  mes integer,
  ano integer,
  data date,
  CONSTRAINT pk_tempo_id_tempo PRIMARY KEY (id_tempo)
);

CREATE TABLE produto
(
  id_produto integer NOT NULL,
  descricao character varying(300), --Correto é text(memorando)
  marca character varying(100),
  categoria character varying(150),
  CONSTRAINT pk_produto_id_produto PRIMARY KEY (id_produto)
);

CREATE TABLE loja
(
  id_loja integer NOT NULL,
  loja_nome character varying(150),
  endereco character varying(200),
  cidade character varying(150),
  estado character varying(100),
  CONSTRAINT pk_loja_id_loja PRIMARY KEY (id_loja)
);

CREATE TABLE vendas
(
  id_vendas integer NOT NULL,
  quantidade integer,
  preco_unidade float(3),
  preco_total float(3),
  tempo_id integer,
  loja_id integer,
  produto_id integer,
  CONSTRAINT pk_vendas_id_vendas PRIMARY KEY (id_vendas),
  CONSTRAINT fk_tempo FOREIGN KEY(tempo_id) REFERENCES tempo(id_tempo),
  CONSTRAINT fk_loja FOREIGN KEY(loja_id) REFERENCES loja(id_loja),
  CONSTRAINT fk_produto FOREIGN KEY(produto_id) REFERENCES produto(id_produto)
  
);