/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import Controllers.CrudView;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;

/**
 *
 * @author Karla
 */
@ManagedBean(name = "categoriaExamenMB")
@ViewScoped
public class categoriaExamen {
    private int idcategoria;
    private String  tipoExa;
    private float precio;
    public categoriaExamen selectedOption;
    private List<categoriaExamen> listaCategorias;

    public categoriaExamen() {
//        cargarCombo();
//        this.selectedOption= new categoriaExamen();
    }
    public categoriaExamen(int idcategoria, String tipo_nombre, float precio){
        this.idcategoria= idcategoria;
        this.tipoExa = tipo_nombre;
        this.precio= precio;
    }

    public categoriaExamen getSelectedOption() {
        return selectedOption;
    }

    public void setSelectedOption(categoriaExamen selectedOption) {
        this.selectedOption = selectedOption;
    }    

    public List<categoriaExamen> getListaCategorias() {
        return listaCategorias;
    }

    public void setListaCategorias(List<categoriaExamen> listaCategorias) {
        this.listaCategorias = listaCategorias;
    }

    public int getIdcategoria() {
        return idcategoria;
    }

    public void setIdcategoria(int idcategoria) {
        this.idcategoria = idcategoria;
    }

    public String getTipoExa() {
        return tipoExa;
    }

    public void setTipoExa(String tipoExa) {
        this.tipoExa = tipoExa;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
    
//    public void cargarCombo(){
//        try {
//            this.listaCategorias = new ArrayList<>();
//            Conexion conn = new Conexion();
//            conn.abrirConexion();
//            ResultSet rs = conn.select("SELECT idcategoria, \"tipo_nombre\", precio FROM public.categoria_exa;");
//            while (rs.next()) {
//                this.listaCategorias.add(new categoriaExamen(
//                        rs.getInt("idcategoria"),
//                        rs.getString("tipo_nombre"),
//                        rs.getFloat("precio")
//                ));
//            }
//            conn.cerrarConexion();
//        } catch (Exception ex) {
//            Logger.getLogger(CrudView.class.getName()).log(Level.SEVERE, null, ex);
//            System.out.println("ERROR: "+ex.getMessage());
//        }
//    }
//    
//    public void llenarCombo(){
//        this.listaCategorias = new ArrayList<>();
//        this.listaCategorias.add(new categoriaExamen(1, "covid", 8));
//        this.listaCategorias.add(new categoriaExamen(2, "delta", 87));
//        this.listaCategorias.add(new categoriaExamen(3, "gripe", 86));
//        this.listaCategorias.add(new categoriaExamen(4, "viruela", 81));
//    }
}