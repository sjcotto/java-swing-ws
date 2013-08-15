/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author gonzalo
 */
public class FiltroRangoFecha extends DataFiltro{
    private DataFechaHora fechaIni;
    private DataFechaHora fechaFin;

    public FiltroRangoFecha(DataFechaHora fIni, DataFechaHora fFin) {
        this.fechaIni = fIni;
        this.fechaFin = fFin;
    }

    public boolean cumpleFiltro (DataPartido dataP) {
        DataFechaHora fechaPart = dataP.getDataInfoPart().getFechaHora();
        return fechaPart.compareTo(this.fechaIni)!=-1 &&
                fechaPart.compareTo(this.fechaFin)!=1;
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
