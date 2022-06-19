CREATE TABLE tb_login (
  id_login int NOT NULL AUTO_INCREMENT,
  email varchar(100) NOT NULL,
  username varchar(50) NOT NULL,
  password varchar(300) DEFAULT NULL,
  fk_id_pessoa int NOT NULL,
  CONSTRAINT fk_id_pessoa FOREIGN KEY (fk_id_pessoa) REFERENCES tb_pessoas (id_pessoa),
  PRIMARY KEY (id_login)
)