/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.Serializable;
import static java.lang.ProcessHandle.current;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import modelo.Conexion;
import modelo.Examenp;
import modelo.Personap;
import modelo.categoriaExamen;
//import modelo.categoriaExa;
import org.primefaces.PrimeFaces;

/**
 *
 * @author Karla
 */
@ManagedBean(name = "crudView")
@ViewScoped
public class CrudView implements Serializable {

    PrimeFaces current = PrimeFaces.current();   
    private categoriaExamen comboCat;
    private Examenp examen;
    //PARA CATEGORIA
    private String opcionSelecionada;
    private categoriaExamen categSeleccionada = new categoriaExamen();
    private List<categoriaExamen> listadoDeCategorias;
    private List<categoriaExamen> listCateSelec;
    private String tipoExamen;

    private List<Personap> personas;

    private Personap personaSeleccionada;

    private List<Personap> selectedPersonas;
    //EXAMENES
    private Personap personaExamen;
    private List<Personap> personasE;//PARA SELECT DE MOSTRAR PACIENTES
    private List<Examenp> examenes;
    private List<Examenp> selectedExamenes;
    private Examenp examenSeleccionado;
    
    String cedula = "";
    String nombres = "";
    String apellidos = "";
    String telefono = "";
    String fechanacimiento = "";
    String direccion = "";
    String email = "";
    String sexo = "";
    String clave = "";
    String tipoU = "";
    private String modo = "";

    @PostConstruct
    public void init() {
//        this.personas = new ArrayList<>();
//        this.personas.add( new Personap("1254565098", "Erick", "Estevez", "0982135256", "2020-11-12", "Ninguna", "ninguno@gmsil.com", "M"));                

    }

    public CrudView() throws Exception {
        cargarExamenes();
        cargarPacientes();//CARGAR PERSONAS EN GENERAL
        cargarPacientesparaExa();//PARA SELECT DE MOSTRAR PACIENTES
        llenarCombo();
        this.examenSeleccionado= new Examenp();
    }

    public Examenp getExamenSeleccionado() {
        return examenSeleccionado;
    }

    public void setExamenSeleccionado(Examenp examenSeleccionado) {
        this.examenSeleccionado = examenSeleccionado;
    }

    public List<Personap> getPersonasE() {
        return personasE;
    }

    public void setPersonasE(List<Personap> personasE) {
        this.personasE = personasE;
    }

    public List<Examenp> getExamenes() {
        return examenes;
    }

    public void setExamenes(List<Examenp> examenes) {
        this.examenes = examenes;
    }

    public List<Examenp> getSelectedExamenes() {
        return selectedExamenes;
    }

    public void setSelectedExamenes(List<Examenp> selectedExamenes) {
        this.selectedExamenes = selectedExamenes;
    }

    public List<categoriaExamen> getListCateSelec() {
        return listCateSelec;
    }

    public void setListCateSelec(List<categoriaExamen> listCateSelec) {
        this.listCateSelec = listCateSelec;
    }

    public categoriaExamen getCategSeleccionada() {
        return categSeleccionada;
    }

    public void setCategSeleccionada(categoriaExamen categSeleccionada) {
        this.categSeleccionada = categSeleccionada;
    }

    public String getTipoExamen() {
        return tipoExamen;
    }

    public void setTipoExamen(String tipoExamen) {
        this.tipoExamen = tipoExamen;
    }

    public List<categoriaExamen> getListadoDeCategorias() {
        return listadoDeCategorias;
    }

    public void setListadoDeCategorias(List<categoriaExamen> listadoDeCategorias) {
        this.listadoDeCategorias = listadoDeCategorias;
    }

    public String getOpcionSelecionada() {
        return opcionSelecionada;
    }

    public void setOpcionSelecionada(String opcionSelecionada) {
        this.opcionSelecionada = opcionSelecionada;
    }

    public Examenp getExamen() {
        return examen;
    }

    public void setExamen(Examenp examen) {
        this.examen = examen;
    }

    public categoriaExamen getComboCat() {
        return comboCat;
    }

    public void setComboCat(categoriaExamen comboCat) {
        this.comboCat = comboCat;
    }

    public Personap getPersonaExamen() {
        return personaExamen;
    }

    public void setPersonaExamen(Personap personaExamen) {
        this.personaExamen = personaExamen;
    }

    public String getModo() {
        return modo;
    }

    public void setModo(String modo) {
        this.modo = modo;
    }
    //CARAGAR PERSONAS EN GENERAL
    public void cargarPacientes() {
        try {
            this.personas = new ArrayList<>();
            Conexion conn = new Conexion();
            conn.abrirConexion();
            ResultSet rs = conn.select("select * from persona;");
            while (rs.next()) {
                this.personas.add(new Personap(
                        rs.getInt("idpersona"),
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
            conn.cerrarConexion();
        } catch (Exception ex) {
            Logger.getLogger(CrudView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cargarPacientesparaExa() {
        try {
            this.personasE = new ArrayList<>();
            Conexion conn = new Conexion();
            conn.abrirConexion();
            ResultSet rs = conn.select("select * from persona where tipousuario= 'Paciente';");
            while (rs.next()) {
                this.personasE.add(new Personap(
                        rs.getInt("idpersona"),
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
            conn.cerrarConexion();
        } catch (Exception ex) {
            Logger.getLogger(CrudView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void cargarExamenes() {
        try {
            this.examenes = new ArrayList<>();
            Conexion conn = new Conexion();
            conn.abrirConexion();
            ResultSet rs = conn.select("SELECT idexamen, e.idpersona, concat(p.nombres, ' ',p.apellidos) as paciente, e.idcategoriaexam, " +
"c.tipo_nombre, fecharegistro, \"fechaEntregaResult\", observacion" +
"	FROM public.examenes e inner join persona p on e.idpersona=p.idpersona inner join " +
"    categoria_exa c on c.idcategoria=e.idcategoriaexam;");
            while (rs.next()) {
                this.examenes.add(new Examenp(
                        rs.getInt("idexamen"),
                        rs.getInt("idpersona"),
                        rs.getString("paciente"),
                        rs.getInt("idcategoriaexam"),
                        rs.getString("tipo_nombre"),
                        rs.getString("fecharegistro"),
                        rs.getString("fechaEntregaResult"),
                        rs.getString("observacion")
                ));
            }
            conn.cerrarConexion();
        } catch (Exception ex) {
            Logger.getLogger(CrudView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getCedula() {
        return cedula;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getTipoU() {
        return tipoU;
    }

    public void setTipoU(String tipoU) {
        this.tipoU = tipoU;
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

    public List<Personap> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Personap> personas) {
        this.personas = personas;
    }

    public Personap getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Personap personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }

    public List<Personap> getSelectedPersonas() {
        return selectedPersonas;
    }

    public void setSelectedPersonas(List<Personap> selectedPersonas) {
        this.selectedPersonas = selectedPersonas;
    }

    public void openNew() {
        this.modo = "insertar";
        this.personaSeleccionada = new Personap();
        //this.examenSeleccionado= new Examenp();
        this.examen= new Examenp();
    }

    public void setGuardar() {
        String data = "";
        try {
            personaSeleccionada.insert(personaSeleccionada);
            System.out.println("Registrado correctamente");

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println(data + "se ingreso no depreciable");

    }

    public String savePer() throws Exception {
        String msg = "error";
        System.out.println(modo);
        System.out.println("USUARIO: " + personaSeleccionada.getCedula() + " Clave: " + personaSeleccionada.getContraseña());
        System.out.println(String.valueOf(this.personaSeleccionada.getId()));
        String pase = personaSeleccionada.login(personaSeleccionada.getCedula(), personaSeleccionada.getContraseña());
        System.out.println(pase);
        //this.personaSeleccionada.getCedula() == null
        //if (modo.equals("insertar")) {
        //if( pase != "Usuario correcto"){
        if ((String.valueOf(this.personaSeleccionada.getId()) == null) || String.valueOf(this.personaSeleccionada.getId()).equals("0")) {
            System.out.println("INSERTAR");
            try {
                if (personaSeleccionada.insert() == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Paciente agregado"));
                    System.out.println("ENTRO A INSERT");
                    this.cargarPacientes();
                    PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
                    msg = "confirmation";
                } else {
                    System.out.println("NO ENTRO A INSERT");
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Paciente no agregado"));
                    msg = "error";
                }
            } catch (Exception e) {
                System.out.println("NO ENTRO A INSERT" + e.toString());
                /*addMessage(FacesMessage.SEVERITY_ERROR, "Información", "Error al guardar los datos.");*/
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Paciente no agregado"));
                msg = "error";
            }
//            
//            
//            /PrimeFaces.current().executeScript("PF('manageProductDialog').hide()");/
        } else {
            System.out.println("ACTUALIZAR Y SU ID ES:" + String.valueOf(personaSeleccionada.getId()));
            try {
                if (personaSeleccionada.updateX() == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Paciente actualizado"));
                    System.out.println("ENTRO A UPDATE");
                    this.cargarPacientes();
                    PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
                    msg = "confirmation";
                } else {
                    System.out.println("NO ENTRO A UPDATE");
                    /*FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Paciente no agregado"));*/
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Paciente no actualizado"));
                    msg = "error";
                }
            } catch (Exception e) {
                System.out.println("NO ENTRO A UPDATE" + e.toString());
                /*addMessage(FacesMessage.SEVERITY_ERROR, "Información", "Error al guardar los datos.");*/
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Paciente no actualizado"));
                msg = "error";
            }

            /*FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));*/
        }
        return msg;
    }

    public String deleteProduct() {
        System.out.println("ENTRO A deleteProduct");
//        personaSeleccionada = new Personap("415", this.nombres, this.apellidos, this.telefono, this.fechanacimiento, this.direccion, this.email, this.sexo);
//        personaSeleccionada = new Personap(this.cedula, this.nombres, this.apellidos, this.telefono, this.fechanacimiento, this.direccion, this.email, this.sexo);

        try {
            if (personaSeleccionada.delete() == 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Paciente eliminado"));
                System.out.println("ENTRO A DELETE");
                this.cargarPacientes();
                PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
                return "confirmation";
            } else {
                System.out.println("NO ENTRO A DELETE desde if");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Paciente no eliminado"));
                return "error";
            }
        } catch (Exception e) {
            System.out.println("NO ENTRO A DELETE desde try catch" + e.toString());
            /*addMessage(FacesMessage.SEVERITY_ERROR, "Información", "Error al guardar los datos.");*/
            return "error";
        }
//        this.personas.remove(personaSeleccionada);
//        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Removed"));
//        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
    }
    public String deleteExamen(){
        System.out.println("ENTRO A deleteExamen");
        try {
            if (examenSeleccionado.deleteExamenes(this.examenSeleccionado.getId())== 1) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exámen eliminado"));
                System.out.println("ENTRO A DELETE");
                this.cargarExamenes();
                PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
                return "confirmation";
            } else {
                System.out.println("NO ENTRO A DELETE desde if");
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Paciente no eliminado"));
                return "error";
            }
        } catch (Exception e) {
            System.out.println("NO ENTRO A DELETE desde try catch" + e.toString());
            /*addMessage(FacesMessage.SEVERITY_ERROR, "Información", "Error al guardar los datos.");*/
            return "error";
        }
    }

    public String getDeleteButtonMessage() {
        if (hasSelectedProducts()) {
            int size = this.selectedPersonas.size();
            return size > 1 ? size + " products selected" : "1 product selected";
        }

        return "Delete";
    }

    public boolean hasSelectedProducts() {
        return this.selectedPersonas != null && !this.selectedPersonas.isEmpty();
    }

    public void deleteSelectedProducts() {
        this.personas.removeAll(this.selectedPersonas);
        this.selectedPersonas = null;
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Products Removed"));
        PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
        PrimeFaces.current().executeScript("PF('dtProducts').clearFilters()");
    }

    private void addMessage(FacesMessage.Severity SEVERITY_ERROR, String información, String error_al_guardar_los_datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void pacienteSelecionado() {
        System.out.println("EN pacienteSelecionado");
        this.comboCat = new categoriaExamen();
        //this.comboCat.llenarCombo();

        if (this.selectedPersonas.size() > 0) {
            System.out.println(this.selectedPersonas.get(0).getId());
            this.personaExamen = this.selectedPersonas.get(0);
//            this.comboCat.cargarCombo();
            current.executeScript("PF('ConfigurarExamen').show();");
        }
    }

    public String guardarExamen() {
        imprimirIdExamen();
        String msg = "error";
//        this.cargarPacientesparaExa();
//        this.personaSeleccionada= this.personasE.get(0);
        //System.out.println("id exámen:  " + this.examenSeleccionado.getId());
        
        System.out.println("Datos para exámen");
        System.out.println("id persona: " + this.selectedPersonas.get(0).getId());
        System.out.println("id exámen" + this.examen.getIdexamenes());
        System.out.println("categoria" + this.categSeleccionada.getIdcategoria());
        //System.out.println("CREO QUE ID DE categoria"+ this.listadoDeCategorias);
        //System.out.println("Precio"+ this.comboCat.selectedOption.getPrecio());
        System.out.println("Fecha Registro" + this.examen.getFechaRegistro());
        System.out.println("Fecha Entrega" + this.examen.getFechaEntregaResult());
        System.out.println("Observación" + this.examen.getObservaciones());
        if ((String.valueOf(this.examenSeleccionado.getId()) == null) || String.valueOf(this.examenSeleccionado.getId()).equals("0")){
            System.out.println("INSERTAR EXAMEN");
            try {
                if (examen.insertExamenes(this.selectedPersonas.get(0).getId(), this.categSeleccionada.getIdcategoria(), 
                        this.examen.getFechaRegistro(), this.examen.getFechaEntregaResult(), this.examen.getObservaciones()) == 1) {
                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Exámen agregado"));
                    System.out.println("ENTRO A INSERT DE EXAMENES");
                    this.cargarExamenes();
                    PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
                    msg = "confirmation";
                } else {
                    System.out.println("NO ENTRO A INSERT DE EXAMENES");
                    FacesContext.getCurrentInstance().addMessage(null,
                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Exámen no agregado"));
                    msg = "error";
                }
            } catch (Exception e) {
                System.out.println("NO ENTRO A INSERT" + e.toString());
                /*addMessage(FacesMessage.SEVERITY_ERROR, "Información", "Error al guardar los datos.");*/
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Exámen no agregado"));
                msg = "error";
            }
        } else {
            System.out.println("ACTUALIZAR EXÁMEN Y SU ID ES:" + String.valueOf(this.examenSeleccionado.getId()));
//            try {
//                if (personaSeleccionada.updateX() == 1) {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Paciente actualizado"));
//                    System.out.println("ENTRO A UPDATE");
//                    this.cargarPacientes();
//                    PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
//                    msg = "confirmation";
//                } else {
//                    System.out.println("NO ENTRO A UPDATE");
//                    /*FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Paciente no agregado"));*/
//                    FacesContext.getCurrentInstance().addMessage(null,
//                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Paciente no actualizado"));
//                    msg = "error";
//                }
//            } catch (Exception e) {
//                System.out.println("NO ENTRO A UPDATE" + e.toString());
//                /*addMessage(FacesMessage.SEVERITY_ERROR, "Información", "Error al guardar los datos.");*/
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Paciente no actualizado"));
//                msg = "error";
//            }
        }
        return msg;
    }
    public void imprimir(){
        System.out.println("Datos para exámen");
    }

    public void llenarCombo() {
        this.listadoDeCategorias = new ArrayList<>();
//        this.listadoDeCategorias.add(new categoriaExamen(1, "covid", 8));
//        this.listadoDeCategorias.add(new categoriaExamen(2, "delta", 87));
//        this.listadoDeCategorias.add(new categoriaExamen(3, "gripe", 86));
//        this.listadoDeCategorias.add(new categoriaExamen(4, "viruela", 81));
        try {
            Conexion conn = new Conexion();
            conn.abrirConexion();
            /*revisar si este constructor esrta inicializado*/
            ResultSet rs = conn.select("SELECT idcategoria, tipo_nombre, precio FROM public.categoria_exa; ");
            while (rs.next()) {
                listadoDeCategorias.add(new categoriaExamen(
                        rs.getInt("idcategoria"),
                        rs.getString("tipo_nombre"),
                        rs.getFloat("precio")
                ));
                //System.out.println("ITERANDO CATEGORIAS");
            }
            conn.cerrarConexion();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.toString());
        }
    }
    public void imprimirIdExamen()
    {
        System.out.println("id exámen:  " + this.examenSeleccionado.getId());
        this.cargarPacientesparaExa();
        this.personaSeleccionada= this.personasE.get(0);
        
//        String msg = "error";
//        if ((String.valueOf(this.examenSeleccionado.getId()) == null) || String.valueOf(this.examenSeleccionado.getId()).equals("0")){
//            System.out.println("INSERTAR EXAMEN");
//        } else {
//            System.out.println("ACTUALIZAR Y SU ID ES:" + String.valueOf(this.examenSeleccionado.getId()));
//            try {
//                if (examen.updateExamenes() == 1) {
//                    FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Paciente actualizado"));
//                    System.out.println("ENTRO A UPDATE");
//                    this.cargarPacientes();
//                    PrimeFaces.current().ajax().update("form:messages", "form:dt-products");
//                    msg = "confirmation";
//                } else {
//                    System.out.println("NO ENTRO A UPDATE");
//                    /*FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Paciente no agregado"));*/
//                    FacesContext.getCurrentInstance().addMessage(null,
//                            new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Paciente no actualizado"));
//                    msg = "error";
//                }
//            } catch (Exception e) {
//                System.out.println("NO ENTRO A UPDATE" + e.toString());
//                /*addMessage(FacesMessage.SEVERITY_ERROR, "Información", "Error al guardar los datos.");*/
//                FacesContext.getCurrentInstance().addMessage(null,
//                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Paciente no actualizado"));
//                msg = "error";
//            }
//
//            /*FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Product Updated"));*/
//        }
//        return msg;

//  llave de
    }
}
