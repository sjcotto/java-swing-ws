package DataTypes;

public class DataLlave implements Comparable{
    private String nombre;
    private int fase;
    private int idLocal;
    //private String nomLocal;
    private int idVisita;
   // private String nomVisita;
    private String llavePreLocal="";
    private String llavePreVisita="";
    private boolean esFinal;
    private boolean tieneSucesora;
    private DataPartido dataP;


    public DataLlave(String nombre, int fase, int idLocal,int idVisita,
            boolean tieneSucesora,boolean esFinal,DataPartido dP){
        this.nombre = nombre;
        this.fase = fase;
        this.idLocal = idLocal;
        this.idVisita = idVisita;
        this.esFinal=esFinal;
        this.tieneSucesora=tieneSucesora;
        this.dataP=dP;
    }

    public DataLlave(String nombre, int fase,String preLocal,String preVisita,
            boolean tieneSucesora,boolean esFinal,DataPartido dP){
        this.nombre = nombre;
        this.fase = fase;
        this.llavePreLocal=preLocal;
        this.llavePreVisita=preVisita;
        this.esFinal=esFinal;
        this.tieneSucesora=tieneSucesora;
        this.dataP=dP;
    }


    public DataLlave(String nombre, int fase) {
        this.nombre = nombre;
        this.fase = fase;
    }

    public String getNombre(){
        return nombre;
    }
    public int getFase(){
        return fase;
    }
    public String getPreLocal(){
        return this.llavePreLocal;
    }

    public String getLLavePreLocal(){
        return this.llavePreLocal;
    }

    public String getPreVisita(){
        return this.llavePreVisita;
    }

    public String getLLavePreVisita(){
        return this.llavePreVisita;
    }
    public int getIdLocal(){
        return this.idLocal;
    }
    public int getIdVisita(){
        return this.idVisita;
    }
    public boolean getTieneSucesora(){
        return this.tieneSucesora;
    }
    public boolean getEsFinal(){
        return this.esFinal;
    }
    public DataPartido getDataPartido(){
        return this.dataP;
    }

    public DataPartido getDataP(){
        return this.dataP;
    }

    public void setNombre(String n){
        nombre=n;
    }

    public void setFase(int f){
        fase = f;
    }

    public void setLLavePreLoc(String n){
        llavePreLocal=n;
    }

    public void setLLavePreVisita(String n){
        llavePreVisita=n;
    }

    public void setIdLocal(int i){
        idLocal=i;
    }

    public void setIdVisita(int i){
        idVisita=i;
    }

    public void setTieneSucesora(boolean t){
        tieneSucesora=t;
    }

    public void setEsFinal(boolean f){
        esFinal=f;
    }

    public void setDataP(DataPartido dp){
        dataP=dp;
    }

    @Override
     public int compareTo (Object d){
        if(this.fase>((DataLlave)d).getFase()){
            return 1;
        }
        if(this.fase<((DataLlave)d).getFase()){
            return -1;
        }
        return 0;
     }

}
