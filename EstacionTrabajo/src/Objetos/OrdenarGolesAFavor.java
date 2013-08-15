package Objetos;
import Interface.IOrdenarLiga;
import java.util.List;
import java.util.Map;
import java.util.Collections;
import java.util.ArrayList;

public class OrdenarGolesAFavor implements IOrdenarLiga{
    
    private int[] tabla;
    private List<Integer> iguales;
    
    @Override
    public void  ordenar(Map<Integer,InfoEquipoCampeonato> lista,int []tabla,List<Integer> igualados){
        this.tabla=tabla;
        this.iguales=new ArrayList<Integer>();
        
        int tope=igualados.size()/2;
        List<InfoEquipoLiga> auxtabla=new ArrayList<InfoEquipoLiga>();
        
        //con los que venian empatados comparo por el nuevo criterio
        for(int g=0;g<tope;g++){ 
            int inicio=igualados.get(2*g);
            int fin=igualados.get(2*g+1);
             auxtabla.clear();
            for (int i=inicio;i<=fin;i++){
                  InfoEquipoLiga iEL=(InfoEquipoLiga)lista.get(tabla[i]) ;  
                  iEL.setCompararPor('g');//se setea por lo que se ordena
                   auxtabla.add(iEL);
            }
            //ordena la sublista 
            Collections.sort( auxtabla);
            //agrego a las posiciones de la tabla 
            for (int i=inicio;i<=fin;i++){
                  tabla[i]= auxtabla.get(fin-i).getEquipo().getId() ; 
            }
            
            // veo cuales siguen empardadas
            //si el siguiente tiene igual diferencia de goles y es distinto se agrega un
            boolean hayIgual=false;
            int golF=((InfoEquipoLiga)lista.get(tabla[inicio])).getGolesAFavor();
            for (int i=inicio+1;i<=fin;i++){
                  int gF= ((InfoEquipoLiga)lista.get(tabla[i])).getGolesAFavor(); 
                  if((golF==gF)&&(!hayIgual)){
                          //cierro el igual
                          this.iguales.add(i-1);
                          hayIgual=true;    
                  }
                 if((golF!=gF)&&(hayIgual)){
                          //cierro el igual
                          this.iguales.add(i-1);
                          hayIgual=false;    
                  }
                  golF=gF;
            }
            if(hayIgual){
                this.iguales.add(fin);
                hayIgual=false; 
            }
        }  
    }
    
    @Override     
    public int[] getTabla(){
        return this.tabla;
    }
    @Override
    public List<Integer> getIgualados(){
        return this.iguales;
    } 
}
