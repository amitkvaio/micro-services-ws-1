insert into currency_exchange
(id,currency_from,currency_to,conversion_multiple,environment)
values(10001,'USD','INR',70,'0');
insert into currency_exchange
(id,currency_from,currency_to,conversion_multiple,environment)
values(10002,'EUR','INR',75,'0');
insert into currency_exchange
(id,currency_from,currency_to,conversion_multiple,environment)
values(10003,'AUD','INR',25,'0');
/*
drop table currency_exchange;
create table currency_exchange(
    id NUMBER(4),
    currency_from VARCHAR(20),
    currency_to VARCHAR2(20),
    conversion_multiple NUMBER(2),
    environment VARCHAR(20)
);
*/