create table profiles(
    id bigint not null auto_increment primary key,
    name varchar(100) not null
);


create table user_profiles(
    user_id bigint not null,
    profile_id bigint not null,

    primary key (user_id, profile_id),
    foreign key (user_id) references users(id),
    foreign key (profile_id) references profiles(id)
);


insert into profiles values (1, 'ROLE_ADMIN');
insert into profiles values (2, 'ROLE_COMUM');
