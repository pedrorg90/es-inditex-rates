create table rate(
    brand_id bigint not null,
    start_date datetime not null,
    end_date datetime not null,
    price_list int not null,
    product_id bigint not null,
    priority int not null,
    price double not null,
    curr varchar (3) not null,
    primary key (brand_id,price_list,product_id)
);