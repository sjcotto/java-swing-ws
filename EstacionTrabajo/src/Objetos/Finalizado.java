
package Objetos;
import DataTypes.DataResumen;
import DataTypes.DataJugPartido;
import DataTypes.TipoDividendo;
import DataTypes.DataIdNombre;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;

public class Finalizado extends EstadoPartido{
    private int golesLocal,golesVisita,penalLocal,penalVisita;
    private Map<Integer,Jugador> JugadorLocal;
    private Map<Integer,Jugador> JugadorVisita;

    private List<Evento> eventos=null;


    public Finalizado(DataResumen dataR){
        golesLocal=dataR.getGolesLocal();
        golesVisita=dataR.getGolesVisita();
        penalLocal=dataR.getpenalLocal();
        penalVisita=dataR.getpenalVisita();
        JugadorLocal = new HashMap<Integer,Jugador>();
        JugadorVisita = new HashMap<Integer,Jugador>();
        this.eventos = new ArrayList<Evento>();

    }

    public int getGolesLocal() {
        return this.golesLocal;
    }

    public int getGolesVisitante() {
        return this.golesVisita;
    }

    public int getPenalesLocal(){
        return this.penalLocal;
    }

    public int getPenalesVisitante() {
        return this.penalVisita;
    }

    // NUEVO!!!!
     public TipoDividendo getResultado(){
        if(golesLocal+penalLocal>golesVisita+penalVisita){
            return TipoDividendo.Local;
        }
        if(golesLocal+penalLocal<golesVisita+penalVisita){
            return TipoDividendo.Visitante;
        }
        if(golesLocal+penalLocal==golesVisita+penalVisita){
            return TipoDividendo.Empate;
        }
        return null;
    }

    public boolean ganoLocal(){
        if(golesLocal+penalLocal>golesVisita+penalVisita){
            return true;
        }
        else{
            return false;
        }

    }

    /// FINALIZAR PARTIDO
    public void agregarJugadores(Jugador jugador,boolean esLocal){
        if(esLocal){
            JugadorLocal.put(jugador.getId(), jugador);
        }
        if(!esLocal){
            JugadorVisita.put(jugador.getId(), jugador);
        }
     //nuevo eventos
    }

    public List<DataTypes.DataEvento>  obtenerDataEventos(){
        List<DataTypes.DataEvento> list=new ArrayList<DataTypes.DataEvento>();
        for(Evento e:this.eventos){
            list.add(e.getDataEvento());
        }
        return list;
    }

    public void agregarEventos(List<DataTypes.DataEvento> lista)  {
        //para cada uno de los eventos hay que crear las instancias y setearles los jugadores.

        //precondicion, antes de llamar a esta funcion, se debe llamar a agregarJugadores, si o si
        Evento e = null;
        for (DataTypes.DataEvento d : lista){
            //obtenemos el primer jugador

            Jugador j = JugadorLocal.get(d.getJugador1().getId());
            if (j==null){ //si no es local, entonces!! que lo busque en la visita
                j = JugadorVisita.get(d.getJugador1().getId());
            }
            if (d.getTipoevento()==DataTypes.TipoEvento.Sustitucion){//buscamos el jugador 2
                Jugador j2 = JugadorLocal.get(d.getJugador2().getId());
                if (j2==null){ //si no es local, entonces!! que lo busque en la visita
                    j2 = JugadorVisita.get(d.getJugador2().getId());
                }
                e = new Objetos.EventoSustitucion(d.getMinuto(), d.getPeriodo(), j, j2);
            }
            if (d.getTipoevento()==DataTypes.TipoEvento.Gol){
                e = new Objetos.EventoGol(d.getMinuto(), d.getPeriodo(), j);
            }
            else if (d.getTipoevento()==DataTypes.TipoEvento.Tarjeta){
                e = new Objetos.EventoTarjeta(d.getMinuto(), d.getPeriodo(), j,d.getAmarilla());
            }
            eventos.add(e);
        }
    }
    /// FIN FINALIZAR PARTIDO

    // VER DETALLES COMPETICION
     public DataJugPartido getDataJugPartido(int idP) {
        List<DataIdNombre> listNomJugLoc = new ArrayList<DataIdNombre>();
        List<DataIdNombre> listNomJugVis = new ArrayList<DataIdNombre>();
        for (Map.Entry<Integer,Jugador> entry : JugadorLocal.entrySet()) {
	    Jugador partJugL = entry.getValue();
            DataIdNombre nomL = partJugL.getDataBasica();
            listNomJugLoc.add(nomL);
        }
        for (Map.Entry<Integer,Jugador> entry : JugadorVisita.entrySet()) {
	    Jugador partJugV = entry.getValue();
            DataIdNombre nomV = partJugV.getDataBasica();
            listNomJugVis.add(nomV);
        }
        DataJugPartido dataJugPart = new DataJugPartido(idP,
                                         listNomJugLoc,listNomJugVis);

        return dataJugPart;
    }
     // VER DETALLES COMPETICION
     //eliel
     public List<Integer> getPartidoJugador(boolean esLocal){
        List <Integer>salida=new ArrayList<Integer>();
        if(esLocal){
            for (Map.Entry<Integer,Jugador> entry : JugadorLocal.entrySet()) {
                salida.add(entry.getKey());
            }
        }else{
            for (Map.Entry<Integer,Jugador> entry : JugadorVisita.entrySet()) {
                salida.add(entry.getKey());
            }
        }
        return salida;
     }


     
}
