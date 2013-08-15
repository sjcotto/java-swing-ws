

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import= "WebServices.CompeticionesWS.*"%>
<%@page import ="java.util.List" %>
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
     .penca{
       display: inline-block;
      margin-top: 20px;
       margin-left: 10px;
      margin-right: 20px;
      text-align: center;
       }

      h1{
           color: #ffffff;
           text-align: center;
          }

          .campoNombre{
              display: inline-block;
            }

        </style>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pencas</title>
    </head>
    <body>
        <h1> Pencas</h1>
        <div class="todo">
            
        <%List<DataIdNombre> pencas = (List)request.getAttribute("pencas");
            if (pencas!=null && !pencas.isEmpty()){
                for (DataIdNombre pen: pencas) {%>
                <div class="penca">
                    <div class="campoNombre">
                        <%if (pen.getImagePath().equals("")){%>
                <a href="?idComp=<% out.print(Integer.toString(pen.getId()));%>&nomComp=<%out.print(pen.getNombre());%>">
                        <img src="/imagen?path=../images/compDefecto.png" alt="Foto Competicion">
                    </a><br>
                    
                    <a href="?idComp=<%=Integer.toString(pen.getId())%>&nomComp=<%out.print(pen.getNombre());%>" ><%= pen.getNombre()%></a>
                    <%}else {%>
                         <a href="?idComp=<% out.print(Integer.toString(pen.getId()));%>&nomComp=<%out.print(pen.getNombre());%>">
                             <img src="/imagen?path=<%out.println(pen.getImagePath());%>" alt="Foto Competicion">
                    </a><br>
                    
                    <a href="?idComp=<%=Integer.toString(pen.getId())%>&nomComp=<%out.print(pen.getNombre());%>" ><%= pen.getNombre()%></a>
                    <%}%>
                   </div>
                </div>
         <%}
             } else{%>
             <div>
                 No hay Pencas Disponibles En El Sistema.
             </div>
             <%}%>
                
         </div>
    </body>
</html>
