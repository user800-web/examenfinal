/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package verificacion;

import java.util.Properties;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Eduardo
 */
@Named
@RequestScoped
public class Mail {

    private String to;
    private String from;
    private String subject;
    private String descr;
    private String username;
    private String password;
    private String smtp;
    private int port;

    public Mail() {
        this.to = null;
        this.from = "balthazarescobar@gmail.com";//crear un correo el cual les permita enviar correos, desde su aplicacion iniciando sesion
        this.subject = null;//asunto
        this.descr = null;//mensaje
        this.username = "balthazarescobar@gmail.com";
        this.password = "ericksson";//contraseña de nuestro correo
        this.smtp = "smtp.gmail.com";
        this.port = 465;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSmtp() {
        return smtp;
    }

    public void setSmtp(String smtp) {
        this.smtp = smtp;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void enviarEmail() {
        Properties props = null;
        Session session = null;
        MimeMessage message = null;
        Address fromAddress = null;
        Address toAddress = null;

        props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", smtp);
        props.put("mail.smtp.port", port);
        //props.put("mail.smtp.ssl.trust", "*");

        session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        message = new MimeMessage(session);
        try {
            message.setContent(getDescr(), "text/plain");
            message.setSubject(getSubject());
            fromAddress = new InternetAddress(getFrom());
            message.setFrom(fromAddress);
            String[] recipientList = to.split(",");
            InternetAddress[] recipientAddress = new InternetAddress[recipientList.length];
            int counter = 0;
            for (String recipient : recipientList){
                recipientAddress[counter] = new InternetAddress(recipient.trim());
                counter++;
            }
            message.setRecipients(Message.RecipientType.TO, recipientAddress);
            message.saveChanges();

            Transport transport = session.getTransport("smtp");
            transport.connect(this.smtp, this.port, this.username, this.password);
            if (!transport.isConnected()) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "se envio su correo"));
            }
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();

        } catch (MessagingException me) {
            System.out.print("error");
            me.printStackTrace();
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Aviso", "se envio su correo"));
        }
    }
}