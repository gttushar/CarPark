<%@page import="com.mycompany.tushar.Slot"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="slotsBean" class="com.mycompany.tushar.getslotsbean" /><!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Booking</title>
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
    <center>
        <% String garage = request.getParameter("garage"); %>
            <br>
            <form action="addbooking" method="post">
            &emsp;Garage Name : <input type ="text" name ="garage" value="<%=garage%>" readonly="true"> <br>
            &emsp;Enter Customer Userid : <input type ="text" name ="customer"> <br>
            &emsp;Enter Phone : <input type ="text" name ="phone"> <br>
            &emsp;Enter Model : <input type ="text" name ="model"> <br>
            &emsp;Enter Slotno : <input type ="text" name ="slotno"> <br>
            &emsp;Enter Start Date (YYYY/MM/DD (space) HH:MM:SS ) : <input type ="text" name ="start"> <br>
            &emsp;Enter End Date (YYYY/MM/DD (space) HH:MM:SS) : <input type ="text" name ="end"> <br>
            &emsp;Is payment done (yes/no) : <input type ="text" name ="paid"> <br><br>
            <input type="submit" style="width:150px;margin:10px;" value="Add Booking">
            </form>
        
    <%
           List<Slot> slots = slotsBean.getslots(garage);
            //List<Slot> getslots = (List<Slot>)request.getAttribute("slots");
            //out.println("<br>No.of slots = " + slots.size());
            //out.println("<br>No.of getslots = " + getslots.size());
            if(slots.size()>0)
            {
        %>
        <br>
        <table>
            Available Slots<br>
            <tr>
                <td>SlotNo.</td><td>Size</td><td>Availability</td><td>Price</td>
            </tr>
            
            <%for(int i=0;i<slots.size();i++){
            %>
                    <tr>
                        <td><%=slots.get(i).slotno%></td>
                        <td><%=slots.get(i).size%></td>
                        <td><%=slots.get(i).availability%></td>
                        <td><%=slots.get(i).price%></td>
                    </tr>
            <%}%>
        </table>
        <% 
            }
            else
                out.println("No Slots Added in "+garage);
            %>
        
      </center>
    </body>
</html>
