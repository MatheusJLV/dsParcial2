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
public class Abastecimiento implements iTransporte{
    
    
    ArrayList<Articulo> articulos;
    String origen;
    iEstablecimiento destino;

    @Override
    public String getDetalles() {
        String str = destino +" "+ origen+"\n";
        for(Articulo art : articulos){
            str+=art.Nombre +" : "+ art.Categoria +" "+ art.Descripcion + "\n";
        }
        return str;
    }

    @Override
    public String getDestino() {
        return destino+"";
    }

    @Override
    public String getOrigen() {
        return origen;
    }

    @Override
    public ArrayList<Articulo> getCarga() {
        return articulos;
    }
    
}
