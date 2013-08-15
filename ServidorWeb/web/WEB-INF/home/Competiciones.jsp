<%-- 
    Document   : Competiciones
    Created on : 05/09/2011, 05:24:15 PM
    Author     : gonzalo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="WebServices.CompeticionesWS.DataIdNombre" %>
<%@page import="java.util.List" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Competiciones.css">

        <title>Competiciones</title>
    </head>
    <body>
        <h1>Competiciones</h1>
        <div class="competiciones">
            <%
                List<DataIdNombre> competiciones = (List)request.getAttribute("competiciones");
                if (competiciones!=null && !competiciones.isEmpty()){
                for (DataIdNombre comp: competiciones) {
            %>
            <div class="competicion">
                <%  if (comp.getImagePath().equals("")) { %>
                <div class="campoImg">
                    <a href="?idComp=<%=Integer.toString(comp.getId())%>">
                        <img src="/imagen?path=../images/compDefecto.png" alt="Foto Competicion">
                    </a>
                </div>
                <% } else {%>
                <div class="campoImg">
                    <a href="?idComp=<%=Integer.toString(comp.getId())%>">
                        <img src="/imagen?path=<%=comp.getImagePath()%>" alt="Foto Competicion">
                    </a>
                </div>
                <% }%>
                <div class="campoNombre">
                    <a href="?idComp=<%=Integer.toString(comp.getId())%>" ><%= comp.getNombre()%></a>
                </div>
            </div>
            <%  }
             } else { %>
             <div>
                No Hay Competiciones En El Sistema.
             </div>
             <%}%>
        </div>
    </body>
</html>
