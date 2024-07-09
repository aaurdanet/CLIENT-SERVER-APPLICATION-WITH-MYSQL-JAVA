/* bike database script
used in jdbc notes
*/

drop database if exists bikedb;

create database bikedb;

use bikedb;

create table bikes (
    bikename varchar(30) not null,
    size int,
    color varchar(15),
    cost int(6),
    purchased date,
    mileage int(6),
    primary key (bikename)
);

insert into bikes values ('Colnago Dream Rabobank',60,'blue/orange',5500,'2002-07-07',4300);
insert into bikes values ('Bianchi Evolution 3',58,'celeste',4800,'2003-11-12',2000);
insert into bikes values ('Eddy Merckx Molteni',58,'orange',5100,'2004-08-12',0);
insert into bikes values ('Eddy Merckx Domo',58,'blue/black',5300,'2004-02-02',0);
insert into bikes values ('Battaglin Carrera',60,'red/white',4000,'2001-03-10',11200);
insert into bikes values ('Colnago V3Rs UAE',58,'black/red',10500,'2023-01-15',0);
insert into bikes values ('Gianni Motta Personal',59,'red/green',4400,'2000-05-01',8700);
insert into bikes values ('Gios Torino Super',60,'blue',2000,'1998-11-08',9000);
insert into bikes values ('Schwinn Paramount P14',60,'blue',1800,'1992-03-01',200);
insert into bikes values ('Bianchi Corse Evo 4',58,'celeste',5700,'2004-12-02',300);
insert into bikes values ('Colnago Extreme-Italia',59,'blue',9600,'2022-7-28',0);
insert into bikes values ('Colnago Superissimo',59,'red',3800,'1996-03-01',13000);
insert into bikes values ('Ridley Damocles', 58,'blue/black',7500,'2008-06-27',0);
insert into bikes values ('Bianchi Infinito', 58, 'celeste',8900,'2011-07-14', 0);
insert into bikes values ('Ridley X-Fire 2012', 58, 'red/white',7500,'2011-09-01',0);
insert into bikes values ('BMC SLC01 - Swiss',58,'red/black/white',8000,'2010-06-23',0);
insert into bikes values ('Colnago C64 - Mapei',58,'blue/white',11000,'2022-11-16',0);

select * from bikes;


create table bluebikes (
    bikename varchar(30),
    color varchar(15),
    price int,
    totalmiles int,
    primary key (bikename)
);
/* Use a form 3 insert statement to load the bluebikes table with
   some of the information on the bikes whose color is blue.
*/
insert into bluebikes
select bikename, color, cost, mileage
from bikes
where color = 'blue';

select * from bluebikes; 
