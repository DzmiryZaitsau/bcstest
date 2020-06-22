drop table if exists persons cascade;
create table  persons (
                       id int (10) AUTO_INCREMENT,
                       name varchar(20) NOT NULL,
                       PRIMARY KEY (id)
);