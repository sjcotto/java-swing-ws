
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="WebServices.UsuarioWS.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Confirmar Apuesta</title>
 <style type="text/css">

        body {background-color:#000000; background-image:url(images/fondomain.png); background-repeat:no-repeat;

                font-weight:bold;
                text-align:center;

                font-family:Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif;
                }

                  div.transbox
                  {
                  margin:0 auto;
                  width:600px;

                  background:#CCCCCC;
                  border:1px solid black;
                  opacity:0.8;
                  filter:alpha(opacity=60); /* For IE8 and earlier */
                  }

                .link{
                    clear:both;
                    margin-left:150px;
                    width:125px;
                    height:31px;
                    background:#666666;
                    text-align:center;
                    line-height:31px;
                    color:#FFFFFF;
                    font-size:11px;
                    font-weight:bold;
                }

                #stylized{
                /*background:#CCCCCC;*/
                }

                #stylized h1 {
                    font-size:14px;
                    font-weight:bold;
                    margin-bottom:8px;
                }

                button{
                clear:both;
                margin-left:150px;
                width:125px;
                height:31px;
                background:#666666;
                text-align:center;
                line-height:31px;
                color:#FFFFFF;
                font-size:11px;
                font-weight:bold;
                border:0;
                margin:0;
                padding:0;
                }

</style>

  <script language="JavaScript" type="text/JavaScript">
        function confirmar(){
            location.href = "Apostar?confirmar=1&paquete=0";
        }
        function confirmarPaquete(){
            parent.frames[2].window.location.href = "Apostar?confirmar=1&paquete=1";
        }
        function cancelar(){
            history.back(1);
        }
</script>

    </head>
    <body>
        <div class="transbox">
        <h1>Confirmar apuesta</h1>

        <%
        
        javax.servlet.http.HttpSession s = request.getSession();

        UsuarioWS icU = (UsuarioWS)s.getAttribute("ControladorUsuario");

        String strPaquete = request.getParameter("paquete");
        boolean paquete = strPaquete.equals("1");

        DataApuestaWS da = null;
        if (!paquete)
            da = icU.mostrarApuesta(session.getId());
        else
            da = icU.mostrarApuestaPaquete(session.getId());

         //request.setAttribute("confirmar", 1);
         //request.setAttribute("paquete", strPaquete);
        %>


            <div class="casillero">Monto: <%out.print(da.getMonto());%></div> <br>
            <div class="casillero">Dividendo: <%out.print(da.getDividendo());;%></div> <br>
            <div class="casillero">Saldo Nuevo: <%out.print(da.getSaldoNuevo());%></div> <br>
            <div class="casillero">Beneficio: <%out.print(da.getBeneficio());%></div> <br>

            <%  if (strPaquete.equals("0")){%>
                <button onclick="confirmar()">
                    Confirmar Apuesta
                </button>
            <%  }else{
            %>
                <button onclick="confirmarPaquete()">
                    Agregar al Paquete
                </button>
            <%  }%>

            <button onclick="cancelar()">Cancelar</button>


        </div>

    </body>
</html>
