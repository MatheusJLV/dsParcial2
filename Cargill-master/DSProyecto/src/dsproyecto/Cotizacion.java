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
public class Cotizacion {
    int id;
    Date fecha;
    Cliente cliente;
    Vendedor vendedor;
    ArrayList<DetalleArticulo> Articulos;
    public void  imprimir(){
        
    }
    public Venta RealizarVenta(){
        Venta venta= new Venta();
    return venta;    
    }
    
}
