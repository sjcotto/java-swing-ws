/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author CAPablito
 */
public class FiltroCompeticion extends DataFiltro{
    private String nomComp;

public FiltroCompeticion(String nomComp) {
        this.nomComp = nomComp;
    }

public boolean cumpleFiltro(DataPartido dataP) {
        return nomComp.equals(dataP.getDataInfoPart().getNomComp());
}

public String getNomComp(){
    return nomComp;
}

public void setNomComp(String n){
    nomComp=n;
}
}
