drop database if exists DisenoSoft;

create DataBase DisenoSoft;
use DisenoSoft;

create table Permiso(
id int primary key AUTO_INCREMENT,
permiso char(15)
);
create table Usuario(
id int  primary key ,
nombre char(30),
telefono int(10),
direccion char(30),
mail char(30),
username char(15),
userpassword char(15),
cargo char(10)
);
create table TablaPermiso(
id int primary key AUTO_INCREMENT,
idPermiso int,
idUsuario int,

foreign key (idPermiso) references Permiso(id),
foreign key (idUsuario) references Usuario(id)

);
create table Establecimiento(
id int primary key AUTO_INCREMENT,
direccion char(30),
telefono int(10)
);
create table Bodega_(
id int primary key AUTO_INCREMENT,
idJefe int,
idEstablecimiento int,

foreign key (idJefe) references Usuario(id),
foreign key (idEstablecimiento) references Establecimiento(id)

);
create table Local_(
id int primary key AUTO_INCREMENT,
idEstablecimiento int,
tipo char(10),

foreign key (idEstablecimiento) references Establecimiento(id)

);

create table LocalidadPersonal(
id int primary key AUTO_INCREMENT,
idUsuario int,
idEstablecimiento int,

foreign key (idUsuario) references Usuario(id),
foreign key (idEstablecimiento) references Establecimiento(id)

);
create table Envio(
id int primary key AUTO_INCREMENT,
idJefe int,
idRepartidor int,
idEstablecimiento int,
fecha char(10),
hora char(5),
estado char(10),
detalles char(30),

foreign key (idRepartidor) references Usuario(id),
foreign key (idJefe) references Usuario(id),
foreign key (idEstablecimiento) references Establecimiento(id)

);
create table Cliente(
id int primary key,
nombre char(30),
telefono int(10),
direccion char(30),
mail char(30)
);
create table Articulo(
id int primary key AUTO_INCREMENT,
nombre char(15),
descripcion char(40),
precioBase float
);
create table Venta(
id int primary key AUTO_INCREMENT,
idCliente int,
idVendedor int,
metodoDePago char (15),
fecha char(10),

foreign key (idCliente) references Cliente(id),
foreign key (idVendedor) references Usuario(id)

);
create table DetalleVenta(
id int primary key AUTO_INCREMENT,
idVenta int,
idArticulo int,
cantidad int,
precio float,
descuento float,

foreign key (idArticulo) references Articulo(id),
foreign key (idVenta) references Venta(id)
);
create table TablaEnvio(
id int primary key AUTO_INCREMENT,
idVenta int,
idEnvio int,

foreign key (idVenta) references Venta(id),
foreign key (idEnvio) references Envio(id)
);
create table DetalleAbastecimiento(
id int primary key AUTO_INCREMENT,
idArticulo int,
cantidad int(3),
origen char(30),

foreign key (idArticulo) references Articulo(id)
);
create table Abastecimiento(
id int primary key AUTO_INCREMENT,
idEnvio int,
idDetalleAbastecimiento int,

foreign key (idDetalleAbastecimiento) references DetalleAbastecimiento(id),
foreign key (idEnvio) references Envio(id)
);


create table Cotizacion(
id int primary key AUTO_INCREMENT,
fecha char(10),
idCliente int,
idVendedor int,
detalle char(40),

foreign key (idCliente) references Cliente(id),
foreign key (idVendedor) references Usuario(id)
);

create table DetalleCotizacion(
id int primary key AUTO_INCREMENT,
idArticulo int,
idCotizacion int,
cantidad int,
precio float,

foreign key (idArticulo) references Articulo(id),
foreign key (idCotizacion) references Cotizacion(id)
);
/*id int primary key,
nombre varchar(30),
telefono int(10),
direccion varchar(30),
mail varchar(30),
username varchar(15),
userpassword varchar(15),
cargo varchar(10)
*/
insert into usuario values(0830864249,"Andres M",0986404042,"Cdla el rio ","molest@hotmail.com","amol","1234","Gerente");
insert into usuario values(0930101449,"Jordy Medina ",0986404042,"Cdla Orquideas","medinajordy@hotmail.com","jlmedina","1234","Vendedor");
insert into usuario values(0987654321,"Mathews ",0989999999,"Cdla Alborada","mathewsl@hotmail.com","matlopez","1234","Jefe");
insert into permiso values(1,"Inventario");
insert into permiso values(2,"Ventas");
insert into permiso values(3,"Envios");
insert into permiso values(4,"Personal");
insert into TablaPermiso values(1,1,0987654321);
insert into TablaPermiso values(2,3,0987654321);
insert into TablaPermiso values(3,4,0930101449);
delete from tablapermiso where idPermiso=1 and idUsuario =0987654321;
insert into cliente values(0999999998,"Antonio",0987654320,"Alborada X","Antonio@hotmail.com");
insert into cliente values(0999999999,"Juanito",0987654321,"Alborada","juanito@hotmail.com");
insert into establecimiento values (1,"Alborada X", 042898450);
insert into establecimiento values (2,"Sauces 6", 042238470);
insert into articulo(nombre,descripcion,PrecioBase) values ("laptop","buen estado", 5.50);
insert into articulo(nombre,descripcion,PrecioBase) values ("PC","DECENTE estado", 55.50);
insert into venta(idCliente,idVendedor,metodoDePago) values (0999999998,0987654321,"efectivo");
insert into detalleventa(idVenta,idArticulo,cantidad, precio) values(1,1,2,10);
insert into detalleventa(idVenta,idArticulo,cantidad, precio) values(1,2,3,5);
insert into establecimiento(direccion,telefono) values("acuarela del rio",2217081);
insert into envio(idJefe,idRepartidor,idEstablecimiento,estado,detalles) values(0987654321,0930101449,1,"Pendiente","entrega fragil");
select j.nombre, r.nombre, e.estado, e.detalles, l.direccion from envio e, usuario j, usuario r, establecimiento l where e.idJefe=j.id and e.idRepartidor = r.id and e.idEstablecimiento=l.id;
-- insert into cliente values (0923101456,"Alfred Coloma",0986405042,"Samanes","AlfredColo@hotmail.com","alcoloma","1234","Jefe");

-- UPDATE Usuario SET nombre="Andy", telefono=123456789, direccion= "casita", mail= "mail@hotmail", username= "wolfpaws", userpassword= "passwordasw", cargo= "politico" WHERE id=830864249;

select u.id, nombre, permiso from usuario u, tablapermiso t, permiso p where u.id=t.idUsuario and p.id=t.idPermiso;
select * from TablaPermiso;
select * from permiso;
select * from usuario;
select * from articulo;
select * from cliente;
select u.nombre as vendedor, c.nombre as cliente, v.fecha, sum(d.cantidad*d.precio) as total from usuario u,venta v ,detalleventa d,cliente c where v.idCliente= c.id and v.idVendedor=u.id and d.idventa=v.id group by v.id;