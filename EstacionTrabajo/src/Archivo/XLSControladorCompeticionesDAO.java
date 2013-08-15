package Archivo;

import Interface.IControladorCompeticionesDAO;
import java.util.List;
import java.util.ArrayList;
import DataTypes.*;
import Objetos.Mensaje;

public class XLSControladorCompeticionesDAO implements IControladorCompeticionesDAO{
    private ManejoDeArchivos arch=null;
    private int fila;

    public XLSControladorCompeticionesDAO() {
        arch = ManejoDeArchivos.getInstance();
    }

    @Override
    public List<DTOCompeticiones> cargarCompeticiones(String ruta)throws Exception{
             
        List <DTOCompeticiones> salida=new ArrayList<DTOCompeticiones>();
        //arch.abrirLibro(ruta);

        int nroComp=Integer.parseInt(arch.leerString("Competiciones", 0,0));
        fila=1;

        for(int i=0;i<nroComp;i++){
            DTOCompeticiones dto=null;
            String tipo=arch.leerString("Competiciones",0,fila);
            if(tipo.equals("Copa")){
                dto=this.cargarCopa();
            }
            else{
                if(tipo.equals("Liga")){
                    dto=this.cargarLiga();
                }
                else{
                    if(tipo.equals("PartidoIndividual")){
                        dto=this.cargarIndividual();
                    }
                }
            }
            salida.add(dto);
        }

        arch.cerrarLibroLec();
        return salida;
       
    }
    
    public void guardarCompeticiones(String ruta,List<DTOCompeticiones> listC)throws Exception{
       
            //arch.crearlibroEscYhojas(ruta);
            System.out.println("entro en guardar");
                  
            
            arch.agregarString("Competiciones",0,0,Integer.toString(listC.size()));
            fila=1;            
            for (int comp=0;comp<listC.size();comp++) {
                DTOCompeticiones dto= listC.get(comp); 
                TipoCompeticion tipo=dto.getDataCompeticion().getTipo();
                if(tipo==TipoCompeticion.Copa){                    
                    System.out.println("Guarda copa");
                    this.guardarCopa(dto);
                    System.out.println("Fin copa");
                }
                else{
                    if(tipo==TipoCompeticion.Liga){
                        System.out.println("Guarda liga");
                        this.guardarLiga( dto);
                        System.out.println("Fin liga");
                    }
                    else{
                        System.out.println("Gurad ind");
                        this.guardarIndividual( dto);
                        System.out.println("Fin ind");
                    }
                }
            }                      
            arch.cerrarLibroEsc();
              
    }
   
    //retorna el lugar en el que quedo el puntero de la lista luego de
    //guardar los datos
    private void guardarCopa(DTOCompeticiones dto)throws Exception{  
        
        
        DataCompeticion dC=dto.getDataCompeticion();
        //primera fila se agregan datos de la copa
        arch.agregarString("Competiciones",0,fila,dC.getTipo().toString());
        arch.agregarString("Competiciones",1,fila,Integer.toString(dC.getId()));  
        arch.agregarString("Competiciones",2,fila,dC.getNombre());           
        arch.agregarString("Competiciones",3,fila,dC.getPathImage());  
        
        //numero de equipos
        List<DataDivEquipo> listDEq=dC.getDividendos();
        arch.agregarString("Competiciones",4,fila,Integer.toString(listDEq.size()));
        //numero de llaves
        List<DataLlave> listDL=dto.getDataLlaves();        
        arch.agregarString("Competiciones",5,fila,Integer.toString(listDL.size())); 
        arch.agregarString("Competiciones",6,fila,Float.toString(dC.getMontoPenca())); 
        //agrego referente a equipos y dividendos
        int col=0;  
        ++fila;
        for( col=0;col<listDEq.size();col++){
            //en la fila 2 se agregan los id equipos
            arch.agregarString("Competiciones",col,fila,Integer.toString(listDEq.get(col).getId()));
            //en la fila 3 se agegan los dividendos
            arch.agregarString("Competiciones",col,fila+1,Float.toString(listDEq.get(col).getDividendo()));                    
        }       
        ++fila;
        
        
        //aca se agregan las apuestas a campeon
        this.guardarApuesta(dto.getApuestasCampeon());
        this.guardarListaIJCYApuestas(dto.getListaInfoJugCampeonatoYApuestas());
        this.guardarPenca(dto.getPpencas());
        this.guardarForo(dto.getForo());
        DataLlave dFinal = null;
        //se guardan las llaves si esta terminado el partido hay 2 filas una por equipo
        //para jugadores, ordenadas por fases
        for(int l=0;l<listDL.size();l++){
            
            DataLlave dL=listDL.get(l);
//fila de datos de la llave
            if (dL.getEsFinal()) {
                dFinal = dL;
            } else {
                ++fila;
                arch.agregarString("Competiciones",0,fila,dL.getNombre());
                int fase=dL.getFase();
                arch.agregarString("Competiciones",1,fila,Integer.toString(fase));

                DataPartido dP=dL.getDataPartido();
                if(fase==1){
                    String idEqL=Integer.toString(dP.getDataInfoPart().getIdLocal());
                    String idEqV=Integer.toString(dP.getDataInfoPart().getIdVisita());
                    arch.agregarString("Competiciones",2,fila,idEqL);
                    arch.agregarString("Competiciones",3,fila,idEqV);
                }
                else{
                    String llaveLocal=dL.getPreLocal();
                    String llaveVisita=dL.getPreVisita();
                    arch.agregarString("Competiciones",2,fila,llaveLocal);
                    arch.agregarString("Competiciones",3,fila,llaveVisita);
                }
                arch.agregarString("Competiciones",4,fila,Boolean.toString(dL.getTieneSucesora()));
                arch.agregarString("Competiciones",5,fila,Boolean.toString(dL.getEsFinal()));

     //guardo el partido
               guardarPartido(dP);
            }
        }
        if (dFinal!=null){
            ++fila;

            arch.agregarString("Competiciones",0,fila,dFinal.getNombre());
            int fase=dFinal.getFase();
            arch.agregarString("Competiciones",1,fila,Integer.toString(fase));

            DataPartido dP=dFinal.getDataPartido();
            if(fase==1){
                String idEqL=Integer.toString(dP.getDataInfoPart().getIdLocal());
                String idEqV=Integer.toString(dP.getDataInfoPart().getIdVisita());
                arch.agregarString("Competiciones",2,fila,idEqL);
                arch.agregarString("Competiciones",3,fila,idEqV);
            }
            else{
                String llaveLocal=dFinal.getPreLocal();
                String llaveVisita=dFinal.getPreVisita();
                arch.agregarString("Competiciones",2,fila,llaveLocal);
                arch.agregarString("Competiciones",3,fila,llaveVisita);
            }
            arch.agregarString("Competiciones",4,fila,Boolean.toString(dFinal.getTieneSucesora()));
            arch.agregarString("Competiciones",5,fila,Boolean.toString(dFinal.getEsFinal()));

    //guardo el partido
           guardarPartido(dP);
        }
        ++fila;                 

    }
   
    private DTOCompeticiones cargarCopa()throws Exception{
                    
        //primera fila se agregan datos de la copa
        //arch.agregarString("Competiciones",0,fila,dC.getTipo().toString());

        int idC=Integer.parseInt(arch.leerString("Competiciones",1,fila));             
        String nomC=arch.leerString("Competiciones",2,fila); 
        String pathC=arch.leerString("Competiciones", 3,fila);   

        //se recuperan numero de equipos y de llaves
        int nroE=Integer.parseInt(arch.leerString("Competiciones",4,fila));
        int nroL=Integer.parseInt(arch.leerString("Competiciones",5,fila)); 
        float montoPenca=Float.parseFloat(arch.leerString("Competiciones",6,fila)); 
        int col=0;
        ++fila;   
        List<DataDivEquipo> dividendos=new ArrayList<DataDivEquipo>();
        for(col=0;col<nroE;col++){
            int idE=Integer.parseInt(arch.leerString("Competiciones", col,fila));
            float div=Float.parseFloat(arch.leerString("Competiciones", col,fila+1));  
            DataDivEquipo dDE = new DataDivEquipo(idE,"",div);
            dividendos.add(dDE);
        }
        ++fila;
       
        DataCompeticion dataC=new DataCompeticion(idC,nomC,TipoCompeticion.Copa,
                dividendos, false,pathC);
        dataC.setMontoPenca(montoPenca);
        

        //aca se cargan las apuestas a campeon
        List<DataApuestaPersistencia>apuestas= cargarApuesta();
        List<DataInfoJugCampeonatoPersistencia> InfoJugYlistaApuGoleador=cargarListaIJCYApuestas();
        List<DataInfoUsuarioPencaPersistencia> pencas=this.cargarPencas();
        List<Mensaje> foro=this.cargarForo();
       
//llaves
        List<DataLlave> llaves=new ArrayList<DataLlave>();
        for(int l=0;l<nroL;l++){
            ++fila;
            String nomL=arch.leerString("Competiciones",0,fila); 
            int fase=Integer.parseInt(arch.leerString("Competiciones",1,fila)); 
            int idEqL=0,idEqV=0;
            String llaveLocal="",llaveVisita="";
            if(fase==1){
                idEqL=Integer.parseInt(arch.leerString("Competiciones", 2,fila));
                idEqV=Integer.parseInt(arch.leerString("Competiciones", 3,fila));
            }            
            else{
                llaveLocal=arch.leerString("Competiciones", 2,fila);
                llaveVisita=arch.leerString("Competiciones", 3,fila);
            }          
            boolean tieneSucesora,esFinal;
            tieneSucesora=Boolean.parseBoolean(arch.leerString("Competiciones", 4,fila));
            esFinal=Boolean.parseBoolean(arch.leerString("Competiciones", 5,fila));            
            
            
            DataPartido dP=cargarPartido(idC,nomC,TipoCompeticion.Copa,nomL);//dataP queda fijado
            DataLlave dataL=null;
            if(fase==1){
                dataL=new DataLlave(nomL,fase, idEqL,idEqV,
                tieneSucesora,esFinal,dP);
            }
            else{
                dataL=new DataLlave(nomL,fase, llaveLocal,llaveVisita,
                tieneSucesora,esFinal,dP);
            }
            llaves.add(dataL);
        }
        //agregar a la coleecion de DTO un nuevo datatype agregando los anteriores dataLlave com
        DTOCompeticiones dTO=new DTOCompeticiones(dataC,llaves,apuestas,InfoJugYlistaApuGoleador,pencas,foro);
        ++fila;   
        return dTO;
    }
    
    
     private DTOCompeticiones cargarLiga()throws Exception{
         
        //primera fila se agregan datos de la copa
        //arch.agregarString("Competiciones",0,fila,dC.getTipo().toString());

        int idC=Integer.parseInt(arch.leerString("Competiciones",1,fila));             
        String nomC=arch.leerString("Competiciones",2,fila); 
        String pathC=arch.leerString("Competiciones", 3,fila);         

        //se recuperan numero de equipos y de criterios
        int nroE=Integer.parseInt(arch.leerString("Competiciones",4,fila));
        int nroP=Integer.parseInt(arch.leerString("Competiciones",5,fila)); 
        float montoPenca=Float.parseFloat(arch.leerString("Competiciones",6,fila)); 
        
        //agrego criterios
        ++fila;
        TipoCriterio[] criter=new TipoCriterio[3];
        for(int cri=0;cri<criter.length;cri++){    
            String tipoCriS=arch.leerString("Competiciones", cri,fila);
            if(tipoCriS.equals("DiferenciaGoles")){
                criter[cri]=TipoCriterio.DiferenciaGoles;
            }
            if(tipoCriS.equals("GolesFavor")){
                criter[cri]=TipoCriterio.GolesFavor;
            }
            if(tipoCriS.equals("Resultado")){
                criter[cri]=TipoCriterio.Resultado;
            }
         }                      
        
        //agrego equipos y dividendos
        int col=0;
        ++fila;
        
        
        List<DataDivEquipo> dividendos=new ArrayList<DataDivEquipo>();
        for(col=0;col<nroE;col++){
            int idE=Integer.parseInt(arch.leerString("Competiciones", col,fila));
            float div=Float.parseFloat(arch.leerString("Competiciones", col,fila+1));  
            DataDivEquipo dDE = new DataDivEquipo(idE,"",div);
            dividendos.add(dDE);
        }
        
        ++fila;
        DataCompeticion dataC=new DataCompeticion(idC,nomC,TipoCompeticion.Liga,
                dividendos, false,pathC);
        dataC.setMontoPenca(montoPenca);
        
        //aca cargo apuestas campeon
        List<DataApuestaPersistencia>apuestas= cargarApuesta();
        List<DataInfoJugCampeonatoPersistencia> InfoJugYlistaApuGoleador=cargarListaIJCYApuestas();
        List<DataInfoUsuarioPencaPersistencia> pencas=this.cargarPencas();
        List<Mensaje> foro=this.cargarForo();
       
//cargar partidos liga
        List<DataPartido> dataPartidos=new ArrayList<DataPartido>();
        for(int p=0;p<nroP;p++){
           DataPartido dP=cargarPartido(idC, nomC, TipoCompeticion.Liga, ""); 
           dataPartidos.add(dP);
        }
         DTOCompeticiones dTO=new DTOCompeticiones(dataC,criter,dataPartidos,apuestas,InfoJugYlistaApuGoleador,pencas, foro);   
        ++fila;   
        return dTO;                
    }
    
    private void guardarLiga(DTOCompeticiones dto)throws Exception{  
        
        DataCompeticion dC=dto.getDataCompeticion();
        //primera fila se agregan datos de la copa
        arch.agregarString("Competiciones",0,fila,dC.getTipo().toString());
        arch.agregarString("Competiciones",1,fila,Integer.toString(dC.getId()));  
        arch.agregarString("Competiciones",2,fila,dC.getNombre());           
        arch.agregarString("Competiciones",3,fila,dC.getPathImage());  
        
        List<DataDivEquipo> listDEq=dC.getDividendos();
        //numero de equipos
        arch.agregarString("Competiciones",4,fila,Integer.toString(listDEq.size()));
        //obtengo el numero de partidos   
        List<DataPartido> listaP=dto.getPartidos();
        arch.agregarString("Competiciones",5,fila,Integer.toString(listaP.size()));
        arch.agregarString("Competiciones",6,fila,Float.toString(dC.getMontoPenca()));
        //en la fila 2 se informan los criterios en caso de ser una liga
        ++fila;
        int cri=0; 
        TipoCriterio[] criter=dto.getCriterios();
        for(cri=0;cri<criter.length;cri++){                         
            arch.agregarString("Competiciones",cri,fila,criter[cri].toString());
        }         
        
        //agrego referente a equipos y dividendos
        int col=0;  
        ++fila;
        for( col=0;col<listDEq.size();col++){
            //en la fila 2 se agregan los id equipos
            arch.agregarString("Competiciones",col,fila,Integer.toString(listDEq.get(col).getId()));
            //en la fila 3 se agegan los dividendos
            arch.agregarString("Competiciones",col,fila+1,Float.toString(listDEq.get(col).getDividendo()));                    
        }
        
        //hay que recuperar los partidos definidos en la liga       
        ++fila;
        
        //guardo apuestas a campeon
        this.guardarApuesta(dto.getApuestasCampeon());
        this.guardarListaIJCYApuestas(dto.getListaInfoJugCampeonatoYApuestas());
        this.guardarPenca(dto.getPpencas());
        this.guardarForo(dto.getForo());
        
        for(int p=0;p<listaP.size();p++){
            //busco el partido
            DataPartido dP=listaP.get(p);
            this.guardarPartido(dP);
        }
        ++fila;  
    }
        
    
    private void guardarIndividual(DTOCompeticiones dto)throws Exception{

        DataCompeticion dC=dto.getDataCompeticion();
        //primera fila se agregan datos del PI
        arch.agregarString("Competiciones",0,fila,dC.getTipo().toString());
        arch.agregarString("Competiciones",1,fila,Integer.toString(dC.getId()));
        arch.agregarString("Competiciones",2,fila,dC.getNombre());
        arch.agregarString("Competiciones",3,fila,dC.getPathImage());

        DataPartido dP = null;
        if (dto.getPartidos()!= null && !dto.getPartidos().isEmpty())
        	dP=dto.getPartidos().get(0);
        //hay partido
        arch.agregarString("Competiciones",4,fila,Boolean.toString(dP!=null));
        //agrego los 2 equipos
        int idEq1=dC.getDividendos().get(0).getId();
        int idEq2=dC.getDividendos().get(1).getId();

        ++fila;
        arch.agregarString("Competiciones",0,fila,Integer.toString(idEq1));
        arch.agregarString("Competiciones",1,fila,Integer.toString(idEq2));
        if(dP!=null){
            this.guardarPartido(dP);
        }
        ++fila;
    }
    
     private DTOCompeticiones cargarIndividual()throws Exception{
        
               
        //primera fila se agregan datos de la copa
        //arch.agregarString("Competiciones",0,fila,dC.getTipo().toString());
        int idC=Integer.parseInt(arch.leerString("Competiciones",1,fila));             
        String nomC=arch.leerString("Competiciones",2,fila);
        String pathC=arch.leerString("Competiciones", 3,fila);
        boolean hayPartido=Boolean.parseBoolean(arch.leerString("Competiciones",4,fila));   
        
        ++fila;
        List<DataDivEquipo> dividendos=new ArrayList<DataDivEquipo>();
        int idE1=Integer.parseInt(arch.leerString("Competiciones",0,fila));
        int idE2=Integer.parseInt(arch.leerString("Competiciones",1,fila));         
        DataDivEquipo dDE1 = new DataDivEquipo(idE1,"",0);
        dividendos.add(dDE1);
        DataDivEquipo dDE2 = new DataDivEquipo(idE2,"",0);
        dividendos.add(dDE2);
            
        DataCompeticion dataC=new DataCompeticion(idC,nomC,TipoCompeticion.PartidoIndividual,
                dividendos, false,pathC);
        
        
        DataPartido dP=null;
        if(hayPartido){
            //cargar aumenta su fila al iniciar
            dP=cargarPartido(idC,nomC,TipoCompeticion.PartidoIndividual,"");
        }            
        
        DTOCompeticiones dTO=new DTOCompeticiones(dataC,dP);  //ser cuidadoso con el null 
        ++fila;   
        return dTO;
    }
    
      
      
    //lee la sig fila / devuelve fila en la ultima fila leida
    private DataPartido cargarPartido(int idC,String nomC,TipoCompeticion tipo,String nomL)throws Exception{
        ++fila;
        
        int idP=Integer.parseInt(arch.leerString("Competiciones",0,fila));   
        String fe=arch.leerString("Competiciones",1,fila);
        DataFechaHora fecha=new  DataFechaHora(fe);
        String lugar=arch.leerString("Competiciones",2,fila);
        
       
        int idEqL=Integer.parseInt(arch.leerString("Competiciones",3,fila));   
        int idEqV=Integer.parseInt(arch.leerString("Competiciones",4,fila));
        String nomEqL="";
        String nomEqV="";
        Dividendos dividendos=new Dividendos();
        boolean finalizo=false;
        int gL=-1;
        int pL=-1;
        int gV=-1;
        int pV=-1;
        List<Integer> jL=new ArrayList<Integer>();
        List<Integer> jV=new ArrayList<Integer>();
        List<DataEvento> eventos=new ArrayList<DataEvento>();
        //hay equipos
        if(idEqL!=-1 &&idEqV!=-1){        
            nomEqL=arch.leerString("Competiciones",5,fila);   
            nomEqV=arch.leerString("Competiciones",6,fila);             
            String div=arch.leerString("Competiciones",7,fila);
            if(!div.equals("sinDividendo")){
                Float divL=Float.parseFloat(div);
                Float divV=Float.parseFloat(arch.leerString("Competiciones",8,fila));
                Float divE=Float.parseFloat(arch.leerString("Competiciones",9,fila));                    
                dividendos=new  Dividendos(divL,divV,divE);
                finalizo=Boolean.parseBoolean(arch.leerString("Competiciones",10,fila));
                if(finalizo){                    
                    gL=Integer.parseInt(arch.leerString("Competiciones",11,fila));
                    pL=Integer.parseInt(arch.leerString("Competiciones",12,fila));
                    if(pL<0){
                        pL=0;
                    }
                    gV=Integer.parseInt(arch.leerString("Competiciones",13,fila));                    
                    pV=Integer.parseInt(arch.leerString("Competiciones",14,fila));
                    if(pV<0){
                        pV=0;
                    }
                    int nroJL=Integer.parseInt(arch.leerString("Competiciones",15,fila));
                    int nroJV=Integer.parseInt(arch.leerString("Competiciones",16,fila));
                    //se agregan los jugadores
                    ++fila;
                    int col;                    
                    for(col=0;col<nroJL;col++){
                        int idJ=Integer.parseInt(arch.leerString("Competiciones",col,fila));
                        jL.add(idJ);
                    }
                    ++fila;                    
                    for(col=0;col<nroJV;col++){
                        int idJ=Integer.parseInt(arch.leerString("Competiciones",col,fila));
                        jV.add(idJ);
                    }
                    eventos=this.cargarEvento();
                }
            }
        }
        List<DataApuestaPersistencia>apuestas= cargarApuesta();
        DataInfoPartido dIP=new DataInfoPartido (idP,idC,nomC,tipo, nomEqL,nomEqV, idEqL,idEqV,lugar,
                fecha, nomL, "","");
        java.util.Map<GolesKey,Float> golesDiv=cargarDivResExacto();
        DataPartido dP=new DataPartido (dIP,dividendos,finalizo,gL,gV,pL,pV,jL,jV,apuestas,golesDiv);
        dP.setEventos(eventos);
        return dP;
    }
    
    //guarda en la sig fila/devuelve fila en la ultima fila escrita 
    private void guardarPartido(DataPartido dP)throws Exception{
        ++fila;
        
        DataInfoPartido dIP=dP.getDataInfoPart();
        arch.agregarString("Competiciones",0,fila,Integer.toString(dIP.getIdPar())); 
        arch.agregarString("Competiciones",1,fila,dIP.getFechaHora().toString());
        arch.agregarString("Competiciones",2,fila,dIP.getLugar()); 
        //hay que ver si esta asignados los equipos d Vcio
        
        arch.agregarString("Competiciones",3,fila,Integer.toString(dP.getDataInfoPart().getIdLocal()));
        arch.agregarString("Competiciones",4,fila,Integer.toString(dP.getDataInfoPart().getIdVisita()));
        arch.agregarString("Competiciones",5,fila,dP.getDataInfoPart().getNomLocal());
        arch.agregarString("Competiciones",6,fila,dP.getDataInfoPart().getNomVisita());


        if(dP.getDividendos().getEstanAsignados()){
            arch.agregarString("Competiciones",7,fila,Float.toString(dP.getDividendos().getDividendoLocal()));
            arch.agregarString("Competiciones",8,fila,Float.toString(dP.getDividendos().getDividendoVisita()));
            arch.agregarString("Competiciones",9,fila,Float.toString(dP.getDividendos().getDividendoEmpate()));
            dP.getDataInfoPart().getIdLocal();
            dP.getDataInfoPart().getIdLocal();
            boolean finalizo=dP.getEstaFinalizado();
            arch.agregarString("Competiciones",10,fila,Boolean.toString(finalizo));            
            if(finalizo){
                arch.agregarString("Competiciones",11,fila,Integer.toString(dP.getGolesL()));
                arch.agregarString("Competiciones",12,fila,Integer.toString(dP.getPenalesL()));
                arch.agregarString("Competiciones",13,fila,Integer.toString(dP.getGolesV()));
                arch.agregarString("Competiciones",14,fila,Integer.toString(dP.getPenalesV()));

                List<Integer> listJL=dP.getJugLocales();
                arch.agregarString("Competiciones",15,fila,Integer.toString(listJL.size()));
                List<Integer> listJV=dP.getJugVisitante();
                arch.agregarString("Competiciones",16,fila,Integer.toString(listJV.size()));
                //agregar los jugadores de los equipos
                ++fila;
                int col;
                for(col=0;col<listJL.size();col++){
                   arch.agregarString("Competiciones",col,fila,Integer.toString(listJL.get(col))); 
                }

                ++fila;
                for(col=0;col<listJV.size();col++){
                   arch.agregarString("Competiciones",col,fila,Integer.toString(listJV.get(col))); 
                }
                guardarEventos(dP.getEventos());
            }            
        }
        else{
            //sin dividendos entonces tampoco esta finalizado
             arch.agregarString("Competiciones",7,fila,"sinDividendo");
        }
        guardarApuesta(dP.getApuestas()); 
        this.guardarDivResExacto(dP.getGolesDivs());
    }
    
    private void guardarApuesta(List<DataApuestaPersistencia> apuestas)throws Exception{
        ++fila;
        arch.agregarString("Competiciones",0,fila,Integer.toString(apuestas.size()));       
        for(int a =0;a<apuestas.size();a++){
            ++fila;
            DataApuestaPersistencia apu=apuestas.get(a);
            arch.agregarString("Competiciones",0,fila,apu.getTipoApuesta().toString());
            arch.agregarString("Competiciones",1,fila,Integer.toString(apu.getIdCampeonato()));
            arch.agregarString("Competiciones",2,fila,Float.toString(apu.getMontoApostado()));
            arch.agregarString("Competiciones",3,fila,apu.getFecha().toString());
            arch.agregarString("Competiciones",4,fila,apu.getEstado().toString());
            if(apu.getTipoDividendo()!=null){
                arch.agregarString("Competiciones",5,fila,apu.getTipoDividendo().toString());
            }
            else{
                arch.agregarString("Competiciones",5,fila,"");
            }
            
            arch.agregarString("Competiciones",6,fila,Integer.toString(apu.getIdEquipo()));                 
            arch.agregarString("Competiciones",7,fila,Integer.toString(apu.getIdPartido()));
            arch.agregarString("Competiciones",8,fila,apu.getNick());
            arch.agregarString("Competiciones",9,fila,Integer.toString(apu.getIdGoleador()));                 
            arch.agregarString("Competiciones",10,fila,Integer.toString(apu.getGolesLoc()));
            arch.agregarString("Competiciones",11,fila,Integer.toString(apu.getGolesVis()));  
            arch.agregarString("Competiciones",12,fila,Integer.toString(apu.getIdPaquete()));  
            
            
        }
    }
        
    private List<DataApuestaPersistencia> cargarApuesta()throws Exception{
        List<DataApuestaPersistencia> salida=new ArrayList<DataApuestaPersistencia>();
        ++fila;
        int tamanio=Integer.parseInt(arch.leerString("Competiciones",0,fila)); 
        for(int a =0;a<tamanio;a++){
            ++fila;
            
            String tA=arch.leerString("Competiciones",0,fila);
            TipoApuesta tipoA=TipoApuesta.Campeonato;
            if(tA.equals("Goleador")){
                tipoA=TipoApuesta.Goleador;
            }
            if(tA.equals("Partido")){
                tipoA=TipoApuesta.Partido;
            }
            if(tA.equals("ResExacto")){
                tipoA=TipoApuesta.ResExacto;
            }      
                    
            Integer idCamp=Integer.parseInt(arch.leerString("Competiciones",1,fila)); 
            float montoApostado=Float.parseFloat(arch.leerString("Competiciones",2,fila));  
            DataFechaHora fecha=new  DataFechaHora(arch.leerString("Competiciones",3,fila));
            EstadoApuesta estado=null;
            String est=arch.leerString("Competiciones",4,fila);
            if(est.equals("Gano")){
                estado=EstadoApuesta.Gano;
            }
            if(est.equals("Perdio")){
                estado=EstadoApuesta.Perdio;
            }
            if(est.equals("Pendiente")){
                estado=EstadoApuesta.Pendiente;
            }        
            TipoDividendo tipoD=null;
            String tip=arch.leerString("Competiciones",5,fila);
            if(tip.equals("Local")){
                tipoD=TipoDividendo.Local;
            }
            if(tip.equals("Visitante")){
                tipoD=TipoDividendo.Visitante;
            }
            if(tip.equals("Empate")){
                tipoD=TipoDividendo.Empate;
            }
            Integer idEquipo=Integer.parseInt(arch.leerString("Competiciones",6,fila));
            Integer idPartido=Integer.parseInt(arch.leerString("Competiciones",7,fila));             
            String nick=arch.leerString("Competiciones",8,fila); 
            Integer idG=Integer.parseInt(arch.leerString("Competiciones",9,fila));
            Integer golL=Integer.parseInt(arch.leerString("Competiciones",10,fila));
            Integer golV=Integer.parseInt(arch.leerString("Competiciones",11,fila));
            Integer idPaq=Integer.parseInt(arch.leerString("Competiciones",12,fila));
            salida.add(new DataApuestaPersistencia(tipoA,idCamp,montoApostado,fecha,estado,tipoD,idEquipo,idPartido,nick,idG,golL,golV,idPaq));
            
        }
        return salida;
     }    
    
    private void guardarEventos(List<DataEvento> eventos)throws Exception{
        ++fila;
        arch.agregarString("Competiciones",0,fila,Integer.toString(eventos.size()));       
        for(int i =0;i<eventos.size();i++){
            ++fila;   
            DataEvento e=eventos.get(i);
            arch.agregarString("Competiciones",0,fila,Integer.toString(e.getMinuto()));
            arch.agregarString("Competiciones",1,fila,e.getPeriodo().toString());
            //primer jugador 
            if(e.getJugador1()==null){
                arch.agregarString("Competiciones",2,fila,"");
                arch.agregarString("Competiciones",3,fila,"");
            }
            else{
                arch.agregarString("Competiciones",2,fila,e.getJugador1().getNombre());
                arch.agregarString("Competiciones",3,fila,Integer.toString(e.getJugador1().getId()));
                
            }
            //segundo jugador
            if(e.getJugador2()==null){
                arch.agregarString("Competiciones",4,fila,"");
                arch.agregarString("Competiciones",5,fila,"");
            }
            else{
                arch.agregarString("Competiciones",4,fila,e.getJugador2().getNombre());
                arch.agregarString("Competiciones",5,fila,Integer.toString(e.getJugador2().getId()));                
            }
            arch.agregarString("Competiciones",6,fila,e.getTipoevento().toString());
            arch.agregarString("Competiciones",7,fila,Boolean.toString(e.getAmarilla()));
        }
    }
     
     private List<DataEvento> cargarEvento()throws Exception{
        List<DataEvento> salida=new ArrayList<DataEvento>();
        ++fila;
        int tamanio=Integer.parseInt(arch.leerString("Competiciones",0,fila)); 
        for(int a =0;a<tamanio;a++){
            ++fila;
            Integer minuto=Integer.parseInt(arch.leerString("Competiciones",0,fila)); 
            
            String tS=arch.leerString("Competiciones",1,fila);
            TipoPeriodo tipoP=TipoPeriodo.PrimerAlargue;  
            if(tS.equals("PrimerTiempo")){
                tipoP=TipoPeriodo.PrimerTiempo; 
            }
            if(tS.equals("SegundoAlargue")){
                tipoP=TipoPeriodo.SegundoAlargue; 
            }
            if(tS.equals("SegundoTiempo")){
                tipoP=TipoPeriodo.SegundoTiempo; 
            }
            
            DataIdNombre j1=null;            
            
            String nombre1=arch.leerString("Competiciones",2,fila);            
            //en caso de que el nombre no sea nulo se crea el jugador
            if(!nombre1.equals("")){
                Integer id1=Integer.parseInt(arch.leerString("Competiciones",3,fila));
                j1=new DataIdNombre(id1,nombre1);
            }
            
            DataIdNombre j2=null;
            String nombre2=arch.leerString("Competiciones",4,fila);            
            //en caso de que el nombre no sea nulo se crea el jugador
            if(!nombre2.equals("")){
                Integer id2=Integer.parseInt(arch.leerString("Competiciones",5,fila));
                j2=new DataIdNombre(id2,nombre2);
            }
            
            String tES=arch.leerString("Competiciones",6,fila);
            TipoEvento tipoE=TipoEvento.Gol;  
            if(tES.equals("Sustitucion")){
                tipoE=TipoEvento.Sustitucion; 
            }
            if(tES.equals("Tarjeta")){
                tipoE=TipoEvento.Tarjeta; 
            }            
            
            
            boolean esAmarilla=Boolean.parseBoolean(arch.leerString("Competiciones",7,fila));
            salida.add(new  DataEvento(minuto,tipoP,j1,j2,tipoE,esAmarilla));
            
        }
        return salida;
     }   
      
     
      public List<DataInfoJugCampeonatoPersistencia> cargarListaIJCYApuestas()throws Exception {
         List<DataInfoJugCampeonatoPersistencia> salida=new ArrayList<DataInfoJugCampeonatoPersistencia>();
         ++fila;
         int tamanio=Integer.parseInt(arch.leerString("Competiciones",0,fila)); 
         for(int a =0;a<tamanio;a++){
            ++fila;
            
            Integer idJ=Integer.parseInt(arch.leerString("Competiciones",0,fila));
            String nombreJ=arch.leerString("Competiciones",1,fila);
            Float div=Float.parseFloat(arch.leerString("Competiciones",2,fila));
            Integer goles=Integer.parseInt(arch.leerString("Competiciones",3,fila));
            DataGoleador dataG=new DataGoleador(idJ,nombreJ,div,goles);
            DataInfoJugCampeonatoPersistencia dataP=new DataInfoJugCampeonatoPersistencia (dataG,this.cargarApuesta());
            salida.add(dataP);
          }
         return salida;
      }
      
      //guardarlista
      public void  guardarListaIJCYApuestas(List<DataInfoJugCampeonatoPersistencia> lista)throws Exception{
            ++fila;
            arch.agregarString("Competiciones",0,fila,Integer.toString(lista.size()));       
            for(int i =0;i<lista.size();i++){
                ++fila;   
                DataInfoJugCampeonatoPersistencia dIJCP=lista.get(i);
                DataGoleador dG=dIJCP.getDataGoleador();
                arch.agregarString("Competiciones",0,fila,Integer.toString(dG.getId()));
                arch.agregarString("Competiciones",1,fila,dG.getNombre());
                arch.agregarString("Competiciones",2,fila,Float.toString(dG.getDividendo()));
                arch.agregarString("Competiciones",3,fila,Integer.toString(dG.getGoles()));            
                dIJCP.getApuestasAGoleador();

                guardarApuesta(dIJCP.getApuestasAGoleador());
            }
      }
      //guardar div resultado exacto
       public void guardarDivResExacto(java.util.Map<GolesKey,Float> lista)throws Exception{
            ++fila;
            arch.agregarString("Competiciones",0,fila,Integer.toString(lista.size()));       
            for (java.util.Map.Entry<GolesKey,Float>  entry: lista.entrySet()) {
                ++fila;
                Float div = entry.getValue();
                GolesKey gk=entry.getKey();
                arch.agregarString("Competiciones",0,fila,Integer.toString(gk.getGolesLocal()));
                arch.agregarString("Competiciones",1,fila,Integer.toString(gk.getGolesVisita()));
                arch.agregarString("Competiciones",2,fila,Float.toString(div));
            }
           
      }
      //cargar div resultado exacto
      public java.util.Map<GolesKey,Float> cargarDivResExacto()throws Exception{
          java.util.Map<GolesKey,Float> salida=new java.util.HashMap<GolesKey,Float>();
          ++fila;
         int tamanio=Integer.parseInt(arch.leerString("Competiciones",0,fila)); 
         for(int a =0;a<tamanio;a++){
            ++fila;            
            Integer golL=Integer.parseInt(arch.leerString("Competiciones",0,fila));
            Integer golV=Integer.parseInt(arch.leerString("Competiciones",1,fila));
            Float div=Float.parseFloat(arch.leerString("Competiciones",2,fila));
            GolesKey gk=new GolesKey (golL,golV);            
            salida.put(gk,div);
          }
         return salida;
      }
      //cargar pencas
      public List<DataInfoUsuarioPencaPersistencia> cargarPencas()throws Exception{
          List<DataInfoUsuarioPencaPersistencia> salida=new ArrayList<DataInfoUsuarioPencaPersistencia>();
          ++fila;
          int tamU=Integer.parseInt(arch.leerString("Competiciones",0,fila));
          for(int a =0;a<tamU;a++){
                ++fila;
                String nick=arch.leerString("Competiciones",0,fila);
                int puntos=Integer.parseInt(arch.leerString("Competiciones",1,fila));
                int tamP=Integer.parseInt(arch.leerString("Competiciones",2,fila));

                List<DataIUPPPersistencia> listaP=new ArrayList<DataIUPPPersistencia>();
                for(int p =0;p<tamP;p++){
                    ++fila;
                    Integer idPartido=Integer.parseInt(arch.leerString("Competiciones",0,fila));
                    Integer golL=Integer.parseInt(arch.leerString("Competiciones",1,fila));
                    Integer golV=Integer.parseInt(arch.leerString("Competiciones",2,fila));
                    DataIUPPPersistencia dataPP= new DataIUPPPersistencia(idPartido,golL,golV);
                    listaP.add(dataPP);
                }
                DataInfoUsuarioPencaPersistencia dataPU=new DataInfoUsuarioPencaPersistencia(nick,puntos,listaP);
                salida.add(dataPU);
          }
         return salida;
      }
      
      
      //guardarPencas
       public void guardarPenca(List<DataInfoUsuarioPencaPersistencia> lista)throws Exception{
            ++fila;
            arch.agregarString("Competiciones",0,fila,Integer.toString(lista.size()));
            for (int i=0;i<lista.size();i++){
                ++fila;
                DataInfoUsuarioPencaPersistencia dataPU=lista.get(i);
                List<DataIUPPPersistencia> listaP=dataPU.getListaPartidosPencaPersistencia();
                arch.agregarString("Competiciones",0,fila,dataPU.getNick());
                arch.agregarString("Competiciones",1,fila,Integer.toString(dataPU.getPuntos()));
                //el numero de partidos en la penca estan en la misma fila del xls
                arch.agregarString("Competiciones",2,fila,Integer.toString(listaP.size()));
                for (int p=0;p<listaP.size();p++){
                    ++fila;
                    DataIUPPPersistencia dataPP=listaP.get(p);
                    arch.agregarString("Competiciones",0,fila,Integer.toString(dataPP.getIdPartido()));
                    arch.agregarString("Competiciones",1,fila,Integer.toString(dataPP.getGolesLocal()));
                    arch.agregarString("Competiciones",2,fila,Integer.toString(dataPP.getGolesVisita()));
                }
            }
      }
       
      //cargar foro
      public List<Mensaje> cargarForo()throws Exception{
          List<Mensaje> salida=new ArrayList<Mensaje>();
          ++fila;
          int tamU=Integer.parseInt(arch.leerString("Competiciones",0,fila));
          for(int a =0;a<tamU;a++){
                ++fila;
                String emisor=arch.leerString("Competiciones",0,fila);
                String receptor=arch.leerString("Competiciones",1,fila);
                boolean publico=Boolean.parseBoolean(arch.leerString("Competiciones",2,fila));
                String texto=arch.leerString("Competiciones",3,fila);                
                Mensaje mensaje=new Objetos.Mensaje(emisor,receptor,publico,texto);
                salida.add(mensaje);
          }
         return salida;
      }
      
      
      //guardar foro
       public void guardarForo(List<Mensaje> lista)throws Exception{
            ++fila;
            arch.agregarString("Competiciones",0,fila,Integer.toString(lista.size()));
            for (int i=0;i<lista.size();i++){
                ++fila;
                Mensaje mensaje=lista.get(i);
                arch.agregarString("Competiciones",0,fila,mensaje.getEmisor());
                arch.agregarString("Competiciones",1,fila,mensaje.getReceptor());
                arch.agregarString("Competiciones",2,fila,Boolean.toString(mensaje.isPublico()));
                arch.agregarString("Competiciones",3,fila,mensaje.getMensaje());
            }
      }       
}
