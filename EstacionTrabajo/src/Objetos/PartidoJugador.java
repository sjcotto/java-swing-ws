package Objetos;

import DataTypes.DataIdNombre;

public class PartidoJugador {

	// ATRIBUTOS
        private int nroGoles;
	/**
        private int nroTarjetasAmarillas;
	private boolean tarjetaRoja;
	private boolean fueTitular;
	private boolean huboCambio;
	private String jugadorCambio;
         **/
        private Jugador jugador;

	// CONSTRUCTORES
	public PartidoJugador(Jugador j) {
            this.jugador=j;
	}

        // GETTERS Y SETTERS
	public  int getNroGoles(){
            return nroGoles;
	}
	/**
         public int getNroTarjetasAmarillas(){
		return nroTarjetasAmarillas;
	}
	public  boolean getTarjetaRoja(){
		return tarjetaRoja;
	}
	public boolean getFueTitular(){
		return fueTitular;
	}
	public boolean getHuboCambio(){
		return huboCambio;
	}
	public String getJugadorCambio(){
		return jugadorCambio;
	}
         **/

        // VER DETALLES COMPETICION
        public String obtenerNombreJug() {
            return this.jugador.getNombre();
        }

        public DataIdNombre obtenerIdNombreJugador() {
            return this.jugador.getDataBasica();
        }

        // FIN VER DETALLES COMPETICION

}