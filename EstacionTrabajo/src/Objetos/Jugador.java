package Objetos;
import DataTypes.DataJugador;
import DataTypes.DataFechaHora;
import DataTypes.TipoPosicion;
import DataTypes.DataIdNombre;
import Controladores.ControladorFecha;

public class Jugador {
	//Atributos
	private int id;
	private String nombre;
	private String nombreCompleto;
	private TipoPosicion posicion;
	private DataFechaHora fechaNac;
	private int edad;
	private double altura;
	private String lugarNacimiento;
	private double peso;
        private boolean jugoAlgunPartido;
        private String pathImage = "";

	//Constructores
	public Jugador(DataJugador j) {
            ControladorFecha cf = ControladorFecha.getInstance();

            id = j.getId();
            nombre=j.getNombre();
            nombreCompleto=j.getNombreCompleto();

            edad=cf.getFecha().getAnio()-j.getFechaDeNacimiento().getAnio();
            if (cf.getFecha().getMes() < j.getFechaDeNacimiento().getMes())
                edad--;
            else if (cf.getFecha().getMes() == j.getFechaDeNacimiento().getMes()) {
                if (cf.getFecha().getDia() < j.getFechaDeNacimiento().getDia())
                    edad--;
            }
            altura=j.getAltura();
            lugarNacimiento=j.getLugarNacimiento();
            peso=j.getPeso();
            fechaNac = j.getFechaDeNacimiento();
            posicion = j.getPosicion();
            jugoAlgunPartido = false;
            pathImage = j.getPathImage();
            
	}

	//Funciones de Sistema get y set
	public int getId(){
		return id;		
	}
	public String getNombre(){
		return nombre;
	}
	public String getNombreCompleto(){
		return nombreCompleto;
	}
	public TipoPosicion getPosicion(){
		return posicion;
	}
	public DataFechaHora getFechaNac(){
		return fechaNac;
	}

	public int getEdad(){
            ControladorFecha cf = ControladorFecha.getInstance();
            edad=cf.getFecha().getAnio()-this.fechaNac.getAnio();
            if (cf.getFecha().getMes() < this.fechaNac.getMes())
                edad--;
            else if (cf.getFecha().getMes() == this.fechaNac.getMes()) {
                if (cf.getFecha().getDia() < this.fechaNac.getDia())
                    edad--;
            }
            return edad;
	}

	public double getAltura(){
		return altura;
	}
	public String getLugarNacimiento(){
		return lugarNacimiento;
	}
	public double getPeso(){
		return peso;
	}
        public String getPathImage(){
            return pathImage;
        }

        boolean getJugoAlgunPartido(){
            return jugoAlgunPartido;
        }


        //SET,  para el modificar jugador
        public void setNombre(String a){
		nombre=a;
	}
	public  void setNombreCompleto(String a){
		nombreCompleto=a;
	}
	public  void setPosicion(TipoPosicion a){
		posicion=a;
	}
	public  void setFechaNac(DataFechaHora c){
		fechaNac=c;
	}
	public void setEdad(int e){
		edad=e;
	}
	public  void setAltura(double a){
		altura = a;
	}
	public void setLugarNacimiento(String a){
		lugarNacimiento = a;
	}
	public  void setPeso(double a){
		 peso = a;
	}

        public void setPathImage(String p){
            pathImage = p;
        }

        boolean setJugoAlgunPartido(boolean f){
            return jugoAlgunPartido = f;
        }

	//Operaciones de Sistema
        public boolean eliminarJugador(){
            return ((jugoAlgunPartido == false));
        }

        public DataIdNombre getDataBasica(){
            return new DataIdNombre(id,nombre);
        }

	public DataJugador getDataJugador() {
		return new DataJugador(this.id,this.nombre,this.nombreCompleto,
			this.posicion,this.fechaNac,this.edad,this.lugarNacimiento,
				this.altura,this.peso,this.pathImage);
	}



}
