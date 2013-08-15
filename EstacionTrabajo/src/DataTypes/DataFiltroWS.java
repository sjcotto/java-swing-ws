/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author Santiago
 */
public class DataFiltroWS {

    private int tipoFiltro;

    // 0: Competicion
    private String nomComp;
    // 1: Equipo
    private String nomEq;
    // 2: Estado Partido
    private String estado;
    // 3: Lugar Encuentro
    private String lugarEnc;
    // 4: Rango Dividendo
    private TipoDividendo tipoDividendo;
    private float rangoInicial;
    private float rangoFinal;
    // 5: Rango Fecha Hora
    private DataFechaHora fechaIni;
    private DataFechaHora fechaFin;


    public DataFiltroWS(){}

    public int getTipoFiltro(){
        return this.tipoFiltro;
    }

    public void setTipoFiltro(int tipo) {
        this.tipoFiltro = tipo;
    }

    public String getNomComp(){
        return nomComp;
    }

    public void setNomComp(String n){
        nomComp=n;
    }

    public String getNomEq(){
        return nomEq;
    }

    public void setNomEq(String n){
        nomEq=n;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String e){
        estado=e;
    }

    public String getLugarEnc(){
        return lugarEnc;
    }

    public void setLugarEnc(String l){
        lugarEnc=l;
    }

    public TipoDividendo getTipoDividendo() {
        return this.tipoDividendo;
    }

    public void setTipoDividendo(TipoDividendo t) {
        this.tipoDividendo = t;
    }

    public float getRangoInicial(){
        return rangoInicial;
    }

    public float getRangoFinal(){
        return rangoFinal;
    }

    public void setRangoInicial(float r){
        rangoInicial=r;
    }

    public void setRangoFinal(float r){
        rangoFinal=r;
    }

    public DataFechaHora getFechaIni(){
        return fechaIni;
    }

    public DataFechaHora getFechaFin(){
        return fechaFin;
    }

    public void setFechaIni(DataFechaHora i){
        fechaIni=i;
    }

    public void setFechaFin(DataFechaHora f){
        fechaFin=f;
    }
}
