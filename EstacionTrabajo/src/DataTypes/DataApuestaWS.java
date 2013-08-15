/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author Santiago
 */
public class DataApuestaWS {
    //atributos en comun
    
    private int tipo;

    private float monto;
    private float saldoNuevo;
    private float beneficio;
    private float dividendo;
    private EstadoApuesta estadoApuesta;
    private DataFechaHora fecha;
    private int pertenecePaquete;

    public float getMonto(){
        return monto;
    }
    public void setMonto(float m){
        monto=m;
    }


    public float getSaldoNuevo(){
        return saldoNuevo;
    }
    public void setSaldoNuevo(float s){
        saldoNuevo=s;
    }

    public float getBeneficio(){
        return beneficio;
    }
    public void setBeneficio(float b){
        beneficio=b;
    }
    public float getDividendo(){
        return dividendo;
    }
    public void setDividendo(float d){
         dividendo=d;
    }
    public EstadoApuesta getEstadoApuesta(){
        return estadoApuesta;
    }
    public void setEstadoApuesta(EstadoApuesta e){
        estadoApuesta=e;
    }
    public DataFechaHora getFecha(){
        return fecha;
    }
    public void setFecha(DataFechaHora d){
        fecha=d;
    }

    public int getPertenecePaquete() {
        return this.pertenecePaquete;
    }

    public void setPertenecePaquete(int p){
        this.pertenecePaquete = p;
    }
    
    //0->DataApuestaCampeon

    private DataEquipo equipo;
    private DataCompeticion competicion;



    public DataApuestaWS(){
    }

    public int getTipo(){
        return tipo;
    }

    public void setTipo(int i){
        this.tipo = i;
    }

    public void setEquipo(DataEquipo value){
        this.equipo = value;
    }
    public void setCompeticion(DataCompeticion value){
        this.competicion = value;
    }
    public DataCompeticion getCompeticion(){
        return this.competicion;
    }
    public DataEquipo getEquipo(){
        return this.equipo;
    }

    //1->DataApuestaResExacto
    private DataPartido partido;
    private int golesL;
    private int golesV;

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

    //2->DataApuestaGoleador
    private DataJugador jugador;
    //private DataCompeticion competicion;
    public DataJugador getJugador() {
        return this.jugador;
    }

    public void setJugador(DataJugador dataJ){
        this.jugador = dataJ;
    }

    //3->DataApuestaPartido
    private TipoDividendo tipoResultado;

    public TipoDividendo getTipoResultado(){
        return this.tipoResultado;
    }
    public void setTipoResultado(TipoDividendo tipoD){
        this.tipoResultado = tipoD;
    }

}
