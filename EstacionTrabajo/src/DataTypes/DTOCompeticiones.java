/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

import java.util.List;
import java.util.ArrayList;
import Objetos.Mensaje;


public class DTOCompeticiones {
   private List<DataApuestaPersistencia> apuestasCampeon;
   private DataCompeticion dataCom;
   private TipoCriterio[] criterios;
   private List<DataPartido> dataPartidos;
   private List<DataLlave> dataLlaves;
   private List<DataInfoJugCampeonatoPersistencia> InfoJugYlistaApuGoleador;


   public DTOCompeticiones (){}

   //partido individual;
   public DTOCompeticiones(DataCompeticion dataCom,DataPartido dataPartido){
       this.dataCom=dataCom;
       this.criterios=null;
       if(dataPartido!=null){
            dataPartidos =new ArrayList<DataPartido>();
            this.dataPartidos.add(dataPartido);
       }
       else{
           this.dataPartidos=null;
       }
       this.dataLlaves=null;
       this.apuestasCampeon=null;
       this.InfoJugYlistaApuGoleador=null;
       this.pencas=null;
   }

   public List<DataInfoJugCampeonatoPersistencia> getListaInfoJugCampeonatoYApuestas(){
       return this.InfoJugYlistaApuGoleador;
   }
   public DataCompeticion getDataCompeticion(){
       return this.dataCom;
   }

   public TipoCriterio[] getCriterios(){
       return this.criterios;
   }

   public List<DataPartido> getPartidos(){
       return this.dataPartidos;
   }
   public List<DataLlave> getDataLlaves(){
       return this.dataLlaves;
   }
   public List<DataApuestaPersistencia> getApuestasCampeon(){
       return this.apuestasCampeon;
   }
   //22-10 agregado a los constructores de copa y liga
   private List<DataInfoUsuarioPencaPersistencia> pencas=null;
   public List<DataInfoUsuarioPencaPersistencia> getPpencas(){
       return this.pencas;
   }


   //noviembre
   private List<Mensaje> foro;

    //copa
   public DTOCompeticiones(DataCompeticion dataCom,List<DataLlave> dataLlaves,
           List<DataApuestaPersistencia> apC,List<DataInfoJugCampeonatoPersistencia> lIJCyA,
           List<DataInfoUsuarioPencaPersistencia> pencas,List<Mensaje> foro){
       this.dataCom=dataCom;
       this.criterios=null;
       this.dataPartidos=null;
       this.dataLlaves=dataLlaves;
       this.apuestasCampeon=apC;
       this.InfoJugYlistaApuGoleador=lIJCyA;
       this.pencas=pencas;
       this.foro=foro;
   }

   //para liga
   public DTOCompeticiones(DataCompeticion dataCom,TipoCriterio[] criterios,
           List<DataPartido> dataPartidos,List<DataApuestaPersistencia> apC,
           List<DataInfoJugCampeonatoPersistencia> lIJCyA,
           List<DataInfoUsuarioPencaPersistencia> pencas,List<Mensaje> foro){
       this.dataCom=dataCom;
       this.criterios=criterios;
       this.dataPartidos=dataPartidos;
       this.dataLlaves=null;
       this.apuestasCampeon=apC;
       this.InfoJugYlistaApuGoleador=lIJCyA;
       this.pencas=pencas;
       this.foro=foro;
   }

    public List<Mensaje> getForo() {
        return foro;
    }

    public void setForo(List<Mensaje> foro) {
        this.foro = foro;
    }


}