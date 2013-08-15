<%-- 
    Document   : RegistroUsuario
    Created on : 02/09/2011, 05:34:20 PM
    Author     : gonzalo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registro Usuario</title>

        <style type="text/css">

                 body {background-color:#000000; background-image:url(images/fondomain.png); background-repeat:no-repeat;}

                  div.transbox
                  {
                  
                
                  margin:0 auto;
                  width:400px;
                

                  background-color:#ffffff;
                  border:1px solid black;
                  opacity:0.8;
                  filter:alpha(opacity=60); /* For IE8 and earlier */
                  }

          
                body{
                font-family:Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif;
                font-size:12px;
                }
                p, h1, form, button{border:0; margin:0; padding:0;}
                .spacer{clear:both; height:1px;}
                /* ���� My Form ���� */
                .myform{
                margin:0 auto;
                width:400px;
                padding:14px;
                }

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


        </style>
    </head>

    <script language="JavaScript" type="text/JavaScript">
	function inicializar(){
		var i=0;
		document.forms[0].comboAnio.clear;
		document.forms[0].comboAnio.length = 100;
                document.forms[0].comboAnio[0].text = "Año";
		for (var i=1; i < (document.forms[0].comboAnio.length); i++){
			document.forms[0].comboAnio[i].text = 1911+ i;
			document.forms[0].comboAnio[i].value = 1911+ i;
		}
		document.forms[0].comboAnio[0].selected = true;

                var i=0;
		document.forms[0].comboMes.clear;
		document.forms[0].comboMes.length = 13;
                document.forms[0].comboMes[0].text = "Mes";
		for (var i=1; i < (document.forms[0].comboMes.length); i++){
			document.forms[0].comboMes[i].text = i;
			document.forms[0].comboMes[i].value =i;
		}
		document.forms[0].comboMes[0].selected = true;

                var i=0;
		document.forms[0].comboDia.clear;
		document.forms[0].comboDia.length = 32;
                document.forms[0].comboDia[0].text = "Dia";
		for (var i=1; i < (document.forms[0].comboDia.length); i++){
			document.forms[0].comboDia[i].text = i;
			document.forms[0].comboDia[i].value =i;
		}
		document.forms[0].comboDia[0].selected = true;
	}
        </script>

 <script language="JavaScript" type="text/JavaScript">
        function validarFormulario(){
            //funcion encargada de validar el formulario
            //chequeamos q el nombre y el nick y el mail no sean vacios y que las contraseñas sean iguales
            //y que alla ingresado una fecha

            var mensajeError = "";
            var nombre = document.getElementById("idNombre");
            var mail = document.getElementById("usermail");
            var nick = document.getElementById("userid");
            var pass1 = document.getElementById("idPass1");
            var pass2 = document.getElementById("idPass2");

            if (nombre.value=="")
                mensajeError = mensajeError + "Nombre vacio\n";
            if (mail.value=="")
                mensajeError = mensajeError + "E-mail vacio\n";
            if (nick.value=="")
                mensajeError = mensajeError + "Nick vacio\n";
            if (pass1.value=="")
                mensajeError = mensajeError + "Debe ingresar una clave\n";
            if (pass1.value != pass2.value){
                mensajeError = mensajeError + "Contraseñas distintas!!\n";
            }

            //hay q ver si la fecha es valida
            if ((document.forms[0].comboAnio.selectedIndex==0) || (document.forms[0].comboDia.selectedIndex==0) || (document.forms[0].comboMes.selectedIndex==0)){
                mensajeError = mensajeError + "Fecha Invalida!!\n";
            }

            
            if (mensajeError!=""){
                alert("Se han producido los siguientes errores:\n" + mensajeError);
                return false;
            }else{
                return true;
            }

        }
</script>

    <body onload ="inicializar()">


<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<style type="text/css">
 .bp_invalid {
    color:white;
    display:block;
    font-size:12px;
    text-align:center;
    width:140px;
    background:red;
    font-weight:normal;
 }
 .bp_valid {
    color:white;
    display:block;
    font-size:12px;
    text-align:center;
    width:140px;
    background:green;
    font-weight:normal;
 }
</style>
<script type="text/javascript">

function AJAXInteraction(url, callback) {

    var req = init();
    req.onreadystatechange = processRequest;

    function init() {
      if (window.XMLHttpRequest) {
        return new XMLHttpRequest();
      } else if (window.ActiveXObject) {
        return new ActiveXObject("Microsoft.XMLHTTP");
      }
    }

    function processRequest () {
      // readyState of 4 signifies request is complete
      if (req.readyState == 4) {
        // status of 200 signifies sucessful HTTP call
        if (req.status == 200) {
          if (callback) callback(req.responseXML);
        }
      }
    }

    this.doGet = function() {
      req.open("GET", url, true);
      req.send(null);
    }
}

function validateUserId() {
    var target = document.getElementById("userid");

    var url = "validate?id=" + encodeURIComponent(target.value);
    var target = document.getElementById("userid");
    var ajax = new AJAXInteraction(url, validateCallback);
    ajax.doGet();
}

function validateUserEmail() {
    var target = document.getElementById("usermail");

    var url = "validate?mail=" + encodeURIComponent(target.value);
    var target = document.getElementById("usermail");
    var ajax = new AJAXInteraction(url, validateCallback);
    ajax.doGet();
}

function validateCallback(responseXML) {
       var msg = responseXML.getElementsByTagName("valid")[0].firstChild.nodeValue;


        if (msg == "falseN"){
           var mdiv = document.getElementById("userIdMessage");
           // set the style on the div to invalid
           mdiv.className = "bp_invalid";
           mdiv.innerHTML = "Nick No Disponible";
           var submitBtn = document.getElementById("submit_btn");
           submitBtn.disabled = true;
        } else if (msg == "trueN"){
           var mdiv = document.getElementById("userIdMessage");
           // set the style on the div to valid
           mdiv.className = "bp_valid";
           mdiv.innerHTML = "Nick Disponible";
           var submitBtn = document.getElementById("submit_btn");
           submitBtn.disabled = false;
        }else if (msg == "falseM"){
           var mdiv = document.getElementById("userMailMessage");
           mdiv.className = "bp_invalid";
           mdiv.innerHTML = "Mail No Disponible";
           var submitBtn = document.getElementById("submit_btn");
           submitBtn.disabled = true;
        }
        else if (msg == "trueM"){
           var mdiv = document.getElementById("userMailMessage");
           mdiv.className = "bp_valid";
           mdiv.innerHTML = "Mail Disponible";
           var submitBtn = document.getElementById("submit_btn");
           submitBtn.disabled = false;
        }
   
}
</script>


<div class="transbox">

<div id=stylized class=myform>
    <form onsubmit="return validarFormulario();" name=”form1” method=”post” action="registroU" >
    <h1>Regitrate! es Gratis!!</h1>
    <p>Ingrese sus Datos</p>
    <label>Nombre
    <span class=small>ingrese un nombre</span>
    </label>
    <input id ="idNombre" type="text" name="nombre"/>
    <label>CI
    <span class=small>ingrese cedula de identidad</span>
    </label>
    <input type="text" name="ci" />
    <label>Clave
    <span class=small>ingrese clave (*)</span>
    </label>
    <input id ="idPass1"type="password" name="password"/>
    <label>Clave
    <span class=small>Confirmar Clave (*)</span>
    </label>
    <input id="idPass2"type="password" name="password2"/>
    <label>E-Mail
    <span class=small id="userMailMessage" >ingresar mail (*)</span>
    </label>
    <input id="usermail" type="text" name="mail" onkeyup="validateUserEmail()"/>
    <label>Nick
    <span class=small id="userIdMessage" >ingresar nick (*)</span>
    </label>
    <input id="userid" type="text" name="nick" onkeyup="validateUserId()"/>

    <label>Direccion
    <span class=small>ingresar direccion</span>
    </label>
    <input type="text" name="dir"/>
    <label>Pais
    <span class=small> ingrese el si Pais</span>
    </label>
    <input type="text" name="pais"/>
    <label>Fecha de Nacimiento
    <span class=small></span>
    </label>

    <select name="comboDia"></select>
    <select name="comboMes">
    </select>
    <select name="comboAnio">
    </select>

    <br>

    <label>Hombre <input type="radio" name="sexo" value="hombre" checked>
    </label>
    <label>Mujer <input type="radio" name="sexo" value="mujer">
    </label>


    <button id="submit_btn" type=”submit”>Registrar</button>
    <div class=spacer></div>
    </form>
    </div>
</div>

    </body>
</html>
