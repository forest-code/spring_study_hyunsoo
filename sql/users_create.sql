-- for operation
create database if not exists springbook;

use springbook;

drop table if exists users;

create table users (
	id varchar(10) primary key,	
	name varchar(20) not null,
	password varchar(10) not null
);

create database if not exists testdb;

-- for test
use testdb;

drop table if exists users;

create table users (
	id varchar(10) primary key,	
	name varchar(20) not null,
	password varchar(10) not null
);

-- user setting
create user 'spring'@'localhost' identified by 'book';
grant all on springbook.* to 'spring'@'localhost';
grant all on testdb.* to 'spring'@'localhost';
flush privileges;