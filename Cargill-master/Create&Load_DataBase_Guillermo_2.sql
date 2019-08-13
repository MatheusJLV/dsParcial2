drop database if exists proyectomedina;
create database proyectomedina;
use proyectomedina;
create table Juridico (
Cod_Cliente int auto_increment ,
RUC varchar(45) not null,
Nom varchar(45) not null,
ape char(45) not null, 
tel varchar (45) not null,
Correo varchar(45) , 
Direc varchar(45) not null,
Raz_soc varchar(45) not null,
limit_credi char(30) not null,
constraint ruc unique (RUC),
constraint limite check (limit_credi ="tiene credito" or limit_credi = "no tiene credito"),
primary key (Cod_Cliente));
create table PNatural (
Cod_Cliente int auto_increment,
Cedula varchar(45) not null, 
Nom varchar(45) not null,
Ape varchar(45) not null,
Tel varchar(45) not null,
Correo varchar(45) ,
Direc varchar(45) not null ,
limit_credi char(30) not null,
constraint cedula Unique(Cedula),
constraint limite2 check (limit_credi ="tiene credito" or limit_credi = "no tiene credito"),
primary key (Cod_Cliente));
create table Gerente (
id_gerente int auto_increment,
 Nom varchar(45) not null, 
 Ape varchar(45) not null,
 tel varchar(45) not null,
 Correo varchar(45) not null,
 direc varchar(45) not null,
primary key (id_gerente));
Create table Proveedores (
id_prov int auto_increment,
Nom varchar(45) not null,
Ape varchar(45) not null, 
Telefono varchar(45),
 primary key (id_prov));
create table Servicio_Cliente (
id_serv int auto_increment,
Nom varchar (45) not null, 
Ape varchar (45) not null,
Direc varchar(45) not null,
Corr varchar (45) not null,
Tel varchar(45) not null,
primary key(id_serv));
Create table Pedido (
id_Ped int auto_increment,
id_clietemp int default 0,
id_clietper int default 0,
Fecha_inicio date,
id_serv int,
primary key(id_Ped),
foreign key(id_serv) references Servicio_Cliente(id_serv),
foreign key(id_clietemp) references Juridico(Cod_Cliente),
foreign key(id_clietper) references PNatural(Cod_Cliente));
Create table Producto (
id_pro int auto_increment,
id_gerente int,
Nom varchar(45) not null,
Recom varchar(200) not null,
Pes_net float not null,
Costo float not null,
Alma_trans varchar(200) not null,
Adv varchar(200) not null,
Fecha_Validacion date,
primary key(id_pro),
foreign key (id_gerente) references Gerente(id_gerente));
Create table Analisis (
id_ana int auto_increment,
id_pro int, Protporcent float not null default "0",
Grasprocent float not null default "0", 
Fibporcen float not null default "0",
Humporcent float not null default "0",
Cenporcent float not null default "0",
primary key (id_ana),
 foreign key(id_pro) references Producto(id_pro));
create table Factura (
No_fact int auto_increment,
 id_ped int,
 id_cljuri int default 0,
 id_pernat int default 0,
 Fecha_Emi date not null,
 RUC varchar(45)  default "1792548861001",
Subtotal float default 0,
Tot_desc float default 0,
iva12 float default 0.12,
Valortot float default 0,
primary key(No_fact),
foreign key (id_ped) references Pedido(id_Ped),
foreign key (id_cljuri) references Juridico(Cod_Cliente),
foreign key (id_pernat) references Pnatural(Cod_Cliente));
create table Materia_Prima (
id_mat int auto_increment,
Nom varchar(45) not null,
Cantidad int,
Peso_Kg float,
Volumen_m3 float,
primary key (id_mat));
create table Produccion (
id_produ int auto_increment,
id_pro int,
id_mat int,
Fecha_elab date,
Fecha_exp date,
primary key (id_produ),
foreign key (id_pro) references Producto(id_pro),
foreign key (id_mat) references Materia_Prima(id_mat));
create table Recoleccion (
id_rec int auto_increment,
id_mat int,
id_prov int,
fecha_entrega date not null,
primary key (id_rec),
foreign key (id_mat) references Materia_Prima(id_mat),
foreign key (id_prov) references Proveedores(id_prov));
create table DetallexPedido (
id_deta int auto_increment,
id_ped int,
id_pro int,
Detalle int default 0,
primary key(id_deta),
foreign key (id_ped) references Pedido(id_Ped),
foreign key (id_pro) references Producto (id_pro));


