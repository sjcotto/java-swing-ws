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
import WebServices.CompeticionesWS.*;
import java.io.FileInputStream;
import java.net.URL;
import java.util.Properties;
import javax.xml.namespace.QName;

/**
 *
 * @author tprog082
 */
public class Index extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String dir = null;
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream("web_services.xml");
        prop.loadFromXML(fis);
        dir = prop.getProperty("dir_publicar");

        request.getSession().setAttribute("DIR", dir);
        
        UsuarioWSService serviceUsu = new UsuarioWSService(new URL("http://"+dir+":8280/usuarioWS?wsdl"),new QName("http://WebServices/", "UsuarioWSService"));
        UsuarioWS proxyUsu = serviceUsu.getUsuarioWSPort();
        
        CompeticionesWSService serviceComp = new CompeticionesWSService(new URL("http://"+dir+":8280/competicionesWS?wsdl"),new QName("http://WebServices/", "CompeticionesWSService"));
        CompeticionesWS proxyComp = serviceComp.getCompeticionesWSPort();

        proxyUsu.agregarUsuarioSesion(request.getSession().getId());
        proxyComp.agregarCompeticionSesion(request.getSession().getId());

        request.getRequestDispatcher("/index2.html").forward(request, response);


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
