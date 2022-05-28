create table call_record
(
    id        varchar default 36 not null
        constraint call_record_pk
            primary key,
    tenant_id varchar default 36,
    source_ip varchar default 15
);



create unique index call_record_id_uindex
    on call_record (call_id);


create table call_operation
(
    id              varchar default 36 not null
        constraint call_operation_pk
            primary key,
    call_record_id  varchar default 36,
    parent_id       varchar default 36,
    app_name        varchar default 36,
    start_date_time timestamp
);

alter table call_operation
    owner to admin;

create unique index call_operation_id_uindex
    on call_operation (id);
