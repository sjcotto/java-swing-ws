/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package dispositivomovil.objetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Santiago
 */
@Entity
public class Penca implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private int id;

    private boolean finalizada;
    private float monto;
    private float pozo;
    private boolean participa;

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Partido> partidos = new ArrayList<Partido>();

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<Mensaje> mensajes = new ArrayList<Mensaje>();

    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    private List<PosicionPenca> pos = new ArrayList<PosicionPenca>();
    
    public Penca(){

    }

    public List<Mensaje> getMensajes() {
        return mensajes;
    }

    public void setMensajes(List<Mensaje> mensajes) {
        this.mensajes = mensajes;
    }

    public List<Partido> getPartidos() {
        return partidos;
    }

    public void setPartidos(List<Partido> partidos) {
        this.partidos = partidos;
    }

    public void setMonto(float value){
        monto=value;
    }
    public float getMonto(){
        return monto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFinalizada(){
        return finalizada;
    }

    public void setFinalizada(boolean value){
        finalizada=value;
    }

    public void setPozo(float value){
        pozo=value;
    }
    public float getPozo(){
        return pozo;
    }

    public List<PosicionPenca> getPos(){
        return pos;
    }

    public void setPos(List<PosicionPenca> value){pos=value;}


    public boolean isParticipa() {
        return participa;
    }

    public void setParticipa(boolean participa) {
        this.participa = participa;
    }

}
