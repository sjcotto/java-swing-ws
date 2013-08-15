/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

/**
 *
 * @author CAPablito
 */
public class InfoUsuarioPartidoPenca {

    private Partido part;
    private int golesLoc;
    private int golesVis;

    public InfoUsuarioPartidoPenca (Partido p,int golesLoc,int golesVis){
        part=p;
        this.golesLoc=golesLoc;
        this.golesVis=golesVis;
    }

    public int getGolesLocal(){
        return golesLoc;
    }

    public int getGolesVisitante(){
        return golesVis;
    }
    public int getIdPartido(){
        return this.part.getId();
    }
	
	// Persistencia Penca
	public DataTypes.DataIUPPPersistencia getDataInfoUPPPersistencia(){
        return new DataTypes.DataIUPPPersistencia(part.getId(),golesLoc,golesVis);
    }
}
