<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add a garage</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">  
        <style>
            select{width:200px; margin:10px;}
            input{width:300px; margin:10px;}
        </style>
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
            <a href="ownerhome.jsp" class="w3-bar-item w3-button"><b>CARPARK</b> Home</a>
            <!-- Float links to the right. Hide them on small screens -->
            <div class="w3-right w3-hide-small">
              <a href="about.jsp" class="w3-bar-item w3-button">About</a>
              <a href="logout" class="w3-bar-item w3-button">Logout</a>
            </div>
          </div>
        </div>
        <br><br><br>
        <div class="w3-left w3-cyan w3-hide-small">
            Owner : <b>${user}</b>
        </div>
        <br><br><br><br><br><br>
        <form action ="newgaragesession" method = "post">
            <center>
            Enter name (max 30 characters) :     <input type ="text" name ="name" > <br>
            Enter Registration no.(10 characters) : <input type ="text" name ="regno"> <br>
            Enter Address (max 30 characters) :   <input type ="text" name ="address"> <br>
            Choose nearest Landmark : 
            <select name="landmark">
                <option value="MainBuilding">MainBuilding</option> <option value="Netaji">Netaji</option> <option value="Tikka">Tikka</option>
                <option value="MT">MT</option> <option value="TGH">TGH</option> <option value="PANloop">PANloop</option>
                <option value="HJB">HJB</option> <option value="SNIG">SNIG</option> <option value="VS">VS</option>
                <option value="LBS">LBS</option> <option value="LLR">LLR</option> <option value="MS">MS</option>
                <option value="RP">RP</option> <option value="ECdept">ECdept</option> <option value="MEdept">MEdept</option>
                <option value="CYdept">CYdept</option> <option value="NehruMuseum">NehruMuseum</option> <option value="Nalanda">Nalanda</option>  
            </select>
            <br>
            <input type ="submit" value="Add Slots in the Garage">  
            </center>
        </form>
    </body>
</html>
