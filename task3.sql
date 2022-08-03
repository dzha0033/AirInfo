drop database if exists airinfo;
create database airinfo;
use airinfo;

create table airinfo(airid int primary key auto_increment,
                     airnumber varchar(10), 
                     address varchar(20),
                     airdate date
					 )ENGINE=InnoDB DEFAULT CHARSET=utf8;
                     
insert into `airinfo` values (1, '001','Beijin', '2020-11-01'), 
                             (2, '002','Hubei', '2021-11-01'),
                             (3, '003','Shanghai', '1995-09-10'),
                             (4,'004','Guangdong', '2005-11-01');