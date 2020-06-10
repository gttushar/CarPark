<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a Vehicle</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">   
    </head>
    <body>
         <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); //HTTP 1.1
            //response.setHeader("Pragma", "no-cache");  HTTP older than 1.1
            //response.setHeader("Expires", "0");   For proxy server (cache expires in 0 sec)   
            if(session.getAttribute("user") == null)
                response.sendRedirect("login.jsp");
        %>
        <!-- Navbar (sit on top) -->
        <div class="w3-top">
          <div class="w3-bar w3-white w3-wide w3-padding w3-card">
            <a href="custhome.jsp" class="w3-bar-item w3-button"><b>CARPARK</b> Home</a>
            <!-- Float links to the right. Hide them on small screens -->
            <div class="w3-right w3-hide-small">
              <a href="about.jsp" class="w3-bar-item w3-button">About</a>
              <a href="logout" class="w3-bar-item w3-button">Logout</a>
            </div>
          </div>
        </div>
        <br><br><br>
        <div class="w3-left w3-cyan w3-hide-small">
            Customer : <b>${user}</b>
        </div>
        <br><br>
    <center>
        <h2>Add a vehicle</h2><br><br>
        <form action ="newvehicle" method = "post">
            Enter Model/Name of vehicle (max 30 characters) : <input type ="text" style="width: 300px;"  name ="model"> <br>
            Enter License no. (max 10 characters) : <input type ="text" style="width: 300px;" name ="license"> <br>
            Choose type of vehicle : 
                <select name="size">
                    <option value="1">Two-wheelers</option>
                    <option value="2">Sedan / convertible</option>
                    <option value="3">SUV / Mini-truck</option>
                    <option value="4">Bus / Truck</option>
                </select>
            <br><br>
            <input type ="submit" style="width: 200px;" value="Add vehicle">
        </form>
        </center>
    </body>
</html>
