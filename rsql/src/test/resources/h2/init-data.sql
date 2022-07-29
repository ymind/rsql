-- @formatter:off
create table "T_DEPARTMENT"
(
    "ID"        int          not null primary key auto_increment,
    "NAME"      varchar(128) not null,
    "LOCATION"  varchar(128) not null,
    "MIXED-CASE" varchar(128)
);

create table "T_EMPLOYEE"
(
    "ID"            int          not null primary key auto_increment,
    "NAME"          varchar(128) not null,
    "JOB"           varchar(128) not null,
    "MANAGER_ID"    int null,
    "HIRE_DATE"     datetime     not null,
    "SALARY"        bigint       not null,
    "DEPARTMENT_ID" int          not null
);

insert into "T_DEPARTMENT"("NAME", "LOCATION") values ('tech', 'Guangzhou');
insert into "T_DEPARTMENT"("NAME", "LOCATION") values ('finance', 'Beijing');

insert into "T_EMPLOYEE"("NAME", "JOB", "MANAGER_ID", "HIRE_DATE", "SALARY", "DEPARTMENT_ID") values ('vince', 'engineer', null, '2018-01-01 12:38:59', 100, 1);
insert into "T_EMPLOYEE"("NAME", "JOB", "MANAGER_ID", "HIRE_DATE", "SALARY", "DEPARTMENT_ID") values ('marry', 'trainee', 1, '2019-01-01 12:38:59', 50, 1);
insert into "T_EMPLOYEE"("NAME", "JOB", "MANAGER_ID", "HIRE_DATE", "SALARY", "DEPARTMENT_ID") values ('tom', 'director', null, '2018-01-01 12:38:59', 200, 2);
insert into "T_EMPLOYEE"("NAME", "JOB", "MANAGER_ID", "HIRE_DATE", "SALARY", "DEPARTMENT_ID") values ('penny', 'assistant', 3, '2019-01-01 12:38:59', 100, 2);
