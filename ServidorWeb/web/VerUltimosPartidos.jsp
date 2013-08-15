<%-- 
    Document   : VerUltimosPartidos
    Created on : 02/09/2011, 05:48:34 PM
    Author     : gonzalo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="WebServices.CompeticionesWS.*;"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <style type="text/css">


        body {background-color:#000000; background-image:url(images/fondomain.png); background-repeat:no-repeat;
        font-family:Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif;
        }


        div.background
          {
          width:900px;
          height:1000px;
          background:url(images/fondo.jpeg) repeat;
          border:2px solid black;
          }
        div.transbox
          {
          width:580px;
          /*height:180px;*/
          margin:30px 50px;
          background-color:#ffffff;
          border:1px solid black;
          opacity:0.6;
          filter:alpha(opacity=60); /* For IE8 and earlier */
          }
        div.transbox p
          {
          margin:30px 40px;
          font-weight:bold;
          color:#000000;
        }


          h1{
            color: #ffffff;
           }

         div.ultimosPartidos{
                 width: 600px;
                 margin-left: 100px;
                 
                  }
                  
                 .ults td {
                  text-align:  center;
                  font-family:sans-serif;
                  font-size: 12px;
                       
               }
               
               .ults th{
                   border-style:  solid;
                   font-family:sans-serif;
                   border-color: inherit;
                      text-align:  center;
                font-size: 12px;
                font-weight: bold;
                background-color: #960e27;
                color :  white;
               
         
                  }

                  .ults{
                   table-layout: auto;
                }
                 .b{
 			color : #ffffff;
			}

     </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
        <title>Ver Ultimos Partidos</title>

    </head>
    <body>


    
<h1>Ultimos Partidos</h1>
        <div class="ultimosPartidos"><br><br>
            <%
                CompeticionesWSService serv = new CompeticionesWSService(new java.net.URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/competicionesWS?wsdl"),
                 new javax.xml.namespace.QName("http://WebServices/", "CompeticionesWSService"));
                CompeticionesWS icc = serv.getCompeticionesWSPort();
                ListBean listBDP = icc.obtenerUltimosPartidosFinalizados(session.getId());
                java.util.List<DataPartido> list = (java.util.List)listBDP.getList();
                if (list!=null && !list.isEmpty()){
                    boolean esCopa=false;%>


                
                <div class="transbox">
             <table class="ults">
            <tr>
             <th>Fecha del Partido</th>
             <th>ID Competicion</th>
             <th>Nombre Competicion</th>
             <th>ID Partido</th>
             <th>Evento</th>
             <th>Resultado</th>
             <th>Llave</th>

              </tr>

            <%
                for (DataPartido data: list) {
                    if (data.getDataInfoPart().getTipoC() == TipoCompeticion.COPA)
                        esCopa = true;
                    else
                        esCopa = false;
            %>
 
                <tr onMouseOver="this.bgColor = '#ffd2a6';" onMouseOut="this.bgColor = '#ffffff';" >
                    <td><% out.print(data.getDataInfoPart().getFechaHora().getStr()); %></td>
                    <td><% out.print(data.getDataInfoPart().getIdComp());%></td>
                    <td><% out.print(data.getDataInfoPart().getNomComp()); %></td>
                    <td> <a href="/partido?idComp=<% out.print(Integer.toString(data.getDataInfoPart().getIdComp()));%>&idPart=<%out.print(Integer.toString(data.getDataInfoPart().getIdPar()));%>"> <%out.print(data.getDataInfoPart().getIdPar());%></a></td>
                    <td> <% out.print(data.getDataInfoPart().getNomLocal());
                            out.print(" vs ");
                    out.print(data.getDataInfoPart().getNomVisita());%></td>
                    <td>  <%out.print(data.getGolesL());
                             if ((esCopa) && (data.getGolesL()== data.getGolesV())){
                                 out.print(" (");
                                 out.print(data.getPenalesL());
                                 out.print(") ");
                             }
                              out.print(" - ");
                              out.print(data.getGolesV());
                              if (esCopa && data.getGolesL()== data.getGolesV()){
                                 out.print(" (");
                                 out.print(data.getPenalesV());
                                 out.print(") ");
                             }%>
                    </td>
                    <td>
                        <% if (esCopa)
                               out.print(data.getDataInfoPart().getNomLlave());
                           else
                               out.print("");
                        %>
                    </td>

                </tr>

           
      <%}%>
           </table>
                </div>
                <% } else { %>
                <div class ="b">
                   No Hay Partidos Finalizados En El Sistema.
                </div>
               <%}%>
        </div>
            
                <br>
                
    </body>
</html>
