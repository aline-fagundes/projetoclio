CREATE TABLE tb_museus (
    id_museu int NOT NULL AUTO_INCREMENT,
    nome_museu varchar(100) NOT NULL,
    descricao varchar(1000) NOT NULL,
    foto blob NULL,
    funcionamento varchar(500) NOT NULL,
    PRIMARY KEY (id_museu)
) 