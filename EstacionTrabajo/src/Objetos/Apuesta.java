/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import DataTypes.DataFechaHora;
import DataTypes.EstadoApuesta;
import DataTypes.DataDetalleApuesta;
import DataTypes.TipoApuesta;
import DataTypes.DataApuestaPersistencia;
import DataTypes.DataApuesta;
import Controladores.ControladorUsuarios;

public abstract class Apuesta {
    protected float montoApostado;
    protected DataFechaHora fecha;
    protected EstadoApuesta estado;
    protected Usuario user;
	
	//************** PAQUETE APUESTAS *******************//
    protected PaqueteApuestas paqueteApuestas;
    //************** PAQUETE APUESTAS *******************//
    
    public Apuesta(float monto, DataFechaHora fec,Usuario u){
        this.montoApostado = monto;
        this.fecha = fec;
        this.estado = EstadoApuesta.Pendiente;
        this.user = u;
        //************** PAQUETE APUESTAS *******************//
        paqueteApuestas = null;
        //************** PAQUETE APUESTAS *******************//
    }
    
    public float getMonto(){
        return montoApostado;
    }

    public DataFechaHora getFechaApuesta(){
        return fecha;
    }

    public EstadoApuesta getEstadoApuesta(){
        return estado;
    }

    public abstract TipoApuesta getTipoApuesta();
    
    public abstract float getDividendo();

    public void setEstado(EstadoApuesta estado){
        this.estado=estado;
    }

    /**************** NUEVO! ******************/
    public DataDetalleApuesta getDataDetalleApuesta() {
        float div = this.getDividendo();
        TipoApuesta tipo = this.getTipoApuesta();
        return new DataDetalleApuesta(this.estado,this.montoApostado*div,
                this.montoApostado,div,tipo,this.fecha);
    }


   //tener cuidado que al cargar las apuestas las notificaciones no se realicen
    //esto es cargar las apuestas luego de finalizada la carga de competicion
    public void notificar(boolean gano){//throws Exception{
        //la apuesta deberia conocer al usuario y en casod de acertar la apuesta
        //aumentar su monedero
        if(gano){
            float montoGanado=this.getDividendo()*this.montoApostado;
            this.estado=EstadoApuesta.Gano;
            this.user.gano(montoGanado);
        }
        else{
            this.estado=EstadoApuesta.Perdio;
        }

        if (this.paqueteApuestas!=null) {
                this.paqueteApuestas.terminarApuesta(this);
        }
        Interface.IControladorUsuarios cu=new ControladorUsuarios();
        try{
            cu.notificarResultadoApuesta(this.user.getNick(),this.getMensaje(true, gano));
        }
        catch(Exception e){
            e.printStackTrace();//no seria bueno que toda la notificacion se caiga por un
            //error en el mensaje por eso solo se informa con un error
        }
    }

    public String getMensaje(boolean terminado,boolean gano){
        String mensaje="";

        if(terminado){
            if(gano){
                mensaje+="Ibet le notifica que ha acertado la apuesta,y obtuvo una ganancia de: "+
                        Float.toString(this.getDividendo()*this.montoApostado);
            }
            else{
                mensaje+="Ibet le notifica que ha perdido la apuesta";
            }
        }
        DataApuesta data=this.getDataApuesta();
        mensaje+="\n\n";
        mensaje+="Fecha efectuada: "+this.fecha.toString()+"\n";
        mensaje+="Monto apostado: "+this.montoApostado+"\n";
        mensaje+="Dividendo apuesta: "+data.getDividendo()+"\n";
        if(null==this.paqueteApuestas){
            mensaje+="Apuesta individual\n";
        }
        else{
            if(terminado){
                mensaje+="Id paquete: "+this.paqueteApuestas.getId()+" \n";
                mensaje+="Estado de paquete: "+this.paqueteApuestas.getEstadoPaq().toString()+" \n";
            }
        }

        if (this instanceof ApuestaResultadoExacto){
            DataTypes.DataApuestaResExacto dataP=(DataTypes.DataApuestaResExacto)data;
            DataTypes.DataPartido dP=(DataTypes.DataPartido) dataP.getPartido();
            String gL=Integer.toString( ((ApuestaResultadoExacto)this ).getGolesLocal());
            String gV=Integer.toString( ((ApuestaResultadoExacto)this ).getGolesVisitante());
            mensaje+="Tipo Apuesta: Resultado exacto\n";
            mensaje+="Id competicion: "+dP.getDataInfoPart().getIdComp()+"\n";
            mensaje+="Nombre competicion: "+dP.getDataInfoPart().getNomComp()+"\n";
            mensaje+="Id partido: "+((ApuestaResultadoExacto)this).partido.getId()+"\n";
            mensaje+="Resultado exacto apostado: "+gL+"-"+gV+"\n";
            if(terminado){
                mensaje+="Resultado del Partido: " +dP.getGolesL()+"-"+dP.getGolesV()+"\n";
            }
        }
        else{
            if (this instanceof ApuestaPartido){
                DataTypes.DataApuestaPartido dataP=(DataTypes.DataApuestaPartido)data;
                DataTypes.DataPartido dP=(DataTypes.DataPartido) dataP.getPartido();

                mensaje+="Tipo Apuesta: Partido\n";
                mensaje+="Id competicion: "+dP.getDataInfoPart().getIdComp()+"\n";
                mensaje+="Nombre competicion: "+dP.getDataInfoPart().getNomComp()+"\n";
                mensaje+="Id partido: "+((ApuestaPartido)this).partido.getId()+"\n";
                mensaje+="Resultado apostado: "+((ApuestaPartido)this ).getTipoDividendo().toString()+"\n";
                if(terminado){
                    if(dP.getGolesL()==dP.getGolesV()){
                        mensaje+="Resultado del Partido: Empate\n";
                    }
                    if(dP.getGolesL()>dP.getGolesV()){
                        mensaje+="Resultado del Partido: Local\n";
                    }
                    if(dP.getGolesL()<dP.getGolesV()){
                        mensaje+="Resultado del Partido: Visitante\n";
                    }
                }
            }
        }
        if (this instanceof ApuestaGoleador){
            DataTypes.DataJugador jug=((DataTypes.DataApuestaGoleador)data).getJugador();
            DataTypes.DataCompeticion dataC=((DataTypes.DataApuestaGoleador)data).getCompeticion();
            mensaje+="Tipo apuesta: Goleador\n";
            mensaje+="Id competicion: "+dataC.getId()+"\n";
            mensaje+="Nombre competicion: "+dataC.getNombre()+"\n";
            mensaje+="Id jugador: "+jug.getId()+"\n";
            mensaje+="Nombre Jugador: "+jug.getNombre()+"\n";
        }
        if (this instanceof ApuestaCampeonato){
            mensaje+="Tipo Apuesta: Campeon\n";
            DataTypes.DataApuestaCampeon dataC=(DataTypes.DataApuestaCampeon)data;
            DataTypes.DataEquipo dE=dataC.getEquipo();
            DataTypes.DataCompeticion dC=dataC.getCompeticion();
            mensaje+="Id competicion: "+dC.getId()+"\n";
            mensaje+="Nombre competicion: "+dC.getNombre()+"\n";
            mensaje+="Id equipo: "+dE.getId()+"\n";
            mensaje+="Nombre equipo: "+dE.getNombre()+"\n";
        }
        return mensaje;
    }

    public abstract DataApuestaPersistencia getDAP(int idCamp,int idPartido);
    /**************** NUEVO! ******************/
	
	//************** PAQUETE APUESTAS *******************//
    public abstract DataApuesta getDataApuesta();

    public void agregarPaqueteApuesta(PaqueteApuestas paq) {
        this.paqueteApuestas = paq;
    }

    public boolean pertenecePaqueteApuestas() {
        return this.paqueteApuestas!=null;
    }
    //************** PAQUETE APUESTAS *******************//
    
    
}
