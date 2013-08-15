<%-- 
    Document   : right
    Created on : 03/09/2011, 09:03:43 PM
    Author     : Santiago
--%>

<%@page import="WebServices.UsuarioWS.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Frame derecha</title>

        <style type="text/css">

            body {background-color:#000000;/* background-image:url(images/derecha.png); background-repeat:no-repeat;*/}
              div.transbox
                  {
                  margin:0 auto;
                    width:200px;

                  background-color:#ffffff;
                  border:1px solid black;
                  opacity:0.6;
                  filter:alpha(opacity=60); /* For IE8 and earlier */
                  }

                body{
                font-family:Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif;
                font-size:10px;
                }
                p, h1, form, button{border:0; margin:0; padding:0;}
                .spacer{clear:both; height:1px;}
                /* ���� My Form ���� */
                .myform{
                margin:0 auto;
                width:200px;
                padding:12px;
                }
                /* ���� stylized ����   background:#ebf4fb;
                border:solid 2px #b7ddf2;

*/
                #stylized{
                background:#CCCCCC;
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
                font-size:11px;
                font-weight:normal;
                text-align:right;
                width:80px;
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

                .todoPaquete {
                    border: 1px solid black;
                    margin:0 auto;
                    display: inline-block;
                    background:#ebf4fb;
                    width:260px;
                    border-style: outset;
                    opacity:0.7;
                    text-align: center;
                    width:200px;
                    background-color:#ffffff;
                }


                th, td {
                    font-size: smaller;
                    border: 1px solid black;
                    padding:2px 2px 2px 2px;
                }

                th {
                    font-size: medium;
                }

                .rojo {
                    color: red;
                }


        </style>

    </head>
<script type="text/javascript">
        function actualizar(){
            //verificamos si hay que recargar algo o no.. dependiendo de la variable de sesion recargar
            //en el id sesin esta el valor -1 si la variable es null y 1 sino, si vale 1 tenemos que recargar los 2 frames
            //sino, no!!

            
            var r = document.getElementById("sesion").innerHTML;

            //alert(r);
            if (r==1){
                var iframe = parent.frames[0];
                iframe.window.location.reload();
                var iframe2 = parent.frames[1];
                iframe2.window.location.reload();
            }else if (r==2){
                var iframe = parent.frames[0];
                iframe.window.location.reload();

                var iframe2 = parent.frames[1];

                //alert("fasd");

                iframe2.window.location.href = "VerUltimosPartidos.jsp";

                //alert("fasd");
    //                iframe2.window.location.reload();
            } else if (r==3) {
                // El paquete es invalido hago un alert y actualizo
                // solamente este frame
                alert("El paquete es invalido");
            }else if(r==4) {
                // El paquete es valido actualizo el frame[1] con el /confirmarPaquete
                // y este con el /right
                var iframe = parent.frames[1];
                iframe.window.location.href="/confirmarPaquete";
            }

        }
        </script>

    <body
        onLoad = "actualizar()"
        >

        <%
        //HttpSession s = request.getSession();
        //if (!(s.getAttribute("estado_sesion") == null ) && (s.getAttribute("estado_sesion") == DataTypes.EstadoSesion.LOGIN_INCORRECTO)) {
          // %>

            <%//}
        //else if (s.getAttribute("estado_sesion") == DataTypes.EstadoSesion.LOGIN_CORRECTO){ %>
             
        <%// }else{ %>



<%
    HttpSession s = request.getSession();
    if ((s.getAttribute("estado_sesion") == null) || (s.getAttribute("estado_sesion") ==DataTypes.EstadoSesion.LOGIN_INCORRECTO) ){ %>

    <div class="transbox">
    <div id=stylized class=myform>
<form name="form1" method="post" action="InicioS">
<h1>Inicio de Sesion</h1>
<%
        s = request.getSession();
        if (!(s.getAttribute("estado_sesion") == null ) && (s.getAttribute("estado_sesion") == DataTypes.EstadoSesion.LOGIN_INCORRECTO)) {
%>          <p><strong><font color="#FF0000">Datos invalidos </font></strong></p>
        <%}else{%>
            <p>Ingrese sus Datos</p>
        <% }%>
<label>Nick
<span class=small id="userIdMessage" >ingresar nick</span>
</label>
<input type="text" name="nick"/>

<label>Clave
<span class=small>ingrese clave</span>
</label>
<input type="password" name="pass"/>

<button onclick="submit()" type=”submit”>Entrar</button>
<div class=spacer></div>

</form>


<A href="/registro" target="mainFrame">Registrate!</A>
</div>
    </div>
<% } %>

        <%
            s = request.getSession();
            if (s.getAttribute("estado_sesion") != null && s.getAttribute("estado_sesion") ==DataTypes.EstadoSesion.LOGIN_CORRECTO) {
                WebServices.UsuarioWS.UsuarioWS icU = (WebServices.UsuarioWS.UsuarioWS) session.getAttribute("ControladorUsuario");

                boolean paqueteActivo = true;
                WebServices.UsuarioWS.DataPaqueteApuestasWS dataPaq = null;

                try {
                    dataPaq = icU.mostrarPaqueteApuestas(session.getId());
                }
                catch (WebServices.UsuarioWS.ExPaqueteNoActivado_Exception exc) {
                    paqueteActivo = false;
                    exc.printStackTrace();
                }catch (WebServices.UsuarioWS.Exception_Exception e){
                    e.printStackTrace();
                }
               

                if (paqueteActivo) {%>
                <div class="todoPaquete">
                    <div>
                        <table border="0" cellpadding="0" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>Evento</th>
                                    <th>Monto</th>
                                    <th>Div.</th>
                                </tr>
                            </thead>
                            <tbody>
                            <%  java.util.List<DataApuestaWS> listDataAp = dataPaq.getApuestas();
                                for (DataApuestaWS dataAp: listDataAp) {
                                    if (dataAp.getTipo()==2) {
                                        
                            %>
                                        <tr>
                                            <td>
                                                <%  out.print(dataAp.getCompeticion().getNombre());
                                                    out.print("-");
                                                %>
                                                <div class="rojo"><%  out.print(dataAp.getJugador().getNombre());%> </div>
                                            </td>
                                    <%  } else if (dataAp.getTipo()==3) {
                                    %>
                                            <td>
                                                <%  out.print(dataAp.getPartido().getDataInfoPart().getNomComp());
                                                    out.print("-");
                                                %>
                                                <%  if(dataAp.getTipoResultado()==TipoDividendo.LOCAL){%>
                                                        <div class="rojo"><%  out.print(dataAp.getPartido().getDataInfoPart().getNomLocal());%></div>
                                                <%  }else{ %>
                                                        <div><%  out.print(dataAp.getPartido().getDataInfoPart().getNomLocal());%></div>
                                                <%  }%>
                                                <%  if(dataAp.getTipoResultado()==TipoDividendo.EMPATE){%>
                                                        <div class="rojo"><%  out.print("-X-");%> </div>
                                                <%  }else{ %>
                                                        <div><%  out.print("-X-");%> </div>
                                                <%  }%>
                                                <%  if(dataAp.getTipoResultado()==TipoDividendo.VISITANTE){%>
                                                        <div class="rojo"><%  out.print(dataAp.getPartido().getDataInfoPart().getNomVisita());%> </div>
                                                <%  }else{ %>
                                                        <div><%  out.print(dataAp.getPartido().getDataInfoPart().getNomVisita());%> </div>
                                                <%  }%>
                                            </td>

                                    <% } else if (dataAp.getTipo()==1) {
                                            
                                    %>
                                            <td>
                                                <%  out.print(dataAp.getPartido().getDataInfoPart().getNomComp());
                                                    out.print("-");
                                                %>
                                                    <div><%  out.print(dataAp.getPartido().getDataInfoPart().getNomLocal());%></div>
                                                    <div class="rojo"><%out.print(" ("+dataAp.getGolesL()+") ");%></div>
                                                    <div><%  out.print(dataAp.getPartido().getDataInfoPart().getNomVisita());%></div>
                                                    <div class="rojo"><%out.print(" ("+dataAp.getGolesV()+") ");%></div>
                                            </td>
                                    <%  } else if (dataAp.getTipo()==0) {
                                    %>
                                            <td>
                                                <%  out.print(dataAp.getCompeticion().getNombre());
                                                    out.print("-");
                                                %>
                                                    <div class="rojo"><%out.print(dataAp.getEquipo().getNombre());%></div>
                                            </td>

                                <%  }%>
                                    <td><%  out.print(dataAp.getMonto()); %></td>
                                    <td><%  out.print(dataAp.getDividendo()); %></td>
                                </tr>
                            <%  }%>
                            </tbody>
                        </table>
                        <div>
                            Beneficio: <% out.print(dataPaq.getMontoTotal()); %>
                        </div>
                        <button type="submit" onclick="location.href='/validarPaquete'"> Confirmar Paquete! </button>
                    </div>
                </div>
        <%  }
          }
        %>


<div style="display: none" id="sesion">
            <%
            s = request.getSession();
           if ((s.getAttribute("recargar") == null) || ((s.getAttribute("estado_sesion") != null) && (s.getAttribute("estado_sesion") ==DataTypes.EstadoSesion.LOGIN_INCORRECTO) ) ) {
                out.print(-1);
           }else if(s.getAttribute("recargar").equals("2")){
                out.print(2);
           }else if (s.getAttribute("recargar").equals("3")) {
               out.print(3);
           }else if (s.getAttribute("recargar").equals("4")) {
               out.print(4);
           }else{
               out.print(1);
           }
           %>
</div>

    </body>
</html>
