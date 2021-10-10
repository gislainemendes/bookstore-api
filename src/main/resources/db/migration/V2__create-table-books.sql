create table books(
    id bigint not null auto_increment,
    title varchar(50) not null,
    releaseDate date not null,
    numberOfPages int not null,
    primary key(id)
);