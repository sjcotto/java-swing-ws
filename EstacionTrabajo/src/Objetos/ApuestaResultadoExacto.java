/// APUESTA RESULTADO EXACTO

package Objetos;

import DataTypes.EstadoApuesta;
import DataTypes.TipoApuesta;
import DataTypes.DataFechaHora;
import DataTypes.DataApuestaPersistencia;

//************** PAQUETE APUESTAS *******************//
import DataTypes.DataApuesta;
import DataTypes.DataApuestaResExacto;
import DataTypes.DataPartido;
import DataTypes.TipoCompeticion;
//************** PAQUETE APUESTAS *******************//

public class ApuestaResultadoExacto extends ApuestaPartido {
    private int golesLocal;
    private int golesVisitante;

    public ApuestaResultadoExacto(float monto, DataFechaHora fec, EstadoApuesta estado, Usuario u, int golL, int golV) {
            super(monto,null,fec,estado,u);
            this.golesLocal = golL;
            this.golesVisitante = golV;
    }

    public int getGolesLocal() {
        return this.golesLocal;
    }

    public int getGolesVisitante() {
        return this.golesVisitante;
    }

    @Override
    public TipoApuesta getTipoApuesta() {
        return TipoApuesta.ResExacto;
    }

    @Override
    public float getDividendo(){
        return this.partido.getDivResExacto(golesLocal,golesVisitante);
    }

    //18-10
    @Override
    public DataTypes.DataApuestaPersistencia getDAP(int idCamp,int idPartido){
        int idP=-1;
        if(this.paqueteApuestas!=null){
            idP=this.paqueteApuestas.getId();
    }
        return new DataTypes.DataApuestaPersistencia(this.getTipoApuesta(),idCamp,this.montoApostado,
            this.fecha,this.estado,null,-1, idPartido,this.user.getNick(),-1,this.golesLocal,this.golesVisitante,idP);
    }



    //************** PAQUETE APUESTAS *******************//
    @Override
    public DataApuesta getDataApuesta() {
        //public DataApuestaResExacto(float monto, float saldoNuevo, float beneficio,
        //float dividendo, DataPartido dataP, int golL, int golV) {
        float montoA = this.montoApostado;
        float div = this.getDividendo();
        float ben = montoA*div;
        float saldoN = ben+this.user.getSaldo();

        Competicion comp = this.partido.getCompeticionPartido();

        int idComp = comp.getId(); // CUIDADO NO MOSTRAR LA COMPETICION!!!!
        String nomComp = comp.getNombre();
        TipoCompeticion tipoComp = null;
        if (comp instanceof PartidoIndividual)
            tipoComp = TipoCompeticion.PartidoIndividual;
        else if (comp instanceof Liga)
            tipoComp = TipoCompeticion.Liga;
        else
            tipoComp = TipoCompeticion.Copa;

        String nomLlave = "";

        DataPartido dataP = this.partido.getDataPartido(idComp,nomComp,tipoComp,nomLlave);

        int paq = -1;
        if (this.paqueteApuestas!=null)
            paq = this.paqueteApuestas.getId();

        return new DataApuestaResExacto(montoA,saldoN,ben,div,dataP,
                this.golesLocal,this.golesVisitante,this.estado,paq);
    }


    //************** PAQUETE APUESTAS *******************//
	
}