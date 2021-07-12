create table customers
(
    id   bigint auto_increment
        primary key,
    name varchar(255) not null
);

create table products
(
    id    bigint auto_increment
        primary key,
    cost  float        not null,
    title varchar(512) not null
);

create table cutomers_products
(
    id           bigint auto_increment
        primary key,
    product_cost float  null,
    customer_id  bigint null,
    product_id   bigint null,
    constraint FKqt30s8upho7locvcnymc38atd
        foreign key (product_id) references lesson_05_hibernate.products (id),
    constraint FKsdpsarwqh0w6v6nu526w7de2h
        foreign key (customer_id) references lesson_05_hibernate.customers (id)
);

INSERT INTO customers (id, name) VALUES (1, 'John');
INSERT INTO customers (id, name) VALUES (2, 'Kevin');

INSERT INTO products (id, cost, title) VALUES (1, 33.7, 'Product 1');
INSERT INTO products (id, cost, title) VALUES (2, 30.7, 'Product 2');
INSERT INTO products (id, cost, title) VALUES (3, 540.3, 'Product 3');

INSERT INTO cutomers_products (id, product_cost, customer_id, product_id) VALUES (1, 33.7, 1, 1);
INSERT INTO cutomers_products (id, product_cost, customer_id, product_id) VALUES (2, 30.7, 1, 2);
INSERT INTO cutomers_products (id, product_cost, customer_id, product_id) VALUES (3, 100.4, 1, 3);
INSERT INTO cutomers_products (id, product_cost, customer_id, product_id) VALUES (4, 33.7, 2, 1);
INSERT INTO cutomers_products (id, product_cost, customer_id, product_id) VALUES (5, 30.7, 2, 2);
INSERT INTO cutomers_products (id, product_cost, customer_id, product_id) VALUES (6, 540.3, 2, 3);