CREATE TABLE tb_museus (
  id_museu int NOT NULL AUTO_INCREMENT,
  nome_museu varchar(100) NOT NULL,
  descricao varchar(1000) NOT NULL,
  foto blob NOT NULL,
  funcionamento varchar(500) NOT NULL,
  fk_id_endereco int NOT NULL,
  PRIMARY KEY (id_museu),
  KEY fk_id_endereco (fk_id_endereco),
  CONSTRAINT fk_id_endereco FOREIGN KEY (fk_id_endereco) REFERENCES tb_endereco_museus (id_endereco)
) 