package DataTypes;

public class DataApuestaPersistencia {
    private float montoApostado;
    private DataFechaHora fecha;
    private EstadoApuesta estado;    
    private int idCamp; 
    private int idEquipo;
    private TipoDividendo tipoD;
    private TipoApuesta tipoA;
    private int idPartido;
    private String nick;
    private int idGoleador;
    private int golesLoc;
    private int golesVis;
    private int idPaquete;

    public DataApuestaPersistencia() {}

    public DataApuestaPersistencia(TipoApuesta tipoA,int idCamp,float montoApostado,DataFechaHora fecha,EstadoApuesta estado, 
                    TipoDividendo tipoD,int idEquipo, int idPartido,String nick, int idG,int golL, int golV,int idP){
        this.tipoA=tipoA;
        this.montoApostado=montoApostado;
        this.fecha=fecha;
        this.estado=estado;    
        this.idCamp=idCamp; 
        this.idEquipo=idEquipo;
        this.tipoD=tipoD;
        this.idPartido=idPartido;
        this.nick=nick;
        this.idGoleador = idG;
        this.golesLoc = golL;
        this.golesVis = golV;
        this.idPaquete=idP;
    }

    public TipoApuesta getTipoApuesta(){
        return this.tipoA;
    }
    
     public float getMontoApostado(){
        return this.montoApostado;
        
    }
    public DataFechaHora getFecha(){
        return this.fecha;
    }
    public EstadoApuesta getEstado(){
        return this.estado; 
    }
    public int getIdCampeonato(){
        return this.idCamp; 
    }
    public int getIdEquipo(){
        return this.idEquipo;
    }
    public TipoDividendo getTipoDividendo(){
        return this.tipoD;
    }
    public int getIdPartido(){
        return this.idPartido;
    }
    public String getNick(){
        return this.nick;
    }

    public int getIdGoleador() {
        return this.idGoleador;
    }
    public int getGolesLoc() {
        return this.golesLoc;
    }
    public int getGolesVis(){
        return this.golesVis;
    }
    public int getIdPaquete(){
        return this.idPaquete;
    }

    // FALTAN SETS!

    public void setIdPaquete(int id) {
        this.idPaquete = id;
    }

}