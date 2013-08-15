package Objetos;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

import DataTypes.DataIdNombre;
import DataTypes.DataEquipo;

public class Equipo {
        //Atributos
	private int id;
	private String nombre;
        private String imagePath="";

	private Map<Integer,Jugador> jugadoresEquipo;

        //Contructores
	public Equipo(int i,String n,String image) {
            id=i;
            nombre=n;
            imagePath = image;
            jugadoresEquipo = new HashMap<Integer,Jugador>();
	}

        //Funciones get y set
	public int getId(){
		return id;
	}

        public String getNombre(){
		return nombre;
	}

        public String getImagePath(){
            return imagePath;
	}
        
        public DataIdNombre getDataIdNombre(){
            return new DataIdNombre(this.id,this.nombre);
        }

        public DataEquipo getDataEquipo(){
            return new DataEquipo(this.id,this.nombre,this.imagePath);
        }

        //Operaciones de Sistema
        public List<DataIdNombre> obtenerJugadores(){
            //retornamos una lista con los datanombrejugador
            List<DataIdNombre> lista = new ArrayList<DataIdNombre>();

            for (Map.Entry<Integer,Jugador> entry: this.jugadoresEquipo.entrySet()) {
                Jugador j = entry.getValue();
                DataIdNombre data = new DataIdNombre(j.getId(),j.getNombre(),j.getPathImage());
                lista.add(data);
            }
            return lista;
        }
        
        /// FINALIZAR PARTIDO
        public void agregarJugador(Jugador jugador){
                this.jugadoresEquipo.put(jugador.getId(),jugador);
        }


}