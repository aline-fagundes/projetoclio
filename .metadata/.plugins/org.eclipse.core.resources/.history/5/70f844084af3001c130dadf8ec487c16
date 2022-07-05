CREATE TABLE tb_usuarios (
  id_usuario int NOT NULL AUTO_INCREMENT,
  pontuacao int NOT NULL,
  fk_id_pessoa_usuario int NOT NULL,
  PRIMARY KEY (id_usuario),
  KEY fk_id_pessoa (fk_id_pessoa_usuario),
  CONSTRAINT fk_id_pessoa_usuario FOREIGN KEY (fk_id_pessoa_usuario) REFERENCES tb_pessoas (id_pessoa)
)