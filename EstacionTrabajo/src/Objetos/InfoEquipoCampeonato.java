package Objetos;

import DataTypes.DataDivEquipo;
import DataTypes.DataIdNombre;
import java.util.LinkedList;
import java.util.List;

// NUEVO!!
import java.util.ArrayList;
import DataTypes.DataApuestaPersistencia;
// NUEVO!!

public abstract class InfoEquipoCampeonato {
    protected float dividendoCampeonato;
    protected Equipo e;
    private List<ApuestaCampeonato> apuestas;
    
    /// NUEVO!!
    protected Campeonato campeonato; // Falta setearselo!!!
    
    public InfoEquipoCampeonato(float div, Equipo e, Campeonato camp) {
	dividendoCampeonato = div;
	this.e = e;
        apuestas = new LinkedList();
        this.campeonato = camp;
    }
    /// NUEVO!!
    

    public float getDividendoCampeonato(){
	return dividendoCampeonato;
    }

    public final DataDivEquipo getDataDivEquipo() {
	return new DataDivEquipo(e.getId(),e.getNombre(),this.dividendoCampeonato);
    }

    public final DataIdNombre getDataIdNombreEquipo() {
	return e.getDataIdNombre();
    }
    public Equipo getEquipo(){
        return this.e;
    }

    public void agregarNavInfoCampApuesta (Apuesta a){
        apuestas.add((ApuestaCampeonato)a);
    }

    // NUEVO!!
    public void notificar(boolean gano){
        for(int i=0;i<apuestas.size();i++){
            apuestas.get(i).notificar(gano);
        }
    }

    public List<DataApuestaPersistencia> getDAP(int idCamp,int idPartido){
        List<DataApuestaPersistencia> salida=new ArrayList<DataApuestaPersistencia>();
        for(int i=0;i<apuestas.size();i++){
            DataApuestaPersistencia dDA=apuestas.get(i).getDAP(idCamp,idPartido);
            salida.add(dDA);
        }
        return salida;
    }
    // NUEVO!!
}



