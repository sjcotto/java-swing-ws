package Objetos;import java.util.List;
import java.util.LinkedList;
import DataTypes.DataApuestaPersistencia;
import java.util.ArrayList;
import DataTypes.DataInfoJugCampeonatoPersistencia;
public class InfoJugadorCampeonato implements Comparable{
    private int goles;
    private float dividendoGoleador;
    private Jugador jugador;
    private Campeonato campeonato;
   private List<ApuestaGoleador> apuestas;
    
    public InfoJugadorCampeonato(Jugador jugador,float dividendo,Campeonato campeonato){
        this.goles=0;
        this.dividendoGoleador=dividendo;
        this.jugador=jugador;
        this.campeonato=campeonato;
        apuestas = new LinkedList();                
    }

    public Campeonato getCampeonato(){
        return this.campeonato;
    }
    
    public void agregarNavGoleadorApuesta (Apuesta a){
        apuestas.add((ApuestaGoleador)a);
    }

    // NUEVO!!
    public void notificar(boolean gano){
        for(int i=0;i<apuestas.size();i++){
            apuestas.get(i).notificar(gano);
        }
    }

    

    public void setGoles(int g){
        this.goles = g;
    }
    
    public int getGoles(){
        return this.goles;
    }
    public float getDividendoGoleador(){
        return this.dividendoGoleador;
    }
    //luego ver cual resulta mas util
    public int getIdJugador(){
        return this.jugador.getId();
    }
    public Jugador getJugador(){
        return this.jugador;
    }
    
    
    @Override
    public int compareTo (Object i){
        //ordena de mayor a menor
         InfoJugadorCampeonato iJC = (InfoJugadorCampeonato) i;
         if (this.goles> iJC.getGoles()){
            return -1;
         }
         if (this.goles< iJC.getGoles()){
            return 1;
         }
         return 0;
     }
    public DataTypes.DataGoleador getDataDivGoleador(){
        return new DataTypes.DataGoleador(jugador.getId(),jugador.getNombre(),dividendoGoleador,goles);
    }
    
    public DataInfoJugCampeonatoPersistencia getDIJCPersistencia(int idCamp,int idPartido){
        List<DataApuestaPersistencia> ldapu=new ArrayList<DataApuestaPersistencia>();
        for(int i=0;i<apuestas.size();i++){
            DataApuestaPersistencia dDA=apuestas.get(i).getDAP(idCamp,idPartido);
            ldapu.add(dDA);
        }
        DataInfoJugCampeonatoPersistencia salida= new DataInfoJugCampeonatoPersistencia(getDataDivGoleador(),ldapu);
        return salida;
    }

    public DataTypes.DataGoleador getDataJugadorCampeonato() {
        return new DataTypes.DataGoleador(this.jugador.getId(),this.jugador.getNombre(), dividendoGoleador, goles);
    }
}
