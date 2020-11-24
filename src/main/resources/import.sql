insert into cozinha (id, nome) values (1,'Tailandesa');
insert into cozinha (id, nome) values (2,'Indiana');
insert into cozinha (id, nome) values (3,'Americana');
insert into cozinha (id, nome) values (4,'Italiana');

insert into restaurante (nome, taxa_frete, cozinha_id) values ('Burger Porn', 5.5, 3)
insert into algafood.restaurante (nome, taxa_frete, cozinha_id) values ("Stilhano's Pizza", 7.50, 4)

insert into forma_pagamento (descricao) values ('dinheiro')
insert into forma_pagamento (descricao) values ('cartao')

insert into estado (id, nome) values (1, 'Sao Paulo')
insert into estado (id, nome) values (2, 'Rio de Janeiro')
insert into estado (id, nome) values (3, 'Minas Gerais')
insert into estado (id, nome) values (4, 'Goias')

insert into cidade (nome, estado_id) values ('Campinas', 1)
insert into cidade (nome, estado_id) values ('Ribeirao Preto', 1)

insert into cidade (nome, estado_id) values ('Cabo Frio', 2)
insert into cidade (nome, estado_id) values ('Paraty', 2)

insert into cidade (nome) values ('Belo Horizonte')
insert into cidade (nome) values ('Rio Quente')

insert into restaurante_forma_pagamento (restaurante_id, forma_pagamento_id) values (1,1), (1,2), (2,1)