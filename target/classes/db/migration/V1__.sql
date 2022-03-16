create table user_data (
    id int8 not null,
    username varchar(255),
    pass_word varchar(255),
    fullname varchar(255),
    isAdmin bool,
    primary key (id)
);


create sequence hibernate_sequence;
