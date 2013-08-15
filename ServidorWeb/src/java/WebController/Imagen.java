/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebController;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebServices.ImageServer.*; 
import java.net.URL;
import java.util.Properties;
import javax.xml.namespace.QName;


/**
 *
 * @author Santiago
 */
public class Imagen extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

    String path = request.getParameter("path").toString();
    System.out.print("path");

    ImageServerService usuServ = new ImageServerService(new URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/imageServerWS?wsdl"),
            new QName("http://WebServices/", "ImageServerService"));
    ImageServer iS = usuServ.getImageServerPort();
    
    try{
       byte[] imgData = iS.getImage(path) ;  
       response.setContentType("image/jpeg");
       OutputStream o = response.getOutputStream();
       o.write(imgData);
       o.flush();
       o.close();
       
    }catch(Exception_Exception e){
    
    }
       // display the image
       
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
