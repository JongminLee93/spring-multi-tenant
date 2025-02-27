CREATE SCHEMA IF NOT EXISTS tenant_a;
CREATE SCHEMA IF NOT EXISTS tenant_b;
CREATE SCHEMA IF NOT EXISTS tenant_c;
CREATE SCHEMA IF NOT EXISTS public;


CREATE TABLE IF NOT EXISTS public.person(
    id integer primary key,
    name varchar(64) not null,
    age integer not null,
    email varchar(256) not null
);

CREATE TABLE IF NOT EXISTS tenant_a.person(
    id integer primary key,
    name varchar(64) not null,
    age integer not null,
    email varchar(256) not null
);

CREATE TABLE IF NOT EXISTS tenant_b.person(
    id integer primary key,
    name varchar(64) not null,
    age integer not null,
    email varchar(256) not null
);

CREATE TABLE IF NOT EXISTS tenant_c.person(
    id integer primary key,
    name varchar(64) not null,
    age integer not null,
    email varchar(256) not null
);

CREATE SEQUENCE IF NOT EXISTS public.person_id_seq;
CREATE SEQUENCE IF NOT EXISTS tenant_a.person_id_seq;
CREATE SEQUENCE IF NOT EXISTS tenant_b.person_id_seq;
CREATE SEQUENCE IF NOT EXISTS tenant_c.person_id_seq;