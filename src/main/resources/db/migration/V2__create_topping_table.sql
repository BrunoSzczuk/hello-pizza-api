create table if not exists topping
(
    id
                     uuid
        primary
            key,
    description
                     varchar(255),
    additional_price numeric(15,
                         4),
    enabled          boolean default true
)