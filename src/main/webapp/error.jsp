<%-- 
    Document   : error
    Created on : 22-apr-2013, 15:25:27
    Author     : fadministrator
--%>

<%@ page isErrorPage = "true"%>
   
<html>
    <head>
        <link rel='stylesheet' href='style.css' type='text/css'>   
    </head>
    <body>
        <section class="formatHTML5">
        <h2>Your application has generated an error</h2>
        <h3>Please check for the error given below</h3>
        <b>Exception:</b><br> 
        <font color="red"><%= exception.toString()%></font>
        
        <br>
        
        <font color="red"><%= exception.getMessage()%></font>
        </section>
    </body>
</html>