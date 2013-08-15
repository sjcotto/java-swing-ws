<%-- 
    Document   : filtroPartido
    Created on : Sep 14, 2011, 4:19:42 PM
    Author     : tprog082
--%>
<%@page import="WebServices.CompeticionesWS.*"%>
<%@page import= "java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title align="center" >Ver detalles Partido</title>
    </head>

        <script language="JavaScript" type="text/JavaScript">
	function inicializar(){
		var i=0;
		document.forms[0].comboAnio_ini.clear;
		document.forms[0].comboAnio_ini.length = 100;
                document.forms[0].comboAnio_ini[0].text = "Año";
		for (var i=1; i < (document.forms[0].comboAnio_ini.length); i++){
			document.forms[0].comboAnio_ini[i].text = 1930+ i;
			document.forms[0].comboAnio_ini[i].value = 1930+ i;
		}
		document.forms[0].comboAnio_ini[0].selected = true;

                var i=0;
		document.forms[0].comboMes_ini.clear;
		document.forms[0].comboMes_ini.length = 13;
                document.forms[0].comboMes_ini[0].text = "Mes";
		for (var i=1; i < (document.forms[0].comboMes_ini.length); i++){
			document.forms[0].comboMes_ini[i].text = i;
			document.forms[0].comboMes_ini[i].value =i;
		}
		document.forms[0].comboMes_ini[0].selected = true;

                var i=0;
		document.forms[0].comboDia_ini.clear;
		document.forms[0].comboDia_ini.length = 32;
                document.forms[0].comboDia_ini[0].text = "Dia";
		for (var i=1; i < (document.forms[0].comboDia_ini.length); i++){
			document.forms[0].comboDia_ini[i].text = i;
			document.forms[0].comboDia_ini[i].value =i;
		}
		document.forms[0].comboDia_ini[0].selected = true;

                var i=0;
		document.forms[0].comboAnio_fin.clear;
		document.forms[0].comboAnio_fin.length = 100;
                document.forms[0].comboAnio_fin[0].text = "Año";
		for (var i=1; i < (document.forms[0].comboAnio_fin.length); i++){
			document.forms[0].comboAnio_fin[i].text = 1930+ i;
			document.forms[0].comboAnio_fin[i].value = 1930+ i;
		}
		document.forms[0].comboAnio_fin[0].selected = true;

                var i=0;
		document.forms[0].comboMes_fin.clear;
		document.forms[0].comboMes_fin.length = 13;
                document.forms[0].comboMes_fin[0].text = "Mes";
		for (var i=1; i < (document.forms[0].comboMes_fin.length); i++){
			document.forms[0].comboMes_fin[i].text = i;
			document.forms[0].comboMes_fin[i].value =i;
		}
		document.forms[0].comboMes_fin[0].selected = true;

                var i=0;
		document.forms[0].comboDia_fin.clear;
		document.forms[0].comboDia_fin.length = 32;
                document.forms[0].comboDia_fin[0].text = "Dia";
		for (var i=1; i < (document.forms[0].comboDia_fin.length); i++){
			document.forms[0].comboDia_fin[i].text = i;
			document.forms[0].comboDia_fin[i].value =i;
		}
		document.forms[0].comboDia_fin[0].selected = true;

	}
        </script>

<script language="JavaScript" type="text/JavaScript">

function validar(){

   var error = "";
   var div_iL = document.getElementById("iL").value;
   var div_fL = document.getElementById("fL").value;
 
   var div_iE = document.getElementById("iE").value;
   var div_fE = document.getElementById("fE").value;

   var div_iV = document.getElementById("iV").value;
   var div_fV = document.getElementById("fV").value;

   if (((div_iL=="" && div_fL!="") || (div_iL!="" && div_fL=="")) ||((div_iE=="" && div_fE!="") || (div_iE!="" && div_fE==""))||((div_iV=="" && div_fV!="") || (div_iV!="" && div_fV==""))){
        error= error + "Uno de los Dividendos es Invalido\n";
       }
    else {
        if (div_iL!="" && div_fL!="") {
                var ini_L = parseFloat(div_iL);
                 var fin_L = parseFloat(div_fL);
                 if (isNaN(ini_L) || isNaN(fin_L)){
                        error= error + "Dividendos Local Invalidos\n";

                  }
         }
         if (div_iE!="" && div_fE!="") {
                var ini_E = parseFloat(div_iE);
                 var fin_E = parseFloat(div_fE);
                 if (!Number(ini_E) || !Number(fin_E)){
                        error= error + "Dividendos Empate Invalidos\n";

                 }
         }
         
         if (div_iV!="" && div_fV!="") {
                var ini_V = parseFloat(div_iV);
                 var fin_V = parseFloat(div_fV);
                 if (!Number(ini_V) || !Number(fin_V)){
                        error= error + "Dividendos Visitante Invalidos\n";

                       }
         }
    }
      
          if(document.forms[0].comboAnio_ini.selectedIndex==0){
                    if(document.forms[0].comboMes_ini.selectedIndex!=0 || document.forms[0].comboDia_ini.selectedIndex!=0){
                           error = error+ "Fecha Desde Invalida\n";
                    }
           }
           else if (document.forms[0].comboMes_ini.selectedIndex==0){
                   if(document.forms[0].comboAnio_ini.selectedIndex!=0 || document.forms[0].comboDia_ini.selectedIndex!=0){
                            error = error+ "Fecha Desde Invalida\n";
                    }
           }
           else if (document.forms[0].comboDia_ini.selectedIndex==0){
               if(document.forms[0].comboAnio_ini.selectedIndex!=0 || document.forms[0].comboMes_ini.selectedIndex!=0){
                    error = error+ "Fecha Desde Invalida\n";
                }
          }

          if(document.forms[0].comboAnio_fin.selectedIndex==0){
                    if(document.forms[0].comboMes_fin.selectedIndex!=0 || document.forms[0].comboDia_fin.selectedIndex!=0){
                           error = error+ "Fecha Hasta Invalida\n";
                    }
           }
           else if (document.forms[0].comboMes_ini.selectedIndex==0){
                   if(document.forms[0].comboAnio_fin.selectedIndex!=0 || document.forms[0].comboDia_fin.selectedIndex!=0){
                            error = error+ "Fecha Hasta Invalida\n";
                    }
           }
           else if (document.forms[0].comboDia_fin.selectedIndex==0){
               if(document.forms[0].comboAnio_fin.selectedIndex!=0 || document.forms[0].comboMes_fin.selectedIndex!=0){
                    error = error+ "Fecha Hasta Invalida\n";
                }
          }
          if (error==""){
              if (document.forms[0].comboAnio_fin.selectedIndex < document.forms[0].comboAnio_ini.selectedIndex){
                     error= error + "Error al Ingresar Fechas: Fecha Hasta < Fecha Desde\n";
               }
              else if (document.forms[0].comboAnio_fin.selectedIndex == document.forms[0].comboAnio_ini.selectedIndex) {
                    if (document.forms[0].comboMes_fin.selectedIndex < document.forms[0].comboMes_ini.selectedIndex) {
                           error= error + "Error al Ingresar Fechas: Fecha Hasta < Fecha Desde\n";
                    }
                    else if (document.forms[0].comboMes_fin.selectedIndex == document.forms[0].comboMes_ini.selectedIndex){
                           if (document.forms[0].comboDia_fin.selectedIndex < document.forms[0].comboDia_ini.selectedIndex){
                                 error= error + "Error al Ingresar Fechas: Fecha Hasta < Fecha Desde\n";
                           }
                    }
                }
               
          }
          if (error!=""){
             alert(error);
             inicializar();
             div = document.getElementById("flotante");
             if (div!=null){
                div.style.display="none";
             }
             return false;
          }

           return true;

  }
 </script>

    <style type="text/css">
        p, h1, form, button{border:0; margin:0; padding:0;}

             body{
                font-family:Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif;
                font-size:14px;
                background-color:#000000; background-image:url(images/fondomain.png); background-repeat:no-repeat;
                }

            .todo{
                  display: inline-block;
                  width: 100%;
                  
                }

        .superior{
            display: block;
            float: left;
           width:320px;
          /*height:180px;*/
          margin-left: 50px;
          background-color:#ffffff;
          border:1px solid black;
          opacity:0.7;
          filter:alpha(opacity=70); /* For IE8 and earlier */
          }


        .form1 {
              width: 320px;
              display:  block;
              background-color:  #CCCCCC;
              border-color:  #000;
              text-align:  center;
           }


               h1{
                
                  font-style: inherit;
                  font-size: 20px;
                  color: black;
                   text-align:  center;
                  }



                      .parts th{
                         border-style:  solid;
                         border-color: inherit;
                        text-align:  center;
                        font-family:  cursive;
                         font-size: 10px;
                        background-color: #960e27;
                        color :  white;

                        }


                       .parts td{
                           width: 180px;
                           text-align:  center;
                          }
                 button{
                clear:both;
                width:125px;
                height:31px;
                background:#666666 url(../../../../../../../Desktop/css/img/button.png) no-repeat;
                text-align:center;
                line-height:31px;
                color:#FFFFFF;
                font-size:11px;
                font-weight:bold;
                margin:  auto;
                }

                .titulos{
                    border-bottom-color:  #000;
                    border-bottom-style:  double;
                    color: #000;
                   
                }

                .noms{
                   font-size:70%;
                    
                  }

                  .entradas{

                     margin-left: 15px;
                      }

            .partidos{
              display: inline-block;
              background-color:#ffffff;
              border:1px solid black;
              opacity:0.6;
              filter:alpha(opacity=60); /* For IE8 and earlier */
              border-style:  ridge;
              border-color:   #000000;
          }

          .l{
              
              color: #000;
              text-align: center;
            }

            .entradas_2 input[type="text"]{
                width: 30px;
            }

            .divs{
                display: inline-block;
                text-align:  center;
              }
              .Local{
                  float: left;
                  display:  block;
                  text-align:  center;
                }
                .Empate{
                   float: right;
                  display:  block;
                  text-align: center;
                    }
                    .Visitante{
                        float: right;
                  display:  block;
                  text-align:  center;

                }


                
    </style>

    
    <body>
           <body onload ="inicializar()">
               <br><div class="todo">
               <div class="superior">
                   <h1> Filtrado de Partidos</h1>
        <form  onsubmit= "return validar();" class="form1" name="form1" action="/detPart">
                 <span class="titulos">
                     <label> Datos del Partido </label>
                 </span><br><br>
                 <span class="noms">
                         <label> Nombre Competicion </label>
                 </span>
                 <span class="entradas">
                             <input type="text" name="nomComp">
                 </span><br>
                 <span class="noms">
                         <LABEL>Nombre Equipo </LABEL>
                 </span>
            <span class="entradas">
                             <INPUT type="text" name="nomEquipo">
                 </span><BR>
                 <span class="noms">
                         <label> Lugar del Partido</label>
                 </span>
                 <span class="entradas">
                             <input type="text" name="lugar">
                 </span><br><br>


             

                 <span class="titulos">
                     <label>Dividendos</label>
                 </span><br><br>
              <span class="divs">
                 <span class="Local">
                 <span class ="l">
                         <label>Local</label><br>
                 </span>
                 <span class ="noms">
                         <label>Desde: </label>
                 </span>
                 <span class="entradas_2">
                      <input id="iL"  type="text" name="div_ini_Local" value="">
                 </span><br>
                 <span class="noms">
                     <label > Hasta: </label>
                 </span>
                 <span class="entradas_2">
                              <input  id="fL" type="text" name="div_fin_Local" value="">
                   </span><br><br>
                 </span>
                  <span class="Empate">
                   <span class ="l">
                         <label>Empate</label><br>
                 </span>
                   <span class ="noms">
                         <label>Desde: </label>
                 </span>
                 <span class="entradas_2">
                      <input id="iE"  type="text" name="div_ini_Empate" value="">
                 </span><br>
                 <span class="noms">
                     <label > Hasta: </label>
                 </span>
                 <span class="entradas_2">
                              <input  id="fE" type="text" name="div_fin_Empate" value="">
                   </span><br><br>
                  </span>
                  <span class="Visitante">
                   <span class ="l">
                         <label>Visitante</label><br>
                 </span>
                   <span class ="noms">
                         <label>Desde: </label>
                 </span>
                 <span class="entradas_2">
                      <input id="iV"  type="text" name="div_ini_Visitante" value="">
                 </span><br>
                 <span class="noms">
                     <label > Hasta: </label>
                 </span>
                 <span class="entradas_2">
                              <input  id="fV" type="text" name="div_fin_Visitante" value="">
                   </span>
                  </span>
              </span><br><br>


                 <span class="titulos">
                           <label>Fecha del Partido </label>
                        </span><br><br>
                 <span class="noms">
                           <label >   Desde : </label>
                           </span>
                                <select name="comboDia_ini"></select>
                                <select name="comboMes_ini"></select>
                                <select name="comboAnio_ini"></select><br>
                            <span class="noms">
                                <label> Hasta : </label>
                             </span>
                                <select name="comboDia_fin"></select>
                                <select name="comboMes_fin"></select>
                                <select name="comboAnio_fin"></select><br><br>
                     <span class="titulos">
                         <label > Estado del partido  </label>
                     </span><br><br>
                                <span class="noms">
                         <label > Pendiente : <input type="radio" name="estado" value="pend">
                               </label>
                          <label"> Finalizado : <input type="radio" name="estado" value="fin">
                               </label>
                                </span>
                  <br><br><br>
                 <button  class ="botn"  type=”submit” name="casoUso"> Filtrar</button><br><br
        </form>

        </div><br><br><br><br>
                         
                       





            
          
            <div id="flotante" class="partidos">
                <% List<DataPartidoComp> l = (List)request.getAttribute("listaPartidos");
               if (l!=null && !l.isEmpty()){
            %> 
            <table class="parts" >

            <tr>
             <th>Id Competicion</th>
             <th>Nombre Competicion</th>
             <th>ID Partido</th>
            </tr>

 
                <%  for (DataPartidoComp dp : l){

                %>
             <tr onMouseOver="this.bgColor = '#ffd2a6';" onMouseOut="this.bgColor = '#ffffff';" >
                <td>
                    <% out.print(dp.getIdComp());%></td>
                <td><%out.print(dp.getNombreComp());%></td>
                <td> <a href="/partido?idComp=<% out.print(Integer.toString(dp.getIdComp()));%>&idPart=<%out.print(Integer.toString(dp.getIdPart()));%>"> <%out.print(dp.getIdPart());%></a></td>
            
                <%}%>
                </table>
            <%}%>

            
        </div>
        
           </div>

        </body>
</html>
