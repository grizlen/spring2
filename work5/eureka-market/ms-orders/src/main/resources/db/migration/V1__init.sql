create table orders (id bigserial primary key, product_id float, count int);
insert into orders (product_id, count)
values
(1, 2),
(2, 1),
(4, 1);
