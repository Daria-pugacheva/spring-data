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

