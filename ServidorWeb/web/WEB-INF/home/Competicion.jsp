<%-- 
    Document   : Competicion
    Created on : 05/09/2011, 06:19:25 PM
    Author     : gonzalo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@page import="java.util.List"%>
<%@page import="WebServices.CompeticionesWS.*"%>
<%@page import="WebServices.UsuarioWS.DataUsuario"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" media="screen" href="css/Competicion.css" />
             <style type="text/css">
   


        </style>

        <script language="JavaScript">
            function CalcularPremio(){

                var m = document.getElementById("idMonto").value;
                var d = document.getElementById("idDividendos").innerHTML;

                var premio=m*d*100;
                premio=Math.floor(premio);
                premio=premio/100;
                //a 2 decimales

                document.getElementById("idPremio").innerHTML = premio;
                var r =  document.getElementById("idPremio").innerHTML;

                if (r == "NaN"){
                    alert("Monto invalido");
                }
            }

            function FrameApuesta (idCom,idEq,div) {

                document.getElementById("idDividendos").innerHTML = div;
                document.getElementById("idSelDiv").innerHTML = div;
                document.getElementById("idComp").value = idCom;
                document.getElementById("idEq").value = idEq;
                CalcularPremio();
            }

            function Apostar(){
                var r =  document.getElementById("idPremio").innerHTML;
                var a = document.getElementById("sesion").innerHTML;
                var m = document.getElementById("idMonto").value;

                var pFinalizado = document.getElementById("iddp").innerHTML;

                if (pFinalizado == -1){
                        alert("La competicion esta finalizada, no se puede apostar");
                        return false;
                }

                if ((r == "NaN")||(m == "") || (m ==0)){
                        alert("Monto invalido");
                        return false;
                }

                var h = document.getElementById("idSelDiv").innerHTML;

                if (parseFloat(h)==-1){
                        alert("No ha seleccionado ningun dividendo")
                        return false;
                }
                if (a==-1){ //deberia de andar
                        alert("Debe iniciar sesion para Apostar!!");
                        return false;
                }

                var aa = parseFloat(a);
                var mm = parseFloat(m);

                var g = document.getElementById("paquete").value;

                if ((g!=1) && (aa < mm)){
                        //vemos si el saldo es correcto
                        alert("Saldo insuficiente");
                        return false;
                }

                return true;
            }

            function Ocultar (apus) {
                if (apus == 1) {
                    var apus = document.getElementById("eqApuestas1");
                    var image = document.getElementById("imagenFlecha1");
                    if (apus.style.display == "none") {
                        apus.style.display = "inline-block";
                        image.src = "/imagen?path=../images/cerrar.png";
                    } else {
                        apus.style.display = "none";
                        image.src = "/imagen?path=../images/abrir.png";
                    }
                } else {
                    var apus = document.getElementById("eqApuestas2");
                    var image = document.getElementById("imagenFlecha2");
                    if (apus.style.display == "none") {
                        apus.style.display = "inline-block";
                        image.src = "/imagen?path=../images/cerrar.png";
                    } else {
                        apus.style.display = "none";
                        image.src = "/imagen?path=../images/abrir.png";
                    }
                }


                return true;
            }



            </script>

    </head>
    <body>

<!-- COTTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO -->
    <div id=stylized class=myform>
    <form  onsubmit="return Apostar();" name="form22" method="post" action="">
    <label>Monto:
    </label>
        <input onkeyup="CalcularPremio()" id="idMonto" name="monto"/>
    <span class=small>X</span>
    <span class=small id="idDividendos" >1.00</span>
    <label>Premio
    </label>
    <label id="idPremio">0
    </label>

    <input id="idEq" name ="idEq"  style="display: none">
    <input id="idComp" name ="idComp"  style="display: none">
    <input id="tipo"  name ="tipo" value="Competicion" style="display: none">
    <input id="paquete" name="paquete" style="display: none">
    <input id="idJug" name="idJug" style="display: none">
    
    <button type=”submit” onclick="document.getElementById('paquete').value=0; document.form22.action='Apostar';">Apostar!!</button>
    <button type=”submit” onclick="document.getElementById('paquete').value=1; document.form22.action='Apostar';">Paquete!!</button>
    <div class=spacer>
    </div>
    </form>
    </div>
        <div style="display: none" id="sesion">
            <%     HttpSession s = request.getSession();
                   if ((s.getAttribute("estado_sesion") == null) || (s.getAttribute("estado_sesion") == DataTypes.EstadoSesion.LOGIN_INCORRECTO) ) {
                       out.print(-1.0);
                    }else{
                        DataUsuario dataU = (DataUsuario) s.getAttribute("usuario");
                        out.print(dataU.getSaldo());
                    }
                   %>
        </div>
        <div style="display: none" id="idSelDiv">-1</div>
<!-- COTTOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOOO -->

        <div class="todo">

        <!-- Muestro partidos, dividendos por equipo,
             esta def, tipoComp, y tabla si es liga -->
        <div class="infoCompBasica">
            
            <%  String pathFoto =  (String) request.getAttribute("pathImage");
                if (pathFoto.equals("")) {
            %>
                <img src="/imagen?path=../images/compDefecto.png" alt="FotoCompeticion">
            <%} else { %>
                <img src="/imagen?path=<%=pathFoto%>" alt="FotoCompeticion">
            <%}%>
            
            <div class="idenComp">
                <div class="campoNombre"> <%= request.getAttribute("nomComp")%> </div>
                <div class="campoEstaDef"> 
                    <%  out.print(request.getAttribute("tipoComp"));
                        Boolean def = (Boolean)request.getAttribute("estaDefinida");
                        Boolean fin = (Boolean) request.getAttribute("fin");
                        if (fin)
                            out.print(" finalizada");
                        else {
                            if (def)
                                out.print(" definida");
                            else
                                out.print(" no definda");
                        }
                    %>
                 </div>
            </div>
        </div>
        <%
                List<DataDivEquipo> dividendos = (List)request.getAttribute("dividendos");

                String idComp = (String)request.getAttribute("idComp");

               // if (dividendos.get(0).getDividendo()!=0) {
         %>
        <div class="dividendos">            
                <div class="eqApuesta">
                    <div class="campoEqApuesta">Equipos</div>
                    <%if (dividendos.get(0).getDividendo()!=0) {%>
                    <div class="campoEqApuesta">Dividendo Campeon</div>
                    <%}%>
                </div>

                <div class="eqApuestas1" id="eqApuestas1" style="display:none">
                    <%      for (DataDivEquipo divEq: dividendos) {
                    %>
                    <div class="eqApuesta" onclick="document.getElementById('idJug').value=-1">
                        <div class="campoEqApuesta"> <a href="/equipos?idEq=<%=divEq.getId()%>&nomEq=<%=divEq.getNombre()%>">
                        <%=divEq.getNombre()%> </a>
                        </div>
                        <%if (dividendos.get(0).getDividendo()!=0) {%>
                        <div class="campoEqApuesta">
                            <a href="javascript:FrameApuesta(<%out.print(idComp+","+divEq.getId()+","+divEq.getDividendo());%>)">
                            <%=divEq.getDividendo()%>
                            </a>
                        </div>
                        <%}%>
                    </div>
                    <%      } %>
                </div>
        </div>

        <button class="ocultar" onmouseover="javascript:Ocultar(1)">
            <img class="imagenFlecha1" id="imagenFlecha1" src="/imagen?path=../images/abrir.png" alt="imagen">
        </button><br>


        <% if (!(request.getAttribute("tipoComp")==TipoCompeticion.PARTIDO_INDIVIDUAL)) {%>
            <div class="dividendos">
                <div class="eqApuesta">
                    <div class="campoEqApuesta">JugadoR</div>
                    <div class="campoEqApuesta">Dividendo Goleador</div>
                </div>
                <div class="eqApuestas2" id="eqApuestas2" style="display:none">
                    <%  List<DataGoleador> goleadores = (List<DataGoleador>)request.getAttribute("goleadores");
                    if (goleadores!=null) {
                    for (DataGoleador dataJugCamp: goleadores) {
                    %>
                    <div class="eqApuesta" onclick="document.getElementById('idJug').value=<%= dataJugCamp.getId() %>">
                        <div class="campoEqApuesta">
                            <a href="/jugador?idJug=<%= dataJugCamp.getId() %>">
                            <%=dataJugCamp.getNombre() %>
                            </a>
                        </div>
                        <div class="campoEqApuesta">
                            <a href="javascript:FrameApuesta(<%out.print(idComp+","+dataJugCamp.getId()+","+dataJugCamp.getDividendo());%>)">
                            <%out.print(dataJugCamp.getDividendo()); %>
                            </a>
                        </div>
                    </div>
                    <% }
                     }
                    %>
                </div>
                <button class="ocultar" onmouseover="javascript:Ocultar(2)">
                <img class="imagenFlecha2" id="imagenFlecha2" src="/imagen?path=../images/abrir.png" alt="imagen">
                </button>
            </div>
        <% } %>

        <div class="bloqueParTabl">
                <%  
                    if (((TipoCompeticion)request.getAttribute("tipoComp"))==TipoCompeticion.LIGA) {%>
                <div class="partidos">
                    <% } else {%>
                    <div class="partidosNoLiga">
                        <% }%>
                        <table border="0" cellpadding="0" cellspacing="0" class="tabla">
                            <caption>Partidos</caption>
                            <tr class="alt">
                            <th>ID</th>
                            <th>Local</th> <th>Visitante</th>
                            <th width="60px">Dividendos Asignados</th> <th width="60px">Esta Finalizado</th>
                            

                            </tr>
                            <%
                            List<DataPartido> partidos = (List)request.getAttribute("partidos");
                            for (DataPartido part: partidos) {
                            %>
                            <tr onMouseOver="this.bgColor = '#ffd2a6';" onMouseOut="this.bgColor = '#ffffff';" class="alt" >
                            <td><a href="/partido?idPart=<%= part.getDataInfoPart().getIdPar()%>&idComp=<%= part.getDataInfoPart().getIdComp() %>">
                            <%= part.getDataInfoPart().getIdPar()%></a>
                            </td>
                            <td><%  if (!part.getDataInfoPart().getNomLocal().equals(""))
                            out.print(part.getDataInfoPart().getNomLocal());
                            else {
                            if (part.getDataInfoPart().isTercer())
                            out.print("Perdedor ");
                            else
                            out.print("Ganador ");
                            out.print(part.getDataInfoPart().getNomLlavePreLoc());
                            }
                            %>
                            </td>
                            <td>
                            <%  if (!part.getDataInfoPart().getNomVisita().equals(""))
                            out.print(part.getDataInfoPart().getNomVisita());
                            else {
                            if (part.getDataInfoPart().isTercer())
                            out.print("Perdedor ");
                            else
                            out.print("Ganador ");
                            out.print(part.getDataInfoPart().getNomLlavePreVis());
                            }
                            %>
                            </td>
                            <td>
                            <%  if (part.getDividendos().isEstanAsignados())
                            out.print("Si");
                            else
                            out.print("No");
                            %>
                            </td>
                            <td>
                            <%  if (part.isEstaFinalizado())
                            out.print("Si");
                            else
                            out.print("No");
                            %>
                            </td>
                            
                            </tr>
                            <%}%>
                        </table>
                        <% if (((TipoCompeticion)request.getAttribute("tipoComp"))==TipoCompeticion.LIGA) {%> 
                    </div>
                    <% } else {%>
                </div>
            <% }%>

            <%
                        List<DataEquipoLiga> tabla = (List)request.getAttribute("tabla");
                        if (tabla!=null) {
            %>
            <div class="tablaPosiciones">
                <table class="tabla" border="0" cellpadding="0" cellspacing="0">
                    <caption> Tabla de Posiciones  </caption>
                    <thead>
                        <tr>
                            <th> Pos. </th> <th width="50px"> Equipo </th> <th> PJ </th> <th> G </th>
                            <th> E </th> <th> P </th> <th> GF </th> <th> GC </th>
                            <th> +/- </th> <th> Ptos. </th>
                        </tr>
                    </thead>
                    <tbody>
                    <%      for (DataEquipoLiga dataEL: tabla) { %>
                        <tr onMouseOver="this.bgColor = '#ffd2a6';" onMouseOut="this.bgColor = '#ffffff';" >
                            <td><%= dataEL.getPosicion() %></td>
                            <td><a href="/equipos?idEq=<%out.print(Integer.toString(dataEL.getIdEquipo()));%>&nomEq=<%out.print(dataEL.getNombreEquipo());%>"><%= dataEL.getNombreEquipo() %></a></td>
                            <td><%= dataEL.getNroPartidosGanados()+dataEL.getNroPartidosEmpatados()+dataEL.getNroPartidosPerdidos() %></td>
                            <td><%= dataEL.getNroPartidosGanados() %></td>
                            <td><%= dataEL.getNroPartidosEmpatados() %></td>
                            <td><%= dataEL.getNroPartidosPerdidos() %></td>
                            <td><%= dataEL.getGolesFavor() %></td>
                            <td><%= dataEL.getGolesContra() %></td>
                            <td><%= dataEL.getGolesFavor()-dataEL.getGolesContra() %></td>
                            <td><%= dataEL.getPuntosEquipo() %></td>

                        </tr>
                    <%      } %>
                    </tbody>
                </table>
                </div>
            <%          }%>

        </div>



               <%-- GOleadores --%>

            <%
                        if (((TipoCompeticion)request.getAttribute("tipoComp"))!=TipoCompeticion.PARTIDO_INDIVIDUAL) {
                        List<DataGoleador> goleadores = (List)request.getAttribute("maxGoleadores");
                        System.out.println("ENTRO GOLEADOREEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEEES");
                        if (goleadores!=null && goleadores.size()>0) {
            %>
            <div class="tablaPosiciones">
                <table class="tabla" border="0" cellpadding="0" cellspacing="0">
                    <caption> Tabla de Goleadores  </caption>
                    <thead>
                        <tr>
                            <th> Pos. </th>
                            <th> Id </th>
                            <th> Nombre </th>
                            <th> Goles </th>
                        </tr>
                    </thead>
                    <tbody> <%  
                        int i=0;
                        int golesPrevios=-1;
                        for (DataGoleador dG: goleadores) {
                            if(golesPrevios<dG.getGoles()){
                                ++i;
                            }
                            golesPrevios=dG.getGoles();
                        %>
                        <tr onMouseOver="this.bgColor = '#ffd2a6';" onMouseOut="this.bgColor = '#ffffff';" >
                            <td><%=i %></td>
                            <td> <a href="/jugador?idJug=<%= dG.getId()%>"><% out.print(dG.getId());%></a></td>
                            <td><%= dG.getNombre() %></td>
                            <td><%= dG.getGoles() %></td>
                        </tr>
                    <%      } %>
                    </tbody>
                </table>
                </div>
            <%          }
                 }
            %>




            <%-- fin goleadores--%>








        </div>

        <div style="display: none" id="iddp">
            <% if ((Boolean)request.getAttribute("fin"))
                    out.print(-1);
               else{
                    out.print(0);
               }
                %>
        </div>

        
    </body>
</html>
