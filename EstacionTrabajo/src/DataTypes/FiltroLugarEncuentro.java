/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author gonzalo
 */
public class FiltroLugarEncuentro extends DataFiltro{
    private String lugarEnc;

    public FiltroLugarEncuentro(String lug) {
        this.lugarEnc = lug;
    }

    public boolean cumpleFiltro(DataPartido dataP) {
        return this.lugarEnc.equals(dataP.getDataInfoPart().getLugar());
    }

    public String getLugarEnc(){
        return lugarEnc;
    }

    public void setLugarEnc(String l){
        lugarEnc=l;
    }
}
