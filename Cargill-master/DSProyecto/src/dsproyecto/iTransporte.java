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
 * @author BadLi
 */
public interface iTransporte {
    Date fechaEnvio = null;
    String estado ="";
    Date fechaLLegada = null;
    Repartidor repartidor =null;
    iEstablecimiento establecimiento=null;
    
String getDetalles();
String getDestino();
String getOrigen();
ArrayList<Articulo> getCarga();

}
