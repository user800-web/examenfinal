/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import modelo.Personap;

/**
 *
 * @author Karla
 */
@ManagedBean(name = "listadoMB")
@ViewScoped
public class Listados {
    private List<Personap> listPersonas = new ArrayList<>();

    public Listados() {
        Personap c = new Personap();
        try {
            this.listPersonas = c.select();
        } catch (Exception ex) {
            Logger.getLogger(Listados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Personap> getListIntegrantes() {
        return listPersonas;
    }

    public void setListIntegrantes(List<Personap> listPersonas) {
        this.listPersonas = listPersonas;
    }
}
