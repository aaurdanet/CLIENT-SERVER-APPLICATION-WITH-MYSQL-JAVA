# SQL commands to create and populate the MySQL database for Project Two
# CNT 4714 - Summer 2024
#
# delete the database if it already exists
drop database if exists project2;

# create a new database named project2
create database project2;

# switch to the new database
use project2;

# create the schemas for the four relations in this database
create table riders (
    ridername varchar(30) not null,
    teamname varchar(20) not null,
    nationality varchar(20),
    num_pro_wins integer,
	gender varchar(1),
    primary key (ridername)
);

create table teams (
    teamname varchar(20) not null,
    bikename varchar(20) not null,
    registered_nation varchar(20),
    num_riders integer,
    manager varchar(20),
    primary key (teamname)
);

create table bikes (
    bikename varchar(20) not null,
    country_of_origin varchar(20),
    cost integer,
    primary key (bikename)
  );

create table racewinners (
    racename varchar(45) not null,
    raceyear integer,
	ridername varchar(30) not null,
    distance integer,
	winning_time varchar(8),
    primary key (racename, raceyear)
);

# populate the database tables

insert into riders values ('Andy Schleck', 'Leopard-Trek', 'Luxembourg', 35,'M');
insert into riders values ('Frank Schleck', 'Leopard-Trek','Luxembourg', 28,'M');
insert into riders values ('Fabian Cancellara', 'SaxoBank', 'Switzerland', 58,'M');
insert into riders values ('Stuart OGrady', 'CSC', 'Australia', 20,'M');
insert into riders values ('Jens Voigt', 'SaxoBank','Germany', 38,'M');
insert into riders values ('Alesandro Ballan', 'Lampre', 'Italy', 21,'M');
insert into riders values ('Tom Boonen', 'Quick-Step','Belgium', 67,'M');
insert into riders values ('Mark Cavendish', 'HTC-Columbia', 'Isle of Man', 34,'M');
insert into riders values ('Matti Breschel', 'Cofidis', 'Denmark', 12,'M');
insert into riders values ('Marianne Vos', 'WM3', 'Netherlands', 230, 'F');
insert into riders values ('Sven Nys', 'Landbouwkredit', 'Belgium', 94,'M');
insert into riders values ('Nicolas Roche', 'Cofidis', 'Ireland', 8,'M');
insert into riders values ('Alberto Contador','Astana','Spain',21,'M');
insert into riders values ('Lizzie Armistead', 'Boels-Dolmans','Great Britain', 89, 'F');
insert into riders values ('Niki Sorensen', 'SaxoBank','Norway', 32,'M');
insert into riders values ('Thor Hushovd', 'SaxoBank','Norway',29,'M');
insert into riders values ('George Hincapie', 'BMC', 'USA', 22,'M');
insert into riders values ('Mario Cipolini', 'Acqua & Sapone', 'Italy', 130,'M');
insert into riders values ('Pauline Ferrand','Gitane', 'France',45, 'F');
insert into riders values ('Elisa Borghini','Schenker','Italy',34,'F');
insert into riders values ('Zednek Stybar', 'Quick-Step', 'Czechoslovakia', 40,'M');
insert into riders values ('Johan Museeuw', 'Quick-Step', 'Belgium', 120,'M');
insert into riders values ('Ceylin del Carmen Alvarado', 'Alpecin-Fenix', 'Netherlands', 88, 'F');
insert into riders values ('Fedor den Hertog', 'Acqua & Sapone', 'Netherlands', 20,'M');
insert into riders values ('Peter Post', 'Ti-Raleigh', 'Netherlands', 150,'M');
insert into riders values ('Chris Froome','Sky','Great Britain',23,'M');
insert into riders values ('Dietrich Thurau', 'Ti-Raleigh', 'Germany', 78,'M');
insert into riders values ('Roger deVlaeminck', 'Brooklyn', 'Belgium', 134,'M');
insert into riders values ('Miguel Indurain','BMC','Spain', 76,'M');
insert into riders values ('Phillipe Gilbert','BMC','Belgium',85,'M');
insert into riders values ('Bradley Wiggins', 'Ti-Raleigh','Great Britain', 13,'M');
insert into riders values ('Robbie McKewen','Brooklyn', 'Austalia',55,'M');
insert into riders values ('Peter Sagan','Bora-Hansgrohe','Slovakia',101,'M');
insert into riders values ('Davide van der Poel', 'Jumbo-Visma', 'Alpecin', 0, 'M');
insert into riders values ('Wout van Aert', 'Jumbo-Visma','Belgium',60,'M');
insert into riders values ('Filippo Ganna','Ineos','Italy',25,'M');
insert into riders values ('Mathieu van der Poel','Alpecin','Netherlands',34,'M');
insert into riders values ('Elisa Balsamo','Trek-Segafredo','Italy',20,'F');


insert into teams values ('Quick-Step','Eddy Merckx','Belgium',24,'Patrick Lafevre');
insert into teams values ('Leopard-Trek', 'Trek','Luxembourg', 20,'Bryan Nygaard');
insert into teams values ('Acqua & Sapone', 'Eddy Merckx', 'Italy', 18,'Felice Gimondi');
insert into teams values ('UAE', 'Colnago', 'UAE', 29, 'Mauro Gianetti');
insert into teams values ('HTC-Columbia', 'Scott', 'Germany', 28, 'Scott Sunderland');
insert into teams values ('CSC', 'Cervelo','Denmark', 18,'Sven Nillsen');
insert into teams values ('Astana','Trek','Kazakhstan',19,'Johann Bryneel');
insert into teams values ('WM3','Ridley','Netherlands',15,'Mark Delora');
insert into teams values ('Molteni','Colnago','Italy',23,'Aldo Sassi');
insert into teams values ('Ti-Raleigh', 'Gios','Netherlands',35,'Rene Pijin');
insert into teams values ('Brooklyn', 'Gios', 'Italy', 22, 'Francesco Moser');
insert into teams values ('SaxoBank', 'Look', 'Germany', 23, 'Erik Zabel');
insert into teams values ('BMC', 'BMC', 'Switzerland',24,'Gerben Karstens');
insert into teams values ('Cofidis', 'Look', 'France', 22, 'Marc Sargent');
insert into teams values ('Lampre','Willier','Italy', 24, 'Guiseppe Saronni');
insert into teams values ('Landbouwkredit','Colnago', 'Belgium', 22,'Tomas Schqvre');



insert into bikes values ('Eddy Merckx','Belgium',9000);
insert into bikes values ('Trek', 'USA', 8500);
insert into bikes values ('Cervelo', 'Canada', 9000);
insert into bikes values ('Colnago','Italy',12000);
insert into bikes values ('Willier', 'Italy', 9800);
insert into bikes values ('Look', 'France', 10000);
insert into bikes values ('Gios', 'Italy', 9800);
insert into bikes values ('BMC','Switzerland',10000);
insert into bikes values ('Scott', 'Germany', 8800);
insert into bikes values ('Ridley','Belgium',10000);
insert into bikes values ('Bianchi','Italy',10000);



insert into racewinners values ('Milan-San Remo',2010,'Mark Cavendish',260, '7:23:00');
insert into racewinners values ('Paris-Roubaix', 2010,'Fabian Cancellara', 243, '6:58:12');
insert into racewinners values ('Milan-San Remo', 1972,'Eddy Merckx',252, '7:05:12');
insert into racewinners values ('Paris-Roubaix',2007,'Fabian Cancellara', 244, '7:02:13');
insert into racewinners values ('Fleche Wallone', 2004, 'Mario Cipollini', 220, '6:44:29');
insert into racewinners values ('Fleche Wallone - Feminine', 2014, 'Marianne Vos', 134, '3:22:12');
insert into racewinners values ('Paris-Roubaix', 1972, 'Roger deVlaeminck', 233, '6:54:22');
insert into racewinners values ('Tour de France', 2010, 'Alberto Contador', 3219, '73:13:54');
insert into racewinners values ('Liege-Bastogne-Leige', 2008, 'Andy Schlek', 248, '6:55:12');
insert into racewinners values ('Vuelta Espana', 2008, 'Alberto Contador', 2701, '78:14:02');
insert into racewinners values ('World Championship - Elite Women', 2021, 'Elisa Balsamo', 157, '3:52:57'); 
insert into racewinners values ('Paris-Roubaix', 2008, 'Tom Boonen', 265, '6:53:33');
insert into racewinners values ('Rund de Flandren', 2008, 'Tom Boonen', 244, '7:03:48');
insert into racewinners values ('Super Prestige - Dienem', 2023, 'Ceylin del Carmen Alvarado',25,'58:10');
insert into racewinners values ('Paris-Roubaix', 2006, 'Johan Museeuw', 254, '7:12:52');
insert into racewinners values ('Milan-San Remo', 2003, 'Mario Cipollini', 300, '7:22:19');
insert into racewinners values ('Paris-Roubaix', 1973, 'Roger deVlaeminck', 240, '7:00:26');
insert into racewinners values ('Paris-Roubaix', 1974, 'Roger deVlaeminck', 244, '7:05:47');
insert into racewinners values ('Paris Roubaix Femmes', 2021, 'Lizzie Deignan', 116, '2:56:07');
insert into racewinners values ('Fleche Wallone', 2002, 'George Hincapie', 212, '5:58:47');
insert into racewinners values ('World Cup CycloCross - Elite Women', 2024,'Ceylin del Carmen Alvarado',140,'5:23;33'); 
insert into racewinners values ('Fleche Wallone - Feminine', 2015, 'Pauline Ferrand', 145, '3:40:18');
insert into racewinners values ('Telnet Super Prestige', 2023, 'Ceylin del Carmen Alvarado', 120, '3:10:10');
insert into racewinners values ('Tour de France', 2020, 'Tadej Pogacar', 3484, '87:20:05');
insert into racewinners values ('Paris-Roubaix', 1971, 'Eddy Merckx', 245, '6:48:10');
insert into racewinners values ('Tour de France', 1970, 'Eddy Merckx', 3387, '74:04:51');
insert into racewinners values ('Tour of Netherlands', 1972, 'Fedor den Hertog', 678, '15:48:02');
insert into racewinners values ('Giro Italia', 1970,'Eddy Merckx', 3113, '82:28:44');
insert into racewinners values ('Amstel Gold', 1999, 'Robbie McKewen', 240, '6:23:14');
insert into racewinners values ('Ghent-Wevelgem', 2008, 'Johan Musseuw', 230, '6:14:52');
insert into racewinners values ('Paris-Roubaix', 1970, 'Eddy Merckx', 245, '6:48:10');
insert into racewinners values ('World Championship - Elite Women', 2015, 'Lizzie Armistead', 140, '4:10:05');
insert into racewinners values ('World Championship - Elite Women', 2019, 'Annemiek van Vlueten', 155, '4:22:14');
insert into racewinners values ('GP-E3', 2010, 'Fabian Cancellara', 210, '5:44:11');
insert into racewinners values ('Rund de Flandren', 2022, 'Mathieu van der Poel', 264, '6:18:30');
insert into racewinners values ('World Cyclocross Championships - Elite Women', 2020, 'Ceylin del Carmen Alvarado', 20, '1:10:22');
insert into racewinners values ('Paris-Roubaix', 1969, 'Eddy Merckx', 248, '6:53:16');
insert into racewinners values ('Tour de France', 2021, 'Tadej Pogacar', 3414, '82:56:36');
insert into racewinners values ('Rund de Flandren', 2009, 'Stuart OGrady', 253, '6:17:29');
insert into racewinners values ('Liege-Bastogne-Liege', 1978, 'Dietrich Thurau', 256, '7:01:00');
insert into racewinners values ('Liege-Bastogne-Liege', 1972, 'Eddy Merckx', 273, '7:11:47');




# uncomment the following 4 lines if you want to see the results of creating and populating the database
select * from riders;
select * from teams;
select * from bikes;
select * from racewinners;