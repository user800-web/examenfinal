/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.faces.application.FacesMessage;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 *
 * @author Karla
 */
public class Conexion {
     public Connection connecion;
    Statement statement;
    ResultSet resultSet;
    private String mensaje;
    private FacesMessage.Severity tipoMensaje;
    private boolean estado;
    
    public Conexion() throws Exception {
//        connecion = null;
//        statement = null;
//        try{
//        Class.forName("org.postgresql.Driver");
//        System.out.println("SE encontro driver");
//        }catch(Exception e){
//            System.out.println("No encontro driver");
//        }
//        try{
//            String url = "jdbc:postgresql://localhost:5432/laboratorioappweb";
//            connecion = DriverManager.getConnection(url, "postgres", "admin");
//            
//            
//        }catch(Exception ee){
//            System.out.println("Error en CONSTRUCTOR CONEXION"+ee.toString());
//        }
//        if (connecion != null) {
//            statement = connecion.createStatement();
//            System.out.println("Conexión a base de datos  ... Ok");
//        }
    }
    
    public Connection getConnecion() {
        try{
            Class.forName("org.postgresql.Driver"); 
        }catch(Exception e){
            System.out.println("No encontro driver");
        }
        try{
            String url = "jdbc:postgresql://localhost:5432/laboratorioappweb";
            connecion = DriverManager.getConnection(url, "postgres", "admin");
            
            
        }catch(Exception ee){
            System.out.println("Error en getConnecion()"+ee.toString());
        }
        return connecion;
    }
    public boolean abrirConexion() throws SQLException {
        try {
            if (connecion == null || !(connecion.isClosed())) {
                //System.out.println(mensaje+ " si abre la conexion");
                Class.forName("org.postgresql.Driver"); 
                String url = "jdbc:postgresql://localhost:5432/laboratorioappweb";
            connecion = DriverManager.getConnection(url, "postgres", "admin");
                statement = connecion.createStatement();
                estado = true;
            }
        } catch (ClassNotFoundException | SQLException exSQL) {
            mensaje = exSQL.getMessage();
            System.out.println(mensaje + " no abre la conexion");
            tipoMensaje = FacesMessage.SEVERITY_FATAL;
            return false;
        }
        return true;
    }
    
    public ResultSet select(String sentence) throws SQLException {
        resultSet = statement.executeQuery(sentence);

        return resultSet;
    }
    
    public long insertarPerson(String sentence) throws Exception {
        int retorno = -1;
        System.out.println("EN INSERTAR PERSONA/ ACTUALIZAR");
        try{
            if (abrirConexion()) {
                retorno = statement.executeUpdate(sentence);
            }        
        }catch(Exception ee){
            System.out.println("NO INSERTO"+ee.toString());
        } finally {
            cerrarConexion();
        }
        return retorno;
    }
    
    public long insertarOrden(String sentence) throws Exception {
        int retorno = -1;
        System.out.println("EN INSERTAR ORDEN");
        try{
            if (abrirConexion()) {
                retorno = statement.executeUpdate(sentence);
            }        
        }catch(Exception ee){
            System.out.println("NO INSERTO"+ee.toString());
        } finally {
            cerrarConexion();
        }
        return retorno;
    }
    
    public long insertarExamenes(String sentence) throws Exception {
        int retorno = -1;
        System.out.println("EN INSERTAR EXAMEN/ ACTUALIZAR");
        try{
            if (abrirConexion()) {
                retorno = statement.executeUpdate(sentence);
            }        
        }catch(Exception ee){
            System.out.println("NO SE INSERTO"+ee.toString());
        } finally {
            cerrarConexion();
        }
        return retorno;
    }
    
    public long deleteExamenes(String sentence) throws Exception {
        int retorno = -1;
        System.out.println("EN BORRAR EXAMEN");
        try{
            if (abrirConexion()) {
                retorno = statement.executeUpdate(sentence);
            }        
        }catch(Exception ee){
            System.out.println("NO SE BORRO"+ee.toString());
        } finally {
            cerrarConexion();
        }
        return retorno;
    }
    
    public long deletePerson(String sentence) throws Exception {
        int retorno = -1;
        System.out.println("EN DELETE PERSONA");
        try{
            if (abrirConexion()) {
                retorno = statement.executeUpdate(sentence);
            }        
        }catch(Exception ee){
            System.out.println("NO elimino"+ee.toString());
        } finally {
            cerrarConexion();
        }
        return retorno;
    }
    
    public int ejecutar(String sql) {
        int retorno = -1;
        try {
            if (abrirConexion()) {
                retorno = statement.executeUpdate(sql);
                mensaje = "Se guardó correctamente : ";
                tipoMensaje = FacesMessage.SEVERITY_INFO;
            }
        } catch (SQLException exc) {
            System.out.println(sql);
            mensaje = exc.getMessage();
            tipoMensaje = FacesMessage.SEVERITY_FATAL;
            System.out.println(mensaje);
        } finally {
            cerrarConexion();
        }
        return retorno;
    }
    
    public String obtenerValor(String consulta, int indx) {
        String valor = "";
        try {
            if (abrirConexion()) {
                statement = connecion.createStatement();
                resultSet = statement.executeQuery(consulta);
                if (resultSet.next()) {
                    valor = resultSet.getString(indx);
                }
                cerrarConexion();
                mensaje = "Se insertó correctamente : ";
                tipoMensaje = FacesMessage.SEVERITY_INFO;
            }
        } catch (SQLException exc) {
            System.out.println(consulta);
            mensaje = exc.getMessage();
            tipoMensaje = FacesMessage.SEVERITY_FATAL;
            System.out.println(mensaje);

        } finally {
            cerrarConexion();
        }
        return valor;
    }
    
    public void cerrarConexion() {
        try {
            if (connecion != null && !connecion.isClosed()) {
                connecion.close();
                connecion = null;
            }
            if (statement != null && !statement.isClosed()) {
                statement.close();
                statement = null;
            }
            if (resultSet != null && !resultSet.isClosed()) {
                resultSet.close();
                resultSet = null;
            }
        } catch (SQLException e) {
             mensaje = e.getMessage();
            tipoMensaje = FacesMessage.SEVERITY_FATAL;
            System.out.println("ERROR: " + mensaje);
        }
    }
}
