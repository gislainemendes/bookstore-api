create table authors(
    id bigint not null auto_increment,
    name varchar(30) not null,
    email varchar(30) not null,
    birthdate date not null,
    curriculo varchar(250) not null,
    primary key(id)
);