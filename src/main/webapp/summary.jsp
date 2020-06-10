<%@page import="java.util.Date"%>
<%@page import="com.mycompany.tushar.Booking"%>
<%@page import="java.util.List"%>
<%@page import="java.util.regex.*"%>
<%@page import="java.text.*"%>
<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="java.time.LocalDateTime"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:useBean id="bookingsbean" class="com.mycompany.tushar.bookingsbean" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Summary of Bookings</title>
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
        <div class="w3-right w3-hide-small">
            <%
            String owner = session.getAttribute("user").toString();
            out.println("CURRENT DATETIME : "+ new Date().toString());
            %>
        </div>
    <center>
        <br><br>
        <%
            //Pattern p = Pattern.compile("(?:[0-9]{2})?[0-9]{2}/[0-1]")
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            df.setLenient(false);
            String start = null,end = null;
            
                start = request.getParameter("start");
                end = request.getParameter("end");
           
            List<Booking> summary = bookingsbean.getsummary(start, end, owner);
            //List<Slot> getslots = (List<Slot>)request.getAttribute("slots");
            //out.println("<br>No.of slots = " + slots.size());
            //out.println("<br>No.of getslots = " + getslots.size());
            if(summary.size()>0)
            {
        %>
        <br>
        <b>Bookings : Summary</b>
        <table>
            <tr>
                <td>BookingNo</td><td>CustomerId</td><td>PhoneNo</td><td>Garage</td><td>Model</td><td>SlotNo</td><td>Booking Time</td><td>Booking Ends</td><td>Cost (in Rs.)</td><td>Paid</td>
            </tr>
            
            <%for(int i=0;i<summary.size();i++){
            %>
                    <tr>
                        <td><%=summary.get(i).number%></td>
                        <td><%=summary.get(i).customer%></td>
                        <td><%=summary.get(i).phone%></td>
                        <td><%=summary.get(i).garage%></td>
                        <td><%=summary.get(i).model%></td>
                        <td><%=summary.get(i).slotno%></td>
                        <td><%=summary.get(i).start%></td>
                        <td><%=summary.get(i).end%></td>
                        <td><%=summary.get(i).cost%></td>
                        <td><%=summary.get(i).paid%></td>
                    </tr>
            <%}%>
            <br><br>
        </table>
        <% 
            }
            else
                out.println("<br>No Bookings Found!!");
        %>
        </center>
    </body>
</html>
