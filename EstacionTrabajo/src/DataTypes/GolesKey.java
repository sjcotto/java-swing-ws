package DataTypes;


public class GolesKey {
    private int golesLocal;
    private int golesVisita;

    public GolesKey() {}

    public GolesKey(int gl, int gv){
        golesLocal = gl;
        golesVisita = gv;
    }
   
    
    @Override
    public boolean equals(Object key){
        if (key instanceof GolesKey){
            GolesKey gk = (GolesKey)key;
            return (gk.golesLocal == golesLocal && gk.golesVisita == golesVisita);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return 0;
    }
    public int getGolesLocal(){
        return this.golesLocal;
    }
    public int getGolesVisita(){
        return this.golesVisita;
    }

    public void setGolesLocal(int g) {
        this.golesLocal = g;
    }

    public void setGolesVisita(int g) {
        this.golesVisita = g;
    }
    
    
}