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
 * @author Eduardo
 */

@ManagedBean(name = "examenMB")
@ViewScoped

public class Examenp {
    //DATOS QUE TENDRÁ LA CONSULTA QUE SE MOSTRARÁ EN EXÁMENES
    private int idOrden;
    private int id;
    private int idpersonaa;
    private String nombrePaciente;
    private int idcategoriaexam;    
    String nombreExamen="";
    String fechaRegistro="";
    String fechaEntregaResult="";
    String Observaciones="";
    float precioUnit;
    
    //TIPO DE DATO ERRONEO, BORRAR LUEGO
    String idpersona="";
    
    String categoriaexamen="";    
    String idexamenes="";
    String Precio="";    

    public Examenp() {
    }

    public Examenp(int id,String nombreExamen, String idpersona, String categoriaexamen,String Observaciones, String Precio) {
        this.id= id;
        this.nombreExamen = nombreExamen;
        this.idpersona = idpersona;
        this.categoriaexamen= categoriaexamen;
        this.Observaciones = Observaciones;
        this.Precio = Precio;
    }

    public Examenp(String nombreExamen, String idpersona, String categoriaexamen, String Observaciones, String Precio) {
        this.nombreExamen = nombreExamen;
        this.idpersona = idpersona;
        this.categoriaexamen= categoriaexamen;
        this.Observaciones = Observaciones;
        this.Precio = Precio;
    }
    //CONSTRUCTOR PARA MOSTRAR DATOS EN SELECT
    public Examenp(int idOrden, int idexamen, int idpersona, String nombrePaciente, int idcategoriaexam, String nombreExamen, 
            String Observaciones, float precioUnit, String fechaRegistro, String fechaEntregaResult){
        this.idOrden= idOrden;
        this.id= idexamen;
        this.idpersonaa= idpersona;
        this.nombrePaciente=nombrePaciente;
        this.idcategoriaexam = idcategoriaexam;
        this.nombreExamen=nombreExamen;
        this.Observaciones=Observaciones;
        this.precioUnit=precioUnit;
        this.fechaRegistro =fechaRegistro;
        this.fechaEntregaResult=fechaEntregaResult;
    }

    public int getIdOrden() {
        return idOrden;
    }

    public void setIdOrden(int idOrden) {
        this.idOrden = idOrden;
    }

    public float getPrecioUnit() {
        return precioUnit;
    }

    public void setPrecioUnit(float precioUnit) {
        this.precioUnit = precioUnit;
    }

    public String getNombrePaciente() {
        return nombrePaciente;
    }

    public void setNombrePaciente(String nombrePaciente) {
        this.nombrePaciente = nombrePaciente;
    }

    public int getIdpersonaa() {
        return idpersonaa;
    }

    public void setIdpersonaa(int idpersonaa) {
        this.idpersonaa = idpersonaa;
    }

    public int getIdcategoriaexam() {
        return idcategoriaexam;
    }

    public void setIdcategoriaexam(int idcategoriaexam) {
        this.idcategoriaexam = idcategoriaexam;
    }

    public String getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(String fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getFechaEntregaResult() {
        return fechaEntregaResult;
    }

    public void setFechaEntregaResult(String fechaEntregaResult) {
        this.fechaEntregaResult = fechaEntregaResult;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreExamen() {
        return nombreExamen;
    }

    public void setNombreExamen(String nombreExamen) {
        this.nombreExamen = nombreExamen;
    }

    public String getIdpersona() {
        return idpersona;
    }

    public void setIdpersona(String idpersona) {
        this.idpersona = idpersona;
    }

    public String getCategoriaexamen() {
        return categoriaexamen;
    }

    public void setCategoriaexamen(String categoriaexamen) {
        this.categoriaexamen = categoriaexamen;
    }

    public String getObservaciones() {
        return Observaciones;
    }

    public void setObservaciones(String Observaciones) {
        this.Observaciones = Observaciones;
    }

    public String getIdexamenes() {
        return idexamenes;
    }

    public void setIdexamenes(String idexamenes) {
        this.idexamenes = idexamenes;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String Precio) {
        this.Precio = Precio;
    }
    
    public long insertExamenes(long orden, int idcate, String observacion, float precioUnit) throws Exception {
        String sentence = "INSERT INTO public.examenes(" +
"	idorden, idcategoriaexam, observacion, \"precioUnit\" )" +
"	VALUES ( "+orden+", "+idcate+", '"+observacion+"', "+precioUnit+" );";
        System.out.println(sentence);
        Conexion conexion = new Conexion();
        return conexion.insertarExamenes(sentence);
    }
    
    public long deleteExamenes(int idExa) throws Exception {
        String sentence = "DELETE FROM public.examenes where idexamen = "+idExa+" ;";
        System.out.println(sentence);
        Conexion conexion = new Conexion();
        return conexion.deletePerson(sentence);
    }
    
    public long updateExamenes() throws Exception {
        System.out.println("en UPDATE DE Examenp");
        String sentence = "UPDATE public.examenes SET nombreExamen = '"+nombreExamen+"' ,idpersona= '"+idpersona+"', categoriaexamen= '"+categoriaexamen+"', Observaciones= '"+
                Observaciones+"', Precio= '"+Precio+"' WHERE idexamenes= "+id+" ;";       
        System.out.println(sentence);
        Conexion conexion = new Conexion();
        return conexion.insertarPerson(sentence);
    }
    
    public List<Examenp> selectExamenes() throws Exception{
        List<Examenp> lista= new ArrayList<>();
        
        Conexion conn = new Conexion(); /*revisar si este constructor esrta inicializado*/
        ResultSet rs = conn.select("select * from examenes;");
        while(rs.next()){
            lista.add(new Examenp(
                    rs.getInt("id"),
                    rs.getString("nombreExamen"),
                    rs.getString("idpersona"),
                    rs.getString("categoriaexamen"),
                    rs.getString("Observaciones"),
                    rs.getString("Precio")
            ));
            
        }
        return lista;
    }
}