/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Controladores;

import java.util.Map;
import java.util.HashMap;
import Objetos.Usuario;
/**
 *
 * @author tprog082
 */
public class ManejadorUsuarios {

    private static ManejadorUsuarios instancia = null;

    private Map<String,Usuario> usuariosEmail;
    private Map<String,Usuario> usuariosNick;

    private Map<String,Usuario> usuarioLogueados = null;//se identifican por el nick, desde la pagina

    private Map<String,Usuario> usuarioLogueadosdisp = null;//se identifican por el nick, usuarios que estan logueados desde el dispositivo movil

    private ManejadorUsuarios(){
        usuariosEmail = new HashMap<String, Usuario>();
        usuariosNick = new HashMap<String, Usuario>();

        this.usuarioLogueados = new HashMap<String, Usuario>();//para la pagina!
        this.usuarioLogueadosdisp = new HashMap<String, Usuario>();//para el celular

    }

    public static ManejadorUsuarios getInstance(){
        if (instancia==null){
            instancia = new ManejadorUsuarios();
        }
        return instancia;
    }

    public Map<String,Usuario> getUsuariosNick(){
        return usuariosNick;
    }

    public Map<String,Usuario> getUsuariosMail(){
        return usuariosEmail;
    }

    public Usuario getUsuario(String nick ){
        return usuariosNick.get(nick);
    }

    public void setUsuario(Usuario u){
        usuariosEmail.put(u.getEmail(), u);
        usuariosNick.put(u.getNick(), u);
    }

    //retorna true si el usuario no pertenece
    public boolean existenDatos(String nick,String email){
        return ((usuariosEmail.containsKey(email)) | (usuariosNick.containsKey(nick)));
    }

     public Map<String,Usuario> getUsuariosConectados(){
        return this.usuarioLogueados;
    }




    public boolean getEstaLogeado(String nick){
             return this.usuarioLogueados.containsKey(nick);
    }

    public void setLogeado(Usuario u){
        this.usuarioLogueados.put(u.getNick(), u);
    }

    public Map<String,Usuario> getUsuariosConectadosDisp(){
        return this.usuarioLogueadosdisp;
    }


}
