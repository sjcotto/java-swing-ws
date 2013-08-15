
package WebController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import WebServices.UsuarioWS.UsuarioWSService;
import WebServices.UsuarioWS.UsuarioWS;
import WebServices.UsuarioWS.TipoSexo;
import WebServices.UsuarioWS.DataFechaHora;
import org.jasypt.util.password.BasicPasswordEncryptor;
//import DataTypes.DataFechaHora;
//import DataTypes.TipoSexo;

public class Registro extends HttpServlet {

    protected void processRequest(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
        String nombre = request.getParameter("nombre");
        String password = request.getParameter("password");
        String email = request.getParameter("mail");
        String nick = request.getParameter("nick");
        String dir = request.getParameter("dir");

        String ci = request.getParameter("ci");
        String pais = request.getParameter("pais");
        String sexo = request.getParameter("sexo");

        String dia = request.getParameter("comboDia");
        String mes = request.getParameter("comboMes");
        String anio = request.getParameter("comboAnio");

        DataFechaHora dataf = new DataFechaHora();

        dataf.setAnio(Integer.valueOf(anio));
        dataf.setMes(Integer.valueOf(mes));
        dataf.setDia(Integer.valueOf(dia));
        dataf.setHora(0);
        dataf.setMinuto(0);
        dataf.setSegundos(0);

        TipoSexo tipoS;
        if (sexo.equals("hombre")){
            tipoS = TipoSexo.HOMBRE;
        }else{
            tipoS = TipoSexo.MUJER;
        }

        UsuarioWSService service = new UsuarioWSService(new java.net.URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/usuarioWS?wsdl"),
                 new javax.xml.namespace.QName("http://WebServices/", "UsuarioWSService"));
	UsuarioWS usuWS = service.getUsuarioWSPort();

        javax.servlet.http.HttpSession session = request.getSession();

        BasicPasswordEncryptor passwordEncryptor = new BasicPasswordEncryptor();
        String encryptedPassword = passwordEncryptor.encryptPassword(password);


        boolean registroExitoso = usuWS.registrarUsuario(session.getId(),nombre, nick, email, dir, encryptedPassword, tipoS, pais, ci, dataf);

        if (registroExitoso){
            response.sendRedirect("/ultimosPart");//para que cambie la url
        }else{
            request.setAttribute("mensaje", "Error en el registro, nick/mail invalido");
            request.getRequestDispatcher("/WEB-INF/home/error.jsp").forward(request,response);
           
        }

    }
	
    @Override
    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
