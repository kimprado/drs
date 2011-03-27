CREATE TABLE vendas
(
  id_vendas integer NOT NULL,
  quantidade integer,
  preco_unidade float(3),
  preco_total float(3),
  CONSTRAINT pk_vendas_id_vendas PRIMARY KEY (id_vendas)
);

CREATE TABLE tempo
(
  id_tempo integer NOT NULL,
  dia integer,
  mes integer,
  ano integer,
  data date,
  id_vendas_dimensao integer NOT NULL,
  CONSTRAINT pk_tempo_id_tempo PRIMARY KEY (id_tempo),
  CONSTRAINT fk_vendas
	FOREIGN KEY(id_vendas_dimensao)
	REFERENCES vendas(id_vendas)
);

CREATE TABLE produto
(
  id_produto integer NOT NULL,
  descricao character varying(300), --Correto é text(memorando)
  marca character varying(100),
  categoria character varying(150),
  id_vendas_dimensao integer NOT NULL,
  CONSTRAINT pk_produto_id_produto PRIMARY KEY (id_produto),
  CONSTRAINT fk_vendas
	FOREIGN KEY(id_vendas_dimensao)
	REFERENCES vendas(id_vendas)
);

CREATE TABLE loja
(
  id_loja integer NOT NULL,
  loja_nome character varying(150),
  endereco character varying(200),
  cidade character varying(150),
  estado character varying(100),
  id_vendas_dimensao integer NOT NULL,
  CONSTRAINT pk_loja_id_loja PRIMARY KEY (id_loja),
  CONSTRAINT fk_vendas
	FOREIGN KEY(id_vendas_dimensao)
	REFERENCES vendas(id_vendas)
);