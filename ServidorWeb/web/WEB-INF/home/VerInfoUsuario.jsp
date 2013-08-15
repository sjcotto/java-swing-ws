

<%@page import="WebServices.CompeticionesWS.CompeticionesWS"%>
<%@page import="WebServices.CompeticionesWS.CompeticionesWSService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import ="org.jfree.chart.*" %>
<%@page import="WebServices.UsuarioWS.*" %>
<%@page import="org.jfree.data.xy.*"%>
<%@page import="org.jfree.data.general.DefaultPieDataset"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Info Usuario</title>

        <style type="text/css">

            body {background-color:#000000; background-image:url(images/fondomain.png); background-repeat:no-repeat;
                font-family:Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif;
                font-size:12px;
                
                color:#000000;
                /* font-weight:bold;

                margin-bottom:20px;
                border-bottom:solid 1px #b7ddf2;
                padding-bottom:10px;*/
            }

            div.transbox
                  {
                  margin-left: 170px;
                  width:660px;
                  border:1px solid black;
                 background-color:#ffffff;
                border:1px solid black;
                opacity:0.6;
                filter:alpha(opacity=60);
                font-size:  15px;
                  
                  }


                p, h1, form, button{border:0; margin:0; padding:0;}
                .spacer{clear:both; height:1px;}
                /* ���� My Form ���� */
                .myform{
                width:400px;
                text-align:  center;
                margin:  auto;
                
                /*padding:14px;*/
                }

                #stylized{
                
                }
                h1{
                   text-align:  center;
                   font-size: 35px;
                }
                #stylized h1 {
                font-size:14px;
                font-weight:bold;
                margin-bottom:8px;
                color:#ffffff;
                
                }

                #stylized label{
                display:block;
                /*font-weight:bold;*/
                text-align:right;
                width:140px;
                float:left;
                }
                #stylized .small{
                color:#666666;
                display:block;
                font-size:11px;
                font-weight:normal;
                text-align:right;
                width:140px;
                }
                #stylized input{
                float:left;
                font-size:12px;
                padding:4px 2px;
                border:solid 1px #aacfe4;
                width:200px;
                margin:2px 0 20px 10px;
                }
                #stylized button{
                clear:both;
                width:125px;
                height:31px;
                background:#666666;
                text-align:center;
                line-height:31px;
                color:#FFFFFF;
                font-size:11px;
                font-weight:bold;
                margin:  auto;
                margin-left: 10px;
                margin-bottom: 10px;
                }

                .tits{
                  margin-left: 50px;
                  font-size: 20px;
                  }
                  .f{
                     
                     display:  inline-block;
                  }

                  .f label{
                      font-size: 20px;
                }
                  
                 .p{
                     margin-left: 50px;
                     display:  inline-block;
                  }

                  .p label{
                      font-size: 10px;
                }

            .tabla {
                font-size: 10px;
                margin-top: 23px;
                margin-bottom: 40px;
                background-color:#000000;
                margin-left: auto;
                margin-right: auto;
                width: 200px;
            }

            .tabla td, .tabla th  {
                font-family:sans-serif;
                padding:3px 3px 3px 3px;
                text-align: center;
                background-color:#FFFFFF;
                border-bottom-style: solid;
                border-bottom-width: 1px;
            }

            .tabla th {
                font-size:100%;
                padding-top:5px;
                padding-bottom:4px;
                color: #000;
                border-bottom-style: solid;
                border-bottom-width: 2px;
            }
        </style>

        <script language="JavaScript" type="text/JavaScript">
            
            function inicializar(){

                var iframe = parent.frames[0];
                iframe.window.location.reload();

                var i=0;
                document.forms[1].comboAnioH.clear;
                document.forms[1].comboAnioD.clear;
                document.forms[1].comboAnioD.length = 101;
                document.forms[1].comboAnioH.length = 101;
                document.forms[1].comboAnioD[0].text = "Año";
                document.forms[1].comboAnioH[0].text = "Año";
                for (var i=1; i < (document.forms[1].comboAnioD.length); i++){
                        document.forms[1].comboAnioD[i].text = 1930+ i;
                        document.forms[1].comboAnioD[i].value = 1930+ i;
                        document.forms[1].comboAnioH[i].text = 1930+ i;
                        document.forms[1].comboAnioH[i].value = 1930+ i;
                }
                document.forms[1].comboAnioD[0].selected = true;
                document.forms[1].comboAnioH[0].selected = true;

                var i=0;
                document.forms[1].comboMesH.clear;
                document.forms[1].comboMesD.clear;
                document.forms[1].comboMesD.length = 13;
                document.forms[1].comboMesH.length = 13;
                document.forms[1].comboMesD[0].text = "Mes";
                document.forms[1].comboMesH[0].text = "Mes";
                for (var i=1; i < (document.forms[1].comboMesD.length); i++){
                        document.forms[1].comboMesD[i].text = i;
                        document.forms[1].comboMesD[i].value = i;
                        document.forms[1].comboMesH[i].text = i;
                        document.forms[1].comboMesH[i].value = i;
                }
                document.forms[1].comboMesD[0].selected = true;
                document.forms[1].comboMesH[0].selected = true;

                var i=0;
                document.forms[1].comboDiaH.clear;
                document.forms[1].comboDiaD.clear;
                document.forms[1].comboDiaD.length = 32;
                document.forms[1].comboDiaH.length = 32;
                document.forms[1].comboDiaD[0].text = "Dia";
                document.forms[1].comboDiaH[0].text = "Dia";
                for (var i=1; i < (document.forms[1].comboDiaD.length); i++){
                        document.forms[1].comboDiaD[i].text = i;
                        document.forms[1].comboDiaD[i].value = i;
                        document.forms[1].comboDiaH[i].text = i;
                        document.forms[1].comboDiaH[i].value = i;
                }
                document.forms[1].comboDiaD[0].selected = true;
                document.forms[1].comboDiaH[0].selected = true;
        }
        function validar(){

              var error="";
                if(document.forms[1].comboAnioD.selectedIndex==0){
                    if(document.forms[1].comboMesD.selectedIndex!=0 || document.forms[1].comboDiaD.selectedIndex!=0){
                           error = error+ "Fecha Desde Invalida\n";
                    }
           }
           else if (document.forms[1].comboMesD.selectedIndex==0){
                   if(document.forms[1].comboAnioD.selectedIndex!=0 || document.forms[1].comboDiaD.selectedIndex!=0){
                            error = error+ "Fecha Desde Invalida\n";
                    }
           }
           else if (document.forms[1].comboDiaD.selectedIndex==0){
               if(document.forms[1].comboAnioD.selectedIndex!=0 || document.forms[1].comboMesD.selectedIndex!=0){
                    error = error+ "Fecha Desde Invalida\n";
                }
          }

          if(document.forms[1].comboAnioH.selectedIndex==0){
                    if(document.forms[1].comboMesH.selectedIndex!=0 || document.forms[1].comboDiaH.selectedIndex!=0){
                           error = error+ "Fecha Hasta Invalida\n";
                    }
           }
           else if (document.forms[1].comboMesH.selectedIndex==0){
                   if(document.forms[1].comboAnioH.selectedIndex!=0 || document.forms[1].comboDiaH.selectedIndex!=0){
                            error = error+ "Fecha Hasta Invalida\n";
                    }
           }
           else if (document.forms[1].comboDiaH.selectedIndex==0){
               if(document.forms[1].comboAnioH.selectedIndex!=0 || document.forms[1].comboMesH.selectedIndex!=0){
                    error = error+ "Fecha Hasta Invalida\n";
                }
          }
          if (error==""){
              if (document.forms[1].comboAnioH.selectedIndex < document.forms[1].comboAnioD.selectedIndex){
                     error= error + "Error al Ingresar Fechas: Fecha Hasta < Fecha Desde\n";
               }
              else if (document.forms[1].comboAnioH.selectedIndex == document.forms[1].comboAnioD.selectedIndex) {
                    if (document.forms[1].comboMesH.selectedIndex < document.forms[1].comboMesD.selectedIndex) {
                           error= error + "Error al Ingresar Fechas: Fecha Hasta < Fecha Desde\n";
                    }
                    else if (document.forms[1].comboMesH.selectedIndex == document.forms[1].comboMesD.selectedIndex){
                           if (document.forms[1].comboDiaH.selectedIndex < document.forms[1].comboDiaD.selectedIndex){
                                 error= error + "Error al Ingresar Fechas: Fecha Hasta < Fecha Desde\n";
                           }
                    }
                }
          }
          if (error!=""){
             alert(error);
             inicializar();
             div = document.getElementById("apuestas");
             if (div!=null){
                div.style.display="none";
             }
             return false;
          }
           return true;
        }

       function validarSaldo(saldoActual){
           var saldo = document.getElementById("s").value;
           var em = document.getElementById("m").value;
           
          if(em==""){
              if (saldo!=""){                  
                  var sal = parseFloat(saldo);
                  if(isNaN(sal)){
                     alert("Monto invalido");
                     return false;
                  }
                  else{
                      if(sal<=0){
                          alert("Monto debe ser mayor a 0");
                          return false;
                      }
                  }
               }
               else{
                  alert("Monto invalido");
                  return false;
               }    
          }
          else{
              if(em.toString().indexOf("@")==-1 || em.toString().indexOf(".")==-1){
                   alert("Email invalido");
                   return false;
               }
               else{
                   if(em.toString().indexOf("@")>em.toString().indexOf(".")){
                      alert("Email invalido");
                      return false;
                   }
                   else{
                       if (saldo!=""){
                          var sal = parseFloat(saldo);
                          if(isNaN(sal)){
                             alert("Monto invalido");
                             return false;
                          }
                          else{
                              if(saldoActual<sal){
                                  alert("Su saldo no es suficiente");
                                  return false;
                              }
                              else{
                                  if(sal<=0){
                                      alert("Monto debe ser mayor a 0");
                                      return false;
                                  }
                              }
                          }
                       }
                       else{
                          alert("Monto invalido");
                          return false;
                       }  
                   }
               }
          }              
          return true;
      }      

      
</script>

 
    </head>
 
    <body onload="inicializar()">
        <div class="transbox">
            
        <h1>Informacion de Perfil</h1><br>

        <% //HttpSession s = request.getSession();
        if ((session.getAttribute("estado_sesion") == null) || (session.getAttribute("estado_sesion") == DataTypes.EstadoSesion.LOGIN_INCORRECTO) ){
            out.print("Debe iniciar sesion");
        }else{
            //obtenemos la informacion del usuario y la imprimimos en pantalla
            WebServices.UsuarioWS.UsuarioWS usuProxy = (WebServices.UsuarioWS.UsuarioWS)session.getAttribute("ControladorUsuario");
            WebServices.UsuarioWS.DataUsuario dataU = null;
            try {
                dataU = usuProxy.verPerfilUsuarioLogueado(session.getId());
            }
            catch ( WebServices.UsuarioWS.Exception_Exception exc) {

            }
            request.getSession().setAttribute("usuario",dataU);
            
           %>
           <span class="tits">
                Nombre :
           </span>
                 <%= dataU.getNombre() %> <br>
                 <span class="tits">Mail : 
                     </span>
                           <%= dataU.getEmail() %><br>
           <span class="tits">
                Dir :
           </span>
                     <%= dataU.getDir() %><br>
            <span class="tits">
           CI:
            </span>
                     <%= dataU.getCI() %><br>
           <span class="tits">
               Nick :
           </span>
                     <%= dataU.getNick() %><br>
             <span class="tits">
                 Sexo:
             </span>
                     <%= dataU.getSexo().toString() %><br>
            <span class="tits">
                Pais:
            </span><%= dataU.getPais() %><br>
             <span class="tits">Saldo actual:</span><%= dataU.getSaldo() %><br>

              
           
           <%if(dataU.isNotApuesta()){ %>
              <span class="tits"></span>Notificar apuestas<br>
           <%}%>   
           <%if(dataU.isNotForo()){ %>
              <span class="tits"></span>Notificar foro<br>
           <%}%>     
           <%if(dataU.isNotPartido()){ %>
              <span class="tits"> </span>Notificar partidos<br>
           <%}%>  
           <br><br>

	    
	  <a href="../../DispositivoMovil.tar.gz">Descargar Aplicacion Movil!!</a>

           <br>
            
            <%if(null!=request.getAttribute("clave")){%>
            <script>
               window.open('https://sandbox.paypal.com/cgi-bin/webscr?cmd=_ap-payment&paykey=<%=request.getAttribute("clave")%>','','resizable=yes,scrollbars=yes,height=500,width=800');
            </script>
            <%}%> 
             
              <%--
            <div id=stylized class=myform>
            <form>
            <input type="button" value="Pagar" id="p"             
            onclick="pagate(p)">
            </form>
            </div> 

             <%}else{%> 
               <%}%>
            --%> 
            
           <div id=stylized class=myform>
           <form  onSubmit="return validarSaldo(<%= dataU.getSaldo() %>);" method="Post" action="/pagos">
                <div class="p">
               <label> Monto</label><br><input id="s" type="text" name="Saldo">
               <label>Email (retirar fondos):</label><br><input id="m" type="text" name="email" value="">
               </div>
               <button type="submit">Aceptar</button>
           </form>
           </div>
           
           
            
                       
           <br>
                <div id=stylized class=myform>
                <form onsubmit="return validar();" method="Post" action="/VerAp" >
                <div class="f">

                Desde:
                <select name="comboDiaD"></select>
                <select name="comboMesD"></select>
                <select name="comboAnioD"></select><br><br>
                Hasta :
                <select name="comboDiaH"></select>
                <select name="comboMesH"></select>
                <select name="comboAnioH"></select>
                </div><br><br>

                <button type="submit">Ver Apuestas</button>
                </form>
                <form action ="PDF">
                <button onclick="submit">Descargar</button>
                </form>
                <form method="Post" action="/verEstadisticas" >
                <button type="submit">Ver Estadisticas</button>
                </form>
                    
                   
                    
                </div>
           <div id=stylized class=myform>  
                    <form method="Post" action="/notificaciones" >
                        <label>notificar apuestas<input type="checkbox" name="notA" value="si"></label> 
                        <label>notificar foros<input type="checkbox" name="notF" value="si"></label> 
                        <label>notificar partidos<input type="checkbox" name="notP" value="si"></label> 
                        <button type="submit">Notificaciones</button> 
                        
                    </form>
           </div>
           
           
            <%
              List<DataApuestaWS> list_da = (List<DataApuestaWS>)request.getAttribute("listaApuestas");

              if (list_da != null){

                if (list_da.size()==0){
                    out.print("No ha realizado apuestas aun");
                }
            %>
           <div class="tablaApuestas">
                <table border="0" cellpadding="0" cellspacing="0" class="tabla">
                    <tr class="alt">
                        <th>Fecha</th> <th>Tipo</th> <th>Paquete</th><th>Campeonato</th> <th>Equipo</th><th>Jugador</th> <th>Local</th>
                        <th>Visitante</th> <th>Resultado</th> <th>Estado</th> <th>Beneficio</th> <th>Monto</th> <th>Dividendo</th>
                    </tr>
                    <%  for (DataApuestaWS da: list_da){ %>
                    <tr class="alt">
                        <td><%=da.getFecha().getStr() %></td>
                        <td>
                        <%  if (da.getTipo()==0) {
                                out.print("Campeon");
                            } else if (da.getTipo()==1) {
                                out.print("Resultado Exacto");
                            } else if (da.getTipo()==2) {
                                out.print("Goleador");
                            } else if (da.getTipo()==3) {
                                out.print("Partido");
                            }
                        %>
                        </td>
                        <td>
                        <%  if (da.getPertenecePaquete()!=-1)
                                out.print(da.getPertenecePaquete());
                            else
                                out.print("No");
                        %>
                        </td>
                        <td>
                        <%  if (da.getTipo()==0 || da.getTipo()==2)
                                out.print(da.getCompeticion().getNombre());
                            else
                                out.print(da.getPartido().getDataInfoPart().getNomComp());
                        %>
                        </td>
                        <td>
                        <%  if (da.getTipo()==0) {
                                out.print(da.getEquipo().getNombre());
                            }
                        %>
                        </td>
                        <td>
                        <%  if (da.getTipo()==2) {
                                out.print(da.getJugador().getNombre());
                            }
                        %>
                        </td>
                        <td>
                        <%  if (da.getTipo()==1 || da.getTipo()==3)
                                out.print(da.getPartido().getDataInfoPart().getNomLocal());
                        %>
                        </td>
                        <td>
                        <%  if (da.getTipo()==1 || da.getTipo()==3)
                                out.print(da.getPartido().getDataInfoPart().getNomVisita());
                        %>
                        </td>
                        <td> <%  if (da.getTipo()==3) {
                                    if (da.getTipoResultado()==TipoDividendo.LOCAL)
                                        out.print("Local");
                                    else if (da.getTipoResultado()==TipoDividendo.EMPATE)
                                        out.print("Empate");
                                    else
                                        out.print("Visitante");
                                 } else if (da.getTipo()==1) {
                                    out.print(da.getGolesL()+"-"+da.getGolesV());
                                 }
                        %>
                        </td>
                        <td>
                        <%  if (da.getEstadoApuesta()==EstadoApuesta.GANO) {
                                out.print("Gano");
                            } else if (da.getEstadoApuesta()==EstadoApuesta.PERDIO) {
                                out.print("Perdio");
                            } else if (da.getEstadoApuesta()==EstadoApuesta.PENDIENTE) {
                                out.print("Pendiente");
                            }
                        %>
                        </td>
                        <td><%=da.getBeneficio()%></td>
                        <td><%=da.getMonto()%></td>
                        <td><%=da.getDividendo()%></td>
                    </tr>
                    <% } %>
                </table>
           </div>
           <%
                
           } //fin de la parte de ver apuestas, que lista todas las apuestas segun el filtro correspondiente

            //vemos la parte de las graficas de estadisticas
            List<DataApuestaWS> listA = (List<DataApuestaWS>)request.getAttribute("ApuestasE");
               
            if (listA!=null){
                float Mgano=0;
                float Mperdio=0;
                float MEsperado=0;
                float m1=0;float m2=0;float m3=0;float m4=0;float m5=0;
                float m6=0;float m7=0;float m8=0;float m9=0;float m10=0;float m11=0;float m12=0;

                //obtenemos la fehca actual
                WebServices.MostrarFecha1.MostrarFecha1Service fechaServ =
                        new WebServices.MostrarFecha1.MostrarFecha1Service(new java.net.URL("http://"+(String)request.getSession().getAttribute("DIR")+":8280/mostrarFecha1?wsdl"),
                    new javax.xml.namespace.QName("http://WebServices/", "MostrarFecha1Service"));
                WebServices.MostrarFecha1.MostrarFecha1 fechaWS = fechaServ.getMostrarFecha1Port();
                WebServices.MostrarFecha1.DataFechaHora ff = fechaWS.getFecha();


                DataFechaHora fecha12 = new DataFechaHora();

                fecha12.setAnio(ff.getAnio());
                fecha12.setMes(ff.getMes());

                int mesActual = fecha12.getMes();
                int anioActual = fecha12.getAnio();

                DataFechaHora fecha11 = new DataFechaHora();fecha11.setMes(mesActual-1);
                DataFechaHora fecha10 = new DataFechaHora();fecha10.setMes(mesActual-2);
                DataFechaHora fecha9 = new DataFechaHora();fecha9.setMes(mesActual-3);
                DataFechaHora fecha8 = new DataFechaHora();fecha8.setMes(mesActual-4);
                DataFechaHora fecha7 = new DataFechaHora();fecha7.setMes(mesActual-5);
                DataFechaHora fecha6 = new DataFechaHora();fecha6.setMes(mesActual-6);
                DataFechaHora fecha5 = new DataFechaHora();fecha5.setMes(mesActual-7);
                DataFechaHora fecha4 = new DataFechaHora();fecha4.setMes(mesActual-8);
                DataFechaHora fecha3 = new DataFechaHora();fecha3.setMes(mesActual-9);
                DataFechaHora fecha2 = new DataFechaHora();fecha2.setMes(mesActual-10);
                DataFechaHora fecha1 = new DataFechaHora();fecha1.setMes(mesActual-11);

		int anio = anioActual -1;

                if (fecha11.getMes()<=0){
                    fecha11.setAnio(anio);
                    fecha11.setMes(12+fecha11.getMes());
                    }
                else
                    fecha11.setAnio(anioActual);

                if (fecha10.getMes()<=0){
                    fecha10.setAnio(anio);
                    fecha10.setMes(12+fecha10.getMes());
                }
                else
                    fecha10.setAnio(anioActual);

                if (fecha9.getMes()<=0){
                    fecha9.setAnio(anio);
                    fecha9.setMes(12+fecha9.getMes());
                }
                else
                    fecha9.setAnio(anioActual);

                if (fecha8.getMes()<=0){
                    fecha8.setAnio(anio);
                    fecha8.setMes(12+fecha8.getMes());
                }
                else
                    fecha8.setAnio(anioActual);

                if (fecha7.getMes()<=0){
                    fecha7.setAnio(anio);
                    fecha7.setMes(12+fecha7.getMes());
                }
                else
                    fecha7.setAnio(anioActual);

                if (fecha6.getMes()<=0){
                    fecha6.setAnio(anio);
                    fecha6.setMes(12+fecha6.getMes());
                }
                else
                    fecha6.setAnio(anioActual);
                
                if (fecha5.getMes()<=0){
                    fecha5.setAnio(anio);
                    fecha5.setMes(12+fecha5.getMes());
                }
                else
                    fecha5.setAnio(anioActual);
                
                if (fecha4.getMes()<=0){
                    fecha4.setAnio(anio);
                    fecha4.setMes(12+fecha4.getMes());
                }
                else
                    fecha4.setAnio(anioActual);
                
                if (fecha3.getMes()<=0){
                    fecha3.setAnio(anio);
                    fecha3.setMes(12+fecha3.getMes());
                }
                else
                    fecha3.setAnio(anioActual);
                
                if (fecha2.getMes()<=0){
                    fecha2.setAnio(anio);
                    fecha2.setMes(12+fecha2.getMes());
                }
                else
                    fecha2.setAnio(anioActual);
                
                if (fecha1.getMes()<=0){
                    
                    fecha1.setAnio(anio);
                    fecha1.setMes(12+fecha1.getMes());
                }
                else
                    fecha1.setAnio(anioActual);
                //tenemos que obtener la fecha para cada mes, para atras..


                //no esta terminado
                for (DataApuestaWS da: listA){
                    if (da.getEstadoApuesta()==EstadoApuesta.GANO) {
                        Mgano= Mgano + da.getMonto();

                        if ((da.getFecha().getMes()==fecha1.getMes()) & (da.getFecha().getAnio()==fecha1.getAnio()))
                            m1= m1 + da.getMonto();
                        else if ((da.getFecha().getMes()==fecha2.getMes()) & (da.getFecha().getAnio()==fecha2.getAnio()))
                            m2= m2 + da.getMonto();
                        else if ((da.getFecha().getMes()==fecha3.getMes()) & (da.getFecha().getAnio()==fecha3.getAnio()))
                            m3= m3 + da.getMonto();
                        else if ((da.getFecha().getMes()==fecha4.getMes()) & (da.getFecha().getAnio()==fecha4.getAnio()))
                            m4= m4 + da.getMonto();
                        else if ((da.getFecha().getMes()==fecha5.getMes()) & (da.getFecha().getAnio()==fecha5.getAnio()))
                            m5= m5 + da.getMonto();
                        else if ((da.getFecha().getMes()==fecha6.getMes()) & (da.getFecha().getAnio()==fecha6.getAnio()))
                            m6= m6 + da.getMonto();
                        else if ((da.getFecha().getMes()==fecha7.getMes()) & (da.getFecha().getAnio()==fecha7.getAnio()))
                            m7= m7 + da.getMonto();
                        else if ((da.getFecha().getMes()==fecha8.getMes()) & (da.getFecha().getAnio()==fecha8.getAnio()))
                            m8= m8 + da.getMonto();
                        else if ((da.getFecha().getMes()==fecha9.getMes()) & (da.getFecha().getAnio()==fecha9.getAnio()))
                            m9= m9 + da.getMonto();
                        else if ((da.getFecha().getMes()==fecha10.getMes()) & (da.getFecha().getAnio()==fecha10.getAnio()))
                            m10= m10 + da.getMonto();
                        else if ((da.getFecha().getMes()==fecha11.getMes()) & (da.getFecha().getAnio()==fecha11.getAnio()))
                            m11= m11 + da.getMonto();
                        else if ((da.getFecha().getMes()==fecha12.getMes()) & (da.getFecha().getAnio()==fecha12.getAnio()))
                            m12= m12 + da.getMonto();

                    } else if (da.getEstadoApuesta()==EstadoApuesta.PERDIO) {
                        
                        Mperdio=Mperdio+da.getMonto();
                        //restamos los montos para la grafica
                        if ((da.getFecha().getMes()==fecha1.getMes()) & (da.getFecha().getAnio()==fecha1.getAnio()))
                            m1= m1 - da.getMonto();
                        else if ((da.getFecha().getMes()==fecha2.getMes()) & (da.getFecha().getAnio()==fecha2.getAnio()))
                            m2= m2 - da.getMonto();
                        else if ((da.getFecha().getMes()==fecha3.getMes()) & (da.getFecha().getAnio()==fecha3.getAnio()))
                            m3= m3 - da.getMonto();
                        else if ((da.getFecha().getMes()==fecha4.getMes()) & (da.getFecha().getAnio()==fecha4.getAnio()))
                            m4= m4 - da.getMonto();
                        else if ((da.getFecha().getMes()==fecha5.getMes()) & (da.getFecha().getAnio()==fecha5.getAnio()))
                            m5= m5 - da.getMonto();
                        else if ((da.getFecha().getMes()==fecha6.getMes()) & (da.getFecha().getAnio()==fecha6.getAnio()))
                            m6= m6 - da.getMonto();
                        else if ((da.getFecha().getMes()==fecha7.getMes()) & (da.getFecha().getAnio()==fecha7.getAnio()))
                            m7= m7 - da.getMonto();
                        else if ((da.getFecha().getMes()==fecha8.getMes()) & (da.getFecha().getAnio()==fecha8.getAnio()))
                            m8= m8 - da.getMonto();
                        else if ((da.getFecha().getMes()==fecha9.getMes()) & (da.getFecha().getAnio()==fecha9.getAnio()))
                            m9= m9 - da.getMonto();
                        else if ((da.getFecha().getMes()==fecha10.getMes()) & (da.getFecha().getAnio()==fecha10.getAnio()))
                            m10= m10 - da.getMonto();
                        else if ((da.getFecha().getMes()==fecha11.getMes()) & (da.getFecha().getAnio()==fecha11.getAnio()))
                            m11= m11 - da.getMonto();
                        else if ((da.getFecha().getMes()==fecha12.getMes()) & (da.getFecha().getAnio()==fecha12.getAnio()))
                            m12= m12 - da.getMonto();

                    } else if (da.getEstadoApuesta()==EstadoApuesta.PENDIENTE) {
                        MEsperado=MEsperado +da.getMonto();
                    } 
 
                }

                //pencas
                 
                 CompeticionesWSService serv = new CompeticionesWSService(new java.net.URL("http://"+(String)session.getAttribute("DIR")+":8280/competicionesWS?wsdl"),
                 new javax.xml.namespace.QName("http://WebServices/", "CompeticionesWSService"));
                 CompeticionesWS icw = serv.getCompeticionesWSPort();

                 WebServices.CompeticionesWS.ListBean pencas = icw.listarPencasDisponibles(session.getId());
                 List<WebServices.CompeticionesWS.DataIdNombre> penc = (List)pencas.getList();

                 usuProxy = (WebServices.UsuarioWS.UsuarioWS)session.getAttribute("ControladorUsuario");

                 for (WebServices.CompeticionesWS.DataIdNombre data : penc){
                     DataInfoPenca info = usuProxy.verTablaPosicionesPenca(session.getId(),data.getId());

                     if (info.isParticipaUsuario()){
                          if (info.isFinalizada()){
                                 int pts_g = info.getTablaPosiciones().get(0).getPuntos();
                                 //obtenemos cant de ganadores
                                 int cant=0;
                                 boolean gano = false;
                                 for (DataInfoUsuarioPenca diup : info.getTablaPosiciones()) {
                                   if(diup.getPuntos()==pts_g){
                                      cant++;
                                      if (diup.getNick().equals(usuProxy.obtenerUsuarioLogueado(session.getId()).getNick()))
                                           gano=true;
                                    }
                                 }
                              if (gano)
                                 Mgano= (Mgano + info.getPozo()/cant)-info.getMontoMin();
                              else
                                 Mperdio=Mperdio+info.getMontoMin();
                            
                         }
                         else
                             MEsperado=MEsperado+info.getPozo();
                    }
               }

                DefaultPieDataset dataset = new DefaultPieDataset();

                dataset.setValue("Monto Ganado",new Float(Mgano));
                dataset.setValue("Monto Perdido",new Float(Mperdio));
                dataset.setValue("Monto Esperado",new Float(MEsperado));

                JFreeChart chart = ChartFactory.createPieChart("Ganancias", dataset, true, true, true);
		
		//System.out.println("directorio" +System.getProperty("user.dir") );  
                //java.io.File f = new java.io.File("ganancias.png");
                //String path = f.getPath();
                
		
		try{
		  org.jfree.chart.ChartUtilities.saveChartAsPNG(new java.io.File("../webapps/ROOT/ganancias.png"), chart, 500, 300);  
		}catch(java.lang.Exception eee){

		  }
		try{
		  org.jfree.chart.ChartUtilities.saveChartAsPNG(new java.io.File("apache-tomcat-6.0.33/webapps/ROOT/ganancias.png"), chart, 500, 300);  
		}catch(java.lang.Exception eee){

		  }



                //ahora construimos la otra grafica, la de Evolución de Monedero
                XYSeries series = new XYSeries("Evolucion del Monedero");
                
                series.add(1, m1);
                series.add(2, m2);
                series.add(3, m3);
                series.add(4, m4);
                series.add(5, m5);
                series.add(6, m6);
                series.add(7, m7);
                series.add(8, m8);
                series.add(9, m9);
                series.add(10, m10);
                series.add(11, m11);
                series.add(12, m12);
               
                org.jfree.data.xy.XYDataset juegoDatos= new org.jfree.data.xy.XYSeriesCollection(series);
                JFreeChart chart2 = ChartFactory.createXYLineChart        ("Monedero","Meses","Evolucion Monedero",juegoDatos,org.jfree.chart.plot.PlotOrientation.VERTICAL,true,true,true);
                
		
		try{  
		      org.jfree.chart.ChartUtilities.saveChartAsPNG(new java.io.File("apache-tomcat-6.0.33/webapps/ROOT/evolucion.png"), chart2, 500, 300);
		   }catch(java.lang.Exception e){}

		try{
		  org.jfree.chart.ChartUtilities.saveChartAsPNG(new java.io.File("../webapps/ROOT/evolucion.png"), chart2, 500, 300);
		}catch(java.lang.Exception ee){

		}
                
		%>               
                
                <img src="ganancias.png" alt="Ganancias"> <br>
                <img src="evolucion.png" alt="Evolucion del Monedero"> <br>
            <%
            }
            %>
            

        <% } %>
        </div>
    </body>
</html>
