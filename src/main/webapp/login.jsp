<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CarPark - Online Car Parking System</title>
        <style>
        body {
          background-color: #76b852;
        }
        input {
          width: 100%;
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
        </style>
    </head>
    <body>
        <div>
        <h3 style="color:blue"><center><b>CarPark Sign-In</b></center></h3>
        <br>
        <form action ="login" method = "post">
            Enter Username : <input type ="text" name ="user"> <br>
            Enter Password : <input type ="text" name ="pass"> <br>
            <input type ="submit" name ="action" value="Login as Owner">
            <input type ="submit" name ="action" value="Login as Customer">
        </form>
        <br>
        <br>
<!--        <form action ="signupowner.jsp" method = "get">
            <input type ="submit" value="Signup as Owner">
        </form>
        <form action ="signupcust.jsp" method = "get">
            <input type ="submit" value="Signup as Customer">
        </form>-->
        <p class="center">
	<a  href="signupowner.jsp">Create Owner account</a> 
        </p>
        <p class="center">
	<a  href="signupcust.jsp">Create Customer account</a> 
        </p>
        </div>
    </body>
</html>