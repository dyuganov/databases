-- Database: bd

-- DROP DATABASE IF EXISTS bd;

drop table contacts cascade;
drop table dev_processes cascade;
drop table dev_statuses cascade;
drop table film_types cascade;
drop table films cascade;
drop table prices cascade;
drop table users cascade;
drop table orders cascade;
drop table service_types cascade;
drop table user_types cascade;

/*CREATE DATABASE bd
    WITH
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Russian_Russia.1251'
    LC_CTYPE = 'Russian_Russia.1251'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;*/

CREATE TABLE "film_types" (
	"id" BIGSERIAL NOT NULL,
	"name" varchar(64) NOT NULL UNIQUE,
	"dev_process_id" bigint NOT NULL,
	"iso" int NOT NULL,
	"is_monochrome" BOOLEAN NOT NULL,
	CONSTRAINT "film_types_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "orders" (
	"id" BIGSERIAL NOT NULL,
	"user_id" bigint NOT NULL,
	"creation_time" timestamp with time zone NOT NULL,
	CONSTRAINT "orders_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "dev_processes" (
	"id" BIGSERIAL NOT NULL,
	"name" varchar(32) NOT NULL,
	CONSTRAINT "dev_processes_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "service_types" (
	"id" BIGSERIAL NOT NULL,
	"name" varchar(32) NOT NULL UNIQUE,
	CONSTRAINT "service_types_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "user_types" (
	"id" BIGSERIAL NOT NULL,
	"name" varchar(64) NOT NULL UNIQUE,
	CONSTRAINT "user_types_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "users" (
	"id" BIGSERIAL NOT NULL,
	"login" varchar(64) NOT NULL UNIQUE,
	"password" varchar(64) NOT NULL,
	"contact_id" bigint unique,
	"user_type_id" bigint NOT NULL,
	CONSTRAINT "users_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "prices" (
	"id" BIGSERIAL NOT NULL,
	"service_type_id" bigint NOT NULL,
	"value" decimal NOT NULL CHECK (value >= 0),
	"adding_date" timestamp with time zone NOT NULL,
	CONSTRAINT "prices_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "dev_statuses" (
	"id" BIGSERIAL NOT NULL,
	"name" varchar(64) NOT NULL,
	CONSTRAINT "dev_statuses_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "films" (
	"id" BIGSERIAL NOT NULL,
	"order_id" serial NOT NULL,
	"film_type_id" bigint NOT NULL,
	"amount" bigint NOT NULL,
	"service_type_id" bigint NOT NULL,
	"dev_status_id" bigint NOT NULL,
	CONSTRAINT "films_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);


CREATE TABLE "contacts" (
	"id" BIGSERIAL NOT NULL,
	"email" varchar(255),
	"phone" varchar(255),
	"vk_link" varchar(255),
	"telegram_link" varchar(255),
	CONSTRAINT "contacts_pk" PRIMARY KEY ("id")
) WITH (
  OIDS=FALSE
);

CREATE TABLE "film_usage" (
	"id" BIGSERIAL NOT NULL PRIMARY KEY,
	"usage" varchar(255) NOT NULL,
	"min_iso" integer default 0
) WITH (
  OIDS=FALSE
);

create function films_update() returns trigger as $films_update$
    begin
        if NEW.amount < 1 then
            RAISE EXCEPTION 'amount cannot be less than 1';
        end if;
        return NEW;
    end
   $films_update$ LANGUAGE plpgsql;

CREATE TRIGGER dev_status_update BEFORE INSERT OR UPDATE ON films
    FOR EACH ROW EXECUTE PROCEDURE films_update();


ALTER TABLE "film_types" ADD CONSTRAINT "film_types_fk0" FOREIGN KEY ("dev_process_id") REFERENCES "dev_processes"("id");

ALTER TABLE "orders" ADD CONSTRAINT "orders_fk0" FOREIGN KEY ("user_id") REFERENCES "users"("id");

ALTER TABLE "users" ADD CONSTRAINT "users_fk0" FOREIGN KEY ("contact_id") REFERENCES "contacts"("id");
ALTER TABLE "users" ADD CONSTRAINT "users_fk1" FOREIGN KEY ("user_type_id") REFERENCES "user_types"("id");

ALTER TABLE "prices" ADD CONSTRAINT "prices_fk0" FOREIGN KEY ("service_type_id") REFERENCES "service_types"("id");

ALTER TABLE "films" ADD CONSTRAINT "films_fk0" FOREIGN KEY ("order_id") REFERENCES "orders"("id");
ALTER TABLE "films" ADD CONSTRAINT "films_fk1" FOREIGN KEY ("film_type_id") REFERENCES "film_types"("id");
ALTER TABLE "films" ADD CONSTRAINT "films_fk2" FOREIGN KEY ("service_type_id") REFERENCES "service_types"("id");
ALTER TABLE "films" ADD CONSTRAINT "films_fk3" FOREIGN KEY ("dev_status_id") REFERENCES "dev_statuses"("id");


-- Data fill
insert into dev_processes values (1, 'C41'),
                                 (2, 'D76'),
                                 (3, 'ECN-2');

insert into film_types values (1, 'Kodak Colorplus 200', 1, 200, false),
                              (2, 'Kodak Ultramax 400', 1, 400, false),
                              (3, 'Agfa Aviphot Pan 200', 2, 200, true),
                              (4, 'KodakVision 500T', 3, 500, false),
                              (5, 'Fujifilm Superia X-TRA 400', 1, 400, false);


insert into service_types values (1, 'Проявка'),
                                 (2, 'Сканирование'),
                                 (3, 'Проявка и сканирование');

insert into prices values (1, 1, 250, now()),
                          (2, 2, 400, now()),
                          (3, 3, 550, now());

insert into dev_statuses values (1, 'Принято'),
                                (2, 'Проявлено'),
                                (3, 'Отсканоровано'),
                                (4, 'Готово к выдаче'),
                                (5, 'Завершено');

insert into user_types values (1, 'Client'),
                              (2, 'Admin');

insert into contacts values (1, null, '89145559111', null, 'https://t.me/dyuganov'),
                            (2, null, null, 'https://vk.com/n.dyuganov', null),
                            (3, null, null, null, 'https://t.me/not_amebka'),
                            (4, 'n.dyuganov@g.nsu.ru', '89145559111', 'https://vk.com/n.dyuganov', 'https://t.me/not_amebka'),
                            (5, null, null, null, null);

insert into users values (1, 'login1', 'password1', 1, 1),
                         (2, 'login2', 'password2', 2, 1),
                         (3, 'login3', 'password3', 3, 1),
                         (4, 'admin', 'admin', null, 2),
                         (5, 'login5', 'password5', 4, 1),
                         (6, 'login6', 'password6', 5, 1);

insert into orders values (1, 1, now()),
                          (2, 1, now()),
                          (3, 2, now()),
                          (4, 5, now()),
                          (5, 1, now());

insert into films values (1, 1, 1, 2, 3, 1),
                         (2, 1, 2, 1, 2, 1),
                         (3, 2, 1, 2, 3, 1),
                         (4, 3, 3, 1, 1, 1),
                         (5, 3, 2, 3, 1, 1),
                         (6, 4, 1, 1, 3, 3);

insert into film_usage values (1, 'Landscapes', 10),
                              (2, 'Sport', 400),
                              (3, 'Portraits', 100),
                              (4, 'Night', 400);


select film_types.name, film_types.iso, film_usage.usage from film_types cross join film_usage where iso >= film_usage.min_iso;

select name, is_monochrome from film_types group by name, is_monochrome having is_monochrome = true;

select * from prices full outer join service_types st on prices.service_type_id = st.id;

create or replace procedure COUNT_WITH_ISO(inout val int)
language plpgsql
as $$
begin
    select COUNT(*) from film_types WHERE film_types.iso = val into val;
end;$$

/*update films set dev_status_id = 5 where order_id = 1;
update films set dev_status_id = 2 where order_id = 1;

select * from orders;

select distinct orders.id, user_id, creation_time
from orders inner join films f on orders.id = f.order_id
where dev_status_id != 5;

select orders.id, user_id, creation_time
from orders inner join films f on orders.id = f.order_id
where orders.id in (select order_id from films where films.dev_status_id != 5);

select * from orders where orders.id in (select order_id from films where films.dev_status_id != 5 and user_id = 2);


select distinct orders.id, user_id, creation_time
from orders inner join films f on orders.id = f.order_id
where orders.id in (select order_id from films where films.dev_status_id != 5 and user_id = 2);*/







