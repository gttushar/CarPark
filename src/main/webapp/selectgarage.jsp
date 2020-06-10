<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="garagesBean" class="com.mycompany.tushar.getgaragesbean" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Garage Update</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">   
        <style>
        table {
  font-family: "Trebuchet MS", Arial, Helvetica, sans-serif;
  border-collapse: collapse;
}

table td, table th {
  border: 1px solid #ddd;
  padding: 8px;
}

table tr:nth-child(even){background-color: #f2f2f2;}

table tr:hover {background-color: #ddd;}

table th {
  padding-top: 12px;
  padding-bottom: 12px;
  text-align: left;
  background-color: #4CAF50;
  color: white;
}
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
        <br><br>
        <% 
            String user = session.getAttribute("user").toString();
            List<String> garages = garagesBean.getgarages(user);
            if(garages.size()>0)
            {
        %>
        <center><br><br><br><br>
            
        <h5>Select Garage to Update</h5>
        <form action ="selectgarage" method="post">
            <select name="name" style="width: 300px">
                <%for(int i=0;i<garages.size();i++){%>
                <option value="<%=garages.get(i)%>"><%=garages.get(i)%></option>
                <%}%>
            </select>&emsp;&emsp;
            <input type="submit" style="width:100px; margin:20px;" value="Update">
        </form>
        
        <%  }
            else
            { 
                out.println("<script type=\"text/javascript\">");
                out.println("alert('No garages to display!');");
                out.println("location='ownerhome.jsp';");
                out.println("</script>");
            }
        %>
        </center>
    </body>
</html>
