/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

import java.util.List;

/**
 *
 * @author gonzalo
 */
public class DataPaqueteApuestasWS {

    private List<DataApuestaWS> apuestas;
    private float montoTotal;
    private EstadoPaqueteApuestas estadoPaquete;

    public DataPaqueteApuestasWS(){

    }


    public List<DataApuestaWS> getApuestas() {
        return this.apuestas;
    }

    public void setApuestas(List<DataApuestaWS> listDA) {
        this.apuestas = listDA;
    }

    public float getMontoTotal() {
        return this.montoTotal;
    }

    public void setMontoTotal(float montTot) {
        this.montoTotal = montTot;
    }

    public EstadoPaqueteApuestas getEstadoPaquete() {
        return this.estadoPaquete;
    }

    public void setEstadoPaquete(EstadoPaqueteApuestas est) {
        this.estadoPaquete = est;
    }
    
}
