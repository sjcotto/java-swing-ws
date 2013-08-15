/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;


public class DataPartidoNoDefinidoLiga {
    private int idEquipoLocal, idEquipoVisitante;
    private String nombreEquipoLocal, nombreEquipoVisitante;

    public DataPartidoNoDefinidoLiga(int idl, int idv, String noml, String nomv){
        idEquipoLocal = idl;
        idEquipoVisitante = idv;
        nombreEquipoLocal = noml;
        nombreEquipoVisitante = nomv;
    }
    public int getIdEquipoLocal(){
        return idEquipoLocal;
    }
    public int getIdEquipoVisitante(){
        return idEquipoVisitante;
    }
    public String getNombreEquipoLocal(){
        return nombreEquipoLocal;
    }
    public String getNombreEquipoVisitante(){
        return nombreEquipoVisitante;
    }

    public void setIdEquipoLocal(int i){
        idEquipoLocal=i;
    }

    public void setIdEquipoVisitante(int i){
        idEquipoVisitante=i;
    }

    public void setNombreEquipoLocal(String  n){
        nombreEquipoLocal=n;
    }

    public void setNombreEquipoVisitante(String  n){
        nombreEquipoVisitante=n;
    }
}
