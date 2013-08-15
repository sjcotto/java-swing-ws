package DataTypes;

import java.util.List;
import java.util.ArrayList;

public class DataJugPartido {
    private int idPart;
    private List<DataIdNombre> jugadoresLocal;
    private List<DataIdNombre> jugadoresVisita;

    public DataJugPartido() {}

    public DataJugPartido(int idP, List<DataIdNombre> jugL, List<DataIdNombre> jugV) {
        this.idPart = idP;
        this.jugadoresLocal = jugL;
        this.jugadoresVisita = jugV;
    }

    public DataJugPartido(int idP) {
        this.idPart = idP;
        this.jugadoresLocal = new ArrayList<DataIdNombre>();
        this.jugadoresVisita = new ArrayList<DataIdNombre>();
    }

    public int getIdPart() {
        return this.idPart;
    }

    public List<DataIdNombre> getJugadoresLocal() {
        return this.jugadoresLocal;
    }

    public List<DataIdNombre> getJugadoresVisita() {
        return this.jugadoresVisita;
    }

    public void setIdPart(int i){
        idPart=i;
    }

    public void setJugadoresLocal(List<DataIdNombre> j){
        jugadoresLocal=j;
    }

    public void setJugadoresVisita(List<DataIdNombre> j){
        jugadoresVisita=j;
    }

}