<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.mycompany.tushar.Slot" %>
<jsp:useBean id="slotsBean" class="com.mycompany.tushar.getslotsbean" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Slots</title>
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
    <br><br><br><center>
        <%
            String regno=null,address=null,landmark=null;
            String name = session.getAttribute("name").toString();
            out.println("<br>&emsp;&emsp;&emsp;Name : <b>" + name +"</b>");
            if(session.getAttribute("regno") != null){
                regno = session.getAttribute("regno").toString();
                out.println("<br>&emsp;&emsp;&emsp;Registration Number : <b>" + regno +"</b>");
            }
            if(session.getAttribute("address") != null){
                address = session.getAttribute("address").toString();
                out.println("<br>&emsp;&emsp;&emsp;Address : <b>" + address +"</b>");
            }
            if(session.getAttribute("landmark") != null){
                landmark = session.getAttribute("landmark").toString();
                out.println("<br>&emsp;&emsp;&emsp;Nearest Landmark : <b>" + landmark +"</b>");
            }
        %>    
        <br><br><br>
        
        <b>Add Slot:</b><br><br>   
        <form action ="addslots" method = "post"> 
             Select Size       : &emsp;&emsp;&emsp;
            <select name="size">
                <option value="1">Small / 1</option>
                <option value="2">Medium / 2</option>
                <option value="3">Large / 3</option>
                <option value="4">XLarge / 4</option>
            </select>
            <br>
            Enter Price per hour in Rs. : <input type ="text" name ="price"> <br>
            <input type ="submit" style="width: 200px" value="Add another Slot">  
        </form>
        <br>
        <form action="newgarage" method="post">
            <input type="submit" style="width: 200px" value="Finish">
        </form>
        <br><br>
        <%
            //out.println(name);
            List<Slot> slots = slotsBean.getslots(name);
            //List<Slot> getslots = (List<Slot>)request.getAttribute("slots");
            //out.println("<br>No.of slots = " + slots.size());
            //out.println("<br>No.of getslots = " + getslots.size());
            if(slots.size()>0)
            {
        %>

        <table>
            <tr>
                <td>Garage</td><td>SlotNo.</td><td>Size</td><td>Availability</td><td>Price</td>
            </tr>
            
            <%for(int i=0;i<slots.size();i++){
            %>
                    <tr>
                        <td><%=slots.get(i).garage%></td>
                        <td><%=slots.get(i).slotno%></td>
                        <td><%=slots.get(i).size%></td>
                        <td><%=slots.get(i).availability%></td>
                        <td><%=slots.get(i).price%></td>
                    </tr>
            <%}
            }%>
            
        </table>
        </center>
    </body>
</html>
