
    alter table users_roles 
        drop constraint FKF6CCD9C66B612FAD;

    alter table users_roles 
        drop constraint FKF6CCD9C6C6366BCD;

    drop table if exists roles cascade;

    drop table if exists users cascade;

    drop table if exists users_roles cascade;

    drop sequence hibernate_sequence;

    create table roles (
        id int4 not null,
        name varchar(255),
        primary key (id)
    );

    create table users (
        id int4 not null,
        email varchar(255),
        locked boolean not null,
        password varchar(255),
        primary key (id)
    );

    create table users_roles (
        role_id int4 not null,
        user_id int4 not null,
        primary key (user_id, role_id)
    );

    alter table users_roles 
        add constraint FKF6CCD9C66B612FAD 
        foreign key (user_id) 
        references users;

    alter table users_roles 
        add constraint FKF6CCD9C6C6366BCD 
        foreign key (role_id) 
        references roles;

    create sequence hibernate_sequence;
