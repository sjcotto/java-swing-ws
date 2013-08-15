/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package DataTypes;

public class DataPartidoIndividualNoDefinido {
    private int idComp;
    private String nomComp;
    private DataEquipo dataEquipo1,dataEquipo2;

    public DataPartidoIndividualNoDefinido(int idC, String nomC, String nome1, String nome2, int id1, int id2){
        idComp = idC;
        nomComp = nomC;
        dataEquipo1 = new DataEquipo(id1,nome1);
        dataEquipo2 = new DataEquipo(id2,nome2);
    }

    public int getIdCompeticion(){
        return idComp;
    }

    public int getIdComp(){
        return idComp;
    }

    public String getNombreCompeticion(){
        return nomComp;
    }

    public String getNomComp(){
        return nomComp;
    }
    public DataEquipo getDataEquipo1(){
        return dataEquipo1;
    }
    public DataEquipo getDataEquipo2(){
        return dataEquipo2;
    }

     public void setIdComp(int i){
        idComp=i;
    }

    public void setNomComp(String n){
        nomComp=n;
    }

    public void setDataEquipo1(DataEquipo e){
        dataEquipo1=e;
    }

    public void setDataEquipo2(DataEquipo e){
        dataEquipo2=e;
    }

 }
