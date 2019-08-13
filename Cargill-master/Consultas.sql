-- 	Queries

-- 1.- Factura
-- Calcular y LLenar valores de las facturas
-- Factura tiene atributos que dependen de valores de otras tablas, por lo tanto se ejecuta este query para poder tener esos atributos Actualizados

update factura 
join pedido as p on factura.id_ped = p.id_ped join detallexpedido dxp on p.id_ped=dxp.id_ped join producto pd on dxp.id_pro=pd.id_pro
set Subtotal = dxp.Detalle*pd.Costo, Valortot= ((dxp.Detalle*pd.Costo)-factura.Tot_desc)*(1+factura.iva12)
where factura.id_ped=p.id_ped;
select*from factura;

-- lo que se muestra en la tabla del aplicativo

select factura.No_fact as NumeroFactura, factura.Fecha_Emi as FechaEmision, factura.RUC as RucCargill, factura.Valortot as ValorTotaldePago, 
juridico.RUC as RUC_Cedula, juridico.Nom as Nombre, juridico.ape as Apellido 
from factura join pedido on factura.id_ped = pedido.id_Ped
join juridico on juridico.Cod_Cliente = factura.id_cljuri
union
select factura.No_fact as NumeroFactura, factura.Fecha_Emi as FechaEmision, factura.RUC as RucCargill, factura.Valortot as ValorTotaldePago, 
pnatural.Cedula as RUC_Cedula, pnatural.Nom as Nombre, pnatural.ape as apellido
from factura join pedido on factura.id_ped = pedido.id_Ped
join pnatural on pnatural.Cod_Cliente = factura.id_pernat ;

-- 2.- Pedido
-- lo que se muestra en la tabla del aplicativo

select Detallexpedido.id_deta,Pedido.Fecha_inicio,Pedido.id_ped,pnatural.Cedula  as Ruc_Cedula,pnatural.Nom as Nombre_Cliente,pnatural.Ape as Apellido_Cliente,Detallexpedido.detalle,Producto.nom as Producto, Producto.costo as PrecioxUnidad,Pedido.id_serv
from Detallexpedido join pedido on Detallexpedido.id_ped = pedido.id_Ped
join Producto on  Detallexpedido.id_pro= Producto.id_pro join pnatural on pnatural.cod_cliente=pedido.id_clietper
union
select Detallexpedido.id_deta,Pedido.Fecha_inicio,Pedido.id_ped,juridico.Ruc  as Ruc_Cedula,Juridico.Nom as Nombre_Cliente,Juridico.Ape as Apellido_Cliente,Detallexpedido.detalle,Producto.nom as Producto, Producto.costo as PrecioxUnidad,Pedido.id_serv
from Detallexpedido join pedido on Detallexpedido.id_ped = pedido.id_Ped
join Producto on  Detallexpedido.id_pro= Producto.id_pro join juridico on juridico.cod_cliente=pedido.id_clietemp;


-- 3.- Juridico
-- lo que se muestra en la tabla del aplicativo


select * from Juridico;

-- 4.- PNatural
-- lo que se muestra en la tabla del aplicativo


select * from PNatural;


-- 5.- Producto
-- lo que se muestra en la tabla del aplicativo


select producto.id_pro, nom, pes_net, recom as Recomendacion, adv as Advertencia, protporcent, grasprocent, fibporcen, humporcent, cenporcent
from producto join analisis on producto.id_pro=analisis.id_pro ;


-- 6 estadistica
select juridico.Nom, juridico.ape, avg(factura.Valortot) as Promedio_de_Gastos	
from juridico join factura on juridico.Cod_Cliente = factura.id_cljuri
Group by juridico.Nom, juridico.ape
having Promedio_de_Gastos	> (select ((select   avg(factura.Valortot) as Promedio_de_Gastos
		from pnatural join factura on pnatural.Cod_Cliente = factura.id_pernat) +
        (Select avg(factura.Valortot) as Promedio_de_Gastos	
		from juridico join factura on juridico.Cod_Cliente = factura.id_cljuri )) as Promedio_Global)
union
select pnatural.Nom, pnatural.Ape, avg(factura.Valortot) as Promedio_de_Gastos
from pnatural join factura on pnatural.Cod_Cliente = factura.id_pernat
Group by pnatural.Nom, pnatural.Ape
having Promedio_de_Gastos > (select ((select   avg(factura.Valortot) as Promedio_de_Gastos
		from pnatural join factura on pnatural.Cod_Cliente = factura.id_pernat) +
        (Select avg(factura.Valortot) as Promedio_de_Gastos	
		from juridico join factura on juridico.Cod_Cliente = factura.id_cljuri )) as Promedio_Global);
                            
