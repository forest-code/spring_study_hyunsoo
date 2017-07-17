-- user setting
create user 'spring'@'localhost' identified by 'book';
grant all on springbook.* to 'spring'@'localhost';
grant all on testdb.* to 'spring'@'localhost';
flush privileges;


-- for operation
create database if not exists springbook;

use springbook;

drop table if exists users;

create table users (
	id varchar(10) primary key,	
	name varchar(20) not null,
	password varchar(10) not null
);

-- add column for user level
alter table users add column level tinyint not null;
alter table users add column login int not null;
alter table users add column recommend int not null;

-- set default value each columns
alter table users alter column level set default 1;
alter table users alter column login set default 0;
alter table users alter column recommend set default 0;

-- add coloumn for email
alter table users add column email varchar(50) not null;


-- for test
create database if not exists testdb;

use testdb;

drop table if exists users;

create table users (
	id varchar(10) primary key,	
	name varchar(20) not null,
	password varchar(10) not null
);

-- add column for user level
alter table users add column level tinyint not null;
alter table users add column login int not null;
alter table users add column recommend int not null;

-- set default value each columns
alter table users alter column level set default 1;
alter table users alter column login set default 0;
alter table users alter column recommend set default 0;

-- add coloumn for email
alter table users add column email varchar(50) not null;