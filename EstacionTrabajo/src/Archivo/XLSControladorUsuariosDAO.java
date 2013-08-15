package Archivo;

import java.util.ArrayList;
import java.util.List;

import Interface.IControladorUsuariosDAO;
import DataTypes.TipoSexo;
import DataTypes.DataUsuario;
import DataTypes.DTOUsuario;
import DataTypes.DataFechaHora;
import DataTypes.DataPaqueteApuestasPersistencia;
import DataTypes.EstadoPaqueteApuestas;

public class XLSControladorUsuariosDAO implements IControladorUsuariosDAO {

    private ManejoDeArchivos arch = null;
    private int fila;

    public XLSControladorUsuariosDAO() {
        arch = ManejoDeArchivos.getInstance();
    }

    public void guardarFecha(String ruta, DataFechaHora fecha) throws Exception {
        arch.agregarString("FechaSistema", 0, 0, fecha.toString());
    }

    public DataFechaHora cargarFecha(String ruta) throws Exception {
        return new DataFechaHora(arch.leerString("FechaSistema", 0, 0));
    }

    public List<DTOUsuario> cargarUsuarios(String ruta) throws Exception {

        List<DTOUsuario> salida = new ArrayList<DTOUsuario>();
        //arch.abrirLibro(ruta);
        int tamanio = Integer.parseInt(arch.leerString("Usuarios", 0, 0));

        for (int u = 1; u < tamanio + 1; u++) {
            ++fila;
            String nom = arch.leerString("Usuarios", 0, fila);
            String nick = arch.leerString("Usuarios", 1, fila);
            String email = arch.leerString("Usuarios", 2, fila);
            String dir = arch.leerString("Usuarios", 3, fila);
            String ci = arch.leerString("Usuarios", 4, fila);
            DataFechaHora fNac = new DataFechaHora(arch.leerString("Usuarios", 5, fila));
            String pais = arch.leerString("Usuarios", 6, fila);
            String sexoS = arch.leerString("Usuarios", 7, fila);
            float saldo = Float.parseFloat(arch.leerString("Usuarios", 8, fila));
            boolean notA=Boolean.parseBoolean(arch.leerString("Usuarios",9, fila));
            boolean notF=Boolean.parseBoolean(arch.leerString("Usuarios",10, fila));
            boolean notP=Boolean.parseBoolean(arch.leerString("Usuarios",11, fila));
            
            TipoSexo sexo = TipoSexo.Hombre;
            if (sexoS.equals("Mujer")) {
                sexo = TipoSexo.Mujer;
            }
            DataUsuario dU = new DataUsuario(nom, nick, email, dir, ci, fNac, pais, sexo, saldo,notA,notF,notP);
            String pass = arch.leerString("Usuarios", 12, fila);
            boolean logeado = Boolean.parseBoolean(arch.leerString("Usuarios", 13, fila));
            List<DataPaqueteApuestasPersistencia> paquetes = new ArrayList<DataPaqueteApuestasPersistencia>();
            Integer nroAp = Integer.parseInt(arch.leerString("Usuarios", 14, fila));
            for (int a = 0; a < nroAp; a++) {
                ++fila;
                Integer idP = Integer.parseInt(arch.leerString("Usuarios", 0, fila));
                String nickU = arch.leerString("Usuarios", 1, fila);
                Integer nroA = Integer.parseInt(arch.leerString("Usuarios", 2, fila));
                String sE=arch.leerString("Usuarios", 3, fila);
                EstadoPaqueteApuestas estadoP=EstadoPaqueteApuestas.Pendiente;
                if(sE.equals("Ganado")){
                    estadoP=EstadoPaqueteApuestas.Ganado;
                }
                if(sE.equals("Perdido")){
                    estadoP=EstadoPaqueteApuestas.Perdido;
                }

                DataPaqueteApuestasPersistencia dPA = new DataPaqueteApuestasPersistencia(idP, nickU,nroA,estadoP);
                  paquetes.add(dPA);
            }

            DTOUsuario dto = new DTOUsuario(dU, pass,logeado,paquetes);
            salida.add(dto);
        }


        return salida;

    }


     public void guardarUsuarios(String ruta, List<DTOUsuario> lista) throws Exception {

        //arch.crearlibroEscYhojas(ruta);
        fila = 0;
        arch.agregarString("Usuarios", 0, 0, Integer.toString(lista.size()));
        for (int u = 0; u < lista.size(); u++) {
            //se ingresan los datos de uno
            ++fila;
            DTOUsuario dto = lista.get(u);
            DataUsuario usuario = dto.getUsuario();
            arch.agregarString("Usuarios", 0, fila, usuario.getNombre());
            arch.agregarString("Usuarios", 1, fila, usuario.getNick());
            arch.agregarString("Usuarios", 2, fila, usuario.getEmail());
            arch.agregarString("Usuarios", 3, fila, usuario.getDir());
            arch.agregarString("Usuarios", 4, fila, usuario.getCI());
            arch.agregarString("Usuarios", 5, fila, usuario.getFechaNac().toString());
            arch.agregarString("Usuarios", 6, fila, usuario.getPais());
            arch.agregarString("Usuarios", 7, fila, usuario.getSexo().toString());
            arch.agregarString("Usuarios", 8, fila, Float.toString(usuario.getSaldo()));
            
            arch.agregarString("Usuarios", 9, fila, Boolean.toString(usuario.isNotApuesta()));
            arch.agregarString("Usuarios", 10, fila, Boolean.toString(usuario.isNotForo()));
            arch.agregarString("Usuarios", 11, fila, Boolean.toString(usuario.isNotPartido()));
            
            arch.agregarString("Usuarios", 12, fila, dto.getPass());
            arch.agregarString("Usuarios", 13, fila, Boolean.toString(dto.getLogeado()));
            arch.agregarString("Usuarios", 14, fila, Integer.toString(dto.getPaquetes().size()));
            List<DataPaqueteApuestasPersistencia> paquetes = dto.getPaquetes();
            for (int a = 0; a < paquetes.size(); a++) {
                ++fila;
                //se guarda un data especial que no posee apuestas sino que se cargaran por competiciones
                DataPaqueteApuestasPersistencia dPA=paquetes.get(a);
                arch.agregarString("Usuarios", 0, fila, Integer.toString(dPA.getIdPaquete()));
                arch.agregarString("Usuarios", 1, fila, dPA.getNickUsuario());
                arch.agregarString("Usuarios", 2, fila, Integer.toString(dPA.getNroApuestasPendientes()));
                arch.agregarString("Usuarios", 3, fila, dPA.getEstadoPaquete().toString());
            }
        }

    }
}