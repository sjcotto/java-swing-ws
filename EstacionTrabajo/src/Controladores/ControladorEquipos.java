package Controladores;

import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import Interface.IControladorEquipos;
import DataTypes.DataEquipo;
import DataTypes.DataIdNombre;
import Objetos.Equipo;

/// PERSISTENCIA
import Interface.IControladorEquipoDAO;
import Interface.XLSFabricaDAO;

import Excepciones.ExNoExisteEquipo;

public class ControladorEquipos implements IControladorEquipos  {
    
    // CONSTRUCTOR
    public ControladorEquipos() {}
 
    public DataEquipo getDataEquipo(int id) {//throws Exception{
        Map<Integer,Equipo> equipos = ManejadorEquipos.getInstance().getEquipos();

        Equipo equipo = equipos.get(id);
        return new DataEquipo(equipo.getId(),equipo.getNombre());
    }

    public Equipo buscarEquipo(int id){
        Map<Integer,Equipo> equipos = ManejadorEquipos.getInstance().getEquipos();
        Equipo equipo = (Equipo)equipos.get(id);
        return equipo;
    }

    // ALTA EQUIPO
    public int darAltaEquipo(String nombre,String image){
        Map<Integer,Equipo> equipos = ManejadorEquipos.getInstance().getEquipos();
        int id = ManejadorEquipos.getInstance().getIdEquipos();
        ManejadorEquipos.getInstance().setIdEquipos(id+1);

        Equipo e = new Equipo(id,nombre,image);
        equipos.put(id, e);
        //queda equipo con la clave id guardado en el diccionario equipos.
        return id;
    }

    // VER EQUIPO
    public List<DataEquipo> listarEquipos(){
        Map<Integer,Equipo> equipos = ManejadorEquipos.getInstance().getEquipos();

        List<DataEquipo> lista = new ArrayList<DataEquipo>();
        for (Map.Entry<Integer, Equipo> entry : equipos.entrySet()) {
            Equipo e = entry.getValue();
            DataEquipo data = new DataEquipo(e.getId(),e.getNombre(),e.getImagePath());
            lista.add(data);
        }
        return lista;
    }

    public List<DataIdNombre> seleccionarEquipo(int id)
            throws ExNoExisteEquipo{
        Map<Integer,Equipo> equipos = ManejadorEquipos.getInstance().getEquipos();

        //listamos los jugadores que tiene asigando un jugador
        if (!equipos.containsKey(id))
              throw (new ExNoExisteEquipo("No Existe Un Equipo con el Id " + Integer.toString(id)));

        Equipo equipo = equipos.get(id);
        List<DataIdNombre> lista = equipo.obtenerJugadores();
        return lista;
    }

    public void cargarEquipos(String ruta)throws Exception{

        ManejadorEquipos.getInstance().getEquipos().clear();
        ManejadorEquipos.getInstance().setIdEquipos(1);

        XLSFabricaDAO f=new XLSFabricaDAO();
        IControladorEquipoDAO ce=f.getIControladorEquiposDAO();
        System.out.println("CARGARA LOS EQUIPOS");
        List<DataEquipo> lista=ce.cargarEquipos(ruta);
        System.out.println("NROEQUIPOSCARGADOS: "+lista.size());
        Map<Integer,Equipo> equipos = ManejadorEquipos.getInstance().getEquipos();
        int tope=0;
        for(int i=0;i<lista.size();i++){
            DataEquipo dE=lista.get(i);
            int id=dE.getId();
            Equipo e = new Equipo(id,dE.getNombre(),dE.getImagePath());
            equipos.put(id,e);
            if(id>tope){
                tope=id;
            }
        }
        ManejadorEquipos.getInstance().setIdEquipos(tope+1);
    }

    public void guardarEquipos(String ruta)throws Exception{
        XLSFabricaDAO f=new XLSFabricaDAO();
        IControladorEquipoDAO ce=f.getIControladorEquiposDAO();
        ce.guardarEquipos(ruta,this.listarEquipos());
    }


}