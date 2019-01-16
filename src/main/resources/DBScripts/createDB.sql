create table language
(
  id       int auto_increment
    primary key,
  language varchar(10) not null,
  country  varchar(10) not null
);

create table themes
(
  id            int auto_increment
    primary key,
  course_name   varchar(30) not null,
  theme_name    varchar(30) not null,
  time          int         not null,
  passing_grade int         not null
);

create table questions
(
  id       int auto_increment
    primary key,
  theme_id int null,
  constraint theme__fk
  foreign key (theme_id) references themes (id)
    on update cascade
    on delete cascade
);

create table question_translate
(
  id             int auto_increment
    primary key,
  lang_id        int          null,
  questionId     int          null,
  question       varchar(300) not null,
  option_1       varchar(300) not null,
  option_2       varchar(300) not null,
  option_3       varchar(300) not null,
  option_4       varchar(300) not null,
  correct_option varchar(300) not null,
  constraint language_fk
  foreign key (lang_id) references language (id),
  constraint question_translate_questions_id_fk
  foreign key (questionId) references questions (id)
    on update cascade
    on delete cascade
);

create index questionId_fk_idx
  on question_translate (questionId);

create index themes_courses_id_fk
  on themes (course_name);

create table user
(
  id         int auto_increment
    primary key,
  first_name varchar(45)            null,
  last_name  varchar(45)            null,
  email      varchar(45)            not null,
  password   varchar(100)           not null,
  salt       varbinary(30)          not null,
  role       enum ('USER', 'ADMIN') not null
);

create table test
(
  id         int auto_increment
    primary key,
  user_id    int                       not null,
  theme_id   int                       not null,
  status     enum ('PASSED', 'FAILED') not null,
  grade      int                       not null,
  start_time varchar(20)               not null,
  end_time   varchar(20)               not null,
  test_time  varchar(20)               not null,
  date       varchar(20)               not null,
  constraint theme_fk
  foreign key (theme_id) references themes (id)
    on update cascade
    on delete cascade,
  constraint user_fk
  foreign key (user_id) references user (id)
    on update cascade
    on delete cascade
);

create table answers
(
  id             int auto_increment
    primary key,
  test_id        int                           not null,
  question       varchar(300)                  not null,
  correct_answer varchar(300)                  not null,
  user_answer    varchar(300)                  not null,
  answer_status  enum ('Incorrect', 'Correct') not null,
  constraint test___fk
  foreign key (test_id) references test (id)
    on update cascade
    on delete cascade
);


