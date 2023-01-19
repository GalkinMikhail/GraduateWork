drop table if exists film cascade;
drop table if exists question cascade;
drop table if exists roles cascade;
drop table if exists survey cascade;
drop table if exists users cascade;

create table users
(
    id BIGINT not null AUTO_INCREMENT,
    username varchar(255),
    password varchar(255),
    primary key (id)
);
alter table users
    add constraint uk_username unique (username);

create table roles
(
    id BIGINT not null AUTO_INCREMENT,
    name varchar(255),
    primary key (id)
);
alter table roles
    add constraint uk_name unique (name);


create table users_roles
(
    user_id BIGINT not null,
    role_id BIGINT not null default 1
);
alter table users_roles
    add constraint uk_user_id unique (user_id);

create table film
(
    id BIGINT not null AUTO_INCREMENT,
    title varchar(500),
    description TEXT(10000),
    release_date DATE,
    country varchar(255),
    image TEXT(10000),
    primary key (id)
);

create table genre
(
    id BIGINT not null AUTO_INCREMENT,
    name varchar(255),
    primary key (id)
);

create table question
(
    id BIGINT not null AUTO_INCREMENT,
    question varchar(255),
    primary key (id)
);

create table survey
(
    id BIGINT not null AUTO_INCREMENT,
    title varchar(500),
    primary key (id)
);

create table film_genre
(
    film_id BIGINT not null,
    genre_id BIGINT not null
);

create table survey_questions
(
    survey_id BIGINT not null,
    question_id BIGINT not null
);

create table users_recommendations_films
(
    user_id BIGINT not null,
    recommendations_films_id BIGINT not null
);

create table users_watched_films
(
    user_id BIGINT not null,
    watched_films_id BIGINT not null
);

alter table users_roles
    add constraint FKa62j07k5mhgifpp955h37ponj foreign key (role_id) references roles(id);
alter table users_roles
    add constraint FK2o0jvgh89lemvvo17cbqvdxaa foreign key (user_id) references users(id);


alter table film_genre
    add constraint FKd4b34b812xlb3nxh9b9m021dk foreign key (genre_id) references genre(id);
alter table film_genre
    add constraint FKe3a6pfgbc4cglfjg7216egpig foreign key (film_id) references film(id);

alter table survey_questions
    add constraint FK6d3brrwy6141fd7375ux6udnx foreign key (survey_id) references survey(id);
alter table survey_questions
    add constraint FK1sx45629iw4nm4vnufjjlr5sm foreign key (question_id) references question(id);

alter table users_recommendations_films
    add constraint FK4jlbv08ikgn6qmxj3ip8upaed foreign key (user_id) references users(id);
alter table users_recommendations_films
    add constraint FKefeban27925tlodtpp6yqdxeh foreign key (recommendations_films_id) references film(id);

alter table users_watched_films
    add constraint FK5gqojekj40lsjjl38sjlsqm77 foreign key (user_id) references users(id);
alter table users_watched_films
    add constraint cnstrName unique (watched_films_id);
alter table users_watched_films
    drop index cnstrName;
