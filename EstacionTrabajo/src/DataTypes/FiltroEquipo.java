/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author gonzalo
 */
public class FiltroEquipo extends DataFiltro{
    private String nomEq;

    public FiltroEquipo (String nomEq) {
        this.nomEq = nomEq;
    }

    public boolean cumpleFiltro(DataPartido dataP) {
        return dataP.getDataInfoPart().getNomLocal().equals(nomEq)||
                dataP.getDataInfoPart().getNomVisita().equals(nomEq);
    }

    public String getNomEq(){
        return nomEq;
    }

    public void setNomEq(String n){
        nomEq=n;
    }
}
