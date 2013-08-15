/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;


public class DataResultadoAltaPartido {
    private boolean quedoDefinido, exito;

    public DataResultadoAltaPartido(boolean qd, boolean ex){
        quedoDefinido = qd;
        exito = ex;
    }
    public boolean getPartidoCreadoExito(){
        return exito;
    }
    public boolean getCompeticionQuedoDefinida(){
        return quedoDefinido;
    }
    
    public boolean getExito(){
        return exito;
    }
    
    public boolean getQuedoDefinido(){
        return quedoDefinido;
    }
    
    public void setExito(boolean e){
        exito=e;
    }
    
    public void setQuedoDefinido(boolean q){
        quedoDefinido=q;
    }


}
