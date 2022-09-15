/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Conexion;
import modelo.Persona;

/**
 *
 * @author kevin
 */
@ManagedBean(name = "crudView")
@ViewScoped
public class CrudView {
    Persona persona;
    private List<Persona> selectedPersonas;
    private Persona personaSeleccionada;
    private List<Persona> personas;

    public CrudView() {
        cargarPostulantes();
    }

    public List<Persona> getPersonas() {
        return personas;
    }

    public void setPersonas(List<Persona> personas) {
        this.personas = personas;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
    private void addMessage(FacesMessage.Severity SEVERITY_ERROR, String información, String error_al_guardar_los_datos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public List<Persona> getSelectedPersonas() {
        return selectedPersonas;
    }

    public void setSelectedPersonas(List<Persona> selectedPersonas) {
        this.selectedPersonas = selectedPersonas;
    }

    public Persona getPersonaSeleccionada() {
        return personaSeleccionada;
    }

    public void setPersonaSeleccionada(Persona personaSeleccionada) {
        this.personaSeleccionada = personaSeleccionada;
    }
    
    public void cargarPostulantes(){
        try {
            this.personas = new ArrayList<>();
            Conexion conn = new Conexion();
            conn.abrirConexion();
            ResultSet rs = conn.select("select id, cedula, nombres, apellidos from personas where tipousuario='Postulante';");
            while (rs.next()) {
                this.personas.add(new Persona(
                        rs.getInt("id"),
                        rs.getString("cedula"),
                        rs.getString("nombres"),
                        rs.getString("apellidos")
                ));
            }
            conn.cerrarConexion();
        } catch (Exception ex) {
            Logger.getLogger(CrudView.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
