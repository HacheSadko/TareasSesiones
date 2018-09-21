drop database if exists usuarios;
create database usuarios;
use usuarios;
create table usuarios(
nombre     varchar(20),
apellido   varchar(20)
);
insert into usuarios values('pablo','rosas');
describe datitos;
select * from datitos;
select * from decontacto;