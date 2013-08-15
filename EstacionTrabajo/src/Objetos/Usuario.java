/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

import DataTypes.DataFechaHora;
import DataTypes.DataDetalleApuesta;
import DataTypes.DataApuesta;
import DataTypes.DataApuestaGoleador;
import DataTypes.DataApuestaResExacto;
import Controladores.ControladorFecha;
import Controladores.ControladorCompeticiones;
import DataTypes.TipoDividendo;
import java.util.*;
import java.util.ArrayList;
import java.util.Collections;
import DataTypes.DataPaqueteApuestasPersistencia;

/**
 *
 * @author tprog082
 */
public class Usuario {

    private String nombre;
    private String email;
    private String nick;
    private String dir;
    private String pass;
    private DataFechaHora fechaNac;
    private String ci;
    private String pais;
    private DataTypes.TipoSexo sexo;
    private float saldo;

    private List<Apuesta> apuestas;

    private Map<Integer,PaqueteApuestas>  paquetesDeApuestas;
	
	private int idPaq=0;

   private boolean notPartido;
    private boolean notApuesta;
    private boolean notForo;

     public Usuario(String nom, String email,String nick,String dir, String pass,
            DataFechaHora fNac, String ci,String pais,DataTypes.TipoSexo sexo){

        this.nombre=nom;
        this.ci=ci;
        this.dir=dir;
        this.email=email;
        this.fechaNac=fNac;
        this.saldo=0;
        this.nick=nick;
        this.pais=pais;
        this.pass=pass;
        this.sexo= sexo;
        this.apuestas = new ArrayList<Apuesta>();
	this.paquetesDeApuestas = new HashMap<Integer,PaqueteApuestas>();
        this.notApuesta=false;
        this.notForo=false;
        this.notPartido=false;
    }

    public void setSaldo(float s){
        this.saldo=s;
    }

    public String getNombre(){
        return nombre;
    }

    public String getEmail(){
        return email;
    }

    public String getNick(){
        return nick;
    }

    public String getDir(){
        return dir;
    }

    public String getPass(){
        return this.pass;
    }

    public String getCI(){
        return ci;
    }

    public String getPais(){
        return pais;
    }

    public DataTypes.TipoSexo getSexo(){
        return sexo;
    }

    public DataFechaHora getFechaNac(){
        return fechaNac;
     }

    public float getSaldo(){
        return saldo;
    }

    public List<DataApuesta> mostrarHistorialApuestas
                (DataFechaHora fechaIni, DataFechaHora fechaFin) {

        List<DataApuesta> lista = new ArrayList<DataApuesta>();
        for (Apuesta apuesta: this.apuestas) {
            DataFechaHora fechaAp = apuesta.getFechaApuesta();
            if (fechaAp.compareTo(fechaIni)>=0 && fechaAp.compareTo(fechaFin) <= 0){
                DataApuesta dataAp = apuesta.getDataApuesta();
                dataAp.setFecha(fechaAp);
                lista.add(dataAp);
            }
        }

        Collections.sort(lista);
        return lista;
    }


    public Apuesta crearApuesta (DataApuesta da, boolean esPartido, TipoDividendo tipo){

        Apuesta a=null;
        //
        DataFechaHora f = ControladorFecha.getInstance().getFecha();
        //
        if (!esPartido) {
            
            if (da instanceof DataApuestaGoleador){
                DataApuestaGoleador dataApGol = (DataApuestaGoleador) da;

                ControladorCompeticiones contrComp = new ControladorCompeticiones();
                int idJug = dataApGol.getJugador().getId();
                int idComp = dataApGol.getCompeticion().getId();
                Campeonato camp = (Campeonato) contrComp.buscarCompeticion(idComp);

                InfoJugadorCampeonato infJugCamp = camp.getInfoJugadorCampeonato(idJug);

                a = new ApuestaGoleador(dataApGol.getMonto(),f,DataTypes.EstadoApuesta.Pendiente,this,infJugCamp);
                infJugCamp.agregarNavGoleadorApuesta(a);
            } else
                a = new ApuestaCampeonato(da.getMonto(), f, DataTypes.EstadoApuesta.Pendiente, this);

        } else {
            if (tipo!=null) {
                a = new ApuestaPartido(da.getMonto(),tipo,f,DataTypes.EstadoApuesta.Pendiente,this);
            }
            else {// Es por resultado exacto
                DataApuestaResExacto dataApResEx = (DataApuestaResExacto)da;
                a = new ApuestaResultadoExacto(da.getMonto(),f,DataTypes.EstadoApuesta.Pendiente,
                        this,dataApResEx.getGolesL(), dataApResEx.getGolesV());
                
            }
        }
        

        saldo -= da.getMonto();
        apuestas.add(a);
        return a;
    }


    //NUEVOOOOOOOOOOO!!!
    public List<DataDetalleApuesta> getDetallesApuestasGuardar(){
        List<DataDetalleApuesta> lista = new ArrayList<DataDetalleApuesta>();
        for (Apuesta apuesta: this.apuestas) {
           lista.add(apuesta.getDataDetalleApuesta());
}
        Collections.sort(lista);
        return lista;
    }

    //18-10 solo agrega la apuesta
    public void agregarApuestaCargar(Apuesta a,int idP){
        apuestas.add(a);
        if(idP!=-1){
            PaqueteApuestas pA=paquetesDeApuestas.get(idP);
            pA.agregarApuestaPaquete(a);
            a.agregarPaqueteApuesta(pA);
        }
        
    }
    public void gano(float aumentar){
        this.saldo+=aumentar;
    }
    //NUEVOOOOOOOOOOO!!!
	
	// VER PAQUETE APUESTAS
   
    //11-10
    
     public List<DataTypes.DataPaqueteApuestas> obtenerPaquetesDeApuestas() {
        List<DataTypes.DataPaqueteApuestas> listDataPaqApu = new ArrayList<DataTypes.DataPaqueteApuestas>();        
        for (Map.Entry<Integer, PaqueteApuestas> entry : this.paquetesDeApuestas.entrySet()) {
            DataTypes.DataPaqueteApuestas dataPaqApu =entry.getValue().getDataPaqueteApuestas();
            listDataPaqApu.add(dataPaqApu);
        }

        return listDataPaqApu;
    }


     //17-10
    public void agregarPaqueteApuestas(PaqueteApuestas paq) {
        this.paquetesDeApuestas.put(++this.idPaq,paq);
        
    }
    //utilizado a la hora de cargar los paquetes
    public void agregarPaqueteApuestasPersistencia(List<DataPaqueteApuestasPersistencia> lista) {
        for(int i=0;i<lista.size();i++){
            DataPaqueteApuestasPersistencia dPAP=lista.get(i);
            PaqueteApuestas paq=new PaqueteApuestas(dPAP,this);
            ++this.idPaq;//para que quede pronto al finalizar toda la carga no se utiliza al cargar
            this.paquetesDeApuestas.put(paq.getId(),paq);
        }
    }
    
     public List<DataPaqueteApuestasPersistencia > getDataPaqueteApuestasPersistencia() {

        List<DataPaqueteApuestasPersistencia> listDataPaqApu = new ArrayList<DataPaqueteApuestasPersistencia>();
        for (Map.Entry<Integer, PaqueteApuestas> entry : this.paquetesDeApuestas.entrySet()) {
            DataPaqueteApuestasPersistencia dataPaqApu =entry.getValue().getDataPAPersistencia();
            listDataPaqApu.add(dataPaqApu);
    }
        return listDataPaqApu;
    }
    
	//se retorna el id del nuevo paquete
    public int getIdPaqueteNuevo(){
        return this.idPaq+1;
    }
	// VER PAQUETE APUESTAS
	
	public float getTotalMontoApostado(){
        if (apuestas.isEmpty())
            return 0;
        
        float res = 0;
        for (Apuesta a : apuestas)
            if (a != null)
                res += a.getMonto();
        return res;
    }
    public float getGananciaTotalApuestas(){
        if (apuestas.isEmpty())
            return 0;
        
        float gan = 0;
        for (Apuesta a : apuestas){
            if (a!=null && a.getEstadoApuesta()==DataTypes.EstadoApuesta.Gano){
                gan += a.getDataApuesta().getBeneficio();
            }
        }
        return gan;
    }
 

    public DataTypes.DataUsuario getDataUsuario(){
        return new DataTypes.DataUsuario(nombre,nick,email,dir,ci,fechaNac,pais,sexo,saldo,notApuesta,notForo,notPartido);
    }
     public boolean isNotApuesta() {
        return notApuesta;
    }

    public void setNotApuesta(boolean notApuesta) {
        this.notApuesta = notApuesta;
    }

    public boolean isNotForo() {
        return notForo;
    }

    public void setNotForo(boolean notForo) {
        this.notForo = notForo;
    }

    public boolean isNotPartido() {
        return notPartido;
    }

    public void setNotPartido(boolean notPartido) {
        this.notPartido = notPartido;
    }
	
}
