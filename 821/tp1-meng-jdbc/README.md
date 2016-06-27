

drop table reservation;
drop table passager;


create table passager(
code_passager int not null GENERATED ALWAYS AS IDENTITY  (START WITH 1, INCREMENT BY 1),
nom varchar(30) not  null,
prenom varchar(30) not null,
telephone varchar(10) ,
adress varchar(50) ,
ville varchar(20),
pays varchar(20),
statut varchar(10),
constraint passager_pk primary key (code_passager)
);

create table reservation(
code_passager int  not null,
code_reservation int not null GENERATED ALWAYS AS IDENTITY  (START WITH 1, INCREMENT BY 1),
status_reservation varchar(20),
date_reservation date not null,
constraint reservation_pk primary key(code_reservation),
constraint reservation_fk foreign key (code_passager) references passager(code_passager)
);
