
package DataTypes;

public class DataApuestaCampeon extends DataApuesta {

    private DataEquipo equipo;
    private DataCompeticion competicion;

    public DataApuestaCampeon(){}

    public DataApuestaCampeon(float monto, float saldoNuevo, float beneficio,
        float dividendo, DataEquipo dataE, DataCompeticion dataC,
            EstadoApuesta est, int paquete){

        super(monto,saldoNuevo,beneficio,dividendo,est,paquete);
        this.equipo = dataE;
        this.competicion = dataC;
    }
    

    public DataEquipo getEquipo() {
        return this.equipo;
    }

    public void setEquipo(DataEquipo dataE){
        this.equipo = dataE;
    }

    public DataCompeticion getCompeticion() {
        return this.competicion;
    }

    public void setCompeticion(DataCompeticion dataC){
        this.competicion = dataC;
    }
}