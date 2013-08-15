/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package DataTypes;

/**
 *
 * @author eliel
 */
public class DataPaqueteApuestasPersistencia {
    private int nroapuestasPendientes;
    private String nickUsuario;
    private int idPaquete;
    private EstadoPaqueteApuestas estadoPaquete;
    public DataPaqueteApuestasPersistencia (int idP,String nickU,int nroA,EstadoPaqueteApuestas estado){
        this.idPaquete=idP;
        this.nickUsuario=nickU;
        this.nroapuestasPendientes=nroA;
        this.estadoPaquete = estado;
    }
    public int getIdPaquete(){
        return this.idPaquete;
    }
    public String getNickUsuario(){
        return this.nickUsuario;
    }
    public int getNroApuestasPendientes(){
        return this.nroapuestasPendientes;
    }
    public EstadoPaqueteApuestas getEstadoPaquete(){
        return this.estadoPaquete;
    }
}
