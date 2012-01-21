# postgres DB schema

CREATE SEQUENCE users_seq;

CREATE TABLE users (
    id              integer PRIMARY KEY DEFAULT nextval('users_seq'),
    usr_nick        varchar(40) NOT NULL,
    usr_name        varchar(60) NOT NULL,
    usr_email       varchar(60) NOT NULL
);

CREATE SEQUENCE lessons_seq;

CREATE TABLE lessons (
    id              integer PRIMARY KEY DEFAULT nextval('lessons_seq'),
    ls_name         varchar(60) NOT NULL,
    ls_code         varchar(15) NOT NULL
);

CREATE SEQUENCE courses_seq;

CREATE TABLE courses (
    id              integer PRIMARY KEY DEFAULT nextval('courses_seq'),
    c_code          varchar(15) NOT NULL
);

CREATE SEQUENCE lessons_courses_seq;

CREATE TABLE lessons_courses (
    id              integer PRIMARY KEY DEFAULT nextval('lessons_courses_seq'),
    lesson_id       integer NOT NULL,
    course_id       integer NOT NULL,
    FOREIGN KEY ( lesson_id ) REFERENCES lessons,
    FOREIGN KEY ( course_id ) REFERENCES courses
);

CREATE SEQUENCE ccRequests_seq;

CREATE TABLE ccRequests (
    id              integer PRIMARY KEY DEFAULT nextval('ccRequests_seq'),
    usr_id          integer NOT NULL,
    course_from_id  integer NOT NULL,
    status          varchar(10) NOT NULL,
    FOREIGN KEY ( usr_id ) REFERENCES users,
    FOREIGN KEY ( course_from_id ) REFERENCES courses
);

CREATE SEQUENCE ccRequests_to_courses_seq;

CREATE TABLE ccRequests_to_courses (
    id              integer PRIMARY KEY DEFAULT nextval('ccRequests_to_courses_seq'),
    ccReq_id        integer NOT NULL,
    course_to_id    integer NOT NULL,
    FOREIGN KEY ( ccReq_id ) REFERENCES ccRequests,
    FOREIGN KEY ( course_to_id ) REFERENCES courses
);