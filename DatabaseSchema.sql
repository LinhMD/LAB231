﻿create database p0014
go

use p0014
go

create table _user
(
	email nvarchar(255) primary key,
	name nvarchar(50) not null,
	password varchar(65) not null,
	role bit not null default 0,
	status bit not null default 1
)
go

insert into _user(email, name, password, role, status)
values ('LinhMD', 'Mai Đình Linh', '7568d29c54d0608467453d9bb7375f80ac48e33a6338341769bead34c538f0a6', 1, 1),
		('User', 'User', '7568d29c54d0608467453d9bb7375f80ac48e33a6338341769bead34c538f0a6', 0, 1)
									/* password : nah */
select * from _user

create table _subject 
(
	code varchar(10) primary key,
	name nvarchar(255) not null,
	time int not null,
	question_limit int not null
)
go

insert into _subject (code, name, question_limit, time)
values ('IOT101', 'Internet of Things', 10, 10),
		('SSG101', 'Working in Group Skills', 10, 10)


create table _question 
(
	id int primary key,
	question_content nvarchar(2048) not null,
	a nvarchar(255) not null,
	b nvarchar(255) not null,
	c nvarchar(255) not null,
	d nvarchar(255) not null,
	answer_correct character not null,
	subjectID varchar(10) foreign key references _subject(code),
	create_date date not null,
	status bit not null default 1
)

insert into _question (id, question_content, a, b, c, d, answer_correct, create_date, subjectID)
values (1, '1', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(2, '2', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(3, '3', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(4, '4', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(5, '5', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(6, '6', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(7, '7', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(8, '8', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(9, '9', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(10, '10', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(11, '11', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(12, '12', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(13, '13', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(14, '14', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(15, '15', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(16, '16', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(17, '17', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(18, '18', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(19, '19', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(20, '20', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(21, '21', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(22, '22', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(23, '23', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'IOT101'),
		(24, '1', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(25, '2', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(26, '3', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(27, '4', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(28, '5', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(29, '6', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(30, '7', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(31, '8', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(32, '9', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(33, '10', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(34, '11', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(35, '12', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(36, '13', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(37, '14', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(38, '15', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(39, '16', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(40, '17', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(41, '18', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(42, '19', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(43, '20', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(44, '21', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(45, '22', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101'),
		(46, '23', 'a', 'b', 'c', 'd' , 'a', '2021-05-02', 'SSG101')



create table _quiz_history
(
	id int primary key,
	quiz_taker nvarchar(255) foreign key references _user(email),
	subject varchar(10)  foreign key references _subject(code),
	time date not null,
	point float not null default 0
)

create table _quiz_detail
(
	quiz_id int not null foreign key references _quiz_history(id),
	question_id int not null foreign key references _question(id),
	answer char,
	primary key (quiz_id, question_id)
)
