/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dsproyecto;

/**
 *
 * @author MatheusJLV
 */
public interface Chain {
    public abstract void setNext(Chain nextInChain); 
    //objetivo siendo el objeto que se busca (articulo, cliente, etc)
    //parametro siendo el tipo de informacion que se tiene
    //referencia siendo la informacion concreta
    public abstract void Buscar(String objetivo, String parametro, String referencia); 
    public abstract void Crear(Object o);
    public abstract void Actualizar(Object o1, Object o2);
}
