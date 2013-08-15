/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;
import DataTypes.TipoDividendo;
import DataTypes.DataFechaHora; 
import DataTypes.TipoApuesta;
import DataTypes.EstadoApuesta;

//************** PAQUETE APUESTAS *******************//
import DataTypes.DataApuesta;
import DataTypes.DataApuestaPartido;
import DataTypes.DataPartido;
import DataTypes.TipoCompeticion;
//************** PAQUETE APUESTAS *******************//

public class ApuestaPartido extends Apuesta{
    private TipoDividendo tipo;
    protected Partido partido;
    
    public ApuestaPartido(float m, TipoDividendo t, DataFechaHora f,EstadoApuesta estado,Usuario u){
        super(m,f,u);
        this.tipo = t;
        this.partido = null;
        this.estado=estado;
    }

    public void agregarPartIndivApuesta (Partido p){
        partido = p;
    }

    public float getDividendo(){
        
        float div=0;
        if (tipo.compareTo(TipoDividendo.Empate) == 0)
            div =  partido.getDividendos().getDividendoEmpate();
        if (tipo.compareTo(TipoDividendo.Local) == 0)
            div =  partido.getDividendos().getDividendoLocal();
        if (tipo.compareTo(TipoDividendo.Visitante) == 0)
            div =  partido.getDividendos().getDividendoVisita();
        return div;
        
    }

    public TipoApuesta getTipoApuesta() {
        return TipoApuesta.Partido;
    }
    public void agregarApuestaPartidoCamp (Partido p){
        partido = p;
        p.agregarApuestaPart(this);
    }

    public void notificar(TipoDividendo tipoD){
        //la apuesta deberia conocer al usuario y en casod de acertar la apuesta
        //aumentar su monedero
        if(this.tipo==tipoD){
            this.notificar(true);
        }
        else{
            this.notificar(false);
        }
    }
 //18-10
    @Override
    public DataTypes.DataApuestaPersistencia getDAP(int idCamp,int idPartido){
        int idP=-1;
        if(this.paqueteApuestas!=null){
            idP=this.paqueteApuestas.getId();
    }
        return new DataTypes.DataApuestaPersistencia(this.getTipoApuesta(),idCamp,montoApostado,
                fecha,estado,tipo,-1, idPartido,this.user.getNick(),-1,-1,-1,idP);
    }
	


	//************** PAQUETE APUESTAS *******************//
    public DataApuesta getDataApuesta() {
        //public DataApuestaPartido(float monto, float saldoNuevo, float beneficio,
        //float dividendo, DataPartido dataP, TipoDividendo tipoD) {
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

        return new DataApuestaPartido(montoA,saldoN,ben,div,dataP,this.tipo,this.estado,paq);
    }
    //************** PAQUETE APUESTAS *******************//
    public TipoDividendo getTipoDividendo(){
        return this.tipo;
    }
}
