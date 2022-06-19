CREATE TABLE tb_endereco_museus (
  id_endereco int NOT NULL AUTO_INCREMENT,
  cep int NOT NULL,
  rua varchar(45) NOT NULL,
  numero int NOT NULL,
  bairro varchar(45) NOT NULL,
  cidade varchar(45) NOT NULL,
  estado varchar(2) NOT NULL,
  pais varchar(45) NOT NULL,
  PRIMARY KEY (id_endereco)
)