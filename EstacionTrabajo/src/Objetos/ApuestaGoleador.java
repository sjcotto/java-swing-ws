/// APUESTA GOLEADOR

package Objetos;

import DataTypes.DataApuestaPersistencia;
import DataTypes.TipoApuesta;
import DataTypes.DataFechaHora;
import DataTypes.EstadoApuesta;

//************** PAQUETE APUESTAS *******************//
import DataTypes.DataApuesta;
import DataTypes.DataApuestaGoleador;
import DataTypes.DataJugador;
import DataTypes.DataCompeticion;
//************** PAQUETE APUESTAS *******************//

public class ApuestaGoleador extends Apuesta {
    private InfoJugadorCampeonato goleador;

    public ApuestaGoleador(float monto, DataFechaHora fec, EstadoApuesta est, Usuario u, InfoJugadorCampeonato jug) {
        super(monto,fec,u);
        this.goleador = jug;
        this.estado = est;
    }

    public TipoApuesta getTipoApuesta() {
        return TipoApuesta.Goleador;
    }

    public float getDividendo(){
        return this.goleador.getDividendoGoleador();
    }

	public void setInfoJugadorCampeonato (InfoJugadorCampeonato infoG){
        this.goleador = infoG;
    }
  //18-10
    public DataApuestaPersistencia getDAP(int idCamp,int idPartido){
        int idP=-1;
        if(this.paqueteApuestas!=null){
            idP=this.paqueteApuestas.getId();
    }
        return new DataApuestaPersistencia(this.getTipoApuesta(),idCamp,this.montoApostado,
            this.fecha,this.estado,null,-1, idPartido,this.user.getNick(),this.goleador.getIdJugador(),-1,-1,idP);
    }


    //************** PAQUETE APUESTAS *******************//
    public DataApuesta getDataApuesta() {
        //public DataApuestaGoleador(float monto, float saldoNuevo, float beneficio,
        //  float dividendo, DataJugador dataJ,DataCompeticion dataC) {
        float montoA = this.montoApostado;
        float div = this.goleador.getDividendoGoleador();
        float ben = montoA*div;
        float saldoN = ben+this.user.getSaldo();
        DataJugador dataJ = this.goleador.getJugador().getDataJugador();
        DataCompeticion dataC = this.goleador.getCampeonato().getDataCompeticion();

        int paq = -1;
        if (this.paqueteApuestas!=null)
            paq = this.paqueteApuestas.getId();

        return new DataApuestaGoleador(montoA,saldoN,ben,div,dataJ,dataC,this.estado,paq);
    }
    //************** PAQUETE APUESTAS *******************//
	
	//PERSISTENCIA
    //public DataTypes.DataApuestaPersistencia getDAP(int idCamp,int idPartido){
    //    return new DataTypes.DataApuestaPersistencia(idCamp,montoApostado,
    //           fecha,estado,null,-1, idPartido,this.user.getNick());
    //}
	//PERSISTENCIA
	
	
	
}