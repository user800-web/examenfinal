/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verificacion;

/**
 *
 * @author Eduardo
 */
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.model.file.UploadedFile;

@ManagedBean
@ViewScoped
public class FileUploadBean implements Serializable {

    private static final long serialVersionUID = 4352236420460919694L;

    private UploadedFile file;

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void upload() {
        FacesMessage msg = new FacesMessage("Ok", "Fichero " + file.getFileName() + " subido correctamente.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

}
