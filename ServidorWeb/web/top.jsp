<%-- 
    Document   : top
    Created on : 03/09/2011, 03:34:37 PM
    Author     : Santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<link rel="stylesheet" type="text/css" media="screen" href="css/menu.css" />
        <title></title>
 <style type="text/css">

     body {background-color:#000000; background-image:url(images/logo.png); background-repeat:no-repeat;
                font-family:Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif;
                font-size:12px;

                color:#000000;
                font-weight:bold;

            }

            div.transbox
                  {
                  margin:0 auto;
                  
                  background-color:#aaaaaa;
                  border:1px solid black;
                  opacity:0.8;
                  filter:alpha(opacity=80);
                   display:  inline-table;    /* For IE8 and earlier */
                  }
                  .todo{
                     display: inline-block;
                     width: 100%;
                     text-align:  center;
                     
                    }

           .menu2{

               text-align: center;
               margin-top: 75px;
               
              }

             .imagen{
                 display: block;
                 float: right;
                 
                 
              }

              .menu{
                  text-align:  center;

                }

                .t{
                    
                    display: block;
                    float: left;
                    margin-left: 40%;
                    height: 150px;
                    
                    }
 </style>
    </head>
    
<body >

    <div class="todo">
        <div class="t">
<div class="transbox"> 
        <% //HttpSession s = request.getSession();
    out.print("Bienvenido ");
//si me recargaron entonces, hago para que no me recargen de vuelta hasta que se inicie o cierre la sesion
    
    if (session.getAttribute("recargar") == "1"){
        session.setAttribute("recargar",null);
    }


if ((session.getAttribute("estado_sesion") == null) | (session.getAttribute("estado_sesion") == DataTypes.EstadoSesion.LOGIN_INCORRECTO) ) {
    out.print("anonimo"); }
else{
    //hay que cargar siempre el usuario del controlador
    //Interface.IControladorUsuarios icU = (Interface.IControladorUsuarios)s.getAttribute("ControladorUsuario");
    //DataTypes.DataUsuario dataU=null;
    //try{
     //dataU = icU.verPerfilUsuarioLogueado();
    //}catch(Exception e){}

    WebServices.UsuarioWS.DataUsuario dataU=(WebServices.UsuarioWS.DataUsuario)session.getAttribute("usuario");

    out.print(dataU.getNombre());
    out.print(" | ");
    out.print(dataU.getNick());
    out.print("@iBet.com");
    %>
        <a href="/CerrarS" target=rightFrame >Cerrar Sesion </a> <br>
        <%
      out.print("Saldo Disponible: ");
      out.print(dataU.getSaldo());
}%>
      </div>



      <div class ="menu2">
        <ul class="menu">
        <%if (!((session.getAttribute("estado_sesion") == null) | (session.getAttribute("estado_sesion") == DataTypes.EstadoSesion.LOGIN_INCORRECTO) )){
                        %>
        <li><a href="/infoUsuario"  target="mainFrame"><span>Perfil</span></a></li>
        <li><a href="/pencas"  target="mainFrame"><span>Pencas</span></a></li>
        <%}%>
        <li><a id="1" href="/ultimosPart" target="mainFrame"><span>Inicio</span></a></li>
        <li><a id="2" href="/detPart" target="mainFrame"><span>Partidos</span></a></li>
        <li><a id="3" href="/competiciones" target="mainFrame"><span>Competiciones</span></a></li>
        <li><a> <span> 
          <%    WebServices.MostrarFecha1.MostrarFecha1Service fechaServ =
                  new WebServices.MostrarFecha1.MostrarFecha1Service(new java.net.URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/mostrarFecha1?wsdl"),
                    new javax.xml.namespace.QName("http://WebServices/", "MostrarFecha1Service"));
                WebServices.MostrarFecha1.MostrarFecha1 fechaWS = fechaServ.getMostrarFecha1Port();
                WebServices.MostrarFecha1.DataFechaHora fecha = fechaWS.getFecha();
                out.print(fecha.getStr());
      %>
          </span></a></li>
      </ul>
      </div>

        </div>
          <div class="imagen">
              <img src ="../images/topderecha.png" alt="logo">
          </div>
</div>
</body>
</html>
