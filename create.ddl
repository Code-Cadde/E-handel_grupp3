
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
insert into t_product (id, taste, description, imageUrl, quantity, pricePerItem, totalPrice) values (1,'Apple', 'Fruktig, mycket söt smak med tydlig karaktär av mango, inslag av ananaskaramell och äpple. Serveras vid 6-8°C som sällskapsdryck.','src\main\resources\Pictures\apple.svg', 1, 15.90, 15.90);
insert into t_product (id, taste, description, imageUrl, quantity, pricePerItem, totalPrice) values (2,'Päron', 'Fruktig, söt smak med tydlig karaktär av päron, inslag av honungsmelon och vanilj. Serveras vid 6-8°C som sällskapsdryck eller till frukt- och bärdesserter.','papple.img', 1, 15.90, 15.90);
insert into t_product (id, taste, description, imageUrl, quantity, pricePerItem, totalPrice) values (3,'Exotic', 'Fruktig, mycket söt smak med inslag av passionsfrukt, persika och äpplen. Serveras 8-10°C som sällskapsdryck eller till frukt- eller bärdesserter.','piron.img', 1, 15.90, 15.90);
