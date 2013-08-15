/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

/**
 *
 * @author gonzalo
 */
public class DataPartidoComp {
    private int idComp;
    private String nombreComp;
    private int idPart;

    public DataPartidoComp() {}

    public DataPartidoComp (int idC, String nomC, int idP){
        this.idComp = idC;
        this.nombreComp = nomC;
        this.idPart = idP;
    }

    public int getIdComp() {
        return this.idComp;
    }

    public String getNombreComp() {
        return this.nombreComp;
    }

    public int getIdPart() {
        return this.idPart;
    }

    public void setIdComp(int i){
        idComp=i;
    }

    public void setNombreComp(String n){
        nombreComp=n;
    }

    public void setIdPart(int i){
        idPart=i;
    }
}
