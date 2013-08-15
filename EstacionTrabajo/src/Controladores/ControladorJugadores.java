
package Controladores;

import Interface.IControladorJugadores;
import DataTypes.DataJugador;
import DataTypes.DataIdNombre;
import Objetos.Jugador;
import java.util.*;
import Excepciones.ExNoExisteJugador;
import Excepciones.ExNoExisteJugadorEnMemoria;

/// PERSISTENCIA
import Interface.IControladorJugadoresDAO;
import Interface.XLSFabricaDAO;

public class ControladorJugadores implements IControladorJugadores{
    /************MEMORIA TEMPORAL DE LOS DSS*/
    private int idRecordado;
    /*************/

    public ControladorJugadores(){
        idRecordado = -1;
    }

    public DataIdNombre getDataBasica(int id) {
        Map<Integer,Jugador> jugadores = ManejadorJugadores.getInstance().getJugadores();
        Jugador j=jugadores.get(id);
        if (j==null){
            return null;
        } else
            return j.getDataBasica();
    }

    public Jugador getJugador(int id) {
        Map<Integer,Jugador> jugadores = ManejadorJugadores.getInstance().getJugadores();
        return  jugadores.get(id);
    }
    
    /// ALTA JUGADOR
    public int ingresarJugador(DataJugador data){
        Map<Integer,Jugador> jugadores = ManejadorJugadores.getInstance().getJugadores();
        int id = ManejadorJugadores.getInstance().getIdActual();
        ManejadorJugadores.getInstance().setIdActual(id+1);

        data.setId(id);

        Jugador j = new Jugador(data);
        jugadores.put(id, j);
        return id;
    }
    
    /// ELIMINAR JUGADOR
    public List<DataIdNombre> listaJugadores(){
        Map<Integer,Jugador> jugadores = ManejadorJugadores.getInstance().getJugadores();

        List<DataIdNombre> lista = new ArrayList<DataIdNombre>();
        for (Map.Entry<Integer, Jugador> entry : jugadores.entrySet()) {
            Jugador e = entry.getValue();
            DataIdNombre data = new DataIdNombre(e.getId(),e.getNombre());
            lista.add(data);
        }
        return lista;
   }

    public boolean eliminarJugador(int id) throws ExNoExisteJugador{//Caso Eliminar Jugador,
        //solo se puede eliminar si el jugador no tiene ningun equipo asosiado, osea no a jugado en ningun equipo
        Map<Integer,Jugador> jugadores = ManejadorJugadores.getInstance().getJugadores();

        if (!jugadores.containsKey(id))
             throw (new ExNoExisteJugador("No Existe un Jugador con el Id :"+ Integer.toString(id)));

        Jugador jugador = jugadores.get(id);
        boolean r = (jugador.eliminarJugador());
        if (r)
            idRecordado = id;
        else
            idRecordado = -1;
        
        return r;

         }

    public void confirmarEliminacion() throws ExNoExisteJugadorEnMemoria{//Caso Eliminar Jugadpr
        //eliminamos el jugador con id recordado.
        Map<Integer,Jugador> jugadores = ManejadorJugadores.getInstance().getJugadores();
        if (idRecordado==-1) {
            throw (new ExNoExisteJugadorEnMemoria("No Existe un Jugador al cual eliminar"));
        }
        jugadores.remove(idRecordado);
        idRecordado = -1;
    }

    /// MODIFICAR JUGADOR
    public DataJugador seleccionarJugador(int id) throws ExNoExisteJugador{
        //El sistema recuerda el id del jugador.
        Map<Integer,Jugador> jugadores = ManejadorJugadores.getInstance().getJugadores();

        if (!jugadores.containsKey(id))
             throw (new ExNoExisteJugador("No Existe un Jugador con el Id :"+ Integer.toString(id)));

        idRecordado = id;
        Jugador j= jugadores.get(id);
        return (new DataJugador(j.getId(),j.getNombre(),j.getNombreCompleto(),j.getPosicion(),j.getFechaNac(),j.getEdad(),j.getLugarNacimiento(),j.getAltura(),j.getPeso(),j.getPathImage()) );
    }

    public void modificarDatos(DataJugador datosNuevos) throws ExNoExisteJugadorEnMemoria {
        //modificamos los datos del id recordado anteriormente en el sistema.
        Map<Integer,Jugador> jugadores = ManejadorJugadores.getInstance().getJugadores();

        if (idRecordado==-1) {
            throw (new ExNoExisteJugadorEnMemoria("No Existe un Jugador al Cual modificar los datos"));
        }
        Jugador jugador = jugadores.get(idRecordado);

        jugador.setAltura(datosNuevos.getAltura());
        
        ControladorFecha cf = ControladorFecha.getInstance();

        int edad=cf.getFecha().getAnio()-datosNuevos.getFechaDeNacimiento().getAnio();
        if (cf.getFecha().getMes() < datosNuevos.getFechaDeNacimiento().getAnio())
            edad--;
        else if (cf.getFecha().getMes() == datosNuevos.getFechaDeNacimiento().getAnio()) {
            if (cf.getFecha().getDia() < datosNuevos.getFechaDeNacimiento().getDia())
                edad--;
        }
        jugador.setEdad(edad);
        jugador.setFechaNac(datosNuevos.getFechaDeNacimiento());
        jugador.setNombre(datosNuevos.getNombre());
        jugador.setNombreCompleto(datosNuevos.getNombreCompleto());
        jugador.setPeso(datosNuevos.getPeso());
        jugador.setPosicion(datosNuevos.getPosicion());
        jugador.setPathImage(datosNuevos.getPathImage());
        jugador.setLugarNacimiento(datosNuevos.getLugarNacimiento());
        idRecordado = -1;        
    }
    
    public void setIdJ(int idJ){
        ManejadorJugadores.getInstance().setIdActual(idJ);
    }

    public void cargarJugadores(String ruta)throws Exception{
        ManejadorJugadores.getInstance().getJugadores().clear();
        ManejadorJugadores.getInstance().setIdActual(1); 
        
        XLSFabricaDAO f=new XLSFabricaDAO();
        IControladorJugadoresDAO cj=f.getIControladorJugadoresDAO();
        List<DataJugador> lista=cj.cargarJugadores(ruta);

        Map<Integer,Jugador> jugadores = ManejadorJugadores.getInstance().getJugadores();
        int tope=0;
        for(int i=0;i<lista.size();i++){
            DataJugador dJ=lista.get(i);
            int id=dJ.getId();
            Jugador j = new Jugador(dJ);
            jugadores.put(id, j);
            if(id>tope){
                tope=id;
            }
        }
        ManejadorJugadores.getInstance().setIdActual(tope+1);
    }

    public void guardarJugadores(String ruta)throws Exception{
        XLSFabricaDAO f=new XLSFabricaDAO();
        IControladorJugadoresDAO ce=f.getIControladorJugadoresDAO();
        List<DataJugador> lista = new ArrayList<DataJugador>();
        Map<Integer,Jugador> jugadores = ManejadorJugadores.getInstance().getJugadores();
        for (Map.Entry<Integer, Jugador> entry : jugadores.entrySet()) {
            Jugador j = entry.getValue();
            DataJugador dJ=new DataJugador(j.getId(),j.getNombre(),j.getNombreCompleto(),
                    j.getPosicion(),j.getFechaNac(),j.getEdad(),j.getLugarNacimiento(),
                    j.getAltura(),j.getPeso(),j.getPathImage());
            lista.add(dJ);
        }
        ce.guardarJugadores(ruta, lista);
    }
    
}
