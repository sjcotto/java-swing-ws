<%-- 
    Document   : Jugador
    Created on : 20/09/2011, 11:24:47 AM
    Author     : gonzalo
--%>

<%@page import="javax.xml.namespace.QName"%>
<%@page import="java.net.URL"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="WebServices.CompeticionesWS.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Jugador.css"
    </head>
    <body>
        <div class="todo">
        <%  
            CompeticionesWSService serv = new CompeticionesWSService(new java.net.URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/competicionesWS?wsdl"),
                    new javax.xml.namespace.QName("http://WebServices/", "CompeticionesWSService"));
            CompeticionesWS icwc = serv.getCompeticionesWSPort();
            
            int id = Integer.valueOf((String)request.getParameter("idJug"));
            DataJugador dataJ = null;

            try {
                dataJ = icwc.seleccionarJugador(session.getId(),id);
            }
            catch (ExNoExisteJugador_Exception exc) {
                request.setAttribute("mensaje",exc.getMessage());
                request.getRequestDispatcher("error.jsp").forward(request,response);
            }
        %>
        <div class="fotoJug">
            <% if (dataJ.getPathImage().equals("")) {%>
                <img src="/imagen?path=../images/jugDefecto.jpg" alt="Foto Jugador Defecto">
            <% } else {%>
                <img src="/imagen?path=<%= dataJ.getPathImage()%>" alt="Foto Jugador">
            <% }%>
        </div>
        <div class="campoInf">
            <div class="campoNomJug"> <%= dataJ.getNombre() %></div>
            <div class="campoInfComun">
                <div class="campoTxt"> Nombre Completo: <%= dataJ.getNombreCompleto() %></div>
                <div class="campoTxt"> Posicion: <%= dataJ.getPosicion() %></div>
                <div class="campoTxt"> Edad: <%= dataJ.getEdad() %></div>
                <div class="campoTxt"> Fecha Nacimiento: <%= dataJ.getFechaDeNacimiento().getStr() %></div>
                <div class="campoTxt"> Lugar de Nacimiento: <%= dataJ.getLugarNacimiento() %></div>
                <div class="campoTxt"> Altura: <%= dataJ.getAltura()%>m</div>
                <div class="campoTxt"> Peso: <%= dataJ.getPeso()%>Kg.</div>
            </div>
        </div>
        </div>
    </body>
</html>


