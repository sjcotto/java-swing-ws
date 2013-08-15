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
public class Partido implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idP;
    private int id; // Identificador del partido

    private String fechaHora;
    private String lugar;
    
    private int  idEqL;
    private int idEqV;

    private String nombreEqL;
    private String nombreEqV;

    private boolean aposto;

    private int golesLoc;
    private int golesVis;

    public String getNombreEqL() {
        return nombreEqL;
    }

    public int getGolesLoc() {
        return golesLoc;
    }

    public void setGolesLoc(int golesLoc) {
        this.golesLoc = golesLoc;
    }

    public int getGolesVis() {
        return golesVis;
    }

    public void setGolesVis(int golesVis) {
        this.golesVis = golesVis;
    }

    public boolean isAposto() {
        return aposto;
    }

    public void setAposto(boolean aposto) {
        this.aposto = aposto;
    }

    public void setNombreEqL(String nombreEqL) {
        this.nombreEqL = nombreEqL;
    }

    public String getNombreEqV() {
        return nombreEqV;
    }

    public void setNombreEqV(String nombreEqV) {
        this.nombreEqV = nombreEqV;
    }




    public Partido(){}


    public int getId(){
        return id;
    }
    public void setId(int value){
        id = value;
    }


    public String getFechaHora(){
        return fechaHora;
    }
    public void setFechaHora(String value){
        fechaHora = value;
    }

    public String getLugar(){
        return lugar;
    }

    public void setLugar(String value){
        lugar = value;
    }

    public int getIdEqV(){
        return idEqV;
    }

    public int getIdEqL(){
        return idEqL;
    }

    public void setIdEqL(int value){
        idEqL = value;
    }

    public void setIdEqV(int value){
        idEqV = value;
    }

    public Long getIdP() {
        return idP;
    }

    public void setIdP(Long idP) {
        this.idP = idP;
    }
    
    
}
