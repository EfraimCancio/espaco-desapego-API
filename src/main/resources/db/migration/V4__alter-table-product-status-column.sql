ALTER TABLE tb_produto DROP COLUMN status;

ALTER TABLE tb_produto ADD status TINYINT NOT NULL;

UPDATE tb_produto SET status = 1;