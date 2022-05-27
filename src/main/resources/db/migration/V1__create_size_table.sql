create table size
(
    id            uuid primary key,
    description   varchar(255),
    topping_limit int,
    default_price numeric(15, 4)

)