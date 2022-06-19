CREATE TABLE tb_avaliacao (
  id_avaliacao int NOT NULL AUTO_INCREMENT,
  nota int NOT NULL,
  avaliacao varchar(300) NOT NULL,
  fk_id_usuario_avaliacao int NOT NULL,
  fk_id_museu_avaliacao int NOT NULL,
  PRIMARY KEY (id_avaliacao),
  KEY fk_id_museu (fk_id_museu_avaliacao),
  KEY fk_id_usuario_avaliacao (fk_id_usuario_avaliacao),
  CONSTRAINT fk_id_museu_avaliacao FOREIGN KEY (fk_id_museu_avaliacao) REFERENCES tb_museus (id_museu),
  CONSTRAINT fk_id_usuario_avaliacao FOREIGN KEY (fk_id_usuario_avaliacao) REFERENCES tb_usuarios (id_usuario)
)