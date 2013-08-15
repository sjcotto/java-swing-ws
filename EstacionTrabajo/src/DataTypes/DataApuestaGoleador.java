
package DataTypes;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class DataApuestaGoleador extends DataApuesta {

    private DataJugador jugador;
    private DataCompeticion competicion;

    public DataApuestaGoleador(){}

    public DataApuestaGoleador(float monto, float saldoNuevo, float beneficio,
        float dividendo, DataJugador dataJ,DataCompeticion dataC,
            EstadoApuesta est, int paquete){

        super(monto,saldoNuevo,beneficio,dividendo,est,paquete);
        this.jugador = dataJ;
        this.competicion = dataC;
    }

    public DataJugador getJugador() {
        return this.jugador;
    }

    public void setJugador(DataJugador dataJ){
        this.jugador = dataJ;
    }

    public DataCompeticion getCompeticion() {
        return this.competicion;
    }

    public void setCompeticion(DataCompeticion dataC){
        this.competicion = dataC;
    }
}