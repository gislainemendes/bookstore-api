alter table books add column author_id bigint not null;

alter table books add foreign key(author_id) references authors(id);