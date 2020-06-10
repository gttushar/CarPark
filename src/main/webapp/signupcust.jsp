<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Signup</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">   
    </head>
    <body>
        <!-- Navbar (sit on top) -->
        <div class="w3-top">
          <div class="w3-bar w3-white w3-wide w3-padding w3-card">
            <a href="login.jsp" class="w3-bar-item w3-button"><b>CARPARK</b></a>
            <!-- Float links to the right. Hide them on small screens -->
            <div class="w3-right w3-hide-small">
              <a href="about.jsp" class="w3-bar-item w3-button">About</a>
            </div>
          </div>
        </div>
        <br><br><br>
    <center>
       <center><h3>New Customer</h3></center><br>
        <form action ="signupcust" method = "post">
            Enter your name (max 30 characters)       : <input type ="text" name ="name"> <br>
            Enter Username (max 10 characters)        : <input type ="text" name ="user"> <br>
            Enter Password  (max 10 characters)        : <input type ="text" name ="pass"> <br>
            Re-Enter Password (max 10 characters) : <input type ="text" name ="rpass"> <br>
            Enter 10digit Phone no. (without leading 0): <input type ="text" name ="phone"> <br><br>
            <input type ="submit" value="Create Customer account">
        </form>
</center>
    </body>
</html>
