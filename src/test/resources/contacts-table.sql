drop table if exists contacts cascade;
create table contacts (
                       id int (10) AUTO_INCREMENT,
                       telephone varchar(20) NOT NULL,
                       id_person int(10) NOT NULL,
                       PRIMARY KEY (id)
);
