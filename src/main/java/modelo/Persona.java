/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author kevin
 */
public class Persona {
    private int id = 0;
    String nombres = "";
    String apellidos = "";
    String correo = "";
    String clave = "";    
    String cedula = "";  
    String estadocuenta;
    int iniciosesion;
    String tipousuario = "";

    public Persona() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getEstadocuenta() {
        return estadocuenta;
    }

    public void setEstadocuenta(String estadocuenta) {
        this.estadocuenta = estadocuenta;
    }

    public int getIniciosesion() {
        return iniciosesion;
    }

    public void setIniciosesion(int iniciosesion) {
        this.iniciosesion = iniciosesion;
    }

    public String getTipousuario() {
        return tipousuario;
    }

    public void setTipousuario(String tipousuario) {
        this.tipousuario = tipousuario;
    }
    
}
