DROP DATABASE IF EXISTS tv_administration;
CREATE DATABASE tv_administration;
use tv_administration;

create table suppliers(
id int auto_increment primary key,
name varchar(100) not null ,
price double not null
);


create table categories(
id int auto_increment primary key,
typeCategory varchar(255) not null,
price double not null
);

create table channels(
id int auto_increment primary key,
name varchar(100) not null unique,
price double not null,
category_id int,
constraint foreign key(category_id) references categories(id) on delete set null on update cascade
);

create table roles(
id int auto_increment primary key,
role varchar(50)
);
create table clients(
id int auto_increment primary key,
name varchar(255) not null,
password varchar(50) not null,
email varchar(50) not null,
role_id int,
constraint foreign key (role_id) references roles(id) on delete set null on update cascade
);
create table contracts(
id int auto_increment primary key,
dateSigned date not null,
deadline date not null,
supplier_id int,
client_id int,
constraint foreign key (supplier_id) references suppliers(id) on delete set null on update cascade, 
constraint foreign key (client_id) references clients(id) on delete set null on update cascade
);

create table channel_supplier(
channel_id int,
supplier_id int,
constraint foreign key (channel_id) references channels(id) on delete set null on update cascade,
constraint foreign key (supplier_id) references suppliers(id) on delete set null on update cascade
);
create table supplier_category(
supplier_id int,
category_id int,
constraint foreign key (supplier_id) references suppliers(id) on delete set null on update cascade,
constraint foreign key (category_id) references categories(id) on delete set null on update cascade
);

create table client_category(
client_id int,
category_id int,
constraint foreign key (client_id) references clients(id) on delete set null on update cascade,
constraint foreign key (category_id) references categories(id) on delete set null on update cascade
);
create table client_channel(
client_id int,
channel_id int,
constraint foreign key (client_id) references clients(id) on delete set null on update cascade,
constraint foreign key (channel_id) references channels(id) on delete set null on update cascade
);

create table contract_channel(
contract_id int,
channel_id int,
constraint foreign key (contract_id) references contracts(id) on delete set null on update cascade,
constraint foreign key (channel_id) references channels(id) on delete set null on update cascade
);
create table contract_category(
contract_id int,
category_id int,
constraint foreign key (contract_id) references contracts(id) on delete set null on update cascade,
constraint foreign key (category_id) references categories(id)on delete set null on update cascade
);


insert into roles(id, role) values (1,'admin');
insert into roles(id, role) values (2,'user');
insert into clients(name,password, email,role_id) values ('Olia Vladova','olia2303', 'o.vladova01@gmail.com', 1);
insert into clients(name,password, email,role_id) values ('Blagovesta Ivanova','bogiIvanova', 'bogi98@gmail.com', 2);

insert into suppliers(name, price) values ('TVLife', 45.90);
insert into suppliers(name, price) values ('Boreks', 49.90);
insert into categories(typeCategory, price) values ('Comedy',12.9);
insert into categories(typeCategory, price) values ('Adventure',15.0);
insert into categories(typeCategory, price) values ('Cartoons',11.60);

/*select * from suppliers;
select suppliers.name, categories.typeCategory
from suppliers join supplier_category
on suppliers.id = supplier_category.supplier_id
join categories 
on categories.id = supplier_category.category_id;*/

insert into channels(name, price, category_id) values ('bTV Comedy', 5.5 , 1);
insert into channels(name, price, category_id) values ('bTV Action', 5.5 , 2);
insert into channels(name, price, category_id) values ('Cartoon Network', 5.5 , 3);
insert into contracts(dateSigned, deadline, supplier_id,client_id) values ('2020-05-10', '2028-05-10' , 2,2);


/*select suppliers.name as supplierName, channels.name as channelName
from suppliers join channel_supplier
on suppliers.id = channel_supplier.supplier_id
join channels 
on channels.id = channel_supplier.channel_id;*/
/*select * from clients;*/
#select * from channels;
/*select * from categories;
select * from contracts;
select contracts.id as contractId, categories.typeCategory 
from contracts join contract_category
on contracts.id = contract_category.contract_id
join categories 
on categories.id = contract_category.category_id;
select contracts.id as contractId, channels.name 
from contracts join contract_channel
on contracts.id = contract_channel.contract_id
join channels
on channels.id = contract_channel.channel_id;
select clients.name as client , channels.name as channel
from clients join client_channel
on clients.id = client_channel.client_id
join channels
on channels.id = client_channel.channel_id;
select clients.name as client , categories.typeCategory as category
from clients join client_category
on clients.id = client_category.client_id
join categories
on categories.id = client_category.category_id;*/
/*insert into contracts(dateSigned,deadline,supplier_id,client_id) values (now(), '2030-05-10',2,2);
insert into contracts(dateSigned,deadline,supplier_id,client_id) values (now(), '2025-07-14',1,2);
insert into channel_supplier(supplier_id, channel_id) values (1,3);
insert into channel_supplier(supplier_id, channel_id) values (1,2);
insert into channel_supplier(supplier_id, channel_id) values (2,1);
insert into channel_supplier(supplier_id, channel_id) values (2,3);*/