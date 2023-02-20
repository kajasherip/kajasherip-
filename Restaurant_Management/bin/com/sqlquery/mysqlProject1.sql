use jacob_restaurant;
show tables;
create table CUSTOMER (
customer_id varchar(10) not null ,
customer_name varchar(25) not null ,
gender varchar(10) not null,
email varchar(25) not null,
address varchar(45) not null,
mobile_number bigint not null,
primary key(customer_id)
);
create table MENU (
product_id varchar(45) not null,
product_name varchar(45) not null,
price decimal not null,
primary key(product_id)
);
create table SALES(
customer_id varchar(10) not null,
order_date date not null,
product_id varchar(45) not null,
constraint fk_customer foreign key(customer_id) references CUSTOMER(customer_id),
constraint fk_menu foreign key(product_id) references MENU(product_id)
);
create table MEMBERS(
customer_id varchar(10) not null,
join_date timestamp not null,
constraint fk_customer1 foreign key(customer_id) references CUSTOMER(customer_id)
);





