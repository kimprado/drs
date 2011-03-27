CREATE TABLE t
(
  id integer NOT NULL,
  numero integer,
  descricao character varying(30),
  CONSTRAINT pk_t_id PRIMARY KEY (id)
);

CREATE TABLE teste
(
  id integer NOT NULL,
  numero integer,
  texto character varying(100),
  CONSTRAINT pk_teste_id PRIMARY KEY (id)
);

CREATE TABLE tteste
(
  id integer NOT NULL,
  numero integer,
  descricao character varying(30),
  CONSTRAINT pk_tteste_id PRIMARY KEY (id)
);