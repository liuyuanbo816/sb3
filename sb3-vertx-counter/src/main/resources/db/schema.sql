DROP TABLE IF EXISTS tb_account;
CREATE TABLE tb_account
(
    id      int          NOT NULL AUTO_INCREMENT,
    name    varchar(255) NOT NULL,
    balance double       NOT NULL,
    PRIMARY KEY (id)
);