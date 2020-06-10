<%@page import="com.mycompany.tushar.City.Garage"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:useBean id="City" class="com.mycompany.tushar.City" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Garage Search</title>
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
        <br>
    <center>
        Which place are you nearest to? <br>
        <br>
        <form action ="garagesearch" method="post">
            <select name="mark"style="width:200px" >
                <option selected="selected">-Select-</option>
                <option value="0">MainBuilding</option> <option value="1">Netaji</option> <option value="2">Tikka</option>
                <option value="3">MT</option> <option value="4">TGH</option> <option value="5">PANloop</option>
                <option value="6">HJB</option> <option value="7">SNIG</option> <option value="8">VS</option>
                <option value="9">LBS</option> <option value="10">LLR</option> <option value="11">MS</option>
                <option value="12">RP</option> <option value="13">ECdept</option> <option value="14">MEdept</option>
                <option value="15">CYdept</option> <option value="16">NehruMuseum</option> <option value="17">Nalanda</option>  
            </select>
            <br><br>
            <input type="submit" style="width:100px" value="Find">
        </form>
        <%
            int mark;
            List<Garage> garages = new ArrayList<Garage>();
            if (request.getParameterMap().containsKey("mark")) 
            {   
                mark = Integer.parseInt(request.getParameter("mark"));
                garages = (List<Garage>)City.searchgarages(mark);
            }
            if(request.getParameterMap().containsKey("landmark"))
                out.print("Landmark chosen ="+request.getAttribute("landmark").toString());
            if(garages.size()>0)
                {
        %>
        <br><br>
        <table>
            <caption><b>Nearby Garages (based on distance)</b></caption>
            <tr>
                <td>Name</td><td>Owner</td><td>Address</td><td>Landmark</td><td>Phone No.</td><td>Rating</td>
            </tr>
        <%
                    for(int i=0;i<garages.size();i++)
                    {
        %>           
            <tr>
                <td><%=garages.get(i).name%></td>
                <td><%=garages.get(i).owner%></td>
                <td><%=garages.get(i).address%></td>
                <td><%=garages.get(i).landmark%></td>
                <td><%=garages.get(i).phone%></td>
                <td><%=garages.get(i).rating%></td>
            </tr>
                        
            <%
                    }
                }
            %>
                
        </table>
       </center>     
    </body>
</html>
