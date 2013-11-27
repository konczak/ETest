
    alter table closedAnswers 
        drop constraint FKB703D69845D3B50;

    alter table closedAnswers 
        drop constraint FKB703D69B0250AB2;

    alter table closedQuestions 
        drop constraint FK212A5941DD6FC9DC;

    alter table closedQuestions 
        drop constraint FK212A5941B0250AB2;

    alter table closedQuestions_categoryOfQuestions 
        drop constraint FK11E8315A83C4B090;

    alter table closedQuestions_categoryOfQuestions 
        drop constraint FK11E8315A845D3B50;

    alter table users_roles 
        drop constraint FKF6CCD9C6CE3BBC90;

    alter table users_roles 
        drop constraint FKF6CCD9C6229B8C70;

    alter table users_userGroups 
        drop constraint FKC80EA016CE3BBC90;

    alter table users_userGroups 
        drop constraint FKC80EA016887EE4B2;

    drop table if exists categoryOfQuestions cascade;

    drop table if exists closedAnswers cascade;

    drop table if exists closedQuestions cascade;

    drop table if exists closedQuestions_categoryOfQuestions cascade;

    drop table if exists images cascade;

    drop table if exists roles cascade;

    drop table if exists userGroups cascade;

    drop table if exists userPersonalData cascade;

    drop table if exists users cascade;

    drop table if exists users_roles cascade;

    drop table if exists users_userGroups cascade;

    create table categoryOfQuestions (
        categoryOfQuestionsId  serial not null unique,
        title varchar(25) not null unique,
        primary key (categoryOfQuestionsId)
    );

    create table closedAnswers (
        closedAnswersId  serial not null unique,
        answer varchar(1000) not null,
        correct boolean not null,
        closedQuestionsId int4 not null,
        imagesId int4 unique,
        primary key (closedAnswersId)
    );

    create table closedQuestions (
        closedQuestionsId  serial not null unique,
        question varchar(1000) not null,
        author_usersId int4 not null,
        imagesId int4 unique,
        primary key (closedQuestionsId)
    );

    create table closedQuestions_categoryOfQuestions (
        closedQuestionsId int4 not null,
        categoryOfQuestionsId int4 not null,
        primary key (categoryOfQuestionsId, closedQuestionsId)
    );

    create table images (
        imagesId  serial not null unique,
        image oid not null,
        primary key (imagesId)
    );

    create table roles (
        rolesId  serial not null unique,
        name varchar(255) not null unique,
        primary key (rolesId),
        unique (name)
    );

    create table userGroups (
        userGroupsId  serial not null unique,
        title varchar(255) not null unique,
        primary key (userGroupsId)
    );

    create table userPersonalData (
        usersId int4 not null unique,
        firstname varchar(50) not null,
        lastname varchar(50) not null,
        primary key (usersId)
    );

    create table users (
        usersId  serial not null unique,
        email varchar(50) not null unique,
        locked boolean not null,
        password varchar(255) not null,
        registeredAt timestamp not null,
        primary key (usersId),
        unique (email)
    );

    create table users_roles (
        rolesId int4 not null,
        usersId int4 not null,
        primary key (usersId, rolesId)
    );

    create table users_userGroups (
        userGroupsId int4 not null,
        usersId int4 not null,
        primary key (usersId, userGroupsId)
    );

    alter table closedAnswers 
        add constraint FKB703D69845D3B50 
        foreign key (closedQuestionsId) 
        references closedQuestions;

    alter table closedAnswers 
        add constraint FKB703D69B0250AB2 
        foreign key (imagesId) 
        references images;

    alter table closedQuestions 
        add constraint FK212A5941DD6FC9DC 
        foreign key (author_usersId) 
        references users;

    alter table closedQuestions 
        add constraint FK212A5941B0250AB2 
        foreign key (imagesId) 
        references images;

    alter table closedQuestions_categoryOfQuestions 
        add constraint FK11E8315A83C4B090 
        foreign key (categoryOfQuestionsId) 
        references categoryOfQuestions;

    alter table closedQuestions_categoryOfQuestions 
        add constraint FK11E8315A845D3B50 
        foreign key (closedQuestionsId) 
        references closedQuestions;

    alter table users_roles 
        add constraint FKF6CCD9C6CE3BBC90 
        foreign key (usersId) 
        references users;

    alter table users_roles 
        add constraint FKF6CCD9C6229B8C70 
        foreign key (rolesId) 
        references roles;

    alter table users_userGroups 
        add constraint FKC80EA016CE3BBC90 
        foreign key (usersId) 
        references users;

    alter table users_userGroups 
        add constraint FKC80EA016887EE4B2 
        foreign key (userGroupsId) 
        references userGroups;
