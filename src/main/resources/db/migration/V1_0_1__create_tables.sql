create table public.users
(
    id                bigserial
        constraint users_pk
            primary key,
    username          varchar               not null,
    user_location     varchar               not null,
    registration_date timestamp             not null,
    is_deleted        boolean default false not null,
    change_date       timestamp
);

alter table public.users
    owner to postgres;

create unique index users_username_uindex
    on public.users (username);

create table public.security
(
    id       bigserial
        constraint security_pk
            primary key,
    login    varchar not null,
    password varchar not null,
    user_id  bigint  not null
        constraint security_users_id_fk
            references public.users
            on update cascade on delete cascade,
    role     varchar,
    email    varchar not null
);

alter table public.security
    owner to postgres;

create unique index security_login_uindex
    on public.security (login);

create table public.preferences
(
    id              bigserial
        constraint preferences_pk
            primary key,
    other_countries varchar not null,
    within_country  varchar not null,
    user_id         bigint  not null
        constraint preferences_users_id_fk
            references public.users
);

alter table public.preferences
    owner to postgres;

create table public.letters
(
    id               bigserial
        constraint letters_pk
            primary key,
    header           text      not null,
    body             text      not null,
    stamp            bytea,
    letter_type      bytea     not null,
    sending_time     timestamp not null,
    receiving_time   timestamp not null,
    sender_user_id   bigint    not null
        constraint letters_users_id_fk
            references public.users
            on update cascade on delete cascade,
    receiver_user_id bigint    not null
        constraint letters_users_id_fk2
            references public.users
            on update cascade on delete cascade
);

alter table public.letters
    owner to postgres;

create table public.letter_condition
(
    id        bigserial
        constraint letter_condition_pk
            primary key,
    condition varchar default 'UNSEEN'::character varying,
    letter_id bigint not null
        constraint letter_condition_letters_id_fk
            references public.letters
            on update cascade on delete cascade,
    user_id   bigint not null
        constraint letter_condition_users_id_fk
            references public.users
            on update cascade on delete cascade
);

alter table public.letter_condition
    owner to postgres;

create table public.friendship
(
    id             bigserial
        constraint friendship_pk
            primary key,
    first_user_id  bigint not null
        constraint friendship_users_id_fk
            references public.users
            on update cascade on delete cascade,
    second_user_id bigint not null
        constraint friendship_users_id_fk2
            references public.users
            on update cascade on delete cascade
);

alter table public.friendship
    owner to postgres;

create table public.flyway_schema_history
(
    installed_rank integer                 not null
        constraint flyway_schema_history_pk
            primary key,
    version        varchar(50),
    description    varchar(200)            not null,
    type           varchar(20)             not null,
    script         varchar(1000)           not null,
    checksum       integer,
    installed_by   varchar(100)            not null,
    installed_on   timestamp default now() not null,
    execution_time integer                 not null,
    success        boolean                 not null
);

alter table public.flyway_schema_history
    owner to postgres;

create index flyway_schema_history_s_idx
    on public.flyway_schema_history (success);

