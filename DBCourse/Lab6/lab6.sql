drop database if exists OnlineShop;
create database if not exists OnlineShop;
use OnlineShop;

CREATE TABLE maker (
	company_name varchar (45) NOT NULL primary key,
	first_name varchar (45) NOT NULL,
    last_name varchar (45) NOT NULL,
    price int NOT NULL 
);

CREATE TABLE clothes (
	id int NOT NULL auto_increment primary key,
	type varchar (45) NOT NULL ,
	amount int NOT NULL ,
	color varchar (45) NOT NULL,
    size varchar (45) NOT NULL,
    FK_clothes_maker varchar (45) NOT NULL
);

CREATE TABLE customer (
	first_name varchar (45) NOT NULL,
    last_name varchar (45) NOT NULL,
    adress varchar (45) NOT NULL,
    pass_name int NOT NULL primary key,
    payment varchar (45) NOT NULL
);

CREATE TABLE clothes_has_customer (
	id int NOT NULL auto_increment primary key,
    clothes_id int,
    customer_pass_name int
);

ALTER TABLE clothes ADD 
	CONSTRAINT FK_clothes_maker FOREIGN KEY 
	(
		FK_clothes_maker
	) REFERENCES maker (
		company_name
	);

ALTER TABLE clothes_has_customer ADD 
	CONSTRAINT FK_clothes_customer FOREIGN KEY 
	(
		clothes_id
	) REFERENCES clothes (
		id
	);
    
ALTER TABLE clothes_has_customer ADD
    CONSTRAINT FK_customer_pass_name FOREIGN KEY
    (
		customer_pass_name
	) REFERENCES customer (
		pass_name
	);



insert into maker values('name1', 'Olga', 'Kryvoruchka', 1000);
insert into maker values('name2', 'Anna', 'Kryvoruchka', 936);
insert into maker values('name3', 'Anastasija', 'Kryvoruchka', 2696);
insert into maker values('name4', 'Viktorija', 'Kryvoruchka', 4896);
insert into maker values('name5', 'Anton', 'Kryvoruchka', 2986);
insert into maker values('name6', 'George', 'Kryvoruchka', 1065);
insert into maker values('name7', 'Maksym', 'Kryvoruchka', 1673);
insert into maker values('name8', 'Volodymyr', 'Kryvoruchka', 1534);
insert into maker values('name9', 'Vitalij', 'Kryvoruchka', 9476);
insert into maker values('name10', 'Roman', 'Kryvoruchka', 7625);

insert into clothes values(1, 'jeans', 45, 'blue', 'S', 'name1');
insert into clothes values(2, 'jeans', 17, 'blue', 'M', 'name1');
insert into clothes values(3, 'jeans', 4, 'orange', 'S', 'name2');
insert into clothes values(4, 't-shirt', 31, 'white', 'S', 'name5');
insert into clothes values(5, 't-shirt', 24, 'black', 'S', 'name8');
insert into clothes values(6, 'shorts', 3, 'blue', 'M', 'name10');
insert into clothes values(7, 'shorts', 8, 'blue', 'L', 'name3');
insert into clothes values(8, 'shorts', 7, 'white', 'M', 'name6');
insert into clothes values(9, 'sweater', 27, 'red', 'S', 'name6');
insert into clothes values(10, 'sweater', 45, 'blue', 'S', 'name7');

insert into customer values('Olga', 'Bruster', 'Snopkivska', 297365456, 'cash');
insert into customer values('Anastasija', 'Bruster', 'Franka', 486965456, 'card');
insert into customer values('George', 'Bruster', 'Franka', 838765456, 'cash');
insert into customer values('Vitalij', 'Bruster', 'Stryjska', 366765456, 'cash');
insert into customer values('Anna', 'Bruster', 'Franka', 293845679, 'cash');
insert into customer values('Viktorija', 'Bruster', 'Snopkivska', 534567483, 'card');
insert into customer values('Roman', 'Bruster', 'Snopkivska', 376543294, 'card');
insert into customer values('Maksym', 'Bruster', 'Stryjska', 394759954, 'card');
insert into customer values('Anton', 'Bruster', 'Bandery', 894657286, 'card');
insert into customer values('Volodymyr', 'Bruster', 'Bandery', 198745766, 'cash');

insert into clothes_has_customer values(1, 1, 297365456);
insert into clothes_has_customer values(2, 3, 297365456);
insert into clothes_has_customer values(3, 7, 297365456);
insert into clothes_has_customer values(4, 4, 838765456);
insert into clothes_has_customer values(5, 2, 838765456);
insert into clothes_has_customer values(6, 2, 198745766);
insert into clothes_has_customer values(7, 1, 394759954);
insert into clothes_has_customer values(8, 6, 293845679);
insert into clothes_has_customer values(9, 10, 293845679);
insert into clothes_has_customer values(10, 9, 366765456);