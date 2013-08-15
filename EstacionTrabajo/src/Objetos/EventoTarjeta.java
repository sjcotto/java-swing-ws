package Objetos;


public class EventoTarjeta extends Evento {
    //Atributos
    private Objetos.Jugador jugador;
    private boolean amarilla;


    public EventoTarjeta(int m,DataTypes.TipoPeriodo tp,Jugador j,boolean amarilla) {
	super(m,tp);
        this.jugador=j;
        this.amarilla = amarilla;
    }


    //Funciones get y set
    public DataTypes.DataEvento getDataEvento(){
	    return new DataTypes.DataEvento(this.minuto,this.periodo,jugador.getDataBasica(),null,DataTypes.TipoEvento.Tarjeta,this.amarilla);
    }

}
    