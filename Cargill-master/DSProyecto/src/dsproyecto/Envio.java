/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproyecto;

import java.util.ArrayList;

/**
 *
 * @author BadLi
 */
public class Envio implements iTransporte{
    String Id;
    Venta venta;
    String destino;

    @Override
    public String getDetalles() {
        return Id +" "+destino +" "+ venta.toString()+"\n";
    }

    @Override
    public String getDestino() {
        return destino;
    }

    @Override
    public String getOrigen() {
        return "origen";
    }

    @Override
    public ArrayList<Articulo> getCarga() {
        return new ArrayList<>();
    }
}
