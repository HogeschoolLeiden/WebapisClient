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

    Document   : addEmployee
    Created on : Jul 16, 2013, 11:35:49 AM
    Author     : hl
--%>

<%@page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='style.css' type='text/css'>
        <title>Medewerker</title>
    </head>
    <body>
        <header class="formatHTML5">Voeg medewerker toe</header>
        <form method="post"  action="InsertEmployee" class="formatHTML5">
            
            <h2>Voer een medewerker in, velden met * zijn verplicht</h2>
            <table>
                <tr>
                    <td>Id*</td><td> <input type="text" name="id" placeholder="id"><td>
                </tr>
                <tr>
                    <td>Achternaam* </td><td><input type="text" name="achternaam" placeholder="achternaam"></td>
                 </tr>
                <tr>
                    <td>Voornamen </td><td><input type="text" name="voornamen" placeholder="voornamen"></td>
                           </tr>
                <tr>
                    <td>Lijstnaam* </td><td><input type="text" name="lijstnaam" placeholder="lijstnaam"></td>
                           </tr>
                <tr>
                    <td>Tussenvoegsels </td><td><input type="text" name="tussenvoegsels" placeholder="tussenvoegsels"></td>
                           </tr>
                <tr>
                    <td>Geslacht </td><td><input type="text" name="geslacht" placeholder="geslacht"></td>
                           </tr>
                <tr>
                    <td>Email </td><td><input type="text" name="email" placeholder="email"></td>
                           </tr>
                <tr>
                    <td>Telefoon </td><td><input type="text" name="telefoon" placeholder="telefoon"></td>
                           </tr>
                <tr>
                    <td>Mobiel  </td><td><input type="text" name="mobiel" placeholder="mobiel"></td>
                           </tr>
                <tr>
                    <td>Afdeling  </td><td><input type="text" name="afdeling" placeholder="afdeling"></td>
                           </tr>
                <tr>
                    <td>Locatie  </td><td><input type="text" name="locatie" placeholder="locatie"></td>
                           </tr>
                <tr>
                    <td>Functie  </td><td><input type="text" name="functie" placeholder="functie"></td>
                           </tr>
                <tr>
                    <td>Medewerkernummer  </td><td><input type="text" name="medewerker" placeholder="medewerker"></td>
                           </tr>
                <tr>
                    <td>Accountnaam* </td><td><input type="text" name="accountnaam" placeholder="accountnaam"></td>
                           </tr>
                <tr>
                    <td>Organisatie* </td><td><input type="text" name="organisatie" placeholder="organisatie"></td>
                </tr>
            </table>
            <p><input type="submit" >
        </form>
        
    </body>
</html>