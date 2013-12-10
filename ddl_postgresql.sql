
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

    alter table exams 
        drop constraint FK5C74C34CE3BBC90;

    alter table exams 
        drop constraint FK5C74C34887EE4B2;

    alter table testTemplates 
        drop constraint FK9E09A2C7DD6FC9DC;

    alter table testTemplates_closedQuestions 
        drop constraint FK74DCE3C91CDF25A3;

    alter table testTemplates_closedQuestions 
        drop constraint FK74DCE3C947E9CE1D;

    alter table userExamClosedAnswers 
        drop constraint FK699522FFEB4C8FFB;

    alter table userExamClosedAnswers 
        drop constraint FK699522FFF94C0CD0;

    alter table userExamClosedQuestions 
        drop constraint FK89AC3157845D3B50;

    alter table userExamClosedQuestions 
        drop constraint FK89AC315766EBE590;

    alter table userExams 
        drop constraint FK12D0172980E0DD10;

    alter table userExams 
        drop constraint FK12D01729CE3BBC90;

    alter table users_roles 
        drop constraint FKF6CCD9C6229B8C70;

    alter table users_roles 
        drop constraint FKF6CCD9C6CE3BBC90;

    alter table users_userGroups 
        drop constraint FKC80EA016CE3BBC90;

    alter table users_userGroups 
        drop constraint FKC80EA016887EE4B2;

    drop table if exists categoryOfQuestions cascade;

    drop table if exists closedAnswers cascade;

    drop table if exists closedQuestions cascade;

    drop table if exists closedQuestions_categoryOfQuestions cascade;

    drop table if exists exams cascade;

    drop table if exists images cascade;

    drop table if exists roles cascade;

    drop table if exists testTemplates cascade;

    drop table if exists testTemplates_closedQuestions cascade;

    drop table if exists userExamClosedAnswers cascade;

    drop table if exists userExamClosedQuestions cascade;

    drop table if exists userExams cascade;

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

    create table exams (
        examsId  serial not null unique,
        createdAt timestamp not null,
        generated boolean not null,
        usersId int4 not null,
        userGroupsId int4 not null,
        primary key (examsId)
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

    create table testTemplates (
        testTemplatesId  serial not null unique,
        subject varchar(50) not null unique,
        author_usersId int4,
        primary key (testTemplatesId)
    );

    create table testTemplates_closedQuestions (
        mandatory boolean not null,
        closedQuestion_closedQuestionsId int4,
        testTemplate_testTemplatesId int4,
        primary key (closedQuestion_closedQuestionsId, testTemplate_testTemplatesId)
    );

    create table userExamClosedAnswers (
        userExamClosedAnswersId  serial not null unique,
        markedByUser boolean not null,
        closedAnswer_closedAnswersId int4,
        userExamClosedQuestionsId int4 not null,
        primary key (userExamClosedAnswersId)
    );

    create table userExamClosedQuestions (
        userExamClosedQuestionsId  serial not null unique,
        points int4,
        pointsMax int4,
        closedQuestionsId int4 not null,
        userExamsId int4 not null,
        primary key (userExamClosedQuestionsId)
    );

    create table userExams (
        userExamsId  serial not null unique,
        activeFrom timestamp not null,
        activeTo timestamp not null,
        examsId int4 not null,
        usersId int4 not null,
        primary key (userExamsId)
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
        usersId int4 not null,
        rolesId int4 not null,
        primary key (rolesId, usersId)
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

    alter table exams 
        add constraint FK5C74C34CE3BBC90 
        foreign key (usersId) 
        references users;

    alter table exams 
        add constraint FK5C74C34887EE4B2 
        foreign key (userGroupsId) 
        references userGroups;

    alter table testTemplates 
        add constraint FK9E09A2C7DD6FC9DC 
        foreign key (author_usersId) 
        references users;

    alter table testTemplates_closedQuestions 
        add constraint FK74DCE3C91CDF25A3 
        foreign key (closedQuestion_closedQuestionsId) 
        references closedQuestions;

    alter table testTemplates_closedQuestions 
        add constraint FK74DCE3C947E9CE1D 
        foreign key (testTemplate_testTemplatesId) 
        references testTemplates;

    alter table userExamClosedAnswers 
        add constraint FK699522FFEB4C8FFB 
        foreign key (closedAnswer_closedAnswersId) 
        references closedAnswers;

    alter table userExamClosedAnswers 
        add constraint FK699522FFF94C0CD0 
        foreign key (userExamClosedQuestionsId) 
        references userExamClosedQuestions;

    alter table userExamClosedQuestions 
        add constraint FK89AC3157845D3B50 
        foreign key (closedQuestionsId) 
        references closedQuestions;

    alter table userExamClosedQuestions 
        add constraint FK89AC315766EBE590 
        foreign key (userExamsId) 
        references userExams;

    alter table userExams 
        add constraint FK12D0172980E0DD10 
        foreign key (examsId) 
        references exams;

    alter table userExams 
        add constraint FK12D01729CE3BBC90 
        foreign key (usersId) 
        references users;

    alter table users_roles 
        add constraint FKF6CCD9C6229B8C70 
        foreign key (rolesId) 
        references roles;

    alter table users_roles 
        add constraint FKF6CCD9C6CE3BBC90 
        foreign key (usersId) 
        references users;

    alter table users_userGroups 
        add constraint FKC80EA016CE3BBC90 
        foreign key (usersId) 
        references users;

    alter table users_userGroups 
        add constraint FKC80EA016887EE4B2 
        foreign key (userGroupsId) 
        references userGroups;
