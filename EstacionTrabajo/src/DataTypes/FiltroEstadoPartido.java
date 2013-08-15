/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author CAPablito
 */
public class FiltroEstadoPartido extends DataFiltro{
    private String estado;

    public FiltroEstadoPartido(String estado) {
        this.estado = estado;
    }

    public boolean cumpleFiltro(DataPartido dataP) {
        if (estado.equals("pendiente"))
            return dataP.getEstaFinalizado()==false;
        else if (estado.equals("finalizado"))
            return dataP.getEstaFinalizado()==true;
        else
           return false;
    }

    public String getEstado(){
        return estado;
    }

    public void setEstado(String e){
        estado=e;
    }
}
