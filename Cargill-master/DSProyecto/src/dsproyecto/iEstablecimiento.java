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
public interface iEstablecimiento {
    String direccion = "";
    int telefono = 0;
    String tipo = "";
    ArrayList<Articulo> ArticulosDisponible = new ArrayList<>();
    
    ArrayList<Articulo> getInventario();
    ArrayList<iUsuario> getPersonal();
    int buscarItem(Articulo articulo);
    
    
}
