create table users(
	id bigint not null auto_increment primary key,
	name varchar(100) not null,
	login varchar(100) not null,
	password varchar(256) not null
);