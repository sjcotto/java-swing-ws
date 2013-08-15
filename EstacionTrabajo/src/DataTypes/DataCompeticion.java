package DataTypes;

import java.util.List;

public class DataCompeticion {
    private int id;
    private String nombre;
    private TipoCompeticion tipo;
    private List<DataDivEquipo> dividendos;
    private boolean estaDefinida;

    private String pathImage = "";

    private float montoPenca;

    public DataCompeticion() {}

    public DataCompeticion(int id,String nombre,TipoCompeticion tipo,
            List<DataDivEquipo> dividendos, boolean estaDef){
        this.id=id;
        this.nombre=nombre;
        this.tipo=tipo;
        this.dividendos=dividendos;
        this.estaDefinida=estaDef;
    }

    public DataCompeticion(int id,String nombre,TipoCompeticion tipo,
            List<DataDivEquipo> dividendos, boolean estaDef,String path){
        this.id=id;
        this.nombre=nombre;
        this.tipo=tipo;
        this.dividendos=dividendos;
        this.estaDefinida=estaDef;
        this.pathImage=path;
    }
    
    public int getId(){
        return id;
    }

    public String getNombre(){
        return nombre;
    }

    public TipoCompeticion getTipo(){
        return this.tipo;
    }

    public List<DataDivEquipo> getDividendos(){
        return this.dividendos;
    }

    public boolean getEstaDefinida(){
        return estaDefinida;
    }

    public String getPathImage(){
        return this.pathImage;
    }

    public float getMontoPenca(){
        return this.montoPenca;
    }

    public void setId(int i){
        id=i;
    }

    public void setNombre(String n){
        nombre=n;
    }

    public void setDividendos(List<DataDivEquipo> d){
        dividendos=d;
    }

    public void setEstaDefinida (boolean  b){
        estaDefinida = b;
    }

    public void setTipo(TipoCompeticion  t){
        tipo=t;
    }

    public void setPathImage(String path){
        pathImage=path;
    }

    public void setMontoPenca(float a){
        this.montoPenca = a;
    }

}
