
package Objetos;
import DataTypes.DataFechaHora;
import DataTypes.TipoApuesta;
import DataTypes.EstadoApuesta;
import DataTypes.DataApuestaPersistencia;

//************** PAQUETE APUESTAS *******************//
import DataTypes.DataApuesta;
import DataTypes.DataApuestaCampeon;
import DataTypes.DataEquipo;
import DataTypes.DataCompeticion;
//************** PAQUETE APUESTAS *******************//

public class ApuestaCampeonato extends Apuesta{
    private InfoEquipoCampeonato campeonato_apuesta; 
    
    public ApuestaCampeonato(float monto, DataFechaHora f,EstadoApuesta estado,Usuario u){
        super(monto,f,u);
        this.campeonato_apuesta = null;
        this.estado=estado;
    }
    
    public void agregarNavApuestaInfoCamp (InfoEquipoCampeonato infoC){
        campeonato_apuesta = infoC;
    }
    public float getDividendo(){
        return campeonato_apuesta.getDividendoCampeonato();
    }

    public TipoApuesta getTipoApuesta() {
        return TipoApuesta.Campeonato;
    }
    
    public int getEquipo(){
        return this.campeonato_apuesta.getEquipo().getId();
}
    //18-10
    public DataApuestaPersistencia getDAP(int idCamp,int idPartido){
        int idP=-1;
        if(this.paqueteApuestas!=null){
            idP=this.paqueteApuestas.getId();
    }
        return new DataApuestaPersistencia(this.getTipoApuesta(),idCamp,montoApostado,fecha,estado,null,
                campeonato_apuesta.getEquipo().getId(), idPartido,this.user.getNick(),-1,-1,-1,idP);

    }
	
	//************** PAQUETE APUESTAS *******************//
    public DataApuesta getDataApuesta() {
        //public DataApuestaCampeon(float monto, float saldoNuevo, float beneficio,
        //float dividendo, DataEquipo dataE, DataCompeticion dataC){
        float montoA = this.montoApostado;
        float div = this.campeonato_apuesta.getDividendoCampeonato();
        float ben = montoA*div;
        float saldoN = ben+this.user.getSaldo();
        DataEquipo dataE = this.campeonato_apuesta.getEquipo().getDataEquipo();
        DataCompeticion dataC = this.campeonato_apuesta.campeonato.getDataCompeticion();

        int paq = -1;
        if (this.paqueteApuestas!=null)
            paq = this.paqueteApuestas.getId();

        return new DataApuestaCampeon(montoA,saldoN,ben,div,dataE,dataC,this.estado,paq);
    }
    //************** PAQUETE APUESTAS *******************//
    
}
