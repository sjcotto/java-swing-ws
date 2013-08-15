<%-- 
    Document   : ConfirmarPaquete
    Created on : 19/10/2011, 01:34:01 AM
    Author     : gonzalo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="WebServices.UsuarioWS.*" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <style type="text/css">

        body {background-color:#000000; background-image:url(images/fondomain.png); background-repeat:no-repeat;

                font-weight:bold;
                text-align:center;

                font-family:Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif;
                }
         .todo
                  {
                  margin:0 auto;
                  width:600px;
                  text-align: center;
                  background:#CCCCCC;
                  border:1px solid black;
                  opacity:0.6;
                  filter:alpha(opacity=60); /* For IE8 and earlier */
                  }
       .tabla td, .tabla th, .tabla caption  {
            font-size:100%;
            border:1px solid #000;
            padding:2px 2px 2px 2px;
            text-align: center;
            margin:  auto;
      }
      .tabla th{
           background-color:  firebrick;
            color:  blanchedalmond;
        }
        .tabla caption{
             background-color:  #b7ddf2;
        }
        .tabla{
            margin: auto;
        }

        </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar Paquete</title>
    </head>
    <body>
        <div class="todo">
        <h1>Confirmar Paquete</h1>
        <%  UsuarioWS icU = (UsuarioWS) session.getAttribute("ControladorUsuario");
            DataPaqueteApuestasWS dataPaq = null;
            try {
                dataPaq = icU.mostrarPaqueteApuestas(session.getId());
            }
            catch (ExPaqueteNoActivado_Exception exc) {
            }
            catch (Exception_Exception exc) {
            }

        %>
        <div class="Apuestas">
        <%  java.util.List<DataApuestaWS> listDataAp = dataPaq.getApuestas();
            for (DataApuestaWS dataAp: listDataAp) {
        %>
        <div class="Apuesta">
            <table class="tabla">
            <%  if (dataAp.getTipo()==2) {%>

                    <caption><%out.println("Apuesta Goleador");%></caption>
                    <tr>
                       <th>Competicion</th><th>Jugador</th><th>Dividendo</th><th>Monto</th>
                    </tr>
                    <tr>
                        <td><%out.println(dataAp.getCompeticion().getNombre());%></td>
                        <td><%  out.println(dataAp.getJugador().getNombre());%></td>
                        <td><%  out.print(dataAp.getDividendo()); %></td>
                        <td><%  out.print(dataAp.getMonto()); %></td>
                    </tr>
            
            <%  } else if (dataAp.getTipo()==3) {%>
                    <caption><%out.println("Apuesta Ganador Partido");%></caption>
                       <tr>
                         <th>Competicion</th><th>Ganador</th><th>Dividendo</th><th>Monto</th>
                      </tr>
                    <tr>
                        <td><%out.println(dataAp.getPartido().getDataInfoPart().getNomComp());%></td>
                    <%if(dataAp.getTipoResultado()==TipoDividendo.LOCAL){
            %>
                        <td><%  out.print(dataAp.getPartido().getDataInfoPart().getNomLocal());%></td>
                <%  }
                  else if(dataAp.getTipoResultado()==TipoDividendo.EMPATE){%>
                        <td><%  out.print("Empate");%> </td>
                <%  }
                  else if(dataAp.getTipoResultado()==TipoDividendo.VISITANTE){%>
                        <td><%  out.print(dataAp.getPartido().getDataInfoPart().getNomVisita());%> </td>
                <%  }%>
                     <td><%  out.print(dataAp.getDividendo()); %></td>
                    <td><%  out.print(dataAp.getMonto()); %></td>
                    </tr>
                <%} else if (dataAp.getTipo()==1) {%>

                <caption><%out.println("Apuesta Resultado Exacto");%></caption>
                <tr>
                   <th>Competicion</th><th>Resultado</th><th>Dividendo</th><th>Monto</th>
                </tr>
                <tr>
                  <td><%out.println(dataAp.getPartido().getDataInfoPart().getNomComp());
                %></td>
                <td><%  out.print(dataAp.getPartido().getDataInfoPart().getNomLocal());%>
                <%out.print(" ("+dataAp.getGolesL()+") ");%>
                <%  out.print(dataAp.getPartido().getDataInfoPart().getNomVisita());%>
                <%out.print(" ("+dataAp.getGolesV()+") ");%></td>
                <td><%  out.print(dataAp.getDividendo()); %></td>
                        <td><%  out.print(dataAp.getMonto()); %></td>
                </tr>
                <%  } else if (dataAp.getTipo()==0) {%>
                <caption><%out.println("Apuesta Campeon");%></caption>
                <tr>
                   <th>Competicion</th><th>Campeon</th><th>Dividendo</th><th>Monto</th>
                </tr>
                <tr>
                <td><%out.println(dataAp.getCompeticion().getNombre());
                %></td>
                <td><%out.println(dataAp.getEquipo().getNombre());%></td>
                <td><%  out.print(dataAp.getDividendo()); %></td>
                <td><%  out.print(dataAp.getMonto()); %></td>
                </tr>
                <%  }%>
                
          </table><br>
        <%  }%>
        </div>
        Beneficio total: <% out.print(dataPaq.getMontoTotal()); %>
   </div><br>
        <button type="submit" onclick="parent.frames[2].window.location.href='/agregarPaquete'"> Confirmar </button>
        <button onclick="history.back(1);"> Cancelar </button>
    </div>
    </body>
</html>
