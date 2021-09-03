create table products
(
    id    bigserial primary key,
    title varchar(255),
    price int
);
insert into products (title, price)
values ('Water', 10),
       ('Juice', 20),
       ('Milk', 30),
       ('Bread', 40),
       ('Apple', 50),
       ('Orange', 60),
       ('Onion', 70),
       ('Pork', 80),
       ('Fish', 90),
       ('Blackberry', 100),
       ('Butter', 110),
       ('Cheese', 120),
       ('Cream', 130),
       ('Potato', 140),
       ('Banana', 150),
       ('Cake', 160),
       ('Pineapple', 170),
       ('Chips', 180),
       ('Wine', 190),
       ('Coffee', 200);

create table users
(
    id         bigserial primary key,
    username   varchar(30) not null,
    password   varchar(80) not null,
    email      varchar(50) unique,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

create table roles
(
    id         bigserial primary key,
    name       varchar(50) not null,
    created_at timestamp default current_timestamp,
    updated_at timestamp default current_timestamp
);

CREATE TABLE users_roles
(
    user_id bigint not null references users (id),
    role_id bigint not null references roles (id),
    primary key (user_id, role_id)
);

insert into roles (name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users (username, password, email)
values ('user', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'bob_johnson@gmail.com'),
       ('admin', '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 'john_johnson@gmail.com');

insert into users_roles (user_id, role_id)
values (1, 1),
       (2, 2);


