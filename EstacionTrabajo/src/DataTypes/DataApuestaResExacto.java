
package DataTypes;

public class DataApuestaResExacto extends DataApuesta {

    private DataPartido partido;
    private int golesL;
    private int golesV;

    public DataApuestaResExacto(){}

    public DataApuestaResExacto(float monto, float saldoNuevo, float beneficio,
        float dividendo, DataPartido dataP, int golL, int golV,
            EstadoApuesta est, int paquete){

        super(monto,saldoNuevo,beneficio,dividendo,est,paquete);
        this.partido = dataP;
        this.golesL = golL;
        this.golesV = golV;
    }

    public DataPartido getPartido(){
        return this.partido;
    }

    public int getGolesL(){
        return this.golesL;
    }

    public int getGolesV(){
        return this.golesV;
    }

    public void setPartido(DataPartido dataP) {
        this.partido = dataP;
    }

    public void setGolesL(int golL){
        this.golesL = golL;
    }

    public void setGolesV(int golV){
        this.golesV = golV;
    }

}