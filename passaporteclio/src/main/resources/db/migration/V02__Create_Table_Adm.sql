CREATE TABLE tb_adm (
  id_adm int NOT NULL AUTO_INCREMENT,
  fk_id_pessoa_adm int NOT NULL,
  PRIMARY KEY (id_adm),
  KEY fk_id_pessoa_adm (fk_id_pessoa_adm),
  CONSTRAINT fk_id_pessoa_adm FOREIGN KEY (fk_id_pessoa_adm) REFERENCES tb_pessoas (id_pessoa)
)