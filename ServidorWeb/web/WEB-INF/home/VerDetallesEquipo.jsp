<%-- 
    Document   : VerDetallesEquipo
    Created on : 02/09/2011, 05:49:23 PM
    Author     : gonzalo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="WebServices.CompeticionesWS.*" %>
<%@page import="java.util.List" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="css/DetallesEquipo.css">
        <title>Ver Detalles Equipo</title>
    </head>
    <body>
        <div class="todo">
        <div class="infoEquipoBasica">
            <div class="nombre"><%= request.getAttribute("nomEq")%> </div>
        <div class="imagen">

            <%String img = (String)request.getAttribute("imgEquipo");
             if (!img.equals("")){ %>
                <img src= "/imagen?path=<%=img%>" alt="FotoEqLoc"><%}
                      else {%>
               <img src="/imagen?path=../images/eq_def.jpg" alt="FotoEquipo"><%}%>
         </div>
        </div><br><br><br>
        <div class="jugs">
                <label>Jugadores Del Equipo</label>
        </div><br><br><br><br>

        <div class="jugadores">    
            <%
                List<DataIdNombre> jugadores = (List)request.getAttribute("jugadoresEquipo");
                if (jugadores.isEmpty()){%>
                     <div class="jugs">
                         <label>No Hay Jugadores Para El Equipo</label>
                     </div>
                     <%}else{
                    for (DataIdNombre jug: jugadores) {
            %>
            <div class="jugador">
                <span class="campoImg">
                    <%  String pathFoto = jug.getImagePath();
                        if (pathFoto.equals("")) {
                    %>
                        <a href="/jugador?idJug=<%=jug.getId()%>">
                            <img src="/imagen?path=../images/jugDefecto.jpg" alt="Foto jugador">
                        </a>
                    <%} else { %>
                        <a href="/jugador?idJug=<%=jug.getId()%>">
                            <img src="/imagen?path=<%=pathFoto%>" alt="Foto jugador">
                        </a>
                    <%}%>
                </span>
                <span class="campoNombre">
                    <a href="/jugador?idJug=<%=jug.getId()%>"> <%=jug.getNombre()%> </a>
                </span>
            </div>
            <% }
               }
            %>
        </div>
        </div>
    </body>
</html>
