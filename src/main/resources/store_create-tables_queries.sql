create table orders(
	number integer primary key,
	clientId integer references clients(id),
	registration_date date not null,
	closing_date date not null,
	status varchar(50) references statuses(status) 
)
create table statuses(
	status varchar(50) primary key
)
create table users(
	id serial references clients(id),
	email varchar(100) not null,
	role varchar(50) references roles(role),
	password varchar(30) not null
)
create table roles (
	role varchar(50) primary key
)
create table clients(
	id serial Primary key,
	name varchar(50) not null,
	surname varchar(50) not null,
	phone_Number varchar(20) not null,
	adres varchar(100) not null
)
create table products(
	id serial primary key,
	name varchar(100) not null,
	description varchar(500),
	password varchar(30) not null,
	remains integer not null
)
create table products_in_order(
	order_number serial references orders(number),
	productId serial references products(id)
)