alter table tb_account
    add document varchar(11) not null;

alter table tb_account
    add email varchar(40) not null;

alter table tb_account
    add password varchar(20) not null;