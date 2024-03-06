-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(1, 'field-1');
-- insert into myentity (id, field) values(2, 'field-2');
-- insert into myentity (id, field) values(3, 'field-3');
-- alter sequence myentity_seq restart with 4;

insert into t_product (id, taste, description, imageUrl, quantity, pricePerItem, totalPrice)
values (1,'Apple', 'Smakar bra','apple.img', 1, 15, 15);

insert into t_product (id, taste, description, imageUrl, quantity, pricePerItem, totalPrice)
values (2,'Pappel', 'Smakar jätte bra','papple.img', 1, 15, 15);

insert into t_product (id, taste, description, imageUrl, quantity, pricePerItem, totalPrice)
values (3,'Piron', 'Smakar inte så bra','piron.img', 1, 15, 15);
