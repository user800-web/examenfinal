/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Persona;

/**
 *
 * @author kevin
 */
@ManagedBean(name = "crudView")
@ViewScoped
public class CrudView {
    Persona persona;

    public CrudView() {
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
    
}
