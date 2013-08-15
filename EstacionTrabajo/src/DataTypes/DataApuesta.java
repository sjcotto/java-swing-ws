/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
@XmlAccessorType(XmlAccessType.FIELD)
public class DataApuesta implements Comparable {
    private float monto;
    private float saldoNuevo;
    private float beneficio;
    private float dividendo;
    private EstadoApuesta estadoApuesta;
    private DataFechaHora fecha;
    private int pertenecePaquete;

    public DataApuesta(){
        
    }
    
    public DataApuesta(float monto, float saldoNuevo, float beneficio,
                       float dividendo, EstadoApuesta est, int p){
        this.monto = monto;
        this.saldoNuevo = saldoNuevo;
        this.beneficio = beneficio;
        this.dividendo = dividendo;
        this.estadoApuesta = est;
        this.pertenecePaquete = p;
    }

    public int compareTo (Object d){
        DataApuesta da = (DataApuesta)d;
        return fecha.compareTo(da.getFecha());
    }

    public float getMonto(){
        return monto;
    }
    public DataFechaHora getFecha(){
        return this.fecha;
    }
    public void setFecha(DataFechaHora f ){
        this.fecha=f;
    }

    public float getSaldoNuevo(){
        return saldoNuevo;
    }
    public float getBeneficio(){
        return beneficio;
    }
    public float getDividendo(){
        return dividendo;
    }
    public EstadoApuesta getEstadoApuesta() {
        return this.estadoApuesta;
    }

    public void setMonto(float m){
        monto=m;
    }
    public void  setSaldoNuevo(float sa){
        saldoNuevo= sa;
    }
    public void  setBeneficio(float be){
        beneficio=be;
    }
    public void setDividendo(float div){
        dividendo=div;
    }
    public void setEstadoApuesta(EstadoApuesta est) {
        this.estadoApuesta = est;
    }
    public int getPertenecePaquete() {
        return this.pertenecePaquete;
}
    public void setPertenecePaquete(int p) {
        this.pertenecePaquete = p;
    }
}
