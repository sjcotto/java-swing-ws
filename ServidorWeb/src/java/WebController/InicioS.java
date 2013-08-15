/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.RequestDispatcher;

import WebServices.UsuarioWS.UsuarioWS;
import WebServices.UsuarioWS.UsuarioWSService;
import java.net.URL;
import javax.xml.namespace.QName;



/**
 *
 * @author Santiago
 */
public class InicioS extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String nick = request.getParameter("nick");
        String pass = request.getParameter("pass");

        //Fabrica f = new Fabrica();
        //IControladorUsuarios icU = f.getIControladorUsuarios();

        UsuarioWSService usuServ = new UsuarioWSService(new URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/usuarioWS?wsdl"),
                new QName("http://WebServices/", "UsuarioWSService"));
        UsuarioWS usuProxy = usuServ.getUsuarioWSPort();
        
        HttpSession objSesion = request.getSession(true);

        boolean b = usuProxy.iniciarSesion(objSesion.getId(),nick, pass);

        DataTypes.EstadoSesion nuevoEstado;
        

		// chequea contraseña
        if(!b)
                nuevoEstado = DataTypes.EstadoSesion.LOGIN_INCORRECTO;
        else {
                nuevoEstado = DataTypes.EstadoSesion.LOGIN_CORRECTO;
                // setea el usuario logueado
                WebServices.UsuarioWS.DataUsuario dataU = usuProxy.seleccionarUsuario(objSesion.getId(),nick);
                request.getSession().setAttribute("usuario", dataU);

                //seteamos el controlador del usuario para usarlo mas adelante.
                request.getSession().setAttribute("ControladorUsuario", usuProxy);

                objSesion.setAttribute("recargar","1");//para que recargue los otros frames, solo en el caso de que se logueo correctamente
        }
        objSesion.setAttribute("estado_sesion", nuevoEstado);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/right");
        dispatcher.forward(request, response);
        

    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
