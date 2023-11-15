CREATE TABLE tb_transfer
(
    id         binary(16) NOT NULL PRIMARY KEY,
    payee      VARCHAR(255)   NOT NULL,
    payer      VARCHAR(255)   NOT NULL,
    status     VARCHAR(255)   NOT NULL,
    reason     VARCHAR(255),
    created_at DATETIME       NOT NULL,
    value      DECIMAL(10, 2) NOT NULL
);