/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Objetos;

import java.util.Map;
import java.util.HashMap;

/**
 *
 * @author CAPablito
 */
public class InfoUsuarioPenca {
   
    private int puntos;
    private String nick;
    private Usuario usuAsociado;
    private Map<Integer,InfoUsuarioPartidoPenca> partidos;


    public InfoUsuarioPenca(Usuario u){
        this.puntos = 0;
        this.nick = u.getNick();
        this.usuAsociado = u;
        partidos= new HashMap<Integer,InfoUsuarioPartidoPenca>();

     }
	 
	public void setPuntos(int puntos){
		this.puntos=puntos;
    }
    
    public void aportar(Partido p, int golesLoc, int golesVis){
        InfoUsuarioPartidoPenca iupp= new InfoUsuarioPartidoPenca(p,golesLoc,golesVis);
        partidos.put(p.getId(), iupp);
     }

    public boolean apostoPartido(int idPart){
        return partidos.containsKey(idPart);
    }

    public String getNick(){
        return nick;
    }
    public int getPuntos(){
        return puntos;
    }

    public void actualizarPuntos(boolean exacto){
        if (exacto)
            puntos=puntos+10;
        else
            puntos=puntos+3;

    }

    public boolean acertoResultadoExacto(int idPart,int golesLoc,int golesVis){
        InfoUsuarioPartidoPenca p = partidos.get(idPart);
        if (p.getGolesLocal()== golesLoc && p.getGolesVisitante()==golesVis)
              return true;
        else
            return false;
    }

    public boolean acertoResultado(int idPart,int golesLoc,int golesVis){
         InfoUsuarioPartidoPenca p = partidos.get(idPart);
         if (golesLoc > golesVis){
             return p.getGolesLocal() > p.getGolesVisitante();
         }
         else if (golesLoc < golesVis){
             return p.getGolesLocal()< p.getGolesVisitante();
         }
         else
            return p.getGolesLocal()==p.getGolesVisitante();
    }

    public void notificarVencedor(float pozo){
        usuAsociado.gano(pozo);
    }
	
	// Persistencia Penca
    public DataTypes.DataInfoUsuarioPencaPersistencia getDIUPencaPersistencia (){
        java.util.List<DataTypes.DataIUPPPersistencia> lista=
                new java.util.ArrayList<DataTypes.DataIUPPPersistencia>();
       for(Map.Entry<Integer,InfoUsuarioPartidoPenca> entry: partidos.entrySet()) {
            InfoUsuarioPartidoPenca info = entry.getValue();
            DataTypes.DataIUPPPersistencia data =info.getDataInfoUPPPersistencia();
            lista.add(data);
       }
       return new DataTypes.DataInfoUsuarioPencaPersistencia(this.usuAsociado.getNick(),this.puntos,lista);
    }

   
}
