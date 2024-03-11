-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into t_product (id, taste, description, imageUrl, quantity, pricePerItem, totalPrice)
values (1,'Äpple', 'Fruktig, mycket söt smak med tydlig karaktär av mango, inslag av ananaskaramell och äpple. Serveras vid 6-8°C som sällskapsdryck.','Pictures\apple.svg', 1, 15.90, 15.90);

insert into t_product (id, taste, description, imageUrl, quantity, pricePerItem, totalPrice)
values (2,'Päron', 'Fruktig, söt smak med tydlig karaktär av päron, inslag av honungsmelon och vanilj. Serveras vid 6-8°C som sällskapsdryck eller till frukt- och bärdesserter.','Pictures\pear.svg', 1, 15.90, 15.90);

insert into t_product (id, taste, description, imageUrl, quantity, pricePerItem, totalPrice)
values (3,'Exotic', 'Fruktig, mycket söt smak med inslag av passionsfrukt, persika och äpplen. Serveras 8-10°C som sällskapsdryck eller till frukt- eller bärdesserter.','Pictures\exotica.svg', 1, 15.90, 15.90);
