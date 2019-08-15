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
public abstract class Persona {
    //EN LOS SETTERS IMPLEMENTAR LOS UPDATES A LA BD
    String nombre;
    int telefono;
    String direccion;
    int cedula;
    String Correo;

    public String getNombre() {
        return nombre;
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCedula() {
        return cedula;
    }

    public String getCorreo() {
        return Correo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public void setCorreo(String Correo) {
        this.Correo = Correo;
    }
    
    
    
}
