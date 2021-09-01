create table roles (id serial not null constraint roles_id_pk primary key, name varchar(20) not null);

create table users (id bigserial not null constraint users_id_pk primary key, login varchar(50) unique, password varchar(500));

create table users_roles(user_id bigint references users(id), role_id int references roles(id));

insert into roles(name) values
('ROLE_ADMIN'),
('ROLE_USER');
