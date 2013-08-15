package DataTypes;

public class DataIUPPPersistencia {
    private int  idPartido;
    private int golesLocal;
    private int golesVisita;
    public DataIUPPPersistencia(int idPartido,int gL,int gV){
        this.idPartido=idPartido;
        this.golesLocal=gL;
        this.golesVisita=gV;        
    }
    public int getIdPartido(){
        return this.idPartido;        
    }
    public int getGolesLocal(){
        return this.golesLocal;
    }
    public int getGolesVisita(){
        return this.golesVisita;
    }
}
