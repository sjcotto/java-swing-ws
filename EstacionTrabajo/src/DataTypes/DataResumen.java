package DataTypes;
import java.util.*;


public class DataResumen {
    int golesLocal;
    int golesVisita;
    int penalLocal;
    int penalVisita;
    List<DataIdNombre> jugadoresLocal;
    List<DataIdNombre> jugadoresVisita;

    List<DataTypes.DataEvento> eventos=null;//nuevooooooooooooooo, cotto


    public DataResumen(int golesLocal,int golesVisita,int penalLocal,
            int penalVisita,List<DataIdNombre> jugadoresLocal,
            List<DataIdNombre> jugadoresVisita){

        this.golesLocal=golesLocal;
        this.golesVisita=golesVisita;
        this.penalLocal=penalLocal;
        this.penalVisita=penalVisita;
        this.jugadoresLocal= jugadoresLocal;
        this.jugadoresVisita=jugadoresVisita;

        this.eventos = new ArrayList<DataTypes.DataEvento>(); // por las dudas que joda el null, si alguno no se hizo el set
    }

    public int getGolesLocal(){
        return golesLocal;
    }
    public int getGolesVisita(){
        return golesVisita;
    }
    public int getpenalLocal(){
        return penalLocal;
    }
    public int getpenalVisita(){
        return penalVisita;
    }
    public List<DataIdNombre> getJugadoresLocal(){
        return this.jugadoresLocal;
    }
     public List<DataIdNombre> getJugadoresVisita(){
        return this.jugadoresVisita;
    }

    public void setEventos(List<DataTypes.DataEvento> l) {
        this.eventos = l;
    }

    public List<DataTypes.DataEvento> getEventos() {
        return this.eventos;
    }
}
