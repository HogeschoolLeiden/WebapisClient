<%-- 
    Document   : zoekpagina
    Created on : 28-mrt-2013, 10:21:57
    Author     : fadministrator
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='style.css' type='text/css'>
        <title>JSP Page</title>
    </head>
    <body>
        <header class="formatHTML5">Zoekpagina</header>
        <form method="post"  action="Student" class="formatHTML5">
            
            <h2>Zoek een student: </h2>
            Voer een achternaam (minimaal 2 tekens) in: <input type="text" name="achternaam" placeholder="achternaam"><br>
            <input type="text" name="max" placeholder="max"><br>
            <input type="text" name="offset" placeholder="offset"><br>
            <p><input type="submit" >
        </form>
        
    </body>
</html>
