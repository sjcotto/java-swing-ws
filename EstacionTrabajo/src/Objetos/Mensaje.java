/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

import Controladores.ControladorFecha;
import DataTypes.DataFechaHora;
import DataTypes.DataMensajePenca;

/**
 *
 * @author tprog084
 */
public class Mensaje {
    String emisor;
    String receptor;
    boolean publico;
    String mensaje;
    DataFechaHora fecha;

    public Mensaje(String emisor, String receptor, boolean publico, String mensaje) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.publico = publico;
        this.mensaje = mensaje;
        this.fecha = ControladorFecha.getInstance().getFecha();
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public DataMensajePenca getDataMensajePenca() {
        return new DataMensajePenca(emisor,receptor, publico,mensaje,this.fecha);
    }

    public DataFechaHora getFecha() {
        return fecha;
    }

    public void setFecha(DataFechaHora fecha) {
        this.fecha = fecha;
    }

    
}
