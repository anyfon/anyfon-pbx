create table tenant
(
    id           varchar(36) not null
        constraint tenant_pk
            primary key,
    "uniqueName" varchar(15) not null,
    enabled      boolean default false
);

alter table tenant
    owner to admin;

create unique index tenant_id_uindex
    on tenant (id);

create unique index tenant_name_uindex
    on tenant ("uniqueName");




create table usr
(
    id           varchar(36) not null
        constraint usr_pk
            primary key,
    first_name   varchar(30) not null,
    middle_name  varchar(30),
    last_name    varchar(30) not null,
    email        varchar(40) not null,
    phone_number varchar(17) not null,
    role         varchar(10),
    password     varchar(100) not null,
    enabled      boolean default false
);

alter table usr
    owner to admin;

create unique index usr_email_uindex
    on usr (email);

create unique index usr_id_uindex
    on usr (id);

create unique index usr_phone_number_uindex
    on usr (phone_number);




create table tenant_user_rel
(
    tenant_id varchar(36) not null,
    user_id   varchar(36)
);

create index tenant_user_rel_tenant_id_user_id_index
    on tenant_user_rel (tenant_id, user_id);
