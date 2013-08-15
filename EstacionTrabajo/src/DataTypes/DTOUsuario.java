package DataTypes;

import java.util.List;

public class DTOUsuario {
    private DataUsuario dataUsuario;
    private String pass;
    private List<DataPaqueteApuestasPersistencia > paquetes;
    private boolean logeado;

    public DTOUsuario (DataUsuario dataUsuario,String pass,boolean logeado,List<DataPaqueteApuestasPersistencia > paq){
        this.dataUsuario=dataUsuario;
        this.pass=pass;
        this.paquetes=paq;
        this.logeado=logeado;
    }
    public DataUsuario getUsuario(){
        return this.dataUsuario;
    }
    public String getPass(){
        return this.pass;
    }
    public List<DataPaqueteApuestasPersistencia> getPaquetes(){
        return this.paquetes;
    }
    public boolean getLogeado(){
        return this.logeado;
    }
}