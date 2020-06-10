<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Owner Home Page</title>
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
<!--        <style>
        body {
          background-color: #76b852;
        }
        input {
          width: 50%;
          padding: 12px 20px;
          margin: 8px 0;
          display: inline-block;
          border: 1px solid #ccc;
          border-radius: 4px;
          box-sizing: border-box;
          background-color: #ffffff;
        }
        input[type=submit] {
          width: 100%;
          background-color: #4CAF50;
          color: white;
          padding: 14px 20px;
          margin: 8px 0;
          border: none;
          border-radius: 4px;
          cursor: pointer;
        }
        input[type=submit]:hover { background-color: #45a049; }
        div {
                  position: fixed;
                  top: 50%;
                  left: 50%;
                  transform: translate(-50%, -70%);
                  border-radius: 5px;
                  background-color: #f2f2f2;
                  padding: 40px;
                  width:15%;
                }
                .center{
                text-align:center;
                }
                a:link{
                        font-size: 14px;
                        color: green;
                }
        </style>-->
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
        <center>
        <div class="w3-left w3-cyan w3-hide-small">
            Owner : <b>${user}</b>
        </div>
        <br><br>
        <form action ="newgarage.jsp" method = "post">
            <input type ="submit" style="width: 400px;" value="Add a Garage">
        </form>
        <br><br>
        <form action ="removegarage.jsp" method = "post">
            <input type ="submit" style="width: 400px;" value="Remove Garage">
        </form>
        <br><br>
        <form action ="selectgarage.jsp" method = "post">
            <input type ="submit" style="width: 400px;" value="Update Garage">
        </form>
        <br><br>
        <form action ="bookings.jsp" method = "post">
            <input type ="submit" style="width: 400px;" value="Manage Bookings">
        </form>
        </center>
        
        
        
    </body>
</html>
