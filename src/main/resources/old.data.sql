create table cliente (
    id integer not null primary key auto_increment,
    nome varchar(100),
    cpf varchar(11)
);

create table produto (
    id integer primary key auto_increment,
    descricao varchar(100),
    preco_unitario numeric(20, 2)
);

create table pedido (
    id integer primary key auto_increment,
    data_pedidio timestamp,
    total numeric(20, 2),
    status varchar(20),
    cliente_id integer references cliente(id)
);

create table item_pedidio (
    id integer primary key auto_increment,
    data_pedidio timestamp,
    quantidade integer,
    pedido_id integer references pedido(id),
    produto_id integer references produto(id)
);