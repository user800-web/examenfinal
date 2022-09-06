/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.faces.model.SelectItemGroup;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Eduardo
 */
@ManagedBean(name = "cascade")
@ViewScoped
public class cascadeSelectView {
    private List<SelectItem> personasItem;
    private String selection;
    private String selection2;

    public cascadeSelectView(){
        personasItem = new ArrayList<>();
        SelectItemGroup group1 = new SelectItemGroup("Australia");

        personasItem.add(group1);
    }
//    @PostConstruct
//    public void init() {
//        personasItem = new ArrayList<>();
//        SelectItemGroup group1 = new SelectItemGroup("Australia");
//
//        personasItem.add(group1);
//    }

    public void onItemSelect(SelectEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Selected City", (String) event.getObject());
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public List<SelectItem> getCountries() {
        return personasItem;
    }

    public String getSelection() {
        return selection;
    }

    public void setSelection(String selection) {
        this.selection = selection;
    }

    public String getSelection2() {
        return selection2;
    }

    public void setSelection2(String selection2) {
        this.selection2 = selection2;
    }
}
