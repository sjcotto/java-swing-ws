/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;


public class DataEvento{
    private int minuto;
    private TipoPeriodo periodo;
    private DataIdNombre jugador1;
    private DataIdNombre jugador2;
    private TipoEvento tipoevento;
    private boolean amarilla; //tiene sentido solo si el tipo es sustitucion

    public DataEvento(){}

    public DataEvento(int minuto,TipoPeriodo tp,DataIdNombre j1,DataIdNombre j2,TipoEvento te,boolean esAmarilla){
        this.jugador1 = j1;
        this.jugador2 = j2;
        this.minuto = minuto;
        this.periodo=tp;//tipo de periodo
        this.tipoevento=te;
        this.amarilla=esAmarilla;               
    }
    public TipoPeriodo getPeriodo(){
        return this.periodo;
    }
    public TipoEvento getTipoevento(){
        return this.tipoevento;
    }
    public DataIdNombre getJugador1(){
        return this.jugador1;
    }
    public DataIdNombre getJugador2(){
        return this.jugador2;
    }
    public int getMinuto(){
        return this.minuto;
    }
    public boolean getAmarilla(){
        return this.amarilla;
    }
    
    public void setPeriodo(TipoPeriodo tp){
        this.periodo =tp;
    }
    public void setTipoevento(TipoEvento te){
        this.tipoevento=te;
    }
    public void setJugador1(DataIdNombre j1){
        this.jugador1 =j1;
    }
    public void setJugador2(DataIdNombre j2){
        this.jugador2=j2;
    }
    public void setMinuto(int minuto){
        this.minuto=minuto;
    }
    public void setAmarilla(boolean a){
        this.amarilla=a;
    }
}
