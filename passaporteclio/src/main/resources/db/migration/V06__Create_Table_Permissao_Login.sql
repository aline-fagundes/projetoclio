CREATE TABLE tb_permissao_login (
  id_permissao int NOT NULL,
  id_login int NOT NULL,
  PRIMARY KEY (id_permissao, id_login),
  KEY (id_permissao),
  KEY (id_login),
  CONSTRAINT fk_id_login FOREIGN KEY (id_login) REFERENCES tb_login (id_login),
  CONSTRAINT fk_id_permissao FOREIGN KEY (id_permissao) REFERENCES tb_permissao (id_permissao)
)