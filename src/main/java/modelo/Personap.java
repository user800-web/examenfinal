/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Karla
 */
@ManagedBean(name = "personaMB")
@ViewScoped
public class Personap {

    private int id = 0;
    String idpersona = "";
    String cedula = "";
    String nombres = "";
    String apellidos = "";
    String telefono = "";
    String fechanacimiento = "";
    String direccion = "";
    String email = "";
    String sexo = "";
    String usuario = "";
    String contraseña = "";
    String tipoU = "";

    public String getTipoU() {
        return tipoU;
    }

    public void setTipoU(String tipoU) {
        this.tipoU = tipoU;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechanacimiento() {
        return fechanacimiento;
    }

    public void setFechanacimiento(String fechanacimiento) {
        this.fechanacimiento = fechanacimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Personap() {
    }

    //PARA EL SELECT
    public Personap(int id, String cedula, String nombres, String apellidos, String telefono, String fechanacimiento, String direccion,
            String email, String sexo, String clave, String tipoU) {
        //this.idpersona=idpersona;
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fechanacimiento = fechanacimiento;
        this.direccion = direccion;
        this.email = email;
        this.sexo = sexo;
        this.contraseña = clave;
        this.tipoU = tipoU;
    }

    //para agregar
    public Personap(String cedula, String nombres, String apellidos, String telefono, String fechanacimiento, String direccion,
            String email, String sexo, String clave, String tipousuario) {
        //this.idpersona=idpersona;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.telefono = telefono;
        this.fechanacimiento = fechanacimiento;
        this.direccion = direccion;
        this.email = email;
        this.sexo = sexo;
        this.contraseña = clave;
        this.tipoU = tipousuario;
    }

    /*para loginDos*/
    public Personap(String usuario, String contraseña) {
        this.usuario = usuario;
        this.contraseña = contraseña;

    }

    public void print() {
        System.out.println(nombres);
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public String login(String usu, String cont) throws Exception {
        String ok = "Usuario incorrecto";
        Conexion unaConexion;
        unaConexion = new Conexion();
        unaConexion.getConnecion();
        try {
            PreparedStatement stmt = unaConexion.connecion.prepareStatement("SELECT cedula, clave FROM public.persona where cedula=? and clave=?;");
            stmt.setString(1, usu);
            stmt.setString(2, cont);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                System.out.println(rs.getString("cedula"));
                System.out.println(rs.getString("clave"));
                ok = "Usuario correcto";
            }
            unaConexion.connecion.close();
        } catch (SQLException ex) {
            Logger.getLogger(Personap.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("error conexion bd");
        }
        return ok;
    }

    public String loginDos() throws Exception {
        return "hola";
    }

    public boolean insert(Personap per) throws Exception {
        Conexion unaConexion;
        unaConexion = new Conexion();
        unaConexion.abrirConexion();
        String sentence = String.format("INSERT INTO public.persona ("
                + "cedula, nombres, apellidos, telefono, fechanacimiento, direccion, email, sexo)"
                + "VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s');", getCedula(), getNombres(), getApellidos(), getTelefono(),
                getFechanacimiento(), getDireccion(), getEmail(), getSexo());
        unaConexion.ejecutar(sentence);
        unaConexion.cerrarConexion();
        System.out.println(sentence);
        return true;
    }

    public long insert() throws Exception {
        long envio;
        String sentence = "INSERT INTO public.persona ("
                + "cedula, nombres, apellidos, telefono, fechanacimiento, direccion, email, sexo, clave, tipousuario)"
                + "VALUES ('" + cedula + "', '" + nombres + "', '" + apellidos + "', '" + telefono + "', '" + fechanacimiento + "', '" + direccion + "', '" + email
                + "' , '" + sexo + "' , '" + contraseña + "', '" + tipoU + "' );";
        System.out.println(sentence);
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        envio = conexion.insertarPerson(sentence);
        conexion.cerrarConexion();
        return envio;
    }

    public long delete() throws Exception {
        long envio;
        String sentence = "DELETE from public.persona where idpersona = " + id + " ;";
        System.out.println(sentence);
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        envio = conexion.deletePerson(sentence);
        conexion.cerrarConexion();
        return envio;
    }

    public long updateX() throws Exception {
        long envio;
        System.out.println("en UPDATE DE PERSONAP");
        String sentence = "UPDATE public.persona SET cedula = '" + cedula + "' ,nombres= '" + nombres + "', apellidos= '" + apellidos + "', telefono= '"
                + telefono + "', fechanacimiento= '" + fechanacimiento + "', direccion= '" + direccion + "', email= '" + email + "', sexo= '"
                + sexo + "' , clave='" + contraseña + "' , tipousuario = '" + tipoU + "' WHERE idpersona= " + id + " ;";
        System.out.println(sentence);
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        envio = conexion.insertarPerson(sentence);
        conexion.cerrarConexion();
        return envio;
    }

    /*SELECT DE PERSONAS*/
    public List<Personap> select() throws Exception {
        List<Personap> lista = new ArrayList<>();

        Conexion conn = new Conexion();
        /*revisar si este constructor esrta inicializado*/
        ResultSet rs = conn.select("select * from personas;");
        while (rs.next()) {
            lista.add(new Personap(
                    rs.getInt("id"),
                    rs.getString("cedula"),
                    rs.getString("nombres"),
                    rs.getString("apellidos"),
                    rs.getString("telefono"),
                    rs.getString("fechanacimiento"),
                    rs.getString("direccion"),
                    rs.getString("email"),
                    rs.getString("sexo"),
                    rs.getString("clave"),
                    rs.getString("tipousuario")
            ));
        }
        return lista;
    }
}
