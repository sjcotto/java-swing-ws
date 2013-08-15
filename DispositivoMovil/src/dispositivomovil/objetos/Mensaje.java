/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dispositivomovil.objetos;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Santiago
 */
@Entity
public class Mensaje implements Serializable {
    boolean publico;
    String emisor;
    String receptor; //solo para el caso de ser privado
    java.sql.Date fecha;

    boolean toSend = false;
    
    String message;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public boolean isToSend() {
        return toSend;
    }

    public void setToSend(boolean toSend) {
        this.toSend = toSend;
    }


    public Mensaje(){}

    public void setPublico(boolean value){
        publico=value;
    }
    public boolean getPublico(){
        return publico;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void setEmisor(String value){
        emisor=value;
    }
    public String getEmisor(){
        return emisor;
    }

    public void setReceptor(String value){
        receptor=value;
    }
    public String getReceptor(){
        return receptor;
    }
    
    public void setMessage(String value){
        message=value;
    }
    public String getMessage(){
        return message;
    }

    public java.sql.Date getFecha(){
        return fecha;
    }
    public void setFecha(java.sql.Date value){
        fecha = value;
    }

    
}
