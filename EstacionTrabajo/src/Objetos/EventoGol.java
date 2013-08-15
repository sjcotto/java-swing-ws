package Objetos;


public class EventoGol extends Evento {
    //Atributos
    private Objetos.Jugador jugador;
    


    public EventoGol(int m,DataTypes.TipoPeriodo tp,Jugador j) {
	super(m,tp);
        this.jugador=j;
    }


    //Funciones get y set
    public DataTypes.DataEvento getDataEvento(){
	    return new DataTypes.DataEvento(this.minuto,this.periodo,jugador.getDataBasica(),null,DataTypes.TipoEvento.Gol,false);
    }

}
    