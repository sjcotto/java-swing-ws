/* Copyright 2005 Sun Microsystems, Inc. All rights reserved. You may not modify, use, reproduce, or distribute this software except in compliance with the terms of the License at: http://developer.sun.com/berkeley_license.html  
$Id: ValidationServlet.java,v 1.4 2006/03/13 22:30:13 gmurray71 Exp $ */

package WebController;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.util.*;

public class ValidationServlet extends HttpServlet {
    
    private ServletContext context;
    private HashMap accounts = new HashMap();

   @Override
    public void init(ServletConfig config) throws ServletException {
	    super.init(config);
        this.context = config.getServletContext();
        accounts.put("greg","account data");
        accounts.put("duke","account data");
        // add a Japanese hiragana example "ne" "ko" (cat)
        accounts.put("\u306D\u3053","account data");
    }

   @Override
   public  void doGet(HttpServletRequest request, HttpServletResponse  response)
        throws IOException, ServletException {
        request.setCharacterEncoding("UTF-8");

        String nick = request.getParameter("id");
        
        WebServices.UsuarioWS.UsuarioWSService usuServ =
                new WebServices.UsuarioWS.UsuarioWSService(new java.net.URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/usuarioWS?wsdl"),
                    new javax.xml.namespace.QName("http://WebServices/", "UsuarioWSService"));
        WebServices.UsuarioWS.UsuarioWS icU = usuServ.getUsuarioWSPort();

        javax.servlet.http.HttpSession session = request.getSession();

        if (nick != null){            
            boolean e = icU.existeNick(session.getId(),nick);
            if (((nick != null) && !e)) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<valid>trueN</valid>");
            } else {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<valid>falseN</valid>");
 
           }
       }else{
            String mail = request.getParameter("mail");
            
            boolean e = icU.existeMail(session.getId(),mail);
            if (((mail != null) && !e) ) {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<valid>trueM</valid>");
            } else {
                response.setContentType("text/xml");
                response.setHeader("Cache-Control", "no-cache");
                response.getWriter().write("<valid>falseM</valid>");
           }
        }
    }

}

