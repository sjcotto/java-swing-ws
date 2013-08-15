
package DataTypes;

public class DataApuestaPartido extends DataApuesta {

    private DataPartido partido;
    private TipoDividendo tipoResultado;

    public DataApuestaPartido(){}

    public DataApuestaPartido(float monto, float saldoNuevo, float beneficio,
        float dividendo, DataPartido dataP, TipoDividendo tipoD,
            EstadoApuesta est, int paquete){

        super(monto,saldoNuevo,beneficio,dividendo,est,paquete);
        this.partido = dataP;
        this.tipoResultado = tipoD;
    }

    public DataPartido getPartido(){
        return this.partido;
    }

    public TipoDividendo getTipoResultado(){
        return this.tipoResultado;
    }

    public void setPartido(DataPartido dataP){
        this.partido = dataP;
    }

    public void setTipoResultado(TipoDividendo tipoD){
        this.tipoResultado = tipoD;
    }
    
}