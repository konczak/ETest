
    alter table categoryOfQuestions_closedQuestions 
        drop constraint FK29A5745A160ACC0D;

    alter table categoryOfQuestions_closedQuestions 
        drop constraint FK29A5745AE11E4C0D;

    alter table closedQuestions_categoryOfQuestions 
        drop constraint FK11E8315AE11E4C0D;

    alter table closedQuestions_categoryOfQuestions 
        drop constraint FK11E8315A160ACC0D;

    alter table closedQuestions_closedAnswers 
        drop constraint FKC50EA2EB160ACC0D;

    alter table closedQuestions_closedAnswers 
        drop constraint FKC50EA2EBA89C50CD;

    alter table users_roles 
        drop constraint FKF6CCD9C61B6440D;

    alter table users_roles 
        drop constraint FKF6CCD9C65C8B802D;

    drop table if exists categoryOfQuestions cascade;

    drop table if exists categoryOfQuestions_closedQuestions cascade;

    drop table if exists closedAnswers cascade;

    drop table if exists closedQuestions cascade;

    drop table if exists closedQuestions_categoryOfQuestions cascade;

    drop table if exists closedQuestions_closedAnswers cascade;

    drop table if exists roles cascade;

    drop table if exists users cascade;

    drop table if exists users_roles cascade;

    create table categoryOfQuestions (
        categoryOfQuestionsId  serial not null unique,
        title varchar(25) not null unique,
        primary key (categoryOfQuestionsId)
    );

    create table categoryOfQuestions_closedQuestions (
        categoryOfQuestionsId int4 not null,
        closedQuestionsId int4 not null,
        primary key (categoryOfQuestionsId, closedQuestionsId)
    );

    create table closedAnswers (
        closedAnswersId  serial not null unique,
        answer varchar(1000) not null unique,
        primary key (closedAnswersId)
    );

    create table closedQuestions (
        closedQuestionsId  serial not null unique,
        question varchar(1000) not null,
        primary key (closedQuestionsId)
    );

    create table closedQuestions_categoryOfQuestions (
        closedQuestionsId int4 not null,
        categoryOfQuestionsId int4 not null,
        primary key (closedQuestionsId, categoryOfQuestionsId)
    );

    create table closedQuestions_closedAnswers (
        correct boolean not null,
        closedQuestionsId int4,
        closedAnswersId int4,
        primary key (closedAnswersId, closedQuestionsId)
    );

    create table roles (
        rolesId  serial not null unique,
        name varchar(255) not null unique,
        primary key (rolesId),
        unique (name)
    );

    create table users (
        usersId  serial not null unique,
        email varchar(50) not null unique,
        locked boolean not null,
        password varchar(255) not null,
        primary key (usersId),
        unique (email)
    );

    create table users_roles (
        rolesId int4 not null,
        usersId int4 not null,
        primary key (usersId, rolesId)
    );

    alter table categoryOfQuestions_closedQuestions 
        add constraint FK29A5745A160ACC0D 
        foreign key (closedQuestionsId) 
        references closedQuestions;

    alter table categoryOfQuestions_closedQuestions 
        add constraint FK29A5745AE11E4C0D 
        foreign key (categoryOfQuestionsId) 
        references categoryOfQuestions;

    alter table closedQuestions_categoryOfQuestions 
        add constraint FK11E8315AE11E4C0D 
        foreign key (categoryOfQuestionsId) 
        references categoryOfQuestions;

    alter table closedQuestions_categoryOfQuestions 
        add constraint FK11E8315A160ACC0D 
        foreign key (closedQuestionsId) 
        references closedQuestions;

    alter table closedQuestions_closedAnswers 
        add constraint FKC50EA2EB160ACC0D 
        foreign key (closedQuestionsId) 
        references closedQuestions;

    alter table closedQuestions_closedAnswers 
        add constraint FKC50EA2EBA89C50CD 
        foreign key (closedAnswersId) 
        references closedAnswers;

    alter table users_roles 
        add constraint FKF6CCD9C61B6440D 
        foreign key (usersId) 
        references users;

    alter table users_roles 
        add constraint FKF6CCD9C65C8B802D 
        foreign key (rolesId) 
        references roles;
