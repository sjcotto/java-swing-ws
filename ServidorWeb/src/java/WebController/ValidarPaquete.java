/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebController;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebServices.UsuarioWS.*;

/**
 *
 * @author gonzalo
 */
public class ValidarPaquete extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        UsuarioWS icUsu = (UsuarioWS) request.getSession().getAttribute("ControladorUsuario");

        javax.servlet.http.HttpSession session = request.getSession();

        boolean paqValido = true;
        try {
            paqValido = icUsu.validarPaqueteApuestas(session.getId());
            if (!paqValido) {
                request.getSession().setAttribute("recargar","3");
            } else {
                request.getSession().setAttribute("recargar","4");
            }
            request.getRequestDispatcher("/right").forward(request, response);
            return;
        }
        catch (Exception_Exception exc) {
            request.setAttribute("mensaje", exc.getMessage());
            request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
        }
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
