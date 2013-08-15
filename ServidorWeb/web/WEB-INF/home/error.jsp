<%-- 
    Document   : error
    Created on : 17/09/2011, 11:02:50 AM
    Author     : Santiago
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <style type="text/css">
        body{
                font-family:Lucida Grande, Lucida Sans Unicode, Verdana, Arial, Helvetica, sans-serif;
                font-size:14px;
                background-color:#000000; background-image:url(images/fondomain.png); background-repeat:no-repeat;
            }
         
       .todo{
            border-style:  ridge;
            border-color:  #000;
             background-color:#ffffff;
            border:1px solid black;
            opacity:0.6;
            filter:alpha(opacity=60);
            width: 800px;
            margin-left: 70px;
            text-align: center;
        }

        .error{
           font-size: 30px;
             }


    </style>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>



    <body>
        <div class="todo">
        <h1>Se ha producido un error:</h1>
        <div class="error">
            <% String msg = (String) request.getAttribute("mensaje");
            out.print(msg);
        %>
        </div>
        </div>
    </body>
</html>
