

CREATE SEQUENCE users_seq;

CREATE TABLE users (
    id              bigint PRIMARY KEY DEFAULT nextval('users_seq'),
    usr_nick        varchar(40) UNIQUE NOT NULL,
    usr_name        varchar(60) NOT NULL,
    usr_email       varchar(60) UNIQUE NOT NULL
);

CREATE SEQUENCE lessons_seq;

CREATE TABLE lessons (
    id              bigint PRIMARY KEY DEFAULT nextval('lessons_seq'),
    ls_name         varchar(60) NOT NULL,
    ls_code         varchar(15) NOT NULL
);

CREATE SEQUENCE ccRequests_seq;

CREATE TABLE ccRequests (
    id                  bigint PRIMARY KEY DEFAULT nextval('ccRequests_seq'),
    usr_id              bigint NOT NULL,
    lesson_id           bigint NOT NULL,
    course_from_code    varchar(15) NOT NULL,
    status              varchar(10) NOT NULL,
    FOREIGN KEY ( usr_id ) REFERENCES users,
    FOREIGN KEY ( lesson_id ) REFERENCES lessons
);

CREATE SEQUENCE ccRequests_to_courses_seq;

CREATE TABLE ccRequests_to_courses (
    id              bigint PRIMARY KEY DEFAULT nextval('ccRequests_to_courses_seq'),
    ccReq_id        bigint NOT NULL,
    course_to_code  varchar(15) NOT NULL,
    FOREIGN KEY ( ccReq_id ) REFERENCES ccRequests
);