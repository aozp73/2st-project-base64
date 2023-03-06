create table user_tb(
    id int auto_increment primary key,
    username varchar not null unique,
    password varchar not null,
    salt varchar not null,
    email varchar not null,
    address varchar,
    detail_address varchar,
    tel varchar,
    profile varchar,
    role varchar,
    created_at timestamp not null
);

create table company_tb(
    user_id int not null unique,
    company_name varchar not null,
    company_scale varchar,
    company_numb bigint not null,
    company_field varchar
);

create table employee_tb(
    user_id int not null unique,
    real_name varchar,
    education varchar,
    career int
);

create table resume_tb(
	id int auto_increment primary key,
	user_id int not null,
    title varchar not null,
    content longtext,
    created_at timestamp not null
);

create table board_tb(
    id int auto_increment primary key,
    user_id int,
    title varchar,
    content longtext,
    career int not null,
    job_type varchar,
    education varchar,
    favor varchar,
    deadline varchar,
    created_at timestamp not null
);

create table apply_tb(
    id int auto_increment primary key,
    user_id int not null,
    board_id int not null,
    resume_id int not null,
    state int not null,
    created_at timestamp not null
);

create table skill_tb(
    skill_code int primary key,
    skill_name varchar unique
);

create table employeetech_tb(
    user_id int,
    skill int
);

create table boardtech_tb(
    board_id int,
    skill int
);

create table custom_board_tb(
    id int auto_increment primary key,
    user_id int,
    title varchar not null,
    content varchar not null,
    created_at timestamp not null
);

create table love_tb(
    id int auto_increment primary key,
    board_id int not null,
    user_id int not null,
    created_at timestamp not null
);