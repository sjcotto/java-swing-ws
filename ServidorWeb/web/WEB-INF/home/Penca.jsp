<%-- 
    Document   : Penca
    Created on : 11-oct-2011, 21:17:01
    Author     : CAPablito
--%>
<%@page import= "WebServices.UsuarioWS.*"%>
<%@page import= "java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
  <style type="text/css">


        body {background-color:#000000; background-image:url(images/fondomain.png); background-repeat:no-repeat;
        font-family:Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif;
        }

        .todo{
            margin-top: 60px;
            background-color:#ffffff;
            border:1px solid black;
            opacity:0.6;
            filter:alpha(opacity=60);
            width: 700px;
            margin:  auto;
            text-align: center;
        }

        .total{
            display: inline-block;
            width: 680px;
            text-align:  center;
           }
     .tabla1{
          float: left;
           margin-left: 10px;
          display: inline-block;
          text-align:  center;

         }

       .parts{
           float: right;
          border-color:  #960e27;
          border-style:  ridge;
          display:  inline-block;
         }
         .tabla{
             margin:  auto;
              
            }
   .tabla td, .tabla th, .tabla caption  {
    font-size:100%;
    border:1px solid #000;
    padding:2px 2px 2px 2px;
    text-align: center;
    margin:  auto;
      }
      .tabla thead{
          color: #ffffff;
          background-color: firebrick;
          }

      .info{
          text-align:  center;
          float: inherit;
          display:  inline-block;
          border-color:  #ffffff;
          border-style:  ridge;
          color:#960e27;
      }
      #golLoc{
          width: 15px;
         }
       #golVis{
          width: 15px;
         }
       .pp{
          text-align: center;
           margin:  auto;
           border:1px solid #000;
           padding:2px 2px 2px 2px;
           font-size: 75%;
            width: 500px;
        }
        .pp thead{
            color: #ffffff;
          background-color: firebrick;
         }


     </style>

     <script language="JavaScript">
            function participar(){
                var saldo =  document.getElementById("sesion").innerHTML;
                var ingreso = document.getElementById("monto").innerHTML;
                var s = parseFloat(saldo);
               var i = parseFloat(ingreso);
                if (s>=i){

                    return true;
                }
                else{

                  alert("Saldo Insuficiente");
                   return false;
                }
            }

            function Apostar(){
               var gL =  document.getElementById("golLoc").value;
               var gV =  document.getElementById("golVis").value;
                
               if ((gL!="" && gV=="") || (gL=="" && gV!="")){
                   alert("Error al Ingresar Goles");
                   return false;
               }
               else if (gL!="" && gV!="") {
                   var l = parseInt(gL);
                   var v = parseInt(gV);
     
                   if (isNaN(l)|| isNaN(v)){
                       alert("Error al Ingresar Goles");
                       return false;
                   }
                   return true;
               }
              
            }

            function inicializar(){
                 var iframe = parent.frames[0];
                iframe.window.location.reload();
               }
          </script>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Penca</title>
    </head>
     <body onload="inicializar()">
    <body>
       <div class="todo">
        <h1><%out.print((String)request.getAttribute("nomComp"));%></h1>
        <% DataInfoPenca dip = (DataInfoPenca)request.getAttribute("infoPenca");%>
        <div class="total">
            <div class="tabla1">
                <%List<DataInfoUsuarioPenca> tabla =dip.getTablaPosiciones();%>
                <table class="tabla" border="0" cellpadding="0" cellspacing="0">
                    <caption> Tabla de Posiciones</caption>
                        <thead>
                        <tr>
                            <th> Usuario </th> <th> Puntos</th>
                        </tr>
                    </thead>
                    <tbody>
                       <%if (tabla!=null){
                           for (DataInfoUsuarioPenca datau: tabla) { %>
                       <tr>
                           <td> <%out.println(datau.getNick());%></td>
                           <td> <%out.println(datau.getPuntos());%></td>
                       </tr>
                       <%}}%>
                    </tbody>
                </table><br><br>
                     <% if (dip.isParticipaUsuario()){%>
                     <div class="pozo">
                         Pozo Actual : <%out.println(dip.getPozo());%><br>
                     </div>
                     <%}%>
            </div>
             <%if (!dip.isFinalizada()){

                  if(dip.isParticipaUsuario()){%>
                   <div id="p" class="parts">
                      <%List<DataInfoPartido> part= (List)request.getAttribute("partidos");%>
                      <table class="pp">
                        <thead>
                            <tr>
                                <th>Fecha del partido</th><th>Equipos</th><th>Pronostico</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%for (DataInfoPartido dipp: part){%>
                            <tr>
                                <td><%out.println(dipp.getFechaHora().getStr());%></td>
                                <td><%out.println(dipp.getNomLocal());%>-<%out.println(dipp.getNomVisita());%></td>
                                <td> <form  onsubmit= "return Apostar();" name="form2" method="post" action="ParticiparPenca">
                                        <input name="golesLoc" id ="golLoc" value=""> - <input name="golesVis" id ="golVis" value="">
                               
                                        <input id="idComp" name ="idComp" style="display: none" VALUE ="<%out.println((String)request.getAttribute("idComp"));%>">
                                        <input id="nomp" name ="idPart" style="display: none" VALUE ="<%out.println(Integer.toString(dipp.getIdPar()));%>">
                                        <input id="nomp" name ="nomComp" style="display: none" VALUE ="<%out.println((String)request.getAttribute("nomComp"));%>">
                                        <button id="b" onclick="submit">Apostar</button>
                                    </form></td>
                            </tr>
                            <%}%>
                        </tbody>
                    </table>
                </div>
                <%}
                  else {%>
                  <div id="i" class="info">
                      <br>
                      Monto Para Ingresar : <label id="monto"><%out.println(dip.getMontoMin());%></label><br>
                      <br><br>
                       <form  onsubmit="return participar();" name="form22" method="post" action="ParticiparPenca">
                           <input id="idComp" name ="idComp" style="display: none" VALUE ="<%out.println((String)request.getAttribute("idComp"));%>">
                           <input id="nomp" name ="nomComp" style="display: none" VALUE ="<%out.println((String)request.getAttribute("nomComp"));%>">
                           <button onclick="submit">Participar</button>
                       </form>
                  </div>
                <%}
                  }else {%>
                  <div class="estado">
                      Penca Finalizada
                  </div>
                  <%}%>
        </div>
        </div>
        <div style="display: none" id="sesion">
            <% HttpSession s = request.getSession();
                   if ((s.getAttribute("estado_sesion") == null) || (s.getAttribute("estado_sesion") == DataTypes.EstadoSesion.LOGIN_INCORRECTO) ) {
                       out.print(-1.0);
                    }else{
                        DataUsuario dataU = (DataUsuario) s.getAttribute("usuario");
                        out.print(dataU.getSaldo());
                    }
                   %>
        </div>
    </body>
</html>
