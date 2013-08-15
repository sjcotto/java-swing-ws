package Objetos;


public class InfoEquipoCopa extends InfoEquipoCampeonato {
    private boolean perteneceLlave;

    public InfoEquipoCopa(float dc, Equipo e, Campeonato c) {
	super(dc,e,c);
	perteneceLlave = false;
    }

    public boolean getPerteneceLlave () {
        return this.perteneceLlave;
    }
    public void setPerteneceLlave(boolean pertenece){
        this.perteneceLlave=pertenece;
    }    

}
