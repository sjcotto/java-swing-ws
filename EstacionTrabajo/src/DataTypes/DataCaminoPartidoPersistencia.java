/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

/**
 *
 * @author eliel
 */
public class DataCaminoPartidoPersistencia {
    private int idC;
    private TipoCompeticion tipoC;
    private String llave;
    private int idP;
    public DataCaminoPartidoPersistencia(int idC,TipoCompeticion tipoC,String llave,int idP){
        this.idC=idC;
        this.tipoC=tipoC;
        this.llave=llave;
        this.idP=idP;
    }
    public int getIdCompeticion(){
        return this.idC;
    }
    public TipoCompeticion getTipoCompeticion(){
        return this.tipoC;
    }
    public String getNombreLlave(){
        return this.getNombreLlave();
    }
    public int getIdPartido(){
        return this.idP;
    }
            
    
}
