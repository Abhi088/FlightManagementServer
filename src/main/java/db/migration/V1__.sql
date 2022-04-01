create table user_data (
    id int8 not null unique,
    email varchar(255) not null unique,
    username varchar(255) not null unique,
    pass_word varchar(255) not null,
    first_name varchar(255) not null,
    middle_name varchar(255),
    last_name varchar(255) not null,
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
    source_terminal int not null,
    destination varchar(255) not null,
    destination_terminal int not null,
    departure time not null,
    arrival time not null,
    primary key (id)
);

create table airport (
    id int8 not null unique,
    code varchar(255) not null unique,
    name varchar(255) not null,
    terminals integer,
    primary key (id)
);

create sequence hibernate_sequence;
