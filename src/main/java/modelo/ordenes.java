/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;

/**
 *
 * @author kevin
 */
public class ordenes {

    int id;
    int idpersona;
    int totalexamenes;
    String fecharegistro;
    String fechaentregaResult;
    float total;
    String estado;

    public ordenes() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(int idpersona) {
        this.idpersona = idpersona;
    }

    public int getTotalexamenes() {
        return totalexamenes;
    }

    public void setTotalexamenes(int totalexamenes) {
        this.totalexamenes = totalexamenes;
    }

    public String getFecharegistro() {
        return fecharegistro;
    }

    public void setFecharegistro(String fecharegistro) {
        this.fecharegistro = fecharegistro;
    }

    public String getFechaentregaResult() {
        return fechaentregaResult;
    }

    public void setFechaentregaResult(String fechaentregaResult) {
        this.fechaentregaResult = fechaentregaResult;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public long insert() throws Exception {
        long envio = 0;
        Conexion conexion = new Conexion();
        conexion.abrirConexion();
        String sentence = "INSERT INTO public.orden_examen( " +
"	idpersona, totalexamenes, fecharegistro, \"fechaentregaResult\", total) " +
"	VALUES ( '"+idpersona+"', "+totalexamenes+", '"+fecharegistro+"', '"+fechaentregaResult+"', "+total+" ) returning id;";
        System.out.println(sentence);
        Statement statement = conexion.connecion.createStatement();
        statement.executeUpdate(sentence, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = statement.getGeneratedKeys();
        if (rs != null && rs.next()) {
            envio = rs.getLong(1);
        }
        conexion.cerrarConexion();
        return envio;
    }
    public long update() throws Exception{
        String sentence = "UPDATE public.orden_examen SET idpersona= "+idpersona+", fecharegistro= '"+fecharegistro+
                "', \"fechaentregaResult\"= '"+fechaentregaResult +"' WHERE id= "+id+" ;";       
        System.out.println(sentence);
        Conexion conexion = new Conexion();
        return conexion.insertarOrden(sentence);
    }
    public long actualizarPrecio() throws Exception{
        String sentence = "UPDATE public.orden_examen SET total= "+total+" WHERE id= "+id+" ;";       
        System.out.println(sentence);
        Conexion conexion = new Conexion();
        return conexion.insertarOrden(sentence);
    }
}
