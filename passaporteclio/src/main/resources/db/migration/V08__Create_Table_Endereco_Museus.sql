CREATE TABLE tb_endereco_museus (
    id_endereco bigint NOT NULL AUTO_INCREMENT,
    cep int NOT NULL,
    rua varchar(45) NOT NULL,
    numero int NOT NULL,
    bairro varchar(45) NOT NULL,
    cidade varchar(45) NOT NULL,
    estado varchar(2) NOT NULL,
    pais varchar(45) NOT NULL,
    fk_id_museu int NOT NULL,
    PRIMARY KEY (id_endereco),
    KEY fk_id_museu (fk_id_museu),
    CONSTRAINT fk_id_museu FOREIGN KEY (fk_id_museu) REFERENCES tb_museus (id_museu)
)