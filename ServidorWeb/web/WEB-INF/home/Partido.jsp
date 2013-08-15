<%--
    Document   : Partido
    Created on : 06/09/2011, 02:52:44 AM
    Author     : gonzalo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="WebServices.CompeticionesWS.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
       <title>Detalles De Partido</title>

     <style type="text/css">
                body{
                font-family:Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif;
                font-size:14px;
                background-color:#000000; background-image:url(images/fondomain.png); background-repeat:no-repeat;
                }
                p, h1, form, button{border:0; margin:0; padding:0;}
                .spacer{clear:both; height:1px;}
                /* ���� My Form ���� */
                .myform{
                /* margin:0 auto; */

                margin-left:auto;
                background:#ebf4fb;
                width:200px;
                padding:10px;
                border-color: inherit;
                border-style: outset;
                opacity:0.7;
                float: right;

                }
                /* ���� stylized ����   background:#ebf4fb;
                border:solid 2px #b7ddf2;

                */
                h1{
                margin-left: 150px;;
                color:  #FFFFFF;
                }

                #stylized{
                    /* background:#CCCCCC; */
                }
                #stylized h1 {
                font-size:14px;
                font-weight:bold;
                margin-bottom:8px;
                }
                #stylized p{
                font-size:11px;
                color:#666666;
                margin-bottom:20px;
                border-bottom:solid 1px #b7ddf2;
                padding-bottom:10px;
                }
                #stylized label{
                display:block;
                font-weight:bold;
                text-align:right;
                width:80px;
                float:left;
                }
                #stylized .small{
                color:#666666;
                display:block;
                font-size:15px;
                font-weight:normal;
                text-align:right;
                width:100px;
                }
                #stylized input{
                float:left;
                font-size:12px;
                padding:4px 2px;
                border:solid 1px #aacfe4;
                width:60px;
                margin:2px 0 20px 10px;
                }
                #stylized button{
                clear:both;
                margin-left:50px;
                width:100px;
                height:31px;
                background:#666666;
                text-align:center;
                line-height:31px;
                color:#FFFFFF;
                font-size:11px;
                font-weight:bold;
                }

           .det
          {
          width:800px;
          /*height:180px;*/
          margin:30px 50px;
          background-color:#ffffff;
          border:1px solid black;
          opacity:0.6;
          filter:alpha(opacity=60); /* For IE8 and earlier */
          /*height: 420px;*/
          text-align:  center;
          float: left;
          }
          .nomComp{
               font-size: 40px;
               text-align:  center;
               color: #000000;
                }

          .detallesPart{

                  width: 600px;
                  text-align:  center;
                  margin-left: 95px;
                }

          .vs{
              font-size: 40px;
              width: 200px;
              margin-left:  50px;
              margin-right: 50px;
              margin-bottom: 105px;

            }

            .nombreLoc{
               font-size: 25px;
               color: #000;
               margin-right: 230px;
             }
             .nombreVis{
                 font-size: 25px;
                 color: #000;

                }
             .res{
                font-size: 50px;
                margin-bottom:  50px;
                background-color: #000;
                color: #FFFFFF;
                text-align:  center;
               }

               .info{
                    font-size: 30px;
                    text-align:  center;

                    }

                    .divs{
                      font-size: 20px;
                    }


                    .jugadores {
                        width:800px;
                        margin-left: 50px;
                         background-color:#ffffff;
                         border:1px solid black;
                         opacity:0.6;
                          filter:alpha(opacity=60); /* For IE8 and earlier */
                          display:  inline-block;
                  }
                  .nombreJugador{
                          font-size: 15px;
                          margin-left: 15px;
                          display: inline-block;
                        }

                  .jugsLocal{
                      width: 350px;
                      display: block;
                      float: left;
                      text-align:  center;
                    }


                    .jugsVisit{

                       width: 350px;
                      display:  block;
                      float: right;
                      text-align:  center;

                    }

                    .t{
                       font-size: 20px;
                       color: #000;
                    }

                    .resExacto {
                        display: inline-block;
                        border: 1px solid black;
                    }
                    .resExacto:hover {
                        border: 2px solid red;

                    }
        .ults{
                   table-layout: auto;
                }
            .transbox
          {
          width:700px;
          margin:30px 50px;
          background-color:#ffffff;
          border:1px solid black;
          opacity:0.6;
          filter:alpha(opacity=60); /* For IE8 and earlier */
          }
		  .eventos{
                 width: 700px;
                 margin-left: 50px;
                  }
                  .evento{
                        display: inline-block;
                  }

                  .evento img {
                    width: 12px;
                    height: 12px;
                    }

                    .fotito {
                        display: inline-block;
                    }
		  
        </style>

        <script language="JavaScript">
            function CalcularPremio(){
                var m = document.getElementById("idMonto").value;
                var d = document.getElementById("idDividendos").innerHTML;
                //limitamos el numero de decimales a mostrar
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

            function FrameApuesta (idCom,idPar,tipoC,tipoA,div) {
                document.getElementById("idDividendos").innerHTML = div;

                document.getElementById("idSelDiv").innerHTML = div;

                document.getElementById("idComp").value = idCom;
                document.getElementById("idPar").value = idPar;
                document.getElementById("tipoA").value = tipoA;
                document.getElementById("tipoC").value = tipoC;
                

                CalcularPremio();
            }

            function Apostar(){
               var r =  document.getElementById("idPremio").innerHTML;
               var a = document.getElementById("sesion").innerHTML;
               var m = document.getElementById("idMonto").value;

               var pFinalizado = document.getElementById("iddp").innerHTML;

               if (pFinalizado == -1){
                   alert("El Partido esta finalizado, no se puede apostar");
                   return false;
               }

               if (a==-1){ //deberia de andar
                   alert("Debe iniciar sesion para Apostar!!");
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
            </script>

    </head>

    <body>
        <h1>Detalles De Partido</h1>

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

    <input id="idComp" name ="idComp" style="display: none">
    <input id ="idPar" name ="idPar" style="display: none">
    <input id="tipoA" name ="tipoA" style="display: none">
    <input id="tipoC" name ="tipoC" style="display: none">
    <input id="tipo"  name ="tipo" value="Partido" style="display: none">
    <input id="paquete" name="paquete" style="display: none">
    <input id="golL" name="golL" style="display: none">
    <input id="golV" name="golV" style="display: none">

    <button type=”submit” onclick="document.getElementById('paquete').value=0; document.form22.action='Apostar';">Apostar!!</button>
    <button type=”submit” onclick="document.getElementById('paquete').value=1; document.form22.action='Apostar';">Paquete!!</button>
    <div class=spacer></div>

    </form></div>
        <div class="det">
            <% DataPartido dp = (DataPartido)request.getAttribute("detalles");
               DataInfoPartido dip = dp.getDataInfoPart();
               String imagenLoc= (String)request.getAttribute("imgLoc");
               String imagenVis= (String)request.getAttribute("imgVis");
                    %>
            Apostar Res Exacto <br>
            <%  FloatArrayArray div = (FloatArrayArray)request.getAttribute("divResEx");
                List<FloatArray> list = div.getItem();
                for (int i=0; i<10; i++) {
                    FloatArray fa = list.get(i);
                    for (int j=0; j<10; j++) {
                        float f = fa.getItem().get(j).floatValue();
                        if (f!=0) {
            %>
                        <button class="resExacto" onclick="document.getElementById('golL').value = <%out.print(i);%>;
                            document.getElementById('golV').value = <%out.print(j);%>;
                            javascript:FrameApuesta(<%out.print(dp.getDataInfoPart().getIdComp());%>,
                                                    <% out.print(dp.getDataInfoPart().getIdPar());%>,
                                                    <% if (dp.getDataInfoPart().getTipoC()==TipoCompeticion.PARTIDO_INDIVIDUAL)
                                                            out.print("0");
                                                       else
                                                           out.print("2");
                                                    %>,
                                                    4,<%out.print(f);%>)">
                         <%
                         out.print(i+"-"+j+" ("+f+")");
                         %>
                        </button>
                        <%}%>
                    <%}%>
                <%}%>

                        <div class="nomComp">
                        <% out.println(dip.getNomComp());%><br><br>
                        </div>
                    <div class="detallesPart">

                            <% if(!imagenLoc.equals("")){%>
                                 <a href="/equipos?idEq=<%=dip.getIdLocal()%>&nomEq=<%=dip.getNomLocal() %>">
                                    <img src= "/imagen?path=<%=imagenLoc%>" alt="FotoEqLoc">
                                </a>
                            <% } else {%>
                                <a href="/equipos?idEq=<%=dip.getIdLocal()%>&nomEq=<%=dip.getNomLocal() %>">
                                <img src="/imagen?path=../images/eq_def.jpg" alt="FotoEquipo">
                                </a>
                            <%}%>
                       <span class="res">
                           <% if (dp.isEstaFinalizado()) {
                                    out.print("  ");out.print(dp.getGolesL());out.print(" ");
                               if((dip.getTipoC()==TipoCompeticion.COPA) && (dp.getGolesL()==dp.getGolesV())) {
                                   out.print("  (");out.print( dp.getPenalesL());out.print(")");
                                }
                           }%></span>

                          <span class="vs">  VS </span>
                          <span class="res">
                           <%if (dp.isEstaFinalizado()){
                                 if((dip.getTipoC()==TipoCompeticion.COPA) && (dp.getGolesL()==dp.getGolesV())) {
                                   out.print("  (");out.print( dp.getPenalesV());out.print(") ");
                                   }
                                 out.print("  ");out.print(dp.getGolesV());out.print(" ");
                             }%> </span>

                          <% if(!imagenVis.equals("")){%>
                             <a href="/equipos?idEq=<%=dip.getIdVisita()%>&nomEq=<%=dip.getNomVisita() %>">
                             <img src= "/imagen?path=<%=imagenVis%>" alt="FotoEqVis">
                             </a>
                         <%} else {%>
                            <a href="/equipos?idEq=<%=dip.getIdVisita()%>&nomEq=<%=dip.getNomVisita() %>">
                                <img src="/imagen?path=../images/eq_def.jpg" alt="FotoEquipo">
                            </a>
                         <%}%>
                    </div>
                    <span class="nombreLoc">
                        <a href="/equipos?idEq=<%=dip.getIdLocal()%>&nomEq=<%=dip.getNomLocal() %>">
                            <% out.print(dip.getNomLocal());%>
                        </a>
                    </span>
                         <span class="nombreVis">
                             <a href="/equipos?idEq=<%=dip.getIdVisita()%>&nomEq=<%=dip.getNomVisita() %>">
                             <%out.print(dip.getNomVisita());
                              %>
                              </a>
                          </span ><br><br>
                           <%int tipoC = 1;
                              if (dip.getTipoC()==TipoCompeticion.PARTIDO_INDIVIDUAL)
                                  tipoC=0;
                                %>

                          <%String par = +dip.getIdComp()+ "," + dip.getIdPar() +"," + tipoC;%>

                           <span class="divs">

                                <a href="javascript:FrameApuesta(<%out.print(par+"," + 1 +","+dp.getDividendos().getDividendoLocal());%>)">
                                 <%  out.print(Float.toString(dp.getDividendos().getDividendoLocal())); %>
                                </a>
                                <%out.print("-");%>
                                 <a href="javascript:FrameApuesta(<%out.print(par+"," + 2 +"," + dp.getDividendos().getDividendoEmpate());%>)">
                                  <%out.print(Float.toString(dp.getDividendos().getDividendoEmpate()));%>
                        </a>
                        <%out.print("-");%>
                        <a href="javascript:FrameApuesta(<%out.print(par+"," + 3 + ","+dp.getDividendos().getDividendoVisita());%>)">
                            <%out.print(Float.toString(dp.getDividendos().getDividendoVisita())); %>
                        </a>
                          </span><br><br>
                          <span class="info">
                              <% out.print(dip.getFechaHora().getStr()); out.print(" ");%><br>
                              <%out.print(dip.getLugar());%><br>
                              </span>


                            <% if(dp.isEstaFinalizado()) {%>

                 </div>



        <div class="jugadores">

            <div class="jugsLocal">
                <label class="t" > Jugadores del equipo local</label><br>

                <%List<DataIdNombre> jugadoresLocal = (List)request.getAttribute("jugsLocal");

                for (DataIdNombre j: jugadoresLocal) {
            %>
                     <label class="nombreJugador">
                         <a href="/jugador?idJug=<%= j.getId()%>"><%= j.getNombre() %></a>
                     </label>
                    <div class="evento">
                    <% java.util.List<DataEvento> eventosJug = dp.getEventos();
                        if(eventosJug!=null && !eventosJug.isEmpty()){
                            for (DataEvento data: eventosJug) {
                                if (data.getJugador1().getId()==j.getId()){
                                    if (data.getTipoevento()==TipoEvento.GOL) {%>
                                        <div class="fotito"> <img src="../images/gol.png" alt="Gol!"></div>
                                    <%
                                    } else if (data.getTipoevento()==TipoEvento.SUSTITUCION) {%>
                                        <div class="fotito"> <img src="../images/flechaEntra.gif" alt="Sust!"></div>
                                    <%} else if (data.getTipoevento()==TipoEvento.TARJETA) {%>
                                        <% if (data.isAmarilla()) { %>
                                            <div class="fotito"> <img src="../images/tarjAmarilla.png" alt="TarjAmarilla!"></div>
                                        <% }else {%>
                                            <div class="fotito"> <img src="../images/tarjRoja.png" alt="TarjRoja!"></div>
                                     <%    }
                                    }
                                } else  if (data.getTipoevento()==TipoEvento.SUSTITUCION) {

                                    if (data.getJugador2().getId()==j.getId()){ %>
                                        <div class="fotito"> <img src="../images/flechaSale.gif" alt="Sust!"></div>
                                    <%}

                                }
                            }
                        }
                     %>
                     </div>
                     <br>
                <%}%>
            </div>
            <div class="jugsVisit">
            <label class="t" >Jugadores del equipo visitante</label><br>
            <%
                List<DataIdNombre> jugadoresVisita = (List)request.getAttribute("jugsVisita");
                for (DataIdNombre j: jugadoresVisita) {
            %>
            <label class="nombreJugador">
                     <a href="/jugador?idJug=<%= j.getId()%>"><%= j.getNombre() %></a>
                        </label><div class="evento">
                    <% java.util.List<DataEvento> eventosJug = dp.getEventos();
                        if(eventosJug!=null && !eventosJug.isEmpty()){
                            for (DataEvento data: eventosJug) {
                                if (data.getJugador1().getId()==j.getId()){
                                    if (data.getTipoevento()==TipoEvento.GOL) {%>
                                        <div class="fotito"> <img src="../images/gol.png" alt="Gol!"></div>
                                    <%
                                    } else if (data.getTipoevento()==TipoEvento.SUSTITUCION) {%>
                                        <div class="fotito"> <img src="../images/flechaEntra.gif" alt="Sust!"></div>
                                    <%} else if (data.getTipoevento()==TipoEvento.TARJETA) {%>
                                        <% if (data.isAmarilla()) { %>
                                            <div class="fotito"> <img src="../images/tarjAmarilla.png" alt="TarjAmarilla!"></div>
                                        <% }else {%>
                                            <div class="fotito"> <img src="../images/tarjRoja.png" alt="TarjRoja!"></div>
                                     <%    }
                                    }
                                } else  if (data.getTipoevento()==TipoEvento.SUSTITUCION) {

                                    if (data.getJugador2().getId()==j.getId()){ %>
                                        <div class="fotito"> <img src="../images/flechaSale.gif" alt="Sust!"></div>
                                    <%}

                                }
                            }
                        }
                     %>
                     </div>
                     <br>

            <%}%>
            </div>
          </div>
                   <%--eventos --%>      
                    <%
                    java.util.List<DataEvento> eventos = dp.getEventos();
                    if(eventos!=null && !eventos.isEmpty()){                        
                    %>
                    <div class="eventos"> Eventos <br><br>
                    <div class="transbox">
                    <table class="ults">
                    <caption>Eventos</caption>
                    <tr>
                    <th>Tipo Evento</th>
                    <th>Periodo</th>
                    <th>Minuto</th>
                    <th>Id Jugador</th>
                    <th>Nombre Jugador</th>
                    <th>Id Sustituye</th>
                    <th>Nombre Sustituye</th>
                    </tr>

                    <%
                    for (DataEvento data: eventos) {                    
                    %>

                    <tr onMouseOver="this.bgColor = '#ffd2a6';" onMouseOut="this.bgColor = '#ffffff';" >
                    <td><%
                    if(data.getTipoevento()==TipoEvento.TARJETA){
                        if(data.isAmarilla()){
                            out.print("TARJETA_AMARILLA");
                        }
                       else{
                            out.print("TARJETA_ROJA");
                       }
                    }
                    else{
                        out.print(data.getTipoevento().toString());
                    }
                    %></td>
                    <td><% out.print(data.getPeriodo().toString()); %></td>
                    <td><% out.print(data.getMinuto()); %></td>
                    <td> <a href="/jugador?idJug=<%= data.getJugador1().getId()%>"><% out.print(data.getJugador1().getId());%></a></td>
                    <td><% out.print(data.getJugador1().getNombre()); %></td>

                    <td><%					
                        if(data.getTipoevento()==TipoEvento.SUSTITUCION){%>
                                <a href="/jugador?idJug=<%= data.getJugador2().getId()%>"><% out.print(data.getJugador2().getId());%></a>
                        <% }
                        else{
                                out.print("NO_APLICA");
                        }					
                    %></td>
                    <td><%					
                        if(data.getTipoevento()==TipoEvento.SUSTITUCION){
                                out.print(data.getJugador2().getNombre());
                        }
                        else{
                                out.print("NO_APLICA");
                        }					
                    %></td>
                    </tr>
					<% } %>
                    </table>
                    </div>
                    
                    </div>
                    <%}%>
                    <br>
                    
                    <%-- --%>	
            
            
            
         <%}%>






        <div style="display: none" id="sesion">
            <% HttpSession s = request.getSession();
                   if ((s.getAttribute("estado_sesion") == null) | (s.getAttribute("estado_sesion") == DataTypes.EstadoSesion.LOGIN_INCORRECTO) ) {
                       out.print(-1.0);
                    }else{
                        WebServices.UsuarioWS.DataUsuario dataU = (WebServices.UsuarioWS.DataUsuario) s.getAttribute("usuario");
                        out.print(dataU.getSaldo());
                    }
                   %>
        </div>

        <div style="display: none" id="idSelDiv">-1</div>

        <div style="display: none" id="iddp">
            <% if (dp.isEstaFinalizado())
                    out.print(-1);
               else{
                    out.print(0);
               }
                %>
        </div>

    </body>
</html>