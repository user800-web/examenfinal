/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author kevin
 */
@ManagedBean(name = "persona")
@ViewScoped
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
    public Persona(int id, String cedula, String nombres, String apellidos){
        this.id=id;
        this.cedula= cedula;
        this.nombres=nombres;
        this.apellidos=apellidos;
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
    private void addMessage(FacesMessage.Severity SEVERITY_ERROR, String información, String error_al_guardar_los_datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void registro() {
        long insercion;
        String pantalla = "inicioSesion";
        String sentencia = "INSERT INTO public.personas( nombres, apellidos, correo, clave, cedula, estadocuenta, tipousuario )"
                + "VALUES ( '" + nombres + "', '" + apellidos + "', '" + correo + "', '" + clave + "', '" + 
                cedula +"' ,'Deshabilitado', 'Postulante' ); ";
        System.out.println("SENTENCIA: "+sentencia);
        try {
            Conexion con = new Conexion();
            con.abrirConexion();
            insercion = con.insertar(sentencia);
            con.cerrarConexion();
            if (insercion == 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario creado"));
                System.out.println("SE CREO EL USUARIO");
//                pantalla = "pantallaUsuario";
            }
        } catch (Exception e) {
            System.out.println("ERROR EN REALIZAR REGISTRO: " + e.toString());
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo realizar el registro"));            
        }
//        if (pantalla.equals("inicioSesion")) {
//                PrimeFaces.current().executeScript("location.reload()");
//            }
//            return pantalla;
    }
    
    public String login() throws Exception{
        String pantalla = "iniciarSesion";
        System.out.println("CORREO: "+correo+" CLAVE: "+clave);
        String sentence="SELECT correo, clave, tipousuario, estadocuenta FROM public.personas where correo= '"+correo+"' " +
" and clave='"+clave+"'; ";
        String tipoUsuario = "";        
        Conexion unaConexion = new Conexion();
        try {            
        unaConexion.abrirConexion();
            PreparedStatement stmt = unaConexion.connecion.prepareStatement(sentence);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("correo"));
                System.out.println(rs.getString("clave"));
                System.out.println(rs.getString("tipousuario"));
                tipoUsuario = rs.getString("tipousuario");
                estadocuenta= rs.getString("estadocuenta");
            }
            if (tipoUsuario.equals("Administrador")) {
                pantalla = "pantallaAdministrador";
            } else {
                if (tipoUsuario.equals("Postulante") && estadocuenta.equals("Habilitada")) {
                    pantalla = "pantallaUsuario";
                }
            }
            unaConexion.connecion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Error conexion bd: " + ex.getMessage());
            unaConexion.connecion.close();
        }
        if (pantalla.equals("iniciarSesion")) {
            PrimeFaces.current().executeScript("location.reload()");
        }
        return pantalla;
    }
}
