package Objetos;


public abstract class Evento {
    //Atributos
    protected int minuto;
    protected DataTypes.TipoPeriodo periodo;


    public Evento(int m,DataTypes.TipoPeriodo tp) {
	this.minuto = m;
        this.periodo =tp;
    }


    //Funciones get y set
    public int getMinuto(){
	    return this.minuto;
    }
    
    public DataTypes.TipoPeriodo getTipoPeriodo(){
	    return this.periodo;
    }
    
    public void setMinuto(int m){
	    this.minuto =m;
    }
    
    public void setTipoPeriodo(DataTypes.TipoPeriodo tp){
	    this.periodo=tp;
    }

    public abstract DataTypes.DataEvento getDataEvento();
}
    