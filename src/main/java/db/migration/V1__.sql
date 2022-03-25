create table user_data (
    id int8 not null unique,
    email varchar(255) not null unique,
    username varchar(255) not null unique,
    pass_word varchar(255) not null,
    firstname varchar(255) not null,
    middlename varchar(255),
    lastname varchar(255) not null,
    dob Date not null,
    gender varchar(255) not null,
    isAdmin bool,
    primary key (id)
);

create table flight (
    id int8 not null unique,
    flightId varchar(255) not null unique,
    airline varchar(255) not null,
    source varchar(255) not null,
    destination varchar(255) not null,
    departure time not null,
    arrival time not null,
    primary key (id)
);

create sequence hibernate_sequence;
