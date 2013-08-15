package Objetos;


public class EventoSustitucion extends Evento {
    //Atributos
    private Objetos.Jugador jugador1;
    private Objetos.Jugador jugador2;
    


    public EventoSustitucion(int m,DataTypes.TipoPeriodo tp,Jugador j1,Jugador j2) {
	super(m,tp);
        this.jugador1=j1;
        this.jugador2=j2;
    }


    //Funciones get y set
    public DataTypes.DataEvento getDataEvento(){
	    return new DataTypes.DataEvento(this.minuto,this.periodo,jugador1.getDataBasica(),jugador2.getDataBasica(),DataTypes.TipoEvento.Sustitucion,false);
    }

}
    