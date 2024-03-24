drop database if exists assign2_students;

create database assign2_students;

use assign2_students;

create table students (
    id int auto_increment not null primary key ,
    name char(30) not null ,
    gender char(10) not null check ( gender in ('male', 'female') ),
    birthday date not null default '2000-01-01'
);

insert into students (name, gender, birthday) value ('John Smith', 'male', '2000-03-15');
insert into students (name, gender, birthday) value ('Emily Johnson', 'female', '2000-07-22');
insert into students (name, gender, birthday) value ('Michael Brown', 'male', '2000-11-09');
insert into students (name, gender, birthday) value ('Sarah Davis', 'female', '2000-02-18');
insert into students (name, gender, birthday) value ('David Miller', 'male', '2000-05-04');
insert into students (name, gender, birthday) value ('Jennifer Wilson', 'female', '2000-12-21');
insert into students (name, gender, birthday) value ('James Martinez', 'male', '2000-08-13');
insert into students (name, gender, birthday) value ('Patricia Anderson', 'female', '2000-09-30');
insert into students (name, gender, birthday) value ('Robert Thompson', 'male', '2000-06-17');
insert into students (name, gender, birthday) value ('Lisa Garcia', 'female', '2000-04-26');
