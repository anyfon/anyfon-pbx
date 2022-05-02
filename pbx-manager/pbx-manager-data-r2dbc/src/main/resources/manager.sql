create table cdr
(
    id              varchar(36)           not null,
    root_id         varchar(36)           not null,
    call_date_time  timestamp   default now(),
    from_number     varchar(20)           not null,
    to_number       varchar(20)           not null,
    answer_time     int         default null,
    using_duration  int         default null,
    call_duration   int         default 0 not null,
    status          varchar(20),
    has_record_file boolean,
    tenant_id       varchar(36)           not null,
    sip_trunk_id    varchar(36) default null
);

create unique index cdr_id_uindex
    on cdr (id);
