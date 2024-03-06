
    create sequence t_product_SEQ start with 1 increment by 50;

    create table t_product (
        pricePerItem float(53) not null,
        quantity integer not null,
        totalPrice float(53) not null,
        id bigint not null,
        description varchar(255),
        imageUrl varchar(255),
        taste varchar(255),
        primary key (id)
    );
insert into t_product (id, taste, description, imageUrl, quantity, pricePerItem, totalPrice) values (1,'Apple', 'Smakar bra','apple.img', 1, 15, 15);
insert into t_product (id, taste, description, imageUrl, quantity, pricePerItem, totalPrice) values (2,'Pappel', 'Smakar jätte bra','papple.img', 1, 15, 15);
insert into t_product (id, taste, description, imageUrl, quantity, pricePerItem, totalPrice) values (3,'Piron', 'Smakar inte så bra','piron.img', 1, 15, 15);
