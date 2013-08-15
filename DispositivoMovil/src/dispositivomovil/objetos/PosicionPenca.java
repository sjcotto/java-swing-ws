/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dispositivomovil.objetos;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Santiago
 */
@Entity
public class PosicionPenca implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    int puntos;
    
    private String nick;

    public PosicionPenca(){}

    public String getNick(){return nick;}
    public void setNick(String value){nick=value;}

    public int getPuntos() {
        return puntos;
    }

    public void setPuntos(int puntos) {
        this.puntos = puntos;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
}
