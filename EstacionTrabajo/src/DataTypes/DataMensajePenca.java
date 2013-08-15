/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author tprog084
 */
public class DataMensajePenca implements Comparable {

    String emisor;
    String receptor;
    boolean publico;
    String mensaje;
    DataFechaHora fecha;
    int index;

    public DataMensajePenca() {
    }



    public DataMensajePenca(String emisor, String receptor, boolean publico, String mensaje, DataFechaHora dataFH) {
        this.emisor = emisor;
        this.receptor = receptor;
        this.publico = publico;
        this.mensaje = mensaje;
        this.fecha = dataFH;
    }

    public String getEmisor() {
        return emisor;
    }

    public void setEmisor(String emisor) {
        this.emisor = emisor;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isPublico() {
        return publico;
    }

    public void setPublico(boolean publico) {
        this.publico = publico;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public DataFechaHora getFecha() {
        return fecha;
    }

    public void setFecha(DataFechaHora fecha) {
        this.fecha = fecha;
    }

    public int compareTo(Object o) {
        DataMensajePenca dataMP = (DataMensajePenca) o;
        return this.fecha.compareTo(dataMP.getFecha());
    }

   public void setIndex(int i){
       index=i;
   }
   public int getIndex(){
       return index;
   }
    

}
