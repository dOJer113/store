create table roles
(
    role varchar(50) primary key
);
create table statuses
(
    status varchar(50) primary key
);
create table users
(
    userId   serial primary key,
    email    varchar(100) not null,
    role     varchar(50) references roles (role),
    password varchar(30)  not null
);
create table clients
(
    userId      serial references users (userId),
    name        varchar(50)  not null,
    surname     varchar(50)  not null,
    phoneNumber varchar(20)  not null,
    address     varchar(100) not null
);

create table orders
(
    number           serial primary key,
    userId           integer references users (userId),
    registrationDate date not null,
    closingDate      date not null,
    status           varchar(50) references statuses (status)
);
create table products
(
    id          serial primary key,
    name        varchar(100) not null,
    description varchar(500),
    price       money      not null,
    remains     integer      not null
);
create table products_in_order
(
    order_number  serial references orders (number),
    productId     serial references products (id),
    count_product integer not null
);