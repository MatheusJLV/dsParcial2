/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproyecto;

/**
 *
 * @author medin
 */
public class Tarjeta implements MetodoPago{
    int numero,cvv;
    String Dueño;

    @Override
    public boolean validar(float cantidad) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
