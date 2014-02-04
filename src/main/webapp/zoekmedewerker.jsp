<%-- 
    
  Copyright 2014 Hogeschool Leiden.
 
  Licensed under the Apache License, Version 2.0 (the "License");
  you may not use this file except in compliance with the License.
  You may obtain a copy of the License at
 
       http://www.apache.org/licenses/LICENSE-2.0
 
  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS,
  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
  See the License for the specific language governing permissions and
  limitations under the License.

  Document   : zoekpagina
  Created on : 28-mrt-2013, 10:21:57
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
        <form method="post"  action="Employee" class="formatHTML5">
            
            <h2>Zoek een medewerker: </h2>
            Voer een achternaam (minimaal 2 tekens) in: <input type="text" name="achternaam" placeholder="achternaam"><br>
            <input type="text" name="max" placeholder="max"><br>
            <input type="text" name="offset" placeholder="offset"><br>
            <p><input type="submit" >
        </form>
        
    </body>
</html>
