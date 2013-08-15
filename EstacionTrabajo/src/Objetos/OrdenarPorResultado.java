/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;
import Interface.IOrdenarLiga;
import java.util.List;
import java.util.Collections;
import java.util.Map;
import java.util.ArrayList;
import java.util.HashMap;

public class  OrdenarPorResultado implements IOrdenarLiga{
    
    private int[] tabla;
    private List<Integer> iguales;
    private List<Partido> finalizados;
    
    public OrdenarPorResultado(Map<Integer, Partido> lista){
        this.finalizados=new ArrayList<Partido>();
        for (Map.Entry<Integer,Partido> entry: lista.entrySet()) {
            Partido partido = entry.getValue();
            if(partido.getFinalizo()){
                this.finalizados.add(partido);
            }
        }
    }
    
    
    @Override
    public void  ordenar(Map<Integer,InfoEquipoCampeonato> lista,int []tabla,List<Integer> igualados){
        this.tabla=tabla;
        this.iguales=new ArrayList<Integer>();
        
        int iguales=igualados.size()/2;
        List<InfoEquipoLiga> auxtabla=new ArrayList<InfoEquipoLiga>();
        
        //con los que venian empatados comparo por el nuevo criterio
        for(int g=0;g<iguales;g++){ 
            int inicio=igualados.get(2*g);
            int fin=igualados.get(2*g+1);
            auxtabla.clear();
            for (int i=inicio;i<=fin;i++){
                InfoEquipoLiga infoNuevo=new InfoEquipoLiga(lista.get(tabla[i]).dividendoCampeonato,
                        lista.get(tabla[i]).e,lista.get(tabla[i]).campeonato);
                infoNuevo.setCompararPor('p');//se setea por lo que se ordena
                auxtabla.add(infoNuevo);
            }
            
            //busco entre los partidos finalizados si encuentro algun par que este
            //entre los que van empatados
            for(int p=0;p<this.finalizados.size();p++){
                Partido partido=finalizados.get(p);
                int idL=partido.getIdLocal();
                int idV=partido.getIdVisita();
                //veo si entre la lista de empatados estan los equipos, en tal caso
                //actualizo los puntos de la lista de nuevos info
                int encontrados=0;
                InfoEquipoLiga local=null,visita=null;
                for(int i=0;(i<auxtabla.size())&&(encontrados<2);i++){
                    int idEq=auxtabla.get(i).getEquipo().getId();
                    if(idEq==idL){
                        ++encontrados;
                        local=auxtabla.get(i);
                    }
                    if(idEq==idV){
                        ++encontrados;
                        visita=auxtabla.get(i);
                    }
                }
                //encontre los equipos del partido
                if(encontrados==2){
                    local.actualizarPuntos(partido.getGolesLocal(),partido.getGolesVisitante());
                    visita.actualizarPuntos(partido.getGolesVisitante(),partido.getGolesLocal());
                }
            }    
            //ordena la sublista 
            Collections.sort( auxtabla);
            //agrego a las posiciones de la tabla 
            for (int i=inicio;i<=fin;i++){
                  tabla[i]= auxtabla.get(fin-i).getEquipo().getId() ; 
            }
            
            // veo cuales siguen empardadas por resultados
            //se busca en la tabla de resultados, la cual esta ordenada
            //se verifican los puntos, pero se indexa por el indice de la tabla principal
            boolean hayIgual=false;
            int punPrev=((InfoEquipoLiga)auxtabla.get(0)).getPuntos();
            for (int i=inicio+1;i<=fin;i++){
                  int pun= ((InfoEquipoLiga)auxtabla.get(i-inicio)).getPuntos(); 
                  if((punPrev==pun)&&(!hayIgual)){
                          //cierro el igual
                          this.iguales.add(i-1);
                          hayIgual=true;    
                  }
                 if((punPrev!=pun)&&(hayIgual)){
                          //cierro el igual
                          this.iguales.add(i-1);
                          hayIgual=false;    
                  }
                  punPrev=pun;
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