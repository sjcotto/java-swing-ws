/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author gonzalo
 */
public class DataEquipoLiga {
    private int idEquipo;
    private String nombreEquipo;
    private int posicion;
    private int puntosEquipo;
    private int nroPartidosEmpatados;
    private int nroPartidosGanados;
    private int nroPartidosPerdidos;
    private int golesFavor;
    private int golesContra;
    // Partidos jugados y saldo de goles se calculan

    public DataEquipoLiga(){}

    public DataEquipoLiga (int idEq, String nomEq, int pos, int puntos,
            int partEmp, int partGan, int partPer, int golF, int golC) {
        this.idEquipo=idEq;
        this.nombreEquipo=nomEq;
        this.posicion = pos;
        this.puntosEquipo=puntos;
        this.nroPartidosEmpatados = partEmp;
        this.nroPartidosGanados=partGan;
        this.nroPartidosPerdidos=partPer;
        this.golesFavor=golF;
        this.golesContra=golC;
    }

    public int getIdEquipo() {
        return this.idEquipo;
    }
    public String getNombreEquipo(){
        return this.nombreEquipo;
    }
    public int getPosicion() {
        return this.posicion;
    }
        public int getPuntosEquipo(){
        return this.puntosEquipo;
    }
    public int getNroPartidosEmpatados(){
        return this.nroPartidosEmpatados;
    }
    public int getNroPartidosGanados(){
        return this.nroPartidosGanados;
    }
    public int getNroPartidosPerdidos(){
        return this.nroPartidosPerdidos;
    }
    public int getGolesFavor(){
        return this.golesFavor;
    }
    public int getGolesContra(){
        return this.golesContra;
    }

     public void setIdEquipo(int i){
        idEquipo=i;
        }
    public void setNombreEquipo(String nom){
        nombreEquipo=nom;
    }

    public void setPosicion(int pos){
        posicion = pos;
    }

    public void setPuntosEquipo(int pts){
        puntosEquipo=pts;
    }

    public void setNroPartidosEmpatados(int n){
        nroPartidosEmpatados=n;
    }

    public void setNroPartidosGanados(int n){
        nroPartidosGanados=n;
    }

    public void setNroPartidosPerdidos(int n){
        nroPartidosPerdidos=n;
    }

    public void setGolesFavor(int g){
        golesFavor=g;
    }

     public void setGolesContra(int g){
        golesContra=g;
    }
}
