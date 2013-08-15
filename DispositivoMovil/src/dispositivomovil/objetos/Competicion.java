/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dispositivomovil.objetos;

//faltan las anotaciones para persistencias
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Santiago
 */
@Entity
public class Competicion  implements Serializable {
    @Id
    int id;

    char[] nombre = new char[100];

    @OneToOne(cascade=CascadeType.ALL)
    Penca penca;

    public int getId(){
        return id;
    }
    public String getNombre(){
        return new String(nombre);
    }

    public Penca getPenca() {
        return penca;
    }

    public void setPenca(Penca penca) {
        this.penca = penca;
    }


     public void setId(int i){
        id=i;
    }

    public void setNombre(String n){
        nombre=n.toCharArray();
    }

    public Competicion(){
       // penca = null;
    }
}
