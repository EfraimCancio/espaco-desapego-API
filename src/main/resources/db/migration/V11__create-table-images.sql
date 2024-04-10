CREATE TABLE TB_IMAGE(
    ID INT AUTO_INCREMENT NOT NULL COMMENT 'Identificação da imagem (chave primária)',
    IMAGE LONGBLOB COMMENT 'Arquivo da imagem salvo no banco',
    FILE_NAME VARCHAR(400),
    ID_PRODUTO INT NOT NULL,
    STATUS TINYINT,

    PRIMARY KEY (ID, ID_PRODUTO),
    foreign key (ID_PRODUTO) REFERENCES TB_PRODUTO(ID) ON UPDATE CASCADE ON DELETE CASCADE

) ENGINE = INNODB;