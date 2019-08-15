/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproyecto;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author medin
 */
public class Venta {
    int id;
    ArrayList<MetodoPago> metodosPago;
    Date fecha,garantia;
    Cliente cliente;
    Vendedor vendedor;
    ArrayList<DetalleArticulo> Articulos;
    
    public void Venta(){
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<MetodoPago> getMetodosPago() {
        return metodosPago;
    }

    public void setMetodosPago(ArrayList<MetodoPago> metodosPago) {
        this.metodosPago = metodosPago;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getGarantia() {
        return garantia;
    }

    public void setGarantia(Date garantia) {
        this.garantia = garantia;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public ArrayList<DetalleArticulo> getArticulos() {
        return Articulos;
    }

    public void setArticulos(ArrayList<DetalleArticulo> Articulos) {
        this.Articulos = Articulos;
    }
    
    
}
