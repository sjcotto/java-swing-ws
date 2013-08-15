/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


/////////// AGREGADO 

package DataTypes;


public class DataInfoPartIndividual {
    private DataIdNombre eqVisit;
    private DataIdNombre eqLocal;
    private Dividendos divs;
    
    public DataInfoPartIndividual(DataIdNombre eqL, DataIdNombre eqv, 
                                  Dividendos diVs){
        eqVisit = eqv;
        eqLocal = eqL;
        divs = diVs;
    }
    public DataIdNombre getEquipoLocal(){
        return eqLocal;
    }

    public DataIdNombre getEqLocal(){
        return eqLocal;
    }

    public DataIdNombre getEqVisit(){
        return eqVisit;
    }
    public DataIdNombre getEquipoVisitante(){
        return eqVisit;
    }
    public Dividendos getDividendos(){
        return divs;
    }

    public void setEqLocal(DataIdNombre l){
        eqLocal=l;
    }

      public void setEqVisit(DataIdNombre v){
        eqVisit=v;
    }

      public void setDivs(Dividendos d){
          divs=d;
      }
}
