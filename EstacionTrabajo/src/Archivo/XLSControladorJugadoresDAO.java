

package Archivo;
import Interface.IControladorJugadoresDAO;
import DataTypes.DataFechaHora;
import DataTypes.DataJugador;
import DataTypes.TipoPosicion;
import java.util.List;
import java.util.ArrayList;

public class XLSControladorJugadoresDAO implements IControladorJugadoresDAO {
    
    private ManejoDeArchivos arch=null;
     
    public XLSControladorJugadoresDAO() {
        arch = ManejoDeArchivos.getInstance();
    }

    public List<DataJugador> cargarJugadores(String ruta)throws Exception{
              
            List<DataJugador> salida=new ArrayList<DataJugador>();
            //arch.abrirLibro(ruta);
         
            int fila=0;
            
            int tamanio=Integer.parseInt(arch.leerString("Jugadores", 0,0));
            for(fila=1;fila<tamanio+1;fila++){ 
                

                int idJug = Integer.parseInt(arch.leerString("Jugadores",0,fila) ) ;
                String nombre = arch.leerString("Jugadores", 1,fila);
                String nombreCompleto = arch.leerString("Jugadores", 2,fila);

                TipoPosicion tipo=TipoPosicion.Golero;
                String tipoS=arch.leerString("Jugadores", 3,fila);
                if(tipoS.equals("Defensa")){
                    tipo=TipoPosicion.Defensa;
                }
                if(tipoS.equals("Delantero")){
                    tipo=TipoPosicion.Delantero;
                }
                if(tipoS.equals("Golero")){
                    tipo=TipoPosicion.Golero;
                }
                if(tipoS.equals("Volante")){
                    tipo=TipoPosicion.Volante;
                }


                DataFechaHora dfh = new DataFechaHora(arch.leerString("Jugadores",4,fila));
                int edad = Integer.parseInt(arch.leerString("Jugadores", 5,fila));
                String lugNac =  arch.leerString("Jugadores", 6,fila);
                double altura = Double.parseDouble(arch.leerString("Jugadores", 7,fila));
                double peso = Double.parseDouble(arch.leerString("Jugadores", 8,fila));
                String imagePath = arch.leerString("Jugadores", 9,fila);
                
                DataJugador dJ=new DataJugador(idJug,nombre,nombreCompleto,tipo,
                        dfh,edad,lugNac,altura,peso,imagePath);
                        
                
                salida.add(dJ);
                
            }
            // arch.cerrarLibroLec();
             return salida;
        
    }
    public void guardarJugadores(String ruta,List<DataJugador> listJ)throws Exception{
        
            //arch.crearlibroEscYhojas(ruta);
            int fila;
            arch.agregarString("Jugadores",0,0,Integer.toString(listJ.size()));
            for (fila=1;fila<listJ.size()+1;fila++) {
                //se ingresan los datos de uno
                DataJugador dJ=listJ.get(fila-1);                
                
                arch.agregarString("Jugadores",0,fila,Integer.toString(dJ.getId()));  
                arch.agregarString("Jugadores",1,fila,dJ.getNombre()); 
                arch.agregarString("Jugadores",2,fila,dJ.getNombreCompleto()); 
                arch.agregarString("Jugadores",3,fila,dJ.getPosicion().toString()); 
                arch.agregarString("Jugadores",4,fila,dJ.getFechaDeNacimiento().toString()); 
                arch.agregarString("Jugadores",5,fila,Integer.toString(dJ.getEdad()));
                arch.agregarString("Jugadores",6,fila,dJ.getLugarNacimiento()); 
                arch.agregarString("Jugadores",7,fila,Double.toString(dJ.getAltura()));
                arch.agregarString("Jugadores",8,fila,Double.toString(dJ.getPeso())); 
                arch.agregarString("Jugadores",9,fila,dJ.getPathImage());  
                
            }               
            //arch.cerrarLibroEsc();
    }
}
