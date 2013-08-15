package Objetos;
import Controladores.ControladorEquipos;

public class PartidoEquipo {
	//Atributos
	private boolean esLocal;
        private Equipo eq;

	//Contructores
	public PartidoEquipo(int idEquipo, boolean esLocal) {
		this.esLocal = esLocal;
                ControladorEquipos ce =new ControladorEquipos();
                eq = ce.buscarEquipo(idEquipo);
	}

	//Funciones get y set
	public boolean getEsLocal(){
		return esLocal;
	}
	//Operaciones de Sistema

}
