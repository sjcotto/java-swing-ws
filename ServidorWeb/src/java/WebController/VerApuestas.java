/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package WebController;

//import WebServices.UsuarioWS.Exception_Exception;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import WebServices.UsuarioWS.DataFechaHora;

import java.util.List;

/**
 *
 * @author tprog084
 */
public class VerApuestas extends HttpServlet {
   
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        HttpSession s = request.getSession();
        WebServices.UsuarioWS.UsuarioWS usuProxy = (WebServices.UsuarioWS.UsuarioWS)s.getAttribute("ControladorUsuario");

        javax.servlet.http.HttpSession session = request.getSession();

        String Ai = request.getParameter("comboAnioD");
        String Mi = request.getParameter("comboMesD");
        String Di = request.getParameter("comboDiaD");
        
        String Af = request.getParameter("comboAnioH");
        String Mf = request.getParameter("comboMesH");
        String Df = request.getParameter("comboDiaH");
        
        DataFechaHora f_inicial=null;
        DataFechaHora f_final=null;
        
        if ((Mi == null) || (Mi.equals("Mes"))) {
            f_inicial = new DataFechaHora();
            f_inicial.setAnio(1911);
            f_inicial.setMes(1);
            f_inicial.setDia(1);
            f_inicial.setHora(1);
            f_inicial.setMinuto(1);
            f_inicial.setSegundos(1);
            f_final = new DataFechaHora();
            f_final.setAnio(999999);
            f_final.setMes(12);
            f_final.setDia(31);
            f_final.setHora(23);
            f_final.setMinuto(59);
            f_final.setSegundos(59);
        }
        
        else{

             int anio_ini = Integer.valueOf(Ai);
             int mes_ini = Integer.valueOf(Mi);
             int dia_ini = Integer.valueOf(Di);


            int anio_fin = Integer.valueOf(Af);
            int mes_fin = Integer.valueOf(Mf);
            int dia_fin = Integer.valueOf(Df);

            f_inicial = new DataFechaHora();
            f_inicial.setAnio(anio_ini);
            f_inicial.setMes(mes_ini);
            f_inicial.setDia(dia_ini);
            f_inicial.setHora(1);
            f_inicial.setMinuto(1);
            f_inicial.setSegundos(1);
            f_final = new DataFechaHora();
            f_final.setAnio(anio_fin);
            f_final.setMes(mes_fin);
            f_final.setDia(dia_fin);
            f_final.setHora(23);
            f_final.setMinuto(59);
            f_final.setSegundos(59);
        }
        
        try {
            WebServices.UsuarioWS.ListBeanApuesta listB= usuProxy.mostrarHistorialApuestas(session.getId(),f_inicial, f_final);
            List<WebServices.UsuarioWS.DataApuestaWS> list_da = (List)listB.getList();
            request.setAttribute("listaApuestas", list_da);
            request.getRequestDispatcher("/infoUsuario").forward(request,response);
        
        } catch (WebServices.UsuarioWS.Exception_Exception e) {
            request.setAttribute("mensaje", e.getMessage());
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
