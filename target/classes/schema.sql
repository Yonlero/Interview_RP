drop table if exists tb_address CASCADE;
drop table if exists tb_client CASCADE;
drop table if exists tb_equipment CASCADE;
drop table if exists tb_order_service CASCADE;

create table tb_address
(
    id       varchar(255) default random_uuid() not null,
    city     varchar(255),
    district varchar(255),
    number   varchar(255),
    street   varchar(255),
    primary key (id)
);

create table tb_client
(
    id         varchar(255) default random_uuid() not null,
    cellphone  varchar(255),
    email      varchar(255),
    name       varchar(255),
    address_id varchar(255),
    primary key (id)
);

create table tb_equipment
(
    id        varchar(255) default random_uuid() not null,
    brand     varchar(255),
    type      varchar(255),
    client_id varchar(255)                       not null,
    order_id  varchar(255),
    primary key (id)
);

create table tb_order_service
(
    id             varchar(255) default random_uuid() not null,
    description    varchar(255),
    order_problems varchar(255),
    status         integer,
    client_id      varchar(255)                       not null,
    primary key (id)
);

alter table tb_client
    add constraint FKnma1100f0pgxukinshf5ab061
        foreign key (address_id)
            references tb_address;

alter table tb_equipment
    add constraint FKavsevqa9bw3rtuuu5a6klnwih
        foreign key (client_id)
            references tb_client;

alter table tb_equipment
    add constraint FK5fjd3b0v3sie6uuj15x69s6hg
        foreign key (order_id)
            references tb_order_service;

alter table tb_order_service
    add constraint FKk7wwkkvhxol73gyqnjkx3tshp
        foreign key (client_id)
            references tb_client;