create table user_data (
    id int8 not null,
    username varchar(255),
    pass_word varchar(255),
    fullname varchar(255),
    isAdmin bool,
    primary key (id)
);

create table flight (
    id int8 not null,
    flightId varchar(255) not null unique,
    airline varchar(255) not null,
    source varchar(255) not null,
    destination varchar(255) not null,
    departure time not null,
    arrival time not null,
    primary key (id)
);

create sequence hibernate_sequence;
