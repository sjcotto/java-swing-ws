package WebController;
import java.io.IOException;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Notificaciones extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


       try{
           System.out.println("entro en notificaciones");
            HttpSession s = request.getSession();
            WebServices.UsuarioWS.UsuarioWS usuProxy = (WebServices.UsuarioWS.UsuarioWS)s.getAttribute("ControladorUsuario");


            boolean notA=false;
            boolean notF=false;
            boolean notP=false;

            if(null!=request.getParameter("notA")){
                  notA=true;
            }
            if(null!=request.getParameter("notF")){
                  notF=true;
            }
            if(null!=request.getParameter("notP")){
                  notP=true;
            }
            usuProxy.setNotificaciones(s.getId(),notA,notF,notP);
            request.getRequestDispatcher("/infoUsuario").forward(request,response);
       }
       catch(Exception e){
           e.printStackTrace();
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
