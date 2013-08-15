
package DataTypes;

import java.util.List;
import java.util.ArrayList;

public class DataPaqueteApuestas {

    private List<DataApuesta> apuestas;
    private float montoTotal;
    private EstadoPaqueteApuestas estadoPaquete;

    public DataPaqueteApuestas(){
        this.apuestas = new ArrayList<DataApuesta>();
        this.montoTotal = 0;
        this.estadoPaquete = EstadoPaqueteApuestas.Pendiente;
    }
    

    public List<DataApuesta> getApuestas() {
        return this.apuestas;
    }

    public void setApuestas(List<DataApuesta> listDA) {
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