create table GuestbookEntry (
    id int8 not null,
    email varchar(255),
    text varchar(255),
    primary key (id)
);


create sequence hibernate_sequence;